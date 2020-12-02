package backEnd.tableManager;

public class ACMajorTable {
    private String majorID;
    private String majorName;
    private String Semester;

    public ACMajorTable(String majorID, String majorName, String semester) {
        this.majorID = majorID;
        this.majorName = majorName;
        Semester = semester;
    }

    public String getMajorID() {
        return majorID;
    }

    public void setMajorID(String majorID) {
        this.majorID = majorID;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }
}
