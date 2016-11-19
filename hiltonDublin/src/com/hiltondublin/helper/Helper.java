package com.hiltondublin.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hiltondublin.db.Cell;

public class Helper {
	public final static String regexEmailValidation = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	public final static String regexInteger = "\\d*$";
	public final static String regexDouble = "-?\\d+(\\.\\d+)?";
	public final static Random random = new Random();
	
	/**
	 * Validates Email address and returns true if email address matches to a email address
	 * @param email
	 * @return boolean
	 */
	public static boolean validateEmail(String email){
		return email.matches(regexEmailValidation);
	}
	
	//Comment
	
	/**
	 * converts the boolean (true/false) to a tiny int (1/0)
	 * @param bool
	 * @return String
	 */
	public static String convertBooleanToTinyInt(String bool) {
		if(bool!=null){
			if(!bool.isEmpty()){
				if(bool.equals("true")){
					bool = "1";
				} else if (bool.equals("false")){
					bool = "0";
				}
			}
		}
		
		return bool;
	}
	
	public static String removeLastChar(String str) {
		if(str == null){
			return null;
		}
        return str.substring(0,str.length()-1);
    }
	
	public static boolean isNullOrEmpty(String obj){
		if(obj == null){
			return true;
		}
		if(obj.isEmpty()){
			return true;
		}
		return false;
	}
	
	/**
	 * Returns null if a String is empty and the string it self if not
	 * @param string
	 * @return String
	 */
	public static String setNullIfEmptyString(String string){
		if(string != null){
			string = string.trim();
			if(string.equals("")){
				string = null;
			}
		}
		return string;
	}
	
	public static String trim(String string){
		if(string != null){
			string.trim();
		}
		return string;
	}
	
	public static boolean isInteger(String number){
		if(number == null){
			return false;
		}
		return number.matches(regexInteger);
	}
	
	public static boolean isDouble(String number){
		if(number == null){
			return false;
		}
		return number.matches(regexDouble);
	}
	
	public static boolean isBoolean(String bool){
		return (bool.matches("1") || bool.matches("0") || bool.matches("true") || bool.matches("false"));
	}
	
	/**
	 * Return a List of cells which contain column-value pairs. Returns only pairs where the value is not null or empty.
	 * @param columns
	 * @param values
	 * @return List<Cell>
	 */
	public static List<Cell> getCellList(String[] columns, String[] values) {
		if(values.length != columns.length){
			return null;
		}
		
		List<Cell> cells = new ArrayList<Cell>();
		int i = 0;
		for(String value : values){
			if(!isNullOrEmpty(value)){
				Cell cell = new Cell();
				cell.setColumn(columns[i]);
				cell.setValue(value);
				cells.add(cell);
			}
			i++;
		}
		
		return cells;
	}
	
	/**
	 * Return a List of cells which contain column-value pairs. 
	 * @param columns
	 * @param values
	 * @return List<Cell>
	 */
	public static List<Cell> getCellListWithNullValues(String[] columns, String[] values) {
		if(values.length != columns.length){
			return null;
		}
		
		List<Cell> cells = new ArrayList<Cell>();
		int i = 0;
		for(String value : values){
			Cell cell = new Cell();
			cell.setColumn(columns[i]);
			cell.setValue(value);
			cells.add(cell);
			i++;
		}
		
		return cells;
	}
	
	public static int randInt(int min, int max) {
	    
		int randomNum = random.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}
