package com.app.carrental.beans;

public class Cars {
		
	private String make;
	private String model;
	private String vin;
	private String metadataColor;
	private String metadataNotes;
	private float perdayrentPrice;
	private float perdayrentDiscount;
	private float metricsMaintanenceCost;
	private float metricsDepreciation;
	private float metricsRentalCountLastweek;
	private float metricsRentalCountYeartodate;
	
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
	public String getMetadataColor() {
		return metadataColor;
	}
	public void setMetadataColor(String metadataColor) {
		this.metadataColor = metadataColor;
	}
	public String getMetadataNotes() {
		return metadataNotes;
	}
	public void setMetadataNotes(String metadataNotes) {
		this.metadataNotes = metadataNotes;
	}
	public float getPerdayrentPrice() {
		return perdayrentPrice;
	}
	public void setPerdayrentPrice(float perdayrentPrice) {
		this.perdayrentPrice = perdayrentPrice;
	}
	public float getPerdayrentDiscount() {
		return perdayrentDiscount;
	}
	public void setPerdayrentDiscount(float perdayrentDiscount) {
		this.perdayrentDiscount = perdayrentDiscount;
	}
	public float getMetricsMaintanenceCost() {
		return metricsMaintanenceCost;
	}
	public void setMetricsMaintanenceCost(float metricsMaintanenceCost) {
		this.metricsMaintanenceCost = metricsMaintanenceCost;
	}
	public float getMetricsDepreciation() {
		return metricsDepreciation;
	}
	public void setMetricsDepreciation(float metricsDepreciation) {
		this.metricsDepreciation = metricsDepreciation;
	}
	public float getMetricsRentalCountLastweek() {
		return metricsRentalCountLastweek;
	}
	public void setMetricsRentalCountLastweek(float metricsRentalCountLastweek) {
		this.metricsRentalCountLastweek = metricsRentalCountLastweek;
	}
	public float getMetricsRentalCountYeartodate() {
		return metricsRentalCountYeartodate;
	}
	public void setMetricsRentalCountYeartodate(float metricsRentalCountYeartodate) {
		this.metricsRentalCountYeartodate = metricsRentalCountYeartodate;
	}
	
}
