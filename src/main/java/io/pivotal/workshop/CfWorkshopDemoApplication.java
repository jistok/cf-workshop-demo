package io.pivotal.workshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the application.
 * @author Brian Jimerson
 *
 */
@SpringBootApplication
public class CfWorkshopDemoApplication {

	/**
	 * The main entry point for the application.
	 * @param args Any command line args passed.
	 */
	public static void main(String[] args) {
		SpringApplication.run(CfWorkshopDemoApplication.class, args);
	}
}
