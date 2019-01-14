package Client;

import Client.Controller.*;
import Client.Model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.Stack;

/**
 * This singleton class handles all the renders of the application (popups and notifies)
 */
public class ViewsManager {
    private static ViewsManager instance;
    private static Stage stage;
    private static Stack<Parent> popup = new Stack<>();
    private static Parent notify;

    /**
     * Singleton method
     * @return instance of ViewsManager
     */
    public static ViewsManager getInstance() {
        if (instance == null) instance = new ViewsManager();
        return instance;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        ViewsManager.stage = stage;
    }

    public Scene getScene() {
        return getStage().getScene();
    }

    /**
     * Renders login view
     * @throws IOException
     */
    public void renderLogin() throws IOException {
        renderFXML("Views/login.fxml");
    }

    /**
     * Renders home view
     * @throws IOException
     */
    public void renderHome() throws IOException {
        renderFXML("Views/home.fxml");
    }

    public void renderSetRoles(Employee employee) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/setRoles.fxml"));
            addPopup(loader.load());

            SetRolesController setRolesController = loader.getController();
            setRolesController.setEmployee(employee);

            setRolesController.filter();
        } catch (IOException e) {
            notifyError(e.getMessage());
        }
    }

    public void renderSetTasks(Project project) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/setTasks.fxml"));
            addPopup(loader.load());

            SetTasksController setTasksController = loader.getController();
            setTasksController.setProject(project);

            setTasksController.filter();
        } catch (IOException e) {
            notifyError(e.getMessage());
        }
    }

    public void renderSetTasks(Report report) {
    }

    public void renderModifyUser(Employee employee, JSONObject user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/modifyUser.fxml"));
            addPopup(loader.load());

            ModifyUserController modifyUserController = loader.getController();
            modifyUserController.setUser(user);
        } catch (IOException e) {
            notifyError(e.getMessage());
        }
    }

    public void renderSetCustomer(Project project) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/setCustomer.fxml"));
            addPopup(loader.load());

            SetCustomerController setCustomerController = loader.getController();
            setCustomerController.setProject(project);

            setCustomerController.filter();
        } catch (IOException e) {
            notifyError(e.getMessage());
        }
    }

    public void renderSetCustomer(Report report) {
    }

    public void renderReportPopup(CalendarDay calendarDay) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/report.fxml"));
            addPopup(loader.load());

            ReportController reportController = loader.getController();
            reportController.setCalendar(calendarDay);
        } catch (IOException e) {
            notifyError(e.getMessage());
        }
    }

    public void renderSetProject(Report report) {
    }

    public void renderReportForm(Report report) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/reportForm.fxml"));
            addPopup(loader.load());

            ReportFormController reportFormController = loader.getController();
            reportFormController.setReport(report);
            reportFormController.render();
        } catch (IOException e) {
            notifyError(e.getMessage());
        }
    }

    /**
     * Add a popup to a generic stack
     * @param parent (Base of the client's view)
     */
    public void addPopup(Parent parent) {
        int offset = popup.size() * 10;
        AnchorPane.setTopAnchor(parent, 20d + offset);
        AnchorPane.setBottomAnchor(parent, 20d + offset);
        AnchorPane.setLeftAnchor(parent, 10d + offset);
        AnchorPane.setRightAnchor(parent, 10d + offset);

        Pane mainRoot = (Pane) getScene().getRoot();
        mainRoot.getChildren().add(popup.push(parent));
    }

    /**
     * Remove the last inserted popup from the popup stack
     */
    public void removePopup() {
        Pane mainRoot = (Pane) getScene().getRoot();
        mainRoot.getChildren().remove(popup.pop());
    }

    /**
     * Renders error notification
     * @param errorMessage (Error message shown in the notification)
     */
    public void notifyError(String errorMessage) {
        if (notify != null) removeNotify();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/error.fxml"));
            notify = loader.load();

            addNotify(loader, errorMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Renders success notification
     * @param successMessage (Success message shown in the notification)
     */
    public void notifySuccess(String successMessage) {
        if (notify != null) removeNotify();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/success.fxml"));
            notify = loader.load();

            addNotify(loader, successMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Renders a generic notification
     * @param loader (Current loader)
     * @param message (Message shown in the notification)
     */
    protected void addNotify(FXMLLoader loader, String message) {
        AbstractNotifyController controller = loader.getController();
        controller.setMessage(message);

        Pane mainRoot = (Pane) getScene().getRoot();
        mainRoot.getChildren().add(notify);
    }

    /**
     * Remove last notification
     */
    public void removeNotify() {
        Pane mainRoot = (Pane) getScene().getRoot();
        mainRoot.getChildren().remove(notify);
        notify = null;
    }

    /**
     * Method used to render a specific FXML resource
     * @param location (location of the FXML required resource)
     * @throws IOException
     */
    private void renderFXML(String location) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(location));
        getStage().setScene(new Scene(root));
    }
}
