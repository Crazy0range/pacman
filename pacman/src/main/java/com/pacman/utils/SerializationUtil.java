package com.pacman.utils;

import java.io.Serializable;

import org.apache.commons.lang.SerializationUtils;

public class SerializationUtil {
	
 
	public static byte[] fromJavaToByteArray(Serializable object){
		return SerializationUtils.serialize(object);
	}
	
	public static Object fromByteArrayToJava(byte[] bytes){
		
		return SerializationUtils.deserialize(bytes);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
