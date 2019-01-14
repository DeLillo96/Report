package Client.Model;

import Client.Controller.AbstractTableController;
import Client.Controller.SetCustomerReportController;
import Client.RemoteManager;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import org.json.simple.JSONObject;

public class SetCustomerReport extends AbstractRowModel {
    private Label code = new Label();

    private RadioButton set = new RadioButton();

    public SetCustomerReport(AbstractTableController tableController, JSONObject data, boolean oldset, ToggleGroup group) throws Exception {
        super(RemoteManager.getInstance().getCustomerService(), tableController, data);

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
        set.setOnAction(event -> ((SetCustomerReportController) controller).setted(data));
    }

    @Override
    protected void refreshModel() {
        setCode((String) data.get("code"));
    }

    public int getId() {
        return Integer.parseInt((String) data.get("id"));
    }

    public Label getCode() {
        return code;
    }

    public void setCode(Label code) {
        this.code = code;
    }

    public void setCode(String code) {
        if (code != null) this.code.setText(code);
    }

    public String getStringCode() {
        return code.getText();
    }

    public RadioButton getSet() {
        return set;
    }

    public void setSet(RadioButton set) {
        this.set = set;
    }
}
