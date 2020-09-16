package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Main {
	
		private	static String driver = "com.mysql.cj.jdbc.Driver";
		private	static String url = "jdbc:mysql://localhost:3306/Mediatheque";     //Il faut changer le port s'il y a une erreur de connexion
		private	static String user = "root";
		private	static String pwd = "";
		private static Connection conn;
		
		static  
		{
			try {
			    Class.forName(driver);
			    conn = DriverManager.getConnection(url, user, pwd);
			    conn.getMetaData();
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);	
			}
	
		}
		
		public static void main(String[] args) throws IOException  
		{
			new FenetrePrincipale();
		}
		
		public static Connection getConnection()
		{
			return conn;
		}
	
}
