package Client.Model;

import Client.Controller.AbstractTableController;
import Client.RemoteManager;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

public class Role extends AbstractRowModel {
    private TextField name = new TextField();
    private TextField description = new TextField();

    public Role(AbstractTableController tableController) throws Exception {
        this(tableController, new JSONObject());
    }

    public Role(AbstractTableController tableController, JSONObject data) throws Exception {
        super(RemoteManager.getInstance().getRoleService(), tableController, data);

        refreshModel();
        events();
    }

    public void events() {
        name.textProperty().addListener((obs, oldText, newText) -> {
            needToSave();
            data.put("name", newText);
        });
        description.textProperty().addListener((obs, oldText, newText) -> {
            needToSave();
            data.put("description", newText);
        });
    }

    @Override
    protected void refreshModel() {
        setName((String) data.get("name"));
        setDescription((String) data.get("description"));
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
}
