package frontEnd;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherMenuController implements Initializable {
    String wndowname = "Teacher Menu";
    public static Stage stage;
    public JFXButton btnAddCourse;
    public JFXButton btnAddUser;
    public JFXButton btnViewCourse;
    public JFXButton btnViewStd;
    public JFXButton btnExite;
    public Label lblMain;


    public void btnAddCourseOnKeyRelease(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            AddCourseController.stage = new Stage();
            Parent root = FXMLLoader.load(AddCourseController.class.getResource("addcCourse.fxml"));
            AddCourseController.stage.setScene(new Scene(root));
            AddCourseController.stage.show();
            stage.close();
        }
    }

    public void btnAddCourseOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        AddCourseController.stage = new Stage();
        Parent root = FXMLLoader.load(AddCourseController.class.getResource("addCourse.fxml") );
        AddCourseController.stage.setScene(new Scene(root));
        AddCourseController.stage.show();
        stage.close();
    }

    public void btnAddUserOnKeyRelease(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            AddUserController.stage = new Stage();
            Parent root = FXMLLoader.load(AddUserController.class.getResource("addUser.fxml"));
            AddUserController.stage.setScene(new Scene(root));
            AddUserController.stage.show();
            stage.close();
        }
    }

    public void btnAddUserOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        AddUserController.stage = new Stage();
        Parent root = FXMLLoader.load(AddUserController.class.getResource("addUser.fxml"));
        AddUserController.stage.setScene(new Scene(root));
        AddUserController.stage.show();
        stage.close();
    }

    public void btnViewCourseOnKeyReleased(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            ViewCourseController.stage = new Stage();
            Parent root = FXMLLoader.load(ViewCourseController.class.getResource("viewCourse.fxml"));
            ViewCourseController.stage.setScene(new Scene(root));
            ViewCourseController.stage.show();
            stage.close();
        }
    }

    public void btnViewCourseOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        ViewCourseController.stage = new Stage();
        Parent root = FXMLLoader.load(ViewCourseController.class.getResource("viewCourse.fxml"));
        ViewCourseController.stage.setScene(new Scene(root));
        ViewCourseController.stage.show();
        stage.close();
    }

    public void btnViewStdOnKeyRelease(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            ViewStudentController.stage = new Stage();
            Parent root = FXMLLoader.load(ViewStudentController.class.getResource("viewStudent.fxml"));
            ViewStudentController.stage.setScene(new Scene(root));
            ViewStudentController.stage.show();
            stage.close();
        }
    }

    public void btnViewStdOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        ViewStudentController.stage = new Stage();
        Parent root = FXMLLoader.load(ViewStudentController.class.getResource("viewStudent.fxml"));
        ViewStudentController.stage.setScene(new Scene(root));
        ViewStudentController.stage.show();
        stage.close();
    }

    public void btnExiteOnKeyRelease(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            stage.close();
        }
    }

    public void btnExiteOnMouseClicked(MouseEvent mouseEvent) {
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblMain.setText(wndowname);
        btnAddUser.requestFocus();
    }
}
