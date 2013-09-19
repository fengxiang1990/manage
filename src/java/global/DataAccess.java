package global;

import java.sql.Connection;
import java.sql.DriverManager;



public class DataAccess {

	protected static Connection dbConnection=null;
    protected static String driverName = Config.dbdriver;
    protected static String dbURL = Config.dburl;
    protected static String userID = Config.dbuser;
    protected static String passwd = Config.dbpass; 
	public static Connection getConnection() {
		// TODO Auto-generated method stub
		try{
            Class.forName(driverName);
            dbConnection = DriverManager.getConnection(dbURL,userID,passwd);
            return dbConnection;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
     
}
