package Client;

/**
 * This singleton class handles all the renders of the application (popups and notifies)
 */
public class ControllerManager {
    private static ControllerManager instance;
    private static int roleId;
    private static int userId;
    private static int employeeId;

    /**
     * Singleton method
     * @return instance of ControllerManager
     */
    public static ControllerManager getInstance() {
        if (instance == null) instance = new ControllerManager();
        return instance;
    }

    public int getRole() {
        return roleId;
    }

    public void setRole(int roleId) {
        ControllerManager.roleId = roleId;
    }

    public void setEmployee(Integer employeeId) {
        ControllerManager.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }
}
