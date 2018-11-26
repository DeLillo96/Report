package Client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This singleton class handles all the renders of the application (popups and notifies)
 */
public class ControllerManager {
    private static ControllerManager instance;
    private static Stage stage;

    /**
     * Singleton method
     * @return instance of ControllerManager
     */
    public static ControllerManager getInstance() {
        if (instance == null) instance = new ControllerManager();
        return instance;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        ControllerManager.stage = stage;
    }

    public Scene getScene() {
        return getStage().getScene();
    }

    /**
     * Renders login view
     * @throws IOException
     */
    public void renderLogin() throws IOException {
        renderFXML("Views/login.fxml");
    }

    /**
     * Renders home view
     * @throws IOException
     */
    public void renderHome() throws IOException {
        renderFXML("Views/home.fxml");
    }

    /**
     * Method used to render a specific FXML resource
     * @param location (location of the FXML required resource)
     * @throws IOException
     */
    private void renderFXML(String location) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(location));
        getStage().setScene(new Scene(root));
    }
}
