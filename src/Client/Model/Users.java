package Client.Model;

import Client.Controller.AbstractTableController;
import Client.RemoteManager;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

public class Users extends AbstractRowModel {
    private TextField username = new TextField();
    private PasswordField password = new PasswordField();

    public Users(AbstractTableController tableController) throws Exception {
        this(tableController, new JSONObject());
    }

    public Users(AbstractTableController tableController, JSONObject data) throws Exception {
        super(RemoteManager.getInstance().getUserService(), tableController, data);

        refreshModel();
        events();
    }

    /**
     * Method used to set liadmin   adminsteners and related events to trigger
     */
    public void events() {
        username.textProperty().addListener((obs, oldText, newText) -> {
            needToSave();
            data.put("username", newText);
        });
        password.textProperty().addListener((obs, oldText, newText) -> {
            needToSave();
            data.put("password", newText);
        });
    }

    @Override
    protected void refreshModel() {
        setUsername((String) data.get("username"));
        setPassword((String) data.get("password"));
    }

    public int getId() {
        return Integer.parseInt((String) data.get("id"));
    }

    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public void setUsername(String username) {
        if (username != null) this.username.setText(username);
    }

    public String getStringUsername() {
        return username.getText();
    }

    public TextField getPassword() {
        return password;
    }

    public void setPassword(PasswordField password) {
        if (password != null) this.password = password;
    }

    public void setPassword(String password) {
        this.password.setText(password);
    }

    public String getStringPassword() {
        return password.getText();
    }
}
