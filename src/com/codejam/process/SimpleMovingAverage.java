package com.codejam.process;

public class SimpleMovingAverage {
	private PriceTracker data;
	
	public SimpleMovingAverage(PriceTracker pData){
		this.data = pData;
	}
	
	public double calculateAverage(int n) {
		double average = 0.0;
		if(data.getTime() <= n)
		{
			for(int i = 0; i < data.getTime(); i++)
			{
				average += data.getNthPrice(i);
			}
			average = average/((double)data.getTime());
		}
		else
		{
			double prevSMA = data.getNthPreviousSMAaverage(0,n);
			double prevPrice = data.getNthPreviousPrice(n);
			double newPrice = data.getNthPreviousPrice(0);
			average = prevSMA - prevPrice/((double)n) + newPrice/((double)n);
		}
		
		return average;
	}

}