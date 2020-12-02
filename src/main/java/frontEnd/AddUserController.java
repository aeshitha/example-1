package frontEnd;

import backEnd.DBHandler;
import backEnd.MajorManager;
import backEnd.MessageManager;
import backEnd.UserManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entites.Major;
import entites.User;
import io.netty.util.concurrent.NonStickyEventExecutorGroup;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class AddUserController implements Initializable {
    String windowname = "Add User";
    public static Stage stage;
    public JFXTextField txtID;
    public JFXTextField txtName;
    public RadioButton rbTypeS;
    public RadioButton rbTypeT;
    public JFXComboBox<String> cbMajor;
    public JFXButton btnAdd;
    public JFXButton btnRefresh;
    public JFXButton btnCancel;
    public PasswordField txtPasward;
    public JFXComboBox<String> cbEnrolledYear;
    public JFXTextField txtPN;
    public Label lblMain;


    public void txtIDOnKeyRelease(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            txtName.requestFocus();
        }
    }

    public void txtNameOnKeyRelease(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            rbTypeS.requestFocus();
        }
    }

    public void cbMajorOnKeyRelease(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            cbEnrolledYear.requestFocus();
        }
    }

    public void btnAddOnKeyRelease(KeyEvent keyEvent) throws InterruptedException, ExecutionException, IOException, ParseException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            if (rbTypeS.isSelected()) {
                UserManager.addUser(new User(txtID.getText(), txtName.getText(), cbMajor.getSelectionModel().getSelectedItem().split("-")[0], rbTypeS.isSelected(), txtPasward.getText(), cbEnrolledYear.getSelectionModel().getSelectedItem(), txtPN.getText(), new ArrayList<>()));
                MessageManager.giveSuccessMessage(lblMain, "Student Added successfully", windowname);
                refreshUI();
            }else if (rbTypeT.isSelected()){
                UserManager.addUser(new User(txtID.getText(), txtName.getText(), null, rbTypeS.isSelected(), txtPasward.getText(), null, null, new ArrayList<>()));
                MessageManager.giveSuccessMessage(lblMain, "Teacher Added successfully", windowname);
                refreshUI();
            }else {
                MessageManager.giveAWarning(lblMain,"Select User Type",windowname);
            }

        }

    }

    public void btnAddOnMouseClicked(MouseEvent mouseEvent) throws InterruptedException, ExecutionException, IOException, ParseException {

        if (rbTypeS.isSelected()) {
            UserManager.addUser(new User(txtID.getText(), txtName.getText(), cbMajor.getSelectionModel().getSelectedItem().split("-")[0], rbTypeS.isSelected(), txtPasward.getText(), cbEnrolledYear.getSelectionModel().getSelectedItem(), txtPN.getText(), new ArrayList<>()));
            MessageManager.giveSuccessMessage(lblMain, "Student Added successfully", windowname);
            refreshUI();
        }else if (rbTypeT.isSelected()){
            UserManager.addUser(new User(txtID.getText(), txtName.getText(), null, rbTypeS.isSelected(), txtPasward.getText(), null, null, new ArrayList<>()));
            MessageManager.giveSuccessMessage(lblMain, "Teacher Added successfully", windowname);
            refreshUI();
        }else {
            MessageManager.giveAWarning(lblMain,"Select User Type",windowname);
        }
    }




    public void btnRefreshOnKeyReleas(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            refreshUI();
        }
    }

    public void btnRefreshOnMouseClicked(MouseEvent mouseEvent) {
        refreshUI();
    }

    public void btnCancelOnKeyRelease(KeyEvent keyEvent) throws IOException {
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

    /*public void setCbEnrolledYearOnKeyRealease(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            txtPasward.requestFocus();
            txtPasward.setText("STP" + txtID.getText());
        }
    }*/

    public void txtPaswardOnKeyRelease(KeyEvent keyEvent) {

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            btnAdd.requestFocus();
        }
    }


    /*public void txtCreditOnKeyRelease(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            btnAdd.requestFocus();
        }
    }*/

    public void rbTypeSOnKeyRelease(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            rbSAction();
        } else if (keyEvent.getCode().equals(KeyCode.RIGHT)) {
            rbTypeT.requestFocus();
        }

    }

    private void rbSAction() {
        rbTypeS.setSelected(true);
        rbTypeT.setSelected(false);
        cbMajor.setDisable(false);
        cbEnrolledYear.setDisable(false);
        txtPN.setDisable(false);
        cbMajor.requestFocus();
    }

    private void rbTAction() {
        rbTypeS.setSelected(false);
        rbTypeT.setSelected(true);
        cbMajor.setDisable(true);
        cbEnrolledYear.setDisable(true);
        txtPN.setDisable(true);
        txtPasward.requestFocus();
    }


    public void rbTypeTOnKeyRelease(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            rbTAction();
        } else if (keyEvent.getCode().equals(KeyCode.LEFT)) {
            rbTypeS.requestFocus();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblMain.setText(windowname);
        rbTypeS.setOnMouseClicked(event -> {
            rbSAction();
        });
        rbTypeT.setOnMouseClicked(event -> {
            rbTAction();
        });
        cbEnrolledYear.getItems().addAll("2017", "2018", "2019", "2020", "2021", "2022");
        try {
            List<Major> majors = MajorManager.getMajors();
            for (Major major : majors) {

                cbMajor.getItems().add(major.toString());
            }
        } catch (IOException | ExecutionException | InterruptedException | ParseException e) {
            e.printStackTrace();
        }


        cbMajor.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                cbMajor.show();
                if (cbMajor.getSelectionModel().isEmpty()) {
                    cbMajor.getSelectionModel().selectFirst();
                }
            } else {
                cbMajor.hide();
            }
        });
        cbEnrolledYear.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                cbEnrolledYear.show();
                if (cbEnrolledYear.getSelectionModel().isEmpty()) {
                    cbEnrolledYear.getSelectionModel().selectFirst();
                }
            } else {
                cbEnrolledYear.hide();
            }
        });


    }

    public void refreshUI() {
        txtID.clear();
        txtPN.clear();
        txtName.clear();
        txtPasward.clear();
        cbEnrolledYear.getSelectionModel().clearSelection();
        cbMajor.getSelectionModel().clearSelection();
        rbTypeS.setSelected(false);
        rbTypeT.setSelected(false);
        txtID.requestFocus();
    }

    public void cbEnrolledYearOnKeyRelease(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            txtPN.requestFocus();
        }
    }

    public void txtPNOnKeyRelease(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            txtPN.requestFocus();
            String text = txtPN.getText();
            txtPasward.setText("ST" + text.substring(text.length()-6,text.length()));
            txtPasward.requestFocus();
        }
    }
}
