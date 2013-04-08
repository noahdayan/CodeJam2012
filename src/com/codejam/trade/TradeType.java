package com.codejam.trade;

public enum TradeType {
	BUY, SELL;

	public static TradeType getType(String str) {
		if (str.equalsIgnoreCase("BUY")) {
			return BUY;
		} else if (str.equalsIgnoreCase("SELL")) {
			return SELL;
		} else {
			return null;
		}
	}
}
