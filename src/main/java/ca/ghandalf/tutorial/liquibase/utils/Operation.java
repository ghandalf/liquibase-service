package ca.ghandalf.tutorial.liquibase.utils;

import java.util.function.DoubleBinaryOperator;

import javax.ws.rs.Produces;

@Produces
public enum Operation implements DoubleBinaryOperator {
	PLUS("+", (l, r) -> l + r),
	MINUS("-", (l, r) -> l - r),
	MULTIPLY("*", (l, r) -> l * r),
	DIVIDE("/", (l, r) -> l / r);

	private final String which;
	private final DoubleBinaryOperator operator;

	private Operation(final String which, final DoubleBinaryOperator operator) {
		this.which = which;
		this.operator = operator;
	}

	public String getWhich() {
		return this.which;
	}

	/**
	 * Retrieve the <code>Operation</code> represent by is name
	 * 
	 * @param operation
	 * @return
	 */
	public static final Operation operationType(String operation) {
		for (Operation currentOperation : Operation.values()) {
			if (currentOperation.name().equalsIgnoreCase(operation)) {
				return currentOperation;
			}
		}
		String operations = "";
		for (Operation currentOperation : Operation.values()) {
			operations += currentOperation.name() + ", ";
		}
		throw new IllegalArgumentException(
				"Operation must be [" + operations.substring(0, operations.length() - 2) + "]");
	}

	public static final Operation getOperator(String which) {
		for (Operation currentOperation : Operation.values()) {
			if (currentOperation.getWhich().equalsIgnoreCase(which)) {
				return currentOperation;
			}
		}
		String operations = "";
		for (Operation currentOperation : Operation.values()) {
			operations += currentOperation.getWhich() + ", ";
		}
		throw new IllegalArgumentException(
				"Operator must be [" + operations.substring(0, operations.length() - 2) + "]");
	}

	@Override
	public double applyAsDouble(final double value, final double operand) {
		return operator.applyAsDouble(value, operand);
	}
}