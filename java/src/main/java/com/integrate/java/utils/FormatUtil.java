package com.integrate.java.utils;

import java.text.DecimalFormat;

public class FormatUtil 
{
	private final static DecimalFormat LONG_COMMA = new DecimalFormat("#,##0");
	private final static DecimalFormat ROUND_TWO_COMMA = new DecimalFormat("#,##0.00");
	private final static DecimalFormat ROUND_FOUR_COMMA = new DecimalFormat("#,##0.0000");
	private final static DecimalFormat ROUND_EIGHT_COMMA = new DecimalFormat("#,##0.00000000");
	private final static DecimalFormat RATE = new DecimalFormat("#.##");
	
	/**
	 * 가격 포맷
	 * @param market
	 * @param price
	 * @return
	 */
	public static String priceFormat( String market, double price )
	{
		String result = null;
		if( "KRW".equals( market ) )	result = LONG_COMMA.format( price );
		else 							result = ROUND_EIGHT_COMMA.format( price );
		
		return result;
	}
	
	/**
	 * 가격 포맷 소수점 둘째자리
	 * @param market
	 * @param price
	 * @return
	 */
	public static String krwRoundFormat( double price )
	{
		return ROUND_TWO_COMMA.format( price );
	}
	
	/**
	 * 수량 포맷
	 * @param amount
	 * @return
	 */
	public static String amountFormat( double amount )
	{
		return ROUND_FOUR_COMMA.format( amount );
	}
	
	/**
	 * 수익률 포맷
	 * @param rate
	 * @return
	 */
	public static String rateFormat( double rate )
	{
		return RATE.format( rate );
	}
	
	/**
	 * 정수 쉼표 포맷 
	 * @return
	 */
	public static DecimalFormat getLongComma()
	{
		return LONG_COMMA;
	}
	
	/**
	 * 소수점 2자리 쉼표 포맷
	 * @return
	 */
	public static DecimalFormat getRoundTwoComma()
	{
		return ROUND_TWO_COMMA;
	}
	
	/**
	 * 소수점 2자리 쉼표 포맷
	 * @return
	 */
	public static DecimalFormat getRoundFourComma()
	{
		return ROUND_FOUR_COMMA;
	}
	
	/**
	 * 소수점 8자리 쉼표 포맷
	 * @return
	 */
	public static DecimalFormat getRoundEightComma()
	{
		return ROUND_EIGHT_COMMA;
	}
	
	/**
	 * 수익률 포맷
	 * @param rate
	 * @return
	 */
	public static DecimalFormat getRate()
	{
		return RATE;
	}
	
	/**
	 * 가격 포맷
	 * @param market
	 * @param price
	 * @return
	 */
	public static DecimalFormat getPriceFormat( String market, String coin )
	{
		DecimalFormat result = null;
		
		//원화포맷
		if( "KRW".equals( market ) ) 
		{
			//원화 소수점 2자리 포맷인 코인
			if( "TRX".equals( coin.toUpperCase() ) || "SC".equals( coin.toUpperCase() ) ) 
			{
				result = ROUND_TWO_COMMA;
			} 
			else 
			{
				result = LONG_COMMA;
			}
		} 
		else 
		{
			result = ROUND_EIGHT_COMMA;
		}
		
		return result;
	}
}
