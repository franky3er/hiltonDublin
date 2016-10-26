package com.hiltondublin.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.hiltondublin.classes.Room;

public class HiltonDublinDBConnection {
	//Database Properties constants
	public final static String DB_PROPERTIES_FILENAME = "db.properties";
	public final static String HILTONDUBLIN_DB_DRIVER = "hiltondublin.db.driver";
	public final static String HILTONDUBLIN_DB_URL = "hiltondublin.db.url";
	public final static String HILTONDUBLIN_DB_USERNAME = "hiltondublin.db.username";
	public final static String HILTONDUBLIN_DB_PASSWORD = "hiltondublin.db.password";
	public final static String HILTONDUBLIN_DB_NAME = "hiltondublin.db.name";
	
	//Room table constants
	public final static String ROOM = "ROOM";
	public final static String ROOM_NUMBER = "ROOMNUMBER";
	public final static String ROOM_TYPEID = "ROOMTYPEID";
	public final static String ROOM_SMOKING = "SMOKING";
	public final static String ROOM_OCCUPIED = "OCCUPIED";
	public final static String []ROOM_COLUMNS = {ROOM_NUMBER, ROOM_TYPEID, ROOM_SMOKING, ROOM_OCCUPIED}; 
	
	//Attributes
	private static HiltonDublinDBConnection instance;
	private Connection dbConnection = null;
	private Properties dbProperties = null;
	
	/**
	 * Returns the instance of the HiltonDublinDBConnection (Singleton)
	 * @return HiltonDublinDBConnection
	 */
	public static HiltonDublinDBConnection getInstance(){
		if(HiltonDublinDBConnection.instance == null){
			instance = new HiltonDublinDBConnection();
		}
		return HiltonDublinDBConnection.instance;
	}
	
	/**
	 * Load Properties and Connect to the DB
	 */
	private HiltonDublinDBConnection(){
		//Load Properties
		if(loadDBProperties()){
			System.out.println("Successfully load Properties from file \"" + DB_PROPERTIES_FILENAME + "\"");
		} else {
			System.out.println("Failed to load Properties from file \"" + DB_PROPERTIES_FILENAME + "\"");
		}
		
		//Connect to Database
		if(connect()){
			System.out.println("Successfully connect to Database \"" + dbProperties.getProperty(HILTONDUBLIN_DB_NAME) + "\"");
		} else {
			System.out.println("Failed to connect to Database \"" + dbProperties.getProperty(HILTONDUBLIN_DB_NAME) + "\"");
		}
	}
	
	
	/**
	 * Load the all for the database connection necessary properties from the file DB_PROPERTIES_FILENAME and saves it in the attribute dbProperties
	 * @return boolean
	 * true if propertie load was successful
	 * false if propertie load failed
	 */
	public boolean loadDBProperties() {
		System.out.println("Load \"" + DB_PROPERTIES_FILENAME + "\"");
		dbProperties = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES_FILENAME);
		try {
			this.dbProperties.load(input);
		} catch (IOException e) {
			System.out.println("Failed to load \"" + DB_PROPERTIES_FILENAME + "\"");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Connects to the database with the properties from the propertie file
	 * @return boolean
	 * true if connection was successful
	 * false if connection failed
	 */
	public boolean connect() {
		//Load properties if necessary
		if(this.dbProperties == null){
			if(!loadDBProperties()){
				return false;
			}
		}
		
		//Load Driver
		System.out.println("Load jdbc Driver \"" + dbProperties.getProperty(HILTONDUBLIN_DB_DRIVER) + "\"");
		try {
			Class.forName(dbProperties.getProperty(HILTONDUBLIN_DB_DRIVER));
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to Load jdbc Driver \"" + dbProperties.getProperty(HILTONDUBLIN_DB_DRIVER) + "\"");
			e.printStackTrace();
			return false;
		}
		
		//Connect to DB
		String url = this.dbProperties.getProperty(HILTONDUBLIN_DB_URL);
		String username = this.dbProperties.getProperty(HILTONDUBLIN_DB_USERNAME);
		String password = this.dbProperties.getProperty(HILTONDUBLIN_DB_PASSWORD);
		System.out.println("Connect to Database \"" + url + "\"");
		try {
			this.dbConnection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Failed to connect to Database \"" + url + "\"");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Disconnect from the database
	 * @return boolean
	 * true if disconnection was successful
	 * false if disconnection failed
	 */
	public boolean disconnect(){
		try {
			this.dbConnection.close();
		} catch (SQLException e) {
			System.out.println("Failed to disconnect Database \"" + this.dbProperties.getProperty(HILTONDUBLIN_DB_NAME) + "\"");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Returns all rooms from the database that are specified by the parameters
	 * @param roomNumber
	 * @param typeID
	 * @param smoking
	 * @param occupied
	 * @param additionalSQL
	 * @return List<Room>
	 */
	public List<Room> getRooms(int roomNumber, int typeID, boolean smoking, boolean occupied, String additionalSQL){
		List<Room> rooms = new ArrayList<Room>();
		
		String []values = {Integer.toString(roomNumber), Integer.toString(typeID), Boolean.toString(smoking), Boolean.toString(occupied)};
		String []tables = {ROOM};
		
		String sqlStatement = createSelectStatement(tables, ROOM_COLUMNS, values, additionalSQL);
		
		//TODO Execute Query
		
		return rooms;
	}

	
	/**
	 * Creates a Select SQL Statement specified by the table name(s) and the given columns with values as a condition
	 * @param tables
	 * @param columns
	 * @param values
	 * @param additionalSQL
	 * @return
	 */
	public String createSelectStatement(String [] tables, String[] columns, String[] values, String additionalSQL) {
		String sqlStatement = "SELEC * FROM ";
		boolean firstTable = true;
		for(String table : tables){
			if(firstTable){
				firstTable = false;
			}
			else{
				sqlStatement += ", ";
			}
			sqlStatement += table;
		}
		sqlStatement += " WHERE ";
		int numberOfColumns = columns.length;
		boolean firstCondition = true;
		for(int i = 0; i < numberOfColumns; i++){
			if( !(values[i]!=null || values[i].trim().equals(""))){
				if(firstCondition){
					firstCondition = false;
				}
				else{
					sqlStatement += "AND ";
				}
				sqlStatement += columns[i] + "='" + values[i] + "' ";
			}
		}
		if( !(additionalSQL==null || additionalSQL.trim().equals(""))){
			sqlStatement += "AND " + additionalSQL + " ";
		}
		sqlStatement += ";";
		
		return sqlStatement;
	}

	
}
