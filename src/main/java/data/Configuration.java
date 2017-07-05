package data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

	static {
		loadProperties();
	}
	
	private static Properties properties;
	
	// General
	private static String machineName;
	
	// Rest
	private static int serverPort;
	
	// MariaDB
	private static String mariaDBUsername;
	private static String mariaDBPassword;
	private static String mariaDBAddress;
	private static int mariaDBPort;
	private static String mariaDBDatabaseName;
	
	private static void loadProperties() {
		properties = new Properties();
		InputStream is = Configuration.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			properties.load(is);
			serverPort = Integer.parseInt(properties.getProperty("serverPort", "8080"));
			machineName = properties.getProperty("machineName");
			mariaDBUsername = properties.getProperty("mariaDBUsername");
			mariaDBPassword = properties.getProperty("mariaDBPassword");
			mariaDBAddress = properties.getProperty("mariaDBAddress");
			mariaDBPort = Integer.parseInt(properties.getProperty("mariaDBPort"));
			mariaDBDatabaseName = properties.getProperty("mariaDBDatabaseName");
			// TODO 2: include check for mandatory fields
		} catch (IOException | NumberFormatException e) {
			System.err.println("Error while loading property file");
			e.printStackTrace();
		}
	}

	// ************************************************************
	// Generated Code
	// ************************************************************
	
	public static int getServerPort() {
		return serverPort;
	}

	public static void setServerPort(int serverPort) {
		Configuration.serverPort = serverPort;
	}

	public static String getMachineName() {
		return machineName;
	}

	public static void setMachineName(String machineName) {
		Configuration.machineName = machineName;
	}

	public static String getMariaDBUsername() {
		return mariaDBUsername;
	}

	public static void setMariaDBUsername(String mariaDBUsername) {
		Configuration.mariaDBUsername = mariaDBUsername;
	}

	public static String getMariaDBPassword() {
		return mariaDBPassword;
	}

	public static void setMariaDBPassword(String mariaDBPassword) {
		Configuration.mariaDBPassword = mariaDBPassword;
	}

	public static String getMariaDBAddress() {
		return mariaDBAddress;
	}

	public static void setMariaDBAddress(String mariaDBAddress) {
		Configuration.mariaDBAddress = mariaDBAddress;
	}

	public static int getMariaDBPort() {
		return mariaDBPort;
	}

	public static void setMariaDBPort(int mariaDBPort) {
		Configuration.mariaDBPort = mariaDBPort;
	}

	public static String getMariaDBDatabaseName() {
		return mariaDBDatabaseName;
	}

	public static void setMariaDBDatabaseName(String mariaDBDatabaseName) {
		Configuration.mariaDBDatabaseName = mariaDBDatabaseName;
	}
	
}
