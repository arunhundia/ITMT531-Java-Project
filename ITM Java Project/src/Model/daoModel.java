package Model;
/**
 * The package model contains two classes Connector and daoModel
 * DaoModel contains 3 methods DB_create() table, DB_insert(), getResultSet().
 * We are getting the conn1 from connector class which will be used to store in statement.
 * creating a connector object in this class
 */

import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.PreparedStatement;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class daoModel {

	Connector DB_con = new Connector();
	private Statement ah_statement = null;
	private static ResultSet result;
	ResultSet rs;
	// private static java.sql.Connection conn2 = null;
	// creating a new table

	public void insert_admin_movie(int movie_id, String movie_name, String Director) throws Exception 
	{
		boolean validMovieID =true;
		try
		{
			String query = "Select Movie_id from a_hund_Movie;";
			PreparedStatement pst = (PreparedStatement) DB_con.getConnection().prepareStatement(query);
			rs=pst.executeQuery(query);
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		while(rs.next())
		{
			if(rs.getInt("Movie_id") == movie_id)
				validMovieID = false;
		}
		if(validMovieID)
		{
			try 
			{
				String query = "insert into a_hund_movie (Movie_id,Movie_name,Director)" + " values('" + movie_id + "','"
						+ movie_name + "','" + Director + "');";
				PreparedStatement pst = (PreparedStatement) DB_con.getConnection().prepareStatement(query);
				pst.executeUpdate(query);
				System.out.println("Movie Inserted");
				Alert insert_nmovie__alert = new Alert(AlertType.INFORMATION);
				insert_nmovie__alert.setTitle("Movie ID");
				insert_nmovie__alert.setHeaderText(null);
				insert_nmovie__alert.setContentText("New Movie Added");
				insert_nmovie__alert.showAndWait();
			} 
			catch (Exception e) 
				{
				System.out.println("Error while inserting data");
				System.out.println(e.getMessage());
				}
			DB_con.conn1.close();
		}
		else
		{
			
			Alert duplicate_movie__alert = new Alert(AlertType.INFORMATION);
			duplicate_movie__alert.setTitle("Duplicate Movie");
			duplicate_movie__alert.setHeaderText(null);
			duplicate_movie__alert.setContentText("Please enter unique Movie ID");
			duplicate_movie__alert.showAndWait();
		}
		

	}// end method

	public void insert_screen(int screen_id, int total_seats) throws Exception 
	{
		boolean validScreenID =true;
		try
		{
			String query = "Select screen_id from a_hund_screen;";
			PreparedStatement pst = (PreparedStatement) DB_con.getConnection().prepareStatement(query);
			rs = pst.executeQuery(query);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		while(rs.next())
		{
			if(rs.getInt("screen_id") == screen_id)
					validScreenID = false;
		}
		
		
		if(validScreenID)
		{
			try {
					String query = "insert into a_hund_screen (screen_id,Total_seats)" + " values('" + screen_id + "','" + total_seats
							+ "');";
					PreparedStatement pst = (PreparedStatement) DB_con.getConnection().prepareStatement(query);
					pst.executeUpdate(query);
					System.out.println("New screen Added");
					Alert insert_nscreen__alert = new Alert(AlertType.INFORMATION);
					insert_nscreen__alert.setTitle("Screen");
					insert_nscreen__alert.setHeaderText(null);
					insert_nscreen__alert.setContentText("New Screen Added");
					insert_nscreen__alert.showAndWait();
				} catch (Exception e) 
				{
					System.out.println("Error while inserting data");
					System.out.println(e.getMessage());
				}
			DB_con.conn1.close();
		}
		else
		{
			System.out.println("New screen Added");
			Alert duplicate_screeID__alert = new Alert(AlertType.INFORMATION);
			duplicate_screeID__alert.setTitle("Duplicate screen");
			duplicate_screeID__alert.setHeaderText(null);
			duplicate_screeID__alert.setContentText("Please enter unique screen ID");
			duplicate_screeID__alert.showAndWait();
		}
	}// end method

	public void insert_show(int screen_id, int movie_id, String movie_date, int seats_available, String movie_time)
			throws Exception {
		try {
			String query = "insert into a_hund_showdetail (screen_id,movie_id,show_Date,Seats_available,show_Time)" + " "
					+ "values('" + screen_id + "','" + movie_id + "','" + movie_date + "','" + seats_available + "','"
					+ movie_time + "');";
			PreparedStatement pst = (PreparedStatement) DB_con.getConnection().prepareStatement(query);
			pst.executeUpdate(query);
			Alert insert_nshow__alert = new Alert(AlertType.INFORMATION);
			insert_nshow__alert.setTitle("Show");
			insert_nshow__alert.setHeaderText(null);
			insert_nshow__alert.setContentText("New Show Added");
			insert_nshow__alert.showAndWait();
			System.out.println("New Shows Added");
			
		} catch (Exception e) {
			System.out.println("Error while inserting data");
			System.out.println(e.getMessage());
		}
		DB_con.conn1.close();
	}// end method

	public void delete_movie(int delete_movie_id) throws Exception 
	{
		boolean validMovieID =false;
		try
		{
			String query = "Select Movie_id from a_hund_Movie;";
			PreparedStatement pst = (PreparedStatement) DB_con.getConnection().prepareStatement(query);
			rs=pst.executeQuery(query);
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		while(rs.next())
		{
			if(rs.getInt("Movie_id") == delete_movie_id)
				validMovieID = true;
		}
		if(validMovieID)
		{
			try {
				String query = "delete from a_hund_showdetail where movie_id=" + delete_movie_id;
				String query_1 = "delete from a_hund_movie where movie_id=" + delete_movie_id;
				PreparedStatement pst = (PreparedStatement) DB_con.getConnection().prepareStatement(query);
				pst.executeUpdate(query);
				System.out.println("Deleted movie from show detail Table ");
				pst.executeUpdate(query_1);
				System.out.println("Deleted movie from Movie Table");
				Alert delete_movie__alert = new Alert(AlertType.INFORMATION);
				delete_movie__alert.setTitle("Movie Deleted");
				delete_movie__alert.setHeaderText(null);
				delete_movie__alert.setContentText("Movie and Shows have been deleted");
				delete_movie__alert.showAndWait();
			} catch (Exception e) {
				System.out.println("Error while inserting data");
				System.out.println(e.getMessage());
			}	
		}
		else
		{
			Alert delete_movie__alert = new Alert(AlertType.INFORMATION);
			delete_movie__alert.setTitle("No Movie ID");
			delete_movie__alert.setHeaderText(null);
			delete_movie__alert.setContentText("Movie ID is not present");
			delete_movie__alert.showAndWait();
		}
		DB_con.conn1.close();
	}// end method

	public void delete_show(int delete_show_id) throws Exception 
	{
		boolean validShowID =false;
		try
		{
			String query = "Select show_id from a_hund_showdetail;";
			PreparedStatement pst = (PreparedStatement) DB_con.getConnection().prepareStatement(query);
			rs=pst.executeQuery(query);
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		while(rs.next())
		{
			if(rs.getInt("show_id") == delete_show_id)
				validShowID = true;
		}
		if(validShowID)
		{
		try {
			String query = "delete from a_hund_showdetail where show_id=" + delete_show_id;
			PreparedStatement pst = (PreparedStatement) DB_con.getConnection().prepareStatement(query);
			pst.executeUpdate(query);
			Alert delete_show__alert = new Alert(AlertType.INFORMATION);
			delete_show__alert.setTitle("Show Deleted");
			delete_show__alert.setHeaderText(null);
			delete_show__alert.setContentText("Show have been deleted");
			delete_show__alert.showAndWait();
			System.out.println("Deleted Shows from show detail Table ");

		} catch (Exception e) {
			System.out.println("Error while inserting data");
			System.out.println(e.getMessage());
		}
		
	}
		else
		{
			Alert delete_movie__alert = new Alert(AlertType.INFORMATION);
			delete_movie__alert.setTitle("No Show");
			delete_movie__alert.setHeaderText(null);
			delete_movie__alert.setContentText("Show ID is not present");
			delete_movie__alert.showAndWait();
		}
		DB_con.conn1.close();
	}

}// end class
