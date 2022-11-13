import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Review {
    private final Connection conn;
    private final Announcement announcement;
    private final String insertTemplate = "INSERT INTO REVIEW VALUES ( ?, ?, ?, ?, ?, TO_DATE(?, 'yyyy-mm-dd'))";
    private Users users;

    public Review(Oracle database, Users users) throws SQLException {
        this.conn = database.getConnection();
        this.announcement = new Announcement();
        this.users = users;
    }

    public void review() {
        Scanner in = new Scanner(System.in);
        announcement.review();
        switch (in.nextInt()) {
            case 1: // 리뷰 작성
                announcement.reviewStart(1);
                // Review 작성 format
                int Review_ID = 0; // 자동으로 오름차순으로 되도록
                int User_ID = 0; // 유저ID는 회원정보에서 가져올 수 있도록
                int Store_ID = 0;
                int Star_Rating = 0;
                String Comments = "";
                String Created_At = "2021-09-25 15:43:48"; // 작성일 현재 날짜로 받아올 수 있도록
                // 입력 받기
                System.out.print("│  가게ID : ");
                Store_ID = in.nextInt();
                System.out.print("│  평점 : ");
                Star_Rating = in.nextInt();
                in.nextLine(); // Int형 받고 string형 받았을 때의 개행문자 제거
                System.out.print("│  리뷰 내용 : ");
                Comments = in.nextLine();
                /* query */
                announcement.reviewData(
                        1,
                        Review_ID,
                        User_ID,
                        Store_ID,
                        Star_Rating,
                        Comments,
                        Created_At
                );
                break;
            case 2: // 리뷰 수정
                announcement.reviewStart(2);
                System.out.println("아직 구현 전");
                break;
            case 3: // 리뷰 삭제
                announcement.reviewStart(3);
                System.out.println("아직 구현 전");
                break;
            case 4: // 내가 쓴 리뷰 조회
                announcement.reviewStart(4);
                System.out.println("아직 구현 전");
                break;
            case 5: // 선택 가게 리뷰 조회
                announcement.reviewStart(5);
                System.out.println("아직 구현 전");
                break;
        }
        announcement.reviewEnd();
    }
}