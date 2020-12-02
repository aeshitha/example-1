package entites;

import java.util.HashMap;

public class Major {
    private String id;
    private String name;

    public Major(String id, String name) {
        this.id = id;
        this.name = name;
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

    public HashMap toMap() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("majorName", getName());
        return data;
    }

    @Override
    public String toString() {
        return this.getId() + "-" + this.getName();
    }
}
