package ca.ghandalf.tutorial.liquibase.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import ca.ghandalf.tutorial.liquibase.domain.entity.Item;
import ca.ghandalf.tutorial.liquibase.utils.Operation;


/**
 * What is it missing in this class?
 * 
 * @author ghandalf
 *
 */
@Component
public class ItemDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Item item;

	private List<Item> items;
	
	private Double operator;
	
	private Operation operationType;

	public ItemDTO() {
		this.item = new Item();
		this.items = new ArrayList<>();
		this.operator = 0.0;
		this.operationType = Operation.PLUS;
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Double getOperator() {
		return operator;
	}

	public void setOperator(Double operator) {
		this.operator = operator;
	}

	public Operation getOperationType() {
		return operationType;
	}

	public void setOperationType(Operation operationType) {
		this.operationType = operationType;
	}

	public Operation getOperationType(String operation) {
		return Operation.operationType(operation);
	}
	
	// To get use to some new features
	@Override
	public String toString() {
		return this.getClass().getSimpleName() 
				+ " [" + Objects.toString(this.getItem(), "isNull")
				+ ", operator: " + this.getOperator()
				+ ", operationTyoe: " + this.getOperationType()
				+ ", Items: [" + this.getItems().stream().map(i -> i.toString()).reduce("", String::concat)
				+ "]]";
	}
	
}
