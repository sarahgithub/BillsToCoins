package com.BillsToCoins.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.BillsToCoins.Entity.Bill;
import com.BillsToCoins.Entity.Coin;
import com.BillsToCoins.exception.BillsToCoinsException;
import com.BillsToCoins.utility.Constants;
import com.BillsToCoins.utility.Helper;

@Service
public class BillsToCoinsService {

	private static final Logger logger = LoggerFactory.getLogger(BillsToCoinsService.class);

	private static int thirtyFourCoins_InSystem = Constants.DEFAULT_CONFIGURED_COINS;
	private static int thirtyThreeCoins_InSystem = Constants.DEFAULT_CONFIGURED_COINS;
	private static int quarterCoins_InSystem = Constants.DEFAULT_CONFIGURED_COINS;
	private static int dimesCoins_InSystem = Constants.DEFAULT_CONFIGURED_COINS;
	private static int nickelsCoins_InSystem = Constants.DEFAULT_CONFIGURED_COINS;
	private static int penniesCoins_InSystem = Constants.DEFAULT_CONFIGURED_COINS;
	private int centsInSystem;

	public Map<String, Integer> calculateLeastAmountOfCoins(Bill bill) throws BillsToCoinsException {

		int centsLeft = Helper.convertDollartoCents(bill.getBillValue());

		logger.info("Bill Amount: " + bill.getBillValue() + " Cents Required:  " + centsLeft + " Cents in System:  "
				+ getCentsInSystem());

		if (!areThereEnoughCoins(centsLeft))
			throw new BillsToCoinsException("Not Enough Coins");

		for (Coin coinType : Coin.values()) {
			centsLeft = calculateValue(bill, coinType, centsLeft);
		}

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put(Coin.THIRTYFOUR.name(), bill.getNumThirtyFour());
		map.put(Coin.THIRTYTHREE.name(), bill.getNumThirtyThree());
		map.put(Coin.QUARTERS.name(), bill.getNumQuarters());
		map.put(Coin.DIMES.name(), bill.getNumDimes());
		map.put(Coin.NICKELS.name(), bill.getNumNickels());
		map.put(Coin.PENNIES.name(), bill.getNumPennies());
		bill.setCoinsMap(map);
		displayCoins(bill);

		return bill.getCoinsMap();

	}

	public Map<String, Integer> calculateMostAmountOfCoins(Bill bill) throws BillsToCoinsException {

		int centsLeft = Helper.convertDollartoCents(bill.getBillValue());
		logger.info("Bill Amount:  " + bill + " Cents Required:  " + centsLeft + " Cents in System:  "
				+ getCentsInSystem());

		if (!areThereEnoughCoins(centsLeft))
			throw new BillsToCoinsException("Not Enough Coins");

		for (int i = Coin.values().length - 1; i >= 0; --i) {
			Coin coinType = Coin.values()[i];
			centsLeft = calculateValue(bill, coinType, centsLeft);
		}

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put(Coin.THIRTYFOUR.name(), bill.getNumThirtyFour());
		map.put(Coin.THIRTYTHREE.name(), bill.getNumThirtyThree());
		map.put(Coin.QUARTERS.name(), bill.getNumQuarters());
		map.put(Coin.DIMES.name(), bill.getNumDimes());
		map.put(Coin.NICKELS.name(), bill.getNumNickels());
		map.put(Coin.PENNIES.name(), bill.getNumPennies());
		bill.setCoinsMap(map);
		displayCoins(bill);
		return map;

	}

	
	private int calculateValue(Bill bill, Coin c, int centsLeft) {

		int coins_InSystem = getCoinsInSystem(c);

		int coinValue = c.getCoinValue();
		int coinTypeCounter = centsLeft / coinValue;
		;
		if (coinTypeCounter <= coins_InSystem) {
			centsLeft = centsLeft % coinValue;
			coins_InSystem = coins_InSystem - coinTypeCounter;
		} else {
			centsLeft = centsLeft - (coinValue * coins_InSystem);
			coinTypeCounter = coins_InSystem;
			coins_InSystem = 0;
		}
		setValues(bill, c, coins_InSystem, coinTypeCounter);

		return centsLeft;

	}

	private void setValues(Bill bill, Coin c, int coins_InSystem, int coinTypeCounter) {
		if (c.name().equals(Coin.THIRTYFOUR.name())) {
			thirtyFourCoins_InSystem = coins_InSystem;
			bill.setNumThirtyFour(coinTypeCounter);
		} else if (c.name().equals(Coin.THIRTYTHREE.name())) {
			thirtyThreeCoins_InSystem = coins_InSystem;
			bill.setNumThirtyThree(coinTypeCounter);
		} else if (c.name().equals(Coin.QUARTERS.name())) {
			quarterCoins_InSystem = coins_InSystem;
			bill.setNumQuarters(coinTypeCounter);
		} else if (c.name().equals(Coin.DIMES.name())) {
			dimesCoins_InSystem = coins_InSystem;
			bill.setNumDimes(coinTypeCounter);
		} else if (c.name().equals(Coin.NICKELS.name())) {
			nickelsCoins_InSystem = coins_InSystem;
			bill.setNumNickels(coinTypeCounter);
		} else if (c.name().equals(Coin.PENNIES.name())) {
			penniesCoins_InSystem = coins_InSystem;
			bill.setNumPennies(coinTypeCounter);
		}
	}

