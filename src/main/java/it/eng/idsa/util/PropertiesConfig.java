package it.eng.idsa.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class PropertiesConfig {
	private final Properties configProp = new Properties();

	private PropertiesConfig() {
		try {
			InputStream in = new FileInputStream("config.properties");
			configProp.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class LazyHolder {
		private static final PropertiesConfig INSTANCE = new PropertiesConfig();
	}

	public static PropertiesConfig getInstance() {
		return LazyHolder.INSTANCE;
	}

	public String getProperty(String key) {
		return configProp.getProperty(key);
	}

	public Set<String> getAllPropertyNames() {
		return configProp.stringPropertyNames();
	}

	public boolean containsKey(String key) {
		return configProp.containsKey(key);
	}
}
