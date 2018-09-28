package ca.ghandalf.tutorial.liquibase.business;

import ca.ghandalf.tutorial.liquibase.business.Planet;
import ca.ghandalf.tutorial.liquibase.business.Mercury;
import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.MDC;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={Mercury.class})
public class MercuryTest {

	@Inject
	private Mercury planet;
	
	@Test
	public void mathExercise() {
		MDC.remove(Planet.LOG_KEY);
		MDC.put(Planet.LOG_KEY, planet.getClass().getSimpleName());
		planet.mathExercise();
	}
}
