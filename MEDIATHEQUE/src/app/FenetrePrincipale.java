package app;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.espaceConsultation.ConsultationInterface;

@SuppressWarnings("serial")
public class FenetrePrincipale extends JFrame{
	
	private Conteneur container=new Conteneur();
	public FenetrePrincipale() {
		
		ImageIcon i = new ImageIcon("assets/URead.png");
		this.setIconImage(i.getImage());
		
		this.setTitle("URead");
	    this.setUndecorated(true);
		this.setSize(900, 500);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setContentPane(container);
	    this.addMouseMotionListener(new DragCont());
	    container.setLayout(null);
	    
	    JPanel panel = new JPanel();
	    panel.setBackground(new Color(216,188,169,140));
	    panel.setBounds(-3, 0, 908, 60);
	    container.add(panel);
	    panel.setLayout(null);
	    
	    
	    JLabel lblNewLabel = new JLabel();
	    lblNewLabel.setIcon(new ImageIcon("assets/logo.png"));
	    lblNewLabel.setBounds(-16, -10, 179, 96);
	    panel.add(lblNewLabel);
	    
	    Xbouton btnNewButton_1 = new Xbouton();	    
	    panel.add(btnNewButton_1);	   
	    btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FenetrePrincipale.this.dispose();
			}
		});
	    
	    JButton btnNewButton = new JButton();
	    btnNewButton.setIcon(new ImageIcon("assets/leftarr.png"));
	    btnNewButton.setBounds(29, 253, 87, 58);
	    btnNewButton.setContentAreaFilled(false);
	    btnNewButton.setFocusPainted(false);
	    btnNewButton.setBorderPainted(false);
	    btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(container.getposx()%900==0)container.moveLeft();
			}
		});
	    container.add(btnNewButton);
	    
	    
	    JButton button = new JButton("");
	    button.setIcon(new ImageIcon("assets/rightarrow.png"));
	    button.setFocusPainted(false);
	    button.setContentAreaFilled(false);
	    button.setBorderPainted(false);
	    button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(container.getposx()%900==0)container.moveRight();
				
			}
		});
	    button.setBounds(788, 252, 87, 58);	    
	    container.add(button);
	    
	    Bouton btnNewButton_2 = new Bouton("Espace Personnel");
	    btnNewButton_2.setBounds(160, 326, 235, 50);	

	    btnNewButton_2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
				FenetrePrincipale.this.setVisible(false);	
				Thread thread=new Thread(new Runnable() {

					@Override
					public void run() {
						LoginInterface  t = new LoginInterface();
						while(t.isDisplayable());
						if (t.getStat().equals("closed"))
						{
							FenetrePrincipale.this.dispose();					
						}
						else
							FenetrePrincipale.this.setVisible(true);
					}});	
				thread.start();
				
	    	}
	    });
	    container.add(btnNewButton_2);
	    
	    Bouton button_1 = new Bouton("Espace de Consultation");
	    button_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
				FenetrePrincipale.this.setVisible(false);	
				Thread thread=new Thread(new Runnable() {

					@Override
					public void run() {
						ConsultationInterface  t=null;
						// TODO Auto-generated method stub
						try {
							t = new ConsultationInterface();
							while(t.isDisplayable());
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (t.getStat().equals("closed"))
						{
							FenetrePrincipale.this.dispose();					
						}
							else
							FenetrePrincipale.this.setVisible(true);
					}});	
				thread.start();
				
	    	}
	    });
	    button_1.setBounds(538, 326, 235, 50);
	    container.add(button_1);
	  
	    
	    
	    this.setVisible(true);
	}
	
	public class DragCont implements MouseMotionListener
	{

		int xx=0,yy=0;
		public void mouseDragged(MouseEvent e) {
			
			int x=e.getXOnScreen();
			int y=e.getYOnScreen();
			FenetrePrincipale.this.setLocation(x-xx,y-yy);
			 		      
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			 xx=e.getX();
			 yy=e.getY();
		}
		
	}
}

