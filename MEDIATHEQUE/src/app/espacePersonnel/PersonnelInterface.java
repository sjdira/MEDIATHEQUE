package app.espacePersonnel;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.espaceConsultation.CdRomPanel;
import app.espaceConsultation.JournalPanel;
import app.espaceConsultation.LivrePanel;
import app.espaceConsultation.MicroFilmPanel;
import app.espacePersonnel.gestionAchatEmprunt.AddAchatPanel;
import app.espacePersonnel.gestionAchatEmprunt.AddConsPanel;
import app.espacePersonnel.gestionAchatEmprunt.AddEmpPanel;
import app.espacePersonnel.gestionAchatEmprunt.StatisticsEmpruntPanel;
import app.espacePersonnel.gestionDocument.AddDocPanel;
import app.espacePersonnel.gestionDocument.ModDocPanel;
import app.espacePersonnel.gestionLecteurs.AddLectPanel;
import app.espacePersonnel.gestionLecteurs.ModLecPanel;
import media.Personnel;


@SuppressWarnings("serial")
public class PersonnelInterface extends JFrame
{
	private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private Date date = new Date();
	private String State="";
	Calendar time= Calendar.getInstance();
	private JPanel Container=new JPanel();
	private BookButton btnBooks = new BookButton("");
	private NewsButton btnNews = new NewsButton("");
	private MicroFilmButton btnMicroFilm = new MicroFilmButton("");
	private CdRomButton btnCdRom = new CdRomButton("");
	private AddReaderButton btn_addreader = new AddReaderButton("");
	private ModReaderButton btn_modreader = new ModReaderButton("");
	private AddDocButton btn_adddoc = new AddDocButton("");
	private ScreensStatusButton btn_screenstatus = new ScreensStatusButton("");
	private StatisticsButton btn_statistics = new StatisticsButton("");
	private AddLoaningButton btn_addloaning = new AddLoaningButton("");
	private ModDocButton btn_moddoc = new ModDocButton("");
	private AddBuyerButton btn_addbuyer = new AddBuyerButton("");
	private AddConsButton add_Cons = new AddConsButton("");
	
	
	
	private CardLayout joker=new CardLayout();
	private String[] panels= {"AddLec","ModLec","AddDoc","ModDoc","AddEmp","AddAchat","ScreenStatus","ConsBooks"
			,"ConsNews","ConsCdRom","ConsMicroFilm","AddCons","Statistics"};
	
	private JPanel holder=new JPanel();
	
