package app;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
//
@SuppressWarnings("serial")
public class Conteneur extends JPanel
{
	private Image img1,img2,img3,img4,img5;
	private int posx =0 ,moved=0;
	
	private int i =0;
	public Conteneur(){
		try{
			img1=ImageIO.read(new File("assets/bgr6.png"));
			img2=ImageIO.read(new File("assets/bgr7.png"));
			img3=ImageIO.read(new File("assets/bgr3.png"));
			img4=ImageIO.read(new File("assets/bgr4.png"));
			img5=ImageIO.read(new File("assets/bgr5.png"));
		}	catch(IOException e){}
	}			
	
	public void paintComponent(Graphics g)
	{
		Graphics2D grp=(Graphics2D)g;
		grp.drawImage(img1, posx, 0, 900,500,this);
		grp.drawImage(img2, posx+900, 0,900,500, this);
		grp.drawImage(img3, posx+1800, 0, this);
		grp.drawImage(img4, posx+2700, 0, this);
		grp.drawImage(img5, posx+3600, 0, this);
	}
	public void moveLeft () {
		this.moved=1;this.i=0;
		new Move().start();
		
	}
	public void moveRight () {
		this.moved=-1;this.i=0;
		new Move().start();
	}
		
	class Move extends Thread{		
		private boolean start;

		public void run() {	
			start=false;
			while(i<900) {				
				posx+=moved;
				if(posx==1) {posx=-3600;start=true;}
				else if(posx==-3601) {posx=0;start=true;}
				Conteneur.this.repaint();
				if(start)break;
				i++;
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {}
			}
		}
	}

	public int getposx() {
		return this.posx;
	}
		
}
