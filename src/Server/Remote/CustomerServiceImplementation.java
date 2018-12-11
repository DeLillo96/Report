package Server.Remote;

import Server.Entity.Customer;
import Server.Entity.EntityInterface;
import Server.Repository.CustomerRepository;
import Shared.BaseService;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.rmi.RemoteException;

public class CustomerServiceImplementation extends AbstractBaseService implements BaseService {
    public CustomerServiceImplementation() throws RemoteException {
        super(new CustomerRepository());
    }

    @Override
    protected EntityInterface castJsonIntoEntity(JSONObject jsonObject) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonObject.toString(), Customer.class);
    }
}
