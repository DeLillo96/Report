package Client.Controller;

import Client.Model.CalendarDay;
import Client.Model.Report;
import Client.RemoteManager;
import Client.ViewsManager;
import javafx.fxml.FXML;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class ReportController extends AbstractTableController {

    CalendarDay calendar;

    public ReportController() throws Exception {
        super(RemoteManager.getInstance().getReportService());
    }

    @FXML
    public void initialize() {
        filter();
    }

    /**
     * Generates a new Child model in order to add it to the tableView as a new row
     * @throws Exception
     */
    @FXML
    public void add() throws Exception {
        JSONObject data = new JSONObject();

        data.put("calendar", calendar.getData());

        Report report = new Report(this, data);
        addIntoTable(report);
        ViewsManager.getInstance().renderReportForm(report);
    }

    @Override
    protected JSONObject takeFilters() {
        JSONObject filters = new JSONObject();

        if (calendar != null) {
            Integer calendarId = calendar.getCalendarId();
            if (calendarId > 0) {
                filters.put("calendar", calendarId);
            }
        }

        return filters;
    }

    @Override
    protected ArrayList parseIntoRows(JSONObject data) throws Exception {
        ArrayList<Report> list = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            JSONObject report = (JSONObject) data.get(i);

            list.add(new Report(this, report));
        }

        return list;
    }

    public void setCalendar(CalendarDay calendar) {
        this.calendar = calendar;
    }

    @FXML
    public void remove() {
        ViewsManager.getInstance().removePopup();
    }
}
