package lab;

import lab.AddressBook;
import lab.BuddyInfo;
import org.junit.Before;
import org.junit.Test;
import javax.persistence.*;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Baillie Noell
 * 101066676
 * SYSC 4806 Lab 2
 */
public class AddressBookTest {
    AddressBook ab;
    BuddyInfo b1, b2;
    @Before
    public void setUp() throws Exception {
        ab = new AddressBook();
        ab.addNewBuddy("John Doe", "123 First St", "(123) 456 7890", 0L);
        ab.addNewBuddy("Bob Smith", "555 Fifth Ave", "(555) 555 5555", 0L);
    }

    @Test
    public void getNumBuddies() {
        assertEquals(2, ab.getNumBuddies());
    }

    @Test
    public void getBuddyAt() {
        BuddyInfo b = ab.getBuddyAt(0);

        assertEquals("John Doe", b.getName());
        assertEquals("123 First St", b.getAddress());
        assertEquals("(123) 456 7890", b.getPhone());
    }

    @Test
    public void addBuddy() {
        ab.addNewBuddy("Peter Rogers", "000 North Pole", "(000) 000 0000", 0L);
        BuddyInfo b = ab.getBuddyAt(2);

        assertEquals(3, ab.getNumBuddies());

        assertEquals("Peter Rogers", b.getName());
        assertEquals("000 North Pole", b.getAddress());
        assertEquals("(000) 000 0000", b.getPhone());
    }

    @Test
    /*
    Used to create AddressBookPersist.db
    (If ran now will create sample.db)
    Creates and persists two lab.BuddyInfo objects, adds to an lab.AddressBook and then persists the lab.AddressBook
    Modified from example JDBCTest
     */
    public void persistenceTest() {
        ab = new AddressBook();
        ab.setName("Book 1");
        b1 = new BuddyInfo("John Smith", "123 Fourth St", "1234567890", 0L);
        b2 = new BuddyInfo("Ryan Jones", "321 Main St", "1111111111", 0L);

        // Connecting to the database through EntityManagerFactory
        // connection details loaded from persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab");

        EntityManager em = emf.createEntityManager();

        // Creating a new transaction
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // Persisting the lab.BuddyInfo entity objects
        em.persist(b1);
        em.persist(b2);

        tx.commit();

        ab.addBuddy(b1);
        ab.addBuddy(b2);

        tx.begin();

        // Persisting the lab.AddressBook entity object
        em.persist(ab);

        tx.commit();

        // Querying the contents of the database using JPQL query
        Query q = em.createQuery("SELECT a FROM AddressBook a");

        @SuppressWarnings("unchecked")
        List<AddressBook> results = q.getResultList();

        assertNotNull(results);

        System.out.println("List of BuddyInfos in AddressBooks\n----------------");

        for (AddressBook a : results) {
            System.out.println("lab.AddressBook Name: " + a.getName());
            List<BuddyInfo> buddies = a.getBuddies();
            for (BuddyInfo b : buddies) {
                System.out.println("Name: " + b.getName());
                System.out.println("Address: " + b.getAddress());
                System.out.println("Phone Number: " + b.getPhone());
                System.out.println();
            }
        }
        // Closing connection
        em.close();

        emf.close();
    }


    @Test
    /*
    Used to create AddressBookCascade.db
    (If ran now will create sample.db)
    Creates an lab.AddressBook, adds 2 lab.BuddyInfo objects and persists all
    Modified from example JDBCTest
     */
    public void cascadeTest() {
        ab = new AddressBook();
        ab.setName("Book 2");
        b1 = new BuddyInfo("Jane Doe", "000 North Pole", "0000000000", 0L);
        b2 = new BuddyInfo("Sally Ride", "Outer Space", "**********", 0L);

        ab.addBuddy(b1);
        ab.addBuddy(b2);

        // Connecting to the database through EntityManagerFactory
        // connection details loaded from persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab");

        EntityManager em = emf.createEntityManager();

        // Creating a new transaction
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // Persisting the lab.AddressBook entity object
        em.persist(ab);

        tx.commit();

        // Querying the contents of the database using JPQL query
        Query q = em.createQuery("SELECT a FROM AddressBook a");

        @SuppressWarnings("unchecked")
        List<AddressBook> results = q.getResultList();

        assertNotNull(results);

        System.out.println("List of BuddyInfos in AddressBooks\n----------------");

        for (AddressBook a : results) {
            System.out.println("lab.AddressBook Name: " + a.getName());
            List<BuddyInfo> buddies = a.getBuddies();
            for (BuddyInfo b : buddies) {
                System.out.println("Name: " + b.getName());
                System.out.println("Address: " + b.getAddress());
                System.out.println("Phone Number: " + b.getPhone());
                System.out.println();
            }
        }
        // Closing connection
        em.close();

        emf.close();
    }

}