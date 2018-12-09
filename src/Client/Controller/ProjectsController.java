package Client.Controller;

import Client.Model.Project;
import Client.ViewsManager;
import Client.RemoteManager;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProjectsController extends AbstractTableController {
    @FXML
    private TextField codeTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private DatePicker expirePickerFrom;
    @FXML
    private DatePicker expirePickerTo;

    public ProjectsController() throws Exception {
        super(RemoteManager.getInstance().getProjectService());
    }

    @FXML
    public void initialize() {
        filter();
    }

    /**
     * Generates a new Project model in order to add it to the tableView as a new row
     * @throws Exception
     */
    @FXML
    public void add() throws Exception {
        addIntoTable(new Project(this));
    }

    @Override
    protected JSONObject takeFilters() {
        JSONObject filters = new JSONObject();

        filters.put("name", codeTextField.getText());
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (expirePickerFrom.getValue() != null) {
                Date fromDate = dateFormat.parse(expirePickerFrom.getValue().toString());
                filters.put("expireFrom", fromDate);
            }
            if (expirePickerTo.getValue() != null) {
                Date toDate = dateFormat.parse(expirePickerTo.getValue().toString());
                filters.put("expireTo", toDate);
            }
        } catch (ParseException e) {
            ViewsManager.getInstance().notifyError("Invalid dates (Required format: yyyy-MM-dd");
        }
        filters.put("description", descriptionTextField.getText());

        return filters;
    }

    @Override
    protected ArrayList parseIntoRows(JSONObject data) throws Exception {
        ArrayList<Project> list = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            JSONObject project = (JSONObject) data.get(i);

            list.add(new Project(this, project));
        }

        return list;
    }
}
