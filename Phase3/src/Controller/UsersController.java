package Controller;

import DTO.UsersDto;
import Model.UsersModel;
import View.UsersView;

import java.sql.SQLException;

public class UsersController {
    private final UsersModel usersModel;
    private final UsersView usersView = new UsersView();

    public UsersController(UsersModel usersModel) {
        this.usersModel = usersModel;
    }

    public UsersDto signUp() {
        this.usersView.signUpStart();
        try {
            UsersDto usersDto = this.usersView.signUp();
            UsersDto signedUp = this.usersModel.insert(usersDto);
            if (signedUp != null) {
                return signedUp;
            }
            this.usersView.signUpEnd();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }

    public UsersDto login() {
        this.usersView.loginStart();
        try {
            UsersDto usersDto = this.usersView.login();
            UsersDto logined = this.usersModel.login(usersDto);
            if (logined != null) {
                this.usersView.loginEnd();
                return logined;
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        this.usersView.loginFail();
        return null;
    }

    public void profile() {
        this.usersView.profile(this.usersModel.getUsers());
    }
}