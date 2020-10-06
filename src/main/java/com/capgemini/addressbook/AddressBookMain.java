package com.capgemini.addressbook;

import java.util.Scanner;

public class AddressBookMain {

	private Contact[] addressBook;
	private int noOfContacts = 0;
	private static final Scanner SC = new Scanner(System.in);

	public AddressBookMain() {
		super();
		addressBook = new Contact[50];
	}

	public Contact[] getContacts() {
		return addressBook;
	}

	public void setContacts(Contact[] contacts) {
		this.addressBook = contacts;
	}

	/**
	 * To add contact to the address book
	 */
	public void addContact(String firstName, String lastName, String address, String city, String state, String email,
			long zip, long phoneNumber) {
		addressBook[noOfContacts] = new Contact(firstName, lastName, address, city, state, email, zip, phoneNumber);
		System.out.println("Contact added successfully!");
		noOfContacts++;
	}

	/**
	 * To get the contact from address book using first and last name
	 */
	public Contact getContact(String firstName, String lastName) {
		Contact contact = null;
		for (int i = 0; i < noOfContacts; i++) {
			if (addressBook[i].getFirstName().equalsIgnoreCase(firstName)
					&& addressBook[i].getLastName().equalsIgnoreCase(lastName)) {
				contact = addressBook[i];
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
			Contact[] copyOfAddressBook = new Contact[addressBook.length];
			for (int i = 0; i < addressBook.length; i++) {
				if (addressBook[i] != (contact)) {
					copyOfAddressBook[i] = addressBook[i];
				}
			}

			addressBook = copyOfAddressBook;
			System.out.println("Contact removed from the address book successfully");
		} else {
			System.out.println("Sorry, no such contact exist with that name!");
		}
	}

	public static void main(String[] args) {

		AddressBookMain addressBookMain = new AddressBookMain();
		addressBookMain.addContact("Tom", "Harry", "14, Brooklyn Heights", "New York City", "New York",
				"tom.harry@yahoo.com", 11208, 1002359753);
		addressBookMain.addContact("Peter", "Clark", "18, Brooklyn Heights", "New York City", "New York",
				"peter.clark@yahoo.com", 11208, 1002359896);

		addressBookMain.editContact("Tom", "Harry");
		addressBookMain.removeContact("Peter", "Clark");

	}

}
