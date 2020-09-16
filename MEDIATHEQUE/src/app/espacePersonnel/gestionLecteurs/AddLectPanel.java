package app.espacePersonnel.gestionLecteurs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import app.Main;
import app.espaceConsultation.Button;
import media.Lecteur;
import net.proteanit.sql.DbUtils;


@SuppressWarnings("serial")
public class AddLectPanel extends JPanel 
{
	private JTextField codeLecteur;
	private JFormattedTextField phone;
	private JTextField nom;
	private JTextField prenom;
	private JTextField adresse;
	private JTextField date;
	private JTextField codeLecteur1;
	private java.sql.Date d2;
	private JScrollPane scrollPane ;
	private JTable table;
	
	
	public AddLectPanel ()
	{
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(900,322));
	    this.setLayout(null);
	    
	    JLabel backGround = new JLabel("");
	    backGround.setIcon(new ImageIcon("assets/AddLectPanel.png"));
	    backGround.setBounds(0, 0, 900, 322);
	    
	    try {			
			phone=new JFormattedTextField(new MaskFormatter("##########"));
			} catch (ParseException e) {}
	    codeLecteur = new JTextField();
	    codeLecteur.setText(codeLecteur());
	    codeLecteur.setBounds(180, 62, 112, 20);
	    codeLecteur.setOpaque(false);
	    codeLecteur.setBorder(null);
	    codeLecteur.setColumns(10);
	    
	    nom = new JTextField();
	    nom.setColumns(10);
	    nom.setBounds(181, 93, 112, 20);
	    nom.setOpaque(false);
	    nom.setBorder(null);
	    
	    prenom = new JTextField();
	    prenom.setColumns(10);
	    prenom.setBounds(181, 120, 112, 20);
	    prenom.setOpaque(false);
	    prenom.setBorder(null);
	    
	    adresse = new JTextField();
	    adresse.setColumns(10);
	    adresse.setBounds(182, 145, 112, 20);
	    adresse.setOpaque(false);
	    adresse.setBorder(null);
	    

	    
	    phone.setOpaque(false);
	    phone.setBorder(null);
	    phone.setBounds(181, 170, 118, 20);
	    
	   
	    

	    java.util.Date d1 = new java.util.Date();
		d2 = new java.sql.Date(d1.getTime());
	    
	    date = new JTextField(d2.toString());
	    date.setColumns(10);
	    date.setBounds(180, 194, 113, 20);
	    date.setOpaque(false);
	    date.setBorder(null);
	    
	    codeLecteur1=new JTextField("");    
	    codeLecteur1.setBounds(558, 48, 112, 20);
	    codeLecteur1.setColumns(10);
	    codeLecteur1.setOpaque(false);
	    codeLecteur1.setBorder(null);
	    
	    ButtonGroup bg = new ButtonGroup();
	   
	    JRadioButton checkCot = new JRadioButton("true");
	    checkCot.setBounds(190, 218, 57, 23);
	    checkCot.setOpaque(false);
	    checkCot.setBorder(null);
	    checkCot.setSelected(true);
	    
	    JRadioButton CheckCot1 = new JRadioButton("false");
	    CheckCot1.setBounds(256, 218, 57, 23);
	    CheckCot1.setOpaque(false);
	    CheckCot1.setBorder(null);
	    
	    bg.add(CheckCot1);
	    bg.add(checkCot);
	    
