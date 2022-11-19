package Model;

import DTO.ReviewDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReviewModel {
    private Oracle database;
    private UsersModel usersModel;

    private StoreModel storeModel;
    private final String insertTemplate = "INSERT INTO REVIEW VALUES ( ?, ?, ?, ?, ?, TO_DATE(?, 'yyyy-mm-dd'))";

    public ReviewModel(Oracle database, UsersModel usersModel, StoreModel storeModel) {
        this.database = database;
        this.usersModel = usersModel;
        this.storeModel = storeModel;
    }

    public void insertReview(ReviewDto dto) throws SQLException {

        String sql = insertTemplate;
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setInt(1, dto.getReviewId());
        ps.setInt(2, dto.getUserId());
        ps.setInt(3, dto.getStoreId());
        ps.setInt(4, dto.getStarRating());
        ps.setString(5, dto.getComments());
        ps.setString(6, dto.getCreatedAt());
        ResultSet rs = ps.executeQuery();
    }

    public void deleteReview(int reviewId) throws SQLException {
        String sql = "DELETE FROM REVIEW WHERE REVIEW_ID = ?";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setInt(1, reviewId);
        ResultSet rs = ps.executeQuery();
    }

    public ArrayList<ReviewDto> getReviewByUser(int userId) throws SQLException {
        String sql = "SELECT * FROM REVIEW WHERE USER_ID = ?";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        ArrayList<ReviewDto> reviewDtos = new ArrayList<>();
        while (rs.next()){
            String storeName = storeModel.getStoreNameById(rs.getInt("STORE_ID"));
            reviewDtos.add(new ReviewDto(
                    rs.getInt("REVIEW_ID"),
                    rs.getInt("USER_ID"),
                    rs.getInt("STORE_ID"),
                    storeName,
                    rs.getInt("STAR_RATING"),
                    rs.getString("COMMENTS"),
                    rs.getString("CREATED_AT")
            ));
        }
        return reviewDtos;
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


    private ArrayList<ReviewDto> toList(ResultSet rs) throws SQLException {
        ArrayList<ReviewDto> reviewDtos = new ArrayList<>();
        while (rs.next()){
            reviewDtos.add(new ReviewDto(
                    rs.getInt("REVIEW_ID"),
                    rs.getInt("USER_ID"),
                    rs.getInt("STORE_ID"),
                    rs.getInt("STAR_RATING"),
                    rs.getString("COMMENTS"),
                    rs.getString("CREATED_AT")
            ));
        }
        return reviewDtos;
    }

    public ArrayList<ReviewDto> selectMyReview() {
        int userId = usersModel.getUsers().userId;

        return null;
    }

    public void selectUpdateReview(ReviewDto dto) throws SQLException {
        String sql = "SELECT * FROM REVIEW WHERE ORDER_ID = ?";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setInt(1, dto.getReviewId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()){

        }

    }

    public void updateReview(ReviewDto dto) throws SQLException {
        String sql = "UPDATE REVIEW SET COMMENTS = ?, STAR_RATING = ?, CREATED_AT = ? WHERE REVIEW_ID = ?";
        PreparedStatement ps = this.database.getPreparedStatement(sql);
        ps.setString(1, dto.getComments());
        ps.setInt(2, dto.getStarRating());
        ps.setString(3, dto.getCreatedAt());
        ps.setInt(4, dto.getReviewId());
        ResultSet rs = ps.executeQuery();

    }
}