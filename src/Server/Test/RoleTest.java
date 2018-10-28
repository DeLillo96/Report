package Server.Test;

import Server.Entity.Role;
import Server.Entity.Users;
import Server.Repository.RoleRepository;
import Server.Result;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoleTest {
    private static String description = "Wife of King Renly Baratheon";
    private static RoleRepository roleRepository = new RoleRepository();
    private static Users user = new Users("Magery", "Tyrell", "margery.tyrell@kingslanding.com");
    private static Role role = new Role("Lady Regina", description);

    @BeforeAll
    static void createUser() {
        role.save();
        user.setRole(role);
        user.save();
    }

    @AfterAll
    static void deleteUser() {
        user.delete();
        role.delete();
    }

    @Test
    void readRole() {
        Role readRole = roleRepository.getRoleById(role.getId());
        String message = "Error during reading operation";

        assertEquals(role.getName(), readRole.getName(), message);
        assertEquals(role.getDescription(), readRole.getDescription(), message);
        assertEquals(role, user.getRole(), message);
    }

    @Test
    void modifyRole() {
        role.setDescription("Wife of King Joffrey Baratheon");
        Result result = role.save();

        assertTrue(result.isSuccess(), "Error during saving operation + " + result.getMessages().toString());
    }

    @Test
    void verifyConstraint() {
        Role newRole = new Role(role.getName(), "Wife of King Tommen Baratheon");
        Result result = newRole.save();

        assertFalse(result.isSuccess(), "Violated constraints");
        if (!result.isSuccess()) newRole.delete();
    }
}
