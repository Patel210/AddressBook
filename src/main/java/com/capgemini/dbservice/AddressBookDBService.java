package com.capgemini.dbservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.capgemini.exceptions.DatabaseException;
import com.capgemini.exceptions.DatabaseException.ExceptionType;
import com.capgemini.pojo.AddressBook;
import com.capgemini.pojo.AddressBook.TYPE;
import com.capgemini.pojo.Contact;

public class AddressBookDBService {
	
	/**
	 * To get connection object
	 */
	private Connection getConnection() throws DatabaseException {
		String jdbcURL = "jdbc:mysql://localhost:3306/addressbook_service";
		String user = "root";
		String password = "Gratitudelog1";
		Connection connection;
		try {
			connection = DriverManager.getConnection(jdbcURL, user, password);
			System.out.println("Connection successfully established!" + connection);
		} catch (SQLException e) {
			throw new DatabaseException("Unable to connect to the database", ExceptionType.UNABLE_TO_CONNECT);
		}
		return connection;
	}

	/**
	 * Reads address Book from DB
	 */
	public LinkedList<AddressBook> readAddressBook() throws DatabaseException {
		String query = "SELECT * FROM address_book";
		LinkedList<AddressBook> addressBooks = getAddressBooks(query);
		addressBooks.forEach(addressBook -> {
			try {
				addressBook.getContacts().addAll(getContacts(addressBook.getId()));
			} catch (DatabaseException e) {
				System.out.println(e.getMessage());
			}
		});
		return addressBooks;
	}

	/**
	 * Returns list of contact from particular address book with given book id
	 */
	private LinkedList<Contact> getContacts(int book_id) throws DatabaseException {
		String query = "select * from contact inner join contact_address on contact.contact_id = contact_address.contact_id "+
				 "where contact.contact_id in (select contact_id from address_book_contact where book_id = ?);";
		LinkedList<Contact> list = new LinkedList<Contact>();
		try(Connection connection = getConnection()){
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, book_id);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				int id = result.getInt("contact_id");
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String address = result.getString("address");
				String city = result.getString("city");
				String state = result.getString("state");
				long zip = result.getLong("zip");
				long phoneNumber = result.getLong("phone_number1");
				String email = result.getString("email");
				list.add(new Contact(id, firstName, lastName, address, city, state, email, zip, phoneNumber));
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while executing the query", ExceptionType.UNABLE_TO_EXECUTE_QUERY);
		}
		return list;
	}

	/**
	 * Returns all the address books from the DB
	 */
	private LinkedList<AddressBook> getAddressBooks(String query) throws DatabaseException {
		LinkedList<AddressBook> list = new LinkedList<AddressBook>();
		try (Connection connection = getConnection();) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				int id = resultSet.getInt("book_id");
				String name = resultSet.getString("name");
				String type = resultSet.getString("type");
				if(type.equalsIgnoreCase("family")) {
					list.add(new AddressBook(id, name, TYPE.FAMILY));
				} 
				if(type.equalsIgnoreCase("friend")) {
					list.add(new AddressBook(id, name, TYPE.FAMILY));
				}
				if(type.equalsIgnoreCase("profession")) {
					list.add(new AddressBook(id, name, TYPE.PROFESSION));
				}
			}
			return list;
		} catch (SQLException e) {
			throw new DatabaseException("Error while executing the query", ExceptionType.UNABLE_TO_EXECUTE_QUERY);
		}
	}
}
