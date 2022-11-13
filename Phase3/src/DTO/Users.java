package DTO;

public class Users {
    public int userId;
    public String username;
    public String password;
    public String dName;
    public String phoneNumber;

    public Users(int userId, String username, String password, String dName, String phoneNumber) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.dName = dName;
        this.phoneNumber = phoneNumber;
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }
}