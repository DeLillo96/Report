package Server.Repository;

import Server.Entity.Employee;
import Server.Entity.Users;

import java.util.HashMap;
import java.util.List;

public class EmployeeRepository extends AbstractRepository {
    public EmployeeRepository() {
        super("Employee");
    }

    public Employee getEmployeeById(Integer id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        List employee = read(params);

        return employee != null && employee.size() == 1 ? (Employee) employee.get(0) : null;
    }

    public Employee getEmployeebyName(String name) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", name);
        List employee = read(params);

        return employee != null && employee.size() == 1 ? (Employee) employee.get(0) : null;
    }

    public Employee getEmployeeByUser(Users user) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("user_id", user.getId());
        List adults = read(params);
        if (adults != null && adults.size() == 1)
            return (Employee) adults.get(0);
        else
            return null;
    }

    public Employee getEmployeeByUsername(String username) {
        UsersRepository usersRepository = new UsersRepository();
        return getEmployeeByUser(usersRepository.getUserByUsername(username));
    }
}
