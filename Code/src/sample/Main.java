package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setResizable(false);
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
        loadWorkplace(primaryStage);

    }

    private void loadWorkplace(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("workplace.fxml"));
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Hello World");
        primaryStage.setFullScreen(true);
//        primaryStage.setScene(new Scene(root));
        primaryStage.setScene(new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(),
                Screen.getPrimary().getVisualBounds().getHeight()));
        primaryStage.show();
    }
}
