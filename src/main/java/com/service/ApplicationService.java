package com.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

	@Value("${bus.acFare}")
	private int acFare;

	@Value("${bus.nonAcFare}")
	private int nonAcFare;
	
	@Value("${bus.sittingAcFare}")
	private int sittingAcFare;
	
	@Value("${bus.sitting}")
	private int sitting;

	public int getAcFare() {
		return acFare;
	}

	public int getNonAcFare() {
		return nonAcFare;
	}

	public int getSittingAcFare() {
		return sittingAcFare;
	}

	public int getSitting() {
		return sitting;
	}	

}
