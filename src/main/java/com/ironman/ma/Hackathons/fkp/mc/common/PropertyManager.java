package com.ironman.ma.Hackathons.fkp.mc.common;

import java.io.*;
import java.util.Properties;

public class PropertyManager {
    private static final String CONFIG = "";
    private Properties prop;

    private void init() {
        prop = new Properties();
        try {
            prop.load(new FileInputStream(CONFIG));
            System.out.printf("Found properties: {}", prop);
        } catch (FileNotFoundException e) {
            System.out.printf("Did not find application config file: {}", CONFIG);
        } catch (IOException e) {
            System.out.printf("Could not read prop file: ", e);
        }
    }

    public String getValue(String key) {
        if (prop == null) {
            init();
        }
        return prop.getProperty(key);
    }
}
