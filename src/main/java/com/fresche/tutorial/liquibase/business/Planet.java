package com.fresche.tutorial.liquibase.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fresche.tutorial.liquibase.utils.Operation;

/**
 * Why do I have this class?
 * 
 * @author ghandalf
 *
 */
public abstract class Planet {
	
	private static final Logger logger = LoggerFactory.getLogger(Planet.class);
	
	public static final String LOG_KEY = "thread";
	
	private double mass;
	private int diameter;
	private int density;
	private double gravity;

	public void mathExercise() {
		logger.info("We are going to do some math.");
		logger.info("\t {} + 4 = {}", getGravity(), Operation.PLUS.applyAsDouble(getGravity(), 4));
		logger.info("\t {} - 4 = {}", getGravity(), Operation.MINUS.applyAsDouble(getGravity(), 4));
		logger.info("\t {} * 4 = {}", getGravity(), Operation.MULTIPLY.applyAsDouble(getGravity(), 4));
		logger.info("\t {} / 4 = {}", getGravity(), Operation.DIVIDE.applyAsDouble(getGravity(), 4));
		logger.info("{}", toString());
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	public int getDensity() {
		return density;
	}

	public void setDensity(int density) {
		this.density = density;
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [mass=" + mass + "  10 exp 24kg, diameter=" + diameter + " km, density=" + density + " kg/m exp 3, gravity=" + gravity
				+ " m/s exp 2]\n";
	}
}
