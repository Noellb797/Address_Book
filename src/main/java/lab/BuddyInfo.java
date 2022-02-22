package lab;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Baillie Noell
 * 101066676
 * SYSC 4806 Lab 5
 */
@Entity
public class BuddyInfo {
    private String name;
    private String address;
    private String phone;
    private Long addressBookId;

    @Id
    @GeneratedValue
    private Long id;

    public BuddyInfo() {
        this.name = "";
        this.address = "";
        this.phone = "";
        this.addressBookId = 0L;
    }

    public BuddyInfo(String name, String address, String phone, Long addressBookId) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.addressBookId = addressBookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAddressBookId() {
        return addressBookId;
    }

    public void setAddressBookId(Long addressBookId) {
        this.addressBookId = addressBookId;
    }
}
