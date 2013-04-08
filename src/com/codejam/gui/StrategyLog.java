package com.codejam.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.codejam.listener.StrategyType;
import com.codejam.trade.Trade;
import com.codejam.trade.TradeType;

public class StrategyLog extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private StrategyType strategyType;
	
	DefaultTableModel model;
	JTable table;
	JScrollPane scrollPane;

	public StrategyLog(StrategyType pStrategyType) {
		this.strategyType = pStrategyType;
		this.setLayout(new BorderLayout());
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		String colHeadings[] = { "Action", "Price", "Time", "Manager"};
		int numRows = 0;
		model = new DefaultTableModel(numRows, colHeadings.length);
		model.setColumnIdentifiers(colHeadings);
		table = new JTable(model);

		scrollPane = new JScrollPane(table);
		add(scrollPane);
	}

	public void log(Trade trade) {
		model.addRow(new Object[] { trade.type, trade.price, trade.time, trade.manager});
	}
	
	public List<Trade> getAllTrades()
	{
		List<Trade> trades = new ArrayList<Trade>();
		for(int i=  0; i < table.getRowCount(); i ++)
		{
			
			Trade trade = new Trade();
			trade.type = (TradeType) table.getValueAt(i, 0);
			trade.price = (String) table.getValueAt(i, 1);
			trade.time = (Integer) table.getValueAt(i, 2);
			trade.manager = (String) table.getValueAt(i, 3);
			trade.strategyType = strategyType;
			if(!trade.price.contains("E"))
			{
				trades.add(trade);
			}
		}
		
		return trades;
	}
}
