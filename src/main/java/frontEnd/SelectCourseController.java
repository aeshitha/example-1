package frontEnd;

import backEnd.CourseaManager;
import backEnd.MessageManager;
import backEnd.UserManager;
import backEnd.tableManager.SCCouseListTable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entites.Course;
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
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class SelectCourseController implements Initializable {
    String windowaname = "Select Courses";
    public static Stage stage;
    public JFXButton btnSelect;
    public JFXButton btnCancel;
    public JFXButton btnRefresh;
    public TableView<SCCouseListTable> tableCourseList;
    public TableView<SCCouseListTable> tableSelectedCourseList;
    public JFXButton btnSave;
    public JFXButton btnDrop;
    public Label lblMain;

    public void btnSelectOnKeyRekease(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            int focusedIndex = tableCourseList.getSelectionModel().getFocusedIndex();
            select(focusedIndex);
        }
    }

    public void btnSelectOnMouseClicked(MouseEvent mouseEvent) {
        int focusedIndex = tableCourseList.getSelectionModel().getFocusedIndex();
        select(focusedIndex);
    }

    private void select(int focusedIndex) {
        tableSelectedCourseList.getItems().add(tableCourseList.getItems().get(focusedIndex));
        tableCourseList.getItems().remove(focusedIndex);
    }


    public void btnCancelOnKeyReleased(KeyEvent keyEvent) throws IOException {TeacherMenuController.stage = new Stage();
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            StudentMenuController.stage = new Stage();
            Parent root = FXMLLoader.load(StudentMenuController.class.getResource("studentMenu.fxml"));
            StudentMenuController.stage.setScene(new Scene(root));
            StudentMenuController.stage.show();
            stage.close();
        }
    }

    public void btnCancelOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        StudentMenuController.stage = new Stage();
        Parent root = FXMLLoader.load(StudentMenuController.class.getResource("studentMenu.fxml"));
        StudentMenuController.stage.setScene(new Scene(root));
        StudentMenuController.stage.show();
        stage.close();
    }

    public void btnRefreshOnKeyRelease(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            tableCourseList.getItems().clear();
            tableSelectedCourseList.getItems().clear();
            String major = DataHolder.user.getMajor();

            try {
                System.out.println(major +"/" + "1");
                List<Course> courses = CourseaManager.getCourses(major, "1");
                System.out.println("query : "+courses.size());
                for (Course course : courses) {
                    SCCouseListTable scCouseListTable = new SCCouseListTable(course.getId(),course.getName(),course.getTeacher(),course.getCredit());

                    if (DataHolder.user.getCourseList().contains(course.getId())){
                        tableSelectedCourseList.getItems().add(scCouseListTable);
                    }
                    else {
                        tableCourseList.getItems().add(scCouseListTable);
                    }
                }
                tableCourseList.refresh();

            } catch (IOException | ExecutionException | InterruptedException | ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public void btnRefreshOnMouseClicked(MouseEvent mouseEvent) {
        tableSelectedCourseList.getItems().clear();
        tableCourseList.getItems().clear();
        String major = DataHolder.user.getMajor();
        Date today = new Date();
        int semester;
        int sYear = Integer.parseInt(DataHolder.user.getEnrolledYear());
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.setTime(today);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        if (year == sYear){
            semester = 1;
        }
        if (month>=7){
            semester = ((year-sYear)*2)+1;
        }else{
            semester = (year-sYear)*2;
        }
        System.out.println("year :" + year + "/" + "sYear" + sYear);
        System.out.println("month : " + month);
        System.out.println("semester : " + semester);


        try {
            System.out.println(major +"/" + semester);
            List<Course> courses = CourseaManager.getCourses(major, Integer.toString(semester));
            System.out.println("query : "+courses.size());
            for (Course course : courses) {
                SCCouseListTable scCouseListTable = new SCCouseListTable(course.getId(),course.getName(),course.getTeacher(),course.getCredit());

                if (DataHolder.user.getCourseList().contains(course.getId())){
                    tableSelectedCourseList.getItems().add(scCouseListTable);
                }
                else {
                    tableCourseList.getItems().add(scCouseListTable);
                }
            }
            tableCourseList.refresh();

        } catch (IOException | ExecutionException | InterruptedException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void txtCCOnKeyRelease(KeyEvent keyEvent) {
    }

    public void btnSaveOnKeyReleased(KeyEvent keyEvent) throws InterruptedException, ExecutionException, IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            MessageManager.giveSuccessMessage(lblMain,"Selection Saved successfully",windowaname);
            save();
            btnCancel.requestFocus();
        }
    }

    public void btnSaveOnMouseClicked(MouseEvent mouseEvent) throws InterruptedException, ExecutionException, IOException {
        MessageManager.giveSuccessMessage(lblMain,"Selection Saved successfully",windowaname);
        save();
        btnCancel.requestFocus();
    }

    private void save() throws InterruptedException, ExecutionException, IOException {
        List<String> l = new ArrayList<>();
        for (SCCouseListTable s : tableSelectedCourseList.getItems()){
            l.add(s.getCourseID());
        }
        DataHolder.user.setCourseList(l);
        UserManager.updateUser(DataHolder.user);
    }

    public void tableCourseOnKeyRelease(KeyEvent keyEvent) {
    }

    public void tableCourseListOnMouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblMain.setText(windowaname);
        setTable();
        String major = DataHolder.user.getMajor();
        Date today = new Date();
        int semester;
        int sYear = Integer.parseInt(DataHolder.user.getEnrolledYear());
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.setTime(today);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        if (year == sYear){
            semester = 1;
        }
        if (month>=7){
            semester = ((year-sYear)*2)+1;
        }else{
            semester = (year-sYear)*2;
        }
        System.out.println("year :" + year + "/" + "sYear" + sYear);
        System.out.println("month : " + month);
        System.out.println("semester : " + semester);

        try {
            System.out.println(major +"/" + semester);
            List<Course> courses = CourseaManager.getCourses(major, Integer.toString(semester));
            System.out.println("query : "+courses.size());
            for (Course course : courses) {
                SCCouseListTable scCouseListTable = new SCCouseListTable(course.getId(),course.getName(),course.getTeacher(),course.getCredit());

                if (DataHolder.user.getCourseList().contains(course.getId())){
                    tableSelectedCourseList.getItems().add(scCouseListTable);
                }
                else {
                    tableCourseList.getItems().add(scCouseListTable);
                }
            }
            tableCourseList.refresh();

        } catch (IOException | ExecutionException | InterruptedException | ParseException e) {
            e.printStackTrace();
        }

    }
    private void setTable() {
        tableCourseList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("courseID"));
        tableCourseList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tableCourseList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("teacher"));
        tableCourseList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("credit"));

        tableSelectedCourseList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("courseID"));
        tableSelectedCourseList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("credit"));
        tableSelectedCourseList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tableSelectedCourseList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("teacher"));
    }

    public void btnDropeOnKeyRekease(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            int focusedIndex = tableSelectedCourseList.getSelectionModel().getFocusedIndex();
            drop(focusedIndex);
        }
    }

    public void btnDropOnMouseClicked(MouseEvent mouseEvent) {
        int focusedIndex = tableSelectedCourseList.getSelectionModel().getFocusedIndex();
        drop(focusedIndex);
    }
    private void drop(int focusedIndex) {
        tableCourseList.getItems().add(tableSelectedCourseList.getItems().get(focusedIndex));
        tableSelectedCourseList.getItems().remove(focusedIndex);
    }
}
