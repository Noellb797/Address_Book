# Address_Book
# [![Maven CI/CD](https://github.com/Noellb797/Address_Book/actions/workflows/main.yml/badge.svg)](https://github.com/Noellb797/Address_Book/actions/workflows/main.yml)

##Description
Baillie Noell

101066676

Address Book application for SYSC 4806 Lab 6

###CI/CD
- CI/CD pipeline implemented in [GitHub actions](https://github.com/Noellb797/Address_Book/actions/workflows/main.yml)
- [GitHib actions file](https://github.com/Noellb797/Address_Book/blob/main/.github/workflows/main.yml)
- When push is made to the main branch the build_and_test with Maven is run
- Only when build_and_test passes does main then deploy to Heroku
- [Heroku app found here](https://baillienoelladdressbook.herokuapp.com/)

###Application Instructions
- The main page in the Heroku app allows for adding of new AddressBooks and Buddies
- Static pages can also be used, URLs can be found on main page
- Instructions for all controller methods can be found in the comments [here](https://github.com/Noellb797/Address_Book/blob/main/src/main/java/lab/AddressBookController.java)
