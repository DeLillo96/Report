package Client.Controller;

import Client.Model.Contact;
import Client.Model.Customer;
import Client.RemoteManager;
import Client.ViewsManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class ContactController extends AbstractTableController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField telephoneTextField;
    @FXML
    private TextField fiscalCodeTextField;

    private Customer customer;

    public ContactController() throws Exception {
        super(RemoteManager.getInstance().getCustomerContactService());
    }

    /**
     * Generates a new Child model in order to add it to the tableView as a new row
     * @throws Exception
     */
    @FXML
    public void add() throws Exception {
        JSONObject contact = new JSONObject();
        contact.put("customer", customer.getData());
        addIntoTable(new Contact(this, contact));
    }

    @Override
    protected JSONObject takeFilters() {
        JSONObject filters = new JSONObject();

        filters.put("name", nameTextField.getText());
        filters.put("lastName", lastNameTextField.getText());
        filters.put("telephone", telephoneTextField.getText());
        filters.put("fiscalCode", fiscalCodeTextField.getText());
        filters.put("customer", customer.getId());

        return filters;
    }

    @Override
    protected ArrayList parseIntoRows(JSONObject data) throws Exception {
        ArrayList<Contact> list = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            JSONObject contact = (JSONObject) data.get(i);

            list.add(new Contact(this, contact));
        }

        return list;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @FXML
    public void remove() {
        ViewsManager.getInstance().removePopup();
    }
}
