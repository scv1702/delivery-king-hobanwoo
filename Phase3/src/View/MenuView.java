package View;

public class MenuView {
    public void affiliatesStart() {
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

    public void affiliatesEnd() {
        System.out.println(
                "└-----------------------------------------------┘");
    }
}
