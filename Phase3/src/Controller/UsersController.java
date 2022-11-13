package Controller;

import DTO.Users;
import Model.UsersModel;
import View.UsersView;

import java.sql.SQLException;

public class UsersController {
    private final UsersView usersView = new UsersView();
    private final UsersModel usersModel;

    public UsersController(UsersModel usersModel) {
        this.usersModel = usersModel;
    }

    public Users signUp(Users users) {
        try {
            this.usersModel.signUp(users);
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    public Users login(Users users) {
        try {
            Users logined = this.usersModel.login(users);
            if (logined) {
                return logined;
            }
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }
}