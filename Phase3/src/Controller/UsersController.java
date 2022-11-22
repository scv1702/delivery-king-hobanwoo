package Controller;

import DTO.CouponDto;
import DTO.UserAddressDto;
import DTO.UsersDto;
import Model.CouponModel;
import Model.UserAddressModel;
import Model.UsersModel;
import View.CouponView;
import View.UserAddressView;
import View.UsersView;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsersController {
    private final UsersModel usersModel;
    private final UserAddressModel userAddressModel;
    private final CouponModel couponModel;
    private final UsersView usersView = new UsersView();
    private final UserAddressView userAddressView = new UserAddressView();
    private final CouponView couponView = new CouponView();

    public UsersController(UsersModel usersModel, UserAddressModel userAddressModel, CouponModel couponModel) {
        this.usersModel = usersModel;
        this.userAddressModel = userAddressModel;
        this.couponModel = couponModel;
    }

    public UsersDto signUp() {
        try {
            UsersDto usersDto = this.usersView.signUp();
            UsersDto signedUp = this.usersModel.insert(usersDto);
            if (signedUp != null) {
                this.usersView.signUpEnd();
                return signedUp;
            }
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

    public void usersMenu() {
        switch (this.usersView.usersMenu()) {
            case 1:
                this.usersView.showMyProfile(this.usersModel.getUsers());
                break;
            case 2:
                insertAddress(this.usersModel.getUsers());
                break;
            case 3:
                myAddress(this.usersModel.getUsers());
                break;
            case 4:
                myCoupon(this.usersModel.getUsers());
                break;
            default:
                System.out.println("잘못 입력하셨습니다.\n");
                break;
        }
    }

    public UserAddressDto insertAddress(UsersDto usersDto) {
        try {
            String newAddress = this.userAddressView.insertAddress();
            boolean myAddress = this.userAddressModel.insert(usersDto, newAddress);
            if (myAddress) {
                this.userAddressView.insertAddressEnd();
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }

    public UserAddressDto myAddress(UsersDto usersDto) {
        try {
            ArrayList<UserAddressDto> myAddress = this.userAddressModel.getAddressByUser(usersDto);
            if (myAddress != null) {
                this.userAddressView.showMyAddress(myAddress);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }

    public CouponDto myCoupon(UsersDto usersDto) {
        try {
            ArrayList<CouponDto> myCoupon = this.couponModel.getCouponsByUser(usersDto);
            if (myCoupon != null && myCoupon.size() > 0) {
                this.couponView.showMyCoupons(myCoupon);
            } else {
                this.couponView.noMyCoupons();
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }
}