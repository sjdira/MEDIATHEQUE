package media;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.Main;

public class Benevole extends Personne {
	private String pseudo ;
	private String password ;
	private Date	dateDubut;
	private Date	dateFin ;
	private Connection conn=Main.getConnection();
	
	public Benevole (String nom ,String prenom ,String adresse ,int phone,String pseudo ,String pasword) {
		this.nom=nom;this.prenom=prenom;this.adresse=adresse;
		this.phonNumber=phone;this.pseudo=pseudo;this.password= pasword;		
	}
	
	public void addBenevole (Date date ) {
		try {
			java.util.Date d1 = new java.util.Date();
			java.sql.Date d2 = new java.sql.Date(d1.getTime()); 
			this.dateDubut=d2;this.dateFin=date;
			PreparedStatement prepare = conn.prepareStatement("INSERT INTO `personnels`(`pseudo`, `password`, `nom`, `prenom`,"+
					"`adresse`, `telephone`, `role`, `dateDebut`,`dateFin`) "+
					"VALUES (`"+this.pseudo+"`,`"+this.password+"`,`"+this.nom+"`,`"+this.prenom+
					"`,`"+this.adresse+"`,"+this.phonNumber+",`benevole`,?,?)");
			prepare.setDate(1, d2);
			prepare.setDate(2, date);
			prepare.executeUpdate();
			ResultSet res=conn.createStatement().executeQuery(
					"SELECT idPersonnel From users WHERE pseudo="+this.pseudo);
			res.next();
			setId(res.getInt(1));
		} catch ( Exception e) {
			e.printStackTrace();
		}
	};
	public boolean verifyLogin(String pseudo,String password) {
		boolean b = false ;
		java.util.Date d1 = new java.util.Date();
		java.sql.Date date= new java.sql.Date(d1.getTime()); 
		try {
			Statement prepare =conn.createStatement();
			ResultSet res = prepare.executeQuery("SELECT password FROM personnel WHERE pseudo="+pseudo);
			res.next();
			if (res.getString(1).equals(password) && date.compareTo(this.dateFin)<0)b=true ;
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
	public Date getDateDubut() {
		return dateDubut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	
}
