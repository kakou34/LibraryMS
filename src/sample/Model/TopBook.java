package sample.Model;

import javafx.beans.property.*;


public class TopBook {

    private final IntegerProperty ISBN;
    private final IntegerProperty borrows;
    private final StringProperty title;

    public TopBook(String title, int ISBN, int borrows) {
        this.ISBN = new SimpleIntegerProperty(ISBN);
        this.borrows = new SimpleIntegerProperty(borrows);
        this.title = new SimpleStringProperty(title);
    }

    public int getISBN() {
        return ISBN.get();
    }

    public void setISBN(int isbn) {
        this.ISBN.set(isbn);
    }

    public IntegerProperty isbnProperty() {
        return ISBN;
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

    public int getBorrows() {
        return borrows.get();
    }

    public void setBorrows(int borrows) {
        this.borrows.set(borrows);
    }

    public IntegerProperty borrowsProperty() {
        return borrows;
    }
}
