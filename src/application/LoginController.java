package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {
	@FXML
	private Label sID;
	@FXML
	private TextField unID;
	@FXML
	private TextField passID;
	
	//login button
	public void Login(ActionEvent event) throws Exception {
		//variables to hold database user data
		String username = "";
		String password = "";
		String userID = "";
		String ingID = "";
		
		String sql ="SELECT * FROM registration";
		try {
			// Establishing connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbconnector", "root",
			"cs380");
			System.out.println("Connected With the database successfully");
			Statement statement = con.createStatement();
	        ResultSet rs = statement.executeQuery(sql);
	        
	        //loop through all users data to check if user input matches
	        while (rs.next()) { 
	        userID = rs.getString(1);
	        username = rs.getString(3);
	        password = rs.getString(4);
	        ingID = rs.getString(5);
	        System.out.println(username);
	        System.out.println(password);
	        //if matches login success
	    	if (unID.getText().equals(username) && passID.getText().equals(password)) {
				sID.setText("Login Success");
				
				//for passing user data between scenes
				User u = new User(userID, ingID); 
					
				  Node node = (Node) event.getSource();
				  Stage stage = (Stage) node.getScene().getWindow();
				  stage.close();
				
				  //load main scene
				Stage primaryStage = new Stage();
				Parent root = 
					FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
				
				Scene scene = new Scene(root, 400, 400);
					scene.getStylesheets().add(getClass().getResource("application.css")
						.toExternalForm());
					primaryStage.setUserData(u);
					primaryStage.setScene(scene);
					primaryStage.show();
					
			
					
					break;
			//fail if not match
			} else {
				sID.setText("Login Failed");
			}
	    	
	        }
	       	    
			} catch (SQLException e) {
			System.out.println("Error while connecting to the database");
			}	
		

	}
}
