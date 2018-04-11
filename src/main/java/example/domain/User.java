package example.domain;

import java.util.Date;

public class User {
    private int id;
    private String name;
    private String birthday;
    private String address;

    // 构造方法

    public User() {
        super();
    }

    public User(int id, String name, String birthday, String address) {
        super();
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
    }

    // getter & setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

