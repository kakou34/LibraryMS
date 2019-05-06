package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sample.Util.DBUtil;

import java.sql.*;

import static sample.Controller.LoginController.showAlert;

public class SuggestController {
    @FXML
    public Pane suggestPane;
    @FXML
    public VBox suggestVBox;
    @FXML
    public TextField suggestTitle;
    @FXML
    public TextField authorFnameSuggest;
    @FXML
    public TextField authorLnameSuggest;
    @FXML
    public TextField publisherSuggest;
    @FXML
    public TextField yearSuggest;
    @FXML
    public Button suggestBtn;
    int year;
    private int id;
    private Connection conn;

    public void setUser(int id) {
        this.id = id;
    }

    public void btnSuggestClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {

            String title = suggestTitle.getText();
            String firstname = authorFnameSuggest.getText();
            String lastname = authorLnameSuggest.getText();
            String publisher = publisherSuggest.getText();
            String yearTxt = yearSuggest.getText();

            try {
                year = Integer.parseInt(yearTxt);
            } catch (NumberFormatException e) {
                showAlert("Please Enter a valid year");
            }

            if (!suggestTitle.getText().isEmpty() &&
                    !authorLnameSuggest.getText().isEmpty() &&
                    !authorFnameSuggest.getText().isEmpty() &&
                    !publisherSuggest.getText().isEmpty() &&
                    !yearSuggest.getText().isEmpty()) {
                conn = DBUtil.dbConnect();
                String query = "INSERT INTO book (`BookID`, `Title`, `AuthorFname`, `AuthorLname`, `Publisher`, `pubYear`) VALUES (NULL, '" + title + "', '" + firstname + "', '" + lastname + "', '" + publisher + "', '" + year + "');";
                Statement stmt = conn.createStatement();
                int result = stmt.executeUpdate(query);
                if (result == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Book Sucessfully Added");
                    alert.showAndWait();
                    query = "select BookID from book WHERE Title = '" + title + "' AND AuthorFname = '" + firstname + "' AND AuthorLname = '" + lastname + "' AND pubYear =  " + year + ";";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        query = "INSERT INTO suggest VALUES ('" + id + "', '" + rs.getInt(1) + "')";
                        Statement stmt2 = conn.createStatement();
                        int rs2 = stmt2.executeUpdate(query);
                        if (rs2 == 1) {
                            showAlert("Suggestion Added Successfully");
                        }
                    }
                    rs.close();
                    stmt.close();
                    conn.close();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please make sure you filled all required information");
                alert.showAndWait();
            }
        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ID and Username must be unique");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a valid ID");
            alert.showAndWait();
        } catch (SQLSyntaxErrorException e) {
            showAlert("Please Don't Use Special Characters!");
        }
    }


}
