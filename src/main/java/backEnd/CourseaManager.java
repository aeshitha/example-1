package backEnd;

import com.google.cloud.firestore.*;
import entites.Course;
import entites.User;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CourseaManager {
    public static boolean addcCourse(Course course) throws IOException, InterruptedException, ExecutionException {
        Firestore db = DBHandler.makeConnection();
        List<String> ids = getCourseIds(db);
        if (ids.contains(course.getId())) {
            return false;
        }
        DocumentReference ref = db.collection("courses").document(course.getId());
        DBHandler.saveData(course.toMap(), ref);
        return true;
    }

    public static Course getCourse(String courseID) throws ExecutionException, InterruptedException, IOException {
        Firestore db = DBHandler.makeConnection();
        System.out.println(db);
        DocumentReference ref = db.collection("courses").document(courseID);
        Course course;
        DocumentSnapshot doc = DBHandler.getDocument(ref);
        List<Integer> l = new ArrayList<>();


        course = new Course(courseID, doc.getString("name"), doc.getString("teacher"), doc.getString("credit"), (List<HashMap<String, String>>) doc.get("major"));
        if (course.getName() == null) return null;

        return course;
    }


    public static List<String> getCourseIds() throws IOException, ExecutionException, InterruptedException {
        Firestore db = DBHandler.makeConnection();
        CollectionReference ref = db.collection("courses");
        List<DocumentSnapshot> docs = DBHandler.getCollection(ref);
        List<String> courseIds = new ArrayList<>();
        for (int i = 0; i < docs.size(); i++) {
            DocumentSnapshot doc = docs.get(i);
            courseIds.add(doc.getId());
        }
        return courseIds;
    }

    public static List<String> getCourseIds(Firestore db) throws IOException, ExecutionException, InterruptedException {
        CollectionReference ref = db.collection("courses");
        List<DocumentSnapshot> docs = DBHandler.getCollection(ref);
        List<String> courseIds = new ArrayList<>();
        for (int i = 0; i < docs.size(); i++) {
            DocumentSnapshot doc = docs.get(i);
            courseIds.add(doc.getId());
        }
        return courseIds;
    }
    public static List<Course> getCourses(String majorId, String semester) throws IOException, ExecutionException, InterruptedException, ParseException {
        Firestore db = DBHandler.makeConnection();
        HashMap<String,String> map = new HashMap<>();
        map.put(majorId, semester);
        Query ref = db.collection("courses").whereArrayContains("major",map);
        List<DocumentSnapshot> docs = DBHandler.getCollection(ref);
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < docs.size(); i++) {
            DocumentSnapshot doc = docs.get(i);
            courses.add(docToCourse(doc));
        }
        return courses;
    }
    private static Course docToCourse(DocumentSnapshot doc) throws ParseException {
        return new Course(doc.getId(), doc.getString("name"), doc.getString("teacher"), doc.getString("credit"), (List<HashMap<String, String>>) doc.get("major"));
    }
}
