package Client;

import Client.Controller.AbstractNotifyController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class NotifyManager {
    private static NotifyManager instance;
    private static Parent notify;

    /**
     * Singleton method
     * @return instance of ControllerManager
     */
    public static NotifyManager getInstance() {
        if (instance == null) instance = new NotifyManager();
        return instance;
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

        Pane mainRoot = (Pane) ControllerManager.getInstance().getScene().getRoot();
        mainRoot.getChildren().add(notify);
    }

    /**
     * Remove last notification
     */
    public void removeNotify() {
        Pane mainRoot = (Pane) ControllerManager.getInstance().getScene().getRoot();
        mainRoot.getChildren().remove(notify);
        notify = null;
    }
}
