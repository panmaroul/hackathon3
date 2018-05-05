
import other.ApplicationPaths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(Paths.get(ApplicationPaths.RESOURCES_VIEWS,"main.fxml").toFile().toURI().toURL());
        Parent root = loader.load();
        stage.setTitle("VideoText Extractor");
        Scene scene = new Scene(root, 680, 390);
        //Scene scene = new Scene(new GoogleMap());
        scene.getStylesheets().add(Paths.get(ApplicationPaths.RESOURCES_CSS,"main.css").toFile().toURI().toURL().toExternalForm());
        stage.setScene(scene);
        stage.getIcons().add(new Image(Paths.get(ApplicationPaths.RESOURCES_ICONS,"app.png").toFile().toURI().toURL().toString()));
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(e -> System.exit(0));
    }

    public static void main(String[] args) throws IOException {
        ApplicationPaths.setApplicationPaths();
        launch(args);
    }
}