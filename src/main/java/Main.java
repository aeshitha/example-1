
import frontEnd.LoginController;
import frontEnd.TeacherMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(TeacherMenuController.class.getResource("Login.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        LoginController.stage = primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
