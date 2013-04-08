package com.codejam.listener;

import java.util.ArrayList;
import java.util.List;

import com.codejam.listener.event.ConnectionEvent;
import com.codejam.listener.event.StrategyEvent;
import com.codejam.process.StrategyPacket;
import com.codejam.trade.Trade;

public class ListenerManager
{
	private static List<Listener> listeners = new ArrayList<Listener>();
	
	public static void addListener(Listener listener)
	{
		listener.init();
		listeners.add(listener);
	}
	
	public static void sendStrategyPacket(StrategyPacket packet, StrategyType type)
	{
		for(Listener listener : listeners)
		{
			if(listener instanceof StrategyListener)
			{
				StrategyListener list = ((StrategyListener) listener);
				if(list.type == type)
				{
					list.onStrategyReceived(packet);
				}
			}
		}
	}	
	
	public static void sendConnectionEvent(ConnectionEvent event)
	{

		for(Listener listener : listeners)
		{
			if(listener instanceof ConnectionListener)
			{
				ConnectionListener list = ((ConnectionListener) listener);
				list.onConnectionEvent(event);
			}
		}
	}
	
	public static void sendTrade(Trade trade)
	{
		for(Listener listener : listeners)
		{
			if(listener instanceof StrategyListener)
			{
				StrategyListener list = ((StrategyListener) listener);
				if(list.type == trade.strategyType)
				{
					list.onTrade(trade);
				}
			}
		}
	}

	public static void sendStrategyEvent(StrategyType strategy1, StrategyEvent strategyEvent)
	{
		for(Listener listener : listeners)
		{
			if(listener instanceof StrategyListener)
			{
				StrategyListener list = ((StrategyListener) listener);
				if(list.type == strategy1)
				{
					list.onStrategyEvent(strategyEvent);
				}
			}
		}
		
	}
	
	public static void sendStrategyEventToAll(List<StrategyType> strategies, StrategyEvent strategyEvent)
	{
		for(Listener listener : listeners)
		{
			if(listener instanceof StrategyListener)
			{
				StrategyListener list = ((StrategyListener) listener);
				if(strategies.contains(list.type))
				{
					list.onStrategyEvent(strategyEvent);
				}
			}
		}
		
	}
	
	public static void sendStrategyEventToAllBut(List<StrategyType> strategies, StrategyEvent strategyEvent)
	{
		for(Listener listener : listeners)
		{
			if(listener instanceof StrategyListener)
			{
				StrategyListener list = ((StrategyListener) listener);
				if(!strategies.contains(list.type))
				{
					list.onStrategyEvent(strategyEvent);
				}
			}
		}
		
	}

	public static void sendStrategyEventToAll(StrategyEvent strategyEvent)
	{
		for(Listener listener : listeners)
		{
			if(listener instanceof StrategyListener)
			{
				StrategyListener list = ((StrategyListener) listener);
				list.onStrategyEvent(strategyEvent);
			}
		}
	}
}
