package Server.Entity;

import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@FilterDefs({
        @FilterDef(name = "id", parameters = @ParamDef(name = "id", type = "integer")),
        @FilterDef(name = "note", parameters = @ParamDef(name = "note", type = "string")),
        @FilterDef(name = "customerNote", parameters = @ParamDef(name = "customerNote", type = "string")),
        @FilterDef(name = "calendar", parameters = @ParamDef(name = "calendar", type = "integer")),
})
@Filters({
        @Filter(name = "id", condition = "id = :id"),
        @Filter(name = "note", condition = "note like '%' || :note || '%'"),
        @Filter(name = "customerNote", condition = "customerNote like '%' || :customerNote || '%'"),
        @Filter(name = "calendar", condition = "calendar = :calendar"),
})
@Table(name = "Report")
public class Report extends AbstractEntity {

    @Id
    @GeneratedValue
    @PrimaryKeyJoinColumn
    private Integer id;

    @Column(nullable = false, length = 256)
    private String note;

    @Column(nullable = false, length = 256)
    private String customerNote;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "calendar")
    private Calendar calendar;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "employee")
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "project")
    private Project project;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "task")
    private Task task;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "customer")
    private Customer customer;


    public Report() {
        this("", "", 0, new Calendar());
    }

    public Report(String note, String customerNote, Integer quantity, Calendar calendar) {
        this.note = note;
        this.customerNote = customerNote;
        this.quantity = quantity;
        this.calendar = calendar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}


