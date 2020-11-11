package com.BillsToCoins.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BillsToCoins.Entity.Bill;
import com.BillsToCoins.exception.BillsToCoinsException;
import com.BillsToCoins.service.BillsToCoinsService;
import com.BillsToCoins.utility.Validations;

@RestController
@RequestMapping("/billsToCoins")
public class BillstoCoinsController {
	
	@Autowired
	BillsToCoinsService billsToCoinsService;
	
	@PostMapping("/LeastAmountOfCoins")
	public Map<String,Integer> billsToCoinsLeast(@RequestParam("bill") int bill) throws Exception {
		Map<String, Integer> map=billsToCoinsService.calculateLeastAmountOfCoins(new Bill(bill));
		return map;
	}
	
	@PostMapping("/MostAmountOfCoins")
	public Map<String,Integer> billsToCoinsMost(@RequestParam("bill") int bill) throws Exception {
		Map<String, Integer> map=billsToCoinsService.calculateMostAmountOfCoins(new Bill(bill));
		return map;		
	}
	
	@ExceptionHandler(value = BillsToCoinsException.class)
	   public ResponseEntity<Object> exception(BillsToCoinsException exception) {
	      return new ResponseEntity<>("Not Enough Coins", HttpStatus.OK);
	   }
	
	@ExceptionHandler(value = NumberFormatException.class)
	   public ResponseEntity<Object> exception(NumberFormatException exception) {
	      return new ResponseEntity<>("Wrong Bill Value", HttpStatus.BAD_REQUEST);
	   }


}
