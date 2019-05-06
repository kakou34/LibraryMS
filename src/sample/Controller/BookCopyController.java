package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import sample.Util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static sample.Controller.LoginController.showAlert;

public class BookCopyController {
    @FXML
    public Pane bookCPAne;
    @FXML
    public Label labTitle;
    @FXML
    public Label labFname;
    @FXML
    public Label labLname;
    @FXML
    public Label labYear;
    @FXML
    public Label labLocation;
    @FXML
    public Label labStatus;
    @FXML
    public Button borrowBookCbtn;
    @FXML
    public Button extendBtn;
    @FXML
    public Label labISBN;
    private Connection conn;

    private int userId;
    private int ISBN;
    private boolean stat;


    public void setUser(int id) {
        this.userId = id;
    }

    public void setISBN(int isbn) {
        this.ISBN = isbn;
    }

    public void setStat(boolean stat) {
        this.stat = stat;
    }


    public void btnBorrowClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (checkStat()) {
            String role = userType(userId);
            if (role.equals("Student")) {
                if (checkLimit(5)) {
                    try {
                        conn = DBUtil.dbConnect();
                        String query = "INSERT INTO borrow  VALUES ('" + userId + "', '" + ISBN + "', CURRENT_TIMESTAMP , DATE_ADD(CURRENT_TIMESTAMP,INTERVAL 10 DAY))";
                        String query2 = "UPDATE `bookcopy` SET `Stat` = '0', `NoBorrows` = NoBorrows+1 WHERE `bookcopy`.`ISBN` = " + ISBN;

                        Statement stmt = conn.createStatement();
                        int rs = stmt.executeUpdate(query);
                        int rs2 = stmt.executeUpdate(query2);

                        if (rs == 1 && rs2 == 1) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Sucessfully Borrowed");
                            labStatus.setText("Status: Unavailable");
                            alert.showAndWait();
                            stmt.close();
                            conn.close();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Something went wrong!");
                            alert.showAndWait();
                        }
                        checkStat();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else showAlert("You have reached the limit of books allowed to borrow!");
            } else if (role.equals("Professor")) {
                if (checkLimit(10)) {
                    try {
                        conn = DBUtil.dbConnect();
                        String query = "INSERT INTO borrow  VALUES ('" + userId + "', '" + ISBN + "', CURRENT_TIMESTAMP , DATE_ADD(CURRENT_TIMESTAMP,INTERVAL 20 DAY))";
                        String query2 = "UPDATE `bookcopy` SET `Stat` = '0', `NoBorrows` = NoBorrows+1 WHERE `bookcopy`.`ISBN` = " + ISBN;
                        Statement stmt = conn.createStatement();
                        int rs = stmt.executeUpdate(query);
                        int rs2 = stmt.executeUpdate(query2);

                        if (rs == 1 && rs2 == 1) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Sucessfully Borrowed");
                            labStatus.setText("Status: Unavailable");
                            alert.showAndWait();
                            stmt.close();
                            conn.close();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Something went wrong!");
                            alert.showAndWait();
                        }
                        checkStat();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else showAlert("You have reached the limit of books allowed to borrow!");
            } else showAlert("User Not Found!");
        } else showAlert("This book is unavailable");
    }

    public void btnExtendClick(ActionEvent actionEvent) {
        String role = userType(userId);
        if (role.equals("Student")) {
            try {
                conn = DBUtil.dbConnect();
                String query = "UPDATE borrow SET ReturnDate = DATE_ADD( ReturnDate ,INTERVAL 5 DAY) WHERE LibUserID = ' " + userId + " '" + "AND ISBN = '" + ISBN + "'";
                Statement stmt = conn.createStatement();
                int rs = stmt.executeUpdate(query);

                if (rs == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Sucessfully Extended");
                    alert.showAndWait();
                    stmt.close();
                    conn.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("You did not borrow this book!");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (role.equals("Professor")) {
            try {
                conn = DBUtil.dbConnect();
                String query = "UPDATE borrow SET ReturnDate = DATE_ADD( ReturnDate ,INTERVAL 10 DAY) WHERE LibUserID = ' " + userId + " '" + " AND ISBN = '" + ISBN + "'";
                Statement stmt = conn.createStatement();
                int rs = stmt.executeUpdate(query);

                if (rs == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Sucessfully Extended");
                    alert.showAndWait();
                    stmt.close();
                    conn.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("You did not borrow this book!");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else showAlert("User Not Found!");
    }


    private String userType(int id) {
        try {
            conn = DBUtil.dbConnect();
            String query = "SELECT * FROM student WHERE LibUserID= " + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                query = "SELECT * FROM professor WHERE LibUserID= " + id;
                rs = stmt.executeQuery(query);
                if (!rs.next()) {
                    return "Not Found";
                } else return "Professor";
            } else return "Student";
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found exception");
        }
        return "Not Found";
    }

    private boolean checkStat() throws SQLException, ClassNotFoundException {
        conn = DBUtil.dbConnect();
        String query = "select Stat from bookcopy WHERE ISBN = '" + ISBN + "';";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        if (!result.next()) {
            showAlert("Copy Not Found!");
        } else {
            Statement s = conn.createStatement();
            result = s.executeQuery(query);
            while (result.next()) {
                stat = result.getBoolean(1);
            }
        }
        stmt.close();
        conn.close();
        if (stat) {
            labStatus.setText("Status: Available");
        } else labStatus.setText("Status: Unavailable");
        return stat;
    }

    private boolean checkLimit(int n) throws SQLException, ClassNotFoundException {
        int booksBorrowed = 0;
        conn = DBUtil.dbConnect();
        String query = "SELECT COUNT(LibUserID) as n \n" +
                "FROM borrow\n" +
                "WHERE LibUserID = " + userId + ";";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        while (result.next()) {
            booksBorrowed = result.getInt(1);
        }
        return (booksBorrowed < n);
    }

    public void btnReturnClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            conn = DBUtil.dbConnect();
            String query = "DELETE FROM `borrow` WHERE `borrow`.`LibUserID` = '" + userId + "' AND `borrow`.`ISBN` =  '" + ISBN + "'";
            String query2 = "UPDATE `bookcopy` SET `Stat` = '1' WHERE `bookcopy`.`ISBN` = " + ISBN;
            Statement stmt = conn.createStatement();
            int rs = stmt.executeUpdate(query);
            int rs2 = stmt.executeUpdate(query2);

            if (rs == 1 && rs2 == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Successfully Returned");
                labStatus.setText("Status: Available");
                alert.showAndWait();
                stmt.close();
                conn.close();
            } else {
                showAlert("Please make sure that you have this copy.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
