package com.app.carrental.beans;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonCreator;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonProperty;

public final class RentalCarBeans {
	    public final Car cars[];

	    @JsonCreator
	    public RentalCarBeans(@JsonProperty("cars") Car[] cars){
	        this.cars = cars;
	    }

	    public static final class Car {
	        public final String make;
	        public final String model;
	        public final String vin;
	        public final Metadata metadata;
	        public final Perdayrent perdayrent;
	        public final Metrics metrics;

	        @JsonCreator
	        public Car(@JsonProperty("make") String make, @JsonProperty("model") String model, @JsonProperty("vin") String vin, @JsonProperty("metadata") Metadata metadata, @JsonProperty("perdayrent") Perdayrent perdayrent, @JsonProperty("metrics") Metrics metrics){
	            this.make = make;
	            this.model = model;
	            this.vin = vin;
	            this.metadata = metadata;
	            this.perdayrent = perdayrent;
	            this.metrics = metrics;
	        }

	        public static final class Metadata {
	            public final String color;
	            public final String notes;
	    
	            @JsonCreator
	            public Metadata(@JsonProperty("color") String color, @JsonProperty("notes") String notes){
	                this.color = color;
	                this.notes = notes;
	            }
	        }

	        public static final class Perdayrent {
	            public final long price;
	            public final long discount;
	    
	            @JsonCreator
	            public Perdayrent(@JsonProperty("price") long price, @JsonProperty("discount") long discount){
	                this.price = price;
	                this.discount = discount;
	            }
	        }

	        public static final class Metrics {
	            public final double yoymaintenancecost;
	            public final double depreciation;
	            public final Rentalcount rentalcount;
	    
	            @JsonCreator
	            public Metrics(@JsonProperty("yoymaintenancecost") double yoymaintenancecost, @JsonProperty("depreciation") double depreciation, @JsonProperty("rentalcount") Rentalcount rentalcount){
	                this.yoymaintenancecost = yoymaintenancecost;
	                this.depreciation = depreciation;
	                this.rentalcount = rentalcount;
	            }
	    
	            public static final class Rentalcount {
	                public final long lastweek;
	                public final long yeartodate;
	        
	                @JsonCreator
	                public Rentalcount(@JsonProperty("lastweek") long lastweek, @JsonProperty("yeartodate") long yeartodate){
	                    this.lastweek = lastweek;
	                    this.yeartodate = yeartodate;
	                }
	            }
	        }
	    }
	}


