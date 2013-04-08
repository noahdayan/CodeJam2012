package com.codejam.process;

public class StrategyPacket 
{
	public double price;
	public double averageFive;
	public double averageTwenty;
	public int time;	
	
	public StrategyPacket(double pPrice, double pAverage5, double pAverage20, int pTime){
		price = pPrice;
		averageFive = pAverage5;
		averageTwenty = pAverage20;
		time = pTime;
	}

	public StrategyPacket()
	{
		// TODO Auto-generated constructor stub
	}
}
