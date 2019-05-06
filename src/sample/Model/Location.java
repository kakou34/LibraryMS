package sample.Model;

import javafx.beans.property.*;

public class Location {
    private final IntegerProperty zipCode;
    private final IntegerProperty buildNo;
    private final StringProperty street;
    private final StringProperty city;
    private final StringProperty state;

    public Location(int zip, int buildingdNo, String str, String cty, String stt) {
        this.zipCode = new SimpleIntegerProperty(zip);
        this.buildNo = new SimpleIntegerProperty(buildingdNo);
        this.street = new SimpleStringProperty(str);
        this.city = new SimpleStringProperty(cty);
        this.state = new SimpleStringProperty(stt);

    }

    public int getZipCode() {
        return zipCode.get();
    }

    public void setZipCode(int zipCode) {
        this.zipCode.set(zipCode);
    }

    public IntegerProperty zipCodeProperty() {
        return zipCode;
    }

    public int getBuildingNo() {
        return buildNo.get();
    }

    public void setBuildingNo(int buildingNo) {
        this.buildNo.set(buildingNo);
    }

    public IntegerProperty BuildingNoProperty() {
        return buildNo;
    }

    public String getState() {
        return state.get();
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public StringProperty stateProperty() {
        return state;
    }

    public String getStreet() {
        return street.get();
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public StringProperty streetProperty() {
        return street;
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public StringProperty cityProperty() {
        return city;
    }


}
