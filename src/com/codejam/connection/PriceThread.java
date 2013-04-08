package com.codejam.connection;

import java.io.IOException;
import java.net.UnknownHostException;

import com.codejam.process.PriceTracker;

public class PriceThread extends Thread
{
	private PriceTracker PRICE_TRACKER;
	private double PRICE;
	
	public void run()
	{
		this.PRICE_TRACKER.pushPrice(this.PRICE);
	}
	
	public PriceThread(PriceTracker pPT, double pPrice)
	{
		this.PRICE_TRACKER = pPT;
		this.PRICE = pPrice;
	}
	
	public void start()
	{
		this.run();
	}
}
