package com.integrate.java.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class CodecUtil 
{
	/**
	 * HmanSHA512 암호화
	 * @param msg
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptHmacSHA512( String msg, String key, String encoding )
	{
		byte[] macData = null;
		
		try 
		{
			final String hmacSHA512 = "HmacSHA512";	//암호화 알고리즘
			
			byte[] byteKey = key.getBytes( encoding );
			
	        Mac mac = Mac.getInstance( hmacSHA512 );
	        SecretKeySpec keySpec = new SecretKeySpec( byteKey, hmacSHA512 );
	        mac.init( keySpec );
	        
	        macData = mac.doFinal( msg.getBytes( encoding ) );
		} 
		catch ( Exception e) 
		{
			e.printStackTrace();
			System.out.println( e.getMessage() );
		}
        
		return macData;
	}
	
	/**
	 * byte[] to Base64
	 * @param bytes
	 * @return
	 */
	public static String toBase64String( byte[] bytes )
	{
	    byte[] byteArray = Base64.encodeBase64( bytes );
	    return new String( byteArray );
	}
     
	/**
	 * byye to hex
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex( byte[] bytes, boolean isLowerCode ) 
	{
	    char[] hexArray = null;
	    
	    // 대소문자 구분
	    if( isLowerCode )	hexArray = "0123456789abcdef".toCharArray();
	    else			hexArray = "0123456789ABCDEF".toCharArray();
	    
	    char[] hexChars = new char[ bytes.length * 2 ];
	    
	    for ( int j=0; j<bytes.length; j++ ) 
	    {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    
	    return new String( hexChars );
	}
}
