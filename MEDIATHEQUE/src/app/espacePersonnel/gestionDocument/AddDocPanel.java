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
public class AddDocPanel extends JPanel {

	private AddExemButton btn_addExemplaire = new AddExemButton("");
	private AddBookButton btn_addLivre = new AddBookButton("");
	private AddNewsButton btn_addJournal = new AddNewsButton("");
	private AddCdRomButton btn_addCdRom = new AddCdRomButton("");
	private AddMicroFilmButton btn_AddMicroFilm = new AddMicroFilmButton("");
	private CardLayout joker=new CardLayout();
	private String panels[]= {"AddExemp","AddBooks","AddNews","AddCdRom","AddMicroFilm"};
	
	JPanel panel=new JPanel();
	private AddExempPanel addExempPanel=new AddExempPanel();
	private AddJournalPanel addJournalPanel=new AddJournalPanel();
	private AddMicroFilmPanel addMicroFilmPanel=new AddMicroFilmPanel();
	private AddLivrePanel addLivrePanel=new AddLivrePanel();
	private AddCdRomPanel addCdRomPanel=new AddCdRomPanel();
	
	public AddDocPanel()
	{
		this.setOpaque(false);
	    this.setPreferredSize(new Dimension(900, 322));
	    this.setLayout(null);
	    
	    
	    JLabel backGround = new JLabel("");
	    backGround.setIcon(new ImageIcon("assets/AddDocPanel.png"));
	    backGround.setBounds(0, 0, 900, 322);
	    
	    
	    
	    
	    btn_addExemplaire.setBorder(null);
	    btn_addExemplaire.setOpaque(false);
	    btn_addExemplaire.setBounds(53, 92, 147, 36);
	   
	    
	    
	    btn_addLivre.setOpaque(false);
	    btn_addLivre.setBorder(null);
	    btn_addLivre.setBounds(53, 129, 147, 36);
	    
	    
	    
	    btn_addJournal.setOpaque(false);
	    btn_addJournal.setBorder(null);
	    btn_addJournal.setBounds(53, 166, 147, 28);
	    
	    
	    
	    btn_addCdRom.setOpaque(false);
	    btn_addCdRom.setBorder(null);
	    btn_addCdRom.setBounds(53, 194, 147, 31);
	    
	    
	    
	    btn_AddMicroFilm.setOpaque(false);
	    btn_AddMicroFilm.setBorder(null);
	    btn_AddMicroFilm.setBounds(53, 226, 147, 32);
	    
	 
	    
	    panel.setOpaque(false);
	    panel.setBounds(201, 0, 699, 322);
	    panel.setLayout(joker);
	    panel.add(addExempPanel,"AddExemp");
	    panel.add(addJournalPanel,"AddNews");
	    panel.add(addMicroFilmPanel,"AddMicroFilm");
	    panel.add(addCdRomPanel,"AddCdRom");
	    panel.add(addLivrePanel,"AddBooks");
	    
	    
	    
	    /***************Button Lisntener*******************************/
	    
	    btn_addExemplaire.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				AddDocPanel.this.joker.show(AddDocPanel.this.panel, panels[0]);
				
				btn_addJournal.setPushed(false);
				btn_AddMicroFilm.setPushed(false);
				btn_addCdRom.setPushed(false);
				btn_addLivre.setPushed(false);
				
				btn_addJournal.repaint();
				btn_AddMicroFilm.repaint();
				btn_addCdRom.repaint();
				btn_addLivre.repaint();
			}
	    	
	    });
	    
	    
	    
	    
	    btn_addLivre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				AddDocPanel.this.joker.show(AddDocPanel.this.panel, panels[1]);
				
				btn_addJournal.setPushed(false);
				btn_AddMicroFilm.setPushed(false);
				btn_addCdRom.setPushed(false);
				btn_addExemplaire.setPushed(false);
				
				btn_addJournal.repaint();
				btn_AddMicroFilm.repaint();
				btn_addCdRom.repaint();
				btn_addExemplaire.repaint();
			}
	    	
	    });
	    
	    
	    
	    
	    
	    btn_AddMicroFilm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				AddDocPanel.this.joker.show(AddDocPanel.this.panel, panels[4]);
				
				btn_addJournal.setPushed(false);
				btn_addLivre.setPushed(false);
				btn_addCdRom.setPushed(false);
				btn_addExemplaire.setPushed(false);
				
				btn_addJournal.repaint();
				btn_addLivre.repaint();
				btn_addCdRom.repaint();
				btn_addExemplaire.repaint();
			}
	    	
	    });
	    
	    
	    
	    
	    btn_addCdRom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				AddDocPanel.this.joker.show(AddDocPanel.this.panel, panels[3]);
				
				btn_addJournal.setPushed(false);
				btn_addLivre.setPushed(false);
				btn_AddMicroFilm.setPushed(false);
				btn_addExemplaire.setPushed(false);
				
				btn_addJournal.repaint();
				btn_addLivre.repaint();
				btn_AddMicroFilm.repaint();
				btn_addExemplaire.repaint();
			}
	    	
	    });
	    
	    
	    
	    
	    
	    
	    btn_addJournal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				AddDocPanel.this.joker.show(AddDocPanel.this.panel, panels[2]);
				
				btn_addCdRom.setPushed(false);
				btn_addLivre.setPushed(false);
				btn_AddMicroFilm.setPushed(false);
				btn_addExemplaire.setPushed(false);
				
				btn_addCdRom.repaint();
				btn_addLivre.repaint();
				btn_AddMicroFilm.repaint();
				btn_addExemplaire.repaint();
			}
	    	
	    });
	    
	    
	    this.add(panel);
	    this.add(btn_addExemplaire);
	    this.add(btn_addLivre);
	    this.add(btn_addJournal);
	    this.add(btn_addCdRom);
	    this.add(btn_AddMicroFilm);
	    this.add(backGround);
	}
	
	
	
	
	
	public class AddBookButton extends JButton implements MouseListener
	{
		private String event="exited";
		private Boolean pushed=false;
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
		private Boolean pushed=true;
		
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
