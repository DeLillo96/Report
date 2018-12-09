package Server.Entity;

import Server.Result;

import javax.persistence.*;

@Entity
@Table(name = "Employee")
public class Employee extends Person {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users")
    private Users users;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role")
    private Role role;

    public Employee() {
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
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

        if(getId() == null || getId() == 0 || getUsers() == null) {
            String username = getName().replace(" ", ".").substring(0,3).toLowerCase()
                    + "." + getLastName().replace(" ", ".").substring(0,3).toLowerCase()
                    + "." + getFiscalCode().substring(12).toLowerCase();
            Users newUser = new Users(username, username);
            Result result = newUser.save();
            if(result.isSuccess()) {
                setUsers(newUser);
            }
        }
    }
}
