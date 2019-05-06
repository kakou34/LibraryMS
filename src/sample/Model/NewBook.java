package sample.Model;

import javafx.beans.property.*;
import java.util.Date;

public class NewBook {

    private final IntegerProperty ISBN;
    private final ObjectProperty<Date> storeDate;
    private final StringProperty title;

    public NewBook(String title, int ISBN, Date storeDate) {
        this.ISBN = new SimpleIntegerProperty(ISBN);
        this.storeDate = new SimpleObjectProperty<>(storeDate);
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

    public Date getStoreDate() {
        return storeDate.get();
    }

    public void setStoreDate(Date storeDate) {
        this.storeDate.set(storeDate);
    }

    public ObjectProperty<Date> storeDateProperty() {
        return storeDate;
    }
}
