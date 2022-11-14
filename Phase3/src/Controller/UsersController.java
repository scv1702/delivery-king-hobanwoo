package Controller;

import DTO.CouponDto;
import DTO.UserAddressDto;
import DTO.UsersDto;
import Model.CouponModel;
import Model.Oracle;
import Model.UserAddressModel;
import Model.UsersModel;
import View.CouponView;
import View.UserAddressView;
import View.UsersView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UsersController {
    private final UsersModel usersModel;
    private final UsersView usersView = new UsersView();
    private UserAddressModel userAddressModel;
    private UserAddressView userAddressView = new UserAddressView();

    private CouponModel couponModel;
    private CouponView couponView = new CouponView();

    public UsersController(UsersModel usersModel) {
        this.usersModel = usersModel;
    }
    public UsersDto signUp() {
        this.usersView.signUpStart();
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

    public void profile(UserAddressModel userAddressModel, CouponModel couponModel) {
        this.userAddressModel = userAddressModel;
        this.couponModel = couponModel;
        this.usersView.profile();
        Scanner in = new Scanner(System.in);
        int selectProfile= in.nextInt();
        switch (selectProfile) {
            case 1: // 내 프로필 보기
                this.usersView.myProfile(this.usersModel.getUsers());
                break;
            case 2: // 주소 등록하기
                insertAddress(this.usersModel.getUsers());
                break;
            case 3: // 등록된 주소 목록 조회
                // userAddressModel= new UserAddressModel(database);
                showMyAddress(this.usersModel.getUsers());
                break;
            case 4: // 보유 쿠폰 조회
                showMyCoupon(this.usersModel.getUsers());
                break;
        }
    }

    public UserAddressDto insertAddress(UsersDto usersDto) {
        this.userAddressView.insertAddressStart();
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

    public UserAddressDto showMyAddress(UsersDto usersDto) {
        this.userAddressView.showMyAddressStart();
        try {
            ArrayList<UserAddressDto> myAddress = this.userAddressModel.showMyAddress(usersDto);
            if (myAddress != null) {
                this.userAddressView.showMyAddress(myAddress);
            }
            this.userAddressView.showMyAddressEnd();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }

    public CouponDto showMyCoupon(UsersDto usersDto) {
        this.couponView.showMyCouponsStart();
        try {
            ArrayList<CouponDto> myCoupon = this.couponModel.showMyCoupons(usersDto);
            if (myCoupon != null) {
                this.couponView.showMyCoupons(myCoupon);
            }
            this.couponView.showMyCouponsEnd();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }
}