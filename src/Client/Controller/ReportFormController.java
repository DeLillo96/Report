package Client.Controller;

import Client.Model.CalendarDay;
import Client.RemoteManager;
import Client.ViewsManager;
import Shared.BaseService;
import javafx.fxml.FXML;

public class ReportFormController {
    private CalendarDay calendar;

    private BaseService service;

    public ReportFormController() throws Exception {
        service = RemoteManager.getInstance().getCalendarService();
    }

    public void events() {
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
    }
}
