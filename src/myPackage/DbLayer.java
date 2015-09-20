package myPackage;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class DbLayer {
	private final static String DB_URL_TEMPLATE = "jdbc:sqlserver://%s;databaseName=%s;";
	private final static String CREATE_CLIENTS_TABLE = "CREATE TABLE CLIENTS("
			+"ID INTEGER IDENTITY PRIMARY KEY CLUSTERED, "
			+ "FIRSTNAME VARCHAR(20) NOT NULL,"
			+ "LASTNAME VARCHAR(20) NOT NULL," 
			+ "BIRTH_DATE DATE," 
			+ "ADDRESS VARCHAR(20) NOT NULL,"
			+ "PHONE VARCHAR(20) NOT NULL,"
			+ "EMAIL VARCHAR(20) NOT NULL"
			+ ")";
	private final static String CREATE_CARS_TABLE="CREATE TABLE CARS("
			+"ID INTEGER IDENTITY PRIMARY KEY CLUSTERED, "
			+ "MAKE VARCHAR(20) NOT NULL,"
			+ "MODEL VARCHAR(20) NOT NULL," 
			+ "YEAR INTEGER,"
			+ "VIN VARCHAR(20) NOT NULL," 
			+ "CLIENT_ID BIGINT NOT NULL)";
	private final static String CREATE_ORDERS_TABLE="CREATE TABLE CARS("
			+"ID INTEGER IDENTITY PRIMARY KEY CLUSTERED, "
			+ "DATE DATETIME,"
			+ "AMOUNT INTEGER," 
			+ "STATUS VARCHAR(20) NOT NULL,"
			+ "CAR_ID BIGINT NOT NULL)";
	private static Connection connection;
	private final static String DB_HOST = "localhost";
	private final static String DB_USERNAME = "sa";
	private final static String DB_PASSWORD = "ta";
	private final static String DB_DATABASE = "PersonDB";
	
	private final static String ADD_CLIENT = "INSERT INTO CLIENTS(FIRSTNAME,LASTNAME,BIRTH_DATE,ADDRESS,PHONE,EMAIL)"
			+ "values(?,?,?,?,?,?)";
	private final static String UPDATE_CLIENT = "UPDATE CLIENTS "
			+ "SET FIRSTNAME=?,LASTNAME=?,BIRTH_DATE=?,ADDRESS=?,PHONE=?,EMAIL=? WHERE ID=?";
	private final static String DELETE_CLIENT = "DELETE FROM CLIENTS WHERE ID=?";
	private final static String SQL_GET_CLIENTS = "SELECT * "
			+ "FROM CLIENTS WHERE FIRSTNAME=? AND LASTNAME=?";
	
	private final static String ADD_CAR = "INSERT INTO CARS(MAKE,MODEL,YEAR,VIN,CLIENT_ID)"
			+ "values(?,?,?,?,?)";
	private final static String UPDATE_CAR = "UPDATE CARS "
			+ "SET MAKE=?,MODEL=?,YEAR=?,VIN=? WHERE ID=?";
	private final static String DELETE_CAR = "DELETE FROM CARS WHERE ID=?";
	private final static String GET_CAR = "SELECT *"
			+ "FROM CARS WHERE CLIENT_ID=?";
	private final static String CAR_EXISTS = "SELECT *"
			+ "FROM CARS WHERE VIN=?";
	//private final static String GET_CAR_ID
	
	private final static String ADD_ORDER = "INSERT INTO ORDERS(DATE,AMOUNT,STATUS,CAR_ID)"
			+ "values(?,?,?,?)";
	private final static String UPDATE_ORDER = "UPDATE ORDERS "
			+ "SET AMOUNT=?,STATUS=? WHERE ID=?";
	private final static String DELETE_ORDER = "DELETE FROM ORDERS WHERE ID=?";
	private final static String SQL_GET_ORDERS = "SELECT *"
			+ "FROM ORDERS WHERE CAR_ID=?";
	private static final String CLIENT_EXISTS = "SELECT *"
			+ "FROM CLIENTS WHERE FIRSTNAME=? AND LASTNAME=? AND PHONE=? AND EMAIL=?";
	private static final String GET_ORDER_ID = "SELECT ID FROM ORDERS WHERE AMOUNT=? AND STATUS=? AND CAR_ID=?";
	
	private static void createTable(String createSql) throws ClassNotFoundException {

		try {
			getConnection().createStatement().executeUpdate(createSql);
		} catch (SQLException e) {
			System.out.println("Table already exists");
			
		}
	}
	public static void editClient(int id, String name, String surname, Date date ,String address,String phone,String email) throws ClassNotFoundException, SQLException
	{
		PreparedStatement ps=getConnection().prepareStatement(UPDATE_CAR);
		ps.setString(1, name);
		ps.setString(2, surname);
		ps.setDate(3, date);
		ps.setString(4,address);
		ps.setString(5,phone);
		ps.setString(6,email);
		ps.setInt(7, id);
		ps.execute();
	}
	public static void editCar(int id, String make, String model, int year,String vin) throws ClassNotFoundException, SQLException
	{
		PreparedStatement ps=getConnection().prepareStatement(UPDATE_CAR);
		ps.setString(1, make);
		ps.setString(2, model);
		ps.setInt(3, year);
		ps.setString(4,vin);
		ps.setInt(5, id);
		ps.execute();
	}
	
	public static void editOrder(int id, int amount, String status) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ps=getConnection().prepareStatement(UPDATE_ORDER);
		ps.setInt(1, amount);
		ps.setString(2, status);
		ps.setInt(3, id);
		ps.execute();
		
	}
	public static boolean verifyCarExists(String vin,int client_id) throws ClassNotFoundException, SQLException
	{
		PreparedStatement ps=getConnection().prepareStatement(CAR_EXISTS);
		ps.setString(1, vin);
		ResultSet rs=ps.executeQuery();
		
	return (rs.next());
	}
	public static boolean verifyClientExists(String name,String surname,String phone,String email) throws ClassNotFoundException, SQLException
	{
		PreparedStatement ps=getConnection().prepareStatement(CLIENT_EXISTS);
		ps.setString(1,name);
		ps.setString(2, surname);
		ps.setString(3, phone);
		ps.setString(4, email);
		ResultSet rs=ps.executeQuery();
		
	return (rs.next());
	}
	public static int getCarID(String vin) throws SQLException, ClassNotFoundException
	{
		PreparedStatement ps=getConnection().prepareStatement(CAR_EXISTS);
		ps.setString(1, vin);
		ResultSet rs=ps.executeQuery();
		int id=0;
		while (rs.next())
		{
			id=rs.getInt(1);
			System.out.println("!!!!!!!!!!!!!!!"+id);
		}
		return id;
	}
	public static int getClientID(String name, String surname, String phone,String email) throws SQLException, ClassNotFoundException
	{
		PreparedStatement ps=getConnection().prepareStatement(CLIENT_EXISTS);
		ps.setString(1, name);
		ps.setString(2, surname);
		ps.setString(3, phone);
		ps.setString(4, email);
		ResultSet rs=ps.executeQuery();
		int id=0;
		while (rs.next())
		{
			id=rs.getInt(1);
			System.out.println("!!!!!!!!!!!!!!!"+id);
		}
		return id;
	}
	public static int getOrderID(String date, int amount, String status,
			int carId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ps=getConnection().prepareStatement(GET_ORDER_ID);
		ps.setInt(1, amount);
		ps.setString(2, status);
		ps.setInt(3, carId);
		ResultSet rs=ps.executeQuery();
		int id=0;
		while (rs.next())
		{
			id=rs.getInt(1);
			System.out.println("!!!!!!!!!!!!!!!"+id);
		}
		System.out.println("!!!!!!!!!!!!!!!"+id);
		return id;
	}
	public static void deleteCar(int id) throws ClassNotFoundException, SQLException
	{
		PreparedStatement ps=getConnection().prepareStatement(DELETE_CAR);
		ps.setInt(1, id);
		ps.execute();
	} 
	public static void deleteClient(int id) throws ClassNotFoundException, SQLException
	{
		PreparedStatement ps=getConnection().prepareStatement(DELETE_CLIENT);
		ps.setInt(1, id);
		ps.execute();
	} 
	public static void deleteOrder(int id) throws ClassNotFoundException, SQLException
	{
		PreparedStatement ps=getConnection().prepareStatement(DELETE_ORDER);
		ps.setInt(1, id);
		ps.execute();
	} 
	public static void addCar(String make,String model, int year,String vin,int client_id) throws ClassNotFoundException, SQLException
	{
		
		PreparedStatement psUpdateCar = getConnection()
				.prepareStatement(ADD_CAR);
		psUpdateCar.setString(1, make);
		psUpdateCar.setString(2, model);
		psUpdateCar.setInt(3, year);
		psUpdateCar.setString(4, vin);	
		psUpdateCar.setInt(5, client_id);
		psUpdateCar.execute();
		
			}
	public static void addClient(String name,String lastName, Date date,String address,String phone, String email) throws ClassNotFoundException, SQLException
	{
		PreparedStatement psUpdatePerson = getConnection()
				.prepareStatement(ADD_CLIENT);
		psUpdatePerson.setString(1, name);
		psUpdatePerson.setString(2, lastName);
		psUpdatePerson.setDate(3, date);
		psUpdatePerson.setString(4, address);
		psUpdatePerson.setString(5, phone);	
		psUpdatePerson.setString(6, email);
		psUpdatePerson.execute();
		}
	public static void addOrder(int amount, String status,int carId) throws ClassNotFoundException, SQLException
	{
		
		PreparedStatement psUpdateCar = getConnection()
				.prepareStatement(ADD_ORDER);
		
		java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	    psUpdateCar.setDate(1, sqlDate);
		psUpdateCar.setInt(2, amount);
		psUpdateCar.setString(3, status);
		psUpdateCar.setInt(4, carId);	
		psUpdateCar.execute();
		
			}
	public static ArrayList<Order>getOrdersList(int carId) throws ClassNotFoundException, SQLException
	{
		PreparedStatement psFindOrders=getConnection().prepareStatement(SQL_GET_ORDERS);
		psFindOrders.setInt(1, carId);
		ResultSet rs=psFindOrders.executeQuery();
		ArrayList<Order> orders=new ArrayList<Order>();
		while (rs.next()) {
			orders.add(new Order(rs.getInt(1), rs.getDate(2), rs
					.getInt(3), rs.getString(4), rs.getInt(5)));
		}
		return orders;
	} 
	public static ArrayList<Car>getCarsList(int clientId) throws ClassNotFoundException, SQLException
	{
		PreparedStatement psFindCars=getConnection().prepareStatement(GET_CAR);
		psFindCars.setInt(1, clientId);
		ResultSet rs=psFindCars.executeQuery();
		ArrayList<Car> cars=new ArrayList<Car>();
		while (rs.next()) {
			cars.add(new Car(rs.getInt(1), rs.getString(2), rs.getString(3), rs
					.getInt(4), rs.getString(5), rs.getInt(6)));
		}
		return cars;
	} 
	private static final Connection getConnection()
			throws ClassNotFoundException {
		System.out.println("getConnection");
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		if (null == connection) {
			System.out.println("getConnection -if");
			try {
				System.out.println("getConnection - try");
				connection = DriverManager.getConnection(
						String.format(DB_URL_TEMPLATE, DB_HOST, DB_DATABASE),
						DB_USERNAME, DB_PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return connection;

	}
	public static ArrayList<Client>getClientsList(String name, String surname) throws ClassNotFoundException, SQLException
	{
		PreparedStatement psFindClients=getConnection().prepareStatement(SQL_GET_CLIENTS);
		psFindClients.setString(1, name);
		psFindClients.setString(2, surname);
		ResultSet rs=psFindClients.executeQuery();
		ArrayList<Client> clients=new ArrayList<Client>();
		while (rs.next()) {
			clients.add(new Client(rs.getInt(1), rs.getString(2), rs
					.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6),rs.getString(7)));
		}
		System.out.println("clients are here");
		return clients;
	}
	
	
	
}
