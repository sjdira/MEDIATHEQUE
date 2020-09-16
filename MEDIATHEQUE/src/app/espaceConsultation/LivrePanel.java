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
public class LivrePanel extends JPanel {
	private JTextField titrelivre;
	private JTextField auteur;
	private JTextField auteur1;
	private JTextField auteur2;
	private Statement prep;
	private ResultSet resultset;
	private JTable table_1;
	private  JScrollPane scrollPane;
	
	
	public LivrePanel()
	{
    this.setLayout(null);
    this.setOpaque(false);
    this.setPreferredSize(new Dimension(897,313));
    
    JLabel BackGround = new JLabel("New label");
    BackGround.setIcon(new ImageIcon("assets/PersonnelLivreConPanel.png"));
    BackGround.setBounds(0, 0, 897, 313);
    
    titrelivre = new JTextField();
    titrelivre.setFont(new Font("Furat", Font.BOLD, 14));
    titrelivre.setForeground(new Color(0, 0, 0));
    titrelivre.setBorder(null);
    titrelivre.setOpaque(false);
    titrelivre.setBounds(151, 52, 108, 20);
    titrelivre.setColumns(10);
    
    auteur = new JTextField();
    auteur.setFont(new Font("Furat", Font.BOLD, 14));
    auteur.setForeground(new Color(0, 0, 0));
    auteur.setOpaque(false);
    auteur.setBorder(null);
    auteur.setColumns(10);
    auteur.setBounds(151, 83, 108, 15);
    
    
    auteur1 = new JTextField();
    auteur1.setOpaque(false);
    auteur1.setBorder(null);
    auteur1.setColumns(10);
    auteur1.setBounds(151, 111, 108, 20);
   
    
    auteur2 = new JTextField();
    auteur2.setBorder(null);
    auteur2.setOpaque(false);
    auteur2.setColumns(10);
    auteur2.setBounds(151, 149, 108, 20);
    
    
    Button cancelButton = new Button("Cancel");
    cancelButton.setBounds(96, 268, 74, 18);
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
    refreshButton.setBounds(838, 22, 19, 19);
    refreshButton.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent arg0) {
			updateTable();
		}
	});

    ButtonGroup bg = new ButtonGroup();
    JRadioButton ordreAlpha = new JRadioButton("Ordre Alphabetique");
    ordreAlpha.setOpaque(false);
    ordreAlpha.setBorder(null);
    ordreAlpha.setBounds(50, 215, 148, 23);
    ordreAlpha.setSelected(true);
    
    
   
    
    JRadioButton date = new JRadioButton("Par date");
    date.setOpaque(false);
    date.setBorder(null);
    date.setBounds(50, 235, 146, 23);
    
    bg.add(ordreAlpha);
    bg.add(date);
    
    
    
    this.add(ordreAlpha);
    this.add(date);
    this.add(titrelivre);
    this.add(auteur);
    this.add(auteur1);
    this.add(auteur2);
    this.add(cancelButton);
    this.add(submitButton);
    this.add(refreshButton);
    
    
    scrollPane = new JScrollPane();      
    scrollPane.setBounds(368, 64,498, 237);
    scrollPane.getViewport().setBackground(new Color(128,128,128));
    table_1 = new JTable();
    table_1.setFont(new Font("URW Chancery L", Font.ITALIC, 20));
    table_1.setSurrendersFocusOnKeystroke(true);
    table_1.setShowVerticalLines(false);
    table_1.setShowHorizontalLines(false);
    table_1.setShowGrid(false);
    table_1.setForeground(new Color(0, 0, 0));
    table_1.setEnabled(false);
    table_1.setColumnSelectionAllowed(true);
    table_1.setBackground(new Color(128,128,128));
    table_1.setRowHeight(35);
    scrollPane.setViewportView(table_1);
    scrollPane.setVisible(false);
    this.add(scrollPane);
    
    this.add(BackGround);
	}
	public void updateTable() {
		 
        try {
        	prep=Main.getConnection().createStatement();
        	resultset = prep.executeQuery(
        			"SELECT titre,auteur,rayonnage,prix FROM `livres` "+
        			"ORDER BY `livres`.`titre` ASC ; ");
          	table_1.setModel(DbUtils.resultSetToTableModel(resultset));
          	 scrollPane.setVisible(true);
        }
            catch (Exception e) {
            	JOptionPane.showMessageDialog(null, "verifier votre connexion !",
            			"ERREUR	DE CONNEXION ! ", 	JOptionPane.ERROR_MESSAGE);
            	
        }
    }
	public void find (String titre ,String auteur) {
		 try {
	        	prep=Main.getConnection().createStatement();
	        	resultset = prep.executeQuery(
	        			"SELECT titre,auteur,rayonnage,prix FROM `livres` WHERE `titre`='"
	        	+titre+"' and `auteur`='"+auteur+"' ; ");
	            table_1.setModel(DbUtils.resultSetToTableModel(resultset));
	            scrollPane.setVisible(true);
	        }
	            catch (Exception e) {
	            	JOptionPane.showMessageDialog(null,
	            			"Veuillez verifier votre connexion", "ERREUR	DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
	    			
	        }
	}
}