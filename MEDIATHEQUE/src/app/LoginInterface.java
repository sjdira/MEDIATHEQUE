package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import app.espacePersonnel.PersonnelInterface;
import media.Personnel;

@SuppressWarnings("serial")
public class LoginInterface extends JFrame
{
	private String  state="";
	private Personnel perso=new Personnel();
	private JPanel Container=new JPanel();
	private JLabel info = new JLabel("		");
	private JPasswordField passwordField;
	private String password ;
	private JTextField username = new JTextField();
	public LoginInterface()
	{
		ImageIcon i = new ImageIcon("assets/URead.png");
		this.setIconImage(i.getImage());
		this.setUndecorated(true);
		this.setSize(900,500);
		this.setTitle("Login");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.addMouseMotionListener(new DragCont());
	    this.setContentPane(Container);
	    Main.getConnection();
	    Container.setLayout(null);	   
	    
	    JLabel backGround = new JLabel("");
	    backGround.setIcon(new ImageIcon("assets/LoginPanel.png"));
	    backGround.setBounds(0, 0, 900, 500);
	    
	    
	    
	    username.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    username.setOpaque(false);
	    username.setBorder(null);
	    username.setBounds(116, 241, 229, 26);
	    username.addKeyListener(new ClavierListener());
	    username.setColumns(10);

	    
	    passwordField = new JPasswordField();
	    passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    passwordField.setOpaque(false);
	    passwordField.setBorder(null);
	    passwordField.addKeyListener(new ClavierListener());
	    passwordField.setBounds(116, 307, 229, 26);
	   
	    
	    
	    
	    
	    SeConnecterButton btn_seConnecter = new SeConnecterButton("");
	    btn_seConnecter.setContentAreaFilled(false);
	    btn_seConnecter.setBorderPainted(false);
	    btn_seConnecter.setFocusPainted(false);
	    btn_seConnecter.addKeyListener(new ClavierListener());
	    btn_seConnecter.setBounds(218, 396, 187, 30);
	    
	    
	    
	    
	    info.setFont(new Font("Tahoma", Font.BOLD, 14));
	    info.setBounds(94, 205, 251, 25);
	    
	    
	   
	    
	    ForgotPasswordButton btn_forgotpassword = new ForgotPasswordButton("");
	    btn_forgotpassword.setOpaque(false);
	    btn_forgotpassword.setBorder(null);
	    btn_forgotpassword.addKeyListener(new ClavierListener());
	    btn_forgotpassword.setBounds(70, 344, 129, 17);
	    
	    ExitButton btnNewButton = new ExitButton("");
	    btnNewButton.setBounds(0, 464, 46, 36);
	    Container.add(btnNewButton);
	    this.setVisible(true);
	    
	    Container.add(username);
	    Container.add(passwordField);
	    Container.add(btn_forgotpassword);
	    Container.add(btn_seConnecter);
	    Container.add(info);
	    Container.add(backGround);
	    
	    
	}
	public class DragCont implements MouseMotionListener
	{

		int xx=0,yy=0;
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			 	//super.mouseDragged(e
			int x=e.getXOnScreen();
			int y=e.getYOnScreen();
			LoginInterface.this.setLocation(x-xx,y-yy);  
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			 xx=e.getX();
			 yy=e.getY();
		}
		
	}
	
	public class SeConnecterButton extends JButton implements MouseListener,ActionListener
	{
		private String event="";
		public SeConnecterButton(String name)
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
			g.setColor(new Color(188,180,193,150));
			g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(),15,15);
			}
			super.paintComponent(g);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Login();
		
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
	public class ForgotPasswordButton extends JButton implements MouseListener,ActionListener
	{
		public ForgotPasswordButton (String name)
		{
			super(name);
		    this.setContentAreaFilled(false);
		    this.setFocusPainted(false);
		    this.setBorderPainted(false);
		    this.addActionListener(this);
		    this.addMouseListener(this);
		}
		public void paintComponenet()
		{
		
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
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
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
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
		    this.addKeyListener(new ClavierListener());
		}
		public void paintComponent(Graphics g)
		{
			if (event.equals("entered"))
			{
			g.setColor(new Color(188,180,193,150));
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
			LoginInterface.this.state="exited";
			LoginInterface.this.dispose();
		}
	}
	public String getStat()
	{
		return this.state;
	}
	class ClavierListener implements KeyListener{
		
		public void keyReleased(KeyEvent event) {
		   
			if (event.getKeyCode()==10)
				Login();
			
		}

		  
	    public void keyPressed(KeyEvent event) {}
		public void keyTyped(KeyEvent event) {}

		}
	
	public void Login() {
		// TODO Auto-generated method stub
		
		password = String.valueOf(passwordField.getPassword());
		if(username.getText().equals("") )
			{
				info.setForeground(Color.gray);
				info.setText("Svp Entrer votre Pseudo ");
				return;
			}
		if(password.equals(""))
		{
			info.setForeground(Color.gray);
			info.setText("		Svp Entrer votre mot de passe");
			return;
		}
		
			if(perso.verifyLogin(username.getText(),password ))
			{								
				LoginInterface.this.setVisible(false);
				Thread thread=new Thread(new Runnable() {
					PersonnelInterface perso;
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							perso=new PersonnelInterface(LoginInterface.this.perso);
							while(perso.isDisplayable());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						if (perso.getStat().equals("closed"))
							{
							LoginInterface.this.state="closed";
							LoginInterface.this.dispose();
							}
						else
						{
							LoginInterface.this.state="exited";
							perso=null;	
							info.setText("");
							passwordField.setText("");
							username.setText("");
							LoginInterface.this.setVisible(true);
						}
					}		
				});
				thread.start();
			}
			else
			{
				info.setForeground(Color.RED);
				info.setText("		Username or password incorrect ");
			}
	
	}
}
