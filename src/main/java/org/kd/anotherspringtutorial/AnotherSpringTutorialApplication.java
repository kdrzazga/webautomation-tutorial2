package org.kd.anotherspringtutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AnotherSpringTutorialApplication {

	public static void main(String[] args) {
		try (ConfigurableApplicationContext context =
					 SpringApplication.run(AnotherSpringTutorialApplication.class, args)) {
			System.out.println("context: " + context);
		}
	}

}
