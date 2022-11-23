package Model;

import DTO.ReviewDto;
import DTO.StoreDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReviewModel {
    private final Oracle database;
    private final StoreModel storeModel;

    public ReviewModel(Oracle database, StoreModel storeModel) {
        this.database = database;
        this.storeModel = storeModel;
    }

    public void insert(ReviewDto review) throws SQLException {
        String sql = "INSERT INTO REVIEW VALUES ( ?, ?, ?, ?, ?, TO_DATE(?, 'yyyy-mm-dd'))";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setInt(1, review.getReviewId());
        ps.setInt(2, review.getUserId());
        ps.setInt(3, review.getStoreId());
        ps.setInt(4, review.getStarRating());
        ps.setString(5, review.getComments());
        ps.setString(6, review.getCreatedAt());
        ps.executeQuery();
    }

    public void deleteById(int reviewId) throws SQLException {
        String sql = "DELETE FROM REVIEW WHERE REVIEW_ID = ?";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setInt(1, reviewId);
        ps.executeQuery();
    }

    public ArrayList<ReviewDto> getReviewByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM REVIEW WHERE USER_ID = ?";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        ArrayList<ReviewDto> reviewList = new ArrayList<>();
        while (rs.next()) {
            String storeName = this.storeModel.getStoreNameById(rs.getInt("STORE_ID"));
            reviewList.add(new ReviewDto(
                    rs.getInt("REVIEW_ID"),
                    rs.getInt("USER_ID"),
                    rs.getInt("STORE_ID"),
                    storeName,
                    rs.getInt("STAR_RATING"),
                    rs.getString("COMMENTS"),
                    rs.getString("CREATED_AT")
            ));
        }
        return reviewList;
    }

    public int getMaxReviewId() throws SQLException {
        String sql = "SELECT MAX(REVIEW_ID) FROM REVIEW"; // 이렇게 하면 동시성 문제 있다는데 다른 방법도 알아봐야할듯?
        Statement stmt = this.database.getStatement();
        ResultSet rs = stmt.executeQuery(sql);
        int reviewId = -1;
        while (rs.next()) {
            reviewId = rs.getInt(1);
        }
        return reviewId;
    }

    public void update(ReviewDto review) throws SQLException {
        String sql = "UPDATE REVIEW SET COMMENTS = ?, STAR_RATING = ?, CREATED_AT = ? WHERE REVIEW_ID = ?";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setString(1, review.getComments());
        ps.setInt(2, review.getStarRating());
        ps.setString(3, review.getCreatedAt());
        ps.setInt(4, review.getReviewId());
        ps.executeQuery();
    }

    public ArrayList<ReviewDto> getReviewByStoreName(String storeName) throws SQLException {
        int storeId = this.storeModel.getStoreByName(storeName).getStoreId();
        String sql = "SELECT * FROM REVIEW R WHERE EXISTS (SELECT * FROM STORE S WHERE S.STORE_NAME = ? AND S.STORE_ID = R.STORE_ID)";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setInt(1, storeId);
        ResultSet rs = ps.executeQuery();
        ArrayList<ReviewDto> reviewList = new ArrayList<>();
        while (rs.next()) {
            reviewList.add(new ReviewDto(
                    rs.getInt("REVIEW_ID"),
                    rs.getInt("USER_ID"),
                    rs.getInt("STORE_ID"),
                    storeName,
                    rs.getInt("STAR_RATING"),
                    rs.getString("COMMENTS"),
                    rs.getString("CREATED_AT")
            ));
        }
        return reviewList;
    }

    public ReviewDto getReviewById(int reviewId) throws SQLException {
        String sql = "SELECT * FROM REVIEW WHERE REVIEW_ID = ?";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setInt(1, reviewId);
        ResultSet rs = ps.executeQuery();
        ReviewDto review = new ReviewDto();
        if (rs.next()) {
            String storeName = this.storeModel.getStoreNameById(rs.getInt("STORE_ID"));
            review.setReviewId(rs.getInt("REVIEW_ID"));
            review.setUserId(rs.getInt("USER_ID"));
            review.setStoreId(rs.getInt("STORE_ID"));
            review.setStoreName(storeName);
            review.setStarRating(rs.getInt("STAR_RATING"));
            review.setComments(rs.getString("COMMENTS"));
            review.setCreatedAt(rs.getString("CREATED_AT"));
        }
        return review;
    }
}