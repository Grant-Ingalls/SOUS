package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Parent root = 
					FXMLLoader.load(getClass().getResource("/application/Login.fxml"))
					;
			//BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		

		
			// Creating PreparedStatement object
			//PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
			// Setting values for Each Parameter
			//preparedStatement.setInt(1, 1);
			//preparedStatement.setString(2, "solmaz");
			//preparedStatement.setString(3, "solmaz@gmail.com");
			//preparedStatement.setString(4, "pass");
			//int row = preparedStatement.executeUpdate();
			// rows affected
			//System.out.println(row); // 1
			launch(args);
			//PreparedStatement deleteStm = connection.prepareStatement("DELETE FROM registration WHERE id = ?");
			// deleteStm.setString(1,"1"); 
	       // deleteStm.executeUpdate();
	
	
		
	}
}
