package Controller;

import DTO.OrdersDto;
import DTO.ReviewDto;
import Model.OrdersModel;
import Model.ReviewModel;
import Model.UsersModel;
import View.ReviewView;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ReviewController {

    private final ReviewView reviewView = new ReviewView();
    private final ReviewModel reviewModel;
    private final OrdersModel ordersModel;
    private final UsersModel usersModel;
    public ReviewController(ReviewModel reviewModel, UsersModel usersModel, OrdersModel ordersModel) {
        this.reviewModel = reviewModel;
        this.usersModel = usersModel;
        this.ordersModel = ordersModel;
    }

    public void review() {
        try {
            reviewView.startSlectOrderReview();
            //사용자의 주문 내역을 받아옴
            ArrayList<OrdersDto> ordersDtos = ordersModel.getOrdersByUser(usersModel.getUsers().userId);
            if (ordersDtos == null) {
                reviewView.noOrderReview();
                return;
            }

            //사용자가 리뷰할 order index를 받아옴.
            int orderNum = reviewView.selectOrderReview(ordersDtos);

            //리뷰할 주문 dto
            OrdersDto ordersDto = ordersDtos.get(orderNum-1);

            reviewView.startReview();
            //리뷰 내용과 별점을 받아옴
            ReviewDto reviewDto = reviewView.review();
            //리뷰 추가
            int reviewId = reviewModel.getMaxReviewId();
            ReviewDto dto = new ReviewDto(
                    ++reviewId,
                    ordersDto.getUserId(),
                    ordersDto.getStoreId(),
                    reviewDto.getStarRating(),
                    reviewDto.getComments(),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    );
            reviewModel.insertReview(dto);
            reviewView.insertSuccess();

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    public void updateReview() {
        try{
            Scanner in = new Scanner(System.in);
            ArrayList<ReviewDto> reviewDtos = getMyReview();
            if (reviewDtos.size() == 0) return;
            reviewView.startUpdate();
            int n = Integer.parseInt(in.nextLine());
            ReviewDto updateDto = reviewView.updateReview(reviewDtos.get(n-1).getReviewId());
            reviewModel.updateReview(updateDto);

            reviewView.updateSuccess();
        }catch (SQLException e){
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    public void deleteReview() {
        try{
            Scanner in = new Scanner(System.in);
            ArrayList<ReviewDto> reviewDtos = getMyReview();
            if (reviewDtos.size() == 0) return;

            reviewView.startDelete();
            int n = Integer.parseInt(in.nextLine());
            ReviewDto dto = reviewDtos.get(n-1);
            reviewModel.deleteReview(dto.getReviewId());

            reviewView.deleteSuccess();
        }catch (SQLException e){
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    public void ReviewMenu() {
        reviewView.reviewMenu();
    }

    public ArrayList<ReviewDto> getMyReview() {
        ArrayList<ReviewDto> reviewDtos = null;
        try {
            reviewDtos = reviewModel.getReviewByUser(usersModel.getUsers().userId);
            reviewView.MyReview(reviewDtos);
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return reviewDtos;
    }
}
