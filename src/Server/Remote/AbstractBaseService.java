package Server.Remote;

import Server.Entity.EntityInterface;
import Server.Repository.RepositoryInterface;
import Server.Result;
import Shared.BaseService;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Abstract class, implements a repository's base services
 */
public abstract class AbstractBaseService extends UnicastRemoteObject implements BaseService {
    protected RepositoryInterface repository;

    public AbstractBaseService(RepositoryInterface repository) throws RemoteException {
        this.repository = repository;
    }

    @Override
    public JSONObject readAll() {
        List list = repository.read();
        return (new Result(true, list)).toJson();
    }

    @Override
    public JSONObject read(JSONObject parameters) {
        List list = repository.read(prepareReadParameter(parameters));
        return (new Result(true, list)).toJson();
    }

    @Override
    public JSONObject save(JSONObject data) {
        EntityInterface entity;
        try {
            entity = castJsonIntoEntity(data);
        } catch (IOException e) {
            Result result = new Result(e.getMessage(), false);
            return result.toJson();
        }
        Result result = entity.save();
        return result.toJson();
    }

    @Override
    public JSONObject delete(JSONObject data) {
        EntityInterface entity;
        try {
            entity = castJsonIntoEntity(data);
        } catch (IOException e) {
            Result result = new Result(e.getMessage(), false);
            return result.toJson();
        }
        Result result = entity.delete();
        return result.toJson();
    }

    protected HashMap<String, Object> prepareReadParameter(JSONObject parameters) {
        HashMap<String, Object> parser = new HashMap<>();
        Set keys = parameters.keySet();

        for (Object key : keys) {
            parser.put((String) key, parameters.get(key));
        }

        return parser;
    }

    /**
     * Method used to case a JSONObject containing an entity model into a defined entity
     * @param jsonObject (JSONObject containing defined entity model)
     * @return (Correctly parsed entity)
     * @throws IOException
     */
    protected abstract EntityInterface castJsonIntoEntity(JSONObject jsonObject) throws IOException;
}
