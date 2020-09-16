package media;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import app.Main;


public class Lecteur extends Personne {
	private int nbrEmpruntEffectue =0;
	private int nbrEmpruntEnCours =0;
	private int codeLecteur ;
	private boolean cotisation ;
	private Date dateDajout ;
	private Connection conn=Main.getConnection();	
	
	public Lecteur(String nom ,String prenom ,String adresse,int codeLecteur,
						int phone,boolean b ) {
		this.nom=nom;this.prenom=prenom;this.adresse=adresse;
		this.phonNumber=phone;	this.cotisation=b;this.codeLecteur=codeLecteur;	
	}
	
	
	public boolean addLecteur () {
		boolean b=true;
		try {
			java.util.Date d1 = new java.util.Date();
			java.sql.Date d2 = new java.sql.Date(d1.getTime()); 
			
			PreparedStatement prepare = conn.prepareStatement(
					"INSERT INTO `users`( `codeLecteur`, `prenom`, `nom`,`adresse` ,"+
					"`telephone`, `nbrEmpEffectuer`, `nbrEmpruntEnCours`,`cotisation`,`dateAjout`) "+
					"VALUES ("+this.codeLecteur+",'"+this.prenom+"','"+this.nom+"','"+this.adresse+
					"',"+this.phonNumber+","+this.nbrEmpruntEffectue+
					","+this.nbrEmpruntEnCours+","+this.cotisation+","+"?"+")");
			prepare.setDate(1, d2);
			prepare.executeUpdate();
			ResultSet res=conn.createStatement().executeQuery(
					"SELECT idUser From users WHERE codeLecteur="+this.codeLecteur);
			res.next();
			setId(res.getInt(1));
		} catch ( Exception e) {
			b=false;
			JOptionPane.showMessageDialog(null,
					" peut être ce lecteur existe\n entrer un noveau codelecteur",
					"échec d'ajout ! ", JOptionPane.ERROR_MESSAGE);			
			}
		return b;
		}
	

	public Date getDateDajout() {
		return dateDajout;
	};	
	public boolean getCotisation() {
		return cotisation;
	};
}
