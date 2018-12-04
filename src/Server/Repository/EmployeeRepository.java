package Server.Repository;

import Server.Entity.Employee;

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
}
