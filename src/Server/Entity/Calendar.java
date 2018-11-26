package Server.Entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@FilterDefs({
        @FilterDef(name = "id", parameters = @ParamDef(name = "id", type = "integer")),
        @FilterDef(name = "date", parameters = @ParamDef(name = "date", type = "date")),
        @FilterDef(name = "dateFrom", parameters = {@ParamDef(name = "dateFrom", type = "date")}),
        @FilterDef(name = "dateTo", parameters = {@ParamDef(name = "dateTo", type = "date")}),
})
@Filters({
        @Filter(name = "id", condition = "id = :id"),
        @Filter(name = "date", condition = "date = :date"),
        @Filter(name = "dateFrom", condition = "date >= :dateFrom"),
        @Filter(name = "dateTo", condition = "date <= :dateTo")
})
@Table(name = "Calendar")
public class Calendar extends AbstractEntity {
    @Id
    @GeneratedValue
    @PrimaryKeyJoinColumn
    private Integer id;

    @Column(nullable = false, unique = true)
    @Temporal(TemporalType.DATE)
    private Date date;

    public Calendar() {
        this.date = new Date();
    }

    public Calendar(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(date);

        cal.set(java.util.Calendar.HOUR_OF_DAY, 0);
        cal.set(java.util.Calendar.MINUTE, 0);
        cal.set(java.util.Calendar.SECOND, 0);
        cal.set(java.util.Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public void setDate(Date date) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(date);
        cal.set(java.util.Calendar.HOUR_OF_DAY, 0);
        cal.set(java.util.Calendar.MINUTE, 0);
        cal.set(java.util.Calendar.SECOND, 0);
        cal.set(java.util.Calendar.MILLISECOND, 0);
        this.date=cal.getTime();
    }
}
