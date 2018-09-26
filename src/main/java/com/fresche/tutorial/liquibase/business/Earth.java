package com.fresche.tutorial.liquibase.business;

import org.springframework.stereotype.Component;

@Component
public class Earth extends Planet {

	public Earth() {
		this.setDensity(5514);
		this.setDiameter(12756);
		this.setGravity(9.8);
		this.setMass(5.97);
	}
	
}
