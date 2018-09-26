package com.fresche.tutorial.liquibase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fresche.tutorial.liquibase.domain.entity.Item;

/**
 * Do we need to test this interface?
 * 
 * @author ghandalf
 *
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
