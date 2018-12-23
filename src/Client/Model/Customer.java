package Client.Model;

import Client.Controller.AbstractTableController;
import Client.RemoteManager;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

public class Customer extends AbstractRowModel {
    private TextField code = new TextField();

    public Customer(AbstractTableController tableController) throws Exception {
        this(tableController, new JSONObject());
    }

    public Customer(AbstractTableController tableController, JSONObject data) throws Exception {
        super(RemoteManager.getInstance().getCustomerService(), tableController, data);

        refreshModel();
        events();
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
