package lab;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Baillie Noell
 * 101066676
 * SYSC 4806 Lab 5
 *
 * Repository to store BuddyInfo objects
 */
@RepositoryRestResource(collectionResourceRel = "BuddyInfo", path="BuddyInfo")
public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Integer> {

    BuddyInfo findByName(String name);

}

