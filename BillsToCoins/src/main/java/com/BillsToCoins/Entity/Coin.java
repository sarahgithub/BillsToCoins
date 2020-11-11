package com.BillsToCoins.Entity;

public enum Coin {

	THIRTYFOUR(34), THIRTYTHREE(33), QUARTERS(25), DIMES(10), NICKELS(5), PENNIES(1);

	private int coinValue;

	private Coin(int coinValue) {
		this.coinValue = coinValue;
	}

	public int getCoinValue() {
		return coinValue;
	}

}
