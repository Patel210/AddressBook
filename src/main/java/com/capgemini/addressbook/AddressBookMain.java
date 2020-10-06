package com.capgemini.addressbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBookMain {

	private static List<Contact> addressBook = new ArrayList<Contact>();
	private static final Scanner SC = new Scanner(System.in);

	public AddressBookMain() {
		super();
	}

	/**
	 * To add contact to the address book
	 */
	public void addContact(Contact contact) {
		addressBook.add(contact);
		System.out.println("Contact added Successfully!");
	}

	/**
	 * To get the contact from address book using first and last name
	 */
	public Contact getContact(String firstName, String lastName) {
		Contact contact = null;
		for (Contact contactObject : addressBook) {
			if (contactObject.getFirstName().equalsIgnoreCase(firstName)
					&& contactObject.getLastName().equalsIgnoreCase(lastName)) {
				contact = contactObject;
				break;
			}
		}
		return contact;
	}

	/**
	 * To edit contact in the address book
	 */
	public void editContact(String firstName, String lastName) {
		Contact contact = getContact(firstName, lastName);
		if (contact != null) {
			System.out.println("Enter the details again to update the contact \nfirst name:");
			contact.setFirstName(SC.next());
			System.out.println("last name:");
			contact.setLastName(SC.next());
			System.out.println("Address: ");
			SC.nextLine();
			contact.setAddress(SC.nextLine());
			System.out.println("City: ");
			contact.setCity(SC.nextLine());
			System.out.println("State: ");
			contact.setState(SC.next());
			System.out.println("Zip: ");
			contact.setZip(SC.nextLong());
			System.out.println("Phone Number: ");
			contact.setPhoneNumber(SC.nextLong());
			System.out.println("Email Address: ");
			contact.setEmail(SC.next());
			System.out.println("Contact Updated Successfully!");
		}

		else {
			System.out.println("NO such contact in the address book!");
		}
	}

	/**
	 * To remove contact from the address book
	 */
	public void removeContact(String firstName, String lastName) {

		Contact contact = getContact(firstName, lastName);
		System.out.println(contact);

		if (contact != null) {
			addressBook.remove(contact);
			System.out.println("Contact removed from the address book successfully");
		} else {
			System.out.println("Sorry, no such contact exist with that name!");
		}
	}

	public static void main(String[] args) {

		AddressBookMain addressBookMain = new AddressBookMain();

		System.out.println("Enter the number of contacts you add into the address book: ");
		int numberOfContacts = SC.nextInt();

		for (int i = 0; i < numberOfContacts; i++) {
			System.out.println("Enter the contact details \nfirst name:");
			String firstName = SC.next();
			System.out.println("last name:");
			String lastName = SC.next();
			SC.nextLine();
			System.out.println("Address: ");
			String address = SC.nextLine();
			System.out.println("City: ");
			String city = SC.nextLine();
			System.out.println("State: ");
			String state = SC.nextLine();
			System.out.println("Zip: ");
			long zip = SC.nextLong();
			System.out.println("Phone Number: ");
			long phoneNumber = SC.nextLong();
			System.out.println("Email Address: ");
			String email = SC.next();

			Contact contact = new Contact(firstName, lastName, address, city, state, email, zip, phoneNumber);
			System.out.println(contact);
			addressBookMain.addContact(contact);
		}

		System.out.println(addressBook);

	}

}
