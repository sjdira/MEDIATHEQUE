package app;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Xbouton extends JButton implements MouseListener{
	
	private String type; 
	
	public Xbouton(){		
		 this.setContentAreaFilled(false);
		 this.setFocusPainted(false);
	     this.setBorderPainted(false);
	     this.addMouseListener(this);
	     this.setIcon(new ImageIcon("assets/exiticon.png"));
		 this.setContentAreaFilled(false);
		 this.setFocusPainted(false);
		 this.setBounds(837, 0, 66, 58);
		 this.setBorderPainted(false);
	}

	public void paintComponent(Graphics g)
	{
		if(type=="pressed")
		{
		g.setColor(new Color(237,50,50));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		else if (type=="released")
		{
		    this.setContentAreaFilled(false);
		}
		else
		{
		    this.setContentAreaFilled(false);
		}
		//Caling mother constructor
		super.paintComponent(g);
	}
	
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		this.type="pressed";
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		this.type="released";
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		this.type="pressed";
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		this.type="released";
	}
	
}
