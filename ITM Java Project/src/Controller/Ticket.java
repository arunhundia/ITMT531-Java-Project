package Controller;

import javafx.scene.control.TextField;

public class Ticket {
	private String show_date;
	private int seats_booked;
	private String cust_name;
	private int screen_id;
	private String show_time;
	private int cost;
	private int movie_id;
	private int ticket_id;

	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}

	public void setShow_date(String show_date) {
		this.show_date = show_date;
	}

	public void setSeats_booked(int seats_booked) {
		this.seats_booked = seats_booked;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public void setScreen_id(int screen_id) {
		this.screen_id = screen_id;
	}

	public void setShow_time(String show_time) {
		this.show_time = show_time;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getTicket_id() {
		return ticket_id;
	}

	public String getShow_date() {
		return show_date;
	}

	public int getSeats_booked() {
		return seats_booked;
	}

	public String getCust_name() {
		return cust_name;
	}

	public int getScreen_id() {
		return screen_id;
	}

	public String getShow_time() {
		return show_time;
	}

	public int getCost() {
		return cost;
	}

}
