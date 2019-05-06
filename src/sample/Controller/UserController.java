package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Model.BookCopy;
import sample.Model.Borrow;
import sample.Model.NewBook;
import sample.Model.TopBook;
import sample.Util.DBUtil;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static sample.Controller.LoginController.showAlert;

public class UserController {
    @FXML
    public TextField fbNo;
    @FXML
    public TableView newBooksTable;
    @FXML
    public TableColumn titleNCol;
    @FXML
    public TableColumn isbnNCol;
    @FXML
    public TableColumn sDateNCol;
    @FXML
    public TableView topBooksTable;
    @FXML
    public TableColumn titleTCol;
    @FXML
    public TableColumn isbnTCol;
    @FXML
    public TableColumn borrowsCol;
    @FXML
    public TableColumn idCol;
    @FXML
    public TableView myBooksTable;
    @FXML
    public TableColumn isbn2Col;
    @FXML
    public TableColumn bDateCol;
    @FXML
    public TableColumn rDateCol;
    @FXML
    public Button showBooksBtn;
    @FXML
    public TextArea feedback;
    @FXML
    public Button sendFeedbackBtn;
    @FXML
    public TextField searchBTitle;
    @FXML
    public TextField searchFName;
    @FXML
    public TextField searchLName;
    @FXML
    public Button searchBookBtn;
    @FXML
    public Button topBooksBtn;
    @FXML
    public Button suggestBookBtn;
    @FXML
    public TableView copyTable;
    @FXML
    public TableColumn isbnCol;
    @FXML
    public TableColumn titleCol;
    @FXML
    public TableColumn fNameCol;
    @FXML
    public TableColumn yearCol;
    @FXML
    public TableColumn lNameCol;
    @FXML
    public TableColumn locCol;
    @FXML
    public TableColumn statusCol;

