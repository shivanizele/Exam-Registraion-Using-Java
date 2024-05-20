package gpp;

import java.sql.*;

class Myconnection {
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");
			return con;
		} catch (Exception e) {

		}
		return null;
	}
	public static Connection main(String args[]) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");
			return con;
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery("select * from login");

//			while (rs.next())
//
//				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
//			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}