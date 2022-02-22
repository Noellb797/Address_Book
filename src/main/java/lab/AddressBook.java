package lab;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Baillie Noell
 * 101066676
 * SYSC 4806 Lab 5
 */
@Entity
public class AddressBook{
    @OneToMany (cascade = CascadeType.PERSIST)
    private List<BuddyInfo> buddies;
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public AddressBook(){
        buddies = new ArrayList<>();
    }

    public AddressBook(String name) {
        this.name = name;
        buddies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addNewBuddy(String name, String address, String phone, Long addressBookId) {
        BuddyInfo buddy = new BuddyInfo(name, address, phone, addressBookId);
        buddies.add(buddy);
    }

    public void addBuddy(BuddyInfo buddy) {
        buddies.add(buddy);
    }

    public void deleteBuddy(int index) {
        buddies.remove(index);
    }

    public int findBuddyIndex(String name, String address, String phone) {
        int index = 0;
        for (BuddyInfo buddy: buddies) {
            if (buddy.getName().equals(name) && buddy.getAddress().equals(address) && buddy.getPhone().equals(phone)) {
                return index;
            }
            index ++;
        }
        return -1;
    }
    public List<BuddyInfo> getBuddies() { return buddies; }

    public void setBuddies(List<BuddyInfo> buddies) {
        this.buddies = buddies;
    }

    public int getNumBuddies() {
        return buddies.size();
    }

    public BuddyInfo getBuddyAt(int index) {
        return buddies.get(index);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

}
