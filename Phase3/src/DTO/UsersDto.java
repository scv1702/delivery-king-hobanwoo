package DTO;

public class UsersDto {
    public int userId;
    public String username;
    public String password;
    public String dName;
    public String phoneNumber;
    public String membershipTier;

    public UsersDto(int userId, String username, String password, String dName, String phoneNumber, String membershipTier) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.dName = dName;
        this.phoneNumber = phoneNumber;
        this.membershipTier = membershipTier;
    }

    public UsersDto(String username, String password, String dName, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.dName = dName;
        this.phoneNumber = phoneNumber;
    }

    public UsersDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}