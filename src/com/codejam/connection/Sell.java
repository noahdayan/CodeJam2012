package com.codejam.connection;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.codejam.listener.ListenerManager;
import com.codejam.listener.StrategyType;
import com.codejam.trade.Trade;
import com.codejam.trade.TradeType;

public class Sell extends Thread
{
	
	private int reqTime;
	private StrategyType TYPE;
	private String MANAGER;
	
	public Sell(int pTime, StrategyType pType, String pManager)
	{
		super();
		this.reqTime = pTime;
		this.TYPE = pType;
		this.MANAGER = pManager;
	}
	
	public void run()
	{
		try {
			Socket s = new Socket(Connections.getSocket().getInetAddress().getHostAddress(), Connections.getSocket().getPort());
			InputStreamReader i = new InputStreamReader(s.getInputStream());
			PrintWriter o = new PrintWriter(s.getOutputStream());
			o.write("S\r\n");
			o.flush();
			
			char[] buffer = new char[10];
			i.read(buffer);
			String price = new String();
			for(char c: buffer)
			{
				if((int)c == 69)
				{
					price = "E";
					break;
				}
				else
				{
					if(((int)c >= 48 && (int)c <= 57) || (int)c == 46)
					{
						price = price + c;
					}
				}
			}			
			i.close();
			o.close();
			s.close();
			if(Connections.MARKET_CLOSED) return;
			if(Connections.getTime() != 0)
			{
				if(price.equals("E")) Connections.MARKET_CLOSED = true;
				//System.out.print("Stock sold at time " + Connections.getTime() + " when requested sell at " + this.reqTime + " for price " + price + "\n");
				ListenerManager.sendTrade(new Trade(Connections.getTime(), price, TradeType.SELL, this.TYPE, this.MANAGER));	
			}
		} catch (UnknownHostException e) {}
		catch (IOException e) {}
	}
}
