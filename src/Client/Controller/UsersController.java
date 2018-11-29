package Client.Controller;

import Client.Model.Users;
import Client.RemoteManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class UsersController extends AbstractTableController {
    @FXML
    private TextField usernameTextField;

    public UsersController() throws Exception {
        super(RemoteManager.getInstance().getUserService());
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
        addIntoTable(new Users(this));
    }

    @Override
    protected JSONObject takeFilters() {
        JSONObject filters = new JSONObject();

        filters.put("username", usernameTextField.getText());

        return filters;
    }

    @Override
    protected ArrayList parseIntoRows(JSONObject data) throws Exception {
        ArrayList<Users> list = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            JSONObject child = (JSONObject) data.get(i);

            list.add(new Users(this, child));
        }

        return list;
    }

}
