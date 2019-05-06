package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Util.DBUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {

    @FXML
    public ChoiceBox userRole;
    @FXML
    public TextField userID;
    @FXML
    public TextField userPassword;
    @FXML
    public Button logInBtn;
    PreparedStatement pst = null;
    ResultSet resultSet = null;
    private Stage prevStage;
    private int id;

    public static void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.show();
    }

    public void setPrevStage(Stage stage) {
        this.prevStage = stage;
    }

    public void login(ActionEvent actionEvent) {
        String userRoleChoice = userRole.getValue().toString();
        String idTxt = userID.getText();

        try {
            id = Integer.parseInt(idTxt);
        } catch (NumberFormatException e) {
            showAlert("Please Enter A Valid ID");
        }
        int password = userPassword.getText().hashCode();

        try {
            Connection conn = DBUtil.dbConnect();

            String query = null;
            switch (userRoleChoice) {
                case "Admin":
                    query = "SELECT * FROM admin WHERE adminID=? AND password=?";
                    break;
                case "Professor":
                    query = "SELECT * FROM libuser WHERE LibUserID=? AND password=?";
                    break;
                case "Student":
                    query = "SELECT * FROM libuser WHERE LibUserID=? AND password=?";
                    break;
            }
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            pst.setInt(2, password);
            resultSet = pst.executeQuery();

            if (resultSet.next()) {
                System.out.println("Login Successful");

                switch (userRoleChoice) {
                    case "Admin":
                        showAdminView();
                        break;
                    case "Professor":
                        showUserView(id);
                        break;
                    case "Student":
                        showUserView(id);
                        break;
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Form Error!");
                alert.setHeaderText(null);
                alert.setContentText("Username or password wrong");
                alert.show();
            }
            pst.close();
            resultSet.close();
            DBUtil.dbDisconnect(conn);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void showAdminView() {
        try {
            Pane myPane = FXMLLoader.load(getClass().getResource("../View/admin.fxml"));
            Scene scene = new Scene(myPane);
            Stage stage = new Stage();
            stage.setTitle("Admin Page");
            stage.setScene(scene);
            prevStage.close();
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showUserView(int id) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/user.fxml"));
            Pane myPane = (Pane) loader.load();
            UserController controller = loader.<UserController>getController();
            controller.setUser(id);
            Scene scene = new Scene(myPane);
            Stage stage = new Stage();
            stage.setTitle("User Page");
            stage.setScene(scene);
            prevStage.close();
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
