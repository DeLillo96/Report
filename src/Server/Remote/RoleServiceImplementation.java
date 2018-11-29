package Server.Remote;

import Server.Entity.Role;
import Server.Entity.EntityInterface;
import Server.Repository.RoleRepository;
import Shared.BaseService;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.rmi.RemoteException;

public class RoleServiceImplementation extends AbstractBaseService implements BaseService {
    public RoleServiceImplementation() throws RemoteException {
        super(new RoleRepository());
    }

    @Override
    protected EntityInterface castJsonIntoEntity(JSONObject jsonObject) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonObject.toString(), Role.class);
    }
}
