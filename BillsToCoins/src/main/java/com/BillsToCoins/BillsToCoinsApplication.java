package com.BillsToCoins;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.BillsToCoins.Entity.Bill;
import com.BillsToCoins.exception.BillsToCoinsException;
import com.BillsToCoins.service.BillsToCoinsService;
import com.BillsToCoins.utility.Validations;

@SpringBootApplication
public class BillsToCoinsApplication {
	
	   private static final Logger logger = LoggerFactory.getLogger(BillsToCoinsApplication.class);
	   private static boolean validState = true;
	   
	   	   
	public static void main(String[] args) throws Exception {
		
		SpringApplication.run(BillsToCoinsApplication.class, args);
		BillsToCoinsService billsToCoinsService=new BillsToCoinsService();
		
		Scanner input = new Scanner(System.in);
		while (isValidState()) {

			System.out.println("Bills to Coins Program");
			System.out.println("----------------------");
			System.out.println("Option 1: Configure the number of coins");
			System.out.println("Option 2: Get the Change for the Bill (Least amount of coins) ");
			System.out.println("Option 3: Get the Change for the Bill (Most amount of coins) ");
			System.out.println("Option 4: Exit \n");
			System.out.println("Please enter a option!");

			while (!input.hasNext("[1234]")) {
				logger.info("That's not a option!");
				input.next();
			}
			int option = input.nextInt();
			switch (option) {

			case 1:
				
				billsToCoinsService.displayConfiguredCoins();
				int quarterInput;
				do {
				    System.out.println("Enter Quarters in System. Please enter a positive number!");
				    quarterInput = validateInput(input);
				} while (quarterInput <= 0);
				BillsToCoinsService.setQuarterCoins_InSystem(quarterInput);

				int dimesInput;
				do {
				    System.out.println("Enter Dimes in System. Please enter a positive number!");
				    dimesInput = validateInput(input);
				} while (dimesInput <= 0);
				BillsToCoinsService.setDimesCoins_InSystem(dimesInput);

				int nickelInput;
				do {
				    System.out.println("Enter Nickel in System. Please enter a positive number!");
				    nickelInput = validateInput(input);
				} while (nickelInput <= 0);
				BillsToCoinsService.setNickelsCoins_InSystem(nickelInput);
				
				int penniesInput;
				do {
				    System.out.println("Enter Pennies in System. Please enter a positive number!");
				    penniesInput = validateInput(input);
				} while (penniesInput <= 0);
				BillsToCoinsService.setPenniesCoins_InSystem(penniesInput);
				billsToCoinsService.displayConfiguredCoins();

				break;

			case 2:
				System.out.println("Enter bill Amount for Change. Accepted Values are (1,2,5,10,20,50,100)\n");
				try {
					int dollars = validateInput(input);
					if (Validations.validateBillValue(dollars)) {
						Bill bill=new Bill(dollars);
						billsToCoinsService.calculateLeastAmountOfCoins(bill);
					}
				} catch (BillsToCoinsException e) {
					System.out.println(e.getMessage());
				}

				break;

			case 3:
				System.out.println("Enter bill Amount for Change. Accepted Values are (1,2,5,10,20,50,100)\n");
				try {
					int dollars = validateInput(input);
					if (Validations.validateBillValue(dollars)) {
						Bill bill=new Bill(dollars);
						billsToCoinsService.calculateMostAmountOfCoins(bill);
					}
				} catch (BillsToCoinsException e) {
					System.out.println(e.getMessage());
				}

				break;

			case 4:
				validState = false;
				logger.info("Exit");
				break;

			}

		}
		input.close();
	
	}

	private static boolean isValidState() {
		return validState;

	}
	
	private static int validateInput(Scanner input) {
		 while (!input.hasNextInt()) {
		        logger.info("That's not a number!");
		        input.next();
		    }
		 int inputNum=input.nextInt();
		 return inputNum;
	}


}
