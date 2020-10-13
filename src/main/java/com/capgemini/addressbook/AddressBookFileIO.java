package com.capgemini.addressbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class AddressBookFileIO {
	private static final String HOME = System.getProperty("user.home");
	private static String WORK_SPACE = "\\eclipse-workspace\\AddressBook";

	public AddressBookFileIO() {
	}

	/**
	 * Writes the contacts to a file
	 */
	public void writeAddressBookToFile(String addressBookName, LinkedList<Contact> contactList) {
		try {
			Path workPath = Paths.get(HOME + WORK_SPACE + "\\OutputDirectory");
			if (Files.notExists(workPath)) {
				Files.createDirectories(workPath);
			}
			Path tempPath = Paths.get(workPath + "\\" + addressBookName + "--contacts.txt");
			if (Files.notExists(tempPath)) {
				Files.createFile(tempPath);
			}
			StringBuffer contactBuffer = new StringBuffer();
			contactList.stream().forEach(contact -> {
				String contactDataString = contact.toString().concat("\n");
				contactBuffer.append(contactDataString);
			});
			Files.write(tempPath, contactBuffer.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads the contacts from a file
	 */
	@SuppressWarnings("finally")
	public LinkedList<Contact> readContactsToAddressBooks(File file) {
		Path path = Paths.get(HOME + WORK_SPACE + "\\OutputDirectory\\" + file);
		LinkedList<Contact> contactList = new LinkedList<Contact>();
		try {
			BufferedReader reader = Files.newBufferedReader(path);
			String currentLine = null;
			while ((currentLine = reader.readLine()) != null) {
				String[] contactDetails = currentLine.trim().split("[,\\s]{0,}[a-zA-Z]+[=]{1}");
				contactList.add(new Contact(contactDetails[1], contactDetails[2], contactDetails[3], contactDetails[4],
						contactDetails[5], contactDetails[6], Long.parseLong(contactDetails[7]),
						Long.parseLong(contactDetails[8])));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return contactList;
		}
	}
}
