package com.fresche.tutorial.liquibase.service;

import java.util.List;

import com.fresche.tutorial.liquibase.domain.entity.Item;

public interface ItemService {

	public Item create(Item item);

	public Item read(Long id);

	public Item update(Item item);

	public void delete(Item item);
	
	public List<Item> findAll();
	
}
