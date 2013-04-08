package com.codejam.schedule;

import com.codejam.listener.StrategyType;

public class Manager {

	private int id;
	private StrategyType[] strategyType1;
	private StrategyType[] strategyType2;
	private int[] schedule;
	
	public Manager(int id, StrategyType[] sType1, StrategyType[] sType2)
	{
		this.setId(id);
		if(sType1 == null){ sType1 = new StrategyType[18]; }
		if(sType2 == null){ sType2 = new StrategyType[18]; }
		this.setStrategyType1(sType1);
		this.setStrategyType2(sType2);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int[] getSchedule() {
		return schedule;
	}

	public void setSchedule(int[] schedule) {
		this.schedule = schedule;
	}

	public StrategyType[] getStrategyType1() {
		return strategyType1;
	}
	
	public StrategyType getCurrentStrategyType(int t, int s){
		if(s == 1){
			return strategyType1[t/1800];
		} else if (s == 2) {
			return strategyType2[t/1800];
		} else {
			return null;
		}
	}
	
	public StrategyType getCurrentStrategyTypeAtIndex(int t, int s){
		if(s == 1){
			return strategyType1[t];
		} else if (s == 2) {
			return strategyType2[t];
		} else {
			return null;
		}
	}

	public void setStrategyType1(StrategyType[] startegyType1) {
		this.strategyType1 = startegyType1;
	}

	public StrategyType[] getStrategyType2() {
		return strategyType2;
	}

	public void setStrategyType2(StrategyType[] strategyType2) {
		this.strategyType2 = strategyType2;
	}

	public int getState(int index)
	{
		return schedule[index];
	}
	
	public int getStateAtTime(int t)
	{
		return schedule[t/1800];
	}
	
	public void advSetStrategyAtTime(int s, StrategyType type, int t){
		if(s == 1){
			this.strategyType1[t/1800] = type;
		} else {
			this.strategyType2[t/1800] = type;
		}
	}
	
	public void advSetStrategyAtIndex(int s, StrategyType type, int i){
		if(s == 1){
			this.strategyType1[i] = type;
		} else {
			this.strategyType2[i] = type;
		}
	}
	
}
