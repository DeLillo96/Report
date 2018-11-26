package Server.Entity;

import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@FilterDefs({
        @FilterDef(name = "id", parameters = @ParamDef(name = "id", type = "integer")),
        @FilterDef(name = "name", parameters = @ParamDef(name = "name", type = "string")),
        @FilterDef(name = "lastName", parameters = @ParamDef(name = "lastName", type = "string")),
        @FilterDef(name = "telephone", parameters = @ParamDef(name = "telephone", type = "string"))
})
@Filters({
        @Filter(name = "id", condition = "id = :id"),
        @Filter(name = "name", condition = "name like :name"),
        @Filter(name = "lastName", condition = "lastName like :lastName"),
        @Filter(name = "telephone", condition = "telephone like :telephone")
})
@Table(name = "Person")
public class Person extends AbstractEntity {

    @Id
    @GeneratedValue
    @PrimaryKeyJoinColumn
    private Integer id;

    @Column(nullable = false, length = 16)
    private String name;

    @Column(nullable = false, length = 16)
    private String lastName;

    @Column(unique = true, length = 10)
    private String telephone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users")
    private Users users;

    @ManyToMany(mappedBy = "persons", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Set<Location> locations = new HashSet<>();

    public Person() {
        this("", "", "", null);
    }

    public Person(String name, String lastName, String telephone, Users user) {
        this.name = name;
        this.lastName = lastName;
        this.telephone = telephone;
        this.users = user;
    }

    public Person(String name, String lastName) {
        this(name, lastName, null, null);
    }

    public Person(String name, String lastName, String telephone) {
        this(name, lastName, telephone, null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users user) {
        this.users = user;
    }
}
