import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserProfileDAO {

    public static void saveUserProfile(String name, int age, double weight, double height, String goal) {
        String sql = "INSERT INTO user_profiles (name, age, weight, height, goal) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setDouble(3, weight);
            pstmt.setDouble(4, height);
            pstmt.setString(5, goal);
            pstmt.executeUpdate();
            System.out.println("User profile saved successfully!");
        } catch (SQLException e) {
            System.out.println("Error saving user profile: " + e.getMessage());
        }
    }

    public static void fetchAllProfiles() {
        String sql = "SELECT * FROM user_profiles";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching profiles: " + e.getMessage());
        }
    }
}
