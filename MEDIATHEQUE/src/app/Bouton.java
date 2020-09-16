package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseListener;

import javax.swing.JButton;


@SuppressWarnings("serial")
public class Bouton extends JButton
{
	private String type;
	public Bouton(String name)
	{
		super(name);
		this.setFont(new Font("Chilanka", Font.BOLD, 17));
		this.setBackground(Color.gray);
		this.setForeground(Color.WHITE);
	    this.setContentAreaFilled(false);
	    this.setFocusPainted(false);
	    this.setBorderPainted(false);
	    
	    this.addMouseListener(new MouseEve());
	    
	}
	public void paintComponent(Graphics g)
	{
		if(type=="pressed")
		{
		g.setColor(new Color(200,204,43));
		g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(),30,30);
		g.setColor(Color.BLACK);
		g.drawRoundRect(0, 0, this.getWidth(), this.getHeight(),30,30);
		}
		else if (type=="released")
		{
			g.setColor(Color.gray);
			g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(),30,30);
		}
		else
		{
			g.setColor(Color.gray);
			g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(),30,30);
		}
		
		super.paintComponent(g);
	}
	
	public void setCol(String type)
	{
		this.type=type;
	}
	
	public class MouseEve implements MouseListener
	{

		public void mouseClicked(java.awt.event.MouseEvent arg0) {
			
		}

		public void mouseEntered(java.awt.event.MouseEvent arg0) {
			Bouton.this.setCol("pressed");
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent arg0) {
			Bouton.this.setCol("released");
			
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent arg0) {
			// TODO Auto-generated method stub
			Bouton.this.setCol("pressed");
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent arg0) {
			// TODO Auto-generated method stub
			Bouton.this.setCol("released");
		}
		
	}
}
