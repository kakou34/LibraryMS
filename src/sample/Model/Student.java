package sample.Model;

import javafx.beans.property.*;

import java.util.Date;

public class Student {
    private final IntegerProperty id;
    private final StringProperty department;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty email;
    private final StringProperty address;
    private final ObjectProperty<Date> regDate;
    private final StringProperty grade;

    public Student(int id, String firstName, String lastName, Date regDate, String email, String address, String department, String grade) {
        this.id = new SimpleIntegerProperty(id);
        this.department = new SimpleStringProperty(department);
        this.grade = new SimpleStringProperty(grade);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
        this.regDate = new SimpleObjectProperty<>(regDate);

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

    public String getDepartment() {
        return department.get();
    }

    public void setDepartment(String dep) {
        this.department.set(dep);
    }

    public StringProperty departmentProperty() {
        return department;
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

    public String getGrade() {
        return grade.get();
    }

    public void setGrade(String grade) {
        this.grade.set(grade);
    }

    public StringProperty gradeProperty() {
        return grade;
    }

}
