package com.pacman.utils;

public class Settings {
	
	static String urlVal = "tcp://localhost";
	
	static String leaderUrl;
	
	//"tcp://localhost:5555"
	public static String getRegistryServerURL(){
		//String url = (String) Settings.getInstance().get("RegistryURL");
		return (urlVal+":"+Variables.RegistryPort);
	}
	
	public static void setURL(String url){
		urlVal = url;
	}
	
	public static String getURL(String url){
		return urlVal;
	}
	
	//tcp://localhost:5557
	public static String getPublisherURL(){
		String url = urlVal;
		return url+":"+Variables.PublisherPort;
	}
	
	public static String getLeaderUrl() {
		return leaderUrl;
	}


	public static void setLeaderUrl(String leaderUrl) {
		Settings.leaderUrl = leaderUrl;
	}


	//"tcp://localhost:5558"
	public static String getSubscriberURL(){
		String url =urlVal ;
		return url+":"+Variables.SubscriberPort;
	}
	

}
