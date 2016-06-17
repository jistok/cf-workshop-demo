package io.pivotal.cf.workshop.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.pivotal.cf.workshop.EnvironmentInfo;

/**
 * REST controller for environment information.
 * @author Brian Jimerson
 *
 */
@RestController
@RequestMapping(value = "/environment")
public class EnvironmentController {
	
	private static final Log log = LogFactory.getLog(EnvironmentController.class);
	
	/**
	 * Gets local environment information.
	 * 
	 * @return A JSON list of environment information.
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public @ResponseBody EnvironmentInfo getEnvironmentInfo() {
		Map<String, String> vcapApplication = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			vcapApplication = mapper.readValue(System.getenv("VCAP_APPLICATION"), HashMap.class);
		} catch (Exception e) {
			log.error(e);
		} 
		
		EnvironmentInfo info = new EnvironmentInfo();
		info.setApplicationName(vcapApplication.get("application_name"));
		info.setInstanceIndex(System.getenv("CF_INSTANCE_INDEX"));
		info.setInstanceIp(System.getenv("CF_INSTANCE_IP"));
		info.setInstancePort(System.getenv("CF_INSTANCE_PORT"));
//		info.setBoundServices(System.getenv("VCAP_SERVICES")); // todo parse bound services
//		info.setUri(vcapApplication.get("uris"));
		
		return info;
	}

}
