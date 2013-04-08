package com.codejam.process;

public class LinearWeightedMovingAverage {
	
	private PriceTracker data;
	
	public LinearWeightedMovingAverage(PriceTracker pData){
		this.data = pData;
	}

	public double calculateAverage(int n) {
		double average = 0.0;
		int t = data.getTime();
		if(data.getTime() <= n)
		{
			int divisor = 0;
			for(int i = 0; i < t; i++)
			{
				average += data.getNthPrice(i)*(i+1);
				divisor += (i+1);
			}
			average = average/((double)divisor);
		}
		else
		{
			int divisor = 0;
			for(int i = 0; i < n; i++)
			{
				average += data.getNthPrice(t-n+i)*(i+1);
				divisor += (i+1);
			}
			average = average/((double)divisor);
		}
		return average;
	}

}
