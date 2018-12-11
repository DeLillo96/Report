package Server.Entity;

import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;

@Entity
@FilterDefs({
        @FilterDef(name = "customer_id", parameters = {@ParamDef(name = "customer_id", type = "integer")}),
})
@Filters({
        @Filter(name = "customer_id", condition = "customer_id = :customer_id"),
})
@Table(name = "CustomerContact")
public class CustomerContact extends Person {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public CustomerContact() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}


