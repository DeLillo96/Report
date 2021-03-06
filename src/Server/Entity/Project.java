package Server.Entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@FilterDefs({
        @FilterDef(name = "id", parameters = @ParamDef(name = "id", type = "integer")),
        @FilterDef(name = "code", parameters = @ParamDef(name = "code", type = "string")),
        @FilterDef(name = "description", parameters = @ParamDef(name = "description", type = "string")),
        @FilterDef(name = "expireFrom", parameters = {@ParamDef(name = "expireFrom", type = "date")}),
        @FilterDef(name = "expireTo", parameters = {@ParamDef(name = "expireTo", type = "date")}),
        @FilterDef(name = "customer", parameters = {@ParamDef(name = "customer", type = "integer")}),
})
@Filters({
        @Filter(name = "id", condition = "id = :id"),
        @Filter(name = "code", condition = "code like '%' || :code || '%'"),
        @Filter(name = "description", condition = "description like '%' || :description || '%'"),
        @Filter(name = "expireFrom", condition = "expire >= :expireFrom"),
        @Filter(name = "expireTo", condition = "expire <= :expireTo"),
        @Filter(name = "customer", condition = "customer = :customer")
})
@Table(name = "Project")
public class Project extends AbstractEntity {

    @Id
    @GeneratedValue
    @PrimaryKeyJoinColumn
    private Integer id;

    @Column(nullable = false, length = 16)
    private String code;

    @Column(nullable = false, length = 128)
    private String description;

    @Column(nullable = false)
    private Date expire;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "customer")
    private Customer customer;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "have",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "task_id")}
    )
    private Set<Task> tasks = new HashSet<>();

    public Project() {
        this("", "", new Date(), null);
    }

    public Project(String code, String description, Date expire, Customer customer) {
        this.code = code;
        this.description = description;
        this.expire = expire;
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
