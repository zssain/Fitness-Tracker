import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserDAO {

    // Register method: hashes password and saves to database
    public static boolean register(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Hash the password
            String hashedPassword = hashPassword(password);

            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Registration error: " + e.getMessage());
            return false; // Username already exists or other error
        }
    }

    // Authentication method: hashes entered password and compares with stored hash
    public static boolean authenticate(String username, String password) {
        String sql = "SELECT password FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                return storedHash.equals(hashPassword(password)); // Compare hashes
            } else {
                return false; // User not found
            }

        } catch (SQLException e) {
            System.out.println("Authentication error: " + e.getMessage());
            return false;
        }
    }

    // Method to hash password
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // Check if a profile exists for the given username
    public static boolean profileExists(String username) {
        String sql = "SELECT name FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() && rs.getString("name") != null; // Check if 'name' is not null
        } catch (SQLException e) {
            System.out.println("Error checking profile: " + e.getMessage());
            return false;
        }
    }


    // Retrieve the profile details for a user
    public static UserProfile getProfile(String username) {
        String sql = "SELECT name, age, weight, height, goal FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new UserProfile(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("weight"),
                        rs.getDouble("height"),
                        rs.getString("goal")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching profile: " + e.getMessage());
        }
        return null;
    }

    // Save the user's profile
    public static void saveProfile(String username, String name, int age, double weight, double height, String goal) {
        String sql = "UPDATE users SET name = ?, age = ?, weight = ?, height = ?, goal = ? WHERE username = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setDouble(3, weight);
            pstmt.setDouble(4, height);
            pstmt.setString(5, goal);
            pstmt.setString(6, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving profile: " + e.getMessage());
        }
    }
}



