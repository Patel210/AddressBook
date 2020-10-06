package com.capgemini.addressbook;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AddressBookUtility {

	public static void main(String[] args) {

		AddressBookMain addressBookMain = new AddressBookMain();
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("1. create new address book\n2. add contact to a particular address book\n3."
					+ " get contacts by city\n4. get a list of all contact in particular city\n5. get a count of contacts by city"
					+ "\n6. sort the address book by name\n7. Exit ");

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
				System.out.println("Enter the city: ");
				List<Contact> listOfContacts = addressBookMain.listOfContactsInParticularCity(sc.next());
				listOfContacts.stream().forEach(System.out::println);
				break;
			case 5:
				Map<String, Integer> countByCity = addressBookMain.contactCountByCity();
				System.out.println(countByCity);
				break;
			case 6:
				System.out.println("Enter the name of address book you want to sort: ");
				addressBookMain.sortAddressBookByPersonName(sc.next());
				break;
			}

		} while (choice != 7);
		sc.close();
	}
}