	private int getCoinsInSystem(Coin c) {
		int coins_InSystem = 0;
		if (c.name().equals(Coin.THIRTYFOUR.name()))
			coins_InSystem = thirtyFourCoins_InSystem;
		else if (c.name().equals(Coin.THIRTYTHREE.name()))
			coins_InSystem = thirtyThreeCoins_InSystem;
		else if (c.name().equals(Coin.QUARTERS.name()))
			coins_InSystem = quarterCoins_InSystem;
		else if (c.name().equals(Coin.DIMES.name()))
			coins_InSystem = dimesCoins_InSystem;
		else if (c.name().equals(Coin.NICKELS.name()))
			coins_InSystem = nickelsCoins_InSystem;
		else if (c.name().equals(Coin.PENNIES.name()))
			coins_InSystem = penniesCoins_InSystem;
		return coins_InSystem;
	}

	private boolean areThereEnoughCoins(int centsLeft) {
		if (centsInSystem >= centsLeft) {
			return true;
		}
		return false;
	}

	private int getCentsInSystem() {
		centsInSystem = (thirtyFourCoins_InSystem * 34) + (thirtyThreeCoins_InSystem * 33)
				+ (quarterCoins_InSystem * 25) + (dimesCoins_InSystem * 10) + (nickelsCoins_InSystem * 5)
				+ (penniesCoins_InSystem * 1);
		return centsInSystem;
	}

	public static int getThirtyFourCoins_InSystem() {
		return thirtyFourCoins_InSystem;
	}

	public static void setThirtyFourCoins_InSystem(int thirtyFourCoins_InSystem) {
		BillsToCoinsService.thirtyFourCoins_InSystem = thirtyFourCoins_InSystem;
	}

	public static int getThirtyThreeCoins_InSystem() {
		return thirtyThreeCoins_InSystem;
	}

	public static void setThirtyThreeCoins_InSystem(int thirtyThreeCoins_InSystem) {
		BillsToCoinsService.thirtyThreeCoins_InSystem = thirtyThreeCoins_InSystem;
	}

	public static int getQuarterCoins_InSystem() {
		return quarterCoins_InSystem;
	}

	public static void setQuarterCoins_InSystem(int quarterCoins_InSystem) {
		BillsToCoinsService.quarterCoins_InSystem = quarterCoins_InSystem;
	}

	public static int getDimesCoins_InSystem() {
		return dimesCoins_InSystem;
	}

	public static void setDimesCoins_InSystem(int dimesCoins_InSystem) {
		BillsToCoinsService.dimesCoins_InSystem = dimesCoins_InSystem;
	}

	public static int getNickelsCoins_InSystem() {
		return nickelsCoins_InSystem;
	}

	public static void setNickelsCoins_InSystem(int nickelsCoins_InSystem) {
		BillsToCoinsService.nickelsCoins_InSystem = nickelsCoins_InSystem;
	}

	public static int getPenniesCoins_InSystem() {
		return penniesCoins_InSystem;
	}

	public static void setPenniesCoins_InSystem(int penniesCoins_InSystem) {
		BillsToCoinsService.penniesCoins_InSystem = penniesCoins_InSystem;
	}
	
	private void displayCoins(Bill bill) {
		logger.info(" ThirtyFour = " + bill.getNumThirtyFour() + "  Quarters Left in System:  "
				+ getThirtyFourCoins_InSystem());
		logger.info(" ThirtyThree = " + bill.getNumThirtyThree() + "  Quarters Left in System:  "
				+ getThirtyThreeCoins_InSystem());
		logger.info(
				" Quarters = " + bill.getNumQuarters() + "  Quarters Left in System:  " + getQuarterCoins_InSystem());
		logger.info(" Dimes    = " + bill.getNumDimes() + "  Dimes Left in System:  " + getDimesCoins_InSystem());
		logger.info(" Nickels  = " + bill.getNumNickels() + "  Nickels Left in System:  " + getNickelsCoins_InSystem());
		logger.info(" Pennies  = " + bill.getNumPennies() + "  Pennies Left in System:  " + getPenniesCoins_InSystem());

	}

	public void displayConfiguredCoins() {
		logger.info("Coins Setup in System");
		logger.info("ThirtyFour " + getThirtyFourCoins_InSystem());
		logger.info("ThirtyThree " + getThirtyThreeCoins_InSystem());
		logger.info("Quarters  " + getQuarterCoins_InSystem());
		logger.info("Dimes  " + getDimesCoins_InSystem());
		logger.info("Nickels  " + getNickelsCoins_InSystem());
		logger.info("Pennies  " + getPenniesCoins_InSystem());

	}


}
