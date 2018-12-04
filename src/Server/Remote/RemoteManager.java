package Server.Remote;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Class used for RMI services, replaces the binding for the specified name contained in registry with an implementation
 * of the required services interface
 */
public class RemoteManager {
    final private int port = 1099;

    public RemoteManager() throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(port);

        registry.rebind("user", new UserServiceImplementation());
        registry.rebind("calendar", new CalendarServiceImplementation());
        registry.rebind("role", new RoleServiceImplementation());
        registry.rebind("project", new ProjectServiceImplementation());
    }

    public int getPort() {
        return port;
    }
}
