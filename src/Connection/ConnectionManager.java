package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionManager {
	static Connection con;
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost/e-jawi";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	
	public static Connection getConnection() {
		try {
				Class.forName(DB_DRIVER);
				try {
						con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
						System.out.println("Connected");
				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		return con;
	}
	
}
