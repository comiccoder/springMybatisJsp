package example.entity;

public class User {
    private int id;
    private String name;
    private String birthday;
    private String address;
    private String pass;

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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}

