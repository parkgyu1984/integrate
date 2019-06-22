package com.integrate.java.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.integrate.java.env.Logger;


/**
 * HttpClient 유틸리티
 * @author Administrator
 *
 */
public class HttpClientUtil
{
	/**
     * POST 요청, default Header
     * @param url       요청할 url
     * @param params    파라메터
     * @param encoding  파라메터 Encoding
     * @return 서버 응답결과 문자열
     */
	public static String post( String url, Map<String,Object> params, String encoding ) 
	{
		HttpPost post = new HttpPost( url );
		//HttpEntity entity = new 
		return post( post, params, encoding );
	}
	
	/**
	 * Post 요청 
	 * @param post
	 * @param params
	 * @param encoding
	 * @return
	 */
	public static String post( HttpPost post, HttpEntity entity, String encoding ) 
	{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		
		try {
			System.out.println( "request post.. " + post.getURI().toString() );
				
			post.setEntity( entity );
			
			CloseableHttpResponse response = client.execute( post );
			String result = EntityUtils.toString( response.getEntity(), encoding );
			
			return result;
		} catch ( Exception e ) {
			e.printStackTrace();
			Logger.error( e.getMessage() );
		} finally {
			client.getConnectionManager().shutdown();
		}

		return "error";
	}
	
	/**
	 * Post 요청 
	 * @param post
	 * @param params
	 * @param encoding
	 * @return
	 */
	public static String post( HttpPost post, Map<String,Object> params, String encoding ) 
	{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		
		try {
			System.out.println( "request post.. " + post.getURI().toString() );
			
			StringEntity entity = new StringEntity( JacksonUtil.mapToJson( params ) );
			entity.setContentEncoding( encoding );
			entity.setContentType( "application/json" );
			post.setEntity( entity );
			
			CloseableHttpResponse response = client.execute( post );
			String result = EntityUtils.toString( response.getEntity(), encoding );
			
			return result;
		} catch ( Exception e ) {
			e.printStackTrace();
			Logger.error( e.getMessage() );
		} finally {
			client.getConnectionManager().shutdown();
		}

		return "error";
	}
	
	
	
	/**
	 * GET 요청 POST 와 동일
	 * @param url
	 * @param params
	 * @param encoding
	 * @return
	 */
	public static String get( String url, Map params, String encoding ) {
		HttpClient client = new DefaultHttpClient();

		try {
			List<NameValuePair> paramList = convertParam( params );
			HttpGet get = new HttpGet( url + "?" + URLEncodedUtils.format( paramList, encoding ) );
			
			System.out.println( "request get.. " + get.getURI().toString() );
			
			ResponseHandler<String> rh = new BasicResponseHandler();
			String result = client.execute( get, rh );
			
			return result;
		} catch ( Exception e ) {
			e.printStackTrace();
			Logger.error( e.getMessage() );
		} finally {
			client.getConnectionManager().shutdown();
		}

		return "error";
	}
	
	/**
	 * get
	 * @param url
	 * @return
	 */
	public static String get( String url )
	{
		HttpClient client = new DefaultHttpClient();

		try {
			HttpGet get = new HttpGet( url );
			
			System.out.println( "request get.. " + get.getURI().toString() );
			
			ResponseHandler<String> rh = new BasicResponseHandler();
			String result = client.execute( get, rh );
			
			//Logger.run( result );
			
			return result;
		} catch ( Exception e ) {
			e.printStackTrace();
			Logger.error( e.getMessage() );
		} finally {
			client.getConnectionManager().shutdown();
		}

		return "error";
	}
	
	/**
	 * get
	 * @param get
	 * @return
	 */
	public static String get( HttpGet get )
	{
		HttpClient client = new DefaultHttpClient();

		try {
			System.out.println( "request get.. " + get.getURI().toString() );
			ResponseHandler<String> rh = new BasicResponseHandler();
			
			String result = client.execute( get, rh );
			//Logger.run( result );
			
			return result;
		} catch ( Exception e ) {
			e.printStackTrace();
			Logger.error( e.getMessage() );
		} finally {
			client.getConnectionManager().shutdown();
		}

		return "error";
	}
	
	/**
	 * delete
	 * @param delete
	 * @return
	 */
	public static String delete( HttpDelete delete )
	{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		String result = null;
		
		try {
			System.out.println( "request delete.. " + delete.getURI().toString() );
			
			CloseableHttpResponse response = client.execute( delete );
			result = EntityUtils.toString( response.getEntity(), "UTF-8" );
			
			System.out.println( result );
		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			client.getConnectionManager().shutdown();
		}
		
		return result;
	}

	/**
	 * params Map -> List
	 * @param params
	 * @return
	 */
	public static List<NameValuePair> convertParam( Map params ) 
	{
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		Iterator<String> keys = params.keySet().iterator();
		
		while ( keys.hasNext() ) {
			String key = keys.next();
			paramList.add( new BasicNameValuePair( key, params.get( key ).toString() ) );
		}

		return paramList;
	}
	
	/**
	 * reponse context를 String으로 리턴
	 * @param content
	 * @return
	 */
	private static String parseResponseContextToString( InputStream content )
	{
		StringBuffer strBuf = null;
		
		try {
			BufferedReader rd = new BufferedReader( new InputStreamReader( content ) );
			
			strBuf = new StringBuffer();
			String line = "";
			
			while ( ( line = rd.readLine() ) != null ) 
			{
				strBuf.append( line );
			}
		} catch ( Exception e ) {
			System.out.println( " + HttpClientUtil.parseResponseContextToString : " + e.getMessage() );
		}
		
		return strBuf.toString();
	}
}	
	
