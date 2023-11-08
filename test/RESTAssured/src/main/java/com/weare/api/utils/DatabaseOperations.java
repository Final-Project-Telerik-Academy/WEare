package com.weare.api.utils;

import java.sql.*;

public class DatabaseOperations {
    private static final String DATABASE_URL = "jdbc:mariadb://windowmaker.myqnapcloud.com:3307/wearedb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Telerik123";

    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver class not found", e);
        }
    }

    public static void removeUserById(String key, int value) {
        if (!userHasPosts(value)) {
            String deleteUser = String.format("DELETE FROM users WHERE %s = ?;", key);
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                 PreparedStatement ps = connection.prepareStatement(deleteUser)) {

                ps.setInt(1, value);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Database error occurred while deleting user", e);
            }
        } else {
            throw new RuntimeException("User cannot be deleted because they have posts.");
        }
    }

    public static void deleteLikesForUserPosts(int userId) {
        String deleteLikes = "DELETE FROM post_likes_table WHERE post_id IN (SELECT post_id FROM posts_table WHERE user_id = ?)";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(deleteLikes)) {

            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while deleting likes for user posts", e);
        }
    }

    public static void deletePostsByUserId(int userId) {
        String deletePosts = "DELETE FROM posts_table WHERE user_id = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(deletePosts)) {

            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while deleting user posts", e);
        }
    }

    public static void deleteLikesForUserComments(int userId) {
        String deleteLikes = "DELETE FROM comment_likes_table WHERE comment_id IN (SELECT comment_id FROM comments_table WHERE user_id = ?)";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(deleteLikes)) {

            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while deleting likes for user comments", e);
        }
    }

    public static void deleteCommentsForUserPosts(int userId) {
        String deleteCommentsSql = "DELETE FROM comments_table WHERE post_id IN (SELECT post_id FROM posts_table WHERE user_id = ?)";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(deleteCommentsSql)) {

            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while deleting comments for user posts", e);
        }
    }


    public static void truncateRequests() {
        executeTruncateUpdate("TRUNCATE TABLE requests;");
    }

    public static void truncateConnections() {
        executeTruncateUpdate("TRUNCATE TABLE connection_users;");
    }

    public static void truncateUsers() {
        executeTruncateUpdate("TRUNCATE TABLE users;");
    }

    private static void executeTruncateUpdate(String sql) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             Statement st = connection.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred during update operation", e);
        }
    }

    private static boolean userHasPosts(int userId) {
        String query = "SELECT COUNT(*) AS post_count FROM posts_table WHERE user_id = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("post_count") > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while checking for user posts", e);
        }
        return false;
    }

    public static boolean checkPostExists(int postId) {
        String query = "SELECT COUNT(*) AS post_count FROM posts_table WHERE post_id = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("post_count") > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred during update operation", e);
        }
        return false;
    }

    public static boolean checkUserExists(int userId) {
        String query = "SELECT COUNT(*) AS user_count FROM users WHERE user_id = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("user_count") > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while checking for user existence", e);
        }
        return false;
    }

    public static int countPostsByUserId(int userId) {
        String query = "SELECT COUNT(*) FROM posts_table WHERE user_id = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while counting user posts", e);
        }
        return 0;
    }

    public static boolean checkFriendRequestExists(int senderId, int receiverId) {
        String query = "SELECT COUNT(*) FROM requests WHERE sender_user_id = ? AND receiver_user_id = ?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, senderId);
            ps.setInt(2, receiverId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking if the friend request exists", e);

        }
        return false;
    }

    public static boolean checkRequestById(int requestId) {
        String query = "SELECT COUNT(*) FROM requests WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, requestId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking the request by ID", e);
        }
        return false;
    }

    public static boolean checkRequestApprovalStatus(int requestId) {
        String query = "SELECT approved FROM requests WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, requestId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("approved");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking the request approval status", e);
        }
        return false;
    }

    public static boolean isPostLikedByUser(int userId, int postId) {
        String query = "SELECT COUNT(*) FROM post_likes_table WHERE user_id = ? AND post_id = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, userId);
            ps.setInt(2, postId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while checking if the post is liked by the user", e);
        }
        return false;
    }
}
