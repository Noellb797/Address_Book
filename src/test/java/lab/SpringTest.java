package lab;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Baillie Noell
 * 101066676
 * SYSC 4806 Lab 6
 *
 * Test class for Spring application
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SpringTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AddressBookController controller;

    /**
     * Sanity test for controller
     */
    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    /**
     * Test that index page loads and contains "Address Book"
     */
    @Test
    void getAddressBook() throws Exception{
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(content().string(containsString("Address Book")));
    }

    /**
     * Test that the /AddressBook url returns json
     */
    @Test
    void getAddressBooks() throws Exception{
        this.mockMvc.perform(get("/AddressBook").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"));
    }

    /**
     * Test that you are able to POST a new Address Book object
     */
    @Test
    void addAddressBook() throws Exception{
        this.mockMvc.perform(post("/AddressBook").contentType("application/json").content("{\"name\":\"test\"}"))
                .andExpect(status().isCreated());
    }

    /**
     * Test that the /BuddyInfo url returns json
     */
    @Test
    void getBuddies() throws Exception{
        this.mockMvc.perform(get("/BuddyInfo").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"));
    }

    /**
     * Test that you are able to POST a new BuddyInfo object
     */
    @Test
    void addBuddy() throws Exception{
        this.mockMvc.perform(post("/BuddyInfo").contentType("application/json").content("{\"addressBookId\":\"1\",\"name\":\"test\",\"address\":\"testStreet\",\"phone\":\"1111111111\"}"))
                .andExpect(status().isCreated());
    }
}
