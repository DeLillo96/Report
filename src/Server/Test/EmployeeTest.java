package Server.Test;

import Server.Entity.Employee;
import Server.Entity.Role;
import Server.Entity.Users;
import Server.Repository.EmployeeRepository;
import Server.Repository.UsersRepository;
import Server.Result;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {
    private static EmployeeRepository employeeRepository = new EmployeeRepository();
    private static UsersRepository usersRepository = new UsersRepository();
    private static Role role = new Role("Lady Regina", "Wife of King Renly Baratheon");
    private static Employee employee = new Employee("Magery", "Tyrell","MGRTYR83P81Y374P","3023948579");

    @BeforeAll
    static void create() {
        employee.save();
        role.save();
    }

    @AfterAll
    static void delete() {
        employee.delete();
        role.delete();
    }

    @Test
    void verifySave() {
        Users user = usersRepository.getUserById(employee.getUser().getId());

        assertNotNull(user, "Error during 'after save' operation");
    }

    @Test
    void readEmployee() {
        Employee readEmployee = employeeRepository.getEmployeeById(employee.getId());
        String message = "Error during reading operation";

        assertEquals(employee.getName(), readEmployee.getName(), message);
        assertEquals(employee.getLastName(), readEmployee.getLastName(), message);
        assertEquals(employee.getTelephone(), readEmployee.getTelephone(), message);
        assertEquals(employee.getFiscalCode(), readEmployee.getFiscalCode(), message);
    }

    @Test
    void modifyEmployee() {
        employee.setTelephone("2103948571");
        Result result = employee.save();

        assertTrue(result.isSuccess(), "Error during saving operation " + result.getMessages().toString());
    }

    @Test
    void verifyConstraint() {
        Employee newEmployee = new Employee("fake name", "fake last name", "MGRTYR83P81Y374P", "fake telephone number");
        Result result = newEmployee.save();

        assertFalse(result.isSuccess(), "Violated constraints");
        if (!result.isSuccess()) newEmployee.delete();
    }

    @Test
    void setRole() {
        employee.setRole(role);
        employee.save();

        Employee readEmployee = employeeRepository.getEmployeeById(employee.getId());
        Role readRole = readEmployee.getRole();

        String message = "Error during setting operation";

        assertEquals(role.getName(), readRole.getName(), message);
        assertEquals(role.getDescription(), readRole.getDescription(), message);
    }
}
