package io.pivotal.cf.workshop.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
		
		String uris = "[none]";
		String boundServices = "[none]";
		String applicationName = "[none]";
		
		JsonParser jsonParser = new JacksonJsonParser();
		
		//VCAP_APPLICATION
		if (System.getenv("VCAP_APPLICATION") != null) {
			Map<String, Object> vcapApplicationMap = 
					jsonParser.parseMap(System.getenv("VCAP_APPLICATION"));
			if (vcapApplicationMap.containsKey("application_name")) {
				applicationName = (String) vcapApplicationMap.get("application_name");
			}
			List<String> uriList = (List<String>) vcapApplicationMap.get("uris");
			Iterator<String> uriIterator = uriList.iterator();
			while (uriIterator.hasNext()) {
				StringBuilder uriBuilder = new StringBuilder();
				uriBuilder.append(uriIterator.next());
				if (uriIterator.hasNext()) {
					uriBuilder.append(", ");
				}
				uris = uriBuilder.toString();
			}
		}
		
		//VCAP_SERVICES
		if (System.getenv("VCAP_SERVICES") != null) {
			Map<String, Object> vcapServicesMap = 
					jsonParser.parseMap(System.getenv("VCAP_SERVICES"));
			if ((vcapServicesMap != null) && (vcapServicesMap.keySet().size() > 0)) {
				StringBuilder servicesBuilder = new StringBuilder();
				Iterator<String> servicesIterator = vcapServicesMap.keySet().iterator();
				while (servicesIterator.hasNext()) {
					String nextKey = servicesIterator.next();
					servicesBuilder.append(nextKey);
					if (servicesIterator.hasNext()) {
						servicesBuilder.append(", ");
					}
				}
				boundServices = servicesBuilder.toString();
			}
		}
		
		EnvironmentInfo info = new EnvironmentInfo();
		info.setApplicationName(applicationName);
		info.setInstanceIndex(System.getenv("CF_INSTANCE_INDEX"));
		info.setInstanceIp(System.getenv("CF_INSTANCE_IP"));
		info.setInstancePort(System.getenv("CF_INSTANCE_PORT"));
		info.setUris(uris);
		info.setBoundServices(boundServices);
		
		log.debug(String.format("Environment info = [%s]", info));
		return info;
	}
	
	/**
	 * Kills the JVM process
	 */
	@RequestMapping(value = "/kill", method = RequestMethod.GET)
	public void killProcess() {
		
		log.warn("Shutting down the JVM process.");
		System.exit(0);
	}

}
