package View;

public class Announcement {
    public void main(){
        System.out.println(
                        "┌-----------------------------------------------┐\n" +
                        "│\t\t\t\t배달왕 호반우가 간다!\t\t\t\t│\n" +
                        "│\t\t\t<경북대학교 제휴업체 배달 서비스>\t\t\t│\n" +
                        "│-----------------------------------------------│\n" +
                        "│\t\t혹시, 배달왕 호반우가 처음이신가요?\t\t\t│\n" +
                        "│\t\t1. 회원가입\t\t\t\t\t\t\t\t│\n" +
                        "│-----------------------------------------------│\n" +
                        "│\t\t회원이라면, 로그인 해주세요.\t\t\t\t\t│\n" +
                        "│\t\t2. 로그인\t\t\t\t\t\t\t\t│\n" +
                        "└-----------------------------------------------┘");
    }

    public void functionSelect(){
        System.out.println(
                        "┌----┬------------------------------------------┐\n" +
                        "│ NO │\t\t\t\t\t기능\t\t\t            │\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 1  │  profile();\t\t\t\t\t\t\t\t│\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 2  │  menu();\t\t\t\t\t\t\t\t\t│\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 3  │  order();\t\t\t\t\t\t\t\t│\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 4  │  myOrder();\t\t\t\t\t\t\t\t│\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 5  │  review();\t\t\t\t\t\t\t\t│\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 6  │  종료하기\t\t\t\t\t\t\t\t\t│\n" +
                        "└----┴------------------------------------------┘");
    }

    public void programExit() {
        System.out.println("------------------------------------------------------------------\n");
        System.out.println("                         다음에 또 만나요 !                            ");
    }

    public void profile(
            int User_ID,
            String User_Name,
            String Dname,
            String Password,
            String Phone_Number,
            String Membership_Tier
    ) {
        String OutPut = String.format(
                        "┌-----------------------------------------------┐\n" +
                        "│                  내 정보 확인                   │\n" +
                        "│----┬------------------------------------------│\n" +
                        "│ 1  │  유저번호 : %d\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 2  │  유저이름 : %s \n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 3  │  학과 : %s\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 4  │  비밀번호 : %s\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 5  │  연락처 : %s\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 6  │  멤버십 등급 : %s\n" +
                        "└----┴------------------------------------------┘"
                , User_ID, User_Name, Dname, Password, Phone_Number, Membership_Tier);
        System.out.println(OutPut);
    }

    public void menu(){
        System.out.println(
                        "┌----┬------------------------------------------┐\n" +
                        "│ NO │\t\t\t\t\t기능\t\t\t            │\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 1  │  음식 카테고리별 제휴업체 조회\t\t\t\t│\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 2  │  내 학과 전체 제휴업체 메뉴\t\t\t\t\t│\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 3  │  내 주소에 있는 학과 제휴업체 메뉴\t\t\t\t│\n" +
                        "└----┴------------------------------------------┘");
    }

    public void affiliatesStart(){
        System.out.print(
                "┌-----------------------------------------------┐\n" +
                "│       \t 내 학과 전체 제휴업체 메뉴\t            │\n");

    }

    public void affiliates(
            int Menu_ID,
            int Store_ID,
            String Mname,
            String Description,
            String Image
    ) {
        String OutPut = String.format(
                        "│----┬------------------------------------------│\n" +
                        "│    │  메뉴ID : %d\n" +
                        "│    │  제휴가게ID : %s \n" +
                        "│    │  메뉴이름 : %s\n" +
                        "│    │  메뉴설명 : %s\n" +
                        "│    │  이미지 : %s\n" +
                        "│----┴------------------------------------------│\n"

                , Menu_ID, Store_ID, Mname, Description, Image);
        System.out.print(OutPut);
    }

    public void affiliatesEnd(){
        System.out.println(
                "└-----------------------------------------------┘");
    }

    public void orderStart(){
        System.out.println(
                "┌---------------------------------------------------------------┐\n" +
                "│       \t\t\t\t 주문 하기 페이지 \t\t\t\t            │\n"+
                "│---------------------------------------------------------------│");
    }

