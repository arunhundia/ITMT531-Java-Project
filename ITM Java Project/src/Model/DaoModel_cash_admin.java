package Model;
/*
 * This is a model class which contains cancel ticket functionality which is used by both admin and cashier.
 *  
 */
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DaoModel_cash_admin {
	Connector DB_con = new Connector();
	int seats_bked;
	int get_screen;
	int get_movie;
	String movie_date;
	String movie_time;
	int get_seats;
	int new_seats;
	ResultSet rs;

	public void delete_ticket(int ticket_id)
	{
		//get booked seats from current ticket table
		try {
			String tickets_query = "select Seats_booked,movie_id,screen_id,Show_Date,Show_Time from a_hund_ticket where ticket_id="
					+ ticket_id;
			PreparedStatement pst_tickets = (PreparedStatement) DB_con.getConnection().prepareStatement(tickets_query);
			rs = pst_tickets.executeQuery(tickets_query);
			if (rs.next()) {
				seats_bked = rs.getInt("Seats_booked");
				get_screen = rs.getInt("movie_id");
				get_movie = rs.getInt("screen_id");
				movie_date = rs.getString("Show_Date");
				movie_time = rs.getString("Show_Time");
			}

			//System.out.println("Tickets booked in ticket table" + seats_bked);
			//System.out.println(get_screen + " " + get_movie + " " + movie_date + " " + movie_time);
		} 
		catch (Exception e)
		{
			System.out.println("Error while retrieving Seats_booked data from ticket table");
			System.out.println(e.getMessage());
		}
		//delete from ticket table
		try
		{
			//delete from ticket table
			String delete_ticket_query = "delete from a_hund_ticket where ticket_id=" + ticket_id;
			PreparedStatement pst = (PreparedStatement) DB_con.getConnection().prepareStatement(delete_ticket_query);
			pst.executeUpdate(delete_ticket_query);
			System.out.println("Ticket deleted");
		} 
		catch (Exception e) 
		{
			System.out.println("Error while inserting data");
			System.out.println(e.getMessage());
		}
		try
		{
			// get the seats availabe from showdetail table
			String tickets_query = "select Seats_available from a_hund_showdetail where screen_id=? and movie_id=? and show_Date=? and show_time=?";
			PreparedStatement pst_tickets = (PreparedStatement) DB_con.getConnection().prepareStatement(tickets_query);
			pst_tickets.setInt(1, get_screen);
			pst_tickets.setInt(2, get_movie);
			pst_tickets.setString(3, movie_date);
			pst_tickets.setString(4, movie_time);

			rs = pst_tickets.executeQuery();
			if (rs.next()) {
				get_seats = rs.getInt("Seats_available");
			}

			//System.out.println("Tickets available in show detail" + get_seats);
		} 
		catch (Exception e) 
		{
			System.out.println("Error while retrieving seats_available data from show detail table");
			System.out.println(e.getMessage());
		}

		new_seats = get_seats + seats_bked;

		try {
			//update the showdetail table
			String new_tickets_query = "update a_hund_showdetail set Seats_available=? where movie_id=? and screen_id=? and show_Date=? and show_time=?";
			PreparedStatement pst_update_tickets = (PreparedStatement) DB_con.getConnection()
					.prepareStatement(new_tickets_query);
			pst_update_tickets.setInt(1, new_seats);
			pst_update_tickets.setInt(2, get_movie);
			pst_update_tickets.setInt(3, get_screen);
			pst_update_tickets.setString(4, movie_date);
			pst_update_tickets.setString(5, movie_time);
			pst_update_tickets.executeUpdate();
			Alert Cancel_ticket__alert = new Alert(AlertType.INFORMATION);
			Cancel_ticket__alert.setTitle("Cancelled Ticket");
			Cancel_ticket__alert.setHeaderText(null);
			Cancel_ticket__alert.setContentText("Your ticket has been cancelled");
			Cancel_ticket__alert.showAndWait();
			//System.out.println("New available seats in show detail");
		} catch (Exception e) {
			System.out.println("Error while updating data");
			System.out.println(e.getMessage());
		}
		try 
		{
			DB_con.conn1.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
