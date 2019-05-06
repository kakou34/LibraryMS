package sample.Model;

import javafx.beans.property.*;

import java.util.Date;

public class Borrow {
    private final IntegerProperty ISBN;
    private final IntegerProperty userID;
    private final ObjectProperty<Date> borrowDate;
    private final ObjectProperty<Date> returnDate;

    public Borrow(int ISBN, int userID, Date borrowDate, Date returnDate) {
        this.ISBN = new SimpleIntegerProperty(ISBN);
        this.userID = new SimpleIntegerProperty(userID);
        this.borrowDate = new SimpleObjectProperty<>(borrowDate);
        this.returnDate = new SimpleObjectProperty<>(returnDate);

    }

    public Date getBorrowDate() {
        return borrowDate.get();
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate.set(borrowDate);
    }

    public ObjectProperty<Date> borrowDateProperty() {
        return borrowDate;
    }

    public Date getReturnDate() {
        return returnDate.get();
    }

    public void setReturnDate(Date borrowDate) {
        this.returnDate.set(borrowDate);
    }

    public ObjectProperty<Date> returnDateProperty() {
        return returnDate;
    }

    public int getUserID() {
        return userID.get();
    }

    public void setUserID(int userID) {
        this.userID.set(userID);
    }

    public IntegerProperty userIDProperty() {
        return userID;
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

}
