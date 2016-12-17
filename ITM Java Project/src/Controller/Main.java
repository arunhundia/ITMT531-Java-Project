package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import View.*;

/*
 * Main class will initiate the javafx application and willdisolay the first view which is thelogin page.
 * The main method will launch the application.
 * To view the login page I have used the FXMLLoader function which will load the view, for that we pass the location of view
 * The main class extends the application class of javafx 
 *  
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent Login_root = FXMLLoader.load(getClass().getResource("/View/login.fxml"));
			// BorderPane root = new BorderPane();
			Scene Login_scene = new Scene(Login_root, 650, 500);
			Login_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(Login_scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		 System.out.println("Current Date=" + timeStamp + "\nProgrammed by Arun Hundia\n");
	}
}
