package Server.Remote;

import Server.Entity.EntityInterface;
import Server.Entity.Project;
import Server.Repository.ProjectRepository;
import Shared.BaseService;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.rmi.RemoteException;

public class ProjectServiceImplementation extends AbstractBaseService implements BaseService {
    public ProjectServiceImplementation() throws RemoteException {
        super(new ProjectRepository());
    }

    @Override
    protected EntityInterface castJsonIntoEntity(JSONObject jsonObject) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonObject.toString(), Project.class);
    }
}
