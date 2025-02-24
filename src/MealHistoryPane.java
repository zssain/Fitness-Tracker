import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.List;

public class MealHistoryPane {

    public static VBox getMealHistoryPane(String username) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        // Label for the page title
        Label titleLabel = new Label("Meal History");

        // Create a ListView to display meal history
        ListView<String> mealHistoryList = new ListView<>();

        // Fetch meal history from database and add to the ListView
        List<Meal> meals = MealDAO.getMealHistory(username);  // Ensure this is returning the correct meals
        if (meals == null || meals.isEmpty()) {
            mealHistoryList.getItems().add("No meals logged yet.");
        } else {
            for (Meal meal : meals) {
                mealHistoryList.getItems().add(meal.getMealName() + " | " + meal.getDate() + " | " + meal.getCalories() + " calories");
            }
        }

        // Total Calories Button and Label
        Button totalCaloriesButton = new Button("Calculate Total Calories");
        Label totalCaloriesLabel = new Label("Total Calories: 0");

        totalCaloriesButton.setOnAction(e -> {
            double totalCalories = meals.stream().mapToDouble(Meal::getCalories).sum();
            totalCaloriesLabel.setText("Total Calories: " + totalCalories);
        });

        // Add components to the layout
        layout.getChildren().addAll(titleLabel, mealHistoryList, totalCaloriesButton, totalCaloriesLabel);

        return layout;  // Return VBox (Node)
    }
}
