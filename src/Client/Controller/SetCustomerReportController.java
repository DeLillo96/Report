package Client.Controller;

import Client.Model.SetCustomerReport;
import Client.RemoteManager;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class SetCustomerReportController extends AbstractTableController {

    private TextField customerCodeTextField;

    final ToggleGroup group = new ToggleGroup();

    private ReportFormController reportFormController;

    public SetCustomerReportController() throws Exception {
        super(RemoteManager.getInstance().getCustomerService());
    }

    @Override
    protected JSONObject takeFilters() {
        JSONObject filters = new JSONObject();

        filters.put("code", customerCodeTextField.getText());

        return filters;
    }

    @Override
    protected ArrayList parseIntoRows(JSONObject data) throws Exception {
        ArrayList<SetCustomerReport> list = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            JSONObject customer = (JSONObject) data.get(i);

            list.add(new SetCustomerReport(this, customer, false, group));
        }

        return list;
    }

    public void setCustomerCodeTextField(TextField customerCodeTextField) {
        this.customerCodeTextField = customerCodeTextField;
    }

    public void setFormController(ReportFormController reportFormController) {
        this.reportFormController = reportFormController;
    }

    public void setted(JSONObject data) {
        reportFormController.projectFilter(Integer.parseInt((String) data.get("id")));
        reportFormController.setCustomer(data);
    }
}
