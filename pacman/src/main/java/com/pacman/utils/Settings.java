package com.pacman.utils;

public class Settings {
	
	static String urlVal = "tcp://localhost";
	
	//"tcp://localhost:5555"
	/*public static String getRegistryServerURL(){
		//String url = (String) Settings.getInstance().get("RegistryURL");
		return url+":"+Variables.RegistryPort;
	}*/
	
	//tcp://localhost:5557
	public static String getPublisherURL(){
		String url = urlVal;
		return url+":"+Variables.PublisherPort;
	}
	
	//"tcp://localhost:5558"
	public static String getSubscriberURL(){
		String url =urlVal ;
		return url+":"+Variables.SubscriberPort;
	}
	

}
