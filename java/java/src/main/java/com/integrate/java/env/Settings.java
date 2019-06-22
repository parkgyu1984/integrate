package com.integrate.java.env;

public class Settings 
{
	//버전
	private static final String Version = "v0.0.1";	
	private static final String UpdateDate = "2019.06.22";	
	
//	private static final boolean isReal = true;
	private static final boolean isReal = false;
	
	public static boolean isRealMode()
	{
		return isReal;
	}
}
