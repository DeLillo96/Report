package Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class used to handle the application's main tabPane
 */
public class Home {
    private Stage stage;
    @FXML
    private TabPane mainTabPane;
    @FXML
    private Tab homeTab;
    @FXML
    private Tab usersTab;
    @FXML
    private Tab rolesTab;
    @FXML
    private Tab projectsTab;
    @FXML
    private Tab employeeTab;

    /**
     * For each tab in the tabPane, this methos loads an FXML file resource and set it to a specific tab
     * @throws IOException
     */
    @FXML
    public void initialize() throws IOException {
        AnchorPane homePane = FXMLLoader.load(getClass().getResource("../Views/calendar.fxml"));
        homeTab.setContent(homePane);

        AnchorPane rolesPane = FXMLLoader.load(getClass().getResource("../Views/roles.fxml"));
        rolesTab.setContent(rolesPane);

        AnchorPane projectsPane = FXMLLoader.load(getClass().getResource("../Views/projects.fxml"));
        projectsTab.setContent(projectsPane);

        AnchorPane employeePane = FXMLLoader.load(getClass().getResource("../Views/employee.fxml"));
        employeeTab.setContent(employeePane);
    }
}
