/**
 * Copyright ...
 */
package com.portal.business.tripplan.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import com.portal.business.tripplan.BusinessExceptions.BusinessExceptions;


/**
 * class contains common utility methods
 * @author kumar
 *
 */
public class CommonUtility extends BusinessExceptions{
    
	private static final long serialVersionUID = 1L;

	public static Map<String, String> loadLocations(String file) throws IOException {
	
		return  Files.lines(Paths.get(file))
			        .map(line -> line.split(BusinessContacts.COMMA_SEPERATOR))
			        .collect(Collectors.toMap(line -> line[0], line -> line[1]));		
		
	}
	
	public static Map<String, String> reverse(Map<String, String> map) {
		return map.entrySet()
	       .stream()
	       .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
	}
	
	/**
	 * 
	 * @param sourceDestMap
	 * @param reversedMap
	 * @param origin
	 * @param destination
	 * @return
	 */
	public static boolean isMatched(Map<String, String> sourceDestMap,  Map<String, String> reversedMap,String origin,String destination) {
		
		return sourceDestMap.entrySet().stream().anyMatch(entry -> {				  
			  String aKey= entry.getKey(); 
			  String aValue = entry.getValue();		
			  String bKey = reversedMap.get(aValue);	
			  String bValue = reversedMap.get(bKey);
			
			  if  (bValue == null) {
				  return false; }

			  if (aKey.equalsIgnoreCase(origin.trim()) && aValue.equalsIgnoreCase(destination.trim()) 
					  ||  aKey.equalsIgnoreCase(destination.trim()) && aValue.equalsIgnoreCase(origin.trim()) 
					  || (aKey.equalsIgnoreCase(bKey) && bValue.equalsIgnoreCase(destination))) { 
				  return true;
			  } return false; });
		
	}
}
