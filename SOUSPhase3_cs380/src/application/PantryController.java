package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class PantryController {
	
	@FXML
	private CheckBox gbChk;
	@FXML
	private CheckBox bpChk;
	@FXML
	private CheckBox chChk;
	@FXML
	private CheckBox spagChk;
	@FXML
	private CheckBox psChk;
	
	//to save pantry to database 
	public void savePantry(ActionEvent event) throws Exception {
		  int bp = 0;
		  int gb = 0;
		  int spag = 0;
		  int sauce = 0; 
		  int ch = 0;
	
		  Node node = (Node) event.getSource();
		  Stage stage = (Stage) node.getScene().getWindow();
		  User u = (User) stage.getUserData();
		  System.out.println("this: " + u.getID()); 
		  System.out.println("this: " + u.getIngID()); 
		
		  try {
		  
		  // Establishing connection 
			  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbconnector",
		  "root", "6200");
		  System.out.println("Connected With the database successfully");
		  
		 
		  //values for Each Parameter
		  if(bpChk.isSelected()) {
			  bp = 1;
		  }
			  
		  if(gbChk.isSelected()) {
		  	  gb = 1;
		  }
		
		  if(spagChk.isSelected()) {
			  spag = 1;
		  
		  }
		  if(psChk.isSelected()) {
			  sauce = 1;
		  
		  }
		  if(chChk.isSelected()) {
			  ch = 1; 
		  
		  }
		  
		  String sqlS ="UPDATE ingredients "  + 
					"SET bell_pepper = " + bp + ", ground_beef = " + gb + 
					", spaghetti_noodles = " + spag + ", sauce = " + sauce + 
					", cheese = " + ch +
					" WHERE ingredient_id = " + u.getIngID();
		  
		   PreparedStatement preparedStatement = con.prepareStatement(sqlS); // Setting
		 
		  
		  int row = preparedStatement.executeUpdate(); System.out.println(row); } catch
		  (SQLException e) {
		  System.out.println("Error while connecting to the database"); }
		 
	
	}
	
}


