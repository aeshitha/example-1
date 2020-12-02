package frontEnd;

import backEnd.CourseaManager;
import backEnd.MajorManager;
import backEnd.MessageManager;
import backEnd.UserManager;
import backEnd.tableManager.SCCouseListTable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import entites.Course;
import entites.Major;
import entites.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.color.CMMException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class ViewStudentController implements Initializable {
    String windowname = "Student Details";
    public static Stage stage;
    public JFXTextField txtID;
    public JFXTextField txtMajor;
    public JFXTextField txtEnrolledYear;
    public JFXButton btnCancel;
    public TableView<SCCouseListTable> tableCourseList;
    public User user;
    public JFXTextField txtPN;
    public JFXTextField txtName;
    public Label lblMain;


    public void btnCancelOnAction(ActionEvent event) throws IOException {
        TeacherMenuController.stage = new Stage();
        Parent root = FXMLLoader.load(TeacherMenuController.class.getResource("teacherMenu.fxml"));
        TeacherMenuController.stage.setScene(new Scene(root));
        TeacherMenuController.stage.show();
        stage.close();

    }

    public void tableCourseOnKeyRelease(KeyEvent keyEvent) {
    }

    public void tableCourseListOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void txtIDOnKeyRelease(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            new Thread(() -> {
                try {
                    user = UserManager.getUser(txtID.getText());
                    Major major = MajorManager.getMajor(user.getMajor());
                    Platform.runLater(() -> {
                        tableCourseList.getItems().clear();
                        txtName.setText(user.getName());
                        txtMajor.setText(major.getName());
                        txtEnrolledYear.setText(user.getEnrolledYear());
                        txtPN.setText(user.getPN());
                    });

                    for (String courseId : user.getCourseList()) {
                        Course course = CourseaManager.getCourse(courseId);
                        SCCouseListTable scCouseListTable = new SCCouseListTable(course.getId(), course.getName(), course.getTeacher(), course.getCredit());
                        Platform.runLater(() -> {
                            tableCourseList.getItems().add(scCouseListTable);
                        });

                    }

                    Platform.runLater(() -> {MessageManager.giveSuccessMessage(lblMain, "Student Details Loaded Successfully", windowname);});


                } catch (NullPointerException e) {
                    Platform.runLater(() -> {
                        MessageManager.giveAWarning(lblMain, "Wrong Student ID", windowname);
                        txtID.clear();
                        txtID.requestFocus();
                    });
                    e.printStackTrace();

                } catch (InterruptedException | ExecutionException | IOException e) {
                    e.printStackTrace();
                }
            }).start();



        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblMain.setText(windowname);
        setTable();
    }

    private void setTable() {
        tableCourseList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("courseID"));
        tableCourseList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tableCourseList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("teacher"));
        tableCourseList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("credit"));
    }
}
