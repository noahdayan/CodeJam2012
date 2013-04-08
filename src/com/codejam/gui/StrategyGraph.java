package com.codejam.gui;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.traces.Trace2DLtd;

import java.awt.BorderLayout;
import java.awt.Color;

import com.codejam.listener.StrategyType;
import com.codejam.process.StrategyPacket;

public class StrategyGraph extends Chart2D
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final static int TRACE_SIZE = 25;
	private StrategyType type;
	private ITrace2D price;
	private ITrace2D averageFive;
	private ITrace2D averageTwenty;

	public StrategyGraph(StrategyType type)
	{
		this.type = type;
		this.setLayout(new BorderLayout());

		price = new Trace2DLtd(TRACE_SIZE, "Price");
		price.setColor(Color.BLUE);
		addTrace(price);

		averageFive = new Trace2DLtd(TRACE_SIZE, type + "[5]");
		averageFive.setColor(Color.GREEN);
		addTrace(averageFive);

		averageTwenty = new Trace2DLtd(TRACE_SIZE, type + "[20]");
		averageTwenty.setColor(Color.RED);
		addTrace(averageTwenty);
	}

	public void addPoint(StrategyPacket packet)
	{
		price.addPoint(packet.time, packet.price);
		averageTwenty.addPoint(packet.time, packet.averageTwenty);
		averageFive.addPoint(packet.time, packet.averageFive);
	}

	public StrategyType getType()
	{
		return type;
	}

	public void setType(StrategyType type)
	{
		this.type = type;
	}

}
