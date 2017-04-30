package io.pivotal.workshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main entry point for the application.
 * @author Brian Jimerson
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CfWorkshopDemoApplication {

	/**
	 * The main entry point for the application.
	 * @param args Any command line args passed.
	 */
	public static void main(String[] args) {
		SpringApplication.run(CfWorkshopDemoApplication.class, args);
	}
}
