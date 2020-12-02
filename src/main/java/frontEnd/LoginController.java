package frontEnd;

import backEnd.MajorManager;
import backEnd.MessageManager;
import backEnd.UserManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entites.Major;
import entites.User;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class LoginController implements Initializable {
    String windowName = "Login";

    public static Stage stage;
    public JFXTextField txtID;
    public PasswordField txtPassword;
    public JFXButton btnLogin;
    public JFXButton btnExite;
    public Label lblMain;

    public void txtIDOnKeyRelease(KeyEvent keyEvent) throws InterruptedException, ExecutionException, IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            User user;
            try{
                user = UserManager.getUser(txtID.getText());
                MessageManager.giveSuccessMessage(lblMain,"Welcome! " + user.getName(),windowName);
                txtPassword.requestFocus();
            } catch (NullPointerException e){
                MessageManager.giveAWarning(lblMain,"Incorrect ID", windowName);
                txtID.requestFocus();
                txtID.clear();
            }

        }
    }

    public void txtPasswordOnKeyRelease(KeyEvent keyEvent) throws IOException, ExecutionException, InterruptedException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            User user = UserManager.getUser(txtID.getText());

            if (user.getpassword().equals(txtPassword.getText())){
                MessageManager.giveSuccessMessage(lblMain,"Login Successful",windowName);
                DataHolder.user = user;
                if (user.getType()) {
                    StudentMenuController.stage = new Stage();
                    Parent root = FXMLLoader.load(StudentMenuController.class.getResource("studentMenu.fxml"));
                    StudentMenuController.stage.setScene(new Scene(root));
                    StudentMenuController.stage.show();
                    stage.close();


                }else {
                    TeacherMenuController.stage = new Stage();
                    Parent root = FXMLLoader.load(TeacherMenuController.class.getResource("teacherMenu.fxml"));
                    TeacherMenuController.stage.setScene(new Scene(root));
                    TeacherMenuController.stage.show();
                    stage.close();
                }
            }else {
                MessageManager.giveAWarning(lblMain,"Incorrect Password",windowName);
                txtPassword.clear();
                txtPassword.requestFocus();
            }
        }
    }

    public void btnLoginOnKeyRelease(KeyEvent keyEvent) throws InterruptedException, ExecutionException, IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            User user = UserManager.getUser(txtID.getText());

            if (user.getpassword().equals(txtPassword.getText())){
                if (user.getType()) {
                    
                }else {
                    TeacherMenuController.stage = new Stage();
                    Parent root = FXMLLoader.load(TeacherMenuController.class.getResource("teacherMenu.fxml"));
                    TeacherMenuController.stage.setScene(new Scene(root));
                    TeacherMenuController.stage.show();
                    stage.close();
                }
            }else {
                MessageManager.giveAWarning(lblMain,"Incorrect Password",windowName);
                txtPassword.clear();
                txtPassword.requestFocus();
            }
        }
    }

    public void btnLoginOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void btnExiteOnKeyRelease(KeyEvent keyEvent) {
    }

    public void btnExiteOnMouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblMain.setText(windowName);
    }
}
