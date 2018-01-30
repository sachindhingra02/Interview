package com.threaterSeating.Impl;

import java.util.Map;
import java.util.Map.Entry;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.threatreSeating.Dao.TheaterDao;
import com.threatreSeating.Domain.ViewersAllocate;
import com.threatreSeating.Impl.TheaterDaoImpl;

public class TheaterDaoImplTest {
	
	static TheaterDao theaterDao;
	
	@Before
	public void beforeLoad() {
		theaterDao = new TheaterDaoImpl();
		theaterDao.populateSeats(1, 1, 6);
		theaterDao.populateSeats(1, 2, 6);
		theaterDao.populateSeats(2, 1, 3);
		theaterDao.populateSeats(2, 1, 5);
		theaterDao.populateSeats(2, 6, 5);
		theaterDao.populateSeats(2, 6, 3);
	}

	@Test
	public void testAllocateSeats() {
		try {
			boolean result = theaterDao.allocateSeats("Sachin", 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, ViewersAllocate> seats = theaterDao.seatsAllocation();
		Assert.assertNotNull(seats);
	}
	
	@Test
	public void testAllocateSeatsWhenNoSeatsReturnFalse() {
		try {
			theaterDao = new TheaterDaoImpl();
			boolean result = theaterDao.allocateSeats("Sachin", 2);
			Assert.assertEquals(result, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAllocateSeatsWhenSplitRequest() {
		try {
			boolean result = theaterDao.allocateSeats("Sachin", 20);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, ViewersAllocate> seats = theaterDao.seatsAllocation();
		Assert.assertNotNull(seats);
		for(Entry<String, ViewersAllocate> results : seats.entrySet()) {
			Assert.assertEquals(results.getValue().getDiffRow(), "Call to split party");
		}
	}
}
