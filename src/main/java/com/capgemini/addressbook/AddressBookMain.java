package com.capgemini.addressbook;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class AddressBookMain {

	private static Map<String, List<Contact>> addressBooks = new HashMap<String, List<Contact>>();
	private static final Scanner SC = new Scanner(System.in);

	public AddressBookMain() {
		super();
	}

	/**
	 * To add contact to the address book
	 */
	public void addAddressBook(String addressBookName, List<Contact> addressBook) {
		addressBooks.put(addressBookName, addressBook);
		System.out.println("Address Book added Successfully!");
	}

	/**
	 * To check if Address book by particular name exists or not
	 */
	public boolean isAddressBookByThatNameExists(String addressBookName) {
		boolean isAddressBookByThatNameExistsboolean = addressBooks.entrySet().stream()
				.filter(a -> a.getKey().equalsIgnoreCase(addressBookName)).findAny() != null;
		return isAddressBookByThatNameExistsboolean;
	}

	/**
	 * To add contact in particular address book
	 */
	public void addContactToParticularAddressBook(String addressBookName, Contact contact) {
		boolean isAddressBookByThatNameExists = isAddressBookByThatNameExists(addressBookName);

		Predicate<Contact> predicate = contactObj -> contactObj.equals(contact);

		if (isAddressBookByThatNameExists) {
			boolean isSame = addressBooks.get(addressBookName).stream().anyMatch(predicate);
			if (!isSame) {
				addressBooks.get(addressBookName).add(contact);
				System.out.println("Contact added successfully!");
			} else {
				System.out.println("Contact by that name already exists in the particular address book! Try again!");
			}
		} else {
			System.out.println(
					"Address Book by that Name does not exist! Do you want to create new addressbook by this name? (Y/N");
			String option = SC.next();
			if (option.equalsIgnoreCase("y")) {
				List<Contact> addressBook = new LinkedList<Contact>();
				addressBook.add(contact);
				addAddressBook(addressBookName, addressBook);
			}
		}
	}

	/**
	 * To create Contact
	 */
	public Contact createContact() {

		System.out.println("Enter the details to add the contact \nfirst name:");
		String firstName = SC.next();
		System.out.println("last name:");
		String lastName = SC.next();
		System.out.println("Address: ");
		SC.nextLine();
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

		Contact contactObject = new Contact(firstName, lastName, address, city, state, email, zip, phoneNumber);

		return contactObject;

	}

	/**
	 * To get the contact from address book using first and last name
	 */
	public Contact getContact(String firstName, String lastName) {
		Contact contact = null;
		for (Map.Entry<String, List<Contact>> entry : addressBooks.entrySet()) {
			for (Contact contactObject : entry.getValue()) {
				if (contactObject.getFirstName().equalsIgnoreCase(firstName)
						&& contactObject.getLastName().equalsIgnoreCase(lastName)) {
					contact = contactObject;
					break;
				}

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
		boolean flag = false;
		for (Map.Entry<String, List<Contact>> entry : addressBooks.entrySet()) {
			for (Contact contactObject : entry.getValue()) {
				if (contactObject.getFirstName().equalsIgnoreCase(firstName)
						&& contactObject.getLastName().equalsIgnoreCase(lastName)) {
					entry.getValue().remove(contactObject);
					flag = true;
					System.out.println("Contact removed from the address book successfully");
					break;
				}
			}
		}
		if (!flag) {
			System.out.println("Sorry, no such contact exist with that name!");
		}
	}

	/**
	 * To get the list of contacts in particular state
	 */
	public List<Contact> listOfContactsInParticularState(String state) {

		List<Contact> contactInParticularState = addressBooks.entrySet().stream()
				.flatMap(entry -> entry.getValue().stream()).filter(contact -> contact.getState().equals(state))
				.collect(Collectors.toList());

		if (contactInParticularState.size() == 0) {
			System.out.println("No contact exist in particular state");
		}
		return contactInParticularState;

	}
	
	/**
	 * To get the list of contacts in particular city
	 */
	public List<Contact> listOfContactsInParticularCity(String city) {
		List<Contact> contactInParticularCity = addressBooks.entrySet().stream()
				.flatMap(entry -> entry.getValue().stream()).filter(contact -> contact.getCity().equals(city))
				.collect(Collectors.toList());

		if (contactInParticularCity.size() == 0) {
			System.out.println("No contact exist in particular city");
		}

		return contactInParticularCity;

	}

	public static void main(String[] args) {

		AddressBookMain addressBookMain = new AddressBookMain();
		System.out.println("Enter the number of address book you want in the system: ");
		int noOfAddressBooks = SC.nextInt();

		for (int i = 0; i < noOfAddressBooks; i++) {
			List<Contact> addressBook = new LinkedList<Contact>();
			System.out.println("Number of contacts to add in this addressbook : ");
			int noOfContacts = SC.nextInt();
			for (int j = 0; j < noOfContacts; j++) {
				addressBook.add(addressBookMain.createContact());
			}
			System.out.println("Enter the address book name:");
			String addressBookName = SC.next();
			addressBookMain.addAddressBook(addressBookName, addressBook);
			System.out.println(addressBooks);
		}
	}
}
