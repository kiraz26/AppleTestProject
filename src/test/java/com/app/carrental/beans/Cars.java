package com.app.carrental.beans;

import java.util.Map;

import com.app.carrental.beans.RentalCarBeans.Car.Metrics;

public class Cars {
	private String make;
	private String model;
	private String vin;
	private Map<String, String> metadata;
	private Map<String, Float> perdayrent;
	private Metrics metrics;
	

	public Metrics getMetrics() {
		return metrics;
	}

	public void setMetrics(Metrics metrics) {
		this.metrics = metrics;
	}

	

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public Map<String, Float> getPerdayrent() {
		return perdayrent;
	}

	public void setPerdayrent(Map<String, Float> perdayrent) {
		this.perdayrent = perdayrent;
	}

}
