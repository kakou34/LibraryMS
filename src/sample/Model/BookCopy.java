package sample.Model;

import javafx.beans.property.*;

public class BookCopy {
    private final IntegerProperty bookID;
    private final IntegerProperty ISBN;
    private final BooleanProperty status;
    private final StringProperty title;
    private final IntegerProperty locZipCode;
    private final StringProperty aFirstName;
    private final StringProperty aLastName;
    private final IntegerProperty pubYear;

    public BookCopy(int id, int ISBN, String title, String firstName, String lastName, int pubYear, int locZipCode, boolean status) {
        this.bookID = new SimpleIntegerProperty(id);
        this.ISBN = new SimpleIntegerProperty(ISBN);
        this.status = new SimpleBooleanProperty(status);
        this.title = new SimpleStringProperty(title);
        this.locZipCode = new SimpleIntegerProperty(locZipCode);
        this.aFirstName = new SimpleStringProperty(firstName);
        this.aLastName = new SimpleStringProperty(lastName);
        this.pubYear = new SimpleIntegerProperty(pubYear);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }


    public int getISBN() {
        return ISBN.get();
    }

    public void setISBN(int ISBN) {
        this.ISBN.set(ISBN);
    }

    public IntegerProperty ISBNProperty() {
        return ISBN;
    }

    public boolean isStatus() {
        return status.get();
    }

    public void setStatus(boolean status) {
        this.status.set(status);
    }

    public BooleanProperty statusProperty() {
        return status;
    }

    public int getLocZipCode() {
        return locZipCode.get();
    }

    public void setLocZipCode(int locZipCode) {
        this.locZipCode.set(locZipCode);
    }

    public IntegerProperty locZipCodeProperty() {
        return locZipCode;
    }

    public String getaFirstName() {
        return aFirstName.get();
    }

    public void setaFirstName(String aFirstName) {
        this.aFirstName.set(aFirstName);
    }

    public StringProperty aFirstNameProperty() {
        return aFirstName;
    }

    public String getaLastName() {
        return aLastName.get();
    }

    public void setaLastName(String aLastName) {
        this.aLastName.set(aLastName);
    }

    public StringProperty aLastNameProperty() {
        return aLastName;
    }

    public int getPubYear() {
        return pubYear.get();
    }

    public void setPubYear(int pubYear) {
        this.pubYear.set(pubYear);
    }

    public IntegerProperty pubYearProperty() {
        return pubYear;
    }

    public int getBookID() {
        return bookID.get();
    }

    public void setBookID(int id) {
        this.bookID.set(id);
    }

    public IntegerProperty bookIDProperty() {
        return bookID;
    }
}
