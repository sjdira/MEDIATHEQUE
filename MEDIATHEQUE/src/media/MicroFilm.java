package media;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class MicroFilm extends Document{
	private int guichet;
	
	public MicroFilm(String title,Double prix, int guichet) {
		super(title,prix);
		this.guichet = guichet;
	}
	
	public void addMicroFilm( ) 
	{
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO microfilms (titre, dateAjout, guichet, prix) VALUES (?, ? ,? ,?)");
			ps.setString(1, title); 
			ps.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			ps.setInt(3, guichet);
			ps.setDouble(4, prix);
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "successfully inserted");
		} catch(SQLException e){
		}
	}
	
	public void modMicroFilm(String colomnName,Object valeur,int idM)  {
		try {
			switch(valeur.getClass().getSimpleName())
			{
				case "String":
					PreparedStatement ps = connection.prepareStatement("UPDATE microfilms SET "+ colomnName +" = \"" + (String)valeur +"\" where idmicrofilm ="+ idM);
					ps.executeUpdate();
					break;
				case "Integer":
					PreparedStatement ps2 = connection.prepareStatement("UPDATE microfilms SET "+ colomnName +" = \"" + (Integer)valeur +"\" where idmicrofilm ="+ idM);
					ps2.executeUpdate();
					break;
				case "Double":
					PreparedStatement ps3 = connection.prepareStatement("UPDATE microfilms SET "+ colomnName +" = \"" + (Double)valeur +"\" where idmicrofilm ="+ idM);
					ps3.executeUpdate();
					break;
			}
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Not Update");
		}
	}

}