import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MealTrackerPane {
    private String loggedInUsername;

    // Constructor to accept logged-in username
    public MealTrackerPane(String loggedInUsername) {
        this.loggedInUsername = loggedInUsername;
    }

    public static VBox getMealTrackerPane(String loggedInUsername) {
        VBox layout = new VBox(10);

        // Create Labels for meal name, date, and calories
        Label meallabel = new Label("Enter Meal Name:");
        TextField mealNameField = new TextField();

        Label mealdate = new Label("Enter Meal Date (YYYY-MM-DD):");
        TextField dateField = new TextField();

        Label M_calories = new Label("Enter Calories:");
        TextField caloriesField = new TextField();

        Button logMealButton = new Button("Log Meal");

        // Button action to log meal
        logMealButton.setOnAction(e -> {
            String mealName = mealNameField.getText();
            String date = dateField.getText();
            double calories = 0;

            try {
                calories = Double.parseDouble(caloriesField.getText());
            } catch (NumberFormatException ex) {
                System.out.println("Invalid calorie value");
                return; // Exit if calories are invalid
            }

            // Create the meal object
            Meal meal = new Meal(mealName, date, calories);

            // Call the DAO method to save the meal to the database
            MealDAO.addMealToHistory(loggedInUsername, meal);

            System.out.println("Meal logged: " + mealName);
            clearFields(mealNameField, dateField, caloriesField); // Clear fields after logging
        });

        // Add Labels and TextFields to the layout
        layout.getChildren().addAll(meallabel, mealNameField, mealdate, dateField, M_calories, caloriesField, logMealButton);

        return layout;
    }

    // Method to clear fields after logging
    private static void clearFields(TextField mealNameField, TextField dateField, TextField caloriesField) {
        mealNameField.clear();
        dateField.clear();
        caloriesField.clear();
    }
}

