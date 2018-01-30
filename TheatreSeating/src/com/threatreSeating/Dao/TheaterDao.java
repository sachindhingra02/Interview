package com.threatreSeating.Dao;

import java.util.Map;

import com.threatreSeating.Domain.ViewersAllocate;

public interface TheaterDao {
	public void populateSeats(int rowNumber, int section, int numberOfSeats);
	
	public boolean allocateSeats(String viewerName, int requiredSeats) throws Exception;
	
	public Map<String, ViewersAllocate> seatsAllocation();
}
