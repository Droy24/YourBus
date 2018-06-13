package com;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "route")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "routeId")
public class Route {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer routeId;
	
	private String source;
	
	private String destination;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name="Route_stations")
	private List<Station> stops;
	
	private LinkedList<Integer> distance;
	
	public int getTotalDistance(Station start,Station end) {
		int startIndex=stops.indexOf(start);
		int endIndex=stops.indexOf(end);
		int totalDistance=0;
		int i;
		for(i=startIndex;i<=endIndex;i++)
		{
			totalDistance+= distance.get(i);
		}
		return totalDistance;
	}
	
	public void setDistance(LinkedList<Integer> distance) {
		this.distance = distance;
	}
	
	/*
	public void addStationInList(Station addst) {
		route.add(addst);
	}
	
	public void addAfterStation(Station station1, Station addStation) {
		int index = this.route.indexOf(station1);
		this.route.add(index, addStation);
	}
	*/
	public int getRouteId() {
		return this.routeId;
	}
	
	public void setRouteId(int routeid) {
		this.routeId = routeid;
	}
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public List<Station> getStops() {
		return stops;
	}
	
	public void setStops(List<Station> stops) {
		this.stops = stops;
	}
	
}