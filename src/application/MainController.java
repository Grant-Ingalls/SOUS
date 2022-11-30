package application;

import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;

import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;

//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainController {

	public void goPantry(ActionEvent event) throws Exception {

		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		User u = (User) stage.getUserData();

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Pantry.fxml"));
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setUserData(u);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public void adminConsole(ActionEvent event) throws Exception {

		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		User u = (User) stage.getUserData();

		System.out.println(u.getAdmin());

		Stage primaryStage = new Stage();
		System.out.println("Test");
		Parent root = FXMLLoader.load(getClass().getResource("/application/Admin.fxml"));
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setUserData(u);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	// generate valid recipes
	public void generate(ActionEvent event) throws Exception {

		try {
			// System.out.println("generate");
			// for row comparison
			ArrayList<Integer> cbSet = new ArrayList<Integer>();
			ArrayList<Integer> usSet = new ArrayList<Integer>();

			List<String> recipe = new ArrayList<String>();

			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			User u = (User) stage.getUserData();
			// String id = u.getID();

			// String cb ="SELECT ingredient_list_id FROM cookbook JOIN ingredients ON
			// ingredient_list_id = ingredient_id";
			String cb = "SELECT * FROM ingredients WHERE ingredient_id > 5"; // hc
			String uIng = "SELECT * FROM ingredients WHERE ingredient_id = " + u.getIngID();
			// Establishing connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbconnector", "root", "cs380");
			System.out.println("Connected With the database successfully");
			Statement statement1 = con.createStatement();
			Statement statement2 = con.createStatement();
			ResultSet cookbk = statement1.executeQuery(cb);
			ResultSet pantry = statement2.executeQuery(uIng);

			// create list for panstry data
			// awful practice
			while (pantry.next()) {
				usSet.add(pantry.getInt(2));
				usSet.add(pantry.getInt(3));
				usSet.add(pantry.getInt(4));
				usSet.add(pantry.getInt(5));
				usSet.add(pantry.getInt(6));

			}

			/*
			 * for(int i = 0; i < usSet.size(); i++) { System.out.print(usSet.get(i)); }
			 * System.out.println();
			 */

			// create list for cookbook data
			while (cookbk.next()) {
				cbSet.add(cookbk.getInt(2));
				cbSet.add(cookbk.getInt(3));
				cbSet.add(cookbk.getInt(4));
				cbSet.add(cookbk.getInt(5));
				cbSet.add(cookbk.getInt(6));

				/*S
				 * for(int i = 0; i < cbSet.size(); i++) { System.out.print(cbSet.get(i)); }
				 * System.out.println();
				 */
				// check to see if recipe list is a sublist of pantry list
				boolean valid = true;

				for (int i = 0; i < cbSet.size(); i++) {

					if (cbSet.get(i) == 1) {
						if (usSet.get(i) != 1) {
							valid = false;
							break;
						}
					}

				}
				if (valid) {
					// get names of valid recipes based on their ingredient id
					Statement statement3 = con.createStatement();
					ResultSet recip = statement3
							.executeQuery("SELECT * FROM cookbook WHERE ingredient_list_id = " + cookbk.getInt(1));
					while (recip.next()) {
						recipe.add(recip.getString(2));
					}
				}
				cbSet.clear();
			}

			// stage.close();

			Stage stage2 = new Stage();

			// create window to display valid recipes
			VBox root = new VBox(5);

			TextArea text = new TextArea();
			text.setPrefRowCount(10);
			text.setPrefColumnCount(27);
			text.setWrapText(true);

			root.getChildren().addAll(text);
			Scene scene = new Scene(root);
			stage2.setScene(scene);
			stage2.setTitle("Recipes");

			// if there were no valid recipes
			if (recipe.size() == 0) {
				recipe.add("No possible recipes.");
			}

			for (int i = 0; i < recipe.size(); i++) {
				text.appendText(recipe.get(i) + "\n");
			}
			stage2.show();

		} catch (SQLException e) {
			System.out.println("Error while connecting to the database");
		}
	}
}
