package Client.Model;

import Client.Controller.AbstractTableController;
import Client.RemoteManager;
import Client.ViewsManager;
import javafx.scene.control.*;
import org.json.simple.JSONObject;

public class Report extends AbstractRowModel {
    private Label customer = new Label();
    private Label project = new Label();
    private Label task = new Label();
    private TextField quantity = new TextField();
    private TextArea note = new TextArea();

    private Button setCustomer = new Button();
    private Button setProject = new Button();
    private Button setTask = new Button();

    public Report(AbstractTableController tableController) throws Exception {
        this(tableController, new JSONObject());
    }

    public Report(AbstractTableController tableController, JSONObject data) throws Exception {
        super(RemoteManager.getInstance().getReportService(), tableController, data);

        note.setMaxHeight(50);
        refreshModel();
        events();
    }

    @Override
    protected void initializeButtons() {
        super.initializeButtons();

        setCustomer = new Button();
        defineImageButton(setCustomer, "Client/Resources/Images/customer.png");
        setCustomer.setOnAction(actionEvent -> setCustomer());
        setCustomer.setTooltip(new Tooltip("Set Customer"));
        
        setProject = new Button();
        defineImageButton(setProject, "Client/Resources/Images/add-project.png");
        setProject.setOnAction(actionEvent -> setProject());
        setProject.setTooltip(new Tooltip("Set Project"));
        
        setTask = new Button();
        defineImageButton(setTask, "Client/Resources/Images/task.png");
        setTask.setOnAction(actionEvent -> setTask());
        setTask.setTooltip(new Tooltip("Set Task"));

        if (data.size() == 0) {
            setCustomer.setVisible(false);
            setProject.setVisible(false);
            setTask.setVisible(false);
        }
        getButtons().getChildren().addAll(setCustomer, setProject, setTask);
    }

    public void events() {
        quantity.textProperty().addListener((obs, oldText, newText) -> {
            needToSave();
            data.put("quantity", newText);
        });
        note.textProperty().addListener((obs, oldText, newText) -> {
            needToSave();
            data.put("note", newText);
        });
    }

    @Override
    public void refreshModel() {
        if (null != data.get("customer")) {
            setCustomer((String) ((JSONObject) data.get("customer")).get("code"));
        }
        if (null != data.get("project")) {
            setProject((String) ((JSONObject) data.get("project")).get("code"));
        }
        if (null != data.get("task")) {
            setTask((String) ((JSONObject) data.get("task")).get("description"));
        }
        setQuantity((String) data.get("quantity"));
        setNote((String) data.get("note"));
    }

    public void setCustomer() {
        ViewsManager.getInstance().renderSetCustomer(this);
    }

    public void setProject() {
        ViewsManager.getInstance().renderSetProject(this);
    }

    public void setTask() {
        ViewsManager.getInstance().renderSetTasks(this);
    }

    public int getId() {
        return Integer.parseInt((String) data.get("id"));
    }

    public Label getCustomer() {
        return customer;
    }

    public void setCustomer(Label customer) {
        this.customer = customer;
    }

    public void setCustomer(String customer) {
        this.customer.setText(customer);
    }

    public Label getProject() {
        return project;
    }

    public void setProject(Label project) {
        this.project = project;
    }

    public void setProject(String project) {
        this.project.setText(project);
    }

    public Label getTask() {
        return task;
    }

    public void setTask(Label task) {
        this.task = task;
    }

    public void setTask(String task) {
        this.task.setText(task);
    }

    public TextField getQuantity() {
        return quantity;
    }

    public void setQuantity(TextField quantity) {
        this.quantity = quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity.setText(quantity);
    }

    public TextArea getNote() {
        return note;
    }

    public void setNote(TextArea note) {
        this.note = note;
    }

    public void setNote(String note) {
        this.note.setText(note);
    }
}
