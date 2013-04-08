package com.codejam.schedule;

//import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import com.codejam.listener.StrategyType;
import java.util.Random;

public class ScheduleManager
{
	private static int randBasis = (new Random()).nextInt(2);
	private static SchedulingStrategyType strategy = 
			(randBasis == 0) ? SchedulingStrategyType.INTERLACED :
				SchedulingStrategyType.WATERFALL;
	private static List<Manager> managers;
	private static int[][] schedule;
	private static int workforce;

	public static enum SchedulingStrategyType
	{
		BASIC, INTERLACED, WATERFALL
	}

	public static void setSchedulingStrategyType(SchedulingStrategyType pStrategy)
	{
		strategy = pStrategy;
	}

	public static List<Manager> getManagersWithSchedules()
	{
		generateSchedules();
		return managers;
	}

	public static List<Manager> getManagers()
	{
		return managers;
	}

	public static void generateSchedules()
	{
		generateStatusSchedule();
		assignStatusSchedules();
		advGenerateStrategySchedules();
	}

	private static void generateStatusSchedule()
	{
		// Generate Naked Schedule based on set strategy
		switch (strategy)
		{
		case BASIC:
			schedule = BasicStrategy.generateSchedule();
			break;
		case INTERLACED:
			schedule = InterlacedStrategy.generateSchedule();
			break;
		case WATERFALL:
			schedule = WaterfallStrategy.generateSchedule();
			break;
		default:
			schedule = InterlacedStrategy.generateSchedule();
		}
	}

	private static void assignStatusSchedules()
	{
		// Adding Managers to the schedule
		workforce = schedule.length;
		managers = new ArrayList<Manager>();
		for (int i = 0; i < workforce; i++)
		{
			managers.add(new Manager(i + 1, null, null));
		}
		//Collections.shuffle(managers);   // ## RANDOMLY
		int[][] tmpSchedule = schedule;
		schedule = new int[workforce][19];
		for (int i = 0; i < workforce; i++)
		{
			for (int j = 0; j < 19; j++)
			{
				if (j == 0)
				{
					schedule[i][j] = managers.get(i).getId();
				} else
				{
					schedule[i][j] = tmpSchedule[i][j - 1];
				}
			}
			managers.get(i).setSchedule(tmpSchedule[i]);
		}
	}

	private static void advGenerateStrategySchedules()
	{
		for(int i = 0; i < 18; i++){
			boolean stratPack1Taken = false;
			for(Manager m : managers){
				if(m.getState(i) == 1){
					if(!stratPack1Taken){
						m.advSetStrategyAtIndex(1, StrategyType.SMA, i);
						m.advSetStrategyAtIndex(2, StrategyType.EMA, i);
						stratPack1Taken = true;
					} else {
						m.advSetStrategyAtIndex(1, StrategyType.LWMA, i);
						m.advSetStrategyAtIndex(2, StrategyType.TMA, i);
					}
				}
			}
		}
	}
	
	public static Manager getManagerWithStrategyAtIndex(StrategyType type, int i){
		for(Manager m : managers){
				if(m.getState(i) == 1){
					if(m.getCurrentStrategyTypeAtIndex(i, 1).equals(type) ||
							m.getCurrentStrategyTypeAtIndex(i, 2).equals(type)){
						return m; } } }
		return null;
	}
}
