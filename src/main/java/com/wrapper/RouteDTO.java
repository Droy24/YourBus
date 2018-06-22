package com.wrapper;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.entity.Route;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RouteDTO {

	private Integer routeId;

	private String source;

	private String destination;

	private List<StationDTO> stops;

	private LinkedList<Integer> distance;

	public RouteDTO() {
	}

	public RouteDTO(Route route) {
		this.routeId = route.getRouteId();
		this.source = route.getSource();
		this.destination = route.getDestination();
		if (route.getStops() != null) {
			this.stops = route.getStops().stream().map(s -> new StationDTO(s)).collect(Collectors.toList());
		}
		this.distance = route.getDistance();
	}

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
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

	public List<StationDTO> getStops() {
		return stops;
	}

	public void setStops(List<StationDTO> stops) {
		this.stops = stops;
	}

	public LinkedList<Integer> getDistance() {
		return distance;
	}

	public void setDistance(LinkedList<Integer> distance) {
		this.distance = distance;
	}

}