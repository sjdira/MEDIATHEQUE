package media;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.Main;


public class Personnel extends Personne {
	private String pseudo;
	private String password;
	private String role;
	private Connection conn=Main.getConnection();
	
	public Personnel()
	{
		this.pseudo=null;
		this.password=null;
	}

	public boolean checkPersonnel () {
		boolean b =false;
		try {
			PreparedStatement prepare =conn.prepareStatement(
					"SELECT pseudo FROM personnel WHERE idPersonnel="+this.id);
			ResultSet res = prepare.executeQuery();
			if (res.getString(1).equals(this.pseudo))b=true ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b ;
	};
	public boolean verifyLogin(String pseudo,String password) {
		boolean b = false ;
		 
		try {
			Statement prepare =conn.createStatement();
			ResultSet res = prepare.executeQuery("SELECT * FROM personnels WHERE pseudo="+"'"+pseudo+"';");
			if(!res.next())return false;
			if (res.getString("password").equals(password) )
				{
				this.nom=res.getString("nom");this.prenom=res.getString("prenom");this.adresse=res.getString("adresse");
				this.phonNumber=res.getInt("telephone");this.pseudo=res.getString("pseudo");this.password=res.getString("password");	
				this.role=res.getString("role");	
				b=true ;
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	public String getPseudo() {
		return pseudo;
	}
	public String getPassword() {
		return password;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getRole()
	{
		return this.role;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
