package indi.welljun.domain;

public class User {
    private int uid;
    private String uname;
    private String password;
    private String email;
    private String phone;
    private String utype;

    public int getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getUname() {
        return uname;
    }

    public String getUtype() {
        return utype;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
