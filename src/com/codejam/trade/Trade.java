package com.codejam.trade;

import com.codejam.listener.StrategyType;

public class Trade
{

	public TradeType type;
	public StrategyType strategyType;
	public String price;
	public int time;
	public String manager;
	
	public Trade()
	{
		
	}
	
	public Trade(int pTime, String pPrice, TradeType pType, StrategyType pSType, String pManager)
	{
		
		this.time = pTime;
		this.price = pPrice;
		this.type = pType;
		this.strategyType = pSType;
		this.manager = pManager;
	}
	
}
