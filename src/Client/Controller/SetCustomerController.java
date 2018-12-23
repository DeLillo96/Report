package Client.Controller;

import Client.Model.Project;
import Client.Model.SetCustomer;
import Client.RemoteManager;
import Client.ViewsManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class SetCustomerController extends AbstractTableController {
    @FXML
    private TextField codeTextField;
    @FXML
    private Label projectLabel;

    private Project project;

    final ToggleGroup group = new ToggleGroup();

    public SetCustomerController() throws Exception {
        super(RemoteManager.getInstance().getCustomerService());
    }

    @FXML
    public void save() {
        try {
            for (int i = 0; i < tableView.getItems().size(); i++) {
                SetCustomer item = (SetCustomer) tableView.getItems().get(i);
                if(item.getSet().isSelected()) {
                    project.newCustomer(item.getData());
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

        filters.put("code", codeTextField.getText());

        return filters;
    }

    @Override
    protected ArrayList parseIntoRows(JSONObject data) throws Exception {
        ArrayList<SetCustomer> list = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            JSONObject role = (JSONObject) data.get(i);
            boolean oldset = project.getCustomerId().equals(Integer.parseInt((String) role.get("id")));

            list.add(new SetCustomer(this, role, oldset, group));
        }

        return list;
    }

    @FXML
    public void remove() {
        project.refreshModel();
        ViewsManager.getInstance().removePopup();
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
