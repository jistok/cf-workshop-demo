package io.pivotal.workshop;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.util.FileCopyUtils;

public class CloudSqlDataSourceBuilder {
	
	/*
	 * FIXME: This fails.  What's needed is this:
	 * 
	 * private Connection connection;
	 * connection = DriverManager.getConnection(jdbcUrl, username, password);
	 */
	
	@Bean
	@Primary
	public DataSource dataSource() {
		return DataSourceBuilder.create().username(username).password(password).url(jdbcUrl)
				.driverClassName(jdbcDriverClass).build();
	}

	private static String instanceConnectionName;
	private static String databaseName;
	private static String username;
	private static String password;
	private static String jdbcUrl;
	private static String jdbcDriverClass;
	
	private static final String GOOGLE_APPLICATION_CREDENTIALS = "GOOGLE_APPLICATION_CREDENTIALS";
    private static final String GCP_CREDENTIALS_FILE_NAME = "GCP_credentials.json";

	static {	
		try {
			parseVcapServices();
		} catch (JSONException e1) {
			throw new RuntimeException(e1);
		}
        jdbcUrl = String.format(
                "jdbc:mysql://google/%s?cloudSqlInstance=%s&"
                        + "socketFactory=com.google.cloud.sql.mysql.SocketFactory",
                databaseName,
                instanceConnectionName);
	}
	
	private static void parseVcapServices() throws JSONException {
        // 1. Get the MySQL details.
        JSONObject mySqlCred = getCredObj("google-cloudsql-mysql");
        String projectId = null; // Get this later, from a different service binding
        String region = System.getenv("CLOUDSQL_REGION"); // FIXME: Get this from CloudSQL service binding
        String instanceName = mySqlCred.getString("instance_name");
        databaseName = mySqlCred.getString("database_name");
        username = mySqlCred.getString("Username");
        password = mySqlCred.getString("Password");

        // 2. Get remaining parts from Storage binding.
        JSONObject storageCred = getCredObj("google-storage");
        projectId = storageCred.getString("ProjectId");
        instanceConnectionName = String.join(":", projectId, region, instanceName);

        // 3. Write out the GOOGLE_APPLICATION_CREDENTIALS file and set up environment variable.
        String privateKeyData = storageCred.getString("PrivateKeyData");
        setupCredentials(privateKeyData);
    }

    private static JSONObject getCredObj (String vcapKey) throws JSONException {
        String env = System.getenv("VCAP_SERVICES");
        JSONObject json = new JSONObject(env);
        JSONArray root = json.getJSONArray(vcapKey);
        JSONObject obj0 = root.getJSONObject(0);
        return obj0.getJSONObject("credentials");
    }

    private static void setupCredentials(String privateKey) {
        InputStream in = new ByteArrayInputStream(Base64.getDecoder().decode(privateKey));
        File gcpJsonFile = new File(System.getProperty("java.io.tmpdir"), GCP_CREDENTIALS_FILE_NAME);
        writeInputStreamToFile(in, gcpJsonFile);

        Map<String, String> replEnv = new HashMap<>();
        replEnv.put(GOOGLE_APPLICATION_CREDENTIALS, gcpJsonFile.getPath());
        setEnv(replEnv);
    }

    private static void writeInputStreamToFile (InputStream is, File outFile) {
        try {
            FileCopyUtils.copy(is, new FileOutputStream(outFile));
        } catch (IOException e) {
            throw new RuntimeException("Failed while creating " + GCP_CREDENTIALS_FILE_NAME + " file", e);
        }
    }

    private static void setEnv(Map<String, String> newenv) {
        try {
            Class<?>[] classes = Collections.class.getDeclaredClasses();
            Map<String, String> env = System.getenv();
            for (Class<?> cl : classes) {
                if ("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
                    Field field = cl.getDeclaredField("m");
                    field.setAccessible(true);
                    Object obj = field.get(env);
                    @SuppressWarnings("unchecked")
                    Map<String, String> map = (Map<String, String>) obj;
                    map.clear();
                    map.putAll(newenv);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed while setting " + GOOGLE_APPLICATION_CREDENTIALS + " environment variable.", e);
        }
    }

}