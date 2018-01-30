package com.threatreSeating.Impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.threatreSeating.Dao.TheaterDao;
import com.threatreSeating.Domain.ViewersAllocate;

public class TheaterDaoImpl implements TheaterDao {
	
	private Map<Integer,Map<Integer, Integer>> seats = new TreeMap<Integer,Map<Integer, Integer>>();
	private long totalNumberOfSeats = 0;
	private long numberOfSeatsUsd = 0;
	private Map<Integer,Map<Integer, Integer>> numberOfSeatsUsdEachRow = new TreeMap<Integer,Map<Integer, Integer>>();
	private Map<String, ViewersAllocate> allocatedSeats = new HashMap<String, ViewersAllocate>();
	public static final String DIFF_ROW = "Call to split party";
	public static final String NOT_HANDLE= "Sorry, we can't handle your party.";
	
	public void populateSeats(int rowNumber, int section, int numberOfSeats) {
		Map<Integer, Integer> rowSectn = seats.get(rowNumber);
		if(rowSectn == null) {
			rowSectn = new TreeMap<Integer, Integer>();
			seats.put(rowNumber, rowSectn);
		}
		totalNumberOfSeats += numberOfSeats;
		rowSectn.put(section, numberOfSeats);
	}
	
	public boolean allocateSeats(String viewerName, int requiredSeats) throws Exception{
		if(null == viewerName || viewerName.trim().length() == 0 || requiredSeats == 0) {
			throw new Exception("In correct input");
		}
		
		if(seats == null || seats.size() == 0) {
			return false;
		}
		
		if((numberOfSeatsUsd + requiredSeats) > totalNumberOfSeats) {
			ViewersAllocate viewersAllocate = new ViewersAllocate(NOT_HANDLE);
			allocatedSeats.put(viewerName, viewersAllocate);
			return false;
		}
		
		for(Entry<Integer,Map<Integer, Integer>> theaterSeats : seats.entrySet()) {
			int rowNumber = theaterSeats.getKey();
			Map<Integer, Integer> rowSections = theaterSeats.getValue();
			Map<Integer, Integer> usdRowSections = numberOfSeatsUsdEachRow.get(rowNumber);
			if(usdRowSections == null) {
				usdRowSections = new TreeMap<Integer, Integer>();
				numberOfSeatsUsdEachRow.put(rowNumber, usdRowSections);
			}
			
			for(Entry<Integer, Integer> rowSctionEntry : rowSections.entrySet()) {
				Integer section = rowSctionEntry.getKey();
				Integer usdSeats = usdRowSections.get(section);
				if(usdSeats == null) {
					usdSeats = 0;
				}
				if(rowSctionEntry.getValue() >= usdSeats + requiredSeats) {
					ViewersAllocate viewersAllocate = new ViewersAllocate(rowNumber, section, requiredSeats);
					allocatedSeats.put(viewerName, viewersAllocate);
					usdRowSections.put(section, (usdSeats + requiredSeats));
					numberOfSeatsUsd += requiredSeats;
					return true;
				} 
			}
		
			ViewersAllocate viewersAllocate = new ViewersAllocate(DIFF_ROW);
			allocatedSeats.put(viewerName, viewersAllocate);
		}
		return false;
	}

	public Map<String, ViewersAllocate> seatsAllocation() {
		return allocatedSeats;
	}
}
