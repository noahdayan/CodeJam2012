package com.codejam.process;

import com.codejam.listener.StrategyType;

public class BuySellAlg {

	private PriceTracker data;
	
	public BuySellAlg(PriceTracker pData){
		this.data = pData;
	}
	
	public int buySellDecision(StrategyType type)
	{
		//Buy = 1; Sell = 2; Not = 0;
		
		if(data.getTime() < 2)
		{
			return 0;
		}
		
		switch(type)
		{
		case SMA:
			double prevSMA5 = data.getNthPreviousSMAaverage(1, 5);
			double newSMA5 = data.getNthPreviousSMAaverage(0, 5);
			double prevSMA20 = data.getNthPreviousSMAaverage(1, 20);
			double newSMA20 = data.getNthPreviousSMAaverage(0, 20);
			if(prevSMA5 <= prevSMA20 && newSMA20 < newSMA5)
			{
				return 1;
			}
			else if(prevSMA5 >= prevSMA20 && newSMA20 > newSMA5)
			{
				return 2;
			}
			break;
			
		case LWMA:
			double prevLWMA5 = data.getNthPreviousLWMAaverage(1, 5);
			double newLWMA5 = data.getNthPreviousLWMAaverage(0, 5);
			double prevLWMA20 = data.getNthPreviousLWMAaverage(1, 20);
			double newLWMA20 = data.getNthPreviousLWMAaverage(0, 20);
			if(prevLWMA5 <= prevLWMA20 && newLWMA20 < newLWMA5)
			{
				return 1;
			}
			else if(prevLWMA5 >= prevLWMA20 && newLWMA20 > newLWMA5)
			{
				return 2;
			}
			break;
			
		case EMA:
			double prevEMA5 = data.getNthPreviousEMAaverage(1, 5);
			double newEMA5 = data.getNthPreviousEMAaverage(0, 5);
			double prevEMA20 = data.getNthPreviousEMAaverage(1, 20);
			double newEMA20 = data.getNthPreviousEMAaverage(0, 20);
			if(prevEMA5 <= prevEMA20 && newEMA20 < newEMA5)
			{
				return 1;
			}
			else if(prevEMA5 >= prevEMA20 && newEMA20 > newEMA5)
			{
				return 2;
			}
			break;
			
		case TMA:
			double prevTMA5 = data.getNthPreviousTMAaverage(1, 5);
			double newTMA5 = data.getNthPreviousTMAaverage(0, 5);
			double prevTMA20 = data.getNthPreviousTMAaverage(1, 20);
			double newTMA20 = data.getNthPreviousTMAaverage(0, 20);
			if(prevTMA5 <= prevTMA20 && newTMA20 < newTMA5)
			{
				return 1;
			}
			else if(prevTMA5 >= prevTMA20 && newTMA20 > newTMA5)
			{
				return 2;
			}
			break;
			
		default:
			break;
		}
		
		return 0;
	}
	
}
