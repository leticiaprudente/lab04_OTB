package main.java.lab04_garcia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
	 public static Connection getConnection() throws SQLException{
		 try {
			Class.forName ("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		 Connection con = DriverManager.getConnection(  
				 "jdbc:oracle:thin:@127.0.0.1:1521:fatec","system","fatec");
		 System.out.println("Connection Success");
		return con;  
	 }  
	 
	 
}
