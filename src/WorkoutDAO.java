import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkoutDAO {

    // Add a new workout to the history
    public static void addWorkoutToHistory(String username, Workout workout) {
        String sql = "INSERT INTO workouts (username, workoutName, date, duration) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, workout.getWorkoutName());
            pstmt.setString(3, workout.getDate());
            pstmt.setDouble(4, workout.getDuration());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error logging workout: " + e.getMessage());
        }
    }

    // Retrieve the workout history for a specific user
    public static List<Workout> getWorkoutHistory(String username) {
        List<Workout> workouts = new ArrayList<>();
        String sql = "SELECT workoutName, date, duration FROM workouts WHERE username = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                workouts.add(new Workout(rs.getString("workoutName"), rs.getString("date"), rs.getDouble("duration")));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching workout history: " + e.getMessage());
        }
        return workouts;
    }
}
