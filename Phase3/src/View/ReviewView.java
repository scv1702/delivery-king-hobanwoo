package View;

import DTO.OrderMenuDto;
import DTO.OrdersDto;
import DTO.ReviewDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ReviewView {

    public void startReview(){
        System.out.println(
                "┌---------------------------------------------------------------┐\n" +
                        "│       \t\t\t\t 리뷰 작성 페이지 \t\t\t\t            │\n" +
                        "│---------------------------------------------------------------│");
    }

    public void startSlectOrderReview(){
        System.out.println(
                "┌---------------------------------------------------------------┐\n" +
                        "│       \t\t\t 리뷰할 주문 선택 페이지 \t\t\t            │\n" +
                        "│---------------------------------------------------------------│");
    }

    public int selectOrderReview(ArrayList<OrdersDto> dtos){
        int i = 1;
        for (OrdersDto dto : dtos){
            System.out.println(i + ". ");
            System.out.println("가게 이름 : " + dto.getStoreName());
            System.out.println("결제 수단 : " + dto.getPayment());
            System.out.println("주문일 : " + dto.getOrderDate());
            System.out.println("----------------------------------------------------");
            i++;
        }
        Scanner in = new Scanner(System.in);
        return Integer.parseInt(in.nextLine());
    }
    public ReviewDto review() {
        Scanner in = new Scanner(System.in);

        System.out.print("│  별점( 1~5 중 선택 ): ");
        int starRating = Integer.parseInt(in.nextLine());
        System.out.print("│  내용: ");
        String comments = in.nextLine();

        ReviewDto dto = new ReviewDto(starRating, comments);
        return dto;
    }

    public void reviewMenu() {
        System.out.println(
                "┌----┬------------------------------------------┐\n" +
                        "│ NO │\t\t\t\t\t기능\t\t\t            │\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 1  │  리뷰 쓰기\t\t\t\t\t\t\t\t    │\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 2  │  리뷰 수정하기\t\t\t\t\t\t\t\t│\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 3  │  리뷰 삭제하기\t\t\t\t\t\t\t    │\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 4  │  내가 쓴 리뷰 보기\t\t\t\t\t\t\t│\n" +
                        "└----┴------------------------------------------┘");
    }

    public void noOrderReview() {
        System.out.println("│       \t\t\t\t 리뷰를 작성할 주문이 없습니다. \t\t\t            │");
        System.out.println("└---------------------------------------------------------------┘\n");
    }

    public void insertSuccess() {
        System.out.println("│       \t\t\t\t 리뷰 작성이 완료되었습니다. \t\t\t            │");
        System.out.println("└---------------------------------------------------------------┘\n");
    }

    public void MyReview(ArrayList<ReviewDto> dtos) {
        if (dtos.size() == 0){
            System.out.println("---------------------------------------------------------");
            System.out.println("                작성한 리뷰가 없습니다           \t\t         ");
            return;
        }
        System.out.println( "┌----┬--------------------------------------------------┐\n" +
                            "│ NO │\t\t\t\t\t리뷰\t\t\t\t\t            │");
        int i = 1;
        for (ReviewDto dto : dtos) {
            System.out.println("│-------------------------------------------------------│");
            System.out.println("│ "+i+"    \t\t\t\t\t\t\t\t\t\t\t\t\t│\n" +
                                "│-------------------------------------------------------│");
            System.out.println("│  가게 이름: " + dto.getStoreName() + "   \t\t         ");
            System.out.println("│  별점: " + dto.getStarRating() + "       \t\t         ");
            System.out.println("│  내용: " + dto.getComments() + "         ");
            System.out.println("│  작성 시간: " + dto.getCreatedAt() + "   \t\t           ");
            i++;
        }
        System.out.println("└-------------------------------------------------------┘");
    }

    public void deleteSuccess() {
        System.out.println();
        System.out.println("---------------------------------------------------------");
        System.out.println();
        System.out.println("                리뷰가 삭제되었습니다.");
        System.out.println();
    }

    public void startDelete() {
        System.out.println("---------------------------------------------------------");
        System.out.println();
        System.out.print("                삭제할 리뷰 번호 : ");
    }

    public void startUpdate() {
        System.out.println("---------------------------------------------------------");
        System.out.println();
        System.out.print("                수정할 리뷰 번호 : ");
    }

    public ReviewDto updateReview(int id) {
        Scanner in = new Scanner(System.in);
        System.out.print("│  별점( 1~5 중 선택 ): ");
        int starRating = Integer.parseInt(in.nextLine());
        System.out.print("│  내용: ");
        String comments = in.nextLine();
        String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return new ReviewDto(id, starRating, comments, createdAt);
    }

    public void updateSuccess() {
        System.out.println();
        System.out.println("---------------------------------------------------------");
        System.out.println();
        System.out.println("                리뷰가 수정되었습니다.");
        System.out.println();
    }
}
