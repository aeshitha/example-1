package backEnd;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.storage.Acl;
import com.google.firebase.auth.internal.DownloadAccountResponse;
import entites.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserManager {
    public static boolean addUser(User user) throws IOException, InterruptedException, ExecutionException {
        Firestore db = DBHandler.makeConnection();
        List<String> ids = getUserIds(db);
        if (ids.contains(user.getId())) {
            return false;
        }
        DocumentReference ref = db.collection("users").document(user.getId());
        DBHandler.saveData(user.toMap(), ref);
        return true;
    }

    public static boolean deleteUser(String user) throws ExecutionException, InterruptedException, IOException {
        Firestore db = DBHandler.makeConnection();
        DocumentReference ref = db.collection("users").document(user);
        DBHandler.deleteDocument(ref);
        return true;
    }

    public static boolean updateUser(User user) throws ExecutionException, InterruptedException, IOException {
        Firestore db = DBHandler.makeConnection();
        List<String> ids = getUserIds(db);
        if (!ids.contains(user.getId())) {
            return false;
        }
        DocumentReference ref = db.collection("users").document(user.getId());
        DBHandler.saveData(user.toMap(), ref);
        return true;
    }
    public static User getUser(String userId) throws ExecutionException, InterruptedException, IOException {
        Firestore db = DBHandler.makeConnection();
        System.out.println(db);
        DocumentReference ref = db.collection("users").document(userId);
        User user;

        DocumentSnapshot doc = DBHandler.getDocument(ref);
        List<Integer> l = new ArrayList<>();


        user = new User(userId, doc.getString("name"), doc.getString("major"), doc.getBoolean("type"), doc.getString("password"), doc.getString("enrolledYear"), doc.getString("pn"), (List<String>) doc.get("courseList"));
        if(user.getName()==null) throw new NullPointerException();

        return user;
    }

    public static List<String> getUserIds() throws IOException, ExecutionException, InterruptedException {
        Firestore db = DBHandler.makeConnection();
        CollectionReference ref = db.collection("users");
        List<DocumentSnapshot> docs = DBHandler.getCollection(ref);
        List<String> userIds = new ArrayList<>();
        for (int i = 0; i < docs.size(); i++) {
            DocumentSnapshot doc = docs.get(i);
            userIds.add(doc.getId());
        }
        return userIds;
    }
    public static List<String> getUserIds(Firestore db) throws IOException, ExecutionException, InterruptedException {
        CollectionReference ref = db.collection("users");
        List<DocumentSnapshot> docs = DBHandler.getCollection(ref);
        List<String> userIds = new ArrayList<>();
        for (int i = 0; i < docs.size(); i++) {
            DocumentSnapshot doc = docs.get(i);
            userIds.add(doc.getId());
        }
        return userIds;
    }


}
