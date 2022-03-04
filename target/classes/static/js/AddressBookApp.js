/*
 * Baillie Noell
 * 101066676
 * SYSC 4806 Lab 5
 *
 * The javascript functions used in the index SPA
 */

//stores the ids of the address books
let addressBookIds = [];

//count of objects created using the SPA
let count = 0;

/*
 * uses ajax call to create new address book using the post method with json body
 */
function newAddressBook(name) {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: '/AddressBook/new',
        dataType: "json",
        data: JSON.stringify({
            name: name
        })
    });
    //set timeout so ajax call finishes
    setTimeout(function(){
        //increase count, add id, and update data
        count++;
        addressBookIds.push(count);
        updateData();
    }, 1000);
}

/*
 * uses ajax call to create new buddy using the post method with json body
 */
function newBuddy(aBID, name, address, phone) {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: '/AddressBook/addBuddy',
        dataType: "json",
        data: JSON.stringify({
            addressBookId: aBID,
            name: name,
            address: address,
            phone: phone
        })
    });
    //set timeout so ajax call can finish, update count and update data
    setTimeout(function(){
        count++;
        updateData();
    }, 1000);

}

/*
 * empty the previously shown books and buddies, and get each addressbook
 */
function updateData(){
    $('#books').empty();
    $('#buddies').empty();
    addressBookIds.forEach(getAddressBook);
}

/*
 * Get each address book by id value, and update the book
 */
function getAddressBook(value) {
    $.ajax({
        type: "GET",
        url: '/AddressBook/' + value,
        dataType: "json",
        success: function(data) {
            updateBook(data, value, data.numBuddies);
        }
    });
}

/*
 * Add each book name and id to html
 * get the buddies in the book
 */
function updateBook(data, value, numBuddies) {
    $('#books').append("<h2>" + data.name + " ID: " + value + "</h2>");
    getBuddies(value, numBuddies);
}

/*
 * get the buddies in the book by id value
 * update the buddies
 */
function getBuddies(value, numBuddies) {
    $.ajax({
        type: "GET",
        url: '/AddressBook/' + value + '/buddies',
        dataType: "json",
        success: function(data) {
            updateBuddies(data, numBuddies);
        }
    });
}

/*
 * add the buddies to the html
 */
function updateBuddies(data, numBuddies) {
    for (let i = 0; i < numBuddies; i++) {
        $('#buddies').append("<p><b>Name:</b> " + data._embedded.BuddyInfo[i].name + "     <b>Address: </b>" + data._embedded.BuddyInfo[i].address + "     <b>Phone: </b>" + data._embedded.BuddyInfo[i].phone + "     <b>Address Book ID: </b> " + data._embedded.BuddyInfo[i].addressBookId + "</p>");
    }
}

