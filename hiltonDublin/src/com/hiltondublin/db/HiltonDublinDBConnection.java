package com.hiltondublin.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.hiltondublin.classes.Room;
import com.hiltondublin.classes.RoomType;

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
	public final static String ROOM_NUMBER = ROOM + "." + "ROOMNUMBER";
	public final static String ROOM_TYPEID = ROOM + "." + "TYPEID";
	public final static String ROOM_SMOKING = ROOM + "." + "SMOKING";
	public final static String ROOM_OCCUPIED = ROOM + "." + "OCCUPIED";
	public final static String []ROOM_COLUMNS = {ROOM_NUMBER, ROOM_TYPEID, ROOM_SMOKING, ROOM_OCCUPIED};
	
	//RoomType table constants
	public final static String ROOMTYPE = "ROOMTYPE";
	public final static String ROOMTYPE_TYPEID = ROOMTYPE + "." + "TYPEID";
	public final static String ROOMTYPE_NAME = ROOMTYPE + "." + "NAME";
	public final static String ROOMTYPE_PICTURE = ROOMTYPE + "." + "PICTURE";
	public final static String ROOMTYPE_STANDARDPRICE = ROOMTYPE + "." + "STANDARDPRICE";
	public final static String ROOMTYPE_DESCRIPTION = ROOMTYPE + "." + "DESCRIPTION";
	public final static String []ROOMTYPE_COLUMNS = {ROOMTYPE_TYPEID, ROOMTYPE_NAME, ROOMTYPE_PICTURE, ROOMTYPE_STANDARDPRICE, ROOMTYPE_DESCRIPTION};
	
	//
	
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
	 * Creates a Select SQL Statement specified by the table name(s) and the given columns with values as a condition
	 * @param tables
	 * @param selectedColumns
	 * @param conditionColumns
	 * @param conditionValues
	 * @param additionalSQL
	 * @return String
	 */
	public String createSelectStatement(String [] tables, String[] selectedColumns, String[] conditionColumns, String[] conditionValues, String additionalSQL) {
		if(tables == null){
			System.out.println("Create SQL Statement failed. No tables selected!");
		}
		
		if(selectedColumns == null){
			System.out.println("Create SQL Statement failed. Selected Columns are missing!");
			return null;
		}
		
		String sqlStatement = "SELECT ";
		boolean firstSelectedColumn = true;
		for(String selectedColumn : selectedColumns){
			if(firstSelectedColumn){
				firstSelectedColumn = false;
			}
			else{
				sqlStatement += ", ";
			}
			sqlStatement += selectedColumn;
		}
		sqlStatement += " FROM ";
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
		
		//Checking if Conditions are available
		boolean conditionsAvailable = false;
		boolean primaryConditionsAvailable = false;
		for(String conditionValue : conditionValues){
			if(!isNullOrEmpty(conditionValue)){
				conditionsAvailable = true;
				primaryConditionsAvailable = true;
				break;
			}
		}
		if(!isNullOrEmpty(additionalSQL)){
			conditionsAvailable = true;
		}
		
		if(conditionsAvailable){
			sqlStatement += " WHERE ";
			int numberOfColumns = conditionColumns.length;
			boolean firstCondition = true;
			for(int i = 0; i < numberOfColumns; i++){
				if( conditionValues[i]!=null ){
					if( !conditionValues[i].isEmpty()){
						if(firstCondition){
							firstCondition = false;
						}
						else{
							sqlStatement += "AND ";
						}
						sqlStatement += conditionColumns[i] + "='" + conditionValues[i] + "' ";
					}
				}
			}
			if( additionalSQL!=null){
				if( !additionalSQL.isEmpty()){
					if(primaryConditionsAvailable){
						sqlStatement += "AND ";
					}
					sqlStatement += additionalSQL + " ";
				}
			}
		}
		sqlStatement += ";";
		
		
		return sqlStatement;
	}
	
	
	/**
	 * Creates an Inser SQL Statement specified by the given parameters
	 * @param table
	 * @param columns
	 * @param values
	 * @return String
	 */
	public String createInsertStatement(String table, List<Cell> cells){
		
		String sqlStatement = null;
		sqlStatement += "INSERT INTO " + table + "( ";
		boolean firstColumn = true;
		for(Cell cell : cells){
			if(firstColumn){
				firstColumn = false;
			}
			else{
				sqlStatement += ", ";
			}
			sqlStatement += cell.getColumn();
		}
		
		sqlStatement += " ) VALUES (";
		boolean firstValue = true;
		for(Cell cell : cells){
			if(firstValue){
				firstValue = false;
			}
			else {
				sqlStatement += ", ";
			}
			
			sqlStatement += "'" + cell.getValue() + "'";
		}
		sqlStatement += " ) ;";
		
		
		return sqlStatement;
	}
	
	
	/**
	 * Executes the from the parameter given SQL Statement on the database and returns the result set
	 * @param sqlStatement
	 * @return ResultSet
	 */
	public ResultSet executeQueryAndReturnResultSet(String sqlStatement) {
		
		try {
			Statement statement;
			System.out.println("Execute Query: \"" + sqlStatement + "\"");
			statement = dbConnection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlStatement);
			return resultSet;
			
		} catch (SQLException e) {
			System.out.println("Execute Query failed: \"" + sqlStatement + "\"");
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	private boolean executeUpdate(String sqlStatement) {
		try {
			Statement statement;
			System.out.println("Execute Update: \"" + sqlStatement + "\"");
			statement = dbConnection.createStatement();
			int numberOfRows = statement.executeUpdate(sqlStatement);
			statement.close();
			if(numberOfRows == 0){
				return false;
			}
			else {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Execute Update failed: \"" + sqlStatement + "\"");
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	/**
	 * converts the boolean (true/false) to a tiny int (1/0)
	 * @param bool
	 * @return String
	 */
	private String convertBooleanToTinyInt(String bool) {
		if(bool!=null){
			if(!bool.isEmpty()){
				if(bool == "true"){
					bool = "1";
				} else if (bool == "false"){
					bool = "0";
				}
			}
		}
		
		return bool;
	}
	
	
	private boolean isNullOrEmpty(String obj){
		if(obj == null){
			return true;
		}
		if(obj.isEmpty()){
			return true;
		}
		return false;
	}
	
	/**
	 * Return a List of cells which contain column-value pairs. Returns only pairs where the value is not null or empty.
	 * @param columns
	 * @param values
	 * @return List<Cell>
	 */
	private List<Cell> getCellList(String[] columns, String[] values) {
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
			}
			i++;
		}
		
		return cells;
	}
	
	
	/**
	 * Returns all rooms from the database that are specified by the parameters.
	 * If parameter is null it won't be included in the conditions from the sql statement.
	 * @param roomNumber
	 * @param typeID
	 * @param smoking
	 * @param occupied
	 * @param additionalSQLCondition
	 * @return List<Room>
	 */
	public List<Room> getRooms(String roomNumber, String typeID, String smoking, String occupied, String additionalSQLCondition){
		List<Room> rooms = new ArrayList<Room>();
		
		//Converting
		smoking = convertBooleanToTinyInt(smoking);
		occupied = convertBooleanToTinyInt(occupied);
		
		//Write values and tables in arrays
		String []values = {roomNumber, typeID, smoking, occupied};
		String []tables = {ROOM};
		
		//Get sql Statement
		String sqlStatement = createSelectStatement(tables, ROOM_COLUMNS, ROOM_COLUMNS, values, additionalSQLCondition);
		
		//Execute Query
		ResultSet rs = executeQueryAndReturnResultSet(sqlStatement);
		
		//Process Result Set
		try{
			while(rs.next()){
				Room room = new Room();
				room.setRoomNumber(rs.getInt(ROOM_NUMBER));
				room.setTypeID(rs.getInt(ROOM_TYPEID));
				room.setSmoking(rs.getBoolean(ROOM_SMOKING));
				room.setOccupied(rs.getBoolean(ROOM_OCCUPIED));
				
				rooms.add(room);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Read ResultSet Failed.");
			e.printStackTrace();
			return null;
		}
		
		
		
		return rooms;
	}
	
	
	/**
	 * Inserts a room into the database specified from the given parameter.
	 * If a parameter is null or empty it won't be recorded in the SQL Statement.
	 * @param roomNumber
	 * @param typeID
	 * @param smoking
	 * @param occupied
	 * @return boolean
	 */
	public boolean insertRoom(String roomNumber, String typeID, String smoking, String occupied){
		//Create cell List
		String []values = {roomNumber, typeID, smoking, occupied};
		
		//Return cell List with contained values
		List<Cell> cells = getCellList(ROOM_COLUMNS, values);
		
		//Create insert SQL Statement
		String sqlStatement = createInsertStatement(ROOM, cells);
		
		//Execute update
		return executeUpdate(sqlStatement);
	}
	
	
	
	/**
	 * Inserts a room type into the database specified from the given parameter.
	 * If a parameter is null or empty it won't be recorded in the SQL Statement.
	 * @param typeID
	 * @param name
	 * @param picture
	 * @param standardPrice
	 * @param description
	 * @return boolean
	 */
	public boolean insertRoomType(String typeID, String name, String picture, String standardPrice, String description){
		//Create cell List
		String []values = {typeID, name, picture, standardPrice, description};
				
		//Return cell List with contained values
		List<Cell> cells = getCellList(ROOMTYPE_COLUMNS, values);
		
		//Create insert SQL Statement
		String sqlStatement = createInsertStatement(ROOMTYPE, cells);
		
		//Execute update
		return executeUpdate(sqlStatement);		
	}
	

	

	/**
	 * Returns all room types from the database that are specified by the parameters.
	 * If parameter is null it won't be included in the conditions from the sql statement.
	 * @param typeID
	 * @param name
	 * @param picture
	 * @param standardprice
	 * @param description
	 * @param additionalSQLCondition
	 * @return List<RoomType>
	 */
	public List<RoomType> getRoomTypes(String typeID, String name, String picture, String standardprice, String description, String additionalSQLCondition){
		List <RoomType> roomTypes = new ArrayList<RoomType>();
		
		//Write Values and Tables in Arrays
		String []values = {typeID, name, picture, standardprice, description};
		String []tables = {ROOMTYPE};
		
		//Get SQL Statement
		String sqlStatement = createSelectStatement(tables, ROOMTYPE_COLUMNS, ROOMTYPE_COLUMNS, values, additionalSQLCondition);
		
		//Execute Query
		ResultSet rs = executeQueryAndReturnResultSet(sqlStatement);
		
		//Process Result Set
		try{
			while(rs.next()){
				RoomType roomType = new RoomType();
				
				roomType.setRoomTypeID(rs.getInt(ROOMTYPE_TYPEID));
				roomType.setName(rs.getString(ROOMTYPE_NAME));
				roomType.setPictureRessource(rs.getString(ROOMTYPE_PICTURE));
				roomType.setStandardPrice(rs.getDouble(ROOMTYPE_STANDARDPRICE));
				roomType.setDescription(rs.getString(ROOMTYPE_DESCRIPTION));
				
				roomTypes.add(roomType);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Read ResultSet Failed.");
			e.printStackTrace();
			return null;
		}
		
		return roomTypes;
	}

}
