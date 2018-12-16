package Server.Remote;

import Server.Entity.Cost;
import Server.Entity.EntityInterface;
import Server.Entity.Task;
import Server.Entity.Time;
import Server.Repository.TaskRepository;
import Shared.BaseService;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.rmi.RemoteException;

public class TaskServiceImplementation extends AbstractBaseService implements BaseService {
    public TaskServiceImplementation() throws RemoteException {
        super(new TaskRepository());
    }

    @Override
    protected EntityInterface castJsonIntoEntity(JSONObject jsonObject) throws IOException {
        Task result = jsonObject.get("type").equals("Time") ? new Time() : new Cost();

        result.setDescription((String) jsonObject.get("description"));
        if(jsonObject.get("id") != null) result.setId(Integer.parseInt((String) jsonObject.get("id")));
        result.setType((String) jsonObject.get("type"));

        return result;
    }
}
