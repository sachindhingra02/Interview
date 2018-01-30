package com.threatreSeating;

import java.util.Map;

import com.threatreSeating.Dao.TheaterDao;
import com.threatreSeating.Domain.ViewersAllocate;
import com.threatreSeating.Impl.TheaterDaoImpl;

public class Theatre {
	public static void main(String[] str) {
		TheaterDao theaterDao = new TheaterDaoImpl();
		theaterDao.populateSeats(1, 1, 6);
		theaterDao.populateSeats(1, 2, 6);
		theaterDao.populateSeats(2, 1, 3);
		theaterDao.populateSeats(2, 1, 5);
		theaterDao.populateSeats(2, 6, 5);
		theaterDao.populateSeats(2, 6, 3);
		
		try {
			boolean result = theaterDao.allocateSeats("Sachin", 2);
			result = theaterDao.allocateSeats("Sachin1", 10);
			result = theaterDao.allocateSeats("Sachin2", 100);
		} catch (Exception e) {
		}
		
		Map<String, ViewersAllocate> seats = theaterDao.seatsAllocation();
		System.out.println(seats);
	}
}
