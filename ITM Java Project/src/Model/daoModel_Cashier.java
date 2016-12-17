package Model;

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

public class daoModel_Cashier {
	Connector DB_con = new Connector();
	private Statement ah_statement = null;
	private static ResultSet result;
	int tickets_available = 0;
	int new_seats = 0;

	public void insert_ticket(int movie_id, int screen_id, String cust_name, String show_date, int seats, int cost,
			String show_time) throws Exception {
		ResultSet rs;
		try {
			String tickets_query = "select Seats_available from a_hund_showdetail where movie_id=? and screen_id=? and show_Date=? and show_Time=?;";
			PreparedStatement pst_tickets = (PreparedStatement) DB_con.getConnection().prepareStatement(tickets_query);
			pst_tickets.setInt(1, movie_id);
			pst_tickets.setInt(2, screen_id);
			pst_tickets.setString(3, show_date);
			pst_tickets.setString(4, show_time);
			rs = pst_tickets.executeQuery();
			if (rs.next()) {
				tickets_available = rs.getInt("Seats_available");
			}

			// System.out.println("Tickets available in show detail
			// "+tickets_available);
		} catch (Exception e) {
			System.out.println("Error while retrieving seats_available data from show detail table");
			System.out.println(e.getMessage());
		}
		DB_con.conn1.close();

		if (seats <= tickets_available) {
			try {

				String query = "insert into a_hund_ticket (Movie_id,Screen_id,Cust_name,Show_Date,Seats_booked,cost,Show_Time)"
						+ " values(" + movie_id + "," + screen_id + ",'" + cust_name + "','" + show_date + "'," + seats
						+ "," + cost + ",'" + show_time + "');";
				PreparedStatement pst = (PreparedStatement) DB_con.getConnection().prepareStatement(query);
				pst.executeUpdate(query);
				// System.out.println("Ticket created");
			} catch (Exception e) {
				System.out.println("Error while inserting data");
				System.out.println(e.getMessage());
			}
			DB_con.conn1.close();
			new_seats = tickets_available - seats;
			try {

				String new_tickets_query = "update a_hund_showdetail set Seats_available=" + new_seats + " where movie_id="
						+ movie_id + " and screen_id=" + screen_id + " and show_Date='" + show_date
						+ "' and show_time='" + show_time + "';";
				PreparedStatement pst_update_tickets = (PreparedStatement) DB_con.getConnection()
						.prepareStatement(new_tickets_query);
				pst_update_tickets.executeUpdate(new_tickets_query);

				// System.out.println("New available seats in show detail");
				// System.out.println("DB "+new_seats);
				// System.out.println("deleteing"+seats);
				Alert insert_ticket_alert = new Alert(AlertType.INFORMATION);
				insert_ticket_alert.setTitle("Ticket Booked");
				insert_ticket_alert.setHeaderText(null);
				insert_ticket_alert.setContentText("Congratulation Your Ticket has been booked. Enjoy!!!!!");
				insert_ticket_alert.showAndWait();
			} catch (Exception e) {
				System.out.println("Error while updating data");
				System.out.println(e.getMessage());
			}
			DB_con.conn1.close();
		} else {
			if (tickets_available == 0) {
				Alert housefll_alert = new Alert(AlertType.INFORMATION);
				housefll_alert.setTitle("HouseFull");
				housefll_alert.setHeaderText(null);
				housefll_alert.setContentText("There are no seats available");
				housefll_alert.showAndWait();
			} else {
				Alert remainng_seats_alert = new Alert(AlertType.INFORMATION);
				remainng_seats_alert.setTitle("Remaining Seats");
				remainng_seats_alert.setHeaderText(null);
				remainng_seats_alert.setContentText("There are only " + tickets_available + " tickets available");
				remainng_seats_alert.showAndWait();
			}
		}
	}
}
