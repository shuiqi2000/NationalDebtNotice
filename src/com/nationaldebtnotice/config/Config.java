package com.nationaldebtnotice.config;

import java.util.ResourceBundle;

public class Config {
	public static String webUrl = null;
	public static String recipient = null;
    static{		
			ResourceBundle resource = ResourceBundle.getBundle("config/config");		
			webUrl=resource.getString("WebURL").trim();
			recipient=resource.getString("Objective.Email").trim();		
    }
}
