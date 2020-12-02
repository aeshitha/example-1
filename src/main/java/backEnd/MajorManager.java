package backEnd;

import com.google.cloud.firestore.*;
import entites.Course;
import entites.Major;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MajorManager {
    public static boolean addMajor(Major major) throws IOException, InterruptedException, ExecutionException {
        Firestore db = DBHandler.makeConnection();
        List<String> ids = getMajorIds(db);
        if (ids.contains(major.getId())) {
            return false;
        }
        DocumentReference ref = db.collection("majors").document(major.getId());
        DBHandler.saveData(major.toMap(), ref);
        return true;
    }

    public static Major getMajor(String majorID) throws ExecutionException, InterruptedException, IOException {
        Firestore db = DBHandler.makeConnection();
        System.out.println(db);
        DocumentReference ref = db.collection("majors").document(majorID);
        Major major;
        DocumentSnapshot doc = DBHandler.getDocument(ref);

        major = new Major(majorID, doc.getString("majorName"));
        if (major.getName() == null) throw new NullPointerException();

        return major;
    }


    public static List<String> getMajorIds() throws IOException, ExecutionException, InterruptedException {
        Firestore db = DBHandler.makeConnection();
        CollectionReference ref = db.collection("majors");
        List<DocumentSnapshot> docs = DBHandler.getCollection(ref);
        List<String> majorIds = new ArrayList<>();
        for (int i = 0; i < docs.size(); i++) {
            DocumentSnapshot doc = docs.get(i);
            majorIds.add(doc.getId());
        }
        return majorIds;
    }

    public static List<String> getMajorIds(Firestore db) throws IOException, ExecutionException, InterruptedException {
        CollectionReference ref = db.collection("majors");
        List<DocumentSnapshot> docs = DBHandler.getCollection(ref);
        List<String> majorIds = new ArrayList<>();
        for (int i = 0; i < docs.size(); i++) {
            DocumentSnapshot doc = docs.get(i);
            majorIds.add(doc.getId());
        }
        return majorIds;
    }

    public static List<Major> getMajors() throws IOException, ExecutionException, InterruptedException, ParseException {
        Firestore db = DBHandler.makeConnection();
        Query ref = db.collection("majors");
        List<DocumentSnapshot> docs = DBHandler.getCollection(ref);
        List<Major> majors = new ArrayList<>();
        for (int i = 0; i < docs.size(); i++) {
            DocumentSnapshot doc = docs.get(i);
            majors.add(docToMajor(doc));
        }
        return majors;
    }
    private static Major docToMajor(DocumentSnapshot doc) throws ParseException {
        return new Major(doc.getId(), doc.getString("majorName"));
    }

}
