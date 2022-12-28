package com.example.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class PropertiesManager {

    Properties getProperties(String propertiesSrc){
		Properties prop1 = new Properties();

		try (InputStream input = new FileInputStream(propertiesSrc)) {
            prop1.load(input);
        } catch (Exception ex) {
			System.err.println("Fail to load email properties");
        }

		return prop1;
	}
    
}