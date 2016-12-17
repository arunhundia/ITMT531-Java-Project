package Model;

/*
 * This is the DaoLogin class.
 * Main controller class will give username and password to Movie_login function.
 * This function will check if the user is valid or not.
 * We will also get role of user which the maincontroller class will be using to populate the screen based on that. 
 */
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

public class DaoLogin implements Dao_interface {
	Connector con = new Connector();
	public String login_role;
	private static ResultSet rs;

	public void Movie_login(String username, String password) {
		try {
			String query = "Select Role from a_hund_user where Username=? and user_password=?";
			PreparedStatement pst = (PreparedStatement) con.getConnection().prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			// Label lbl_status=new Label();
			rs = pst.executeQuery();
			int count = 0;
			try
			{
				while (rs.next()) 
				{
					login_role=rs.getString("Role");
					count = count + 1;
				}
				
			}
			catch(Exception e)
			{
				System.out.println("Invalid login");
			}
			
			rs.close();
			con.conn1.close();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}