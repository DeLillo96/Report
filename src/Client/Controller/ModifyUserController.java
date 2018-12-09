package Client.Controller;

import Client.RemoteManager;
import Client.ViewsManager;
import Shared.BaseService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

public class ModifyUserController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField1;
    @FXML
    private TextField passwordTextField2;
    @FXML
    private TextField emailTextField;

    private JSONObject user;

    private BaseService service;

    public ModifyUserController() throws Exception {
        service = RemoteManager.getInstance().getUserService();
    }

    public void events() {
        usernameTextField.textProperty().addListener((obs, oldText, newText) -> {
            user.put("username", newText);
        });
        passwordTextField1.textProperty().addListener((obs, oldText, newText) -> {
            user.put("password", newText);
        });
        emailTextField.textProperty().addListener((obs, oldText, newText) -> {
            user.put("email", newText);
        });
    }

    @FXML
    public void remove() {
        ViewsManager.getInstance().removePopup();
    }

    public void setUser(JSONObject user) {
        this.user = user;
        usernameTextField.setText((String) user.get("username"));
        passwordTextField1.setText((String) user.get("password"));
        passwordTextField2.setText((String) user.get("password"));
        emailTextField.setText((String) user.get("email"));
        events();
    }

    public void save() {
        try {
            if(!passwordTextField1.getText().equals(passwordTextField2.getText()))
                throw new Exception("Passwords must match!");
            JSONObject result = service.save(user);
            if ((boolean) result.get("success")) {
                ViewsManager.getInstance().notifySuccess(result.get("messages").toString());
                remove();
            } else {
                ViewsManager.getInstance().notifyError(result.get("messages").toString());
            }
        } catch (Exception e) {
            ViewsManager.getInstance().notifyError(e.getMessage());
        }
    }
}
