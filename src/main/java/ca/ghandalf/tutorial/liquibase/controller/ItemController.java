package ca.ghandalf.tutorial.liquibase.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.ghandalf.tutorial.liquibase.domain.dto.ItemDTO;
import ca.ghandalf.tutorial.liquibase.domain.entity.Item;
import ca.ghandalf.tutorial.liquibase.service.ItemService;

/**
 * What does this annotation @CrossOrigin(origins = "http://localhost:4200")?
 * 
 * @author ghandalf
 *
 */
// see: https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
@RestController
@RequestMapping("liquibase/item")
public class ItemController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);
	
	@Inject
	private ItemService calculatorService;
	
	@Inject
	private ItemDTO calculator;
	
        // To access via url has to be http://localhost:<find the port number in the application.yaml>/liquibase/item/
	@CrossOrigin//(origins = "http://localhost:4200")
	@RequestMapping("/root")
	public String root() {
		LOGGER.info("A call as been done for root method");
		return "Welcome to Calculator";
	}
	
	// Using Postman tool pass those two lines in Body (raw) and use http://localhost:<find the port number in the application.yaml>/liquibase/item
	// {"id":233,"sku":23333,"name":"SamsungTV 3","description":"Samsung Smart TV 3","price":233.00}
	// {"id":244,"sku":24444,"name":"SamsungTV 4","description":"Samsung Smart TV 4","price":244.00}
	@CrossOrigin//(origins = "http://localhost:4200")
	@RequestMapping(method=RequestMethod.POST)
	public ItemDTO create(@RequestBody @Valid Item item) {
		LOGGER.info("");
		LOGGER.info("*********** BEGIN CREATE ************");
		LOGGER.info(item.toString());

		calculator.setItem(calculatorService.create(item));
		calculator.setItems(calculatorService.findAll());
		LOGGER.info(calculator.toString());
		LOGGER.info("*********** END CREATE ************\n");
		
		return calculator;
	}
	
	// Pass in header from SOAP: 
	// liquibase/item?id=233
	// liquibase/item?id=244
	@CrossOrigin//(origins = "http://localhost:4200")
	@RequestMapping(method=RequestMethod.GET)
	public ItemDTO read(@RequestParam("id") Long id) {
		LOGGER.info("");
		LOGGER.info("*********** BEGIN READ ************");
                Item item = calculatorService.read(id);
                LOGGER.info("ITEM: {}", item.toString());
		calculator.setItem(item);
		calculator.setItems(calculatorService.findAll());
		LOGGER.info(calculator.toString());
		LOGGER.info("*********** END READ ************\n");
		
		return calculator;
	}

	// Pass in Body from SOAP: 
	// {"id":233,"sku":23333,"name":"SamsungTV 3 updated","description":"Samsung Smart TV 3 updated","price":333.00}
	// {"id":244,"sku":24444,"name":"SamsungTV 4 updated","description":"Samsung Smart TV 4 updated","price":444.00}
	@CrossOrigin//(origins = "http://localhost:4200")
	@RequestMapping(method=RequestMethod.PUT)
	public ItemDTO update(@RequestBody @Valid Item item) {
		LOGGER.info("");
		LOGGER.info("*********** BEGIN UPDATE ************");
		LOGGER.info(item.toString());
		calculator.setItem(calculatorService.update(item));		
		calculator.setItems(calculatorService.findAll());
		LOGGER.info(calculator.toString());
		LOGGER.info("*********** END UPDATE ************\n");
		
		return calculator;
	}

	// Pass in Body from SOAP: 
	// {"id":233,"sku":23333,"name":"SamsungTV 3 updated","description":"Samsung Smart TV 3 updated","price":333.00}
	// {"id":244,"sku":24444,"name":"SamsungTV 4 updated","description":"Samsung Smart TV 4 updated","price":444.00}
	@CrossOrigin//(origins = "http://localhost:4200")
	@RequestMapping(method=RequestMethod.DELETE)
	public ItemDTO delete(@RequestBody @Valid Item item) {
		LOGGER.info("");
		LOGGER.info("*********** BEGIN DELETE ************");
		calculatorService.delete(item);
		calculator.setItem(null);
		calculator.setItems(calculatorService.findAll());
		LOGGER.info(calculator.toString());
		LOGGER.info("*********** END DELETE ************\n");
		
		return calculator;
	}

	// To access via url has to be http://localhost:<find the port number in the application.yaml>/liquibase/item/
	@CrossOrigin//(origins = "http://localhost:4200")
	@RequestMapping("/findAll")
	public List<Item> findAll() {
		LOGGER.info("");
		LOGGER.info("*********** BEGIN FINDALL ************");
		LOGGER.info("++++++++++++++++++++++++ " + calculatorService.findAll());
		
		calculator.setItems(calculatorService.findAll());
		if (!calculatorService.findAll().isEmpty()) {
			 calculator.setItem(calculator.getItems().get(0));
		}
		
		LOGGER.info(calculator.toString());
		LOGGER.info("*********** END FINDALL ************\n");
		
		return calculatorService.findAll();
	}
	
	// Pass in Body from SOAP: 
	// Header: ?value=333.00&operationType=plus
	// 	Body: {"id":233,"sku":23333,"name":"SamsungTV 3 updated","description":"Samsung Smart TV 3 updated","price":333.00}
	//		Result price=666.00
	// Header: ?value=111.00&operationType=minus
	// 	Body: {"id":233,"sku":23333,"name":"SamsungTV 3 updated","description":"Samsung Smart TV 3 updated","price":666.00}
	//		Result price=555.00
	// Header: ?value=2&operationType=multiply
	// 	Body: {"id":233,"sku":23333,"name":"SamsungTV 3 updated","description":"Samsung Smart TV 3 updated","price":555.00}
	//		Result price=1110.00
	// Header: ?value=10&operationType=divide
	// 	Body: {"id":233,"sku":23333,"name":"SamsungTV 3 updated","description":"Samsung Smart TV 3 updated","price":1110.00}
	//		Result price=111.00
	@CrossOrigin//(origins = "http://localhost:4200")
	@RequestMapping("modify")
	public ItemDTO modify(
			@RequestBody @Valid Item item, 
			@RequestParam("value") double value, 
			@RequestParam("operationType") String operationType) {
		LOGGER.info("");
		LOGGER.info("*********** BEGIN MODIFY ************");
		LOGGER.info(item.toString());
		LOGGER.info("value:" + value);
		LOGGER.info("operationType: " + operationType);

		calculator.setOperationType(calculator.getOperationType(operationType));
		calculator.setOperator(value);
		LOGGER.info(calculator.toString());
		
		item.setPrice(calculator.getOperationType().applyAsDouble(item.getPrice(), calculator.getOperator()));
		calculator.setItem(calculatorService.update(item));
		
		calculator.setItems(calculatorService.findAll());
		LOGGER.info(calculator.toString());
		LOGGER.info("*********** END MODIFY ************\n");
		
		return calculator;
	}
	
	/** 
	{
	   "item":    {
	      "id": 456,
	      "sku": 456456,
	      "name": "Laptop Asus",
	      "description": "16G Ram",
	      "price": 1890.00
	   },
	   "items": [   {
	      "id": 456,
	      "sku": 456456,
	      "name": "Laptop Asus",
	      "description": "16G Ram",
	      "price": 1890.00
	   }],
	   "operator": 10,
	   "operationType": "DIVIDE"
	}
	*/
	@CrossOrigin//(origins = "http://localhost:4200")
	@RequestMapping("calculate")
	public ItemDTO calculate(@RequestBody @Valid ItemDTO calculatorDTO) {
		LOGGER.info("");
		LOGGER.info("*********** BEGIN CALCULATE ************");
		LOGGER.info("Actual Price of the item: " + calculatorDTO.getItem().getPrice());
		LOGGER.info("Operator to be used:" + calculatorDTO.getOperator());
		LOGGER.info("Operation Type to be used: " + calculatorDTO.getOperationType().name());
		
		LOGGER.info("Before calculation:: " + calculatorDTO.toString());
		Item item = calculatorDTO.getItem();
		item.setPrice(calculatorDTO.getOperationType().applyAsDouble(item.getPrice(), calculatorDTO.getOperator()));

		calculatorService.update(item);
		calculator.setItem(item);
		calculator.setItems(calculatorService.findAll());
		
		LOGGER.info("After calculation:: " + calculator.toString());
		LOGGER.info("*********** END CALCULATE ************\n");
		
		return calculator;
	}
	
}
