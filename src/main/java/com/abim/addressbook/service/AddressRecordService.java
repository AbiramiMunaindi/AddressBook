package com.abim.addressbook.service;

import java.util.ArrayList;

import com.abim.addressbook.model.AddressRecord;

public interface AddressRecordService {

	boolean checkIfAddressRecordExists(AddressRecord addressRecord);
	
	void createAddressRecord(AddressRecord addressRecord);
	
	AddressRecord getAddressRecordById(int id);
	AddressRecord getAddressRecordByFirstName(String firstName);
	
	ArrayList<AddressRecord> retriveAddressBook();
	
	void updateAddressRecord(AddressRecord addressRecord);
	
	void deleteAddressRecordById(int id);
	void deleteAddressBook();
	
}
