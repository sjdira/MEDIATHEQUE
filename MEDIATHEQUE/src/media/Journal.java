package media;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import javax.swing.JOptionPane;


public class Journal extends Document {
	private String rayonnage;
	
	public Journal(String title,  String rayonnage, double prix) {
		super(title, prix);
		this.rayonnage = rayonnage;
	}
	
	public void addJournal() {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO journals (titre, dateAjout, dateJournal, rayonnage, prix) VALUES (?, ? ,? ,? ,?)");
			ps.setString(1, title); 
			ps.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			ps.setDate(3, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			ps.setString(4, rayonnage);
			ps.setDouble(5, prix);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "successfully inserted");
		} catch(SQLException e){
		}
	}
	
	public void modJournal(String colomnName,Object valeur,int idJ)  {
		try {
			switch(valeur.getClass().getSimpleName())
			{
				case "String":
					PreparedStatement ps = connection.prepareStatement("UPDATE journals SET "+ colomnName +" = \"" + (String)valeur +"\" where idJournal ="+ idJ);
					ps.executeUpdate();
					break;
				case "Integer":
					PreparedStatement ps2 = connection.prepareStatement("UPDATE journals SET "+ colomnName +" = "+ (Integer)valeur +" where idJournal ="+ idJ);
					ps2.executeUpdate();
					break;
				case "Double":
					PreparedStatement ps3 = connection.prepareStatement("UPDATE journals SET "+ colomnName +" = "+ (Double)valeur +" where idJournal ="+ idJ);
					ps3.executeUpdate();
					break;
			}
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Not Update");
		}
	}
}