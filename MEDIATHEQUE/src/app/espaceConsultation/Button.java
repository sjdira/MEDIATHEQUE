package app.espaceConsultation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton implements MouseListener {
	private String event="";
	public Button(String name)
	{
		super(name);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.addMouseListener(this);
	}
	public void paintComponent(Graphics g)
	{
		if (event.equals("entered"))
		{
		g.setColor(new Color(211,201,201,120));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}

		super.paintComponent(g);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		this.event="entered";
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		this.event="exited";
	}

}
