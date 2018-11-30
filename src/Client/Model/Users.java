package Client.Model;

import Client.Controller.AbstractTableController;
import Client.ControllerManager;
import Client.RemoteManager;
import javafx.scene.control.*;
import org.json.simple.JSONObject;

public class Users extends AbstractRowModel {
    private TextField username = new TextField();
    private PasswordField password = new PasswordField();
    private Label role = new Label();
    private Button roles = new Button();

    public Users(AbstractTableController tableController) throws Exception {
        this(tableController, new JSONObject());
    }

    public Users(AbstractTableController tableController, JSONObject data) throws Exception {
        super(RemoteManager.getInstance().getUserService(), tableController, data);

        refreshModel();
        events();
    }

    @Override
    protected void initializeButtons() {
        super.initializeButtons();

        roles = new Button();
        defineImageButton(roles, "Client/Resources/Images/role.png");
        roles.setOnAction(actionEvent -> roles());
        roles.setTooltip(new Tooltip("Set Role"));

        if (data.size() == 0) {
            roles.setVisible(false);
        }
        getButtons().getChildren().addAll(roles);
    }

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
    public void refreshModel() {
        setUsername((String) data.get("username"));
        setPassword((String) data.get("password"));
        if (null != data.get("role")) {
            setRole((String) ((JSONObject) data.get("role")).get("name"));
        }
    }

    public void roles() {
        ControllerManager.getInstance().renderSetRoles(this);
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
    
    public Label getRole() {
        return role;
    }

    public void setRole(Label role) {
        if (role != null) this.role = role;
    }

    public void setRole(String role) {
        this.role.setText(role);
    }

    public String getStringRole() {
        return role.getText();
    }

    public Integer getRoleId() {
        if (null == data.get("role")) return 0;
        return Integer.parseInt((String) ((JSONObject) data.get("role")).get("id"));
    }

    public void newRole(JSONObject newRole) {
        data.put("role", newRole);
        save();
    }
}
