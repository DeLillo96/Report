package Client.Model;

import Client.Controller.AbstractTableController;
import Client.RemoteManager;
import Client.ViewsManager;
import Shared.RelationService;
import javafx.scene.control.*;
import org.json.simple.JSONObject;

public class SetTasks extends AbstractRowModel {
    private TextField description = new TextField();
    private ChoiceBox type = new ChoiceBox();
    private CheckBox select = new CheckBox();
    private Project project;

    public SetTasks(AbstractTableController tableController) throws Exception {
        this(tableController, new JSONObject());
    }

    public SetTasks(AbstractTableController tableController, JSONObject data) throws Exception {
        super(RemoteManager.getInstance().getTaskService(), tableController, data);

        select.setTooltip(new Tooltip("Add/remove task to project"));
        getType().getItems().addAll("Time", "Cost");
        getType().setPrefSize(250, 40);
        getType().setMinSize(250, 40);
        getType().setMaxSize(250, 40);

        refreshModel();
        events();
    }

    @Override
    protected void initializeButtons() {}

    /**
     * Method used to set listeners and related events to trigger
     */
    public void events() {
        description.textProperty().addListener((obs, oldText, newText) -> {
            needToSave();
            data.put("description", newText);
        });
        type.valueProperty().addListener((obs, oldText, newText) -> {
            needToSave();
            data.put("type", newText);
        });
        select.setOnAction(event -> needToSave());
    }

    @Override
    public void save() {
        try {
            JSONObject result = service.save(getData());
            if ((boolean) result.get("success")) {
                setData((JSONObject) ((JSONObject) result.get("data")).get(0));
                refreshModel();
                save.getStyleClass().remove("red-button");
                RelationService projectTaskService = RemoteManager.getInstance().getProjectTaskService();
                result = select.isSelected() ?
                        projectTaskService.assign(getId(), project.getId()) :
                        projectTaskService.deAssign(getId(), project.getId());
                enableButtons();
            }
            notifyResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            ViewsManager.getInstance().notifyError(e.getMessage());
        }
    }

    @Override
    protected void refreshModel() {
        setDescription((String) data.get("description"));
        setType((String) data.get("type"));
    }

    public Integer getId() {
        return Integer.parseInt((String) data.get("id"));
    }

    public TextField getDescription() {
        return description;
    }

    public void setDescription(TextField description) {
        this.description = description;
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public String getStringDescription() {
        return description.getText();
    }

    public ChoiceBox getType() {
        return type;
    }

    public void setType(ChoiceBox type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type.setValue(type);
    }

    public String getStringType() {
        return (String) type.getValue();
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
