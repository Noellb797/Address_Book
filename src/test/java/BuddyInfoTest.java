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
public class BuddyInfoTest {
    BuddyInfo b1, b2;

    @Before
    public void setUp() throws Exception {
        b1 = new BuddyInfo("John Smith", "123 Fourth St", "1234567890", 0l);
        b2 = new BuddyInfo("Ryan Jones", "321 Main St", "1111111111", 0l);
    }

    @Test
    public void getName() {
        assertEquals("John Smith", b1.getName());
    }

    @Test
    public void getAddress() {
        assertEquals("123 Fourth St", b1.getAddress());
    }

    @Test
    public void getPhone() {
        assertEquals("1234567890", b1.getPhone());
    }

    @Test
    /*
    Used to create lab.BuddyInfo.db
    (If ran now will create sample.db)
    Persists two lab.BuddyInfo objects
    Modified from example JDBCTest
     */
    public void persistenceTest() {
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

        // Querying the contents of the database using JPQL query
        Query q = em.createQuery("SELECT b FROM BuddyInfo b");

        @SuppressWarnings("unchecked")
        List<BuddyInfo> results = q.getResultList();

        assertNotNull(results);

        System.out.println("List of BuddyInfos\n----------------");

        for (BuddyInfo b : results) {
            System.out.println("Name: " + b.getName());
            System.out.println("Address: " + b.getAddress());
            System.out.println("Phone Number: " + b.getPhone());
            System.out.println();
        }
        // Closing connection
        em.close();

        emf.close();
    }
}