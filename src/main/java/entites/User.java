package entites;

import com.google.firebase.messaging.LightSettings;

import java.util.HashMap;
import java.util.List;

public class User {
    private String id;
    private String name;
    private String major;
    private Boolean type;
    private String password;
    private String enrolledYear;
    private String pn;
    private List<String> courseList;

    public User(String id, String name, String major, Boolean type, String password, String enrolledYear, String pn, List<String> courseList) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.type = type;
        this.password = password;
        this.enrolledYear = enrolledYear;
        this.pn = pn;
        this.courseList = courseList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getEnrolledYear() {
        return enrolledYear;
    }

    public void setEnrolledYear(String enrolledYear) {
        this.enrolledYear = enrolledYear;
    }

    public String getPN() {
        return pn;
    }

    public void setPN(String pn) {
        this.pn = pn;
    }

    public List<String> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<String> courseList) {
        this.courseList = courseList;
    }

    public HashMap toMap() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", getName());
        data.put("major", getMajor());
        data.put("type", getType());
        data.put("password", getpassword());
        data.put("enrolledYear", getEnrolledYear());
        data.put("pn", getPN());
        data.put("courseList",getCourseList());
        return data;
    }

}
