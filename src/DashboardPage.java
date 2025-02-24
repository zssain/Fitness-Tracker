import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;


public class DashboardPage {

    public static void show(Stage primaryStage, String username) {
        VBox dashboardPane = new VBox(10);
        dashboardPane.setPadding(new Insets(10));

        Label welcomeLabel = new Label("Welcome, " + username + "!");
        Label infoLabel = new Label("This is your personalized account.");

        dashboardPane.getChildren().addAll(welcomeLabel, infoLabel);

        Scene scene = new Scene(dashboardPane, 400, 200);
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

