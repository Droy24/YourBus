package com;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "station")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "stationId",scope = Station.class)
public class Station {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stationId == null) ? 0 : stationId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		if (stationId == null) {
			if (other.stationId != null)
				return false;
		} else if (!stationId.equals(other.stationId))
			return false;
		return true;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer stationId;

	private String stationName;

	public Station(Integer stationId, String stationname, List<Bus> busList) {
		this.stationId = stationId;
		this.stationName = stationname;
		this.busList = busList;
	}

	public String getStationname() {
		return stationName;
	}

	public void setStationname(String stationname) {
		this.stationName = stationname;
	}

	public Integer getStationId() {
		return stationId;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "stops")
	List<Bus> busList;

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public Station() {
	}

	public Station(Integer stationId) {
		this.stationId = stationId;
	}

	public List<Bus> getBusList() {
		return this.busList;
	}

	public void setBusList(List<Bus> busList) {
		this.busList = busList;
	}

	@Override
	public String toString() {
		return "Station [stationId=" + stationId + ", stationName=" + stationName + ", busList=" + busList + "]";
	}
}