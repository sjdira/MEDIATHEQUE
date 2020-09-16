package media;

import app.Main;
import java.sql.*;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class Emprunt {
	private int idEmp;
	private int codeLecteur;
	private int idLecteur;
	private int idDocument;
	private Boolean cotisation;
	private int nbrEmpruntEnCours;
	private Boolean canTakeToHome;
	private String categorie;
	private Boolean dispo;
	private java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	private Connection connection = Main.getConnection();
	
	public Emprunt (int codeLecteur, int idDocument) {
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
	
	public void addEmprunt() {
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT cotisation FROM users WHERE idUser = ?");
			ps.setInt(1, idLecteur);
			ResultSet rs = ps.executeQuery();
			rs.next();
			cotisation = rs.getBoolean(1);
			
			PreparedStatement ps1 = connection.prepareStatement("SELECT count(*) FROM emprunts WHERE idUser = ? && dateRetour is NULL");
			ps1.setInt(1, idLecteur);
			ResultSet rs1 = ps1.executeQuery();
			rs1.next();
			nbrEmpruntEnCours = rs1.getInt(1);
			
			PreparedStatement ps2 = connection.prepareStatement("SELECT categorie, canTakeToHome, dispo FROM exemplaire WHERE idExemplaire = ?");
			ps2.setInt(1, idDocument);
			ResultSet rs2 = ps2.executeQuery();
			rs2.next();
			categorie = rs2.getString(1);
			canTakeToHome = rs2.getBoolean(2);
			dispo = rs2.getBoolean(3);
			
			if (cotisation && nbrEmpruntEnCours <= 5 && canTakeToHome && dispo && ( categorie.equalsIgnoreCase("CdRom") || categorie.equalsIgnoreCase("Livre"))) {	
				//l ajout dans table emprunt
				PreparedStatement ps3 = connection.prepareStatement("INSERT INTO emprunts (idUser, idExemplaire, dateEmp) VALUES (?, ?, ?)");
				ps3.setInt(1, idLecteur);
				ps3.setInt(2, idDocument);
				ps3.setDate(3, date);
				ps3.executeUpdate();
				
				PreparedStatement ps4 = connection.prepareStatement("UPDATE exemplaire SET dispo = FALSE WHERE idExemplaire = ?");
				ps4.setInt(1, idDocument);
				ps4.executeUpdate();
				
				PreparedStatement ps5 = connection.prepareStatement("SELECT idEmp FROM emprunts WHERE idExemplaire = ? && idUser = ? && dateEmp = ? && dateRetour IS NULL");
				ps5.setInt(1, idDocument);
				ps5.setInt(2, idLecteur);
				ps5.setDate(3, date);
				ResultSet rs3 = ps5.executeQuery();
				rs3.next();
				idEmp = rs3.getInt(1);
		
				if (categorie.equalsIgnoreCase("CdRom")) {            //Si le document est un Cd if faut ajouter la caution
					PreparedStatement ps6 = connection.prepareStatement("INSERT INTO cautions (idAction, type, caution) values (?,?,true)");
					ps6.setInt(1, idEmp);
					ps6.setString(2, "emprunt");
					ps6.executeUpdate();
				}
				ActualiserNbrEmprunt();	
				JOptionPane.showMessageDialog(null, "successfully inserted");
			}
				
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Not saved");
		}
	}
	
	public void retourEmprunt() {
		try {
			PreparedStatement ps5 = connection.prepareStatement("SELECT idEmp FROM emprunts WHERE idExemplaire = ? && idUser = ? && dateRetour IS NULL");
			ps5.setInt(1, idDocument);
			ps5.setInt(2, idLecteur);
			ResultSet rs3 = ps5.executeQuery();
			rs3.next();
			idEmp = rs3.getInt(1);
			
			PreparedStatement ps = connection.prepareStatement("UPDATE emprunts SET dateRetour = ? WHERE idExemplaire = ? && idUser = ? && DateRetour IS NULL");
			ps.setDate(1, date);
			ps.setInt(2, idDocument);
			ps.setInt(3, idLecteur);
			ps.executeUpdate();
			
			PreparedStatement ps1 = connection.prepareStatement("UPDATE exemplaire SET dispo = true WHERE idExemplaire = ?");
			ps1.setInt(1, idDocument);
			ps1.executeUpdate();
			
			PreparedStatement ps2 = connection.prepareStatement("SELECT categorie FROM exemplaire WHERE idExemplaire = ?");
			ps2.setInt(1, idDocument);
			ResultSet rs2 = ps2.executeQuery();
			rs2.next();
			String categorie = rs2.getString(1);
			
			if (categorie.equals("CdRom")) {            //Si le document est un Cd if faut ajouter la caution
				PreparedStatement ps6 = connection.prepareStatement("UPDATE cautions SET caution = false WHERE idAction = ? && TYPE = ?");
				ps6.setInt(1, idEmp);
				ps6.setString(2, "emprunt");
				ps6.executeUpdate();
			}
			
			JOptionPane.showMessageDialog(null, "successfully update");
			ActualiserNbrEmprunt();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Not update");
		}
	}
	
	private void ActualiserNbrEmprunt() {

	    try {
			PreparedStatement ps = connection.prepareStatement("UPDATE users SET nbrEmpEffectuer = ( SELECT count(*) FROM emprunts WHERE idUser = ?) WHERE idUser = ?");
			ps.setInt(1, idLecteur);
			ps.setInt(2, idLecteur);
			ps.executeUpdate();
			
			PreparedStatement ps2 = connection.prepareStatement("UPDATE users SET nbrEmpruntEnCours = ( SELECT count(*) FROM emprunts WHERE idUser = ? && dateRetour IS NULL) WHERE idUser = ?");
			ps2.setInt(1, idLecteur);
			ps2.setInt(2, idLecteur);
			ps2.executeUpdate();  
	    } catch (SQLException e) {
		}

	}
	
	public String genereFichePret() {
		try {
			if(categorie.equalsIgnoreCase("Journal") || categorie.equalsIgnoreCase("MicroFilm"))
			{
				return "Emprunt non enregistre car on\npeut enprunte que des livres et les Cd-Rom";
			}
			
			if(!cotisation)
			{
				return "Emprunt non enregistre car ce\nlecteur n'a pas regle sa cotisation";
			}
			
			if(nbrEmpruntEnCours > 5)
			{
				return "Emprunt non enregistre car ce\nlecteur a plus de 5 emprunts simultane";
			}
			
			if(!canTakeToHome)
			{
				return "Emprunt non enregistre car ce\ndocument est consultable uniquement sur place";
			}
			
			if(!dispo)
			{
				return "Emprunt non enregistre car ce\ndocument n'est pas disponible";
			}
			
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM emprunts WHERE idexemplaire = ? && idUser = ? && DateEmp = ? && DateRetour IS NULL");
			ps.setInt(1, idDocument);
			ps.setInt(2, idLecteur);
			ps.setDate(3, date);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return "Id Emprunt: " + rs.getInt(1) + "\n\nCode Lecteur: " + codeLecteur + "\n\nID lecteur: " + rs.getInt(2) + "\n\nà emprunté l'exemplaire: " + rs.getInt(3) + "\n\nLe: " + rs.getDate(4);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Not saved");
		}
		return null;
	}
}

