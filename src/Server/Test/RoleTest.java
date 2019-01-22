package Server.Test;

import Server.Entity.Role;
import Server.Repository.RoleRepository;
import Server.Result;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoleTest {
    private static String description = "Protector of the people";
    private static RoleRepository roleRepository = new RoleRepository();
    private static Role role = new Role("King", description);

    @BeforeAll
    static void createUser() {
        role.save();
    }

    @AfterAll
    static void deleteUser() {
        role.delete();
    }

    @Test
    void readRole() {
        Role readRole = roleRepository.getRoleById(role.getId());
        String message = "Error during reading operation";

        assertEquals(role.getName(), readRole.getName(), message);
        assertEquals(role.getDescription(), readRole.getDescription(), message);
    }

    @Test
    void modifyRole() {
        role.setDescription("Occupant of the throne of swords");
        Result result = role.save();

        assertTrue(result.isSuccess(), "Error during saving operation " + result.getMessages().toString());
    }

    @Test
    void verifyConstraint() {
        Role newRole = new Role(role.getName(), "Traitor");
        Result result = newRole.save();

        assertFalse(result.isSuccess(), "Violated constraints");
        if (!result.isSuccess()) newRole.delete();
    }
}
