package com.fresche.tutorial.liquibase.business;

import org.springframework.stereotype.Component;

@Component
public class Mars extends Planet {

	public Mars() {
		this.setDensity(3933);
		this.setDiameter(6792);
		this.setGravity(3.7);
		this.setMass(0.642);
	}

}
