package com.wrapper;

import java.util.List;
import java.util.stream.Collectors;

import com.entity.Station;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class StationDTO {
	
	private Integer stationId;
	
	private String stationName;

	private List<BusDTO> busList;

	public StationDTO() {
	}

	public StationDTO(Station station) {
		this.stationId = station.getStationId();
		this.stationName = station.getStationname();
		if (station.getBusList() != null) {
			this.busList = station.getBusList().stream().map(b -> new BusDTO(b)).collect(Collectors.toList());
		}
	}

	public StationDTO(Integer stationId, String stationName) {
		this.stationId = stationId;
		this.stationName = stationName;
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public List<BusDTO> getBusList() {
		return busList;
	}

	public void setBusList(List<BusDTO> busList) {
		this.busList = busList;
	}
}
