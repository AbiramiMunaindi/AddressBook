package com.abim.addressbook.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abim.addressbook.model.AddressRecord;
import com.abim.addressbook.service.AddressRecordService;
import com.abim.addressbook.util.ErrorMessage;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

	public static final Logger LOGGER = LoggerFactory.getLogger(AddressBookController.class);

	AddressRecordService addressRecordService;
	
	public void printme() {
		System.out.println("printing controller");
	}

	//create an address record
	@RequestMapping(value = "/entry/", method = RequestMethod.POST)
	public ResponseEntity<?> createEntry(@RequestBody AddressRecord addressRecord) {
		LOGGER.info("Creating entry in the address book : {}", addressRecord);

		if (addressRecordService.checkIfAddressRecordExists(addressRecord)){
			LOGGER.error("Unable to create an entry. Address record already exists");
			return new ResponseEntity<>(new ErrorMessage("Unable to create an entry in the address book. Entry already exists"), HttpStatus.CONFLICT);
		}

		addressRecordService.createAddressRecord(addressRecord);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	//get address record by id
	@RequestMapping(value = "/entry/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getEntryById(@PathVariable("id") int id) {
		LOGGER.info("Retrieving entry in the address book using id : {}", id);

		AddressRecord addressRecord = addressRecordService.getAddressRecordById(id);
		if(addressRecord == null) {
			LOGGER.error("Address record with id {} does not exist in the address book", id);
			return new ResponseEntity<>(new ErrorMessage("Address record with id " + id + " not found in the address book"),
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<AddressRecord>(addressRecord, HttpStatus.OK);

	}

	//get address record by first name //returns the first record with the mentioned first name
	@RequestMapping(value = "/entry/{firstName}", method = RequestMethod.GET)
	public ResponseEntity<?> getEntryById(@PathVariable("firstName") String firstName) {
		LOGGER.info("Retrieving entry in the address book using first name : {}", firstName);

		AddressRecord addressRecord = addressRecordService.getAddressRecordByFirstName(firstName);
		if(addressRecord == null) {
			LOGGER.error("Address record with first name {} does not exist in the address book", firstName);
			return new ResponseEntity<>(new ErrorMessage("Address record with first name " + firstName + " not found in the address book"),
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<AddressRecord>(addressRecord, HttpStatus.OK);

	}

	//get address book
	@RequestMapping(value = "/addressbookentries/", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<AddressRecord>> retriveAddressBook() {
		LOGGER.info("Retrieving the address");

		ArrayList<AddressRecord> addressBook = addressRecordService.retriveAddressBook();
		
		if(addressBook.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<ArrayList<AddressRecord>>(addressBook, HttpStatus.OK);

	}
	
	//update an address record
	@RequestMapping(value = "/entry/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateEntry(@PathVariable("id") int id, @RequestBody AddressRecord addressRecord) {
		LOGGER.info("Creating entry in the address book : {}", addressRecord);

		AddressRecord record = addressRecordService.getAddressRecordById(id);
		if(record == null) {
			LOGGER.error("Unable to update the entry. Address record does not exist");
			return new ResponseEntity<>(new ErrorMessage("Unable to update the entry in the address book. Entry does not exist"), HttpStatus.NOT_FOUND);

		}
		
		record.setFirstName(addressRecord.getFirstName());
		record.setLastName(addressRecord.getLastName());
		record.setPhoneNumber(addressRecord.getPhoneNumber());
		record.setAddress(addressRecord.getAddress());
		record.setEmailAddres(addressRecord.getEmailAddres());
		
		addressRecordService.updateAddressRecord(record);
		
		return new ResponseEntity<AddressRecord>(record, HttpStatus.OK);
	}
	
	//delete an entry in the address book
	@RequestMapping(value = "/entry/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEntry(@PathVariable("id") int id) {
		LOGGER.info("Delete entry in the address book with id: {}", id);

		AddressRecord record = addressRecordService.getAddressRecordById(id);
		if(record == null) {
			LOGGER.error("Unable to delete the entry. Address record does not exist");
			return new ResponseEntity<>(new ErrorMessage("Unable to delete the entry in the address book. Entry does not exist"), HttpStatus.NOT_FOUND);

		}
		
		addressRecordService.deleteAddressRecordById(id);
		
		return new ResponseEntity<AddressRecord>(HttpStatus.NO_CONTENT);
	}
	
	//delete the address book
	@RequestMapping(value = "/entry/", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAddressBook() {
		
		LOGGER.info("Delete the address book");

		addressRecordService.deleteAddressBook();
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
