package Server.Repository;

import Server.Entity.Customer;

import java.util.HashMap;
import java.util.List;

public class CustomerRepository extends AbstractRepository {
    public CustomerRepository() {
        super("Customer");
    }

    public Customer getCustomerById(Integer id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        List customer = read(params);

        return customer != null && customer.size() == 1 ? (Customer) customer.get(0) : null;
    }

    public Customer getCustomerbyCode(String code) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("code", code);
        List customer = read(params);

        return customer != null && customer.size() == 1 ? (Customer) customer.get(0) : null;
    }
}
