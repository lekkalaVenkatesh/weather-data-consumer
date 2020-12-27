package com.weather.weatherdataconsumer.dto;

import com.weather.weatherdataconsumer.enums.ObservatoryEnum;

public class ObservatoryCount {

	private ObservatoryEnum observatory;
    private Long count;

    public ObservatoryCount(ObservatoryEnum observatory, Long count) {
        this.observatory = observatory;
        this.count = count;
    }

	public ObservatoryEnum getObservatory() {
		return observatory;
	}

	public void setObservatory(ObservatoryEnum observatory) {
		this.observatory = observatory;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
    
}
