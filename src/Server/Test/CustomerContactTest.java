package Server.Test;

import Server.Entity.Customer;
import Server.Entity.CustomerContact;
import Server.Repository.CustomerContactRepository;
import Server.Repository.CustomerRepository;
import Server.Result;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerContactTest {
    private static CustomerContactRepository customerContactRepository = new CustomerContactRepository();
    private static CustomerContact customerContact = new CustomerContact("John", "Snow","SNWJHN96T27V730G","3648120384");
    private static CustomerRepository customerRepository = new CustomerRepository();
    private static Customer customer = new Customer("The Wall");

    @BeforeAll
    static void create() {
        customer.save();
        customerContact.save();
    }

    @AfterAll
    static void delete() {
        customerContact.delete();
        customer.delete();
    }

    @Test
    void readCustomerContact() {
        CustomerContact readCustomerContact = customerContactRepository.getCustomerContactById(customerContact.getId());
        String message = "Error during reading operation";

        assertEquals(customerContact.getName(), readCustomerContact.getName(), message);
        assertEquals(customerContact.getLastName(), readCustomerContact.getLastName(), message);
        assertEquals(customerContact.getTelephone(), readCustomerContact.getTelephone(), message);
        assertEquals(customerContact.getFiscalCode(), readCustomerContact.getFiscalCode(), message);
    }

    @Test
    void modifyCustomerContact() {
        customerContact.setTelephone("2103948571");
        Result result = customerContact.save();

        assertTrue(result.isSuccess(), "Error during saving operation " + result.getMessages().toString());
    }

    @Test
    void verifyConstraint() {
        CustomerContact newCustomerContact = new CustomerContact("fake name", "fake last name", "SNWJHN96T27V730G", "fake telephone number");
        Result result = newCustomerContact.save();

        assertFalse(result.isSuccess(), "Violated constraints");
        if (!result.isSuccess()) newCustomerContact.delete();
    }

    @Test
    void setCustomer() {
        customerContact.setCustomer(customer);
        customerContact.save();

        CustomerContact readCustomerContact = customerContactRepository.getCustomerContactById(customerContact.getId());
        Customer readCustomer = readCustomerContact.getCustomer();

        String message = "Error during setting operation";

        assertEquals(customer.getCode(), readCustomer.getCode(), message);
    }
}
