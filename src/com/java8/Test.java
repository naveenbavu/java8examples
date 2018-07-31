package com.java8;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

class Test {
	
	private static JSONObject myObj;


	public static boolean  cleanJSON(Object arg) throws JSONException{
	    boolean valueExist = false;
	    if(arg instanceof String){
	        String str= (String)arg;
	        if(!str.equals("")) valueExist = true;
	    }else if(arg instanceof JSONObject){
	        JSONObject obj = (JSONObject)arg;
	        Iterator<String> iter = obj.keys();
	        ArrayList<String> fields = new ArrayList<>();
	        while(iter.hasNext())   fields.add(iter.next());
	        for(String field:fields){
	            Object value = obj.get(field);
	            if(cleanJSON(value))    valueExist = true;
	            else                    obj.remove(field);
	        }
	    }else if(arg instanceof JSONArray){
	        JSONArray arr = (JSONArray)arg;
	        for(int i=0;i<arr.length();i++){
	            if(cleanJSON(arr.get(i)))   valueExist = true;
	            else{
	                arr.remove(i);
	                i--;
	            }
	        }
	    }
	    return valueExist;
	}
	
	
	private String getFile(String fileName) {

		StringBuilder result = new StringBuilder("");

		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}

			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return result.toString();

	  }
	
	
	
	public static void main(String args[]) throws Exception {
		
	    JSONParser parser = new JSONParser();
	    Test test  = new Test();
	    
	    try {
	    	String str = test.getFile("C:/EclipseWS/Java8/src/test.json");
	    	
	    	 System.out.println("str :"+str);
	    	 
	    	 
	    	 Object obj = parser.parse(new FileReader("C:/EclipseWS/Java8/src/test.json"));
	    	 JSONObject jsonObject = (JSONObject) obj;
	    	 System.out.println("jsonObject :"+jsonObject);
	    	 cleanJSON(jsonObject);
	    
	    	 

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
