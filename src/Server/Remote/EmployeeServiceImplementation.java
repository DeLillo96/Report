package Server.Remote;

import Server.Entity.EntityInterface;
import Server.Entity.Employee;
import Server.Repository.EmployeeRepository;
import Shared.BaseService;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.rmi.RemoteException;

public class EmployeeServiceImplementation extends AbstractBaseService implements BaseService {
    public EmployeeServiceImplementation() throws RemoteException {
        super(new EmployeeRepository());
    }

    @Override
    protected EntityInterface castJsonIntoEntity(JSONObject jsonObject) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonObject.toString(), Employee.class);
    }
}
