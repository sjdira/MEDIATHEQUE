package media;

import java.sql.*;
import java.util.Calendar;

import javax.swing.JOptionPane;

import app.Main;

public class Consultation 
{
	private int idConsultation;
	private Boolean cotisation;
	private int idLecteur;
	private Date dateConsultation = new java.sql.Date(Calendar.getInstance().getTime().getTime());;
	private int idDoc;
	private int idScreen;
	private String categorie;
	private Boolean dispo;
	private Connection connection = Main.getConnection();
	
	public Consultation(int codeLecteur, int idDoc, int idScreen)
	{
		this.idDoc = idDoc;
		this.idScreen = idScreen;
		
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
	
	public void addConsultation() {
		try {
			
			PreparedStatement ps = connection.prepareStatement("SELECT cotisation FROM users WHERE idUser = ?");
			ps.setInt(1, idLecteur);
			ResultSet rs = ps.executeQuery();
			rs.next();
			cotisation = rs.getBoolean(1);
			
			PreparedStatement ps2 = connection.prepareStatement("SELECT categorie, dispo FROM exemplaire WHERE idExemplaire = ?");
			ps2.setInt(1, idDoc);
			ResultSet rs2 = ps2.executeQuery();
			rs2.next();
			categorie = rs2.getString(1);
			dispo = rs2.getBoolean(2);
			
			if (cotisation && dispo && ( categorie.equalsIgnoreCase("CdRom") || categorie.equalsIgnoreCase("MicroFilm"))) {	
				//l ajout dans table consultation
				PreparedStatement ps3 = connection.prepareStatement("INSERT INTO consultations (idUser, idExemplaire, idEcran, dateConsultation) VALUES (?, ?, ?, ?)");
				ps3.setInt(1, idLecteur);
				ps3.setInt(2, idDoc);
				ps3.setInt(3, idScreen);
				ps3.setDate(4, dateConsultation);
				ps3.executeUpdate();
				
				PreparedStatement ps4 = connection.prepareStatement("UPDATE exemplaire SET dispo = FALSE WHERE idExemplaire = ?");
				ps4.setInt(1, idDoc);
				ps4.executeUpdate();
				
				PreparedStatement ps5 = connection.prepareStatement("SELECT MAX(idConsultation) FROM consultations WHERE idExemplaire = ? && idUser = ? && dateConsultation = ? && idEcran = ?");
				ps5.setInt(1, idDoc);
				ps5.setInt(2, idLecteur);
				ps5.setDate(3, dateConsultation);
				ps5.setInt(4, idScreen);
				ResultSet rs3 = ps5.executeQuery();
				rs3.next();
				idConsultation = rs3.getInt(1);
		
				if (categorie.equalsIgnoreCase("CdRom")) {            //Si le document est un Cd if faut ajouter la caution
					PreparedStatement ps6 = connection.prepareStatement("INSERT INTO cautions (idAction, type, caution) values (?,?,true)");
					ps6.setInt(1, idConsultation);
					ps6.setString(2, "consultation");
					ps6.executeUpdate();
				}
				
				PreparedStatement ps7 = connection.prepareStatement("UPDATE ecrans SET dispo = FALSE WHERE idEcran = ?");
				ps7.setInt(1, idScreen);
				ps7.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "successfully inserted");
			}
				
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Not saved");
		}
	}
	
	public void retourConsultation() {
		try {
			
			PreparedStatement ps5 = connection.prepareStatement("SELECT MAX(idConsultation), idEcran FROM consultations WHERE idExemplaire = ? && idUser = ? && dateConsultation = ?");
			ps5.setInt(1, idDoc);
			ps5.setInt(2, idLecteur);
			ps5.setDate(3, dateConsultation);
			ResultSet rs3 = ps5.executeQuery();
			rs3.next();
			idConsultation = rs3.getInt(1);
			idScreen = rs3.getInt(2);
			
			PreparedStatement ps1 = connection.prepareStatement("UPDATE exemplaire SET dispo = true WHERE idExemplaire = ?");
			ps1.setInt(1, idDoc);
			ps1.executeUpdate();
			
			PreparedStatement ps2 = connection.prepareStatement("SELECT categorie FROM exemplaire WHERE idExemplaire = ?");
			ps2.setInt(1, idDoc);
			ResultSet rs2 = ps2.executeQuery();
			rs2.next();
			String categorie = rs2.getString(1);
			
			if (categorie.equals("CdRom")) {            //Si le document est un Cd if faut ajouter la caution
				PreparedStatement ps6 = connection.prepareStatement("UPDATE cautions SET caution = false WHERE idAction = ? && TYPE = ?");
				ps6.setInt(1, idConsultation);
				ps6.setString(2, "consultation");
				ps6.executeUpdate();
			}

			PreparedStatement ps7 = connection.prepareStatement("UPDATE ecrans SET dispo = true WHERE idEcran = ?");
			ps7.setInt(1, idScreen);
			ps7.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "successfully update");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Not update");
		}
	}
}
