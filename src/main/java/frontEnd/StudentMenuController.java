package frontEnd;

import com.jfoenix.controls.JFXButton;
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

public class StudentMenuController implements Initializable {
    String windowname = "Student Menu";
    public static Stage stage;
    public JFXButton btnSelectCourse;
    public JFXButton btnExite;
    public JFXButton btnSelectedCourse;
    public JFXButton btnChangePasward;
    public Label lblMain;

    public void btnSelectCourseOnKeyRelease(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {

            SelectCourseController.stage = new Stage();
            Parent root = FXMLLoader.load(SelectCourseController.class.getResource("SelectCourse.fxml"));
            SelectCourseController.stage.setScene(new Scene(root));
            SelectCourseController.stage.show();
            stage.close();
        }
    }

    public void btnSelectCourseOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        SelectCourseController.stage = new Stage();
        Parent root = FXMLLoader.load(SelectCourseController.class.getResource("selectCourse.fxml"));
        SelectCourseController.stage.setScene(new Scene(root));
        SelectCourseController.stage.show();
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

    public void btnSelectedCourseOnKeyRelease(KeyEvent keyEvent) {
    }

    public void btnSelectedCourseOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void btnChangePaswardOnKeyRelease(KeyEvent keyEvent) {
    }

    public void btnChangePaswardOnMouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblMain.setText(windowname);
    }
}
