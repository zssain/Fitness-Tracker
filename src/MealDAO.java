import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MealDAO {

    // Add a new meal to the history
    public static void addMealToHistory(String username, Meal meal) {
        String sql = "INSERT INTO meals (username, mealName, date, calories) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, meal.getMealName());
            pstmt.setString(3, meal.getDate());
            pstmt.setDouble(4, meal.getCalories());
            pstmt.executeUpdate();
            System.out.println("Meal logged successfully for " + username);
        } catch (SQLException e) {
            System.out.println("Error logging meal: " + e.getMessage());
        }
    }


    // Retrieve the meal history for a specific user
    public static List<Meal> getMealHistory(String username) {
        List<Meal> meals = new ArrayList<>();
        String sql = "SELECT mealName, date, calories FROM meals WHERE username = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                meals.add(new Meal(rs.getString("mealName"), rs.getString("date"), rs.getDouble("calories")));
            }
            System.out.println("Meal history fetched successfully for " + username);
        } catch (SQLException e) {
            System.out.println("Error fetching meal history: " + e.getMessage());
        }
        return meals;
    }

}
