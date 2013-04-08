package com.codejam.connection;

import java.io.IOException;
import java.net.UnknownHostException;

import com.codejam.process.PriceTracker;

public class ConnectionThread extends Thread
{
	public ConnectionThread()
	{
		super();
	}
	
	public void run()
	{
		try {
			Connections.WorkingDay(new PriceTracker());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
