package Controller;

/*
 * This is the login class.
 * Here the text entered in the login page is stored in String username and String password format
 * and a object of DaoLogin class which is in Model package is created.
 * A parameterized function Movie_login is called of Dalogin class which contains the username and password.
 * Once the Movie_login function is executed it will also get the role of the user.
 * This role will be used in to execute the next screen in javafx.
 * IF the role selected is admin then admin screen will be executed.
 * IF the role selected is cashier then cashier screen will be executed.  
 * If the user entrs wrong credentials then an alert is displayed.
 */

import Model.DaoLogin;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import View.*;

public class maincontroller {

	@FXML
	private TextField txtusername;

	@FXML
	private TextField txt_password;

	// private Statement ah_statement=null;
	DaoLogin daologin = new DaoLogin();
	String Role;
	String username;
	String password;

	public void ah_Login(ActionEvent event) throws Exception {
		// verify username and password from database

		// lbl_status.setText("Admin");
		// based on the role call that particular java file
		daologin.Movie_login(txtusername.getText(), txt_password.getText());
		if(daologin.login_role != null)
		{
				Role = daologin.login_role;
				if (Role.charAt(0) == 'A') 
				{
					// System.out.println("You are an admin");
					Stage AdminStage = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("/View/Admin.fxml"));
					Scene scene = new Scene(root, 500, 500);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					AdminStage.setScene(scene);
					AdminStage.show();
					// lbl_status.setText("Admin");
				} else if (Role.charAt(0) == 'C') 
				
				{
					// System.out.println("You are a Cashier");
					Stage Cashier = new Stage();
					// Parent root
					// =FXMLLoader.load(getClass().getResource("/View/timepass.fxml"));
					Parent root = FXMLLoader.load(getClass().getResource("/View/Cashier.fxml"));
					Scene scene = new Scene(root, 525, 235);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					Cashier.setScene(scene);
					Cashier.show();
					// lbl_status.setText("User");
				}
		
				else {
					Alert login_alert = new Alert(AlertType.INFORMATION);
					login_alert.setTitle("Wrong Credentials");
					login_alert.setHeaderText(null);
					login_alert.setContentText("Please Enter correct username and Password");
					login_alert.showAndWait();
				} //end of if

		}
		else {
			Alert login_alert = new Alert(AlertType.INFORMATION);
			login_alert.setTitle("Wrong Credentials");
			login_alert.setHeaderText(null);
			login_alert.setContentText("Please Enter correct username and Password");
			login_alert.showAndWait();
		} //end of if

	}

}
