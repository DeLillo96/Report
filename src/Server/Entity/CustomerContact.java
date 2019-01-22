package Server.Entity;

import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@FilterDefs({
        @FilterDef(name = "customer", parameters = {@ParamDef(name = "customer", type = "integer")}),
})
@Filters({
        @Filter(name = "customer", condition = "customer = :customer"),
})
public class CustomerContact extends Person {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "customer")
    private Customer customer;

    public CustomerContact() {
    }

    public CustomerContact(String name, String surname, String fiscalCode, String telephone) {
        super(name, surname, fiscalCode, telephone);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}


