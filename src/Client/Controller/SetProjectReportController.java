package Client.Controller;

import Client.Model.SetProjectReport;
import Client.RemoteManager;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class SetProjectReportController extends AbstractTableController {

    private TextField projectCodeTextField;
    private TextField projectDescriptionTextField;

    final ToggleGroup group = new ToggleGroup();

    private ReportFormController reportFormController;
    private Integer customerId = 0;

    public SetProjectReportController() throws Exception {
        super(RemoteManager.getInstance().getProjectService());
    }

    @Override
    protected JSONObject takeFilters() {
        JSONObject filters = new JSONObject();

        filters.put("code", projectCodeTextField.getText());
        filters.put("description", projectDescriptionTextField.getText());

        if(customerId != 0) {
            filters.put("customer", customerId);
        }

        return filters;
    }

    @Override
    protected ArrayList parseIntoRows(JSONObject data) throws Exception {
        ArrayList<SetProjectReport> list = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            JSONObject project = (JSONObject) data.get(i);

            list.add(new SetProjectReport(this, project, false, group));
        }

        return list;
    }

    public void setProjectCodeTextField(TextField projectCodeTextField) {
        this.projectCodeTextField = projectCodeTextField;
    }

    public void setProjectDescriptionTextField(TextField projectDescriptionTextField) {
        this.projectDescriptionTextField = projectDescriptionTextField;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setFormController(ReportFormController reportFormController) {
        this.reportFormController = reportFormController;
    }

    public void setted(JSONObject data) {
        reportFormController.taskFilter(Integer.parseInt((String) data.get("id")));
        reportFormController.setProject(data);
    }
}
