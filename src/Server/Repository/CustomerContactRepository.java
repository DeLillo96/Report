package Server.Repository;

import Server.Entity.CustomerContact;

import java.util.HashMap;
import java.util.List;

public class CustomerContactRepository extends AbstractRepository {
    public CustomerContactRepository() {
        super("CustomerContact");
    }

    public CustomerContact getCustomerContactById(Integer id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        List customerContact = read(params);

        return customerContact != null && customerContact.size() == 1 ? (CustomerContact) customerContact.get(0) : null;
    }
}
