package io.pivotal.cf.workshop;

/**
 * Simple class to store environment information.
 * @author Brian Jimerson
 *
 */
public class EnvironmentInfo {

	private String applicationName;
	private String uris;
	private String instanceIndex;
	private String instanceIp;
	private String instancePort;
	private String boundServices;
	
	/**
	 * Gets the application name
	 * @return the applicationName
	 */
	public String getApplicationName() {
		return applicationName;
	}
	
	/**
	 * Sets the application name
	 * @param applicationName the applicationName to set
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	
	/**
	 * Gets the uris
	 * @return the uri
	 */
	public String getUris() {
		return uris;
	}
	
	/**
	 * Sets the uris
	 * @param uris the uris to set
	 */
	public void setUris(String uris) {
		this.uris = uris;
	}
	
	/**
	 * Gets the instance index
	 * @return the instanceIndex
	 */
	public String getInstanceIndex() {
		return instanceIndex;
	}
	
	/**
	 * Sets the instance index
	 * @param instanceIndex the instanceIndex to set
	 */
	public void setInstanceIndex(String instanceIndex) {
		this.instanceIndex = instanceIndex;
	}
	
	/**
	 * Gets the instance IP
	 * @return the instanceIp
	 */
	public String getInstanceIp() {
		return instanceIp;
	}
	
	/**
	 * Sets the instance IP
	 * @param instanceIp the instanceIp to set
	 */
	public void setInstanceIp(String instanceIp) {
		this.instanceIp = instanceIp;
	}
	
	/**
	 * Gets the instance port
	 * @return the instancePort
	 */
	public String getInstancePort() {
		return instancePort;
	}
	
	/**
	 * Sets the instance port
	 * @param instancePort the instancePort to set
	 */
	public void setInstancePort(String instancePort) {
		this.instancePort = instancePort;
	}
	
	/**
	 * Gets the bound services
	 * @return the boundServices
	 */
	public String getBoundServices() {
		return boundServices;
	}
	
	/**
	 * Sets the bound services
	 * @param boundServices the boundServices to set
	 */
	public void setBoundServices(String boundServices) {
		this.boundServices = boundServices;
	}
	
	/**
	 * Overrides Object's toString method to return the
	 * state of the Environemnt object.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Environment = [applicationName: ");
		sb.append(applicationName);
		sb.append(", uris: ");
		sb.append(uris);
		sb.append(", instanceIndex: ");
		sb.append(instanceIndex);
		sb.append(", instanceIp: ");
		sb.append(instanceIp);
		sb.append(", instancePort: ");
		sb.append(instancePort);
		sb.append(", boundServices: ");
		sb.append(boundServices);
		sb.append("]");		
		return sb.toString();
	}

}
