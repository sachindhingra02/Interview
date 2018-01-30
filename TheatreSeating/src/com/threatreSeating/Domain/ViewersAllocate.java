package com.threatreSeating.Domain;

public class ViewersAllocate {
	private int rowNumber;
	private int sectionNumber;
	private int numberOfSeats;
	private String diffRow;
	
	public ViewersAllocate(int rowNumber, int sectionNumber, int numberOfSeats) {
		this.rowNumber = rowNumber;
		this.sectionNumber = sectionNumber;
		this.numberOfSeats = numberOfSeats;
	}
	
	public ViewersAllocate(String diffRow) {
		this.diffRow = diffRow;
	}
	
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	public int getSectionNumber() {
		return sectionNumber;
	}
	public void setSectionNumber(int sectionNumber) {
		this.sectionNumber = sectionNumber;
	}
	public int getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public String getDiffRow() {
		return diffRow;
	}

	public void setDiffRow(String diffRow) {
		this.diffRow = diffRow;
	}

	@Override
	public String toString() {
		return "ViewersAllocate [rowNumber=" + rowNumber + ", sectionNumber="
				+ sectionNumber + ", numberOfSeats=" + numberOfSeats
				+ ", diffRow=" + diffRow + "]";
	}
}
