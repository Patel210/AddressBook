package com.capgemini.addressbook;

public class AddressBookMain {

	private Contact[] addressBook;
	private int noOfContacts = 0;

	public AddressBookMain() {
		super();
		addressBook = new Contact[5];
	}

	public Contact[] getContacts() {
		return addressBook;
	}

	public void setContacts(Contact[] contacts) {
		this.addressBook = contacts;
	}

	public void addContact(String firstName, String lastName, String address, String city, String state, String email,
			long zip, long phoneNumber) {
		addressBook[noOfContacts] = new Contact(firstName, lastName, address, city, state, email, zip, phoneNumber);
		System.out.println("Contact added successfully!");
		noOfContacts++;
	}

	public static void main(String[] args) {
		AddressBookMain addressBookMain = new AddressBookMain();
		addressBookMain.addContact("Tom", "Harry", "14, Brooklyn Heights", "New York City", "New York",
				"tom.harry@yahoo.com", 11208, 1002359753);
	}
}
