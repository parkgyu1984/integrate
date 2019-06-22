package com.integrate.java.env;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger 
{
	/**
	 * 실행 로그
	 * @param msg
	 */
	public static void info( String msg )
	{
		String printMsg = getLogDateStr() + "[Run] " + msg;
		System.out.println( printMsg );
	}
	
	/**
	 * 에러 로그
	 * @param msg
	 */
	public static void error( String msg )
	{
		String printMsg = getLogDateStr() + "[Error] " + msg;
		System.out.println( printMsg );
		//TelegramHandler.sendMessage( printMsg );
	}
	
	/**
	 * 현재 일시
	 * @return
	 */
	private static String getLogDateStr()
	{
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat( "[yyyy-MM-dd HH:mm:ss]" );
		return df.format( now );
	}
}
