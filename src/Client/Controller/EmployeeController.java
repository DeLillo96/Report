package Client.Controller;

import Client.Model.Employee;
import Client.RemoteManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class EmployeeController extends AbstractTableController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField telephoneTextField;
    @FXML
    private TextField fiscalCodeTextField;

    public EmployeeController() throws Exception {
        super(RemoteManager.getInstance().getEmployeeService());
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
        addIntoTable(new Employee(this));
    }

    @Override
    protected JSONObject takeFilters() {
        JSONObject filters = new JSONObject();

        filters.put("name", nameTextField.getText());
        filters.put("lastName", lastNameTextField.getText());
        filters.put("telephone", telephoneTextField.getText());
        filters.put("fiscalCode", fiscalCodeTextField.getText());

        return filters;
    }

    @Override
    protected ArrayList parseIntoRows(JSONObject data) throws Exception {
        ArrayList<Employee> list = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            JSONObject employee = (JSONObject) data.get(i);

            list.add(new Employee(this, employee));
        }

        return list;
    }

}
