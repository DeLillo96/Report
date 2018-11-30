package Client;

import Client.Controller.SetRolesController;
import Client.Model.Users;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

/**
 * This singleton class handles all the renders of the application (popups and notifies)
 */
public class ControllerManager {
    private static ControllerManager instance;
    private static Stage stage;
    private static Stack<Parent> popup = new Stack<>();

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

    public void renderSetRoles(Users user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/setRoles.fxml"));
            addPopup(loader.load());

            SetRolesController setRolesController = loader.getController();
            setRolesController.setUsers(user);

            setRolesController.filter();
        } catch (IOException e) {
            NotifyManager.getInstance().notifyError(e.getMessage());
        }
    }
    /**
     * Add a popup to a generic stack
     * @param parent (Base of the client's view)
     */
    public void addPopup(Parent parent) {
        int offset = popup.size() * 10;
        AnchorPane.setTopAnchor(parent, 20d + offset);
        AnchorPane.setBottomAnchor(parent, 20d + offset);
        AnchorPane.setLeftAnchor(parent, 10d + offset);
        AnchorPane.setRightAnchor(parent, 10d + offset);

        Pane mainRoot = (Pane) getScene().getRoot();
        mainRoot.getChildren().add(popup.push(parent));
    }

    /**
     * Remove the last inserted popup from the popup stack
     */
    public void removePopup() {
        Pane mainRoot = (Pane) getScene().getRoot();
        mainRoot.getChildren().remove(popup.pop());
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
