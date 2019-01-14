package Client.Controller;

import Client.Model.SetTasksReport;
import Client.RemoteManager;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class SetTasksReportController extends AbstractTableController {
    private TextField descriptionTextField;
    private ChoiceBox typeChoiceBox;

    final ToggleGroup group = new ToggleGroup();

    private ReportFormController reportFormController;
    private Integer projectId = 0;

    public SetTasksReportController() throws Exception {
        super(null);
    }

    @Override
    protected JSONObject takeFilters() {
        JSONObject filters = new JSONObject();

        filters.put("description", descriptionTextField.getText());

        return filters;
    }

    @Override
    protected ArrayList search(JSONObject filters) throws Exception {
        JSONObject response;
        if(projectId != 0) {
            response = RemoteManager.getInstance().getProjectTaskService().leftRead(projectId);
        } else {
            response = RemoteManager.getInstance().getTaskService().read(filters);
        }

        if ((boolean) response.get("success")) {

            JSONObject data = (JSONObject) response.get("data");
            return parseIntoRows(data);

        } else throw new Exception("Read from server error");
    }

    @Override
    protected ArrayList parseIntoRows(JSONObject data) throws Exception {
        ArrayList<SetTasksReport> list = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            JSONObject task = (JSONObject) data.get(i);

            SetTasksReport setTasksReport = new SetTasksReport(this, task, false, group);

            list.add(setTasksReport);
        }

        return list;
    }


    public void setDescriptionTextField(TextField descriptionTextField) {
        this.descriptionTextField = descriptionTextField;
    }

    public JSONObject getSettedModel(){
        return new JSONObject();
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public void setFormController(ReportFormController reportFormController) {
        this.reportFormController = reportFormController;
    }

    public void setted(JSONObject data) {
        reportFormController.setTask(data);
    }
}