	    Button btn_cancel = new Button("Cancel");
	    btn_cancel.setBounds(121, 269, 95, 19);
	    btn_cancel.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {
				nom.setText("");prenom.setText("");phone.setText("");
				codeLecteur.setText("");adresse.setText("");
				codeLecteur.setText(codeLecteur());
			}
		});
	    
	    Button btn_submit = new Button("Submit");
	    btn_submit.setBounds(228, 269, 93, 20);
	    btn_submit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {			
				boolean b=true;
				try {
					int code =Integer.parseInt(codeLecteur.getText());
					b = new Lecteur(nom.getText(), prenom.getText(),adresse.getText(),code,
							Integer.parseInt(phone.getText()),
							checkCot.isSelected()).addLecteur();					
				} catch (Exception e) {
					b=false;
					JOptionPane.showMessageDialog(null,
							"Vellier remplire  les champs correctement",
							"échec d'ajout ! ", JOptionPane.ERROR_MESSAGE);
				}
				if(b)JOptionPane.showMessageDialog(null,nom.getText()+" est ajout�",
						"", JOptionPane.INFORMATION_MESSAGE);
				nom.setText("");prenom.setText("");
				codeLecteur.setText(codeLecteur());adresse.setText("");
				phone.setText("");
			}
		});

	    
	    
	    
	    Button btn_submit_1 = new Button("Chercher");
	    btn_submit_1.setBounds(781, 53, 93, 20);
	    btn_submit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int code =Integer.parseInt(codeLecteur1.getText());
					find(code);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"entrer le codelecteur correctement",
							"échec de recherche ! ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	    
	    Button btn_refresh = new Button("");
	    btn_refresh.setBounds(856, 91, 18, 17);
	    btn_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 try {
			        	Statement prep = Main.getConnection().createStatement();
			        	ResultSet resultset = prep.executeQuery(
			        		"SELECT codeLecteur,nom,prenom,adresse,"+
			        		"telephone,dateAjout,cotisation"+" FROM `users`");        	
			        	table.setModel(DbUtils.resultSetToTableModel(resultset));
			        	scrollPane.setVisible(true);
			        }
			            catch (Exception e) {
			            	JOptionPane.showMessageDialog(null,
			            			"Veillez verefier votre conextion", 
			            			"ERREUR	DE CONNEXION ! ",
			            			JOptionPane.ERROR_MESSAGE);				    			
			        }
				 codeLecteur.setText(codeLecteur());
			}
		});

	    
	    scrollPane =new JScrollPane();
	   
	    scrollPane.setBounds(412, 110, 466,200);
	    scrollPane.getViewport().setBackground(new Color(128,128,128));	   
	    table = new JTable();
	    table.setFont(new Font("URW Chancery L", Font.ITALIC, 20));	    
	    table.setShowVerticalLines(false);
	    table.setShowHorizontalLines(false);
	    table.setShowGrid(false);
	    table.setForeground(new Color(0, 0, 0));
	    table.setEnabled(false);
	    table.setBackground(new Color(128,128,128));
	    table.setBounds(413, 111,800, 200);
	    table.setRowHeight(35);
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    scrollPane.setViewportView(table);
	    scrollPane.setVisible(false);
	    
	    this.add(scrollPane);	    
	    this.add(codeLecteur);
	    this.add(codeLecteur1);
	    this.add(nom);
	    this.add(prenom);
	    this.add(adresse);
	    this.add(date);
	    this.add(phone);
	    this.add(CheckCot1);
	    this.add(btn_cancel);
	    this.add(btn_submit);
	    this.add(btn_submit_1);
	    this.add(btn_refresh);
	    this.add(backGround);
	    
	    
	    
	}
	private void find (int codeLecteur ) {
		 try {
	        	Statement prep = Main.getConnection().createStatement();
	        	ResultSet resultset = prep.executeQuery(
	        			"SELECT * FROM `users` WHERE `codeLecteur`="
	        					+codeLecteur+" ;");        	
	        	table.setModel(DbUtils.resultSetToTableModel(resultset));
	        	scrollPane.setVisible(true);
	        }
	            catch (Exception e) {
	            	JOptionPane.showMessageDialog(null,
	            			"Veuillez verifier votre connexion",
	            			"ERREUR	DE CONNEXION ! ",
	            			JOptionPane.ERROR_MESSAGE);
	    			
	        }
	}
	private String codeLecteur() {
		String a="";
		 try {
	        	Statement prep = Main.getConnection().createStatement();
	        	ResultSet resultset = prep.executeQuery(
	        			"SELECT MAX(codeLecteur) FROM `users`");        	
	        	resultset.next();a=String.valueOf(resultset.getInt(1)+1);
	        }
	            catch (Exception e) {
	            	JOptionPane.showMessageDialog(null,
	            			"Veuillez verifier votre connexion",
	            			"ERREUR	DE CONNEXION ! ",
	            			JOptionPane.ERROR_MESSAGE);	    			
	        }
		 return a;
	}
	
}
