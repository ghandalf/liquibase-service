package ca.ghandalf.tutorial.liquibase.business;

import org.springframework.stereotype.Component;

@Component
public class Venus extends Planet {

	public Venus() {
		this.setDensity(5243);
		this.setDiameter(12104);
		this.setGravity(8.9);
		this.setMass(4.87);
	}

}
