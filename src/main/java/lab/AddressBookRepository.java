package lab;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Baillie Noell
 * 101066676
 * SYSC 4806 Lab 5
 *
 * Repository to store AddressBooks
 */
@RepositoryRestResource(collectionResourceRel = "AddressBook", path = "AddressBook")
public interface AddressBookRepository extends PagingAndSortingRepository<AddressBook, Long> {
    AddressBook findAddressBookById(@Param("id") Long id);
    List<AddressBook> findAll();

}
