package org.kd.anotherspringtutorial.unit;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.kd.anotherspringtutorial.test.config.ProfileManager;
import org.kd.anotherspringtutorial.test.utils.report.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ComponentScan(basePackages = {"org.kd.anotherspringtutorial"})
class AnotherSpringTutorialApplicationTests {

	@Autowired
	private ProfileManager profileManager;

	@Autowired
	private Report report;

	@Test
	void contextLoads() {
	}

	@Test
	public void testProfileManager(){
		assertThat(profileManager, Matchers.notNullValue());
		assertThat(profileManager.getActiveProfiles(), Matchers.not(Matchers.emptyString()));
	}

	@Test
	public void testReport(){

		String generatedReport = report.generate();
		assertThat(generatedReport, Matchers.notNullValue());

		System.out.println("Generated report:\n" + generatedReport);
	}
}
