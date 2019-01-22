package Client.Model;

import Client.Controller.AbstractTableController;
import Client.RemoteManager;
import Client.ViewsManager;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import org.json.simple.JSONObject;

public class Customer extends AbstractRowModel {
    private TextField code = new TextField();

    private Button contact = new Button();
    
    public Customer(AbstractTableController tableController) throws Exception {
        this(tableController, new JSONObject());
    }

    public Customer(AbstractTableController tableController, JSONObject data) throws Exception {
        super(RemoteManager.getInstance().getCustomerService(), tableController, data);

        refreshModel();
        events();
    }

    @Override
    protected void initializeButtons() {
        super.initializeButtons();

        contact = new Button();
        defineImageButton(contact, "Client/Resources/Images/contact.png");
        contact.setOnAction(actionEvent -> contact());
        contact.setTooltip(new Tooltip("Set Contact"));

        if (data.size() == 0) {
            contact.setVisible(false);
        }
        getButtons().getChildren().addAll(contact);
    }

    public void events() {
        code.textProperty().addListener((obs, oldText, newText) -> {
            needToSave();
            data.put("code", newText);
        });
    }

    @Override
    protected void refreshModel() {
        setCode((String) data.get("code"));
    }

    public void contact() {
        ViewsManager.getInstance().renderContactPopup(this);
    }

    public int getId() {
        return Integer.parseInt((String) data.get("id"));
    }

    public TextField getCode() {
        return code;
    }

    public void setCode(TextField name) {
        this.code = name;
    }

    public void setCode(String name) {
        if (name != null) this.code.setText(name);
    }

    public String getStringCode() {
        return code.getText();
    }
}
