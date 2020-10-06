package com.capgemini.addressbook;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AddressBookUtilityUC10 {

	public static void main(String[] args) {

		AddressBookMain addressBookMain = new AddressBookMain();
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("1. create new address book\n2. add contact to a particular address book\n3."
					+ " get contacts by city\n4. get contacts by state\n5. get a list of all contact in particular city\n6. get a count of contacts by city"
					+ "\n7. sort the address book by name\n8. sort the address book by city\n9. sort the address book by state"
					+ "\n10. sort the address book by zip\n11. Exit");

			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter the address book name: ");
				addressBookMain.addAddressBook(sc.next());
				break;
			case 2:
				System.out.println("Enter the address book name for entering the contact: ");
				addressBookMain.addContactToParticularAddressBook(sc.next());
				break;
			case 3:
				Map<String, LinkedList<Contact>> contactsByCity = addressBookMain.addressBookByCity();
				System.out.println(contactsByCity);
				break;
			case 4:
				Map<String, LinkedList<Contact>> contactsByState = addressBookMain.addressBookByState();
				System.out.println(contactsByState);
				break;
			case 5:
				System.out.println("Enter the city: ");
				List<Contact> listOfContacts = addressBookMain.listOfContactsInParticularCity(sc.next());
				listOfContacts.stream().forEach(System.out::println);
				break;
			case 6:
				Map<String, Integer> countByCity = addressBookMain.contactCountByCity();
				System.out.println(countByCity);
				break;
			case 7:
				System.out.println("Enter the name of address book you want to sort: ");
				addressBookMain.sortAddressBookByPersonName(sc.next());
				break;
			case 8:
				System.out.println("Enter the name of address book you want to sort: ");
				addressBookMain.sortAddressBookByCity(sc.next());
				break;
			case 9:
				System.out.println("Enter the name of address book you want to sort: ");
				addressBookMain.sortAddressBookByState(sc.next());
				break;
			case 10:
				System.out.println("Enter the name of address book you want to sort: ");
				addressBookMain.sortAddressBookByZip(sc.next());
				break;
			}

		} while (choice != 11);

		sc.close();
	}
}
