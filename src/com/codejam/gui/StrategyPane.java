package com.codejam.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.codejam.listener.StrategyListener;
import com.codejam.listener.ListenerManager;
import com.codejam.listener.StrategyType;
import com.codejam.listener.event.StrategyEvent;
import com.codejam.process.StrategyPacket;
import com.codejam.trade.Trade;

public class StrategyPane extends JPanel
{
	private static final long serialVersionUID = 1L;

	private StrategyType strategyType;

	private JTabbedPane tabbedPane;
	private StrategyGraphButton graph;
	private StrategyLog log;
	
	public StrategyPane(StrategyType t)
	{
		this.strategyType = t;
		this.setLayout(new BorderLayout());
		graph = new StrategyGraphButton(strategyType);
		log = new StrategyLog(strategyType);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab( "Graph (" + strategyType +")", graph );
		tabbedPane.addTab( "Log", log );

		this.add( tabbedPane);
		
		
		
		ListenerManager.addListener( new StrategyListener()
		{	
			@Override
			public void onStrategyReceived(StrategyPacket packet)
			{
				addPoint(packet);
			}

			@Override
			public void init() {
				System.out.println("type="+strategyType);
				this.type = strategyType;	
			}

			@Override
			public void onTrade(Trade trade)
			{
				addTrade(trade);
				
			}

			@Override
			public void onStrategyEvent(StrategyEvent strategyEvent)
			{
				if(strategyEvent.getMessage().equalsIgnoreCase("Selected"))
				{
					setBorder(BorderFactory.createLineBorder(Color.red));
				}
				else if(strategyEvent.getMessage().equalsIgnoreCase("UnSelected"))
				{
					setBorder(null);
				}
				
			}
		});
	}
	
	
	
	public void addPoint(StrategyPacket packet)
	{
		graph.addPoint(packet);
	}
	
	public void addTrade(Trade trade)
	{
		log.log(trade);
	}
	
	public List<Trade> getAllTrades()
	{
		return log.getAllTrades();
	}
	/**
	 * @return the type
	 */
	public StrategyType getType()
	{
		return strategyType;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(StrategyType type)
	{
		this.strategyType = type;
	}
}
