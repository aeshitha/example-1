package backEnd.tableManager;

public class SCCouseListTable {
    private String courseID;
    private String courseName;
    private String teacher;
    private String credit;

    public SCCouseListTable(String courseID, String courseName, String teacher, String credit) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.teacher = teacher;
        this.credit = credit;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }
}
