package app.espacePersonnel.gestionDocument;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ModDocPanel extends JPanel {


	private AddBookButton btn_modLivre = new AddBookButton("");
	private AddNewsButton btn_modJournal = new AddNewsButton("");
	private AddCdRomButton btn_modCdRom = new AddCdRomButton("");
	private AddMicroFilmButton btn_modMicroFilm = new AddMicroFilmButton("");
	private CardLayout joker=new CardLayout();
	private String panels[]= {"ModBooks","ModNews","ModCdRom","ModMicroFilm"};
	
	JPanel panel=new JPanel();

	private ModJournalPanel modJournalPanel=new ModJournalPanel();
	private ModMicroFilmPanel modMicroFilmPanel=new ModMicroFilmPanel();
	private ModLivrePanel modLivrePanel=new ModLivrePanel();
	private ModCdRomPanel modCdRomPanel=new ModCdRomPanel();
	
	
	public ModDocPanel()
	{   
	this.setOpaque(false);
    this.setPreferredSize(new Dimension(900,22));
    this.setLayout(null);
    
    JLabel backGround = new JLabel("");
    backGround.setIcon(new ImageIcon("assets/ModDocPanel.png"));
    backGround.setBounds(0, 0, 900, 322);
    

    btn_modLivre.setOpaque(false);
    btn_modLivre.setBorder(null);
    btn_modLivre.setBounds(38, 111, 147, 36);
    
    

    btn_modJournal.setOpaque(false);
    btn_modJournal.setBorder(null);
    btn_modJournal.setBounds(38, 148, 147, 28);
    
    

    btn_modCdRom.setOpaque(false);
    btn_modCdRom.setBorder(null);
    btn_modCdRom.setBounds(38, 177, 147, 31);
    
    
    btn_modMicroFilm.setOpaque(false);
    btn_modMicroFilm.setBorder(null);
    btn_modMicroFilm.setBounds(38, 208, 147, 30);
    
    

    
    panel.setOpaque(false);
    panel.setBounds(201, 0, 699, 322);
    panel.setLayout(joker);
    panel.add(modLivrePanel,"ModBooks");
    panel.add(modJournalPanel,"ModNews");
    panel.add(modMicroFilmPanel,"ModMicroFilm");
    panel.add(modCdRomPanel,"ModCdRom");
    
    
    
    
    
    
    /*********************Buttons Listener****************************/
    
    btn_modLivre.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			ModDocPanel.this.joker.show(ModDocPanel.this.panel, panels[0]);
			
			btn_modCdRom.setPushed(false);
			btn_modJournal.setPushed(false);
			btn_modMicroFilm.setPushed(false);

			
			btn_modCdRom.repaint();
			btn_modJournal.repaint();
			btn_modMicroFilm.repaint();
		}
    	
    });
    
    
    
    
    
    btn_modMicroFilm.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			ModDocPanel.this.joker.show(ModDocPanel.this.panel, panels[3]);
			
			btn_modCdRom.setPushed(false);
			btn_modLivre.setPushed(false);
			btn_modJournal.setPushed(false);

			
			btn_modCdRom.repaint();
			btn_modLivre.repaint();
			btn_modJournal.repaint();
		}
    	
    });
    
    
    
    
    btn_modCdRom.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			ModDocPanel.this.joker.show(ModDocPanel.this.panel, panels[2]);
			
			btn_modJournal.setPushed(false);
			btn_modLivre.setPushed(false);
			btn_modMicroFilm.setPushed(false);

			
			btn_modJournal.repaint();
			btn_modLivre.repaint();
			btn_modMicroFilm.repaint();
		}
    	
    });
    
    
    
    
    
    
    btn_modJournal.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			ModDocPanel.this.joker.show(ModDocPanel.this.panel, panels[1]);
			
			btn_modCdRom.setPushed(false);
			btn_modLivre.setPushed(false);
			btn_modMicroFilm.setPushed(false);

			
			btn_modCdRom.repaint();
			btn_modLivre.repaint();
			btn_modMicroFilm.repaint();

		}
    	
    });
    
    
    
    this.add(btn_modLivre);
    this.add(btn_modJournal);
    this.add(btn_modCdRom);
    this.add(btn_modMicroFilm);
    this.add(panel);
    this.add(backGround);
	}
	
	
	public class AddBookButton extends JButton implements MouseListener
	{
		private String event="exited";
		private Boolean pushed=true;
		public AddBookButton(String name)
		{
			super(name);
		    this.setContentAreaFilled(false);
		    this.setFocusPainted(false);
		    this.setBorderPainted(false);
		    this.addMouseListener(this);
		}

		public void paintComponent(Graphics g)
		{
			if (event.equals("entered") || pushed)
			{
				g.setColor(new Color(198,96,28,120));
				g.fillRect(0,0,this.getWidth(),this.getHeight());
			}
			else if (event.equals("exited") && !pushed)
				g.setColor(Color.black);
			super.paintComponent(g);
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			pushed=true;
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
		public void setPushed(Boolean pushed)
		{
			this.pushed=pushed;
		}
	}
	public class AddNewsButton extends JButton implements MouseListener
	{
		private String event="exited";
		private Boolean pushed=false;
		
		public AddNewsButton(String name)
		{
			super(name);
		    this.setContentAreaFilled(false);
		    this.setFocusPainted(false);
		    this.setBorderPainted(false);
		    this.addMouseListener(this);
		}
		public void paintComponent(Graphics g)
		{
			if (event.equals("entered") || pushed)
			{
				g.setColor(new Color(198,96,28,120));
				g.fillRect(0,0,this.getWidth(),this.getHeight());
			}
			else if (event.equals("exited") && !pushed)
				g.setColor(Color.black);
			super.paintComponent(g);
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			pushed=true;
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
		public void setPushed(Boolean pushed)
		{
			this.pushed=pushed;
		}
	}
	public class AddMicroFilmButton extends JButton implements MouseListener
	{
		private String event="exited";
		private Boolean pushed=false;
		
		public AddMicroFilmButton(String name)
		{
			super(name);
		    this.setContentAreaFilled(false);
		    this.setFocusPainted(false);
		    this.setBorderPainted(false);
		    this.addMouseListener(this);
		}
		public void paintComponent(Graphics g)
		{
			if (event.equals("entered") || pushed)
			{
				g.setColor(new Color(198,96,28,120));
				g.fillRect(0,0,this.getWidth(),this.getHeight());
			}
			else if (event.equals("exited") && !pushed)
				g.setColor(Color.black);
			super.paintComponent(g);
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			pushed=true;
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
		public void setPushed(Boolean pushed)
		{
			this.pushed=pushed;
		}
	}
	public class AddCdRomButton extends JButton implements MouseListener
	{
		private String event="exited";
		private Boolean pushed=false;
		
		public AddCdRomButton(String name)
		{
			super(name);
		    this.setContentAreaFilled(false);
		    this.setFocusPainted(false);
		    this.setBorderPainted(false);
		    this.addMouseListener(this);	
		}
		public void paintComponent(Graphics g)
		{
			if (event.equals("entered") || pushed)
			{
				g.setColor(new Color(198,96,28,120));
				g.fillRect(0,0,this.getWidth(),this.getHeight());
			}
			else if (event.equals("exited") && !pushed)
				g.setColor(Color.black);
			super.paintComponent(g);
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			pushed=true;
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
		public void setPushed(Boolean pushed)
		{
			this.pushed=pushed;
		}
	}
	public class AddExemButton extends JButton implements MouseListener
	{
		private String event="exited";
		private Boolean pushed=false;
		
		public AddExemButton(String name)
		{
			super(name);
		    this.setContentAreaFilled(false);
		    this.setFocusPainted(false);
		    this.setBorderPainted(false);
		    this.addMouseListener(this);	
		}
		public void paintComponent(Graphics g)
		{
			if (event.equals("entered") || pushed)
			{
				g.setColor(new Color(198,96,28,120));
				g.fillRect(0,0,this.getWidth(),this.getHeight());
			}
			else if (event.equals("exited") && !pushed)
				g.setColor(Color.black);
			super.paintComponent(g);
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			pushed=true;
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
		public void setPushed(Boolean pushed)
		{
			this.pushed=pushed;
		}
	}
}


