package Server.Test;

import Server.Entity.*;
import Server.Repository.ProjectRepository;
import Server.Result;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectTest {
    private static ProjectRepository projectRepository = new ProjectRepository();
    private static Project project = new Project("Murder", "Kill a man", new Date(), null);
    private static Customer customer = new Customer("Volantis");
    private static Time task1 = new Time("Spy the man");
    private static Cost task2 = new Cost("Buy a knife");
    private static Time task3 = new Time("Kill the man");

    @BeforeAll
    static void create() {
        project.save();
        customer.save();
        task1.save();
        task2.save();
        task3.save();
    }

    @AfterAll
    static void delete() {
        project.delete();
        customer.delete();
        task1.delete();
        task2.delete();
        task3.delete();
    }

    @Test
    void readProject() {
        Project readProject = projectRepository.getProjectById(project.getId());
        String message = "Error during reading operation";

        assertEquals(project.getCode(), readProject.getCode(), message);
        assertEquals(project.getDescription(), readProject.getDescription(), message);
    }

    @Test
    void modifyProject() {
        project.setDescription("Kill the Queen");
        Result result = project.save();

        assertTrue(result.isSuccess(), "Error during saving operation " + result.getMessages().toString());
    }

    @Test
    void setCustomer() {
        project.setCustomer(customer);
        project.save();

        Project readProject = projectRepository.getProjectById(project.getId());
        Customer readCustomer = readProject.getCustomer();

        assertEquals(customer.getCode(), readCustomer.getCode(), "Error during setting customer operation");
    }

    @Test
    void setTasks() {
        Set<Task> taskSet = new HashSet<>();
        taskSet.add(task1);
        taskSet.add(task2);
        taskSet.add(task3);
        project.setTasks(taskSet);

        Result result = project.save();

        assertTrue(result.isSuccess(), "Error during setting tasks operation");
    }
}
