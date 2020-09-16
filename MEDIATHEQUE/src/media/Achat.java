package media;

import app.Main;
import java.sql.*;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class Achat {
	private java.sql.Date dateAchat = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	private int codeLecteur;
	private int idDocument;
	private int idLecteur;
	private Connection connection = Main.getConnection();
	
	Boolean cotisation;
	Boolean dispo;
	
	public Achat(int codeLecteur, int idDocument) {
		this.codeLecteur = codeLecteur;
		this.idDocument = idDocument;
		
		try {
			PreparedStatement psID = connection.prepareStatement("SELECT idUser FROM users WHERE codeLecteur = ?");
			psID.setInt(1, codeLecteur);
			ResultSet rsID = psID.executeQuery();
			rsID.next();
			idLecteur = rsID.getInt(1);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Entrer un Code Lecteur existe");
		}
		
	}
	
	public void buyDoc() {
		try {		
			
			PreparedStatement ps = connection.prepareStatement("SELECT cotisation FROM users WHERE idUSer = ?");
			ps.setInt(1, idLecteur);
			ResultSet rs = ps.executeQuery();
			rs.next();
			cotisation = rs.getBoolean(1);
			
			PreparedStatement psD = connection.prepareStatement("SELECT dispo FROM exemplaire WHERE idExemplaire = ?");
			psD.setInt(1, idDocument);
			ResultSet rs2 = psD.executeQuery();
			rs2.next();
			dispo = rs2.getBoolean(1);
			
			if (cotisation && dispo) {
				
				//l'ajout dans table achat
				PreparedStatement ps1 = connection.prepareStatement("INSERT INTO achats (idUser, idExemplaire, dateAchat) VALUES (?, ?, ?)");
				ps1.setInt(1, idLecteur);
				ps1.setInt(2, idDocument);
				ps1.setDate(3, dateAchat);
				ps1.executeUpdate();
				
				//changer la disponibilite du document dans la table exemplaire
				PreparedStatement ps2 = connection.prepareStatement("UPDATE exemplaire SET dispo = FALSE WHERE idExemplaire = ?");
				ps2.setInt(1, idDocument);
				ps2.executeUpdate();
				JOptionPane.showMessageDialog(null, "successfully inserted");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Not saved");
		}
	}
	
	public String genereFicheAchat() {
		try {
			
			if(!cotisation)
			{
				return "Achat non enregistre car ce\nlecteur n'a pas regle sa cotisation";
			}
			
			if(!dispo)
			{
				return "Achat non enregistre car ce\ndocument n'est pas disponible";
			}
			
			PreparedStatement ps4 = connection.prepareStatement("SELECT * FROM achats WHERE idexemplaire = ? && idUser = ?");
			ps4.setInt(1, idDocument);
			ps4.setInt(2, idLecteur);
			ResultSet rs2 = ps4.executeQuery();
			rs2.next();
			return "Id Achat: " + rs2.getInt(1) + "\n\nCode Lecteur: " + codeLecteur + "\n\nId lecteur: " + rs2.getInt(2) + "\n\nà acheté l'exemplaire: " + rs2.getInt(3) + "\n\nLe: " + rs2.getDate(4);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Not saved");
		}
		return null;
	}
}
