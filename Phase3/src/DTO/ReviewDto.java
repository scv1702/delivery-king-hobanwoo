package DTO;

public class ReviewDto {
    private int reviewId;
    private int userId;
    private int storeId;
    private int starRating;
    private String comments;
    private String storeName;
    private String createdAt;

    public ReviewDto(int reviewId, int userId, int storeId, int starRating,
                     String comments, String createdAt) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.storeId = storeId;
        this.starRating = starRating;
        this.comments = comments;
        this.createdAt = createdAt;
    }

    public ReviewDto(int reviewId, int userId, int storeId, String storeName, int starRating,
                     String comments, String createdAt) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.starRating = starRating;
        this.comments = comments;
        this.createdAt = createdAt;
    }

    public ReviewDto(int starRating, String comments) {
        this.starRating = starRating;
        this.comments = comments;
    }

    public ReviewDto(int reviewId, int starRating, String comments, String createdAt) {
        this.reviewId = reviewId;
        this.starRating = starRating;
        this.comments = comments;
        this.createdAt = createdAt;
    }

    public ReviewDto() {

    }

    public int getReviewId() {
        return reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public int getStoreId() {
        return storeId;
    }

    public int getStarRating() {
        return starRating;
    }

    public String getComments() {
        return comments;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
