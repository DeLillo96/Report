package Server.Test;

import Server.Entity.*;
import Server.Result;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReportTest {
    private static Project project = new Project("War", "north vs south", new Date(), null);
    private static Customer customer = new Customer("King's Landing");
    private static Time task = new Time("Battle");
    private static Employee employee = new Employee("Sandor", "Clegane", "SNDCLG92H51A730S", "8204710473");
    private static Report report = new Report();

    @BeforeAll
    static void create() {
        project.save();
        customer.save();
        task.save();
        employee.save();
    }

    @AfterAll
    static void delete() {
        report.delete();
        project.delete();
        customer.delete();
        task.delete();
    }

    @Test
    void save() {
        report.setProject(project);
        report.setCustomer(customer);
        report.setTask(task);
        report.setEmployee(employee);
        report.setQuantity(8);
        report.setNote("Kill 3 person");

        Result result = report.save();

        assertTrue(result.isSuccess(), "Error during saving Report");
    }

}
