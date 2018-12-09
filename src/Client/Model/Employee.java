package Client.Model;

import Client.Controller.AbstractTableController;
import Client.RemoteManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import org.json.simple.JSONObject;

public class Employee extends AbstractRowModel {
    private TextField name = new TextField();
    private TextField lastName = new TextField();
    private TextField telephone = new TextField();
    private TextField fiscalCode = new TextField();
    private Label role = new Label();
    private Button roles = new Button();
    private Button user = new Button();

    public Employee(AbstractTableController tableController) throws Exception {
        this(tableController, new JSONObject());
    }

    public Employee(AbstractTableController tableController, JSONObject data) throws Exception {
        super(RemoteManager.getInstance().getEmployeeService(), tableController, data);

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
        
        user = new Button();
        defineImageButton(user, "Client/Resources/Images/user.png");
        user.setOnAction(actionEvent -> user());
        user.setTooltip(new Tooltip("Modify User"));

        if (data.size() == 0) {
            roles.setVisible(false);
            user.setVisible(false);
        }
        getButtons().getChildren().addAll(roles, user);
    }

    public void events() {
        name.textProperty().addListener((obs, oldText, newText) -> {
            needToSave();
            data.put("name", newText);
        });
        lastName.textProperty().addListener((obs, oldText, newText) -> {
            needToSave();
            data.put("lastName", newText);
        });
        telephone.textProperty().addListener((obs, oldText, newText) -> {
            needToSave();
            data.put("telephone", newText);
        });
        fiscalCode.textProperty().addListener((obs, oldText, newText) -> {
            needToSave();
            data.put("fiscalCode", newText);
        });
    }

    @Override
    public void refreshModel() {
        setName((String) data.get("name"));
        setLastName((String) data.get("lastName"));
        setTelephone((String) data.get("telephone"));
        setFiscalCode((String) data.get("fiscalCode"));
        if (null != data.get("role")) {
            setRole((String) ((JSONObject) data.get("role")).get("name"));
        }
    }

    public void roles() {
        //ViewsManager.getInstance().renderSetRoles(this);
    }

    public void user() {
        //ViewsManager.getInstance().renderModifyUser(this);
    }

    public int getId() {
        return Integer.parseInt((String) data.get("id"));
    }

    public TextField getName() {
        return name;
    }

    public void setName(TextField name) {
        this.name = name;
    }

    public void setName(String name) {
        if (name != null) this.name.setText(name);
    }

    public String getStringName() {
        return name.getText();
    }
    
    public TextField getLastName() {
        return lastName;
    }

    public void setLastName(TextField lastName) {
        this.lastName = lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != null) this.lastName.setText(lastName);
    }

    public String getStringLastName() {
        return lastName.getText();
    }
    
    public TextField getTelephone() {
        return telephone;
    }

    public void setTelephone(TextField telephone) {
        this.telephone = telephone;
    }

    public void setTelephone(String telephone) {
        if (telephone != null) this.telephone.setText(telephone);
    }

    public String getStringTelephone() {
        return telephone.getText();
    }
    
    public TextField getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(TextField fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        if (fiscalCode != null) this.fiscalCode.setText(fiscalCode);
    }

    public String getStringFiscalCode() {
        return fiscalCode.getText();
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
