package example.entity;

public class Permission
{
    //直接查出权限
    private int id;
    private String permission;
    private String perDes;
    private int   isUsed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPerDes() {
        return perDes;
    }

    public void setPerDes(String perDes) {
        this.perDes = perDes;
    }

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(int isUsed) {
        this.isUsed = isUsed;
    }

}
