package com.codejam.process;

import com.codejam.connection.Buy;
import com.codejam.connection.Sell;
import com.codejam.listener.ListenerManager;
import com.codejam.listener.StrategyType;
import com.codejam.schedule.Manager;
import com.codejam.schedule.ScheduleManager;
import java.util.List;

public class PriceTracker{

	private double[] allPrices;
	private double[] averageSMA5;
	private double[] averageLWMA5;
	private double[] averageEMA5;
	private double[] averageTMA5;
	private double[] averageSMA20;
	private double[] averageLWMA20;
	private double[] averageEMA20;
	private double[] averageTMA20;
	private List<Manager> managers;
	private SimpleMovingAverage SMAstrategy;
	private LinearWeightedMovingAverage LWMAstrategy;
	private ExponentialMovingAverage EMAstrategy;
	private TriangularMovingAverage TMAstrategy;
	private StrategyPacket packet;
	private BuySellAlg tradeAlgo;
	
	private int RAIaverageSMA5;
	private int RAIaverageLWMA5;
	private int RAIaverageEMA5;
	private int RAIaverageTMA5;
	private int RAIaverageSMA20;
	private int RAIaverageLWMA20;
	private int RAIaverageEMA20;
	private int RAIaverageTMA20;
	
	private int time = 0;
	
	public PriceTracker(){
		this.reset();
	}

	public void pushPrice(double price){
		setNewPrice(price); time++;
		process();
	}
	
	public List<Manager> getManagers(){
		return this.managers;
	}
		
	private void process(){
		double average5;
		double average20;
		double price = getNthPreviousPrice(0);
		StrategyType type;
		Manager m;
		
		type = StrategyType.SMA;
		average5 = calcSMAaverage(5);
		average20 = calcSMAaverage(20);
		if(time < 32400){
		m = ScheduleManager.getManagerWithStrategyAtIndex(type, time/1800);
		switch(tradeAlgo.buySellDecision(type)){
		case 1: (new Buy(time, type, "Manager " + m.getId())).start(); break;
		case 2: (new Sell(time, type, "Manager " + m.getId())).start(); break;
		default: break; } }
		packet = new StrategyPacket(price, average5, average20, time);
		ListenerManager.sendStrategyPacket(packet, type);
		
		type = StrategyType.EMA;
		average5 = calcEMAaverage(5);
		average20 = calcEMAaverage(20);
		if(time < 32400){
		m = ScheduleManager.getManagerWithStrategyAtIndex(type, time/1800);
		switch(tradeAlgo.buySellDecision(type)){
		case 1: (new Buy(time, type, "Manager " + m.getId())).start(); break;
		case 2: (new Sell(time, type, "Manager " + m.getId())).start(); break;
		default: break; } }
		packet = new StrategyPacket(price, average5, average20, time);
		ListenerManager.sendStrategyPacket(packet, type);

		type = StrategyType.LWMA;
		average5 = calcLWMAaverage(5);
		average20 = calcLWMAaverage(20);
		if(time < 32400){
		m = ScheduleManager.getManagerWithStrategyAtIndex(type, time/1800);
		switch(tradeAlgo.buySellDecision(type)){
		case 1: (new Buy(time, type, "Manager " + m.getId())).start(); break;
		case 2: (new Sell(time, type, "Manager " + m.getId())).start(); break;
		default: break; } }
		packet = new StrategyPacket(price, average5, average20, time);
		ListenerManager.sendStrategyPacket(packet, type);

		type = StrategyType.TMA;
		average5 = calcTMAaverage(5);
		average20 = calcTMAaverage(20);
		if(time < 32400){
		m = ScheduleManager.getManagerWithStrategyAtIndex(type, time/1800);
		switch(tradeAlgo.buySellDecision(type)){
		case 1: (new Buy(time, type, "Manager " + m.getId())).start(); break;
		case 2: (new Sell(time, type, "Manager " + m.getId())).start(); break;
		default: break; } }
		packet = new StrategyPacket(price, average5, average20, time);
		ListenerManager.sendStrategyPacket(packet, type);

	}
	
	public void reset(){
		time = 0;
		allPrices = new double[32400];
		averageSMA5 = new double[5];
		averageLWMA5 = new double[5];
		averageEMA5 = new double[5];
		averageTMA5 = new double[5];
		averageSMA20 = new double[20];
		averageLWMA20 = new double[20];
		averageEMA20 = new double[20];
		averageTMA20 = new double[20];
		SMAstrategy = new SimpleMovingAverage(this);
		LWMAstrategy = new LinearWeightedMovingAverage(this);
		EMAstrategy = new ExponentialMovingAverage(this);
		TMAstrategy = new TriangularMovingAverage(this);
		tradeAlgo = new BuySellAlg(this);
		RAIaverageSMA5 = 0;
		RAIaverageLWMA5 = 0;
		RAIaverageEMA5 = 0;
		RAIaverageTMA5 = 0;
		RAIaverageSMA20 = 0;
		RAIaverageLWMA20 = 0;
		RAIaverageEMA20 = 0;
		RAIaverageTMA20 = 0;
		
	}
	
	public int getTime() { return time; }

	public double getNthPrice(int n){ return allPrices[n]; }
	
