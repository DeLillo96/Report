package Server.Remote;

import Server.Entity.CustomerContact;
import Server.Entity.EntityInterface;
import Server.Repository.CustomerContactRepository;
import Shared.BaseService;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.rmi.RemoteException;

public class CustomerContactServiceImplementation extends AbstractBaseService implements BaseService {
    public CustomerContactServiceImplementation() throws RemoteException {
        super(new CustomerContactRepository());
    }

    @Override
    protected EntityInterface castJsonIntoEntity(JSONObject jsonObject) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonObject.toString(), CustomerContact.class);
    }
}
