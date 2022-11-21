package DTO;

public class MenuDto {

    public int menuId;
    public int storeId;
    public String menuName;
    public String description;
    public String image;
    public int price;

    public MenuDto(int menuId, int storeId, String menuName, String description, String image, int price) {
        this.menuId = menuId;
        this.storeId = storeId;
        this.menuName = menuName;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public MenuDto() {

    }

    public int getMenuId() {
        return menuId;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }
}
