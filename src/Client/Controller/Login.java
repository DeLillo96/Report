package Client.Controller;

import Client.ViewsManager;
import Client.ViewsManager;
import Client.RemoteManager;
import Shared.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

public class Login {
    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    /**
     * This method parses two strings from username and password fields, gets
     */
    @FXML
    public void loginButtonAction() {
        try {
            String username = usernameField.getText();
            String password = passwordField.getText();

            RemoteManager remoteManager = RemoteManager.getInstance();
            UserService userService = remoteManager.getUserService();
            JSONObject response = userService.login(username, password);

            if ((boolean) response.get("success")) {
                ViewsManager.getInstance().renderHome();
            } else throw new Exception(response.get("messages").toString());
        } catch (Exception e) {
            ViewsManager.getInstance().notifyError(e.getMessage());
            e.printStackTrace();
        }
    }
}
