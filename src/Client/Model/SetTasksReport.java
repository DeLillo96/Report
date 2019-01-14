package Client.Model;

import Client.Controller.AbstractTableController;
import Client.Controller.SetTasksReportController;
import Client.RemoteManager;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import org.json.simple.JSONObject;

public class SetTasksReport extends AbstractRowModel {
    private Label description = new Label();
    private Label type = new Label();

    private RadioButton set = new RadioButton();

    public SetTasksReport(AbstractTableController tableController, JSONObject data, boolean oldset, ToggleGroup group) throws Exception {
        super(RemoteManager.getInstance().getTaskService(), tableController, data);

        refreshModel();
        set.setToggleGroup(group);
        set.setSelected(oldset);

        events();
    }

    @Override
    protected void initializeButtons() {}

    /**
     * Method used to set listeners and related events to trigger
     */
    public void events() {
        set.setOnAction(event -> ((SetTasksReportController) controller).setted(data));
    }

    @Override
    protected void refreshModel() {
        setDescription((String) data.get("description"));
        setType((String) data.get("type"));
    }

    public int getId() {
        return Integer.parseInt((String) data.get("id"));
    }

    public Label getDescription() {
        return description;
    }

    public void setDescription(Label description) {
        this.description = description;
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public String getStringDescription() {
        return description.getText();
    }

    public Label getType() {
        return type;
    }

    public void setType(Label type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type.setText(type);
    }

    public String getStringType() {
        return (String) type.getText();
    }

    public RadioButton getSet() {
        return set;
    }

    public void setSet(RadioButton set) {
        this.set = set;
    }
}
