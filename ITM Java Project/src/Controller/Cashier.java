package Controller;
/*
 * set up all the cashier screens
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.mysql.jdbc.PreparedStatement;

import Model.Connector;
import Model.DaoModel_cash_admin;
import Model.daoModel;
import View.*;

public class Cashier {
	/*
	 * @FXML private TextField ticket_id;
	 * 
	 * @FXML private ChoiceBox<String> show_date;
	 * 
	 * @FXML private TextField seats_booked;
	 * 
	 * @FXML private TextField cust_name;
	 * 
	 * @FXML private ChoiceBox <String> screen_id;
	 * 
	 * @FXML private ChoiceBox<String> show_time;
	 * 
	 * @FXML private TextField cost;
	 * 
	 * @FXML private ComboBox movie_name_db ;
	 * 
	 * @FXML private javafx.scene.control.Button close_button;
	 * 
	 * private static ResultSet rs; //ObservableList<String> data =
	 * FXCollections.observableArrayList("Arn","vasukedi");
	 */
	Stage Show_book = new Stage();
	Stage Cancel_ticket_Cashier = new Stage();
	Stage Display_Ticket = new Stage();

	public void show_book_ticket() throws Exception {
		/*
		 * try { //ComboBox movie_name_db=new ComboBox(data);
		 * 
		 * Connector con=new Connector(); String
		 * query="Select movie_name from movie;"; PreparedStatement pst
		 * =(PreparedStatement) con.getConnection().prepareStatement(query); rs
		 * =pst.executeQuery(query); while(rs.next()) {
		 * 
		 * data.add(rs.getString("movie_name"));
		 * 
		 * }
		 * 
		 * System.out.println(data); movie_name_db = new ComboBox<String>();
		 * movie_name_db.getItems().setAll(data);
		 * 
		 * //movie_name_db.setItems(data);
		 * //movie_name_db.setItems(FXCollections.observableArrayList(data));
		 * //System.out.println(movie_name_db);
		 * 
		 * } catch (Exception e) { System.out.println(e.getMessage()); }
		 */

		Parent root = FXMLLoader.load(getClass().getResource("/View/Book_ticket.fxml"));
		Scene scene = new Scene(root, 475, 625);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Show_book.setScene(scene);
		Show_book.show();

	}

	/*
	 * public void exit(ActionEvent event) { Stage stage = (Stage)
	 * close_button.getScene().getWindow(); // do what you have to do
	 * stage.close(); }//end method
	 * 
	 * 
	 */
	public void display_cancel_view() {
		try {
			Parent cancel_tckt_root = FXMLLoader.load(getClass().getResource("/View/cancel_ticket_cashier.fxml"));
			Scene cancel_tckt_scene = new Scene(cancel_tckt_root, 530, 320);
			cancel_tckt_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Cancel_ticket_Cashier.setScene(cancel_tckt_scene);
			Cancel_ticket_Cashier.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void ah_display_ticket() throws Exception {
		Parent dis_ticket_root = FXMLLoader.load(getClass().getResource("/View/Display_All_Ticket2.fxml"));
		Scene dis_tckt_scene = new Scene(dis_ticket_root, 1050, 800);
		dis_tckt_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Display_Ticket.setScene(dis_tckt_scene);
		Display_Ticket.show();
		// System.out.println("tickets");
	}

}
