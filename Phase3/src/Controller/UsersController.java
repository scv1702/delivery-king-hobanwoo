package Controller;

import DTO.Users;
import Model.UsersModel;
import View.UsersView;

public class UsersController {
    private final UsersView usersView;
    private final UsersModel usersModel;

    public UsersController(UsersView usersView, UsersModel usersModel) {
        this.usersView = usersView;
        this.usersModel = usersModel;
    }

    public void signUp() {
        Users users = usersView.signUp();
        try {
            this.usersModel.signUp(users);
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }
}