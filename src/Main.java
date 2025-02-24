import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private String loggedInUsername; // Store logged-in username

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showLoginPage();  // Show the login page first
    }

    public void showLoginPage() {
        // Create LoginPage and pass current Main instance
        LoginPage loginPage = new LoginPage(this);

        // Get the Scene from the LoginPage and set it to the primaryStage
        Scene loginScene = loginPage.getLoginPage();

        // Set the login scene to the primary stage
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login Page");
        primaryStage.show();
    }

    // Method to show the RegisterPage
    public void showRegisterPage() {
        // Create RegisterPage instance and pass current Main instance
        RegisterPage registerPage = new RegisterPage(this);

        // Get the VBox from the RegisterPage and create a Scene from it
        Scene registerScene = new Scene(registerPage.getRegisterPage(), 400, 300);

        // Set the register scene to the primary stage
        primaryStage.setScene(registerScene);
        primaryStage.setTitle("Register Page");
        primaryStage.show();
    }

    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;  // Set the username after successful login
    }

    public void showUserProfilePage() {
        // Create and show the main page with tabs for user profile, meal tracking, and workout tracking
        TabPane tabPane = new TabPane();

        // User Profile Tab
        Tab userTab = new Tab("User Profile", UserProfilePane.getUserProfilePane(loggedInUsername));

        // Meal Tracker Tab
        MealTrackerPane mealTrackerPane = new MealTrackerPane(loggedInUsername);
        Tab mealTab = new Tab("Meal Tracker", mealTrackerPane.getMealTrackerPane(loggedInUsername));

        // Workout Tracker Tab
        Tab workoutTab = new Tab("Workout Tracker", WorkoutTrackerPane.getWorkoutTrackerPane(loggedInUsername));

        // Meal History Tab
        Tab mealHistoryTab = new Tab("Meal History", MealHistoryPane.getMealHistoryPane(loggedInUsername));

        // Workout History Tab
        Tab workoutHistoryTab = new Tab("Workout History", WorkoutHistoryPane.getWorkoutHistoryPane(loggedInUsername));

        // Add tabs to the TabPane
        tabPane.getTabs().addAll(userTab, mealTab, workoutTab, mealHistoryTab, workoutHistoryTab);

        // Set up the main scene and show the primary stage
        Scene mainScene = new Scene(tabPane, 600, 400);
        primaryStage.setTitle("Nutrition and Fitness Tracker");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
