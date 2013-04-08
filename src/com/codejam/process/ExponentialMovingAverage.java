package com.codejam.process;

public class ExponentialMovingAverage {
	
	private PriceTracker data;
	
	public ExponentialMovingAverage(PriceTracker pData){
		this.data = pData;
	}
	
	public double calculateAverage(int n) {
		
		if(data.getTime() <= 1)
		{
			return data.getNthPrice(0);
		}
		else
		{
			double prevEMA = data.getNthPreviousEMAaverage(0,n);
			double newPrice = data.getNthPreviousPrice(0);
			return prevEMA + ((2.0/((double)(n+1)))*(newPrice - prevEMA));
		}
	}

}
