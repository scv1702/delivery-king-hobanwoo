package View;

import DTO.OrdersDto;
import DTO.ReviewDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ReviewView {
    public int selectOrderForReview(ArrayList<OrdersDto> ordersList) {
        for (OrdersDto orders : ordersList) {
            System.out.println("주문 번호: " + orders.getOrderId());
            System.out.println("가게 이름: " + orders.getStoreName());
            System.out.println("결제 수단: " + orders.getPayment());
            System.out.println("주문일: " + orders.getOrderDate() + '\n');
        }
        System.out.print("리뷰를 남길 주문 번호: ");
        Scanner in = new Scanner(System.in);
        int orderNum = Integer.parseInt(in.nextLine());
        System.out.println();
        return orderNum;
    }

    public ReviewDto writeReview() {
        Scanner in = new Scanner(System.in);
        System.out.print("별점(1 ~ 5): ");
        int starRating = Integer.parseInt(in.nextLine());
        System.out.print("내용: ");
        String comments = in.nextLine();
        System.out.println();
        return new ReviewDto(starRating, comments);
    }

    public int reviewMenu() {
        Scanner in = new Scanner(System.in);
        System.out.println("1. 리뷰 쓰기");
        System.out.println("2. 리뷰 수정");
        System.out.println("3. 리뷰 삭제");
        System.out.println("4. 내가 쓴 리뷰");
        System.out.println("5. 가게별 리뷰");
        System.out.print(": ");
        int select = Integer.parseInt(in.nextLine());
        System.out.println();
        return select;
    }

    public void noOrderForReview() {
        System.out.println("리뷰를 작성할 주문이 없습니다.\n");
    }

    public void writeSuccess() {
        System.out.println("리뷰 작성이 완료 됐습니다.\n");
    }

    public void showMyReview(ArrayList<ReviewDto> reviewList) {
        if (reviewList.size() == 0) {
            System.out.println("작성한 리뷰가 없습니다.\n");
            return;
        }
        showReview(reviewList);
    }

    public void reviewByStore(ArrayList<ReviewDto> reviewList) {
        if (reviewList.size() == 0) {
            System.out.println("해당 가게의 리뷰가 없습니다.\n");
            return;
        }
        showReview(reviewList);
    }

    public void showReview(ArrayList<ReviewDto> reviewList) {
        for (ReviewDto review : reviewList) {
            System.out.println("리뷰 번호: " + review.getReviewId());
            System.out.println("작성자: " + review.getUserId());
            System.out.println("가게 이름: " + review.getStoreName());
            System.out.println("별점: " + review.getStarRating());
            System.out.println("내용: " + review.getComments());
            System.out.println("작성 시간: " + review.getCreatedAt() + '\n');
        }
    }

    public int getReviewIdForUpdate() {
        System.out.print("수정할 리뷰 번호: ");
        Scanner in = new Scanner(System.in);
        int updateNum = Integer.parseInt(in.nextLine());
        System.out.println();
        return updateNum;
    }

    public int getReviewIdForDelete() {
        System.out.print("삭제할 리뷰 번호: ");
        Scanner in = new Scanner(System.in);
        int deleteNum = Integer.parseInt(in.nextLine());
        System.out.println();
        return deleteNum;
    }

    public ReviewDto updateReview(ReviewDto review) {
        Scanner in = new Scanner(System.in);
        System.out.print("별점(1 ~ 5): ");
        int starRating = Integer.parseInt(in.nextLine());
        System.out.print("내용: ");
        String comments = in.nextLine();
        String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println();
        review.setStarRating(starRating);
        review.setComments(comments);
        review.setCreatedAt(createdAt);
        return review;
    }

    public void updateSuccess() {
        System.out.println("리뷰가 수정 됐습니다.\n");
    }

    public void deleteSuccess() {
        System.out.println("리뷰가 삭제 됐습니다.\n");
    }

    public String selectStoreForReview() {
        System.out.print("리뷰를 조회할 가게 이름: ");
        Scanner in = new Scanner(System.in);
        String storeName = in.nextLine();
        System.out.println();
        return storeName;
    }
}
