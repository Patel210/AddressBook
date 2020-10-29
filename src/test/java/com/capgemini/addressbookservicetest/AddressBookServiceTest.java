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
	
	@Test
	public void givenNewPhoneNumberForAContact_WhenUpdated_ShouldBeUpdatedInTheDB() {
		AddressBookService addressBookService = new AddressBookService();
		addressBookService.readAddressBook();
		addressBookService.updateContactPhoneNumber(1, 9876543210L);
		boolean result = addressBookService.isContactInSyncWithDB(2);
		assertTrue(result);
	}

}
