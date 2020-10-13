package com.capgemini.addressbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@FunctionalInterface
interface AddingKVpair {
	void addKVPair(String key, LinkedList<Contact> value);
}

public class AddressBookMain {

	private static Map<String, List<Contact>> addressBooks = new HashMap<String, List<Contact>>();
	private static final Scanner SC = new Scanner(System.in);

	public AddressBookMain() {
		super();
	}

	/**
	 * To add contact to the address book
	 */
	public void addAddressBook(String addressBookName) {
		List<Contact> addressBook = new LinkedList<Contact>();
		addressBooks.put(addressBookName, addressBook);
		System.out.println("Address Book added Successfully!");
	}

	/**
	 * To check if Address book by particular name exists or not
	 */
	public boolean isAddressBookByThatNameExists(String addressBookName) {
		int count = (int) addressBooks.entrySet().stream().filter(a -> a.getKey().equalsIgnoreCase(addressBookName))
				.count();
		return (count == 1) ? true : false;
	}

	/**
	 * To add contact in particular address book
	 */
	public void addContactToParticularAddressBook(String addressBookName) {
		Contact contact = createContact();
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
				addAddressBook(addressBookName);
				addressBooks.get(addressBookName).add(contact);
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

		LinkedList<Contact> contactInParticularState = (LinkedList<Contact>) addressBooks.entrySet().stream()
				.flatMap(entry -> entry.getValue().stream()).filter(contact -> contact.getState().equals(state))
				.collect(Collectors.toCollection(LinkedList::new));

		if (contactInParticularState.size() == 0) {
			System.out.println("No contact exist in particular state");
		}
		return contactInParticularState;

	}

	/**
	 * To get the list of contacts in particular city
	 */
	public List<Contact> listOfContactsInParticularCity(String city) {

		LinkedList<Contact> contactInParticularCity = (LinkedList<Contact>) addressBooks.entrySet().stream()
				.flatMap(entry -> entry.getValue().stream()).filter(contact -> contact.getCity().equals(city))
				.collect(Collectors.toCollection(LinkedList::new));

		if (contactInParticularCity.size() == 0) {
			System.out.println("No contact exist in particular city");
		}

		return contactInParticularCity;

	}

	/**
	 * To get contacts by city
	 */
	public Map<String, LinkedList<Contact>> addressBookByCity() {
		Map<String, LinkedList<Contact>> contactByCities = new HashMap<String, LinkedList<Contact>>();
		Function<String, LinkedList<Contact>> cityToContactsInThatCity = str -> (LinkedList<Contact>) listOfContactsInParticularCity(
				str);
		AddingKVpair addingKVPair = (x, y) -> contactByCities.put(x, y);
		listOfAllCities().forEach(str -> addingKVPair.addKVPair(str, cityToContactsInThatCity.apply(str)));

		return contactByCities;
	}

	/**
	 * To get contacts by state
	 */
	public Map<String, LinkedList<Contact>> addressBookByState() {
		Map<String, LinkedList<Contact>> contactByStates = new HashMap<String, LinkedList<Contact>>();
		Function<String, LinkedList<Contact>> stateToContactsInThatState = str -> (LinkedList<Contact>) listOfContactsInParticularState(
				str);
		AddingKVpair addingKVPair = (x, y) -> contactByStates.put(x, y);
		listOfAllCities().forEach(str -> addingKVPair.addKVPair(str, stateToContactsInThatState.apply(str)));

		return contactByStates;
	}

	/**
	 * To get the list of city based on all contacts in tha address books
	 */
	public List<String> listOfAllCities() {
		Function<Contact, String> togetCityValue = contact -> contact.getCity();
		List<String> cities = addressBooks.entrySet().stream().flatMap(entry -> entry.getValue().stream())
				.map(togetCityValue).collect(Collectors.toList());
		return cities;
	}

	/**
	 * To get the list of state based on all contacts in tha address books
	 */
	public List<String> listOfAllStates() {
		Function<Contact, String> togetStateValue = contact -> contact.getState();
		List<String> states = addressBooks.entrySet().stream().flatMap(entry -> entry.getValue().stream())
				.map(togetStateValue).collect(Collectors.toList());
		return states;
	}

	/**
	 * To get contact count by city
	 */
	public Map<String, Integer> contactCountByCity() {
		Map<String, Integer> countByCities = new HashMap<String, Integer>();
		Function<String, Integer> countByCity = str -> (Integer) listOfContactsInParticularCity(str).size();
		listOfAllCities().stream().forEach(str -> countByCities.put(str, countByCity.apply(str)));

		return countByCities;
	}

