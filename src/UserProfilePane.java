import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class UserProfilePane {

    public static GridPane getUserProfilePane(String username) {
        GridPane userProfilePane = new GridPane();
        userProfilePane.setHgap(10);
        userProfilePane.setVgap(10);

        // Check if the profile exists
        if (UserDAO.profileExists(username)) {
            UserProfile profile = UserDAO.getProfile(username);

            // Display the saved profile details
            Label nameLabel = new Label("Name: " + profile.getName());
            Label ageLabel = new Label("Age: " + profile.getAge());
            Label weightLabel = new Label("Weight (kg): " + profile.getWeight());
            Label heightLabel = new Label("Height (cm): " + profile.getHeight());
            Label goalLabel = new Label("Goal: " + profile.getGoal());

            userProfilePane.add(nameLabel, 0, 0);
            userProfilePane.add(ageLabel, 0, 1);
            userProfilePane.add(weightLabel, 0, 2);
            userProfilePane.add(heightLabel, 0, 3);
            userProfilePane.add(goalLabel, 0, 4);
        } else {
            // Input fields for new profile
            Label nameLabel = new Label("Name:");
            TextField nameField = new TextField();

            Label ageLabel = new Label("Age:");
            TextField ageField = new TextField();

            Label weightLabel = new Label("Weight (kg):");
            TextField weightField = new TextField();

            Label heightLabel = new Label("Height (cm):");
            TextField heightField = new TextField();

            Label goalLabel = new Label("Goal:");
            TextField goalField = new TextField();

            Button saveButton = new Button("Save Profile");
            saveButton.setOnAction(e -> {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                double weight = Double.parseDouble(weightField.getText());
                double height = Double.parseDouble(heightField.getText());
                String goal = goalField.getText();

                UserDAO.saveProfile(username, name, age, weight, height, goal);

                // Refresh the pane
                userProfilePane.getChildren().clear();
                userProfilePane.add(new Label("Profile saved! Reload the page."), 0, 0);
            });

            userProfilePane.add(nameLabel, 0, 0);
            userProfilePane.add(nameField, 1, 0);
            userProfilePane.add(ageLabel, 0, 1);
            userProfilePane.add(ageField, 1, 1);
            userProfilePane.add(weightLabel, 0, 2);
            userProfilePane.add(weightField, 1, 2);
            userProfilePane.add(heightLabel, 0, 3);
            userProfilePane.add(heightField, 1, 3);
            userProfilePane.add(goalLabel, 0, 4);
            userProfilePane.add(goalField, 1, 4);
            userProfilePane.add(saveButton, 1, 5);
        }

        return userProfilePane;
    }
}
