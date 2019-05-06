package sample.Model;

import javafx.beans.property.*;

import java.util.Date;

public class Professor {
    private final IntegerProperty id;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty email;
    private final StringProperty address;
    private final ObjectProperty<Date> regDate;
    private final StringProperty title;


    public Professor(int id, String firstName, String lastName, Date regDate, String email, String address, String title) {
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
        this.regDate = new SimpleObjectProperty<>(regDate);
        this.title = new SimpleStringProperty(title);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty addressProperty() {
        return address;
    }

    public Date getRegDate() {
        return regDate.get();
    }

    public void setRegDate(Date regDate) {
        this.regDate.set(regDate);
    }

    public ObjectProperty<Date> regDateProperty() {
        return regDate;
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


}
