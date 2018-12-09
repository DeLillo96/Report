package Client.Controller;

import Client.Model.Employee;
import Client.Model.SetRole;
import Client.RemoteManager;
import Client.ViewsManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class SetRolesController extends AbstractTableController {
    @FXML
    private TextField roleTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private Label employeenameLabel;

    private Employee employee;

    final ToggleGroup group = new ToggleGroup();

    public SetRolesController() throws Exception {
        super(RemoteManager.getInstance().getRoleService());
    }

    @FXML
    public void save() {
        try {
            for (int i = 0; i < tableView.getItems().size(); i++) {
                SetRole item = (SetRole) tableView.getItems().get(i);
                if(item.getSet().isSelected()) {
                    employee.newRole(item.getData());
                }
            }
        } catch (Exception e) {
            ViewsManager.getInstance().notifyError(e.getMessage());
        }
        remove();
    }

    @Override
    protected JSONObject takeFilters() {
        JSONObject filters = new JSONObject();

        filters.put("name", roleTextField.getText());
        filters.put("description", descriptionTextField.getText());

        return filters;
    }

    @Override
    protected ArrayList parseIntoRows(JSONObject data) throws Exception {
        ArrayList<SetRole> list = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            JSONObject role = (JSONObject) data.get(i);
            boolean oldset = employee.getRoleId().equals(Integer.parseInt((String) role.get("id")));

            list.add(new SetRole(this, role, oldset, group));
        }

        return list;
    }

    @FXML
    public void remove() {
        employee.refreshModel();
        ViewsManager.getInstance().removePopup();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
