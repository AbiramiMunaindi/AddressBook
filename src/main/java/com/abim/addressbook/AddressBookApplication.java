package com.abim.addressbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.abim.addressbook.controller.AddressBookController;

@SpringBootApplication(scanBasePackages= {"com.abim.addressbook"})
public class AddressBookApplication{

	public static void main(String[] args) {
		SpringApplication.run(AddressBookApplication.class, args);
		AddressBookController controller= new AddressBookController();
		controller.printme();
	}
	
	
}
