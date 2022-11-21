package View;

import DTO.MenuDto;

public class MenuView {
    public void menu(MenuDto menuDto) {
        System.out.println("메뉴 이름: " + menuDto.getMenuName());
        System.out.println("메뉴 설명: " + menuDto.getDescription());
        System.out.println("가격: " + menuDto.getPrice() + '\n');
    }
}
