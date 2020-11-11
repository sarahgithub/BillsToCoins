package com.BillsToCoins.Entity;

import java.util.HashMap;
import java.util.Map;

public class Bill {

	private int billValue;
	private int numQuarters;
	private int numDimes;
	private int numNickels;
	private int numPennies;
	private int numThirtyThree;
	private int numThirtyFour;
	private int centsLeft;
	
	public Bill(Integer billValue) {
		super();
		this.billValue = billValue;
	}

	public int getNumQuarters() {
		return numQuarters;
	}

	public void setNumQuarters(int numQuarters) {
		this.numQuarters = numQuarters;
	}

	public int getNumDimes() {
		return numDimes;
	}

	public void setNumDimes(int numDimes) {
		this.numDimes = numDimes;
	}

	public int getNumNickels() {
		return numNickels;
	}

	public void setNumNickels(int numNickels) {
		this.numNickels = numNickels;
	}

	public int getNumPennies() {
		return numPennies;
	}

	public void setNumPennies(int numPennies) {
		this.numPennies = numPennies;
	}

	public int getNumThirtyThree() {
		return numThirtyThree;
	}

	public void setNumThirtyThree(int numThirtyThree) {
		this.numThirtyThree = numThirtyThree;
	}

	public int getNumThirtyFour() {
		return numThirtyFour;
	}

	public void setNumThirtyFour(int numThirtyFour) {
		this.numThirtyFour = numThirtyFour;
	}

	public int getCentsLeft() {
		return centsLeft;
	}

	public void setCentsLeft(int centsLeft) {
		this.centsLeft = centsLeft;
	}

	Map<String, Integer> coinsMap = new HashMap<String, Integer>();

	

	public int getBillValue() {
		return billValue;
	}

	public void setBillValue(int billValue) {
		this.billValue = billValue;
	}

	public Map<String, Integer> getCoinsMap() {
		return coinsMap;
	}

	public void setCoinsMap(Map<String, Integer> coinsMap) {
		this.coinsMap = coinsMap;
	}

}
