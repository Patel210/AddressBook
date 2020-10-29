package com.capgemini.addressbookservicetest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.LinkedList;

import org.junit.Test;

import com.capgemini.addressbookservice.AddressBookService;
import com.capgemini.addressbookservice.AddressBookService.IOTYPE;
import com.capgemini.pojo.Contact;

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
	
	@Test
	public void givenDateRange_WhenRetrievedFromDB_ShouldMatchTotalEntries() {
		AddressBookService addressBookService = new AddressBookService();
		addressBookService.readAddressBook();
		LocalDate startDate = LocalDate.of(2019, 01, 01);
		LocalDate endDate = LocalDate.now();
		LinkedList<Contact> contactInGivenDateRange = addressBookService.readContactForDateRange(IOTYPE.DB_IO, startDate, endDate); 
		assertEquals(3, contactInGivenDateRange.size());
	}

}
