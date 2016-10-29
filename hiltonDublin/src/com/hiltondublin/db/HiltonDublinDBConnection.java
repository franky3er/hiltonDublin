package com.hiltondublin.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.hiltondublin.classes.ConsumerProduct;
import com.hiltondublin.classes.Rating;
import com.hiltondublin.classes.Reservation;
import com.hiltondublin.classes.Room;
import com.hiltondublin.classes.RoomType;
import com.hiltondublin.classes.SpecialPrice;
import com.hiltondublin.classes.WeekdayPrice;

public class HiltonDublinDBConnection {
	//MySQL Date Format
	public final static SimpleDateFormat mySQLDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//Database Properties constants
	public final static String DB_PROPERTIES_FILENAME = "hiltonDublinDB.properties";
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
	
	//Rating table constants
	public final static String RATING = "RATING";
	public final static String RATING_RATINGID = RATING + "." + "RATINGID";
	public final static String RATING_ROOMTYPEID = RATING + "." + "TYPEID";
	public final static String RATING_GUESTID = RATING + "." + "GUESTID";
	public final static String RATING_RATING = RATING + "." + "RATING";
	public final static String RATING_COMMENT = RATING + "." + "COMMENT";
	public final static String []RATING_COLUMNS = {RATING_RATINGID, RATING_ROOMTYPEID, RATING_GUESTID, RATING_RATING, RATING_COMMENT};
	
	//ConsumerProduct table constants
	public final static String CONSUMERPRODUCT = "CONSUMERPRODUCT";
	public final static String CONSUMERPRODUCT_PRODUCTID = CONSUMERPRODUCT + "." + "PRODUCTID";
	public final static String CONSUMERPRODUCT_NAME = CONSUMERPRODUCT + "." + "NAME";
	public final static String CONSUMERPRODUCT_PRICE = CONSUMERPRODUCT + "." + "PRICE";
	public final static String []CONSUMERPRODUCT_COLUMNS = {CONSUMERPRODUCT_PRODUCTID, CONSUMERPRODUCT_NAME, CONSUMERPRODUCT_PRICE};
	
	//Reservation table constants
	public final static String RESERVATION = "RESERVATION";
	public final static String RESERVATION_RESERVATIONID = RESERVATION + "." + "RESERVATIONID";
	public final static String RESERVATION_GUESTID = RESERVATION + "." + "GUESTID";
	public final static String RESERVATION_ARRIVALDATE = RESERVATION + "." + "ARRIVALDATE";
	public final static String RESERVATION_DEPARTUREDATE = RESERVATION + "." + "DEPARTUREDATE";
	public final static String RESERVATION_PAID = RESERVATION + "." + "PAID";
	public final static String []RESERVATION_COLUMNS = {RESERVATION_RESERVATIONID, RESERVATION_GUESTID, RESERVATION_ARRIVALDATE, RESERVATION_DEPARTUREDATE, RESERVATION_PAID};
	
	//SpecialPrice table constants
	public final static String SPECIALPRICE = "SPECIALPRICE";
	public final static String SPECIALPRICE_ROOMTYPEID = SPECIALPRICE + "." + "TYPEID";
	public final static String SPECIALPRICE_DATE = SPECIALPRICE + "." + "DATE";
	public final static String SPECIALPRICE_PRICE = SPECIALPRICE + "." + "PRICE";
	public final static String SPECIALPRICE_COMMENT = SPECIALPRICE + "." + "COMMENT";
	public final static String []SPECIALPRICE_COLUMNS = {SPECIALPRICE_ROOMTYPEID, SPECIALPRICE_DATE, SPECIALPRICE_PRICE, SPECIALPRICE_COMMENT};
	
	//WeekdayPrice table constants
	public final static String WEEKDAYPRICE = "WEEKDAYPRICE";
	public final static String WEEKDAYPRICE_ROOMTYPEID = WEEKDAYPRICE + "." + "TYPEID";
	public final static String WEEKDAYPRICE_PRICE = WEEKDAYPRICE + "." + "PRICE";
	public final static String WEEKDAYPRICE_WEEKDAY = WEEKDAYPRICE + "." + "WEEKDAY";
	public final static String []WEEKDAYPRICE_COLUMNS = {WEEKDAYPRICE_ROOMTYPEID, WEEKDAYPRICE_PRICE, WEEKDAYPRICE_WEEKDAY};
	
	//Resereved_Room Table
	public final static String RESERVED_ROOM = "RESERVED_ROOM";
	public final static String RESERVED_ROOM_ROOMNUMBER = RESERVED_ROOM + "." + "ROOMNUMBER";
	public final static String RESERVED_ROOM_RESERVATIONID = RESERVED_ROOM + "." + "RESERVATIONID";
	public final static String []RESERVED_ROOM_COLUMNS = {RESERVED_ROOM_ROOMNUMBER, RESERVED_ROOM_RESERVATIONID};
	
