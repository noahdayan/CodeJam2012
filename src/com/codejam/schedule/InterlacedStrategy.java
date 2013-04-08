package com.codejam.schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InterlacedStrategy {
	public static int[][] generateSchedule(){
		int[][] baseSchedule = {
				{1,1,1,1,2,0,0,0,1,1,1,1,0,0,0,0,0,0},
				{0,0,0,0,1,1,1,1,2,0,0,0,1,1,1,1,0,0},
				{0,0,1,1,1,1,2,0,0,0,1,1,1,1,0,0,0,0},
				{0,0,0,0,0,0,1,1,1,1,2,0,0,0,1,1,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1} };
		List<Integer> randomizer = new ArrayList<Integer>();
		randomizer.add(0);
		randomizer.add(1);
		randomizer.add(2);
		randomizer.add(3);
		randomizer.add(4);
		Collections.shuffle(randomizer);
		int[][] schedule = new int[5][18];
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 18; j++){
				schedule[i][j] = baseSchedule[randomizer.get(i)][j]; } }
		return schedule;
	}
}
