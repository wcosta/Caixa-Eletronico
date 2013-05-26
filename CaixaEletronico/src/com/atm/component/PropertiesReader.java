/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Walter
 */
public class PropertiesReader {
    Properties properties;
    public PropertiesReader() throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream("src/resources/atm.properties");
        properties = new Properties();
        properties.load(fis);
    }
    
    public String getMsg(String key) {
        return properties.getProperty(key);
    }
}
