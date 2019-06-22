package com.integrate.java.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JacksonUtil 
{
	public static final ObjectMapper mapper = new ObjectMapper();
	public static final TypeReference<HashMap<String, Object>> typeRefObject = new TypeReference<HashMap<String, Object>>() {};
	public static final TypeReference<ArrayList<Object>> typeRefArray = new TypeReference<ArrayList<Object>>() {};
	
	/**
	 * json to map
	 * @param src
	 * @return
	 */
	public static Map<String,Object> jsonToMap( String src )
	{
		Map<String,Object> resMap = null;
		
		try 
		{
			resMap = mapper.readValue( src, typeRefObject );
		}
		catch ( Exception e ) 
		{
			e.printStackTrace();
			System.out.println( e.getMessage() );
		}
		
		return resMap;
	}
	
	/**
	 * map to json
	 * @param map
	 * @return
	 */
	public static String mapToJson( Map<String,Object> map )
	{
		String jsonStr = null;
		
		try 
		{
			jsonStr = mapper.writeValueAsString( map ); 
		}
		catch ( Exception e ) 
		{
			e.printStackTrace();
			System.out.println( e.getMessage() );
		}
		
		return jsonStr;
	}
	
	/**
	 * json to list
	 * @param src
	 * @return
	 */
	public static List<Map<String,Object>> jsonToList( String src )
	{
		List<Map<String,Object>> resList = null;
		
		try 
		{
			resList = mapper.readValue( src, typeRefArray );
		}
		catch ( Exception e ) 
		{
			e.printStackTrace();
			System.out.println( e.getMessage() );
		}
		
		return resList;
	}
	
}
