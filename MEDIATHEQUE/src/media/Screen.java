package media;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import app.Main;

public class Screen
{
	private int idScreen;
	private boolean disp;
	private Connection conn=Main.getConnection();
	
	Screen(int id,boolean disp)
	{
		this.idScreen=id;
		this.disp=disp;
	}
	void setDispo(boolean disp)
	{
		this.disp=disp;
	}
	boolean getDisp()
	{
		return this.disp;
	}
	void updatedisp()
	{
		try
		{
			Statement state=conn.createStatement();
			state.executeUpdate("update SCREEN set dispo=0 where idEcran="+this.idScreen);
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	int getIdScreen()
	{
		return this.idScreen;
	}
}
