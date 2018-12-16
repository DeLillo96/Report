package Client.Controller;

import Client.Model.AbstractRowModel;
import Client.Model.Project;
import Client.Model.SetTasks;
import Client.RemoteManager;
import Client.ViewsManager;
import Shared.RelationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class SetTasksController extends AbstractTableController {
    @FXML
    private TextField descriptionTextField;
    @FXML
    private ChoiceBox typeChoiceBox;
    @FXML
    private Label projectLabel;

    private Project project;

    public SetTasksController() throws Exception {
        super(RemoteManager.getInstance().getTaskService());
    }

    @FXML
    @Override
    public void filter() {
        try {
            ArrayList<SetTasks> list = search(takeFilters());

            RelationService projectTaskService = RemoteManager.getInstance().getProjectTaskService();
            JSONObject result = projectTaskService.leftRead(project.getId());

            ArrayList<SetTasks> projectTask = parseIntoRows((JSONObject) result.get("data"));
            for (SetTasks task : list) {
                projectTask.forEach(o -> {
                    if (o.getId().equals(task.getId())) {
                        task.getSelect().setSelected(true);
                    }
                });
            }

            ObservableList<AbstractRowModel> items = FXCollections.observableArrayList(list);
            tableView.setItems(items);
            newRowFlag = true;
        } catch (Exception e) {
            e.printStackTrace();
            ViewsManager.getInstance().notifyError(e.getMessage());
        }
    }
    
    @FXML
    public void add() throws Exception {
        SetTasks task = new SetTasks(this);
        task.setProject(project);
        addIntoTable(task);
    }

    @Override
    protected JSONObject takeFilters() {
        JSONObject filters = new JSONObject();

        filters.put("description", descriptionTextField.getText());

        return filters;
    }

    @Override
    protected ArrayList parseIntoRows(JSONObject data) throws Exception {
        ArrayList<SetTasks> list = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            JSONObject task = (JSONObject) data.get(i);

            SetTasks setTasks = new SetTasks(this, task);
            setTasks.setProject(project);

            list.add(setTasks);
        }

        return list;
    }

    @FXML
    public void remove() {
        ViewsManager.getInstance().removePopup();
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
