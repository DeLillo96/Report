package Server.Remote;

import Server.Entity.EntityInterface;
import Server.Entity.Report;
import Server.Repository.ReportRepository;
import Shared.BaseService;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.rmi.RemoteException;

public class ReportServiceImplementation extends AbstractBaseService implements BaseService {
    public ReportServiceImplementation() throws RemoteException {
        super(new ReportRepository());
    }

    @Override
    protected EntityInterface castJsonIntoEntity(JSONObject jsonObject) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonObject.toString(), Report.class);
    }
}
