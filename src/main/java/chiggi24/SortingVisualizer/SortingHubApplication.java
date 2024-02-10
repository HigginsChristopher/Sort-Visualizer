package chiggi24.SortingVisualizer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SortingHubApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SortingHubApplication.class.getResource("/SortingVisualizer-view.fxml"));
        // Setting conditions for application dimensions
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        // Setting icon for application
        stage.getIcons().add(
                new Image(SortingHubApplication.class.getResourceAsStream("/SortingIcon.png")));
        // Setting title name of application to "Sorting Visualizer"
        stage.setTitle("Sorting Visualizer");
        stage.setScene(scene);
        stage.show();
    }
}