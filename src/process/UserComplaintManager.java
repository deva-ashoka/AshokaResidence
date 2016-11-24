package process;

import database.DatabaseManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Class responsible for managing all complaints of departments. Can launch a complaint and check the status of a complaint.
 */
public class UserComplaintManager extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        if (request.getServletPath().intern() == "/launchComplaint") {
            //request for registering the complaint
            Complaint userComplaint = new Complaint();
            userComplaint.setDepartment(request.getParameter("department"));
            userComplaint.setType(request.getParameter("type"));
            userComplaint.setIssue(request.getParameter("issue"));
            userComplaint.setDescription(request.getParameter("issueDescription"));
            userComplaint.setLocation(request.getParameter("location"));
            userComplaint.setStatus("unresolved");
            userComplaint.setUserId("parasb");

            //out.println(getIssue());

            createComplaint(userComplaint);
            response.setHeader("Location", "myComplaints.jsp");
            /*RequestDispatcher view = request.getRequestDispatcher("/myComplaints.jsp");
            view.forward(request, response);*/
        }
    }

    private void createComplaint(Complaint userComplaint) {

        DatabaseManager db = new DatabaseManager();
        if (db.success.intern() == "success") {
            try {
                PreparedStatement statement = db.con.prepareStatement("INSERT INTO public.complaints(\"userId\", location, issue, description, department, type, status, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
                statement.setString(1, userComplaint.getUserId());
                statement.setString(2, userComplaint.getLocation());
                statement.setString(3, userComplaint.getIssue());
                statement.setString(4, userComplaint.getDescription());
                statement.setString(5, userComplaint.getDepartment());
                statement.setString(6, userComplaint.getType());
                statement.setString(7, userComplaint.getStatus());

                //get current date, since creating a new complaint
                Calendar calender = Calendar.getInstance();
                java.util.Date currentDate = calender.getTime();
                java.sql.Date date = new java.sql.Date(currentDate.getTime());

                statement.setDate(8, date);

                db.insert(statement);

            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        db.close();
    }

    /**
     * function returns list of all complaints (as Resultset claass) specified by the userId in the sessions variable.
     **/
    public List<Complaint> getUserComplaints(String userId) {
        DatabaseManager db = new DatabaseManager();
        List<Complaint> allComplaints = new ArrayList<>();
        if (db.success.intern() == "success") {

            try {
                PreparedStatement statement = db.con.prepareStatement("SELECT \"userId\", location, issue, description, department, type, status, date FROM public.complaints WHERE \"userId\" LIKE ? ORDER BY date desc;");
                statement.setString(1, userId);

                ResultSet complaintsRows = db.select(statement);
                while (complaintsRows.next()) {
                    Complaint singleComplaint = new Complaint();
                    singleComplaint.setLocation(complaintsRows.getString("location"));
                    singleComplaint.setIssue(complaintsRows.getString("issue"));
                    singleComplaint.setDepartment(complaintsRows.getString("department"));
                    singleComplaint.setDescription(complaintsRows.getString("description"));
                    singleComplaint.setType(complaintsRows.getString("type"));
                    singleComplaint.setUserId(complaintsRows.getString("userId"));
                    singleComplaint.setDateOfComplaint(complaintsRows.getDate("date"));
                    singleComplaint.setStatus(complaintsRows.getString("status"));

                    allComplaints.add(singleComplaint);
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        db.close();
        return allComplaints;
    }
}
//TODO:: remove form parameter task from userMenu.html