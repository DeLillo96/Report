package Client.Controller;

import Client.Model.Role;
import Client.RemoteManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class RolesController extends AbstractTableController {
    @FXML
    private TextField roleTextField;
    @FXML
    private TextField descriptionTextField;

    public RolesController() throws Exception {
        super(RemoteManager.getInstance().getRoleService());
    }

    @FXML
    public void initialize() {
        filter();
    }

    /**
     * Generates a new Child model in order to add it to the tableView as a new row
     * @throws Exception
     */
    @FXML
    public void add() throws Exception {
        addIntoTable(new Role(this));
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
        ArrayList<Role> list = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            JSONObject role = (JSONObject) data.get(i);

            list.add(new Role(this, role));
        }

        return list;
    }

}
