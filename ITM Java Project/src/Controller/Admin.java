package Controller;

/*
 * Admin class has many functions like add movie, add screen, add shows, display shows, display movie, delete show, delete movie
 * and cancel ticket
 * Add movie will add new movies to the theater.
 * Add screen function will create new screens in the theater
 * Add shows will add shows for different dates and screen
 * We can delete shows, delete movie.
 * To get the details of the shows we call the display shows function
 * To get the details of the movies we call the display movies function
 * Admin can cancel the ticket
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.sql.ResultSet;
import com.mysql.jdbc.PreparedStatement;
import Model.Connector;
import Model.DaoModel_cash_admin;
import Model.daoModel;
import View.*;

public class Admin extends DaoModel_cash_admin {
	daoModel dao = new daoModel();
	// DaoModel_cash_admin dao_cash_admin =new DaoModel_cash_admin();
	Stage Add_movie = new Stage();
	Stage Add_screen = new Stage();
	Stage Add_show = new Stage();
	Stage Delete_show = new Stage();
	Stage Display_Movie = new Stage();
	Stage Display_Shows = new Stage();
	Stage Cancel_ticket_admin = new Stage();
	@FXML
	private TextField movie_id;

	@FXML
	private TextField movie_name;

	@FXML
	private TextField director_name;
	@FXML
	private javafx.scene.control.Button close_button;
	@FXML
	private TextField screen_id;
	@FXML
	private TextField total_seats;
	@FXML
	private TextField show_id;
	@FXML
	private TextField show_screen_id;
	@FXML
	private TextField show_movie_id;
	@FXML
	private TextField show_seats;
	@FXML
	private TextField show_time;
	@FXML
	private DatePicker show_date_id;
	@FXML
	private TextField delete_movie_id;
	@FXML
	private TextField delete_show_id;
	@FXML
	private TableView<Movie_getter_setter> List_Movie;
	@FXML
	private TableColumn<Movie_getter_setter, Integer> view_movie_id;
	@FXML
	private TableColumn<Movie_getter_setter, String> view_movie_name;
	@FXML
	private TableColumn<Movie_getter_setter, String> view_director;
	@FXML
	private TableView<Show_setter_getter> show_table;
	@FXML
	private TableColumn<Show_setter_getter, Integer> show_id_view;
	@FXML
	private TableColumn<Show_setter_getter, Integer> movie_id_view;
	@FXML
	private TableColumn<Show_setter_getter, Integer> screen_id_view;
	@FXML
	private TableColumn<Show_setter_getter, String> date_show_view;
	@FXML
	private TableColumn<Show_setter_getter, String> show_time_view;
	@FXML
	private TableColumn<Show_setter_getter, Integer> seats_available_view;

	@FXML
	private TextField ticket_id;

	ObservableList<String> movie = FXCollections.observableArrayList(); // creating observable list for displaying data in Movie combo box
	ObservableList<Movie_getter_setter> data_display_movie; //creating observable list for displaying Movie in table view by passing setter and getters class.
	ObservableList<Show_setter_getter> data_show_detail; // creating observable list for displaying shows in table view.

	public void Add_movie(ActionEvent event) throws Exception {
		// display the add movie screen
		Parent Movie_root = FXMLLoader.load(getClass().getResource("/View/Add_movie.fxml"));
		Scene movie_scene = new Scene(Movie_root, 800, 500);
		movie_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Add_movie.setScene(movie_scene);
		Add_movie.show();

		// System.out.println(movie_id.getText());
		// System.out.println(movie_name.getText());
		// System.out.println(director_name.getText());
	}// end method

	public void insert_movie() throws Exception {
		// inserting new movie in the database
		int ah_movie_id = 0;
		String insert_movie_name = "";
		String insert_director = "";
		try {
			ah_movie_id = Integer.parseInt(movie_id.getText());
			insert_movie_name = movie_name.getText();
			insert_director = director_name.getText();
			if (insert_movie_name.matches("[a-zA-Z ]+")) 
			{
				if (insert_director.matches("[a-zA-Z ]+")) 
				{
					// calling the insert_admin_movie function of daoModel class for inserting movie
					dao.insert_admin_movie(ah_movie_id, insert_movie_name, insert_director); 
				} else 
				{
					Alert mv_name_alert = new Alert(AlertType.INFORMATION);
					mv_name_alert.setTitle("Wrong Director Name");
					mv_name_alert.setHeaderText(null);
					mv_name_alert.setContentText("Please Enter only Text charatcers");
					mv_name_alert.showAndWait();
				}
			} else 
				{
				Alert mv_dir_alert = new Alert(AlertType.INFORMATION);
				mv_dir_alert.setTitle("Wrong Movie Name");
				mv_dir_alert.setHeaderText(null);
				mv_dir_alert.setContentText("Please Enter only Text characters");
				mv_dir_alert.showAndWait();
				}

		} catch (Exception e) {
			Alert insert_alert = new Alert(AlertType.INFORMATION);
			insert_alert.setTitle("Wrong Movie_ID");
			insert_alert.setHeaderText(null);
			insert_alert.setContentText("Please Enter only integer");
			insert_alert.showAndWait();
			// System.out.println("enter correct integer for movie id");
		}

		// System.out.println(movie_id.getText());
		// System.out.println(movie_name.getText());
		// System.out.println(director_name.getText());
	}// end method

	public void exit(ActionEvent event) {
		// closing the screen
		Stage exit_stage = (Stage) close_button.getScene().getWindow();
		exit_stage.close();
	}// end method

	public void Add_screen(ActionEvent event) throws Exception {
		// Display new screens in javafx for adding screen
		Parent screen_root = FXMLLoader.load(getClass().getResource("/View/Add_screen.fxml"));
		Scene screen_scene = new Scene(screen_root, 500, 360);
		screen_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Add_screen.setScene(screen_scene);
		Add_screen.show();

	}

	public void insert_screen() throws Exception {
		// insert new screen in database
		int seats = 0;
		int screen = 0;
		try {
			seats = Integer.parseInt(total_seats.getText());
			screen = Integer.parseInt(screen_id.getText());
			dao.insert_screen(screen, seats);
			
			}
			catch (Exception e) 
				{
					Alert insert_screen__alert = new Alert(AlertType.INFORMATION);
					insert_screen__alert.setTitle("Wrong Seats or Screen");
					insert_screen__alert.setHeaderText(null);
					insert_screen__alert.setContentText("Please Enter only integer in Seats and Screen");
					insert_screen__alert.showAndWait();
					// System.out.println("enter correct integer for movie id");
				}
		// System.out.println(seats);
		// System.out.println(screen);
		// call inser_screen in DaoModel class
	}// end method

	public void Add_show(ActionEvent event) throws Exception {
		// display add show screen in javafx to add shows
		Parent show_root = FXMLLoader.load(getClass().getResource("/View/Adding_Shows.fxml"));
		Scene show_scene = new Scene(show_root, 600, 513);
		show_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Add_show.setScene(show_scene);
		Add_show.show();

	}

	public void Remove_Movie(ActionEvent event) throws Exception {
		// display remove movie scene
		Parent rem_Movie_root = FXMLLoader.load(getClass().getResource("/View/Delete_Movie.fxml"));
		Scene rem_movie_scene = new Scene(rem_Movie_root, 500, 320);
		rem_movie_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Delete_show.setScene(rem_movie_scene);
		Delete_show.show();

	}

	public void Delete_movie() throws Exception {
		// delete movie from database
		int delete_movie = 0;
		try {
			delete_movie = Integer.parseInt(delete_movie_id.getText());
			dao.delete_movie(delete_movie);

		} catch (Exception e) {
			Alert delete_movie_alert = new Alert(AlertType.INFORMATION);
			delete_movie_alert.setTitle("Wrong Movie_ID entered");
			delete_movie_alert.setHeaderText(null);
			delete_movie_alert.setContentText("Please Enter only integer");
			delete_movie_alert.showAndWait();
			// System.out.println("Enter correct integer for movie id");
		}
		// call delete movie function of daoModel class
		// System.out.println(delete_movie);
	}

	public void Remove_Show(ActionEvent event) throws Exception {
		// display remove show scene in javafx
		Parent rem_show_root = FXMLLoader.load(getClass().getResource("/View/Delete_Show.fxml"));
		Scene rem_show_scene = new Scene(rem_show_root, 500, 292);
		rem_show_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Delete_show.setScene(rem_show_scene);
		Delete_show.show();

	}

	public void Delete_show() throws Exception {
		// delete show from showdetail table
		int delete_show = 0;
		try {
			delete_show = Integer.parseInt(delete_show_id.getText());
			dao.delete_show(delete_show);
		} catch (Exception e) {
			Alert delete_movie_alert = new Alert(AlertType.INFORMATION);
			delete_movie_alert.setTitle("Wrong Show_ID entered");
			delete_movie_alert.setHeaderText(null);
			delete_movie_alert.setContentText("Please Enter only integer");
			delete_movie_alert.showAndWait();
			// System.out.println("Enter correct integer for movie id");
		}

		// System.out.println(delete_show);
	}// end method

	public void ah_display_movie() throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/View/View_Movie.fxml"));
		Scene scene = new Scene(root, 600, 600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Display_Movie.setScene(scene);
		Display_Movie.show();
	}

	public void display_movie() throws Exception {
		data_display_movie = FXCollections.observableArrayList();
		ResultSet rs;
		Connector DB_con = new Connector();
		try {
			String display_movie_query = "Select movie_id,movie_name,director from a_hund_movie;";
			PreparedStatement pst = (PreparedStatement) DB_con.getConnection().prepareStatement(display_movie_query);
			rs = pst.executeQuery(display_movie_query);
			// pst.executeUpdate();
			System.out.println("Get Movie list");
			while (rs.next()) {
				Movie_getter_setter mgs = new Movie_getter_setter();
				mgs.setMovie_id(rs.getInt("movie_id"));
				mgs.setMovie_name(rs.getString("movie_name"));
				mgs.setDirector(rs.getString("director"));

				data_display_movie.add(mgs);
			}

			List_Movie.setItems(data_display_movie);
			view_movie_id.setCellValueFactory(new PropertyValueFactory<Movie_getter_setter, Integer>("movie_id"));
			view_movie_name.setCellValueFactory(new PropertyValueFactory<Movie_getter_setter, String>("movie_name"));
			view_director.setCellValueFactory(new PropertyValueFactory<Movie_getter_setter, String>("director"));
			// System.out.println("View Data");
			rs.close();
			DB_con.conn1.close();
		} catch (Exception e) {
			System.out.println("Error while inserting data");
			System.out.println(e.getMessage());
		}
	}// end method

	public void ah_display_show() throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/View/Show_list.fxml"));
			Scene scene = new Scene(root, 900, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Display_Shows.setScene(scene);
			Display_Shows.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void display_all_show() throws Exception {
		data_show_detail = FXCollections.observableArrayList();
		ResultSet rs;
		Connector DB_con = new Connector();
		try {
			String display_show_query = "Select show_id,screen_id,movie_id,show_Date,Seats_available,show_time from a_hund_showdetail;";
			PreparedStatement pst = (PreparedStatement) DB_con.getConnection().prepareStatement(display_show_query);
			rs = pst.executeQuery(display_show_query);
			// pst.executeUpdate();
			System.out.println("Displaying Show list");
			while (rs.next()) {
				Show_setter_getter ssg = new Show_setter_getter();
				ssg.setShow_id(rs.getInt("show_id"));
				ssg.setMovie_id(rs.getInt("movie_id"));
				ssg.setScreen_id(rs.getInt("screen_id"));
				ssg.setShow_Date(rs.getString("show_Date"));
				ssg.setAvailable_seats(rs.getInt("Seats_available"));
				ssg.setShow_time(rs.getString("show_time"));

				data_show_detail.add(ssg);
			}

			show_table.setItems(data_show_detail);
			show_id_view.setCellValueFactory(new PropertyValueFactory<Show_setter_getter, Integer>("show_id"));
			movie_id_view.setCellValueFactory(new PropertyValueFactory<Show_setter_getter, Integer>("movie_id"));
			screen_id_view.setCellValueFactory(new PropertyValueFactory<Show_setter_getter, Integer>("screen_id"));
			date_show_view.setCellValueFactory(new PropertyValueFactory<Show_setter_getter, String>("show_Date"));
			show_time_view.setCellValueFactory(new PropertyValueFactory<Show_setter_getter, String>("show_time"));
			seats_available_view
					.setCellValueFactory(new PropertyValueFactory<Show_setter_getter, Integer>("available_seats"));
			// System.out.println("View show Detail");
			rs.close();
			DB_con.conn1.close();
		} catch (Exception e) {
			System.out.println("Error while inserting data");
			System.out.println(e.getMessage());
		}
	}// end method

	public void display_cancel_ticket() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/View/CancelTicket_admin.fxml"));
			Scene scene = new Scene(root, 550, 320);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Cancel_ticket_admin.setScene(scene);
			Cancel_ticket_admin.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}// end method

	public void admin_cancel_ticket() {
		Admin current_admin = new Admin();
		int cancel_id;
		try {
			cancel_id = Integer.parseInt(ticket_id.getText());
			current_admin.delete_ticket(cancel_id);

		} catch (Exception e) {
			Alert admin_cncl_ticket_alert = new Alert(AlertType.INFORMATION);
			admin_cncl_ticket_alert.setTitle("Wrong Ticket entered");
			admin_cncl_ticket_alert.setHeaderText(null);
			admin_cncl_ticket_alert.setContentText("Please Enter only integer");
			admin_cncl_ticket_alert.showAndWait();
		}

	}
}// end class
