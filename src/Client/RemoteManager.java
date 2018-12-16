package Client;

import Shared.BaseService;
import Shared.RelationService;
import Shared.UserService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RemoteManager {
    private static RemoteManager instance;
    private static Registry registry = null;

    /**
     * Method used to generate a single instance of a RemoteManager object (Singleton class)
     * @return instance of RemoteManager
     */
    public static RemoteManager getInstance() throws RemoteException {
        if (instance == null) {
            instance = new RemoteManager();
            registry = LocateRegistry.getRegistry("localhost", 1099);
        }
        return instance;
    }

    public UserService getUserService() throws Exception {
        return (UserService) registry.lookup("user");
    }

    public BaseService getCalendarService() throws Exception {
        return (BaseService) registry.lookup("calendar");
    }

    public BaseService getRoleService() throws Exception {
        return (BaseService) registry.lookup("role");
    }

    public BaseService getProjectService() throws Exception {
        return (BaseService) registry.lookup("project");
    }

    public BaseService getEmployeeService() throws Exception {
        return (BaseService) registry.lookup("employee");
    }

    public BaseService getCustomerService() throws Exception {
        return (BaseService) registry.lookup("customer");
    }

    public BaseService getTaskService() throws Exception {
        return (BaseService) registry.lookup("task");
    }

    public RelationService getProjectTaskService() throws Exception {
        return (RelationService) registry.lookup("projectTask");
    }
}
