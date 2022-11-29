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
import java.sql.PreparedStatement;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainController {
	
	public void goPantry(ActionEvent event) throws Exception {
				
		  Node node = (Node) event.getSource();
		  Stage stage = (Stage) node.getScene().getWindow();
		  User u = (User) stage.getUserData();
		  String id = u.getID();
		  //System.out.println("this: " + id); 
		  //System.out.println("this: " + u.getIngID()); 
		  //stage.close();
		  
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Pantry.fxml"));
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setUserData(u);
		primaryStage.setScene(scene);
		primaryStage.show();
					
					
	
	}
	
	public void generate(ActionEvent event) throws Exception {
		
		//SELECT * FROM A
		//EXCEPT
		//SELECT * FROM B
		
		try {
			System.out.println("generate"); 
			 String cbSet = ""; 
			 String usSet = ""; 
			 
			  Node node = (Node) event.getSource();
			  Stage stage = (Stage) node.getScene().getWindow();
			  User u = (User) stage.getUserData();
			  String id = u.getID();
			  
			  String cb ="SELECT ingredient_list_id, recipe_desc FROM cookbook JOIN ingredients ON ingredient_list_id = ingredient_id";
			  String uIng ="SELECT * FROM ingredients WHERE ingredient_id = " + u.getIngID();
			// Establishing connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbconnector", "root",
			"6200");
			System.out.println("Connected With the database successfully");
			Statement statement1 = con.createStatement();
			Statement statement2 = con.createStatement();
	        ResultSet cookbk = statement1.executeQuery(cb);
	        ResultSet pantry = statement2.executeQuery(uIng);
	        
	    	usSet = String.valueOf(pantry.getInt(2)) + String.valueOf(pantry.getInt(3)) + String.valueOf(pantry.getInt(4)) +
        			String.valueOf(pantry.getInt(5)) + String.valueOf(pantry.getInt(6));
	    	
	        while(cookbk.next()) {
	        	
	        	cbSet = String.valueOf(cookbk.getInt(2)) + String.valueOf(cookbk.getInt(3)) + String.valueOf(cookbk.getInt(4)) +
	        			String.valueOf(cookbk.getInt(5)) + String.valueOf(cookbk.getInt(6));
	        	
	        	boolean valid = true; 
	        	
	        	for(int i = 0; i < cbSet.length(); i++) {
	        		
	        		if(cbSet.charAt(i) == 1) {
	        			if(usSet.charAt(i) != 1) {
	        				valid = false; 
	        				break; 
	        			}
	        		}
	        		
	           }
	        	 if(valid) {
	        		 System.out.print(cookbk.getInt(1));
	        	 }
	        }
	       
			
				  stage.close();
				
				Stage primaryStage = new Stage();
				Parent root = 
					FXMLLoader.load(getClass().getResource("/application/Recipes.fxml"));
				
				Scene scene = new Scene(root, 400, 400);
					scene.getStylesheets().add(getClass().getResource("application.css")
						.toExternalForm());
					primaryStage.setUserData(u);
					primaryStage.setScene(scene);
					primaryStage.show();
					
	    	
	        
	       	    
			} catch (SQLException e) {
			System.out.println("Error while connecting to the database");
			}	
	}
}
