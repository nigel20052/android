package com.example.retrofit;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class APIConnector{

	/**
	 * @param args
	 */
	public static String auth(String username, String password) throws Exception {
		
		String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
		data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
		
        //Create connection
		URL url = new URL("http://retrofit2.ensi.com.mx/api/v1/auth");
		String datos = conexion(data,url,"POST",false);
        
        return( datos );
    }
	
	public static String getTools(String username, String password) throws Exception {
	
	username +=":";
	username +=password;
	String data =  Base64.encodeBytes(username.getBytes("UTF-8"));
	
    //Create connection
	URL url = new URL("http://retrofit2.ensi.com.mx/api/v1/tool");
	String datos = conexion(data,url,"GET",true);
	
	return(datos);
	}
	
	public static String getMechanics(String username, String password) throws Exception {
		
		username +=":";
		username +=password;
		String data =  Base64.encodeBytes(username.getBytes("UTF-8"));
		
	    //Create connection
		URL url = new URL("http://retrofit2.ensi.com.mx/api/v1/mechanic");
		String datos = conexion(data,url,"GET",true);
		
		return(datos);
		}
	
	public static String conexion(String data, URL url, String metodo, Boolean seguro) throws Exception{
		
		HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
		if(seguro){		
			httpConn.setRequestProperty  ("Authorization", "Basic " + data);
		}
	    
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
	    byte[] buffer = new byte[data.length()];
	    buffer = data.getBytes();
	    bout.write(buffer);
	    byte[] b = bout.toByteArray();
	    
	    httpConn.setRequestMethod(metodo);
	    httpConn.setDoOutput(true);
	    httpConn.setDoInput(true);
	   
	    OutputStream out = httpConn.getOutputStream();
	    out.write(b);
	    out.close();
	    
	    InputStreamReader isr =
	        new InputStreamReader(httpConn.getInputStream());
	    BufferedReader in = new BufferedReader(isr,8192);
	    
	    String responseString = "";
	    String outputString = "";
	   
	    while ((responseString = in.readLine()) != null) {        	
	        outputString = outputString + responseString;
	    }
	    
	    return( outputString );
	}

}