	private AddAchatPanel addAchatPanel=new AddAchatPanel();
	private AddEmpPanel addEmpPanel=new AddEmpPanel();
	private AddLectPanel addLecPanel=new AddLectPanel();
	private ModLecPanel modLecPanel=new ModLecPanel();
	private AddDocPanel addDocPanel=new AddDocPanel();
	private ModDocPanel modDocPanel=new ModDocPanel();
	private ScreenStatus screenStatus=new ScreenStatus();
	private AddConsPanel addConsPanel=new AddConsPanel();
	private StatisticsEmpruntPanel statisticsPanel=new StatisticsEmpruntPanel();
	/*********Cons Panels***************************/
	private CdRomPanel cdRomPanel=new CdRomPanel();
	private LivrePanel livrePanel=new LivrePanel();
	private JournalPanel journalPanel=new JournalPanel();
	private MicroFilmPanel microFilmPanel=new MicroFilmPanel();
	
	
	
	
	
	
	
	
	
	
	public PersonnelInterface(Personnel perso) throws IOException
	{
		ImageIcon i = new ImageIcon("assets/URead.png");
		this.setIconImage(i.getImage());
	this.setUndecorated(true);
	this.setSize(900,500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setTitle("Espace Personnel");
    this.addMouseMotionListener(new DragCont());
    this.setContentPane(Container);
    Container.setOpaque(true);
    Container.setLayout(null);
    
    JLabel BackGround = new JLabel("New label");
    BackGround.setIcon(new ImageIcon("assets/EspacePersonnel.png"));
    BackGround.setBounds(0, 0, 900, 500);
    
    
    
    btnBooks.setBounds(151, 132, 125, 46);
    Container.add(btnBooks);
    
    
    btnNews.setBounds(277, 132, 119, 46);
    Container.add(btnNews);
    
    
    btnMicroFilm.setBounds(531, 132, 119, 46);
    Container.add(btnMicroFilm);
    
    
    btnCdRom.setBounds(650, 132, 127, 46);
    Container.add(btnCdRom);
    
    
    btn_addreader.setBounds(371, 39, 167, 25);
    Container.add(btn_addreader);
    
    
    btn_modreader.setBounds(371, 66, 167, 25);
    Container.add(btn_modreader);
    
    
    btn_adddoc.setBounds(371, 94, 167, 25);
    Container.add(btn_adddoc);
    
    
    btn_screenstatus.setBounds(698, 39, 149, 25);
    Container.add(btn_screenstatus);
    
    
    btn_statistics.setBounds(698, 66, 158, 27);
    Container.add(btn_statistics);
    
    
    btn_moddoc.setBounds(539, 39, 159, 25);
    Container.add(btn_moddoc);
    
    
    btn_addloaning.setBounds(539, 66, 159, 25);
    Container.add(btn_addloaning);
    
    
    btn_addbuyer.setBounds(539, 94, 159, 25);
    Container.add(btn_addbuyer);
    
    
    add_Cons.setOpaque(false);
    add_Cons.setBorder(null);
    add_Cons.setBounds(700, 94, 179, 28);
    Container.add(add_Cons);
    
    ExitButton btn_exit = new ExitButton("");
    btn_exit.setBounds(340, 99, 18, 23);
    Container.add(btn_exit);
    
    StatLabel status = new StatLabel("");
    status.setBounds(234, 109, 18, 18);
    Container.add(status);
    
    JLabel label_user = new JLabel("");
    label_user.setFont(new Font("Tahoma", Font.BOLD, 14));
    label_user.setText(perso.getPseudo());
    label_user.setBounds(246, 15, 119, 18);
    Container.add(label_user);
    
    JLabel label_logedat = new JLabel("");
    label_logedat.setFont(new Font("Tahoma", Font.BOLD, 14));
    label_logedat.setText(dateFormat.format(date));
    label_logedat.setBounds(246, 42, 119, 18);
    Container.add(label_logedat);
    
    JLabel label_rank = new JLabel("");
    label_rank.setFont(new Font("Tahoma", Font.BOLD, 14));
    label_rank.setText(perso.getRole());
    label_rank.setBounds(246, 69, 119, 18);
    Container.add(label_rank);
    
    CloseButton btn_close = new CloseButton("");
    btn_close.setBounds(842, 5, 48, 39);
    Container.add(btn_close);
    
    
    
    holder.setBounds(0, 178, 900, 383);
    holder.setOpaque(false);
    holder.setLayout(joker);
    
    
    if(perso.getRole().equals("Benevol"))
    {
    	holder.add(addEmpPanel,panels[4]);
    	holder.add(addLecPanel,panels[0]); 
        holder.add(modLecPanel,panels[1]);
        holder.add(addDocPanel,panels[2]);
        holder.add(modDocPanel,panels[3]);
        holder.add(addAchatPanel,panels[5]);
        holder.add(screenStatus,panels[6]);
        holder.add(livrePanel,panels[7]);
        holder.add(journalPanel,panels[8]);
        holder.add(cdRomPanel,panels[9]);
        holder.add(microFilmPanel,panels[10]);
        holder.add(addConsPanel,panels[11]);
        holder.add(statisticsPanel,panels[12]);
        btn_addloaning.setPushed(true);
        update("AddEmp");
    }
    else
    {
    	holder.add(addLecPanel,panels[0]); 
    	holder.add(addEmpPanel,panels[4]);
        holder.add(modLecPanel,panels[1]);
        holder.add(addDocPanel,panels[2]);
        holder.add(modDocPanel,panels[3]);
        holder.add(addAchatPanel,panels[5]);
        holder.add(screenStatus,panels[6]);
        holder.add(livrePanel,panels[7]);
        holder.add(journalPanel,panels[8]);
        holder.add(cdRomPanel,panels[9]);
        holder.add(microFilmPanel,panels[10]);
        holder.add(addConsPanel,panels[11]);
        holder.add(statisticsPanel,panels[12]);	
    }
    
    
   
    
    /****************Action listeners**************************/
    btnBooks.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			
		    		PersonnelInterface.this.joker.show(PersonnelInterface.this.holder, panels[7]);
		    		update("ConsBooks");
		
		}
    	
    });
 
    btnNews.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			PersonnelInterface.this.joker.show(PersonnelInterface.this.holder, panels[8]);
			update("ConsNews");
		}
    	
    });
    btnCdRom.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			PersonnelInterface.this.joker.show(PersonnelInterface.this.holder, panels[9]);
			update("ConsCdRom");
		}
    	
    });
    
    btnMicroFilm.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			PersonnelInterface.this.joker.show(PersonnelInterface.this.holder, panels[10]);
			update("ConsMicroFilm");
		}
    	
    });
    btn_addreader.addActionListener(new ActionListener() {

		@Override
		
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			if(perso.getRole().contentEquals("Benevol"))
		    	JOptionPane.showMessageDialog(null, "Votre role ne vous permet pas d'acceder a  cette section ","ERROR", JOptionPane.ERROR_MESSAGE);
		    else
		    	{
			PersonnelInterface.this.joker.show(PersonnelInterface.this.holder, panels[0]);
			update("AddLec");}
		}
    	
    });
    btn_modreader.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			if(perso.getRole().contentEquals("Benevol"))
		    	JOptionPane.showMessageDialog(null, "Votre role ne vous permet pas d'acceder a  cette section ","ERROR", JOptionPane.ERROR_MESSAGE);
		    else
		    	{
			PersonnelInterface.this.joker.show(PersonnelInterface.this.holder, panels[1]);
			update("ModLec");}
		}
    	
    });
    btn_adddoc.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			if(perso.getRole().contentEquals("Benevol"))
		    	JOptionPane.showMessageDialog(null, "Votre role ne vous permet pas d'acceder a  cette section ","ERROR", JOptionPane.ERROR_MESSAGE);
		    else
		    	{
			PersonnelInterface.this.joker.show(PersonnelInterface.this.holder, panels[2]);
			update("AddDoc");
		    	}
		}
    	
    });
    btn_moddoc.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(perso.getRole().contentEquals("Benevol"))
		    	JOptionPane.showMessageDialog(null, "Votre role ne vous permet pas d'acceder a  cette section ","ERROR", JOptionPane.ERROR_MESSAGE);
		    else
		    	{
			// TODO Auto-generated method stub
			PersonnelInterface.this.joker.show(PersonnelInterface.this.holder, panels[3]);
			update("ModDoc");}
		}
    	
    });
    btn_addloaning.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			PersonnelInterface.this.joker.show(PersonnelInterface.this.holder, panels[4]);
			update("AddEmp");
		}
    	
    });
    btn_addbuyer.addActionListener(new ActionListener() {

		@Override
		
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			if(perso.getRole().contentEquals("Benevol"))
		    	JOptionPane.showMessageDialog(null, "Votre role ne vous permet pas d'acceder a  cette section ","ERROR", JOptionPane.ERROR_MESSAGE);
		    else
		    	{
			PersonnelInterface.this.joker.show(PersonnelInterface.this.holder, panels[5]);
			update("AddAchat");}
		}
    	
    });
    btn_screenstatus.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			if(perso.getRole().contentEquals("Benevol"))
		    	JOptionPane.showMessageDialog(null, "Votre role ne vous permet pas d'acceder a  cette section ","ERROR", JOptionPane.ERROR_MESSAGE);
		    else
		    	{
			PersonnelInterface.this.joker.show(PersonnelInterface.this.holder, panels[6]);
			update("ScreenStatus");}
		}
    	
    });
    btn_statistics.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			PersonnelInterface.this.joker.show(PersonnelInterface.this.holder, panels[12]);
			update("Statistics");
		}
    	
    });
    
    add_Cons.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			if(perso.getRole().contentEquals("Benevol"))
		    	JOptionPane.showMessageDialog(null, "Votre role ne vous permet pas d'acceder a  cette section ","ERROR", JOptionPane.ERROR_MESSAGE);
		    else
		    	{
			PersonnelInterface.this.joker.show(PersonnelInterface.this.holder, panels[11]);
			update("add_Cons");}
		}
    	
    });
    Container.add(holder);
    Container.add(BackGround);
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
			 PersonnelInterface.this.setLocation(x-xx,y-yy);
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			 xx=e.getX();
			 yy=e.getY();
		}
		
	}
	
	
	public class AddReaderButton extends JButton implements MouseListener
	{
		private String event="exited";
		private Boolean pushed=true;
		public AddReaderButton(String name)
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
	
	
	public class ModReaderButton extends JButton implements MouseListener
	{
		private String event="exited";
		private Boolean pushed=false;
		public ModReaderButton(String name)
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
	
	public class AddBuyerButton extends JButton implements MouseListener
	{
		private String event="exited";
		private Boolean pushed=false;
		public AddBuyerButton(String name)
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
	
	
	public class AddLoaningButton extends JButton implements MouseListener
	{
		private String event="exited";
		private Boolean pushed=false;
		public AddLoaningButton(String name)
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
	
	
	public class AddDocButton extends JButton implements MouseListener
	{
		private String event="exited";
		private Boolean pushed=false;
		public AddDocButton(String name)
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
	
	public class ModDocButton extends JButton implements MouseListener
	{
		private String event="exited";
		private Boolean pushed=false;
		public ModDocButton(String name)
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
	
	public class ScreensStatusButton extends JButton implements MouseListener
	{
		private String event="exited";
		private Boolean pushed=false;
		public ScreensStatusButton(String name)
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
	public class AddConsButton extends JButton implements MouseListener
	{
		private String event="exited";
		private Boolean pushed=false;
		public AddConsButton(String name)
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
	
	public class StatisticsButton extends JButton implements MouseListener
	{
		private String event="exited";
		private Boolean pushed=false;
		public StatisticsButton(String name)
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
	
	
	
	
	
	
	
	
	
	public class BookButton extends JButton implements MouseListener
	{
		private String event="exited";
		private Boolean pushed=false;
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
			PersonnelInterface.this.State="closed";
			PersonnelInterface.this.dispose();
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
			PersonnelInterface.this.State="exited";
			PersonnelInterface.this.dispose();
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

	public void update (String str)
	{
		if(!str.equals("ConsBooks"))btnBooks.setPushed(false);
		if(!str.equals("ConsMicroFilm")) btnNews.setPushed(false);
		if(!str.equals("MicroFilm"))btnMicroFilm.setPushed(false);
		if(!str.equals("ConsCdRom")) btnCdRom.setPushed(false);
		if(!str.equals("AddLec")) btn_addreader.setPushed(false);	
		if(!str.equals("ModLec")) btn_modreader.setPushed(false);
		if(!str.equals("AddEmp"))btn_addloaning.setPushed(false);
		if(!str.equals("AddDoc"))btn_adddoc.setPushed(false);
		if(!str.equals("ScreenStatus"))btn_screenstatus.setPushed(false);
		if(!str.equals("Statistics")) btn_statistics.setPushed(false);
		if(!str.equals("ModDoc"))btn_moddoc.setPushed(false);
		if(!str.equals("AddAchat"))btn_addbuyer.setPushed(false);
		if(!str.equals("add_Cons"))add_Cons.setPushed(false);
		add_Cons.repaint();
		btnBooks.repaint();
		btnNews.repaint();
		btnMicroFilm.repaint();
		btnCdRom.repaint();
		btn_addreader.repaint();
		btn_modreader.repaint();
		btn_adddoc.repaint();
		btn_screenstatus.repaint();
		btn_statistics.repaint();
		btn_addloaning.repaint();
		btn_moddoc.repaint();
	    btn_addbuyer.repaint();
	}
	
	public String getStat()
	{
		return this.State;
	}
}