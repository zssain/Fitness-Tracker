import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RegisterPage {
    private Main mainApp;

    public RegisterPage(Main mainApp) {
        this.mainApp = mainApp;
    }

    public VBox getRegisterPage() {
        VBox layout = new VBox(10);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (UserDAO.register(username, password)) {
                System.out.println("Registration successful!");
                mainApp.showLoginPage();  // After registration, return to login page
            } else {
                System.out.println("Registration failed");
            }
        });

        // Back Button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> mainApp.showLoginPage());  // Return to the login page

        layout.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, registerButton, backButton);

        return layout;
    }
}
