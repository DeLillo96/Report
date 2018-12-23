package Client.Model;

import Client.Controller.AbstractTableController;
import Client.RemoteManager;
import Client.ViewsManager;
import javafx.scene.control.*;
import org.json.simple.JSONObject;

import java.time.LocalDate;

public class Project extends AbstractRowModel {
    private TextField code = new TextField();
    private DatePicker expire = new DatePicker();
    private TextField description = new TextField();
    private Label customer = new Label();
    
    private Button task = new Button();
    private Button setCustomer = new Button();

    public Project(AbstractTableController tableController) throws Exception {
        this(tableController, new JSONObject());
    }

    public Project(AbstractTableController tableController, JSONObject data) throws Exception {
        super(RemoteManager.getInstance().getProjectService(), tableController, data);

        refreshModel();
        events();
    }

    @Override
    protected void initializeButtons() {
        super.initializeButtons();

        task = new Button();
        defineImageButton(task, "Client/Resources/Images/task.png");
        task.setOnAction(actionEvent -> task());
        task.setTooltip(new Tooltip("Set Role"));

        setCustomer = new Button();
        defineImageButton(setCustomer, "Client/Resources/Images/customer.png");
        setCustomer.setOnAction(actionEvent -> setCustomer());
        setCustomer.setTooltip(new Tooltip("Set Customer"));

        if (data.size() == 0) {
            task.setVisible(false);
            setCustomer.setVisible(false);
        }
        getButtons().getChildren().addAll(task, setCustomer);
    }
    
    public void events() {
        code.textProperty().addListener((obs, oldText, newText) -> {
            needToSave();
            data.put("code", newText);
        });
        description.textProperty().addListener((obs, oldText, newText) -> {
            needToSave();
            data.put("description", newText);
        });
        expire.setOnAction(event -> {
            needToSave();
            data.put("expire", expire.getValue().toString());
        });
    }

    @Override
    public void refreshModel() {
        setCode((String) data.get("code"));
        setExpire((CharSequence) data.get("expire"));
        setDescription((String) data.get("description"));
        if (null != data.get("customer")) {
            setCustomer((String) ((JSONObject) data.get("customer")).get("code"));
        }
    }

    public void task() {
        ViewsManager.getInstance().renderSetTasks(this);
    }

    public void setCustomer() {
        ViewsManager.getInstance().renderSetCustomer(this);
    }

    public int getId() {
        return Integer.parseInt((String) data.get("id"));
    }

    public TextField getCode() {
        return code;
    }

    public void setCode(TextField code) {
        this.code = code;
    }

    public void setCode(String code) {
        if (code != null) this.code.setText(code);
    }

    public String getStringCode() {
        return code.getText();
    }

    public DatePicker getExpire() {
        return expire;
    }

    public void setExpire(DatePicker expire) {
        this.expire = expire;
    }

    public void setExpire(CharSequence birthDate) {
        if (birthDate != null) this.expire.setValue(LocalDate.parse(birthDate));
    }

    public TextField getDescription() {
        return description;
    }

    public void setDescription(TextField description) {
        if (description != null) this.description = description;
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public String getStringDescription() {
        return description.getText();
    }

    public Label getCustomer() {
        return customer;
    }

    public void setCustomer(Label customer) {
        if (customer != null) this.customer = customer;
    }

    public void setCustomer(String customer) {
        this.customer.setText(customer);
    }
    
    public Integer getCustomerId() {
        if (null == data.get("customer")) return 0;
        return Integer.parseInt((String) ((JSONObject) data.get("customer")).get("id"));
    }

    public void newCustomer(JSONObject newRole) {
        data.put("customer", newRole);
        save();
    }
}
