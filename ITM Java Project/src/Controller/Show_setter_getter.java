package Controller;

public class Show_setter_getter {

	int show_id;
	int screen_id;
	int movie_id;
	String show_Date;
	int available_seats;
	String show_time;

	public int getShow_id() {
		return show_id;
	}

	public int getScreen_id() {
		return screen_id;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public String getShow_Date() {
		return show_Date;
	}

	public int getAvailable_seats() {
		return available_seats;
	}

	public String getShow_time() {
		return show_time;
	}

	public void setShow_id(int show_id) {
		this.show_id = show_id;
	}

	public void setScreen_id(int screen_id) {
		this.screen_id = screen_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public void setShow_Date(String show_Date) {
		this.show_Date = show_Date;
	}

	public void setAvailable_seats(int available_seats) {
		this.available_seats = available_seats;
	}

	public void setShow_time(String show_time) {
		this.show_time = show_time;
	}
}
