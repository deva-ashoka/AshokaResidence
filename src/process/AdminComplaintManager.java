package process;

import database.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AdminComplaintManager {
    public void getComplaints(String department){

        List<Complaint> allComplaints = new ArrayList<>();
        DatabaseManager db = new DatabaseManager();
        if(db.success.intern() == "success"){
            try {
                PreparedStatement statement = db.con.prepareStatement("SELECT \"userId\", location, issue, description, department, type, status, date FROM public.complaints WHERE status='unresolved' ORDER BY date asc;");
                
            }catch (SQLException se){
                se.printStackTrace();
            }
        }
        db.close();
    }
}
