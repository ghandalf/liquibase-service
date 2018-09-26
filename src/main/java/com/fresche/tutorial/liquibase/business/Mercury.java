package com.fresche.tutorial.liquibase.business;

import org.springframework.stereotype.Component;

@Component
public class Mercury extends Planet {

	public Mercury() {
		this.setDensity(5427);
		this.setDiameter(4879);
		this.setGravity(3.7);
		this.setMass(0.330);
	}

}
