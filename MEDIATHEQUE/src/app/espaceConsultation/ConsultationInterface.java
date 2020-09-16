package app.espaceConsultation;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ConsultationInterface extends JFrame
{
	private JPanel Container=new JPanel();
	private CardLayout joker=new CardLayout();
	private String stat="";
	
	private String panels[]= {"Books","News","MicroFilm","CdRoom"};
	JPanel panel = new JPanel();
	
	
	private CdRomPanel cdRomPanel=new CdRomPanel();
	private LivrePanel livrePanel=new LivrePanel();
	private JournalPanel journalPanel=new JournalPanel();
	private MicroFilmPanel microFilmPanel=new MicroFilmPanel();
	
	public ConsultationInterface() throws IOException
	{
		ImageIcon i = new ImageIcon("assets/URead.png");
		this.setIconImage(i.getImage());
	this.setTitle("Espace Consultation");
    this.setUndecorated(true);
	this.setSize(897,492);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.addMouseMotionListener(new DragCont());
    this.setContentPane(Container);
    Container.setOpaque(true);
    Container.setLayout(null);
    
    JLabel backGroundImage = new JLabel("New label");
    backGroundImage.setIcon(new ImageIcon("assets/EspaceConsPanel.png"));
    backGroundImage.setBounds(0, 0, 897, 492);
    
    
    BookButton btnBooks = new BookButton("");
    btnBooks.setBounds(149, 133, 125, 44);
    Container.add(btnBooks);
    
    NewsButton btnNews = new NewsButton("");
    btnNews.setBounds(275, 133, 120, 44);
    Container.add(btnNews);
    
    MicroFilmButton btnMicrofilm = new MicroFilmButton("");
    btnMicrofilm.setBounds(529, 133, 120, 44);
    Container.add(btnMicrofilm);
    
    CdRomButton btnCdrom = new CdRomButton("");
    btnCdrom.setBounds(649, 133, 127, 44);
    Container.add(btnCdrom);
    
    ExitButton retourButton = new ExitButton("");
    retourButton.setBounds(504, 101, 18, 23);
    Container.add(retourButton);
    
    CloseButton exitButton = new CloseButton("");
    exitButton.setBounds(841, 11, 46, 35);
    Container.add(exitButton);
    
    
    StatLabel status = new StatLabel("");
    status.setBounds(110, 108, 18, 18);
    Container.add(status);
    
    
    panel.setLayout(joker);
    panel.setOpaque(false);
    panel.setBounds(0, 177, 897, 313);
    panel.add(livrePanel,panels[0]);
    panel.add(journalPanel,panels[1]);
    panel.add(microFilmPanel,panels[2]);
    panel.add(cdRomPanel,panels[3]);


    
    
    /****Action listener for buttons **********/
       btnBooks.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			ConsultationInterface.this.joker.show(ConsultationInterface.this.panel, panels[0]);
			btnNews.setPushed(false);
			btnMicrofilm.setPushed(false);
			btnCdrom.setPushed(false);
			btnCdrom.repaint();
			btnNews.repaint();
			btnMicrofilm.repaint();
		}
    	
    });
    
    btnNews.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			ConsultationInterface.this.joker.show(ConsultationInterface.this.panel, panels[1]);
			btnBooks.setPushed(false);
			btnMicrofilm.setPushed(false);
			btnCdrom.setPushed(false);
			btnBooks.repaint();
			btnMicrofilm.repaint();
			btnMicrofilm.repaint();
		}
    	
    });
    
    btnMicrofilm.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			ConsultationInterface.this.joker.show(ConsultationInterface.this.panel, panels[2]);
			btnBooks.setPushed(false);
			btnNews.setPushed(false);
			btnCdrom.setPushed(false);
			btnBooks.repaint();
			btnNews.repaint();
			btnCdrom.repaint();
		}
    	
    });
    
    btnCdrom.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			ConsultationInterface.this.joker.show(ConsultationInterface.this.panel, panels[3]);
			btnBooks.setPushed(false);
			btnNews.setPushed(false);
			btnMicrofilm.setPushed(false);
			btnBooks.repaint();
			btnNews.repaint();
			btnMicrofilm.repaint();
		}
    	
    });
    
    

    
   
    

    Container.add(panel);
    Container.add(backGroundImage);
    this.setVisible(true);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public class DragCont implements MouseMotionListener
	{

		int xx=0,yy=0;
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			 	//super.mouseDragged(e
			int x=e.getXOnScreen();
			int y=e.getYOnScreen();
			 ConsultationInterface.this.setLocation(x-xx,y-yy);
	
		      
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			 xx=e.getX();
			 yy=e.getY();
		}
		
	}
	
	
	
	
	
	
	public class BookButton extends JButton implements MouseListener
	{
		private String event="exited";
		private Boolean pushed=true;
		public BookButton(String name)
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
	public class NewsButton extends JButton implements MouseListener
	{
		private String event="exited";
		private Boolean pushed=false;
		
		public NewsButton(String name)
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
	public class MicroFilmButton extends JButton implements MouseListener
	{
		private String event="exited";
		private Boolean pushed=false;
		
		public MicroFilmButton(String name)
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
	public class CdRomButton extends JButton implements MouseListener
	{
		private String event="exited";
		private Boolean pushed=false;
		
		public CdRomButton(String name)
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
	public class ExitButton extends JButton implements MouseListener,ActionListener
	{
		private String event="";
		public ExitButton(String name)
		{
			super(name);
		    this.setContentAreaFilled(false);
		    this.setFocusPainted(false);
		    this.setBorderPainted(false);
		    this.addActionListener(this);
		    this.addMouseListener(this);
		}
		public void paintComponent(Graphics g)
		{
			if (event.equals("entered"))
			{
			g.setColor(new Color(211,201,201,120));
			g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(),10,10);
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
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			ConsultationInterface.this.stat="exited";

			ConsultationInterface.this.dispose();
		}
		
	}
	public class CloseButton extends JButton implements MouseListener,ActionListener
	{
		private String event="";
		public CloseButton(String name)
		{
			super(name);
		    this.setContentAreaFilled(false);
		    this.setFocusPainted(false);
		    this.setBorderPainted(false);
		    this.addActionListener(this);
		    this.addMouseListener(this);
		}
		public void paintComponent(Graphics g)
		{
			if (event.equals("entered"))
			{
			g.setColor(new Color(211,201,201,120));
			g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(),10,10);
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
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			ConsultationInterface.this.stat="closed";
			ConsultationInterface.this.dispose();
			
		}
		
	}
	public class StatLabel extends JLabel
	{
		Image img,img1;
		public StatLabel(String name)
		{
			super(name);
			try {
				img=ImageIO.read(new File("assets/redstat.png"));
				img1=ImageIO.read(new File("assets/greenStat.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    public void paintComponent(Graphics g)
	    {
	    	if (app.Main.getConnection()!=null)
	    	{
	    		g.drawImage(img1, 0, 0, this);
	    	}	
	    	else
	    	{
	    		g.drawImage(img, 0, 0, this);
	    	}
	    	super.paintComponent(g);
	    }
	}
	public String getStat()
	{
		return this.stat;
	}
}