    private Connection conn;
    private int id;
    private ObservableList<BookCopy> data;
    private ObservableList<Borrow> data2;
    private ObservableList<NewBook> data3;
    private ObservableList<TopBook> data4;

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        //Set cell value factory to tableviews
        idCol.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("aFirstName"));
        lNameCol.setCellValueFactory(new PropertyValueFactory<>("aLastName"));
        locCol.setCellValueFactory(new PropertyValueFactory<>("locZipCode"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("pubYear"));
        isbn2Col.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        bDateCol.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        rDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        isbnNCol.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        titleNCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        sDateNCol.setCellValueFactory(new PropertyValueFactory<>("storeDate"));
        isbnTCol.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        titleTCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        borrowsCol.setCellValueFactory(new PropertyValueFactory<>("borrows"));

    }

    public void tblColClick(MouseEvent mouseEvent) {
        if (!copyTable.getSelectionModel().getSelectedIndices().isEmpty()) {
            int isbn = ((BookCopy) copyTable.getSelectionModel().getSelectedItem()).getISBN();
            String fname = ((BookCopy) copyTable.getSelectionModel().getSelectedItem()).getaFirstName();
            String lname = ((BookCopy) copyTable.getSelectionModel().getSelectedItem()).getaLastName();
            String title = ((BookCopy) copyTable.getSelectionModel().getSelectedItem()).getTitle();
            int year = ((BookCopy) copyTable.getSelectionModel().getSelectedItem()).getPubYear();
            int zipcode = ((BookCopy) copyTable.getSelectionModel().getSelectedItem()).getLocZipCode();
            boolean status = ((BookCopy) copyTable.getSelectionModel().getSelectedItem()).isStatus();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/bookCopy.fxml"));
                Pane myPane = (Pane) loader.load();
                BookCopyController controller = loader.<BookCopyController>getController();
                controller.setUser(id);
                controller.setISBN(isbn);
                controller.setStat(status);
                controller.labFname.setText(controller.labFname.getText() + fname);
                controller.labLname.setText(controller.labLname.getText() + lname);
                controller.labTitle.setText(controller.labTitle.getText() + title);
                controller.labYear.setText(controller.labYear.getText() + year);
                controller.labLocation.setText(controller.labLocation.getText() + zipcode);
                controller.labISBN.setText(controller.labISBN.getText() + isbn);
                if (status) {
                    controller.labStatus.setText(controller.labStatus.getText() + "Available");
                } else controller.labStatus.setText(controller.labStatus.getText() + "Unavailable");
                Scene scene = new Scene(myPane);
                Stage stage = new Stage();
                stage.setTitle("Book Copy Page");
                stage.setScene(scene);

                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void setUser(int id) {
        this.id = id;
    }

    public void btnShowMyBooksClick(ActionEvent actionEvent) {
        copyTable.setVisible(false);
        newBooksTable.setVisible(false);
        topBooksTable.setVisible(false);
        myBooksTable.setVisible(true);
        data2 = FXCollections.observableArrayList();
        try {
            conn = DBUtil.dbConnect();
            String query = "SELECT * FROM `borrow` WHERE `LibUserID` = " + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                showAlert("You Have No Borrow Records ");
            } else {
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    data2.add(new Borrow(rs.getInt(2), id, rs.getDate(3), rs.getDate(4)));
                }
                rs.close();
                stmt.close();
                conn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        myBooksTable.setItems(data2);
    }

    public void btnSendClick(ActionEvent actionEvent) {
        int feedBackNo = 0;
        try {
            feedBackNo = Integer.parseInt(fbNo.getText());
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid number!");
        }
        String feedbackStr = feedback.getText();
        try {
            conn = DBUtil.dbConnect();
            String query = "INSERT INTO feedback (`LibUserID`, `Number`, `Description`) VALUES ('" + id + "', '" + feedBackNo + "', '" + feedbackStr + "')";
            Statement stmt = conn.createStatement();
            int rs = stmt.executeUpdate(query);

            if (rs == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Sucessfully sent");
                alert.showAndWait();
                fbNo.setText("");
                feedback.setText("");
                stmt.close();
                conn.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please make sure you filled all required information");
                alert.showAndWait();
            }
        } catch (SQLSyntaxErrorException e) {
            showAlert("Please don't use Special Characters");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnSearchClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            copyTable.getItems().clear();
            data = FXCollections.observableArrayList();
            String title = searchBTitle.getText();
            String aFname = searchFName.getText();
            String aLname = searchLName.getText();
            conn = DBUtil.dbConnect();
            String query = "select book.BookID, ISBN, Title, AuthorFname, AuthorLname, pubYear, LocZIPCode, Stat from book join bookcopy on (book.BookID = bookcopy.BookID) WHERE Title = '" + title + "' AND AuthorFname = '" + aFname + "' AND AuthorLname = '" + aLname + "';";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                data.add(new BookCopy(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getBoolean(8)));
            }
            rs.close();
            stmt.close();
            conn.close();
            myBooksTable.setVisible(false);
            newBooksTable.setVisible(false);
            topBooksTable.setVisible(false);
            copyTable.setVisible(true);
            copyTable.setItems(data);
        } catch (SQLSyntaxErrorException e) {
            showAlert("Please don't use special characters in the search fields!");
        }
    }

    public void btnNewClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        newBooksTable.getItems().clear();
        data3 = FXCollections.observableArrayList();
        conn = DBUtil.dbConnect();
        String query = "SELECT Title, ISBN, StoreDate\n" +
                "FROM bookcopy \n" +
                "JOIN book on (book.BookID = bookcopy.BookID) \n" +
                "ORDER By bookcopy.StoreDate DESC \n" +
                "LIMIT 10";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            data3.add(new NewBook(rs.getString(1), rs.getInt(2), rs.getDate(3)));
        }
        rs.close();
        stmt.close();
        conn.close();
        myBooksTable.setVisible(false);
        copyTable.setVisible(false);
        topBooksTable.setVisible(false);
        newBooksTable.setVisible(true);
        newBooksTable.setItems(data3);
    }

    public void btnTopClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        topBooksTable.getItems().clear();
        data4 = FXCollections.observableArrayList();
        conn = DBUtil.dbConnect();
        String query = "SELECT Title, bookcopy.ISBN, bookcopy.NoBorrows\n" +
                "FROM book\n" +
                "JOIN bookcopy on (bookcopy.BookID=book.BookID)\n" +
                "ORDER BY bookcopy.NoBorrows DESC\n" +
                "LIMIT 20";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            data4.add(new TopBook(rs.getString(1), rs.getInt(2), rs.getInt(3)));
        }
        rs.close();
        stmt.close();
        conn.close();
        myBooksTable.setVisible(false);
        copyTable.setVisible(false);
        topBooksTable.setVisible(true);
        newBooksTable.setVisible(false);
        topBooksTable.setItems(data4);
    }

    public void btnSuggestClick(ActionEvent actionEvent) throws SQLException {
        try {
            String title = searchBTitle.getText();
            String aFname = searchFName.getText();
            String aLname = searchLName.getText();
            conn = DBUtil.dbConnect();
            String query = "select BookID from book WHERE Title = '" + title + "' AND AuthorFname = '" + aFname + "' AND AuthorLname = '" + aLname + "';";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/suggest.fxml"));
                    Pane myPane = (Pane) loader.load();
                    SuggestController controller = loader.<SuggestController>getController();
                    controller.setUser(id);
                    Scene scene = new Scene(myPane);
                    Stage stage = new Stage();
                    stage.setTitle("Suggest A Book");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }


            } else {
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String query2 = "INSERT INTO suggest VALUES ('" + id + "', '" + rs.getInt(1) + "')";
                    Statement stmt2 = conn.createStatement();
                    int rs2 = stmt2.executeUpdate(query2);
                    if (rs2 == 1) {
                        showAlert("Suggestion Added Successfully");
                    } else showAlert("No suggestion added");
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLIntegrityConstraintViolationException e) {
            showAlert("You cannot suggest the same book twice!");
        } catch (SQLSyntaxErrorException e) {
            showAlert("Please don't use special characters!");
        }
    }


}