	/**
	 * To get contact count by city
	 */
	public Map<String, Integer> contactCountByState() {
		Map<String, Integer> countByStates = new HashMap<String, Integer>();
		Function<String, Integer> countByState = str -> listOfContactsInParticularState(str).size();
		listOfAllStates().stream().forEach(str -> countByStates.put(str, countByState.apply(str)));

		return countByStates;
	}

	/**
	 * To sort the entries in the address book by person's name
	 */
	public void sortAddressBookByPersonName(String addressBook) {

		boolean isAddressBookByThatNameExists = isAddressBookByThatNameExists(addressBook);
		if (isAddressBookByThatNameExists) {
			if (addressBooks.get(addressBook).size() == 0) {
				System.out.println("Sorry! There is no contact in this address book to sort!");
			} else {
				LinkedList<Contact> sortedAddressBook = addressBooks.get(addressBook).stream()
						.sorted(Comparator.comparing(Contact::getFirstName))
						.collect(Collectors.toCollection(LinkedList::new));

				addressBooks.replace(addressBook, sortedAddressBook);
				System.out.println(addressBooks);
			}
		} else {
			System.out.println("There is no address book by this name in the address books");
		}
	}

	/**
	 * To sort the entries in the address book by city
	 */
	public void sortAddressBookByCity(String addressBook) {

		boolean isAddressBookByThatNameExists = isAddressBookByThatNameExists(addressBook);
		if (isAddressBookByThatNameExists) {
			if (addressBooks.get(addressBook).size() == 0) {
				System.out.println("Sorry! There is no contact in this address book to sort!");
			} else {
				LinkedList<Contact> sortedAddressBook = addressBooks.get(addressBook).stream()
						.sorted(Comparator.comparing(Contact::getCity))
						.collect(Collectors.toCollection(LinkedList::new));

				addressBooks.replace(addressBook, sortedAddressBook);
				System.out.println(addressBooks.get(addressBook));
			}
		} else {
			System.out.println("There is no address book by this name in the address books");
		}
	}

	/**
	 * To sort the entries in the address book by state
	 */
	public void sortAddressBookByState(String addressBook) {

		boolean isAddressBookByThatNameExists = isAddressBookByThatNameExists(addressBook);
		if (isAddressBookByThatNameExists) {
			if (addressBooks.get(addressBook).size() == 0) {
				System.out.println("Sorry! There is no contact in this address book to sort!");
			} else {
				LinkedList<Contact> sortedAddressBook = addressBooks.get(addressBook).stream()
						.sorted(Comparator.comparing(Contact::getState))
						.collect(Collectors.toCollection(LinkedList::new));

				addressBooks.replace(addressBook, sortedAddressBook);
				System.out.println(addressBooks.get(addressBook));
			}
		} else {
			System.out.println("There is no address book by this name in the address books");
		}
	}

	/**
	 * To sort the entries in the address book by zip
	 */
	public void sortAddressBookByZip(String addressBook) {

		boolean isAddressBookByThatNameExists = isAddressBookByThatNameExists(addressBook);
		if (isAddressBookByThatNameExists) {
			if (addressBooks.get(addressBook).size() == 0) {
				System.out.println("Sorry! There is no contact in this address book to sort!");
			} else {
				LinkedList<Contact> sortedAddressBook = addressBooks.get(addressBook).stream()
						.sorted(Comparator.comparing(Contact::getZip))
						.collect(Collectors.toCollection(LinkedList::new));

				addressBooks.replace(addressBook, sortedAddressBook);
				System.out.println(addressBooks.get(addressBook));
			}
		} else {
			System.out.println("There is no address book by this name in the address books");
		}
	}

	/*
	 * Writes the address book in a file
	 */
	public void writeAddressBookToFile(String addressBookName) {
		boolean isAddressBookByThatNameExists = isAddressBookByThatNameExists(addressBookName);
		if (isAddressBookByThatNameExists) {
			new AddressBookFileIO().writeAddressBookToFile(addressBookName,
					(LinkedList<Contact>) addressBooks.get(addressBookName));
		} else {
			System.out.println("Please enter the correct address book name!");
		}
	}

	/**
	 * Reads the file and adds contacts to the particular address book
	 */
	public void readContactsFromAFile(String fileName, String addressBookName) {
		File file = new File(fileName);
		if (!isAddressBookByThatNameExists(addressBookName)) {
			addAddressBook(addressBookName);
			addressBooks.put(addressBookName, new AddressBookFileIO().readContactsToAddressBooks(file));
		} else {
			System.out.println(
					"Address Book by that already exists! Do you want to overwrite the content of the address books (Y/N)?");
			if ('Y' == (SC.next().charAt(0))) {
				addressBooks.put(addressBookName, new AddressBookFileIO().readContactsToAddressBooks(file));
			}
		}
	}
}
