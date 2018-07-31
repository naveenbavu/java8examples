package com.java8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JsonSimpleReadExample {
	
	public static boolean  cleanJSON(Object arg) throws JSONException{
	    boolean valueExist = false;
	    if(arg instanceof String){
	        String str= (String)arg;
	        if(!str.equals("DELETE")) valueExist = true;
	    }else if(arg instanceof Integer){
	        Integer str= (Integer)arg;
	        if(!str.equals("DELETE")) valueExist = true;
	    }
	    else if(arg instanceof Float){
	    	Float str= (Float)arg;
	        if(!str.equals("DELETE")) valueExist = true;
	    }
	    else if(arg instanceof Double){
	    	Double str= (Double)arg;
	        if(!str.equals("DELETE")) valueExist = true;
	    }
	    else if(arg instanceof Long){
	    	Long str= (Long)arg;
	        if(!str.equals("DELETE")) valueExist = true;
	    }
	    else if(arg instanceof JSONObject){
	        JSONObject obj = (JSONObject)arg;
	        Iterator<String> iter = obj.keys();
	        ArrayList<String> fields = new ArrayList<>();
	        while(iter.hasNext())   
	        	fields.add(iter.next());
	        for(String field:fields){
	            Object value = obj.get(field);
	            if(cleanJSON(value))    
	            	valueExist = true;
	            else                    
	            	obj.remove(field);
	        }
	    }else if(arg instanceof JSONArray){
	        JSONArray arr = (JSONArray)arg;
	        for(int i=0;i<arr.length();i++){
	            if(cleanJSON(arr.get(i)))  
valueExist = true;
	            else{
	                arr.remove(i);
	                i--;
	            }
	        }
	    }
	    return valueExist;
	}
	
	

    public static void main(String[] args) throws ParseException, JSONException {


        try {
        	BufferedReader reader = new BufferedReader(new FileReader("C:\\EclipseWS\\Java8\\src\\test.json"));
        	StringBuilder contentBuilder = new StringBuilder();
        	String line = null;
        	while ((line = reader.readLine()) != null) {
        		
        		contentBuilder.append(line);
        	}
        	String str = contentBuilder.toString();
        	System.out.println("str "+str);
        	
        	JSONObject obj =new JSONObject(str);
        
            cleanJSON(obj);
            
           System.out.println(obj);

       /*     String name = (String) jsonObject.get("firstname");
            System.out.println(name);

            String age = (String) jsonObject.get("lastname");
            System.out.println(age);*/

            // loop array
          //  org.json.simple.JSONObject msg = (org.json.simple.JSONObject) jsonObject.get("address");
           // Iterator<String> iterator = msg.iterator();
          //  while (iterator.hasNext()) {
           //     System.out.println(iterator.next());
           // }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}