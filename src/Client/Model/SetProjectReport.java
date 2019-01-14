package Client.Model;

import Client.Controller.AbstractTableController;
import Client.Controller.SetProjectReportController;
import Client.RemoteManager;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import org.json.simple.JSONObject;

import java.time.LocalDate;

public class SetProjectReport extends AbstractRowModel {
    private Label code = new Label();
    private Label description = new Label();
    private DatePicker expire = new DatePicker();

    private RadioButton set = new RadioButton();

    public SetProjectReport(AbstractTableController tableController, JSONObject data, boolean oldset, ToggleGroup group) throws Exception {
        super(RemoteManager.getInstance().getProjectService(), tableController, data);

        refreshModel();
        set.setToggleGroup(group);
        set.setSelected(oldset);

        expire.setDisable(true);
        events();
    }

    @Override
    protected void initializeButtons() {}

    /**
     * Method used to set listeners and related events to trigger
     */
    public void events() {
        set.setOnAction(event -> ((SetProjectReportController) controller).setted(data));
    }

    @Override
    protected void refreshModel() {
        setCode((String) data.get("code"));
        setExpire((CharSequence) data.get("expire"));
        setDescription((String) data.get("description"));
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

    public DatePicker getExpire() {
        return expire;
    }

    public void setExpire(CharSequence birthDate) {
        if (birthDate != null) this.expire.setValue(LocalDate.parse(birthDate));
    }

    public Label getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public RadioButton getSet() {
        return set;
    }

    public void setSet(RadioButton set) {
        this.set = set;
    }
}
