package com.codejam.connection;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import com.codejam.listener.ListenerManager;
import com.codejam.listener.event.ConnectionEvent;
import com.codejam.process.PriceTracker;

public class Connections
{
	public static boolean MARKET_CLOSED;
	private static int TIME = 0;
	private static int READ_INT;
	private static double STOCK_PRICE;
	private static String PARSED_STOCK_PRICE = new String();
	
	private static Socket TRADE_BOOK_SOCKET;
	private static InputStreamReader TRADE_BOOK_SERVER_READER;
	private static PrintWriter TRADE_BOOK_SERVER_WRITER;
	
	private static Socket PRICE_FEED_SOCKET;
	private static InputStreamReader PRICE_FEED_SERVER_READER;
	private static PrintWriter PRICE_FEED_SERVER_WRITER;
	
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException
	{
		WorkingDay(new PriceTracker());
	}
	
	public static void WorkingDay(PriceTracker PRICE_TRACKER) throws UnknownHostException, IOException, InterruptedException
	{
		//INIT MARKET NOT CLOSED
		MARKET_CLOSED = false;
		
		//INIT TRADE BOOK SOCKET
		TRADE_BOOK_SOCKET = new Socket(JOptionPane.showInputDialog(null, "Enter IP for Trade Book", "127.0.0.1"), Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Port for Trade Book", "3001")));
		
		//INIT PRICE FEED SOCKET
		PRICE_FEED_SOCKET = new Socket(JOptionPane.showInputDialog(null, "Enter IP for Price Feed", "127.0.0.1"), Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Port for Price Feed", "3000")));
		
		//INIT READ WRITERS
		TRADE_BOOK_SERVER_READER = new InputStreamReader(TRADE_BOOK_SOCKET.getInputStream());
		TRADE_BOOK_SERVER_WRITER = new PrintWriter(new OutputStreamWriter(TRADE_BOOK_SOCKET.getOutputStream()), true);
		PRICE_FEED_SERVER_READER = new InputStreamReader(PRICE_FEED_SOCKET.getInputStream());
		PRICE_FEED_SERVER_WRITER = new PrintWriter(new OutputStreamWriter(PRICE_FEED_SOCKET.getOutputStream()), true);
		
		//INIT Price Feed
		PRICE_FEED_SERVER_WRITER.print("H\r\n");
		PRICE_FEED_SERVER_WRITER.flush();
		
		//PARSE LOOP
		while((READ_INT = PRICE_FEED_SERVER_READER.read()) != 'C')
		{
			//TOKENIZE using | stroke
			if((char)READ_INT != '|')
			{
				PARSED_STOCK_PRICE = PARSED_STOCK_PRICE + (char)READ_INT;

			}
			else
			{
				//new word so we can convert string to double for calculations
				STOCK_PRICE = Double.parseDouble(PARSED_STOCK_PRICE);
				//System.out.println("Time: " + TIME + " and Price: $" + STOCK_PRICE);
				(new PriceThread(PRICE_TRACKER, STOCK_PRICE)).start();
				PARSED_STOCK_PRICE = new String();
				TIME++;
			}
		}
		System.out.println("closing connections: price feed");
		PRICE_FEED_SERVER_READER.close();
		PRICE_FEED_SERVER_WRITER.close();
		PRICE_FEED_SOCKET.close();
		
		System.out.println("closing connections: trade");
		TRADE_BOOK_SERVER_READER = null;
		//TRADE_BOOK_SERVER_READER.close();
		TRADE_BOOK_SERVER_WRITER.close();
		TRADE_BOOK_SOCKET.close();
		System.out.println("Connections Closed");
		
		TIME = 0;
		PARSED_STOCK_PRICE = new String();
		ListenerManager.sendConnectionEvent(ConnectionEvent.CLOSE);
	}

	
	public static int getTime()
	{
		return TIME;
	}
	
	public static InputStreamReader getTradeBookReader()
	{
		return TRADE_BOOK_SERVER_READER;
	}
	
	public static PrintWriter getTradeBookWriter()
	{
		return TRADE_BOOK_SERVER_WRITER;
	}
	
	public static Socket getSocket()
	{
		return TRADE_BOOK_SOCKET;
	}
	
}
