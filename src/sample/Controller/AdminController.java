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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Model.*;
import sample.Util.DBUtil;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static sample.Controller.LoginController.showAlert;

public class AdminController {
    @FXML
    public TableColumn idCol;
    @FXML
    public TextField insertIsbn;
    @FXML
    public TextField insertLoc;
    @FXML
    public Button cAddBtn;
    @FXML
    public TextField insertTitle;
    @FXML
    public TextField insertAuthorF;
    @FXML
    public TextField insertAuthorL;
    @FXML
    public TextField insertPublisher;
    @FXML
    public TextField insertYear;
    @FXML
    public TableView studentTable;
    @FXML
    public TableColumn sidCol;
    @FXML
    public TableColumn sfnameCol;
    @FXML
    public TableColumn semailCol;
    @FXML
    public TableColumn slnameCol;
    @FXML
    public TableColumn sdepCol;
    @FXML
    public TableColumn sgradeCol;

    @FXML
    public TableColumn pidCol;
    @FXML
    public TableColumn pfnameCol;
    @FXML
    public TableColumn pemailCol;
    @FXML
    public TableColumn plnameCol;
    @FXML
    public TableColumn ptitleCol;


    @FXML
    public TableColumn sugTitleCol;
    @FXML
    public TableColumn sugIdCol;
    @FXML
    public TableColumn sugNoCol;
    @FXML
    public TableView sugTable;
    @FXML
    public TextField addZipCode;
    @FXML
    public TextField addState;
    @FXML
    public TextField addCity;
    @FXML
    public TextField addStreet;
    @FXML
    public TextField addBuildNo;
    @FXML
    public TableView locTable;
    @FXML
    public TableColumn zipCol;
    @FXML
    public TableColumn stateCol;
    @FXML
    public TableColumn cityCol;
    @FXML
    public TableColumn streetCol;
    @FXML
    public TableColumn buildNoCol;
    @FXML
    public TableView feedTable;
    @FXML
    public TableColumn fIdCol;
    @FXML
    public TableColumn feedCol;
    @FXML
    public Button uShowAllSBtn;
    @FXML
    public Button sSearchBtn;
    @FXML
    public TableView professorTable;
    @FXML
    public TextField addIdp;
    @FXML
    public TextField addPWp;
    @FXML
    public TextField addEmailp;
    @FXML
    public TextArea addAddressp;
    @FXML
    public TextField addtitlep;
    @FXML
    public Button pAddBtn;
    @FXML
    public TextField prchId;
    @FXML
    public Button pSearchBtn;
    @FXML
    public Button uShowAllPBtn;
    @FXML
    public Pane studentPane;
    @FXML
    public Pane professorPane;
    @FXML
    public TextField addFNs;
    @FXML
    public TextField addLNs;
    @FXML
    public TextField addFNp;
    @FXML
    public TextField addLNp;
    @FXML
    public TableColumn isbnCol;
    @FXML
    public TableColumn titleCol;
    @FXML
    public TableColumn fNameCol;
    @FXML
    public TableColumn lNameCol;
    @FXML
    public TableColumn yearCol;
    @FXML
    public TableColumn locCol;
    @FXML
    public TableColumn statusCol;
    @FXML
    public AnchorPane admin;
    @FXML
    public Pane bookPane;
    @FXML
    public TextField srchTitle;
    @FXML
    public TextField srchF;
    @FXML
    public TextField srchL;
    @FXML
    public Button searchBtn;
    @FXML
    public Button showAllBtn;
    @FXML
    public TextField addId;
    @FXML
    public TextField addPW;
    @FXML
    public TextField addEmail;
    @FXML
    public TextArea addAddress;
    @FXML
    public TextField addDep;
    @FXML
    public TextField addGrade;
    @FXML
    public Button sAddBtn;
    @FXML
    public TextField srchId;
    @FXML
    public Pane suggestionPane;
    @FXML
    public Pane locPane;
    @FXML
    public Button locBtn;
    @FXML
    public Pane feedPane;
    @FXML
    public TableView copyTable;
    @FXML
    public Label booksNo;
    @FXML
    public Button bAddBtn;
    private ObservableList<BookCopy> data;
    private ObservableList<TopBook> data3;
    private ObservableList<Location> data4;
    private ObservableList<FeedBack> data5;
    private ObservableList<Student> data6;
    private ObservableList<Professor> data7;
    private Connection conn;

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
        pidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        pfnameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        plnameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        pemailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        ptitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        sidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        sfnameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        slnameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        semailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        sgradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));
        sdepCol.setCellValueFactory(new PropertyValueFactory<>("department"));
        sugIdCol.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        sugNoCol.setCellValueFactory(new PropertyValueFactory<>("borrows"));
        sugTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        zipCol.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        buildNoCol.setCellValueFactory(new PropertyValueFactory<>("buildingNo"));
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        fIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        feedCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
    }

    //Menu functions;
    public void booksMenu(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        bookPane.setVisible(true);
        feedPane.setVisible(false);
        locPane.setVisible(false);
        suggestionPane.setVisible(false);
        studentPane.setVisible(false);
        professorPane.setVisible(false);


        conn = DBUtil.dbConnect();
        String query = "select COUNT(*) from bookcopy;";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            booksNo.setText("" + rs.getInt(1));
        }
        rs.close();
        stmt.close();
        conn.close();


    }

    public void studentsMenu(ActionEvent actionEvent) {
        studentPane.setVisible(true);
        professorPane.setVisible(false);
        bookPane.setVisible(false);
        feedPane.setVisible(false);
        locPane.setVisible(false);
        suggestionPane.setVisible(false);
    }

    public void professorsMenu(ActionEvent actionEvent) {
        studentPane.setVisible(false);
        professorPane.setVisible(true);
        bookPane.setVisible(false);
        feedPane.setVisible(false);
        locPane.setVisible(false);
        suggestionPane.setVisible(false);
    }

    public void sugMenu(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        studentPane.setVisible(false);
        professorPane.setVisible(false);
        bookPane.setVisible(false);
        feedPane.setVisible(false);
        locPane.setVisible(false);
        suggestionPane.setVisible(true);

        sugTable.getItems().clear();
        data3 = FXCollections.observableArrayList();
        conn = DBUtil.dbConnect();
        String query = "SELECT Title, book.BookID, \n" +
                "       COUNT(*) as numberOfSuggestions \n" +
                "FROM suggest \n" +
                "JOIN book on (book.BookID = suggest.BookID) \n" +
                "GROUP By suggest.BookID\n" +
                "ORDER BY numberOfSuggestions DESC\n" +
                "LIMIT 10";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            data3.add(new TopBook(rs.getString(1), rs.getInt(2), rs.getInt(3)));
        }
        rs.close();
        stmt.close();
        conn.close();
        sugTable.setItems(data3);
    }

    public void locMenu(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        studentPane.setVisible(false);
        professorPane.setVisible(false);
        bookPane.setVisible(false);
        feedPane.setVisible(false);
        locPane.setVisible(true);
        suggestionPane.setVisible(false);

        locTable.getItems().clear();
        data4 = FXCollections.observableArrayList();
        conn = DBUtil.dbConnect();
        String query = "SELECT * FROM location";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            data4.add(new Location(rs.getInt(1), rs.getInt(5), rs.getString(4), rs.getString(3), rs.getString(2)));
        }
        rs.close();
        stmt.close();
        conn.close();
        locTable.setItems(data4);
    }

    public void feedMenu(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        studentPane.setVisible(false);
        professorPane.setVisible(false);
        bookPane.setVisible(false);
        feedPane.setVisible(true);
        locPane.setVisible(false);
        suggestionPane.setVisible(false);
        sugTable.getItems().clear();
        data5 = FXCollections.observableArrayList();
        conn = DBUtil.dbConnect();
        String query = "SELECT LibUserID, Description FROM feedback;";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            data5.add(new FeedBack(rs.getInt(1), rs.getString(2)));
        }
        rs.close();
        stmt.close();
        conn.close();
        feedTable.setItems(data5);
    }

    public void tblColClick(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        if (!copyTable.getSelectionModel().getSelectedIndices().isEmpty()) {
            int bookid = ((BookCopy) copyTable.getSelectionModel().getSelectedItem()).getBookID();
            String fname = ((BookCopy) copyTable.getSelectionModel().getSelectedItem()).getaFirstName();
            String lname = ((BookCopy) copyTable.getSelectionModel().getSelectedItem()).getaLastName();
            String title = ((BookCopy) copyTable.getSelectionModel().getSelectedItem()).getTitle();
            int year = ((BookCopy) copyTable.getSelectionModel().getSelectedItem()).getPubYear();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/book.fxml"));
                Pane myPane = (Pane) loader.load();
                BookController controller = loader.<BookController>getController();
                controller.labFname.setText(controller.labFname.getText() + fname);
                controller.labLname.setText(controller.labLname.getText() + lname);
                controller.labTitle.setText(controller.labTitle.getText() + title);
                controller.labYear.setText(controller.labYear.getText() + year);

                //getting copies no
                int copyNo = 0;
                conn = DBUtil.dbConnect();
                String query = "SELECT COUNT(ISBN) as CopyNo\n" +
                        "FROM bookcopy\n" +
                        "WHERE bookcopy.BookID = " + bookid;
                Statement stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery(query);
                while (result.next()) {
                    copyNo = result.getInt(1);
                }
                stmt.close();

                //getting Borrows No:
                int borrowNo = 0;
                conn = DBUtil.dbConnect();
                String query2 = "SELECT sum(NoBorrows) as noBorrowsB\n" +
                        "FROM bookcopy\n" +
                        "WHERE BookID = " + bookid;
                Statement stmt2 = conn.createStatement();
                ResultSet result2 = stmt2.executeQuery(query2);
                while (result2.next()) {
                    borrowNo = result2.getInt(1);
                }
                stmt2.close();
                conn.close();

                controller.labNborrows.setText("Number Of Borrows: " + borrowNo);
                controller.labNCopies.setText("Number Of Copies: " + copyNo);

                Scene scene = new Scene(myPane);
                Stage stage = new Stage();
                stage.setTitle("Book Page");
                stage.setScene(scene);

                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }


    public void btnSearchClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            copyTable.getItems().clear();
            data = FXCollections.observableArrayList();
            String title = srchTitle.getText();
            String aFname = srchF.getText();
            String aLname = srchL.getText();
            conn = DBUtil.dbConnect();
            String query = "select book.BookID, ISBN, Title, AuthorFname, AuthorLname, pubYear, LocZIPCode, Stat from book left outer join bookcopy on (book.BookID = bookcopy.BookID) WHERE Title = '" + title + "' AND AuthorFname = '" + aFname + "' AND AuthorLname = '" + aLname + "';";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                data.add(new BookCopy(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getBoolean(8)));
            }
            rs.close();
            stmt.close();
            conn.close();
            copyTable.setItems(data);
        } catch (SQLSyntaxErrorException e) {
            showAlert("Please don't use special characters");
        }
    }

    public void btnShowClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        copyTable.getItems().clear();
        data = FXCollections.observableArrayList();
        conn = DBUtil.dbConnect();
        String query = "select book.BookID, ISBN, Title, AuthorFname, AuthorLname, pubYear, LocZIPCode, Stat from book left outer join bookcopy on (book.BookID = bookcopy.BookID);";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            int isbn = 0;
            int zipcode = 0;
            try {
                isbn = rs.getInt(2);
                zipcode = rs.getInt(7);
            } catch (NullPointerException e) {
            }
            data.add(new BookCopy(rs.getInt(1), isbn, rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), zipcode, rs.getBoolean(8)));
        }
        rs.close();
        stmt.close();
        conn.close();
        copyTable.setItems(data);
    }

    public void btnAddCopyClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            String title = insertTitle.getText();
            String firstname = insertAuthorF.getText();
            String lastname = insertAuthorL.getText();
            String publisher = insertPublisher.getText();
            String yearTxt = insertYear.getText();
            int ISBN = Integer.parseInt(insertIsbn.getText());
            int zipCode = Integer.parseInt(insertLoc.getText());
            int year = Integer.parseInt(yearTxt);

            if (!insertTitle.getText().isEmpty() &&
                    !insertAuthorL.getText().isEmpty() &&
                    !insertAuthorF.getText().isEmpty() &&
                    !insertYear.getText().isEmpty() &&
                    !insertPublisher.getText().isEmpty() &&
                    !insertIsbn.getText().isEmpty() &&
                    !insertLoc.getText().isEmpty()) {

                conn = DBUtil.dbConnect();

                String query = "select BookID from book WHERE Title = '" + title + "' AND AuthorFname = '" + firstname + "' AND AuthorLname = '" + lastname + "' AND publisher = '" + publisher + "' AND pubYear = '" + year + "';";
                Statement stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery(query);
                if (!result.next()) {
                    showAlert("Please make sure the book you want to add copies to exists in the database!");
                } else {
                    Statement s = conn.createStatement();
                    result = s.executeQuery(query);
                    while (result.next()) {
                        int bookID = result.getInt(1);
                        Statement stmt2 = conn.createStatement();
                        String query2 = "INSERT INTO bookcopy VALUES ('" + ISBN + "', CURRENT_TIMESTAMP , '1', '" + bookID + "', '" + zipCode + "', '0');";
                        int result2 = stmt2.executeUpdate(query2);
                        if (result2 == 1) {
                            showAlert("Copy Successfully Added!");
                        }
                    }
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please make sure you filled all required information");
                alert.showAndWait();
            }

            conn = DBUtil.dbConnect();
            String query = "select COUNT(*) from bookcopy;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                booksNo.setText("" + rs.getInt(1));
            }
            rs.close();
            stmt.close();
            conn.close();


        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a valid Year and ISBN and Zip code");
            alert.showAndWait();
        } catch (SQLIntegrityConstraintViolationException e) {
            showAlert("An error happened! \n Please make sure that:\n   1- The ISBN you used is not used before." +
                    "\n   2- The book you want to add one of its copies already exists in the database." +
                    "\n   3- The zip code you entered belongs to a valid location of your library.");
        } catch (SQLSyntaxErrorException e) {
            showAlert("Please don't use special characters!");
        }

    }

    public void btnAddLocClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            int zipCode = Integer.parseInt(addZipCode.getText());
            int buildNo = Integer.parseInt(addBuildNo.getText());
            String street = addStreet.getText();
            String city = addCity.getText();
            String state = addState.getText();

            if (!addState.getText().isEmpty() &&
                    !addCity.getText().isEmpty() &&
                    !addStreet.getText().isEmpty() &&
                    !addBuildNo.getText().isEmpty() &&
                    !addZipCode.getText().isEmpty()) {

                conn = DBUtil.dbConnect();
                String query = "INSERT INTO location VALUES ( '" + zipCode + "', '" + state + "', '" + city + "', '" + street + "', '" + buildNo + "')";
                Statement stmt = conn.createStatement();
                int result = stmt.executeUpdate(query);
                if (result == 1) {
                    showAlert("Location successfully added");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please make sure you filled all required information");
                alert.showAndWait();
            }
            locTable.getItems().clear();
            data4 = FXCollections.observableArrayList();
            conn = DBUtil.dbConnect();
            String query = "SELECT * FROM location";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                data4.add(new Location(rs.getInt(1), rs.getInt(5), rs.getString(4), rs.getString(3), rs.getString(2)));
            }
            rs.close();
            stmt.close();
            conn.close();
            locTable.setItems(data4);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a valid zip Code and Building Number");
            alert.showAndWait();
        } catch (SQLIntegrityConstraintViolationException e) {
            showAlert("Zip Code must be unique");
        } catch (SQLSyntaxErrorException e) {
            showAlert("Please Don't Use Special Characters!");
        }
    }

    public void btnAddClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            String title = insertTitle.getText();
            String firstname = insertAuthorF.getText();
            String lastname = insertAuthorL.getText();
            String publisher = insertPublisher.getText();
            String yearTxt = insertYear.getText();
            int year = Integer.parseInt(yearTxt);

            if (!insertTitle.getText().isEmpty() &&
                    !insertAuthorL.getText().isEmpty() &&
                    !insertAuthorF.getText().isEmpty() &&
                    !insertYear.getText().isEmpty() &&
                    !insertPublisher.getText().isEmpty()) {

                conn = DBUtil.dbConnect();
                String query = "select BookID from book WHERE Title = '" + title + "' AND AuthorFname = '" + firstname + "' AND AuthorLname = '" + lastname + "' AND publisher = '" + publisher + "' AND pubYear = '" + year + "';";
                Statement stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery(query);
                if (!result.next()) {
                    query = "INSERT INTO book VALUES (NULL, '" + title + "', '" + firstname + "', '" + lastname + "', '" + publisher + "', '" + year + "')";
                    stmt = conn.createStatement();
                    int result2 = stmt.executeUpdate(query);
                    if (result2 == 1) {
                        showAlert("Book Successfully added!");
                        stmt.close();
                        conn.close();
                    }
                } else {
                    showAlert("The Book Already Exists");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please make sure you filled all required information");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a valid Year");
            alert.showAndWait();
        } catch (SQLSyntaxErrorException e) {
            showAlert("Please Don't Use Special Characters!");
        }
    }

    public void btnAddStuClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            int id = Integer.parseInt(addId.getText());
            int password = addPW.getText().trim().hashCode();
            String fname = addFNs.getText();
            String lname = addLNs.getText();
            String dep = addDep.getText();
            String grade = addGrade.getText();
            String mail = addEmail.getText();
            String address = addAddress.getText();

            if (!addId.getText().isEmpty() &&
                    !addPW.getText().isEmpty() &&
                    !addLNs.getText().isEmpty() &&
                    !addFNs.getText().isEmpty() &&
                    !addGrade.getText().isEmpty() &&
                    !addEmail.getText().isEmpty() &&
                    !addAddress.getText().isEmpty() &&
                    !addDep.getText().isEmpty()) {

                conn = DBUtil.dbConnect();
                String query = "INSERT INTO libuser VALUES ( '" + id + "', '" + fname + "', '" + lname + "', CURRENT_TIMESTAMP , '" + password + "', '" + mail + "', '" + address + "')";
                String query2 = "INSERT INTO student VALUES ( '" + id + "', '" + dep + "', '" + grade + "' );";
                Statement stmt = conn.createStatement();
                int result = stmt.executeUpdate(query);
                int rs = stmt.executeUpdate(query2);
                if (result == 1 && rs == 1) {
                    showAlert("Student successfully added");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please make sure you filled all required information");
                alert.showAndWait();
            }
            showAllStudents();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a valid ID");
            alert.showAndWait();
        } catch (SQLIntegrityConstraintViolationException e) {
            showAlert("ID must be unique");
        } catch (SQLSyntaxErrorException e) {
            showAlert("Please Don't Use Special Characters!");
        }
    }

    private void showAllProfessors() throws SQLException, ClassNotFoundException {
        professorTable.getItems().clear();
        data7 = FXCollections.observableArrayList();
        conn = DBUtil.dbConnect();
        String query = "SELECT libuser.LibUserID, FName, LName, regDate , Email, Address, Title \n" +
                "FROM libuser\n" +
                "JOIN professor on (libuser.LibUserID=professor.LibUserID)";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            data7.add(new Professor(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7)));
        }
        rs.close();
        stmt.close();
        conn.close();
        professorTable.setItems(data7);

    }

    public void btnShowProClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        showAllProfessors();
    }

    public void btnSearchProClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            int id = Integer.parseInt(prchId.getText());
            professorTable.getItems().clear();
            data7 = FXCollections.observableArrayList();
            conn = DBUtil.dbConnect();
            String query = "SELECT libuser.LibUserID, FName, LName, regDate , Email, Address, Title \n" +
                    "FROM libuser\n" +
                    "JOIN professor on (libuser.LibUserID=professor.LibUserID)" +
                    "WHERE libuser.LibUserID= '" + id + "' ;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                data7.add(new Professor(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
            rs.close();
            stmt.close();
            conn.close();
            professorTable.setItems(data7);
        } catch (NumberFormatException e) {
            showAlert("Please Enter A Valid ID");
        } catch (SQLSyntaxErrorException e) {
            showAlert("Please Don't Use Special Characters!");
        }
    }

    public void btnAddProClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            int id = Integer.parseInt(addIdp.getText());
            int password = addPWp.getText().trim().hashCode();
            String fname = addFNp.getText();
            String lname = addLNp.getText();
            String title = addtitlep.getText();
            String mail = addEmailp.getText();
            String address = addAddressp.getText();

            if (!addIdp.getText().isEmpty() &&
                    !addPWp.getText().isEmpty() &&
                    !addLNp.getText().isEmpty() &&
                    !addFNp.getText().isEmpty() &&
                    !addtitlep.getText().isEmpty() &&
                    !addEmailp.getText().isEmpty() &&
                    !addAddressp.getText().isEmpty()) {

                conn = DBUtil.dbConnect();
                String query = "INSERT INTO libuser VALUES ( '" + id + "', '" + fname + "', '" + lname + "', CURRENT_TIMESTAMP , '" + password + "', '" + mail + "', '" + address + "')";
                String query2 = "INSERT INTO professor VALUES ( " + id + ", '" + title + "')";
                Statement stmt = conn.createStatement();
                int result = stmt.executeUpdate(query);
                int rs = stmt.executeUpdate(query2);
                if (result == 1 && rs == 1) {
                    showAlert("Professor successfully added");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please make sure you filled all required information");
                alert.showAndWait();
            }
            showAllProfessors();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a valid ID");
            alert.showAndWait();
        } catch (SQLIntegrityConstraintViolationException e) {
            showAlert("ID must be unique");
        } catch (SQLSyntaxErrorException e) {
            showAlert("Please Don't Use Special Characters!");
        }
    }

    private void showAllStudents() throws SQLException, ClassNotFoundException {
        studentTable.getItems().clear();
        data6 = FXCollections.observableArrayList();
        conn = DBUtil.dbConnect();
        String query = "SELECT libuser.LibUserID, FName, LName, regDate , Email, Address, Department, Grade \n" +
                "FROM libuser\n" +
                "JOIN student on (libuser.LibUserID=student.LibUserID)";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            data6.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
        }
        rs.close();
        stmt.close();
        conn.close();
        studentTable.setItems(data6);

    }

    public void btnShowStuClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        showAllStudents();
    }

    public void btnSearchStClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            int id = Integer.parseInt(srchId.getText());
            studentTable.getItems().clear();
            data6 = FXCollections.observableArrayList();
            conn = DBUtil.dbConnect();
            String query = "SELECT libuser.LibUserID, FName, LName, regDate , Email, Address, Department, Grade \n" +
                    "FROM libuser\n" +
                    "JOIN student on (libuser.LibUserID=student.LibUserID)" +
                    "WHERE libuser.LibUserID= '" + id + "' ;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                data6.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
            rs.close();
            stmt.close();
            conn.close();
            studentTable.setItems(data6);
        } catch (NumberFormatException e) {
            showAlert("Please Enter A Valid ID");
        } catch (SQLSyntaxErrorException e) {
            showAlert("Please Don't Use Special Characters!");
        }
    }


}
