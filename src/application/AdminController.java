package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javafx.event.ActionEvent;

public class AdminController {

	public void getData(ActionEvent event) throws Exception {

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbconnector", "root", "cs380");
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM registration");
		ResultSetMetaData rsmd = rs.getMetaData();

		int columnsNumber = rsmd.getColumnCount();

		// print column names
		for (int i = 1; i <= columnsNumber; i++) {
			System.out.println(rsmd.getColumnName(i));
		}

		// Iterate through the data in the result set and display it.

		while (rs.next()) {
			// Print one row
			for (int i = 1; i <= columnsNumber; i++) {

				System.out.print(rs.getString(i) + " "); // Print one element of a row

			}

			System.out.println();// Move to the next line to print the next row.

		}

		// for spacing
		System.out.println();

		rs = st.executeQuery("SELECT * FROM ingredients");
		rsmd = rs.getMetaData();

		columnsNumber = rsmd.getColumnCount();
		// print column names
		for (int i = 1; i <= columnsNumber; i++) {
			System.out.println(rsmd.getColumnName(i));
		}

		// Iterate through the data in the result set and display it.

		while (rs.next()) {
			// Print one row
			for (int i = 1; i <= columnsNumber; i++) {

				System.out.print(rs.getString(i) + " "); // Print one element of a row

			}

			System.out.println();// Move to the next line to print the next row.

		}

		// for spacing
		System.out.println();

		rs = st.executeQuery("SELECT * FROM cookbook");
		rsmd = rs.getMetaData();

		columnsNumber = rsmd.getColumnCount();

		// print column names
		for (int i = 1; i <= columnsNumber; i++) {
			System.out.println(rsmd.getColumnName(i));
		}

		// Iterate through the data in the result set and display it.

		while (rs.next()) {
			// Print one row
			for (int i = 1; i <= columnsNumber; i++) {

				System.out.print(rs.getString(i) + " "); // Print one element of a row

			}

			System.out.println();// Move to the next line to print the next row.

		}
	}

}
