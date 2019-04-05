package com.abim.addressbook.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.abim.addressbook.model.AddressRecord;

@Service("addressRecordService") 
public class AddressRecordServiceImpl implements AddressRecordService{

	private static ArrayList<AddressRecord> addressBook = new ArrayList<AddressRecord>();
	private static AtomicInteger generateIdCounter  = new AtomicInteger();

	static {
		addressBook = addInitialAddressRecords();
	}
	
	@Override
	public boolean checkIfAddressRecordExists(AddressRecord addressRecord) {
		if(addressBook.contains(addressRecord)) {
			return true;
		}
		return false;
	}
	
	@Override
	public void createAddressRecord(AddressRecord addressRecord) {
		addressRecord.setId(generateIdCounter.incrementAndGet());
		addressBook.add(addressRecord);
	}

	@Override
	public AddressRecord getAddressRecordById(int id) {
		for(AddressRecord currentRecord : addressBook) {
			if(currentRecord.getId() == id) {
				return currentRecord;
			}
		}
		return null;
	}

	@Override
	public AddressRecord getAddressRecordByFirstName(String firstName) {
		for(AddressRecord currentRecord : addressBook) {
			if(currentRecord.getFirstName().equalsIgnoreCase(firstName)) {
				return currentRecord;
			}
		}
		return null;
	}

	@Override
	public ArrayList<AddressRecord> retriveAddressBook() {
		return addressBook;
	}

	@Override
	public void updateAddressRecord(AddressRecord addressRecord) {
		int index = addressBook.indexOf(addressRecord);
		addressBook.set(index, addressRecord);
	}

	@Override
	public void deleteAddressRecordById(int id) {
		Iterator<AddressRecord> iterator = addressBook.iterator();
		AddressRecord currentRecord;
		while(iterator.hasNext()) {
			currentRecord = iterator.next();
			if(currentRecord.getId() == id) {
				addressBook.remove(currentRecord);
			}
		}
	}

	@Override
	public void deleteAddressBook() {
		addressBook.clear();
	}
	
	private static ArrayList<AddressRecord> addInitialAddressRecords() {
		ArrayList<AddressRecord> records = new ArrayList<AddressRecord>();
		
		records.add(new AddressRecord(generateIdCounter.incrementAndGet(), "Abi", "Ganesh", "123", "Dublin", "ag@gmail.com"));
		records.add(new AddressRecord(generateIdCounter.incrementAndGet(), "Ganesh", "Abi", "321", "Dublin", "ga@gmail.com"));
		
		return records;
	}

}
