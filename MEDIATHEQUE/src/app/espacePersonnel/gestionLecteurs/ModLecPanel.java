package app.espacePersonnel.gestionLecteurs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
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
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class ModLecPanel  extends JPanel{

	private JTextField codeLecteur;
	private JTextField nom;
	private JTextField prenom;
	private JTextField codeLecteur2;
	private JTextField adresse;
	private JTextField dateAjout;
	private JFormattedTextField phone;
	private JRadioButton checkCot;
	private JScrollPane scrollPane ;
	private JTable table;
	
	public ModLecPanel()
	{
		 	this.setOpaque(false);
		    this.setPreferredSize(new Dimension( 900, 322));
		    this.setLayout(null);
		    
		    JLabel backGround = new JLabel("New label");
		    backGround.setIcon(new ImageIcon("assets/ModLecPanel.png"));
		    backGround.setBounds(0, 0, 900, 322);
		    
		    try {				
				phone=new JFormattedTextField(new MaskFormatter("##########"));
				dateAjout = new JFormattedTextField(new MaskFormatter("####-##-##"));				
				} catch (ParseException e) {}
		    codeLecteur = new JTextField();
		    codeLecteur2 = new JTextField();
		    codeLecteur.setOpaque(false);
		    codeLecteur.setBorder(null);
		    codeLecteur.setBounds(177, 39, 108, 20);
		    codeLecteur.setColumns(10);
		    
		    nom = new JTextField();
		    nom.setBorder(null);
		    nom.setOpaque(false);
		    nom.setColumns(10);
		    nom.setBounds(178, 96, 112, 20);

		    prenom = new JTextField();
		    prenom.setBorder(null);
		    prenom.setOpaque(false);
		    prenom.setColumns(10);
		    prenom.setBounds(178, 125, 112, 20);

		    Button btn_submit = new Button("Submit");
		    btn_submit.setBounds(225, 71, 92, 19);
		    btn_submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						if(!submit(Integer.parseInt(codeLecteur.getText()))) {
							JOptionPane.showMessageDialog(ModLecPanel.this,
									"ce lecteur n'exite pas dans la base");
						};
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
							"entrer le codelecteur correctement",
							"Ã©chec d'ajout ! ", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			});
		    
		    Button btn_modify = new Button("Modify");
		    btn_modify.setBounds(225, 266, 93, 20);
		    btn_modify.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(modifyLecteur())
						JOptionPane.showMessageDialog(ModLecPanel.this, 
						Integer.parseInt(codeLecteur.getText())+" est modifié");
				}
			});
		    
		   
		    codeLecteur2.setOpaque(false);
		    codeLecteur2.setBorder(null);
		    codeLecteur2.setBounds(554, 44, 112, 20);  
		    codeLecteur2.setColumns(10);
		    
		    Button btn_chercher = new Button("Chercher");
		    btn_chercher.setBounds(777, 49, 93, 19);
		    btn_chercher.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						int code =Integer.parseInt(codeLecteur2.getText());
						find(code);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
							"entrer le code lecteur correctement",
							"Echec de recherche ! ", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		    
		    Button btn_refresh = new Button("");
		    btn_refresh.setBounds(852, 88, 18, 17);
		    btn_refresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					 try {
				        	Statement prep = Main.getConnection().createStatement();
				        	ResultSet resultset = prep.executeQuery(
				        		"SELECT codeLecteur,nom,prenom,adresse,"+
				        		"telephone,dateAjout,cotisation FROM `users`");        	
				        	table.setModel(DbUtils.resultSetToTableModel(resultset));
				        	scrollPane.setVisible(true);
				        }
				            catch (Exception e) {
				            	JOptionPane.showMessageDialog(null,
				            			"Veillez verefier votre conextion", 
				            			"ERREUR	DE CONNEXION ! ",
				            			JOptionPane.ERROR_MESSAGE);				    			
				        }
				}
			});
		    
		    Button bnt_cancel = new Button("Cancel");
		    bnt_cancel.setBounds(119, 266, 94, 20);
		    bnt_cancel.addActionListener(new ActionListener() {			
				public void actionPerformed(ActionEvent arg0) {
					dateAjout.setText("");phone.setText("");
					nom.setText("");prenom.setText("");phone.setText("");
					codeLecteur.setText("");adresse.setText("");
				}
			});
		    
		    
		    adresse = new JTextField();
		    adresse.setOpaque(false);
		    adresse.setColumns(10);
		    adresse.setBorder(null);
		    adresse.setBounds(178, 151, 112, 20);
		    
		  
		    phone.setOpaque(false);
		    phone.setBorder(null);
		    phone.setBounds(178, 176, 112, 20);
		    
		    
		   
		    dateAjout.setOpaque(false);
		    dateAjout.setColumns(10);
		    dateAjout.setBorder(null);
		    dateAjout.setBounds(179, 197, 112, 20);
		    
		    ButtonGroup bg = new ButtonGroup();
			   
		    checkCot = new JRadioButton("true");
		    checkCot.setBorder(null);
		    checkCot.setOpaque(false);
		    checkCot.setBounds(182, 225, 62, 23);
		    
		    
		    JRadioButton CheckCot1 = new JRadioButton("false");
		    CheckCot1.setBorder(null);
		    CheckCot1.setOpaque(false);
		    CheckCot1.setBounds(246, 225, 62, 23);
		    
		    bg.add(CheckCot1);
		    bg.add(checkCot);

		    scrollPane =new JScrollPane();
		   
		    scrollPane.setBounds(412, 110, 466, 200);
		    scrollPane.getViewport().setBackground(new Color(128,128,128));
		    scrollPane.setOpaque(true);
		    table = new JTable();
		    table.setFont(new Font("URW Chancery L", Font.ITALIC, 20));		   
		    table.setShowVerticalLines(false);
		    table.setShowHorizontalLines(false);
		    table.setShowGrid(false);
		    table.setForeground(new Color(0, 0, 0));
		    table.setEnabled(false);		    
		    table.setBackground(new Color(128,128,128));
		    table.setBounds(413, 111,800,200);
		    table.setRowHeight(35);
		    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		    scrollPane.setViewportView(table);
		    scrollPane.setVisible(false);
		    
		    this.add(scrollPane);
		    this.add(codeLecteur);
		    this.add(nom);
		    this.add(prenom);
		    this.add(btn_submit);   
		    this.add(btn_modify);
		    this.add(codeLecteur2);
		    this.add(btn_chercher);
		    this.add(btn_refresh);
		    this.add(bnt_cancel);
		    this.add(adresse);
		    this.add(phone);
		    this.add(dateAjout);
		    this.add(checkCot);
		    this.add(CheckCot1);
		    this.add(backGround); 
		    
		    
	}
	public void find (int codeLecteur ) {
		 try {
	        	Statement prep = Main.getConnection().createStatement();
	        	ResultSet resultset = prep.executeQuery(
	        			"SELECT * FROM `users` WHERE `codeLecteur`="+codeLecteur+" ;");        	
	        	table.setModel(DbUtils.resultSetToTableModel(resultset));
	        	scrollPane.setVisible(true);
	        }
	            catch (Exception e) {
	            	JOptionPane.showMessageDialog(null,
	            			"Veuillez verifier votre connection",
	            			"ERREUR	DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
	    			
	        }
	}
	public boolean submit (int codeLecteur) {
		boolean b = false ;
		 try {
	        	Statement prep = Main.getConnection().createStatement();
	        	ResultSet resultset = prep.executeQuery(
	        			"SELECT nom,prenom,adresse,telephone,dateAjout,cotisation"+
	        			" FROM `users` WHERE `codeLecteur`="+codeLecteur+" ;");        	
	        	while(resultset.next()) {	        		
	        		this.nom.setText(resultset.getString(1));
	        		this.prenom.setText(resultset.getString(2));
	        		this.adresse.setText(resultset.getString(3));
	        		this.phone.setText(0+String.valueOf(resultset.getInt(4)));
	        		this.dateAjout.setText(String.valueOf(resultset.getDate(5)));
	        		this.checkCot.setSelected(resultset.getBoolean(6));
	        	}	        
	        	b=true;
	        }
		 catch (Exception e) {
			 b=false;
	         JOptionPane.showMessageDialog(null,
	         		"Veillez verifier votre connexion", "ERREUR	DE CONNEXION ! ",
	           		JOptionPane.ERROR_MESSAGE);	    			
	        }
		 return b ;	    	
		 }
	public boolean modifyLecteur () {
		boolean b=true;
		try {
        	PreparedStatement prep = Main.getConnection().prepareStatement(      			
        	"UPDATE `users` SET `prenom` ='"+this.prenom.getText()+"', `nom` ='"
        	+this.nom.getText()+"',`adresse`='"+this.adresse.getText()+"', `telephone` ="
        			+Integer.parseInt(this.phone.getText())+
        			",`cotisation`= "+this.checkCot.isSelected()+
        			" WHERE `users`.`codeLecteur`="+Integer.parseInt(this.codeLecteur.getText())+";");
        	
        	prep.executeUpdate();	   	
        	}
		catch (Exception e) {
			b=false;
            	JOptionPane.showMessageDialog(null,
            			"vous devez remplir tous les chanps correctement \n"+
            			"(verifiez que le nemero de telephone est de 10 chifres)", 
            			"ERREUR	DE CONNEXION ! ",
            			JOptionPane.ERROR_MESSAGE);            	
        }
		return b ;
	}
}
