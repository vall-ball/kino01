package ru.vallball.kino01.model;

import java.util.ArrayList;
import java.util.List;

public class Row {

	private int row;
	
	private List<Place> places = new ArrayList<>();

	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public Row(int row) {
		super();
		this.row = row;
	}

}
