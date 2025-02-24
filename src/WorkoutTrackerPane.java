import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WorkoutTrackerPane {
    private String loggedInUsername;

    // Constructor to accept logged-in username
    public WorkoutTrackerPane(String loggedInUsername) {
        this.loggedInUsername = loggedInUsername;
    }

    public static VBox getWorkoutTrackerPane(String loggedInUsername) {
        VBox layout = new VBox(10);

        // Create Labels for workout name, date, and duration
        Label workoutLabel = new Label("Enter Workout Name:");
        TextField workoutNameField = new TextField();

        Label workoutDate = new Label("Enter Workout Date (YYYY-MM-DD):");
        TextField dateField = new TextField();

        Label workoutDuration = new Label("Enter Duration (in minutes):");
        TextField durationField = new TextField();

        Button logWorkoutButton = new Button("Log Workout");

        // Button action to log workout
        logWorkoutButton.setOnAction(e -> {
            String workoutName = workoutNameField.getText();
            String date = dateField.getText();
            int duration = 0;

            try {
                duration = Integer.parseInt(durationField.getText());
            } catch (NumberFormatException ex) {
                System.out.println("Invalid duration value");
                return; // Exit if duration is invalid
            }

            // Create the workout object
            Workout workout = new Workout(workoutName, date, duration);

            // Call the DAO method to save the workout to the database
            WorkoutDAO.addWorkoutToHistory(loggedInUsername, workout);

            System.out.println("Workout logged: " + workoutName);
            clearFields(workoutNameField, dateField, durationField); // Clear fields after logging
        });

        // Add Labels and TextFields to the layout
        layout.getChildren().addAll(workoutLabel, workoutNameField, workoutDate, dateField, workoutDuration, durationField, logWorkoutButton);

        return layout;
    }

    // Method to clear fields after logging
    private static void clearFields(TextField workoutNameField, TextField dateField, TextField durationField) {
        workoutNameField.clear();
        dateField.clear();
        durationField.clear();
    }
}


