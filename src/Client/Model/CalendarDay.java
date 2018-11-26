package Client.Model;

import Client.Controller.CalendarController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.util.Date;

public class CalendarDay {
    private Integer calendarId;
    private Integer day;
    private Date date;
    private VBox container;
    private CalendarController controller;

    /**
     * Generates a new object with a linked container containing all the daily news
     * @param calendarController (Main calendar controller)
     */
    public CalendarDay(CalendarController calendarController) {
        this.setController(calendarController);
        setContainer(new VBox());
        container.setAlignment(Pos.CENTER);
        container.getStyleClass().add("calendar-box");
    }

    /**
     * Method called on calendar refresh, clears cell's style in order to a successive assignment
     */
    public void clearContainer() {
        setDay(0);
        container.getChildren().clear();
        container.getStyleClass().remove("unactive-box");
        container.getStyleClass().remove("today-box");
    }

    /**
     * Method used to set a calendar cell as unused (darker colour and no day label)
     */
    public void setUnusedDay() {
        container.getStyleClass().add("unactive-box");
    }

    /**
     * Method used to sign calendar cell as today cell
     */
    public void setToday() {
        container.getStyleClass().add("today-box");
    }

    public Integer getDay() {
        return day;
    }

    /**
     * Method used to set a day to a calendar cell through a specific label
     * @param day (Day of the current month)
     */
    public void setDay(Integer day) {
        this.container.getChildren().clear();
        this.day = day;
        Label lbl = new Label(day.toString());
        lbl.setPadding(new Insets(5));
        this.container.getChildren().add(lbl);
    }

    public Integer getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(Integer calendarId) {
        this.calendarId = calendarId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStringDate() {
        return controller.dateStringConstructor(day);
    }

    public VBox getContainer() {
        return container;
    }

    public void setContainer(VBox container) {
        this.container = container;
    }

    public CalendarController getController() {
        return controller;
    }

    public void setController(CalendarController controller) {
        this.controller = controller;
    }
}