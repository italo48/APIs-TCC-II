package br.com.badcompany.sparkjavapetclinic.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private Connection c = null;

	public Connection conn() {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/petclinic", "postgres", "postgres");
			System.out.println("Open connection");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return c;
	}

	public void closeConn() {
		try {
			c.close();
			System.out.println("Close connection");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
