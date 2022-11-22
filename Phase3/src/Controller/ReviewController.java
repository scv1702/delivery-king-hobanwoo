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

    public int reviewMenu() {
        return this.reviewView.reviewMenu();
    }

    public void write() {
        try {
            ArrayList<OrdersDto> myOrderList = this.ordersModel.getOrdersByUser();
            if (myOrderList == null) {
                reviewView.noOrderForReview();
                return;
            }
            int orderId = reviewView.selectOrderForReview(myOrderList);
            OrdersDto orders= this.ordersModel.getOrdersById(orderId);
            ReviewDto review = reviewView.writeReview();
            int reviewId = reviewModel.getMaxReviewId();
            ReviewDto insertedReview = new ReviewDto(
                    ++reviewId,
                    orders.getUserId(),
                    orders.getStoreId(),
                    review.getStarRating(),
                    review.getComments(),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            );
            reviewModel.insert(insertedReview);
            reviewView.writeSuccess();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    public void update() {
        try {
            myReview();
            int reviewId = this.reviewView.getReviewIdForUpdate();
            ReviewDto review = this.reviewModel.getReviewById(reviewId);
            if (review == null) {
                System.out.println("해당 리뷰가 없습니다.\n");
                return;
            }
            ReviewDto updateDto = this.reviewView.updateReview(review);
            this.reviewModel.update(updateDto);
            this.reviewView.updateSuccess();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    public void delete() {
        try {
            myReview();
            int reviewId = this.reviewView.getReviewIdForDelete();
            this.reviewModel.deleteById(reviewId);
            reviewView.deleteSuccess();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    public void myReview() {
        try {
            this.reviewView.showMyReview(this.reviewModel.getReviewByUserId(usersModel.getUsers().userId));
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    public void reviewByStoreName() {
        try {
            String storeName = this.reviewView.selectStoreForReview();
            ArrayList<ReviewDto> reviewList = this.reviewModel.getReviewByStoreName(storeName);
            this.reviewView.reviewByStore(reviewList);
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    public void unReviewedOrdersList() {
        try {
            ArrayList<OrdersDto> myOrderList = this.ordersModel.getUnreviewedOrdersByUser();
            if (myOrderList == null) {
                reviewView.noOrderForReview();
                return;
            }
            reviewView.showOrderForReview(myOrderList);
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }
}