    public void orderEnd(){
        System.out.println("│       \t\t\t\t 주문 접수 완료 ! \t\t\t            │" );
        System.out.println("└---------------------------------------------------------------┘\n");
    }

    public void myOrderStart(){
        System.out.print(
                "┌-----------------------------------------------┐\n" +
                "│       \t\t 내 주문 내역 \t\t            │\n");
    }

    public void myOrder(
            int Order_ID,
            int User_ID,
            int Store_ID,
            String Payment,
            String State,
            String Order_Date
    ) {
        String OutPut = String.format(
                "│----┬------------------------------------------│\n" +
                "│    │  주문ID : %d\n" +
                "│    │  유저ID : %d\n" +
                "│    │  가게ID : %d\n" +
                "│    │  결제방식 : %s\n" +
                "│    │  주문상태 : %s\n" +
                "│    │  주문날짜 : %s\n" +
                "│----┴------------------------------------------│\n"
                , Order_ID, User_ID, Store_ID, Payment, State, Order_Date);
        System.out.print(OutPut);
    }

    public void myOrderEnd(){
        System.out.println(
                "└-----------------------------------------------┘");
    }

    public void review(){
        System.out.println(
                        "┌----┬------------------------------------------┐\n" +
                        "│ NO │\t\t\t\t\t기능\t\t\t            │\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 1  │  리뷰 작성\t\t\t\t\t\t\t\t\t│\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 2  │  리뷰 수정\t\t\t\t\t\t\t\t\t│\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 3  │  리뷰 삭제\t\t\t\t\t\t\t\t\t│\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 4  │  내가 쓴 리뷰 조회\t\t\t\t\t\t\t│\n" +
                        "│----┼------------------------------------------│\n" +
                        "│ 5  │  선택 가게 리뷰 조회\t\t\t\t\t\t│\n" +
                        "└----┴------------------------------------------┘");
    }

    public void reviewStart(int number){
        switch (number){
            case 1: // 리뷰 작성
                System.out.println("┌---------------------------------------------------------------┐");
                System.out.println("│       \t\t\t\t 리뷰 작성 페이지 \t\t\t\t            │");
                System.out.println("│---------------------------------------------------------------│");
                break;
            case 2: // 리뷰 수정
                System.out.println("┌---------------------------------------------------------------┐");
                System.out.println("│       \t\t\t\t 리뷰 수정 페이지 \t\t\t\t            │");
                System.out.println("│---------------------------------------------------------------│");
                break;
            case 3: // 리뷰 삭제
                System.out.println("┌---------------------------------------------------------------┐");
                System.out.println("│       \t\t\t\t 리뷰 삭제 페이지 \t\t\t\t            │");
                System.out.println("│---------------------------------------------------------------│");
                break;
            case 4: // 내가 쓴 리뷰 조회
                System.out.print(
                        "┌---------------------------------------------------------------┐\n" +
                        "│       \t\t\t\t 내가 쓴 리뷰 조회 \t\t\t            │\n");
                break;
            case 5: // 선택 가게 리뷰 조회
                System.out.print(
                        "┌---------------------------------------------------------------┐\n" +
                        "│       \t\t\t\t 선택 가게 리뷰 조회 \t\t\t            │\n");
                break;
        }
    }

    public void reviewData(
            int number,
            int Review_ID,
            int User_ID,
            int Store_ID,
            int Star_Rating,
            String Comments,
            String Created_At
    ) {
        switch (number){
            case 1: // 리뷰 작성
                System.out.println(
                        "│       \t\t\t\t 리뷰 작성 완료 ! \t\t\t            │");
                break;
            case 2: // 리뷰 수정
                System.out.println(
                        "│       \t\t\t\t 리뷰 수정 완료 ! \t\t\t            │");
                break;
            case 3: // 리뷰 삭제
                System.out.println(
                        "│       \t\t\t\t 리뷰 삭제 완료 ! \t\t\t            │");
                break;
            case 4: // 내가 쓴 리뷰 조회
                break;
            case 5: // 선택 가게 리뷰 조회
                break;
        }
    }

    public void reviewEnd(){
        System.out.println("└---------------------------------------------------------------┘\n");
    }
}