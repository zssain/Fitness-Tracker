import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginPage {

    private Main mainApp;

    // Constructor to accept Main instance reference
    public LoginPage(Main mainApp) {
        this.mainApp = mainApp;
    }

    // This method returns a Scene for the LoginPage
    public Scene getLoginPage() {
        VBox layout = new VBox(10);

        // Labels and text fields for login
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        // Login button
        Button loginButton = new Button("Login");

        // Register button
        Button registerButton = new Button("Register");

        // Login button action
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            // Perform login action (add authentication logic here)
            if (authenticateUser(username, password)) {
                mainApp.setLoggedInUsername(username);
                mainApp.showUserProfilePage();  // Navigate to user profile page
            } else {
                System.out.println("Invalid credentials");
            }
        });

        // Register button action (navigate to RegisterPage)
        registerButton.setOnAction(e -> {
            mainApp.showRegisterPage();  // Navigate to register page
        });

        // Add all components to layout
        layout.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton, registerButton);

        // Return a new Scene with the layout
        return new Scene(layout, 400, 300);
    }

    // User authentication logic: verify against database
    private boolean authenticateUser(String username, String password) {
        // Call the UserDAO to authenticate the user
        return UserDAO.authenticate(username, password);
    }
}