	public double getNthPreviousPrice(int n){
		return allPrices[time-n-1]; }
	
	public double setNewPrice(double x){
		return allPrices[time] = x; }
	
	public double calcEMAaverage(int t){
		return setNewEMAaverage(EMAstrategy.calculateAverage(t),t); }
	
	public double calcLWMAaverage(int t){
		return setNewLWMAaverage(LWMAstrategy.calculateAverage(t),t); }
	
	public double calcSMAaverage(int t){
		return setNewSMAaverage(SMAstrategy.calculateAverage(t),t); }
	
	public double calcTMAaverage(int t){
		return setNewTMAaverage(TMAstrategy.calculateAverage(t),t); }
	
	public double getNthSMAaverage(int n, int t){
		if(t == 5){
			n = ((n + RAIaverageSMA5-time) % 5);
			return averageSMA5[n]; }
		if(t == 20){
			n = ((n + RAIaverageSMA20-time) % 20);
			return averageSMA20[n]; }
		else{ return 0.0; }
	}
	
	public double getNthPreviousSMAaverage(int n, int t){
		if(t == 5){
			n = ((RAIaverageSMA5-1 - n) % 5);
			return averageSMA5[n]; }
		if(t == 20){
			n = ((RAIaverageSMA20-1 - n) % 20);
			return averageSMA20[n]; }
		else{ return 0.0; }
	}
	
	public double setNewSMAaverage(double x, int t){
		if(t == 5){ int n = 0;
			RAIaverageSMA5++; n = ((RAIaverageSMA5-1) % 5);
			return averageSMA5[n] = x; }
		if(t == 20){ int n = 0;
			RAIaverageSMA20++; n = ((RAIaverageSMA20-1) % 20);
			return averageSMA20[n] = x; }
		else { return 0.0; }
	}
	
	public double getNthLWMAaverage(int n, int t){
		if(t == 5){
			n = ((n + RAIaverageLWMA5-time) % 5);
			return averageLWMA5[n]; }
		if(t == 20){
			n = ((n + RAIaverageLWMA20-time) % 20);
			return averageLWMA20[n]; }
		else{ return 0.0; }
	}
	
	public double getNthPreviousLWMAaverage(int n, int t){
		if(t == 5){
			n = ((RAIaverageLWMA5-1 - n) % 5);
			return averageLWMA5[n]; }
		if(t == 20){
			n = ((RAIaverageLWMA20-1 - n) % 20);
			return averageLWMA20[n]; }
		else{ return 0.0; }
	}
	
	public double setNewLWMAaverage(double x, int t){
		if(t == 5){ int n = 0;
			RAIaverageLWMA5++; n = ((RAIaverageLWMA5-1) % 5);
			return averageLWMA5[n] = x; }
		if(t == 20){ int n = 0;
			RAIaverageLWMA20++; n = ((RAIaverageLWMA20-1) % 20);
			return averageLWMA20[n] = x; }
		else { return 0.0; }
	}
	
	public double getNthEMAaverage(int n, int t){
		if(t == 5){
			n = ((n + RAIaverageEMA5-time) % 5);
			return averageEMA5[n]; }
		if(t == 20){
			n = ((n + RAIaverageEMA20-time) % 20);
			return averageEMA20[n]; }
		else{ return 0.0; }
	}
	
	public double getNthPreviousEMAaverage(int n, int t){
		if(t == 5){
			n = ((RAIaverageEMA5-1 - n) % 5);
			return averageEMA5[n]; }
		if(t == 20){
			n = ((RAIaverageEMA20-1 - n) % 20);
			return averageEMA20[n]; }
		else{ return 0.0; }
	}
	
	public double setNewEMAaverage(double x, int t){
		if(t == 5){ int n = 0;
			RAIaverageEMA5++; n = ((RAIaverageEMA5-1) % 5);
			return averageEMA5[n] = x; }
		if(t == 20){ int n = 0;
			RAIaverageEMA20++; n = ((RAIaverageEMA20-1) % 20);
			return averageEMA20[n] = x; }
		else { return 0.0; }
	}
	
	public double getNthTMAaverage(int n, int t){
		if(t == 5){
			n = ((n + RAIaverageTMA5-time) % 5);
			return averageTMA5[n]; }
		if(t == 20){
			n = ((n + RAIaverageTMA20-time) % 20);
			return averageTMA20[n]; }
		else{ return 0.0; }
	}
	
	public double getNthPreviousTMAaverage(int n, int t){
		if(t == 5){
			n = ((RAIaverageTMA5-1 - n) % 5);
			return averageTMA5[n]; }
		if(t == 20){
			n = ((RAIaverageTMA20-1 - n) % 20);
			return averageTMA20[n]; }
		else{ return 0.0; }
	}
	
	public double setNewTMAaverage(double x, int t){
		if(t == 5){ int n = 0;
			RAIaverageTMA5++; n = ((RAIaverageTMA5-1) % 5);
			return averageTMA5[n] = x; }
		if(t == 20){ int n = 0;
			RAIaverageTMA20++; n = ((RAIaverageTMA20-1) % 20);
			return averageTMA20[n] = x; }
		else { return 0.0; }
	}
	
}
