package backEnd;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DBHandler {

    private static Firestore db;
    public static InputStream keyStream = null;


    public static Firestore makeConnection() throws IOException, InterruptedException {

        if (null == db) {
            FirebaseOptions options = null;


            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials
                            .fromStream(

                                    DBHandler.class.getResourceAsStream("key.json")
                            )).build();


            if (FirebaseApp.getApps().isEmpty()) {
                try {
                    FirebaseApp.initializeApp(options);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            db = FirestoreClient.getFirestore();
        }
        return db;
    }

    public static void saveData(HashMap<String, Object> data, DocumentReference ref) throws
            ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = ref.set(data);
        System.out.println("successfully updated at : " + future.get().getUpdateTime());
    }

    public static void updateData(HashMap<String, Object> data, DocumentReference ref) throws Exception {
        ApiFuture<WriteResult> future = ref.update(data);
        System.out.println("successfully updated at : " + future.get().getUpdateTime());
    }

    public static DocumentSnapshot getDocument(DocumentReference ref) throws
            ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> future = ref.get();
        return future.get();
    }

    public static boolean deleteDocument(DocumentReference ref) throws ExecutionException, InterruptedException {
        ref.delete();
        return true;
    }

    public static List<DocumentSnapshot> getCollection(CollectionReference ref) throws
            ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = ref.get();
        QuerySnapshot docs = future.get();
        List<QueryDocumentSnapshot> l = docs.getDocuments();
        List<DocumentSnapshot> documentSnapshots = new ArrayList<DocumentSnapshot>();
        for (DocumentSnapshot i : docs) {
            documentSnapshots.add(i);
        }

        return documentSnapshots;
    }

    public static List<DocumentSnapshot> getCollection(Query ref) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = ref.get();
        QuerySnapshot docs = future.get();
        List<QueryDocumentSnapshot> l = docs.getDocuments();
        List<DocumentSnapshot> documentSnapshots = new ArrayList<DocumentSnapshot>();
        for (DocumentSnapshot i : docs) {
            documentSnapshots.add(i);
        }

        return documentSnapshots;
    }



}
