package Server.Test;

import Server.Entity.Cost;
import Server.Entity.Task;
import Server.Entity.Time;
import Server.Repository.TaskRepository;
import Server.Result;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    private static TaskRepository taskRepository = new TaskRepository();
    private static Task time = new Time("Pray to the gods");
    private static Task cost = new Cost("buy a horse");

    @BeforeAll
    static void createUser() {
        time.save();
        cost.save();
    }

    @AfterAll
    static void deleteUser() {
        time.delete();
        cost.delete();
    }

    @Test
    void readTask() {
        Task readTask = taskRepository.getTaskById(time.getId());
        String message = "Error during reading operation";

        assertEquals(time.getDescription(), readTask.getDescription(), message);
        assertEquals("Time", readTask.getType(), message);
        assertNotEquals("Cost", readTask.getType(), message);
    }

    @Test
    void modifyTask() {
        cost.setDescription("buy two horse");
        Result result = cost.save();

        assertTrue(result.isSuccess(), "Error during saving operation " + result.getMessages().toString());
    }
}
