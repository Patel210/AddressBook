package com.capgemini.addressbook;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.capgemini.addressbook.AddressBookMain.IOTYPE;

public class AddressBookUtility {

	public static void main(String[] args) {

		AddressBookMain addressBookMain = new AddressBookMain();
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println(
					"1. create new address book\n2. add contact to a particular address book\n3. view address book\n4."
							+ " get contacts by city\n5. get contacts by state\n6. get a list of all contact in particular city\n7. get a count of contacts by city"
							+ "\n8. sort the address book by name\n9. sort the address book by city\n10. sort the address book by state"
							+ "\n11. sort the address book by zip\n12. write address book to text file\n13. write address book to CSV file"
							+ "\n14. read contacts from a text file\n15. read contacts from a CSV file\n16. write address book to JSON\n17. read contacts from json file\n18. Exit");

			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter the address book name: ");
				addressBookMain.addAddressBook(sc.next());
				break;
			case 2:
				System.out.println("Enter the address book name for entering the contact: ");
				Contact contact = addressBookMain.createContact();
				addressBookMain.addContactToParticularAddressBook(sc.next(), contact);
				break;
			case 3:
				System.out.println("Enter the address book name for viewing: ");
				addressBookMain.viewAddressBook(sc.next());
				break;
			case 4:
				Map<String, LinkedList<Contact>> contactsByCity = addressBookMain.addressBookByCity();
				System.out.println(contactsByCity);
				break;
			case 5:
				Map<String, LinkedList<Contact>> contactsByState = addressBookMain.addressBookByState();
				System.out.println(contactsByState);
				break;
			case 6:
				System.out.println("Enter the city: ");
				List<Contact> listOfContacts = addressBookMain.listOfContactsInParticularCity(sc.next());
				listOfContacts.stream().forEach(System.out::println);
				break;
			case 7:
				Map<String, Integer> countByCity = addressBookMain.contactCountByCity();
				System.out.println(countByCity);
				break;
			case 8:
				System.out.println("Enter the name of address book you want to sort: ");
				addressBookMain.sortAddressBookByPersonName(sc.next());
				break;
			case 9:
				System.out.println("Enter the name of address book you want to sort: ");
				addressBookMain.sortAddressBookByCity(sc.next());
				break;
			case 10:
				System.out.println("Enter the name of address book you want to sort: ");
				addressBookMain.sortAddressBookByState(sc.next());
				break;
			case 11:
				System.out.println("Enter the name of address book you want to sort: ");
				addressBookMain.sortAddressBookByZip(sc.next());
				break;
			case 12:
				System.out.println("Enter the name of the address book you want to write in a file:");
				addressBookMain.writeAddressBook(sc.next(), IOTYPE.TXT_FILE);
				break;
			case 13:
				System.out.println("Enter the name of the address book you want to write in a file:");
				addressBookMain.writeAddressBook(sc.next(), IOTYPE.CSV_FILE);
				break;
			case 14:
				System.out.println("Enter the file name:");
				String fileName = sc.next();
				System.out.println("Enter the address book where you want to store the contacts");
				addressBookMain.readContacts(fileName, sc.next(), IOTYPE.TXT_FILE);
				break;
			case 15:
				System.out.println("Enter the file name:");
				String fileName1 = sc.next();
				System.out.println("Enter the address book where you want to store the contacts");
				addressBookMain.readContacts(fileName1, sc.next(), IOTYPE.CSV_FILE);
				break;
			case 16:
				System.out.println("Enter the name of the address book you want to write in a file:");
				addressBookMain.writeAddressBook(sc.next(), IOTYPE.JSON_FILE);
				break;
			case 17:
				System.out.println("Enter the file name:");
				String file = sc.next();
				System.out.println("Enter the address book where you want to store the contacts");
				addressBookMain.readContacts(file, sc.next(), IOTYPE.JSON_FILE);
			}

		} while (choice != 18);

		sc.close();
	}
}
