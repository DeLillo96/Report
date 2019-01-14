package Client.Controller;

import Client.Model.AbstractRowModel;
import Client.Model.CalendarDay;
import Client.Model.Report;
import Client.ViewsManager;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.json.simple.JSONObject;

public class ReportFormController {
    @FXML
    private TextField customerCodeTextField;
    @FXML
    private TableView<AbstractRowModel> customerTableView;

    @FXML
    private TextField projectCodeTextField;
    @FXML
    private TextField projectDescriptionTextField;
    @FXML
    private TableView<AbstractRowModel> projectTableView;

    @FXML
    private TextField taskDescriptionTextField;
    @FXML
    private TableView<AbstractRowModel> taskTableView;

    private CalendarDay calendar;
    private Report report;

    private SetCustomerReportController customerController;
    private SetProjectReportController projectController;
    private SetTasksReportController taskController;

    final ToggleGroup group = new ToggleGroup();

    public ReportFormController() throws Exception {
        customerController = new SetCustomerReportController();
        projectController = new SetProjectReportController();
        taskController = new SetTasksReportController();
    }

    public void events() {
    }

    public void render() {
        customerController.setCustomerCodeTextField(customerCodeTextField);
        customerController.setTableView(customerTableView);
        customerController.setFormController(this);
        customerFilter();

        projectController.setProjectCodeTextField(projectCodeTextField);
        projectController.setProjectDescriptionTextField(projectDescriptionTextField);
        projectController.setTableView(projectTableView);
        projectController.setFormController(this);
        //projectFilter();

        taskController.setDescriptionTextField(taskDescriptionTextField);
        taskController.setTableView(taskTableView);
        taskController.setFormController(this);
        //taskFilter();
    }

    @FXML
    public void remove() {
        ViewsManager.getInstance().removePopup();
    }

    public void setCalendar(CalendarDay calendar) {
        this.calendar = calendar;
        events();
    }

    public void save() {
        remove();
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public void customerFilter() {
        customerController.filter();
    }

    public void projectFilter() {
        projectController.filter();
    }

    public void projectFilter(Integer customerId) {
        projectController.setCustomerId(customerId);
        projectFilter();
    }

    public void taskFilter() {
        taskController.filter();
    }

    public void taskFilter(Integer projectId) {
        taskController.setProjectId(projectId);
        taskFilter();
    }

    public void setCustomer(JSONObject customer) {
        report.setCustomer(customer);
    }

    public void setProject(JSONObject project) {
        report.setProject(project);
    }

    public void setTask(JSONObject task) {
        report.setTask(task);
    }
}
