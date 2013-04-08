package com.codejam.process;

public class TriangularMovingAverage {
	
	private PriceTracker data;
	
	public TriangularMovingAverage(PriceTracker pData){
		this.data = pData;
	}
	
	public double calculateAverage(int n) {
		double average = 0.0;
		int t = data.getTime();
		if(t <= n)
		{
			for(int i = 0; i < t; i++)
			{
				average += data.getNthSMAaverage(i, n);
			}
			average = average/((double)t);
		}
		else
		{
			for(int i = 0; i < n; i++)
			{
				average += data.getNthPreviousSMAaverage(i, n);
			}
			average = average/((double)n);
		}
		return average;
	}

}
