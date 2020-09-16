package media;

import java.util.Calendar;

import javax.swing.JOptionPane;

import java.sql.*;


public class Livre extends Document {
	private String auteur;
	private String rayonnage;
	
	public Livre(String title,Double prix, String auteur, String rayonnage) {
		super(title,prix);
		this.auteur = auteur;
		this.rayonnage = rayonnage;
	}

	public void addLivre() {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO livres (titre, auteur, rayonnage, dateAjout, prix) VALUES (?, ? ,? ,? ,?)");
			ps.setString(1, title);
			ps.setString(2, auteur);
			ps.setString(3, rayonnage);
			ps.setDate(4, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			ps.setDouble(5, prix);
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "successfully inserted");
		}
		catch(Exception e){
		}
	}
	
	public void modLivre(String colomnName,Object valeur,int idL)  {
		try {
			switch(valeur.getClass().getSimpleName())
			{
			case "String":
				PreparedStatement ps = connection.prepareStatement("UPDATE livres SET "+ colomnName +" = \"" + (String)valeur +"\" where idlivre ="+ idL);
				ps.executeUpdate();
				break;
			case "Integer":
				PreparedStatement ps2 = connection.prepareStatement("UPDATE livres SET "+ colomnName +" = \"" + (Integer)valeur +"\" where idlivre ="+ idL);
				ps2.executeUpdate();
				break;
			case "Double":
				PreparedStatement ps3 = connection.prepareStatement("UPDATE livres SET "+ colomnName +" = \"" + (Double)valeur +"\" where idlivre ="+ idL);
				ps3.executeUpdate();
				break;

			}
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Not Update");
		}

		}

}