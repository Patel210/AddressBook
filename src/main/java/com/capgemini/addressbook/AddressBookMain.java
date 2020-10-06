package com.capgemini.addressbook;

import java.util.Scanner;

public class AddressBookMain {

	private Contact[] addressBook;
	private int noOfContacts = 0;

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

	public void addContact(String firstName, String lastName, String address, String city, String state, String email,
			long zip, long phoneNumber) {
		addressBook[noOfContacts] = new Contact(firstName, lastName, address, city, state, email, zip, phoneNumber);
		System.out.println("Contact added successfully!");
		noOfContacts++;
	}

	public static void main(String[] args) {
		AddressBookMain addressBookMain = new AddressBookMain();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of contacts you add into the address book (Max limit is 50): ");
		int numberOfContacts = sc.nextInt();
		for (int i = 0; i < numberOfContacts; i++) {
			System.out.println("Enter the contact details \nfirst name:");
			String firstName = sc.next();
			System.out.println("last name:");
			String lastName = sc.next();
			System.out.println("Address: ");
			sc.nextLine();
			String address = sc.nextLine();
			System.out.println("City: ");
			String city = sc.nextLine();
			System.out.println("State: ");
			String state = sc.nextLine();
			System.out.println("Zip: ");
			long zip = sc.nextLong();
			System.out.println("Phone Number: ");
			long phoneNumber = sc.nextLong();
			System.out.println("Email Address: ");
			String email = sc.next();
			sc.close();
			addressBookMain.addContact(firstName, lastName, address, city, state, email, zip, phoneNumber);

		}

	}
}
