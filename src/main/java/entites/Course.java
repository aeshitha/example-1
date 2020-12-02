package entites;

import java.util.HashMap;
import java.util.List;

public class Course {
    private String id;
    private String name;
    private String teacher;
    private String credit;
    private List<HashMap<String, String>> major;

    public Course(String id, String name, String teacher, String credit, List<HashMap<String,String>> major) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.credit = credit;
        this.major = major;
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

    public List<HashMap<String, String>> getMajor() {
        return major;
    }

    public void setMajor(List<HashMap<String,String>> major) {
        this.major = major;
    }

    public HashMap toMap() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", getName());
        data.put("teacher", getTeacher());
        data.put("credit", getCredit());
        data.put("major", getMajor());
        return data;
    }

}
