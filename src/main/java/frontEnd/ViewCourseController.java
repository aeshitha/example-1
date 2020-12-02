package frontEnd;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewCourseController {
    public static Stage stage;
    public JFXComboBox cbMajor;
    public JFXListView lvCourse;
    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXTextField txtTeacher;
    public JFXTextField txtNoOfStudents;
    public JFXTextField txtCredit;
    public JFXButton btnCancel;

    public void cbMajorOnAction(ActionEvent event) {
    }

    public void txtIDOnAction(ActionEvent event) {
    }

    public void txtNameOnAction(ActionEvent event) {
    }

    public void txtTeacherOnAction(ActionEvent event) {
    }

    public void txtNoOfStudentsOnAction(ActionEvent event) {
    }

    public void txtCreditOnAction(ActionEvent event) {
    }

    public void btnCancelOnAction(ActionEvent event) throws IOException {
        TeacherMenuController.stage = new Stage();
        Parent root = FXMLLoader.load(TeacherMenuController.class.getResource("teacherMenu.fxml"));
        TeacherMenuController.stage.setScene(new Scene(root));
        TeacherMenuController.stage.show();
        stage.close();
    }
}
