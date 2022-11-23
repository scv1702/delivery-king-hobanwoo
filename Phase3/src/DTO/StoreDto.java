package DTO;

import java.util.ArrayList;

public class StoreDto {
    private int storeId;
    private String address;
    private String foodCategory;
    private String storeName;
    private String phoneNumber;
    private String description;
    private int deliveryFee;
    private String image;
    private int businessHour;
    private ArrayList<MenuDto> menuList;

    public StoreDto(String storeName, String address, String foodCategory, String phoneNumber, String description, int deliveryFee, int businessHour) {
        this.storeName = storeName;
        this.address = address;
        this.foodCategory = foodCategory;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.deliveryFee = deliveryFee;
        this.businessHour = businessHour;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getBusinessHour() {
        return businessHour;
    }

    public void setBusinessHour(int businessHour) {
        this.businessHour = businessHour;
    }

    public int getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(int deliveryFee) {
        this.deliveryFee = deliveryFee;
    }
}
