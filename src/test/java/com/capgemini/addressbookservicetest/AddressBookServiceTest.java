package com.capgemini.addressbookservicetest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.addressbookservice.AddressBookService;

public class AddressBookServiceTest {

	@Test
	public void givenAddressBookDB_WhenRetrieved_ShouldMatchTotalEntries() {
		AddressBookService addressBookService = new AddressBookService();
		int enteries = addressBookService.readAddressBook();
		assertEquals(7, enteries);
	}

}
