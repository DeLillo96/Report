package Server.Test;

import Server.Entity.Customer;
import Server.Repository.CustomerRepository;
import Server.Result;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    private static CustomerRepository customerRepository = new CustomerRepository();
    private static Customer customer = new Customer("Winterfell");

    @BeforeAll
    static void createCustomer() {
        customer.save();
    }

    @AfterAll
    static void deleteCustomer() {
        customer.delete();
    }

    @Test
    void readCustomer() {
        Customer readCustomer = customerRepository.getCustomerById(customer.getId());
        String message = "Error during reading operation";

        assertEquals(customer.getCode(), readCustomer.getCode(), message);
    }

    @Test
    void modifyCustomer() {
        customer.setCode("Westeros");
        Result result = customer.save();

        assertTrue(result.isSuccess(), "Error during saving operation + " + result.getMessages().toString());
    }

    @Test
    void verifyConstraint() {
        Customer newCustomer = new Customer(customer.getCode());
        Result result = newCustomer.save();

        assertFalse(result.isSuccess(), "Violated constraints");
        if (!result.isSuccess()) newCustomer.delete();
    }
}
