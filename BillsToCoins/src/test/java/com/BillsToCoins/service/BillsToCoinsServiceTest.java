package com.BillsToCoins.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.BillsToCoins.Entity.Bill;

class BillsToCoinsServiceTest {

	BillsToCoinsService billsToCoinsService;

	@Test
	void testLeastAmountOfCoinsForBillAmountOne() {
		billsToCoinsService = new BillsToCoinsService();
		billsToCoinsService.calculateLeastAmountOfCoins(new Bill(1));
		Assertions.assertEquals(billsToCoinsService.getThirtyFourCoins_InSystem(), 98);
		Assertions.assertEquals(billsToCoinsService.getThirtyThreeCoins_InSystem(), 100);
		Assertions.assertEquals(billsToCoinsService.getQuarterCoins_InSystem(), 99);
		Assertions.assertEquals(billsToCoinsService.getDimesCoins_InSystem(), 100);
		Assertions.assertEquals(billsToCoinsService.getNickelsCoins_InSystem(), 99);
		Assertions.assertEquals(billsToCoinsService.getPenniesCoins_InSystem(), 0);


	}

	@Test
	void testMostAmountOfCoinsForBillAmountOne() {
		billsToCoinsService = new BillsToCoinsService();
		billsToCoinsService.calculateMostAmountOfCoins(new Bill(1));
		Assertions.assertEquals(billsToCoinsService.getThirtyFourCoins_InSystem(), 100);
		Assertions.assertEquals(billsToCoinsService.getThirtyThreeCoins_InSystem(), 100);
		Assertions.assertEquals(billsToCoinsService.getQuarterCoins_InSystem(), 100);
		Assertions.assertEquals(billsToCoinsService.getDimesCoins_InSystem(), 100);
		Assertions.assertEquals(billsToCoinsService.getNickelsCoins_InSystem(), 100);
		Assertions.assertEquals(billsToCoinsService.getPenniesCoins_InSystem(), 0);
	}

}
