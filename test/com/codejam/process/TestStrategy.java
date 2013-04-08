package com.codejam.process;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestStrategy {

	@Test
	public void testStrategy()
	{
		PriceTracker tracker = new PriceTracker();
		
		tracker.pushPrice(61.590);
		assertEquals(61.590,tracker.calcSMAaverage(5), 0.0005);
		assertEquals(61.590,tracker.calcLWMAaverage(5), 0.0005);
		assertEquals(61.590,tracker.calcEMAaverage(5), 0.0005);
		assertEquals(61.590,tracker.calcTMAaverage(5), 0.0005);
		tracker.pushPrice(61.440);
		assertEquals(61.515,tracker.calcSMAaverage(5), 0.0005);
		assertEquals(61.490,tracker.calcLWMAaverage(5), 0.0005);
		assertEquals(61.540,tracker.calcEMAaverage(5), 0.0005);
		assertEquals(61.553,tracker.calcTMAaverage(5), 0.0005);
		tracker.pushPrice(61.320);
		assertEquals(61.450,tracker.calcSMAaverage(5), 0.0005);
		assertEquals(61.405,tracker.calcLWMAaverage(5), 0.0005);
		assertEquals(61.467,tracker.calcEMAaverage(5), 0.0005);
		assertEquals(61.518,tracker.calcTMAaverage(5), 0.0005);
		tracker.pushPrice(61.670);
		assertEquals(61.505,tracker.calcSMAaverage(5), 0.0005);
		assertEquals(61.511,tracker.calcLWMAaverage(5), 0.0005);
		assertEquals(61.534,tracker.calcEMAaverage(5), 0.0005);
		assertEquals(61.515,tracker.calcTMAaverage(5), 0.0005);
		tracker.pushPrice(61.920);
		assertEquals(61.588,tracker.calcSMAaverage(5), 0.0005);
		assertEquals(61.647,tracker.calcLWMAaverage(5), 0.0005);
		assertEquals(61.663,tracker.calcEMAaverage(5), 0.0005);
		assertEquals(61.530,tracker.calcTMAaverage(5), 0.0005);
		tracker.pushPrice(62.610);
		assertEquals(61.792,tracker.calcSMAaverage(5), 0.0005);
		assertEquals(61.988,tracker.calcLWMAaverage(5), 0.0005);
		assertEquals(61.979,tracker.calcEMAaverage(5), 0.0005);
		assertEquals(61.570,tracker.calcTMAaverage(5), 0.0005);
		tracker.pushPrice(62.880);
		assertEquals(62.080,tracker.calcSMAaverage(5), 0.0005);
		assertEquals(62.351,tracker.calcLWMAaverage(5), 0.0005);
		assertEquals(62.279,tracker.calcEMAaverage(5), 0.0005);
		assertEquals(61.683,tracker.calcTMAaverage(5), 0.0005);
		tracker.pushPrice(63.060);
		assertEquals(62.428,tracker.calcSMAaverage(5), 0.0005);
		assertEquals(62.677,tracker.calcLWMAaverage(5), 0.0005);
		assertEquals(62.539,tracker.calcEMAaverage(5), 0.0005);
		assertEquals(61.879,tracker.calcTMAaverage(5), 0.0005);
		tracker.pushPrice(63.290);
		assertEquals(62.752,tracker.calcSMAaverage(5), 0.0005);
		assertEquals(62.965,tracker.calcLWMAaverage(5), 0.0005);
		assertEquals(62.790,tracker.calcEMAaverage(5), 0.0005);
		assertEquals(62.128,tracker.calcTMAaverage(5), 0.0005);
		tracker.pushPrice(63.320);
		assertEquals(63.032,tracker.calcSMAaverage(5), 0.0005);
		assertEquals(63.154,tracker.calcLWMAaverage(5), 0.0005);
		assertEquals(62.966,tracker.calcEMAaverage(5), 0.0005);
		assertEquals(62.417,tracker.calcTMAaverage(5), 0.0005);
		tracker.pushPrice(63.260);
		assertEquals(63.162,tracker.calcSMAaverage(5), 0.0005);
		assertEquals(63.230,tracker.calcLWMAaverage(5), 0.0005);
		assertEquals(63.064,tracker.calcEMAaverage(5), 0.0005);
		assertEquals(62.691,tracker.calcTMAaverage(5), 0.0005);
		tracker.pushPrice(63.120);
		assertEquals(63.210,tracker.calcSMAaverage(5), 0.0005);
		assertEquals(63.216,tracker.calcLWMAaverage(5), 0.0005);
		assertEquals(63.083,tracker.calcEMAaverage(5), 0.0005);
		assertEquals(62.917,tracker.calcTMAaverage(5), 0.0005);
		tracker.pushPrice(62.240);
		assertEquals(63.046,tracker.calcSMAaverage(5), 0.0005);
		assertEquals(62.893,tracker.calcLWMAaverage(5), 0.0005);
		assertEquals(62.802,tracker.calcEMAaverage(5), 0.0005);
		assertEquals(63.040,tracker.calcTMAaverage(5), 0.0005);
		tracker.pushPrice(62.190);
		assertEquals(62.826,tracker.calcSMAaverage(5), 0.0005);
		assertEquals(62.607,tracker.calcLWMAaverage(5), 0.0005);
		assertEquals(62.598,tracker.calcEMAaverage(5), 0.0005);
		assertEquals(63.055,tracker.calcTMAaverage(5), 0.0005);
		tracker.pushPrice(62.890);
		assertEquals(62.740,tracker.calcSMAaverage(5), 0.0005);
		assertEquals(62.629,tracker.calcLWMAaverage(5), 0.0005);
		assertEquals(62.695,tracker.calcEMAaverage(5), 0.0005);
		assertEquals(62.997,tracker.calcTMAaverage(5), 0.0005);
	}
}
