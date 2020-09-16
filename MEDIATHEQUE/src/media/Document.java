package media;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.Main;

public class Document {
	
	protected String title;
	protected double prix;
	
	public Connection connection=Main.getConnection();
	
	public Document(String title,double prix) {
		this.title = title;
		this.prix = prix;
	}
	
	public void addExemplaire(int idDoc, String categorie, boolean dispo, Boolean canTakeToHome) {
		// TODO Auto-generated method stub //
		ResultSet rs;
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO exemplaire (idDoc, categorie, dispo, canTakeToHome) values (?, ?, ? ,?)");
			ps.setInt(1, idDoc);
			ps.setString(2, categorie);
			ps.setBoolean(3, dispo);
			
			if(categorie.equalsIgnoreCase("Livre")) {
				ps.setBoolean(4, canTakeToHome);
				PreparedStatement ps1 = connection.prepareStatement("SELECT 1 FROM livres WHERE idLivre = ?");
				ps1.setInt(1, idDoc);
				rs = ps1.executeQuery();
				rs.next();
				if(rs.getInt(1) == 1) {
					ps.executeUpdate();
				}
			}
			
			if(categorie.equalsIgnoreCase("Journal")) {
				ps.setBoolean(4, false);
				PreparedStatement ps1 = connection.prepareStatement("SELECT 1 FROM journals WHERE idJournal = ?");
				ps1.setInt(1, idDoc);
				rs = ps1.executeQuery();
				rs.next();
				if(rs.getInt(1) == 1) {
					ps.executeUpdate();
				}
			}
			
			if(categorie.equalsIgnoreCase("CdRom")) {
				ps.setBoolean(4, true);
				PreparedStatement ps1 = connection.prepareStatement("SELECT 1 FROM cdroms WHERE idCdRom = ?");
				ps1.setInt(1, idDoc);
				rs = ps1.executeQuery();
				rs.next();
				if(rs.getInt(1) == 1) {
					ps.executeUpdate();
				}
			}
			
			if(categorie.equalsIgnoreCase("MicroFilm")) {
				ps.setBoolean(4, false);
				PreparedStatement ps1 = connection.prepareStatement("SELECT 1 FROM microfilms WHERE idMicroFilm = ?");
				ps1.setInt(1, idDoc);
				rs = ps1.executeQuery();
				rs.next();
				if(rs.getInt(1) == 1) {
					ps.executeUpdate();
				}
			}
			
		} catch (SQLException e) {
		}
	}
	
}