package process;

import java.sql.Date;

/**
 * This class represents students or anyone who files a complaint
 */
public class Complaint {
    private String userId;
    private String location;
    private String issue;
    private String description;
    private String department;
    private String type;
    private String status;
    private Date dateOfComplaint;

    public Date getDateOfComplaint() {
        return dateOfComplaint;
    }

    public void setDateOfComplaint(Date dateOfComplaint) {
        this.dateOfComplaint = dateOfComplaint;
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
