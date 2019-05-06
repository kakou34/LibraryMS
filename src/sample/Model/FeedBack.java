package sample.Model;

import javafx.beans.property.*;

public class FeedBack {
    private final IntegerProperty userId;
    private final StringProperty desc;

    public FeedBack(int id, String dsc) {
        this.userId = new SimpleIntegerProperty(id);
        this.desc = new SimpleStringProperty(dsc);

    }

    public int getUserId() {
        return userId.get();
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    public String getDesc() {
        return desc.get();
    }

    public void setDesc(String city) {
        this.desc.set(city);
    }

    public StringProperty descProperty() {
        return desc;
    }


}
