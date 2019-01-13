package Client.Model;

import Client.Controller.AbstractTableController;
import Client.RemoteManager;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import org.json.simple.JSONObject;

public class SetRole extends AbstractRowModel {
    private Label name = new Label();
    private Label description = new Label();
    private RadioButton set = new RadioButton();

    public SetRole(AbstractTableController tableController, JSONObject data, boolean oldset, ToggleGroup group) throws Exception {
        super(RemoteManager.getInstance().getRoleService(), tableController, data);

        refreshModel();
        set.setToggleGroup(group);
        set.setSelected(oldset);
    }

    @Override
    protected void initializeButtons() {}

    @Override
    protected void refreshModel() {
        setName((String) data.get("name"));
        setDescription((String) data.get("description"));
    }

    public int getId() {
        return Integer.parseInt((String) data.get("id"));
    }

    public Label getName() {
        return name;
    }

    public void setName(Label name) {
        this.name = name;
    }

    public void setName(String name) {
        if (name != null) this.name.setText(name);
    }

    public String getStringName() {
        return name.getText();
    }

    public Label getDescription() {
        return description;
    }

    public void setDescription(Label description) {
        if (description != null) this.description = description;
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public String getStringDescription() {
        return description.getText();
    }

    public RadioButton getSet() {
        return set;
    }

    public void setSet(RadioButton set) {
        this.set = set;
    }
}
