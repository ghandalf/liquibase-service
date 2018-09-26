package com.fresche.tutorial.liquibase.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.fresche.tutorial.liquibase.domain.entity.Item;
import com.fresche.tutorial.liquibase.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

	@Inject
	private ItemRepository calculatorRepository;
	
	@Override
	public Item create(Item item) {
		return this.calculatorRepository.save(item);
	}

	@Override
	public Item read(Long id) {
		return this.calculatorRepository.getOne(id);
	}

	@Override
	public Item update(Item item) {
		return this.calculatorRepository.saveAndFlush(item);
	}

	@Override
	public void delete(Item item) {
		this.calculatorRepository.delete(item);
	}

	@Override
	public List<Item> findAll() {
		return this.calculatorRepository.findAll();
	}
}