	//Reserved_Products Table
	public final static String RESERVED_PRODUCT = "RESERVED_PRODUCT";
	public final static String RESERVED_PRODUCT_PRODUCTID = RESERVED_PRODUCT + "." + "PRODUCTID";
	public final static String RESERVED_PRODUCT_RESERVATIONID = RESERVED_PRODUCT + "." + "RESERVATIONID";
	public final static String []RESERVED_PRODUCT_COLUMNS = {RESERVED_PRODUCT_PRODUCTID, RESERVED_PRODUCT_RESERVATIONID};
	
	
	
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
		
		String sqlStatement = "INSERT INTO " + table + "( ";
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
				cells.add(cell);
			}
			i++;
		}
		
		return cells;
	}
	
	
	//------------------------------------------------------------------------------------------
	//-----------------------------------------ROOM---------------------------------------------
	//------------------------------------------------------------------------------------------
	
	/**
	 * Returns the Select Statement to get all rooms specified by the given parameters.
	 * If a parameter is null or empty it won't be recorded in the sql Statement.
	 * @param roomNumber
	 * @param typeID
	 * @param smoking
	 * @param occupied
	 * @param additionalSQLCondition
	 * @return String
	 */
	public String getRoomsAsSQLStatement(String selectedColumns[], String roomNumber, String typeID, String smoking, String occupied, String additionalSQLCondition){
		//Converting
		smoking = convertBooleanToTinyInt(smoking);
		occupied = convertBooleanToTinyInt(occupied);
			
		//Write values and tables in arrays
		String []values = {roomNumber, typeID, smoking, occupied};
		String []tables = {ROOM};
		
		if(selectedColumns == null){
			selectedColumns = ROOM_COLUMNS;
		}
				
		//Get sql Statement
		return createSelectStatement(tables, selectedColumns, ROOM_COLUMNS, values, additionalSQLCondition);		
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
		
		
		//Get sql Statement
		String sqlStatement = getRoomsAsSQLStatement(null, roomNumber, typeID, smoking, occupied, additionalSQLCondition);
				
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
				
				//Add roomType Object
				RoomType roomType = getRoomTypes(Integer.toString(room.getTypeID()), null, null, null, null, null).get(0);
				room.setType(roomType);
				
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
		//Convert Booleans to tinyint
		smoking = convertBooleanToTinyInt(smoking);
		occupied = convertBooleanToTinyInt(occupied);
		
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
	 * Inserts a room
	 * @param room
	 * @return Boolean
	 */
	public boolean insertRoom(Room room){
		//Get values in String format
		String roomNumber, typeID, smoking, occupied;
		if(room.getRoomNumber() == -1){
			roomNumber = null;
		} else {
			roomNumber = Integer.toString(room.getRoomNumber());
		}
		if(room.getTypeID() == -1){
			typeID = null;
		}
		else {
			typeID = Integer.toString(room.getTypeID());
		}
		smoking = Boolean.toString(room.isSmoking());
		occupied = Boolean.toString(room.isOccupied());
		
		//Convert Booleans to tinyint
		smoking = convertBooleanToTinyInt(smoking);
		occupied = convertBooleanToTinyInt(occupied);
				
		return insertRoom(roomNumber, typeID, smoking, occupied);
	}
	
	
	
	
	//------------------------------------------------------------------------------------------
	//-----------------------------------------ROOMTYPE-----------------------------------------
	//------------------------------------------------------------------------------------------
	
	/**
	 * Returns the Select Statement to get all room types specified by the given parameters.
	 * If a parameter is null or empty it won't be recorded in the sql Statement.
	 * @param typeID
	 * @param name
	 * @param picture
	 * @param standardprice
	 * @param description
	 * @param additionalSQLCondition
	 * @return String
	 */
	public String getRoomTypesAsSQLStatement(String []selectedColumns, String typeID, String name, String picture, String standardprice, String description, String additionalSQLCondition){
		//Write Values and Tables in Arrays
		String []values = {typeID, name, picture, standardprice, description};
		String []tables = {ROOMTYPE};
		
		if(selectedColumns==null){
			selectedColumns = ROOMTYPE_COLUMNS;
		}
				
		//Get SQL Statement
		return createSelectStatement(tables, selectedColumns, ROOMTYPE_COLUMNS, values, additionalSQLCondition);
				
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
		
		String sqlStatement = getRoomTypesAsSQLStatement(null, typeID, name, picture, standardprice, description, additionalSQLCondition);
		
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
				
				roomType.setSpecialPrices(getSpecialPrices(Integer.toString(roomType.getRoomTypeID()), null, null, null, null));
				roomType.setWeekdayPrices(getWeekdayPrices(Integer.toString(roomType.getRoomTypeID()), null, null, null));
				
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
		//Create value array
		String []values = {typeID, name, picture, standardPrice, description};
				
		//Return cell List with contained values
		List<Cell> cells = getCellList(ROOMTYPE_COLUMNS, values);
		
		//Create insert SQL Statement
		String sqlStatement = createInsertStatement(ROOMTYPE, cells);
		
		//Execute update
		return executeUpdate(sqlStatement);	
	}
	
	
	/**
	 * Inserts a room type into the database
	 * @param roomType
	 * @return Boolean
	 */
	public boolean insertRoomType(RoomType roomType){
		String typeID, name, picture, standardPrice, description;
		
		if(roomType.getRoomTypeID() == -1){
			typeID = null;
		}
		else{
			typeID = Integer.toString(roomType.getRoomTypeID());
		}
		name = roomType.getName();
		picture = roomType.getPictureRessource();
		if(roomType.getStandardPrice() == -1){
			standardPrice = null;
		}
		else{
			standardPrice = Double.toString(roomType.getStandardPrice());
		}
		description = roomType.getDescription();
		
		
		return insertRoomType(typeID, name, picture, standardPrice, description);
	}
	
	
	
	//------------------------------------------------------------------------------------------
	//-----------------------------------------RATING-------------------------------------------
	//------------------------------------------------------------------------------------------
	
	/**
	 * Returns the Select Statement to get all ratings specified by the given parameters.
	 * If a parameter is null or empty it won't be recorded in the sql Statement.
	 * @param ratingID
	 * @param roomTypeID
	 * @param guestID
	 * @param rating
	 * @param comment
	 * @param additionalSQLCondition
	 * @return String
	 */
	public String getRatingsAsSQLStatement(String []selectedColumns, String ratingID, String roomTypeID, String guestID, String rating, String comment, String additionalSQLCondition){
		//Write Values and Tables in Arrays
		String []values = {ratingID, roomTypeID, guestID, rating, comment};
		String []tables = {RATING};
		
		if(selectedColumns == null){
			selectedColumns = RATING_COLUMNS;
		}
						
		//Get SQL Statement
		return createSelectStatement(tables, selectedColumns, RATING_COLUMNS, values, additionalSQLCondition);
					
	}
	
	/**
	 * Returns all room types from the database that are specified by the parameters.
	 * If parameter is null it won't be included in the conditions from the sql statement.
	 * @param ratingID
	 * @param roomTypeID
	 * @param guestID
	 * @param rating
	 * @param comment
	 * @param additionalSQLCondition
	 * @return
	 */
	public List<Rating> getRatings(String ratingID, String roomTypeID, String guestID, String rating, String comment, String additionalSQLCondition){
		List<Rating> ratings = new ArrayList<Rating>();
		
		//Create Select SQL Statement
		String sqlStatement = getRatingsAsSQLStatement(null, ratingID, roomTypeID, guestID, rating, comment, additionalSQLCondition);
		
		//Execute Query
		ResultSet rs = executeQueryAndReturnResultSet(sqlStatement);
				
		//Process Result Set
		try{
			while(rs.next()){
				Rating ratingObj = new Rating();
				
				ratingObj.setRatingID(rs.getInt(RATING_RATINGID));
				ratingObj.setTypeID(rs.getInt(RATING_ROOMTYPEID));
				ratingObj.setGuestID(rs.getInt(RATING_GUESTID));
				ratingObj.setRating(rs.getInt(RATING_RATING));
				ratingObj.setComment(rs.getString(RATING_COMMENT));
				
				//Set objects of rating
				ratingObj.setRoomType(getRoomTypes(Integer.toString(ratingObj.getTypeID()), null, null, null, null, null).get(0));
				//TODO ratingObj.setGuest(Guest);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Read ResultSet Failed.");
			e.printStackTrace();
			return null;
		}
		
		return ratings;
	}
	
	
	/**
	 * Inserts a Rating into the database specified from the given parameter.
	 * If a parameter is null or empty it won't be recorded in the SQL Statement.
	 * @param ratingID
	 * @param roomTypeID
	 * @param guestID
	 * @param rating
	 * @param comment
	 * @return Boolean
	 */
	public boolean insertRating(String ratingID, String roomTypeID, String guestID, String rating, String comment){
		
		//Create value array
		String []values = {ratingID, roomTypeID, guestID, rating, comment};
		
		//Return cell List with contained values
		List<Cell> cells = getCellList(RATING_COLUMNS, values);
				
		//Create insert SQL Statement
		String sqlStatement = createInsertStatement(RATING, cells);
				
		//Execute update
		return executeUpdate(sqlStatement);	
		
	}
	
	
	/**
	 * Inserts a Rating type into the database
	 * @param ratingObj
	 * @return Boolean
	 */
	public boolean insertRating(Rating ratingObj){
		String ratingID, roomTypeID, guestID, rating, comment;
		
		if(ratingObj.getRatingID() == -1){
			ratingID = null;
		}
		else {
			ratingID = Integer.toString(ratingObj.getRatingID());
		}
		if(ratingObj.getTypeID() == -1){
			roomTypeID = null;
		}
		else {
			roomTypeID = Integer.toString(ratingObj.getTypeID());
		}
		if(ratingObj.getGuestID() == -1){
			guestID = null;
		}
		else{
			guestID = Integer.toString(ratingObj.getGuestID());
		}
		if(ratingObj.getRating() == -1){
			rating = null;
		}
		else {
			rating = Integer.toString(ratingObj.getRating());
		}
		comment = ratingObj.getComment();
		
		return insertRating(ratingID, roomTypeID, guestID, rating, comment);
		
	}
	
	
	
	//------------------------------------------------------------------------------------------
	//-----------------------------------------CONSUMERPRODUCT----------------------------------
	//------------------------------------------------------------------------------------------
	
	/**
	 * Returns the Select Statement to get all consumer products specified by the given parameters.
	 * If a parameter is null or empty it won't be recorded in the sql Statement.
	 * @param consumerProductID
	 * @param name
	 * @param price
	 * @param additionalSQLCondition
	 * @return String
	 */
	public String getConsumerProductsAsSQLStatement(String []selectedColumns, String consumerProductID, String name, String price, String additionalSQLCondition){
		//Write Values and Tables in Arrays
		String []values = {consumerProductID, name, price};
		String []tables = {CONSUMERPRODUCT};
		
		if(selectedColumns == null){
			selectedColumns = CONSUMERPRODUCT_COLUMNS;
		}
				
		//Get SQL Statement
		return createSelectStatement(tables, selectedColumns, CONSUMERPRODUCT_COLUMNS, values, additionalSQLCondition);
				
	}
	
	/**
	 * Returns all Consumer Products from the database that are specified by the parameters.
	 * If parameter is null it won't be included in the conditions from the sql statement.
	 * @param consumerProductID
	 * @param name
	 * @param price
	 * @param additionalSQLCondition
	 * @return List<ConsumerProduct>
	 */
	public List<ConsumerProduct> getConsumerProducts(String consumerProductID, String name, String price, String additionalSQLCondition){
		List<ConsumerProduct> consumerProducts = new ArrayList<ConsumerProduct>();
		
		//Create Select SQL Statement
		String sqlStatement = getConsumerProductsAsSQLStatement(null, consumerProductID, name, price, additionalSQLCondition);
		
		//Execute Query
		ResultSet rs = executeQueryAndReturnResultSet(sqlStatement);
				
		//Process Result Set
		try{
			while(rs.next()){
				ConsumerProduct consumerProduct = new ConsumerProduct();
				
				consumerProduct.setProductID(rs.getInt(CONSUMERPRODUCT_PRODUCTID));
				consumerProduct.setName(rs.getString(CONSUMERPRODUCT_NAME));
				consumerProduct.setPrice(rs.getDouble(CONSUMERPRODUCT_PRICE));
				
				consumerProducts.add(consumerProduct);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Read ResultSet Failed.");
			e.printStackTrace();
			return null;
		}
			
		return consumerProducts;
	}
	
	
	/**
	 * Inserts a Consumer Product into the database specified from the given parameter.
	 * If a parameter is null or empty it won't be recorded in the SQL Statement.
	 * @param consumerProductID
	 * @param name
	 * @param price
	 * @return Boolean
	 */
	public boolean insertConsumerProduct(String consumerProductID, String name, String price){
		//Create value array
		String []values = {consumerProductID, name, price};
		
		//Return cell List with contained values
		List<Cell> cells = getCellList(CONSUMERPRODUCT_COLUMNS, values);
				
		//Create insert SQL Statement
		String sqlStatement = createInsertStatement(CONSUMERPRODUCT, cells);
				
		//Execute update
		return executeUpdate(sqlStatement);	
	}
	
	/**
	 * inserts a consumer product to the database
	 * @param consumerProduct
	 * @return Boolean
	 */
	public boolean insertConsumerProduct(ConsumerProduct consumerProduct){
		String consumerProductID, name, price;
		if(consumerProduct.getProductID() == -1){
			consumerProductID = null;
		}
		else {
			consumerProductID = Integer.toString(consumerProduct.getProductID());
		}
		name = consumerProduct.getName();
		if(consumerProduct.getPrice() == -1){
			price = null;
		}
		else {
			price = Double.toString(consumerProduct.getPrice());
		}
		
		return insertConsumerProduct(consumerProductID, name, price);
	}
	
	
	
	
	//------------------------------------------------------------------------------------------
	//-----------------------------------------RESERVATION--------------------------------------
	//------------------------------------------------------------------------------------------
	
	/**
	 * Returns the Select Statement to get all reservations specified by the given parameters.
	 * If a parameter is null or empty it won't be recorded in the sql Statement.
	 * @param reservationID
	 * @param guestID
	 * @param arrivalDate
	 * @param departureDate
	 * @param paid
	 * @param additionalSQLCondition
	 * @return String
	 */
	public String getReservationsAsSQLStatement(String []selectedColumns, String reservationID, String guestID, String arrivalDate, String departureDate, String paid, String additionalSQLCondition){
		// Check if Date is in right format
		if(!isNullOrEmpty(arrivalDate)){
			try {
				if (!arrivalDate.equals(mySQLDateFormat.format(mySQLDateFormat.parse(arrivalDate)))) {
					System.out.println("arrivalDate is not in right format! Please refer to \"HiltonDublinDBConnection.mySQLDateFormat!\"");
					return null;
				}
			} catch (ParseException e1) {
				System.out.println("arrivalDate is not in right format! Please refer to \"HiltonDublinDBConnection.mySQLDateFormat!\"");
				return null;
			}
		}
		if(!isNullOrEmpty(departureDate)){
			try {
				if (!arrivalDate.equals(mySQLDateFormat.format(mySQLDateFormat.parse(departureDate)))) {
					System.out.println("departureDate is not in right format! Please refer to \"HiltonDublinDBConnection.mySQLDateFormat!\"");
					return null;
				}
			} catch (ParseException e1) {
				System.out.println("departureDate is not in right format! Please refer to \"HiltonDublinDBConnection.mySQLDateFormat!\"");
				return null;
			}
		}

		// convert boolean to tinyint
		paid = convertBooleanToTinyInt(paid);

		// Write Values and Tables in Arrays
		String[] values = { reservationID, guestID, arrivalDate, departureDate, paid };
		String[] tables = { RESERVATION };
		
		if(selectedColumns == null){
			selectedColumns = RESERVATION_COLUMNS;
		}
		
		//Get SQL Statement
		return createSelectStatement(tables, selectedColumns, RESERVATION_COLUMNS, values, additionalSQLCondition);
	}
	
	/**
	 * Returns all Reservations from the database that are specified by the parameters.
	 * If parameter is null it won't be included in the conditions from the sql statement.
	 * @param reservationID
	 * @param guestID
	 * @param arrivalDate
	 * @param departureDate
	 * @param paid
	 * @param additionalSQLCondition
	 * @return List<Reservation>
	 */
	public List<Reservation> getReservations(String reservationID, String guestID, String arrivalDate, String departureDate, String paid, String additionalSQLCondition){
		List<Reservation> reservations = new ArrayList<Reservation>();
		
		//Create SQL Select Statement
		String sqlStatement = getReservationsAsSQLStatement(null, reservationID, guestID, arrivalDate, departureDate, paid, additionalSQLCondition);
		
		//Execute Query
		ResultSet rs = executeQueryAndReturnResultSet(sqlStatement);
				
		//Process Result Set
		try{
			while(rs.next()){
				Reservation reservation = new Reservation();
				
				reservation.setBookingNumber(rs.getInt(RESERVATION_RESERVATIONID));
				reservation.setGuestID(rs.getInt(RESERVATION_GUESTID));
				reservation.setArrivalDate(rs.getDate(RESERVATION_ARRIVALDATE));
				reservation.setDepartureDate(rs.getDate(RESERVATION_DEPARTUREDATE));
				reservation.setPaid(rs.getBoolean(RESERVATION_PAID));
				
				//TODO reservation.setGuests()
				reservation.setRooms(getReservedRooms(Integer.toString(reservation.getBookingNumber())));
				reservation.setConsumerProducts(getReservedProducts(Integer.toString(reservation.getBookingNumber())));
				
				reservations.add(reservation);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Read ResultSet Failed.");
			e.printStackTrace();
			return null;
		}
		
		return reservations;
	}
	
	
	/**
	 * Inserts a Reservation into the database specified from the given parameter.
	 * If a parameter is null or empty it won't be recorded in the SQL Statement.
	 * @param reservationID
	 * @param guestID
	 * @param arrivalDate
	 * @param departureDate
	 * @param paid
	 * @param additionalSQLCondition
	 * @return
	 */
	public boolean insertReservation(String reservationID, String guestID, String arrivalDate, String departureDate, String paid){
		
		//Check if Date is in right format
		
		try {
			if(isNullOrEmpty(arrivalDate)){
				System.out.println("arrivalDate is null or empty!");
			}
			if(!arrivalDate.equals(mySQLDateFormat.format(mySQLDateFormat.parse(arrivalDate)))){
				System.out.println("arrivalDate is not in right format! Please refer to \"HiltonDublinDBConnection.mySQLDateFormat!\"");
				return false;
			}
		} catch (ParseException e1) {
			System.out.println("arrivalDate is not in right format! Please refer to \"HiltonDublinDBConnection.mySQLDateFormat!\"");
			return false;
		}
		try {
			if(isNullOrEmpty(departureDate)){
				System.out.println("departureDate is null or empty!");
			}
			if(!arrivalDate.equals(mySQLDateFormat.format(mySQLDateFormat.parse(departureDate)))){
				System.out.println("departureDate is not in right format! Please refer to \"HiltonDublinDBConnection.mySQLDateFormat!\"");
				return false;
			}
		} catch (ParseException e1) {
			System.out.println("departureDate is not in right format! Please refer to \"HiltonDublinDBConnection.mySQLDateFormat!\"");
			return false;
		}
		
		//Convert boolean to tinyint
		paid = convertBooleanToTinyInt(paid);
		
		
		//Create value array
		String []values = {reservationID, guestID, arrivalDate, departureDate, paid};
		
		//Return cell List with contained values
		List<Cell> cells = getCellList(RESERVATION_COLUMNS, values);
				
		//Create insert SQL Statement
		String sqlStatement = createInsertStatement(RESERVATION, cells);
				
		//Execute update
		return executeUpdate(sqlStatement);	
		
	}
	
	/**
	 * Inserts a Reservation to the Database
	 * Inserts the assigned Rooms from that reservation to the Reserved_Rooms table of the Database
	 * Inserts the assigned Products from that reservation to the Reserved_Product table of the Database
	 * @param reservation
	 * @return boolean
	 */
	public boolean insertReservation(Reservation reservation){
		String reservationID, guestID, arrivalDate, departureDate, paid;
		
		if(reservation.getBookingNumber() == -1){
			reservationID = null;
		}
		else {
			reservationID = Integer.toString(reservation.getBookingNumber());
		}
		if(reservation.getGuestID() == -1){
			guestID = null;
		}
		else {
			guestID = Integer.toString(reservation.getGuestID());
		}
		arrivalDate = mySQLDateFormat.format(reservation.getArrivalDate());
		departureDate = mySQLDateFormat.format(reservation.getDepartureDate());
		paid = Boolean.toString(reservation.isPaid());
		
		//Convert boolean to tinyint
		paid = convertBooleanToTinyInt(paid);
				
		return insertReservation(reservationID, guestID, arrivalDate, departureDate, paid);
	}
	
	
	
	
	//------------------------------------------------------------------------------------------
	//-----------------------------------------SPECIALPRICE-------------------------------------
	//------------------------------------------------------------------------------------------
	
	/**
	 * Returns the Select Statement to get all special prices specified by the given parameters.
	 * If a parameter is null or empty it won't be recorded in the sql Statement.
	 * @param roomTypeID
	 * @param date
	 * @param price
	 * @param comment
	 * @param additionalSQLCondition
	 * @return String
	 */
	public String getSpecialPricesAsSQLStatements(String []selectedColumns, String roomTypeID, String date, String price, String comment, String additionalSQLCondition){
		//Check if Date is in right format
		if(!isNullOrEmpty(date)){
			try {
				if(!date.equals(mySQLDateFormat.format(mySQLDateFormat.parse(date)))){
					System.out.println("date is not in right format! Please refer to \"HiltonDublinDBConnection.mySQLDateFormat!\"");
					return null;
				}
			} catch (ParseException e1) {
				System.out.println("date is not in right format! Please refer to \"HiltonDublinDBConnection.mySQLDateFormat!\"");
				return null;
			}
		}
		
		
		//Write Values and Tables in Arrays
		String []values = {roomTypeID, date, price, comment};
		String []tables = {SPECIALPRICE};
		
		if(selectedColumns == null){
			selectedColumns = SPECIALPRICE_COLUMNS;
		}
				
		//Get SQL Statement
		return createSelectStatement(tables, selectedColumns, SPECIALPRICE_COLUMNS, values, additionalSQLCondition);
				
	}
	
	/**
	 * Returns all special prices from the database that are specified by the parameters.
	 * If parameter is null it won't be included in the conditions from the sql statement.
	 * @param roomTypeID
	 * @param date
	 * @param price
	 * @param comment
	 * @param additionalSQLCondition
	 * @return List<SpecialPrice>
	 */
	public List<SpecialPrice> getSpecialPrices(String roomTypeID, String date, String price, String comment, String additionalSQLCondition){
		List<SpecialPrice> specialPrices = new ArrayList<SpecialPrice>();		
		
		//Create Select SQL Statement
		String sqlStatement = getSpecialPricesAsSQLStatements(null, roomTypeID, date, price, comment, additionalSQLCondition);
				
		//Execute Query
		ResultSet rs = executeQueryAndReturnResultSet(sqlStatement);
				
		//Process Result Set
		try{
			while(rs.next()){
				SpecialPrice specialPrice = new SpecialPrice();
				
				specialPrice.setTypeID(rs.getInt(SPECIALPRICE_ROOMTYPEID));
				specialPrice.setDate(rs.getDate(SPECIALPRICE_DATE));
				specialPrice.setPrice(rs.getDouble(SPECIALPRICE_PRICE));
				specialPrice.setComment(rs.getString(SPECIALPRICE_COMMENT));
				
				specialPrices.add(specialPrice);				
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Read ResultSet Failed.");
			e.printStackTrace();
			return null;
		}
		
		return specialPrices;
	}
	
	/**
	 * Inserts a special price into the database specified from the given parameter.
	 * If a parameter is null or empty it won't be recorded in the SQL Statement.
	 * @param roomTypeID
	 * @param date
	 * @param price
	 * @param comment
	 * @return boolean
	 */
	public boolean insertSpecialPrice(String roomTypeID, String date, String price, String comment) {
		// Check if Date is in right format
		try {
			if(isNullOrEmpty(date)){
				System.out.println("date is null or empty!");
			}
			if (!date.equals(mySQLDateFormat.format(mySQLDateFormat.parse(date)))) {
				System.out.println("date is not in right format! Please refer to \"HiltonDublinDBConnection.mySQLDateFormat!\"");
				return false;
			}
		} catch (ParseException e1) {
			System.out.println("date is not in right format! Please refer to \"HiltonDublinDBConnection.mySQLDateFormat!\"");
			return false;
		}

		// Create value array
		String[] values = { roomTypeID, date, price, comment };

		// Return cell List with contained values
		List<Cell> cells = getCellList(SPECIALPRICE_COLUMNS, values);

		// Create insert SQL Statement
		String sqlStatement = createInsertStatement(SPECIALPRICE, cells);

		// Execute update
		return executeUpdate(sqlStatement);
	}

	/**
	 * Inserts a special price to the database
	 * @param specialPrice
	 * @return boolean
	 */
	public boolean insertSpecialPrice(SpecialPrice specialPrice){
		String roomTypeID, date, price, comment;
		
		if(specialPrice.getTypeID() == -1){
			roomTypeID = null;
		}
		else{
			roomTypeID = Integer.toString(specialPrice.getTypeID());
		}
		date = mySQLDateFormat.format(specialPrice.getDate());
		if(specialPrice.getPrice() == -1){
			price = null;
		}
		else {
			price = Double.toString(specialPrice.getPrice());
		}
		comment = specialPrice.getComment();

		return insertSpecialPrice(roomTypeID, date, price, comment);
	}
	
	
	//------------------------------------------------------------------------------------------
	//-----------------------------------------WEEKDAYPRICES------------------------------------
	//------------------------------------------------------------------------------------------
	
	/**
	 * Returns the Select Statement to get all weekday prices specified by the given parameters.
	 * If a parameter is null or empty it won't be recorded in the sql Statement.
	 * @param roomTypeID
	 * @param price
	 * @param weekday
	 * @param additionalSQLCondition
	 * @return String
	 */
	public String getWeekdayPricesAsSQLStatement(String []selectedColumns, String roomTypeID, String price, String weekday, String additionalSQLCondition){
		//Write Values and Tables in Arrays
		String []values = {roomTypeID, price, weekday};
		String []tables = {WEEKDAYPRICE};
		
		if(selectedColumns == null){
			selectedColumns = WEEKDAYPRICE_COLUMNS;
		}
				
		//Get SQL Statement
		return createSelectStatement(tables, selectedColumns, WEEKDAYPRICE_COLUMNS, values, additionalSQLCondition);
				
	}
	
	/**
	 * Returns all special prices from the database that are specified by the parameters.
	 * If parameter is null it won't be included in the conditions from the sql statement.
	 * @param roomTypeID
	 * @param price
	 * @param weekday
	 * @param additionalSQLCondition
	 * @return List<WeekdayPrice>
	 */
	public List<WeekdayPrice> getWeekdayPrices(String roomTypeID, String price, String weekday, String additionalSQLCondition){
		List<WeekdayPrice> weekdayPrices = new ArrayList<WeekdayPrice>();
		
		//Create Select SQL Statement
		String sqlStatement = getWeekdayPricesAsSQLStatement(null, roomTypeID, price, weekday, additionalSQLCondition);
		
		//Execute Query
		ResultSet rs = executeQueryAndReturnResultSet(sqlStatement);
				
		//Process Result Set
		try{
			while(rs.next()){
				WeekdayPrice weekdayPrice = new WeekdayPrice();
				
				weekdayPrice.setRoomTypeID(rs.getInt(WEEKDAYPRICE_ROOMTYPEID));
				weekdayPrice.setPrice(rs.getDouble(WEEKDAYPRICE_PRICE));
				weekdayPrice.setWeekday(rs.getInt(WEEKDAYPRICE_WEEKDAY));
				
				weekdayPrices.add(weekdayPrice);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Read ResultSet Failed.");
			e.printStackTrace();
			return null;
		}
		
		return weekdayPrices;
	}
	
	/**
	 * Inserts a weekday price into the database specified from the given parameter.
	 * If a parameter is null or empty it won't be recorded in the SQL Statement.
	 * @param roomTypeID
	 * @param price
	 * @param weekday
	 * @return boolean
	 */
	public boolean insertWeekdayPrice(String roomTypeID, String price, String weekday){
		
		// Create value array
		String[] values = { roomTypeID, price, weekday };

		// Return cell List with contained values
		List<Cell> cells = getCellList(WEEKDAYPRICE_COLUMNS, values);

		// Create insert SQL Statement
		String sqlStatement = createInsertStatement(WEEKDAYPRICE, cells);

		// Execute update
		return executeUpdate(sqlStatement);
		
	}
	
	/**
	 * Inserts a weekday price into the database
	 * @param weekdayPrice
	 * @return boolean
	 */
	public boolean insertWeekdayPrice(WeekdayPrice weekdayPrice){
		String roomTypeID, price, weekday;
		
		if(weekdayPrice.getRoomTypeID() == -1){
			roomTypeID = null;
		}
		else {
			roomTypeID = Integer.toString(weekdayPrice.getRoomTypeID());
		}
		if(weekdayPrice.getPrice() == -1){
			price = null;
		}
		else {
			price = Double.toString(weekdayPrice.getPrice());
		}
		if(weekdayPrice.getWeekday() == -1){
			weekday = null;
		}
		else {
			weekday = Integer.toString(weekdayPrice.getWeekday());
		}
		
		return insertWeekdayPrice(roomTypeID, price, weekday);
		
	}
	
	//------------------------------------------------------------------------------------------
	//-----------------------------------------RESERVED_ROOMS-----------------------------------
	//------------------------------------------------------------------------------------------
	
	/**
	 * 
	 * @param []selectedColumns
	 * @param roomNumber
	 * @param reservationID
	 * @param additionalSQLCondition
	 * @return String
	 */
	public String getReserved_RoomsAsSQLStatement(String []selectedColumns, String roomNumber, String reservationID, String additionalSQLCondition){
		//Write Values and Tables in Arrays
		String []values = {roomNumber, reservationID};
		String []tables = {RESERVED_ROOM};
		
		if(selectedColumns == null){
			selectedColumns = RESERVED_ROOM_COLUMNS;
		}
						
		//Get SQL Statement
		return createSelectStatement(tables, selectedColumns, RESERVED_ROOM_COLUMNS, values, additionalSQLCondition);
	}
	
	/**
	 * Returns a List of all rooms that are assigned to one reservation
	 * @param reservationID
	 * @return List<Room>
	 */
	public List<Room> getReservedRooms(String reservationID){
		String [] subQuerySelectedColumns = { RESERVED_ROOM_ROOMNUMBER };
		return getRooms(null, null, null, null, ROOM_NUMBER + " IN ( " + getReserved_RoomsAsSQLStatement(subQuerySelectedColumns, null, reservationID, null) + " )");
	}
	
	/**
	 * Assigns a room to a reservation. Inserts a Reserved_Room into the database specified by the given parameters.
	 * If a parameter is null or empty it won't be recorded in the SQL Statement.
	 * @param roomNumber
	 * @param reservationID
	 * @return
	 */
	public boolean assignRoomToReservation(String roomNumber, String reservationID){
		// Create value array
		String[] values = { roomNumber, reservationID };

		// Return cell List with contained values
		List<Cell> cells = getCellList(RESERVED_ROOM_COLUMNS, values);

		// Create insert SQL Statement
		String sqlStatement = createInsertStatement(RESERVED_ROOM, cells);

		// Execute update
		return executeUpdate(sqlStatement);
				
	}
	
	/**
	 * Assigns a room to a reservation. Inserts a Reserved_Room into the database.
	 * @param room
	 * @param reservation
	 * @return
	 */
	public boolean assignRoomToReservation(Room room, Reservation reservation){
		return assignRoomToReservation(Integer.toString(room.getRoomNumber()), Integer.toString(reservation.getBookingNumber()));
	}
	
	
	
	
	//------------------------------------------------------------------------------------------
	//-----------------------------------------RESERVED_PRODUCTS--------------------------------
	//------------------------------------------------------------------------------------------
		
	/**
	 * 
	 * @param []selectedColumns
	 * @param productID
	 * @param reservationID
	 * @param additionalSQLCondition
	 * @return String
	 */
	public String getReserved_ProductAsSQLStatement(String []selectedColumns, String productID, String reservationID, String additionalSQLCondition){
		//Write Values and Tables in Arrays
		String []values = {productID, reservationID};
		String []tables = {RESERVED_ROOM};
			
		if(selectedColumns == null){
			selectedColumns = RESERVED_ROOM_COLUMNS;
		}
						
		//Get SQL Statement
		return createSelectStatement(tables, selectedColumns, RESERVED_ROOM_COLUMNS, values, additionalSQLCondition);
	}
		
	/**
	 * Returns a List of all products that are assigned to one reservation
	 * @param productID
	 * @return List<Room>
	 */
	public List<ConsumerProduct> getReservedProducts(String reservationID){
		String [] subQuerySelectedColumns = { RESERVED_PRODUCT_PRODUCTID };
		return getConsumerProducts(null, null, null, CONSUMERPRODUCT_PRODUCTID + " IN ( " + getReserved_ProductAsSQLStatement(subQuerySelectedColumns, null, reservationID, null) + " )");
	}
	
	/**
	 * Assigns a product to a reservation. Inserts a Reserved_Product into the database specified by the given parameters.
	 * If a parameter is null or empty it won't be recorded in the SQL Statement.
	 * @param productID
	 * @param reservationID
	 * @return
	 */
	public boolean assignProductToReservation(String productID, String reservationID){
		// Create value array
		String[] values = { productID, reservationID };
		// Return cell List with contained values
		List<Cell> cells = getCellList(RESERVED_PRODUCT_COLUMNS, values);

		// Create insert SQL Statement
		String sqlStatement = createInsertStatement(RESERVED_PRODUCT, cells);
		// Execute update
		return executeUpdate(sqlStatement);
				
	}
		
	/**
	 * Assigns a product to a reservation. Inserts a Reserved_Product into the database.
	 * @param product
	 * @param reservation
	 * @return
	 */
	public boolean assignProductToReservation(ConsumerProduct product, Reservation reservation){
		return assignRoomToReservation(Integer.toString(product.getProductID()), Integer.toString(reservation.getBookingNumber()));
	}
	
	
	
	
	//------------------------------------------------------------------------------------------
	//----------------------------------Additional Functions------------------------------------
	//------------------------------------------------------------------------------------------
	public boolean roomsAvailable(RoomType roomType, int ammountOfRooms, Date arrivalDate, Date departureDate){
		//TODO check if rooms from a certain room type are available to a certain time
		
		return true;
	}

}
