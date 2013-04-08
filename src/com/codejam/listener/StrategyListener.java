package com.codejam.listener;

import com.codejam.listener.event.StrategyEvent;
import com.codejam.process.StrategyPacket;
import com.codejam.trade.Trade;

public abstract class StrategyListener extends Listener
{
	public StrategyType type;
	public abstract void onStrategyReceived(StrategyPacket packet);
	public abstract void onTrade(Trade trade);
	public abstract void onStrategyEvent(StrategyEvent strategyEvent);
}
