package Model;

/**
 * The package model contains two classes Connector and daoModel
 * Connector class is just used to connect to the database using the host/url,username and password.
 * Checking the mysql driver 
 * We are creating a connection conn1 and storing the credentials and database url in it.
 * importing sql classes
 * We are returning the conn1 to DaoModel class 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import com.mysql.jdbc.DatabaseMetaData;

public class Connector {

	public Connection conn1 = null;

	public Connection getConnection() {

		// System.out.println("Checking the Connection JDBC Driver");
		// Checking the mysql driver is in the package or not
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}

		catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Driver Missing");
			e.printStackTrace();

		}

		// connecting DB

		try {
			
			conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab_3", "root", "arunhundia23@");

			if (conn1 != null) {
				// System.out.println("Connected to the database 510Labs");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return conn1;
	}// end method

}// end class
