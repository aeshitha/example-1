package frontEnd;

import backEnd.CourseaManager;
import backEnd.MajorManager;
import backEnd.MessageManager;
import backEnd.tableManager.ACMajorTable;
import com.jfoenix.controls.*;
import entites.Course;
import entites.Major;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class AddCourseController implements Initializable {
    String windowname = "Add Course";
    public static Stage stage;
    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXTextField txtTeacher;
    public JFXTextField txtMID;
    public JFXTextField txtMName;
    public JFXButton btnAddM;
    public JFXTextField txtCredit;
    public JFXComboBox<String> cbSemester;
    public JFXButton btnAdd;
    public JFXButton btnRefresh;
    public JFXButton btnCancel;
    public List<HashMap<String, String>> majorMap;
    public TableView<ACMajorTable> tableMajor;
    public Label lblMain;


    public void txtIDnKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            txtName.requestFocus();
        }
    }

    public void txtNameOnKeyRelease(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            txtTeacher.requestFocus();
        }
    }

    public void txtTeacherOnKeyRelease(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            txtCredit.requestFocus();
        }
    }

    public void txtMIDOnKeyRelease(KeyEvent keyEvent) throws InterruptedException, ExecutionException, IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            try {
                Major major = MajorManager.getMajor(txtMID.getText());
                txtMName.setText(major.getName());
                cbSemester.requestFocus();
            } catch (NullPointerException e) {
                e.printStackTrace();
                txtMName.requestFocus();
            }
        }

    }


    public void txtMNameOnKeyRelease(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            cbSemester.requestFocus();
        }
    }

    public void btnAddMOnKeyRelease(KeyEvent keyEvent) throws InterruptedException, ExecutionException, IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            try {
                Major major = MajorManager.getMajor(txtMID.getText());
            } catch (NullPointerException e) {
                MajorManager.addMajor(new Major(txtMID.getText(), txtMName.getText()));
                MessageManager.giveSuccessMessage(lblMain,"New Major Added",windowname);
            }
            HashMap<String ,String> map = new HashMap<>();
            map.put(txtMID.getText(),cbSemester.getSelectionModel().getSelectedItem());
            majorMap.add(map);
            ACMajorTable acMajorTable = new ACMajorTable(txtMID.getText(), txtMName.getText(), cbSemester.getSelectionModel().getSelectedItem());
            tableMajor.getItems().add(acMajorTable);
            txtMID.requestFocus();
        }

    }

    public void btnAddMOnMouseClicked(MouseEvent mouseEvent) throws InterruptedException, ExecutionException, IOException {
        try {
            Major major = MajorManager.getMajor(txtMID.getText());
        } catch (NullPointerException e) {
            MajorManager.addMajor(new Major(txtMID.getText(), txtMName.getText()));
            MessageManager.giveSuccessMessage(lblMain,"New Major Added",windowname);
        }
        HashMap<String ,String> map = new HashMap<>();
        map.put(txtMID.getText(),cbSemester.getSelectionModel().getSelectedItem());
        majorMap.add(map);
        ACMajorTable acMajorTable = new ACMajorTable(txtMID.getText(), txtMName.getText(), cbSemester.getSelectionModel().getSelectedItem());
        tableMajor.getItems().add(acMajorTable);
        txtMID.requestFocus();
    }

    public void txtCreditOnKeyRelease(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            txtMID.requestFocus();
        }
    }

    public void cbSemesterOnKeyRelease(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            btnAddM.requestFocus();
        }
    }

    public void btnAddOnKeyRelease(KeyEvent keyEvent) throws InterruptedException, ExecutionException, IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            CourseaManager.addcCourse(new Course(txtID.getText(), txtName.getText(), txtTeacher.getText(), txtCredit.getText(), majorMap));
            MessageManager.giveSuccessMessage(lblMain,"Courses Added successfully",windowname);
            refreshUi();
            txtID.requestFocus();
        }
    }

    public void btnAddOnMouseClicked(MouseEvent mouseEvent) throws InterruptedException, ExecutionException, IOException {
        CourseaManager.addcCourse(new Course(txtID.getText(), txtName.getText(), txtTeacher.getText(), txtCredit.getText(), majorMap));
        MessageManager.giveSuccessMessage(lblMain,"Courses Added successfully",windowname);
        refreshUi();
        txtID.requestFocus();
    }

    public void btnRefreshOnKeyRelease(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            refreshUi();
            txtID.requestFocus();
        }
    }

    public void btnRefreshOnMouseClicked(MouseEvent mouseEvent) {
        refreshUi();
        txtID.requestFocus();
    }

    public void btnCancelOnKeyReleased(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            TeacherMenuController.stage = new Stage();
            Parent root = FXMLLoader.load(TeacherMenuController.class.getResource("teacherMenu.fxml"));
            TeacherMenuController.stage.setScene(new Scene(root));
            TeacherMenuController.stage.show();
            stage.close();
        }
    }

    public void btnCancelOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        TeacherMenuController.stage = new Stage();
        Parent root = FXMLLoader.load(TeacherMenuController.class.getResource("teacherMenu.fxml"));
        TeacherMenuController.stage.setScene(new Scene(root));
        TeacherMenuController.stage.show();
        stage.close();
    }

    public void refreshUi() {
        txtID.clear();
        txtName.clear();
        txtMID.clear();
        txtMName.clear();
        txtCredit.clear();
        txtTeacher.clear();
        cbSemester.getSelectionModel().clearSelection();
        majorMap.clear();
        tableMajor.getItems().clear();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblMain.setText(windowname);
        txtMName.setText(null);
        majorMap = new ArrayList<>();
        cbSemester.getItems().addAll("1","2","3","4","5","6","7","8");
        setTable();

        cbSemester.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                cbSemester.show();
                if (cbSemester.getSelectionModel().isEmpty()) {
                    cbSemester.getSelectionModel().selectFirst();
                }
            } else {
                cbSemester.hide();
            }
        });

    }

    private void setTable() {
        tableMajor.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("majorID"));
        tableMajor.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("majorName"));
        tableMajor.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("semester"));
    }
}
