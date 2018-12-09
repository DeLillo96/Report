package Server.Entity;

import Server.Result;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;

@Entity
@FilterDefs({
        @FilterDef(name = "user_id", parameters = {@ParamDef(name = "user_id", type = "integer")}),
        @FilterDef(name = "role_id", parameters = {@ParamDef(name = "role_id", type = "integer")}),
})
@Filters({
        @Filter(name = "user_id", condition = "user_id = :user_id"),
        @Filter(name = "role_id", condition = "role_id = :role_id"),
})
@Table(name = "Employee")
public class Employee extends Person {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public Employee() {
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    protected void beforeSave() {
        super.beforeSave();

        if(getId() == null || getId() == 0 || getUser() == null) {
            String username = getName().replace(" ", ".").substring(0,3).toLowerCase()
                    + "." + getLastName().replace(" ", ".").substring(0,3).toLowerCase()
                    + "." + getFiscalCode().substring(12).toLowerCase();
            Users newUser = new Users(username, username);
            Result result = newUser.save();
            if(result.isSuccess()) {
                setUser(newUser);
            }
        }
    }
}


