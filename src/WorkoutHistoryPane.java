import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.List;

public class WorkoutHistoryPane {

    public static VBox getWorkoutHistoryPane(String username) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        // Label for the page title
        Label titleLabel = new Label("Workout History");

        // Create a ListView to display workout history
        ListView<String> workoutHistoryList = new ListView<>();

        // Fetch workout history from database and add to the ListView
        List<Workout> workouts = WorkoutDAO.getWorkoutHistory(username);
        if (workouts == null || workouts.isEmpty()) {
            workoutHistoryList.getItems().add("No workouts logged yet.");
        } else {
            for (Workout workout : workouts) {
                workoutHistoryList.getItems().add(workout.getWorkoutName() + " | " + workout.getDate() + " | " + workout.getDuration() + " minutes");
            }
        }

        // Total Duration Button and Label
        Button totalDurationButton = new Button("Calculate Total Duration");
        Label totalDurationLabel = new Label("Total Duration: 0.0 minutes");

        totalDurationButton.setOnAction(e -> {
            double totalDuration = workouts.stream().mapToDouble(Workout::getDuration).sum();
            totalDurationLabel.setText("Total Duration: " + totalDuration + " minutes");
        });

        // Add components to the layout
        layout.getChildren().addAll(titleLabel, workoutHistoryList, totalDurationButton, totalDurationLabel);

        return layout; // Return VBox (Node)
    }
}
