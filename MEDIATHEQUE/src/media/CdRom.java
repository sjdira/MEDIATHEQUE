package media;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class CdRom extends Document{
	private String auteur;
	private int guichet;
	
	public CdRom(String title,Double prix, String auteur, int guichet) {
		super(title, prix);
		this.auteur = auteur;
		this.guichet = guichet;
	}
	
	public void addCdrom() {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO cdroms (titre, auteur, dateAjout, guichet, prix) VALUES (?, ? ,? ,? ,?)");
			ps.setString(1, title);
			ps.setString(2, auteur);
			ps.setDate(3, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			ps.setInt(4,guichet);
			ps.setDouble(5, prix);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "successfully inserted");
		} catch(SQLException e){
		}
	}
	
	public void modCdrom(String colomnName,Object valeur,int idC)  {
		try {
			switch(valeur.getClass().getSimpleName())
			{
				case "String":
					PreparedStatement ps = connection.prepareStatement("UPDATE cdroms SET "+ colomnName +" = \"" + (String)valeur +"\" where idcdrom ="+ idC);
					ps.executeUpdate();
					break;
				case "Integer":
					PreparedStatement ps2 = connection.prepareStatement("UPDATE cdroms SET "+ colomnName +" = \"" + (Integer)valeur +"\" where idcdrom ="+ idC);
					ps2.executeUpdate();
					break;
				case "Double":
					PreparedStatement ps3 = connection.prepareStatement("UPDATE cdroms SET "+ colomnName +" = \"" + (Double)valeur +"\" where idcdrom ="+ idC);
					ps3.executeUpdate();
					break;
			}
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Not Update");
		}

	}
}