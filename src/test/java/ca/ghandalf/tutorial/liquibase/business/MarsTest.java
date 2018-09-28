package ca.ghandalf.tutorial.liquibase.business;

import ca.ghandalf.tutorial.liquibase.business.Mars;
import ca.ghandalf.tutorial.liquibase.business.Planet;
import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.MDC;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { Mars.class })
public class MarsTest {

	@Inject
	private Mars planet;

	@Test
	public void mathExercise() {
		MDC.remove(Planet.LOG_KEY);
		MDC.put(Planet.LOG_KEY, planet.getClass().getSimpleName());
		planet.mathExercise();
	}
}
