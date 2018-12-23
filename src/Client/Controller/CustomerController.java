package Client.Controller;

import Client.Model.Customer;
import Client.RemoteManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class CustomerController extends AbstractTableController {
    @FXML
    private TextField codeTextField;

    public CustomerController() throws Exception {
        super(RemoteManager.getInstance().getCustomerService());
    }

    @FXML
    public void initialize() {
        filter();
    }

    /**
     * Generates a new Customer model in order to add it to the tableView as a new row
     * @throws Exception
     */
    @FXML
    public void add() throws Exception {
        addIntoTable(new Customer(this));
    }

    @Override
    protected JSONObject takeFilters() {
        JSONObject filters = new JSONObject();

        filters.put("code", codeTextField.getText());

        return filters;
    }

    @Override
    protected ArrayList parseIntoRows(JSONObject data) throws Exception {
        ArrayList<Customer> list = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            JSONObject customer = (JSONObject) data.get(i);

            list.add(new Customer(this, customer));
        }

        return list;
    }
}
