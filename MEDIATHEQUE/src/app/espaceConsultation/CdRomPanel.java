package app.espaceConsultation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import app.Main;
import net.proteanit.sql.DbUtils;



@SuppressWarnings("serial")
public class CdRomPanel extends JPanel {
	private JTextField titrelivre;
	private JTextField auteur;
	private JTextField auteur1;
	private JTextField auteur2;
	private JTable table;
	private Statement prep;
	private ResultSet resultset;
	private  JScrollPane scrollPane;
	
	public CdRomPanel()
	{
    this.setLayout(null);
    this.setOpaque(false);
    this.setPreferredSize(new Dimension(897,313));
    JLabel BackGround = new JLabel("New label");
    BackGround.setIcon(new ImageIcon("assets/PersonnelCdRomConPanel.png"));
    BackGround.setBounds(0, 0, 897, 313);
    
    titrelivre = new JTextField();
    titrelivre.setForeground(Color.BLACK);
    titrelivre.setFont(new Font("Furat", Font.BOLD, 14));
    titrelivre.setBorder(null);
    titrelivre.setOpaque(false);
    titrelivre.setBounds(153, 52, 108, 20);
    titrelivre.setColumns(10);
    
    auteur = new JTextField();
    auteur.setForeground(Color.BLACK);
    auteur.setFont(new Font("Furat", Font.BOLD, 14));
    auteur.setOpaque(false);
    auteur.setBorder(null);
    auteur.setColumns(10);
    auteur.setBounds(153, 83, 108, 15);
    
    
    auteur1 = new JTextField();
    auteur1.setOpaque(false);
    auteur1.setBorder(null);
    auteur1.setColumns(10);
    auteur1.setBounds(150, 113, 108, 20);
   
    
    auteur2 = new JTextField();
    auteur2.setBorder(null);
    auteur2.setOpaque(false);
    auteur2.setColumns(10);
    auteur2.setBounds(150, 150, 108, 20);
    
    
    Button cancelButton = new Button("Cancel");
    cancelButton.setBounds(96, 268, 74, 19);
     cancelButton.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent arg0) {
			titrelivre.setText("");
			auteur.setText("");
			auteur1.setText("");
			auteur2.setText("");			
		}
	});
    
    Button submitButton = new Button("Submit");
    submitButton.setBounds(188, 268, 74, 19);
    submitButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			find(titrelivre.getText(), auteur.getText());
		}
	});
    
    Button refreshButton = new Button("");
    refreshButton.setBounds(839, 22, 19, 19);
    refreshButton.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent arg0) {
			 ubdateTable();
		}
	});

    ButtonGroup bg = new ButtonGroup();
    JRadioButton ordreAlpha = new JRadioButton("Ordre Alphabetique");
    ordreAlpha.setOpaque(false);
    ordreAlpha.setBorder(null);
    ordreAlpha.setBounds(58, 218, 160, 23);
    ordreAlpha.setSelected(true);       
    bg.add(ordreAlpha);
     
    
    this.add(ordreAlpha);    
    this.add(titrelivre);
    this.add(auteur);
    this.add(auteur1);
    this.add(auteur2);
    this.add(cancelButton);
    this.add(submitButton);
    this.add(refreshButton);
    
    scrollPane = new JScrollPane();      
    scrollPane.setBounds(368, 64, 498, 237);
    scrollPane.getViewport().setBackground(new Color(128,128,128));
    table = new JTable();
    table.setFont(new Font("URW Chancery L", Font.ITALIC, 20));
    table.setSurrendersFocusOnKeystroke(true);
    table.setShowVerticalLines(false);
    table.setShowHorizontalLines(false);
    table.setShowGrid(false);
    table.setBackground(new Color(128,128,128));
    table.setForeground(new Color(0,0,0));
    table.setEnabled(false);
    table.setColumnSelectionAllowed(true);
    table.setRowHeight(35);
    scrollPane.setViewportView(table);
    scrollPane.setVisible(false);
    this.add(scrollPane);
    
    this.add(BackGround);
    
	}
	public void ubdateTable() {
		 
        try {
        	prep=Main.getConnection().createStatement();
        	resultset = prep.executeQuery(
        			"SELECT titre,auteur,guichet,prix FROM `cdroms` ORDER BY `cdroms`.`titre` ASC ; ");
            table.setModel(DbUtils.resultSetToTableModel(resultset));
            scrollPane.setVisible(true);
        }
            catch (Exception e) {
            	JOptionPane.showMessageDialog(null,"Veillez verifier votre connexion",
            			"ERREUR	DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
    			
        }
    }
	public void find (String titre ,String auteur) {
		 try {
	        	prep=Main.getConnection().createStatement();
	        	resultset = prep.executeQuery(
	        			"SELECT titre,auteur,guichet,prix FROM `cdroms` WHERE `titre`='"
    					+titre+"' and `auteur`='"+auteur+"' ; ");
	        	
	            table.setModel(DbUtils.resultSetToTableModel(resultset));
	            scrollPane.setVisible(true);
	        }
	            catch (Exception e) {
	            	JOptionPane.showMessageDialog(null,
	            			"Veuillez verifier votre connexion", "ERREUR	DE CONNEXION ! ",
	            			JOptionPane.ERROR_MESSAGE);
	    			
	        }
	}
}
