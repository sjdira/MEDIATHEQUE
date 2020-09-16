package app.espaceConsultation;

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
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import app.Main;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class MicroFilmPanel extends JPanel
{	private JTextField titreMicroFilm;
	private JFormattedTextField dateMicroFilm;
	private Statement prep;
	private ResultSet resultset;
	private JTable table;
	private JScrollPane scrollPane;
	
	public MicroFilmPanel()
	{
	this.setOpaque(false);
	this.setPreferredSize(new Dimension(897,313));
    this.setLayout(null);
    
    JLabel BackGround = new JLabel("New label");
    BackGround.setHorizontalAlignment(SwingConstants.LEFT);
    BackGround.setIcon(new ImageIcon("assets/PersonnelMicroFilmConPanel.png"));
    BackGround.setBounds(0, 0, 897, 313);
    
    titreMicroFilm = new JTextField();
    titreMicroFilm.setForeground(Color.BLACK);
    titreMicroFilm.setFont(new Font("Furat", Font.BOLD, 14));
    titreMicroFilm.setBorder(null);
    titreMicroFilm.setOpaque(false);
    titreMicroFilm.setBounds(166, 83, 90, 20);
    titreMicroFilm.setColumns(10);
    
    
    
    try {
		MaskFormatter mf1 = new MaskFormatter("####-##-##");
		dateMicroFilm = new JFormattedTextField(mf1);
		dateMicroFilm.setFont(new Font("Furat", Font.BOLD, 14));
		dateMicroFilm.setForeground(Color.BLACK);
		} catch (ParseException e) {}
    
    dateMicroFilm.setBorder(null);
    dateMicroFilm.setOpaque(false);
    dateMicroFilm.setColumns(10);
    dateMicroFilm.setBounds(166, 114, 90, 20);
    JLabel lp = new JLabel("yyyy-mm-dd");
    lp.setOpaque(false);
    lp.setBorder(null);
    lp.setBounds(166, 134, 90,15);
    
    Button cancelButton = new Button("Cancel");
    cancelButton.setBounds(96, 243, 75, 19);
    cancelButton.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent arg0) {
			titreMicroFilm.setText("");
			dateMicroFilm.setText("");			
		}
	});
    
    Button submitButton = new Button("Submit");
    submitButton.setBounds(187, 243, 74, 19);
    submitButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			find(titreMicroFilm.getText(), dateMicroFilm.getText());
		}
	});
    
    Button refreshButton = new Button("");
    refreshButton.setBounds(838, 23, 19, 19);
    refreshButton.addActionListener(new ActionListener() {		
		public void actionPerformed(ActionEvent arg0) {
		updateTable();
		}
	});
      
   
    
    ButtonGroup bg = new ButtonGroup();
    JRadioButton ordreAlpha = new JRadioButton("Ordre Alphabetique");
    ordreAlpha.setOpaque(false);
    ordreAlpha.setBorder(null);;
    ordreAlpha.setBounds(50, 190, 226, 20);
    ordreAlpha.setSelected(true);       
    bg.add(ordreAlpha);    
       
    this.add(lp);
    this.add(ordreAlpha);
    this.add(titreMicroFilm);
    this.add(dateMicroFilm);
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
    table.setForeground(new Color(0, 0, 0));
    table.setEnabled(false);
    table.setColumnSelectionAllowed(true);
    table.setBackground(new Color(128,128,128));
    table.setRowHeight(35);
    scrollPane.setViewportView(table);
    scrollPane.setVisible(false);
    this.add(scrollPane);
    
	this.add(BackGround);    
	}
	public void updateTable() {
		 
        try {
        	prep=Main.getConnection().createStatement();
        	resultset = prep.executeQuery(
        			"SELECT titre,guichet,prix FROM `microfilms` ORDER BY `microfilms`.`titre` ASC ; ");
            table.setModel(DbUtils.resultSetToTableModel(resultset));
            scrollPane.setVisible(true);
        }
            catch (Exception e) {
            	JOptionPane.showMessageDialog(null, 
            			"verifier votre connexion !", 
            			"ERREUR	DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);    			
        }
    }
	public void find (String titre ,String date) {
		 try {
	        	prep=Main.getConnection().createStatement();
	        	resultset = prep.executeQuery(
	        			"SELECT titre,guichet,prix FROM `microfilms` WHERE `titre`='"
    					+titre+"' and `dateAjout`='"+date+"' ; ");
	            table.setModel(DbUtils.resultSetToTableModel(resultset));
	            scrollPane.setVisible(true);
	        }
	            catch (Exception e) {
	            	JOptionPane.showMessageDialog(null,
	            			"Veuillez verifier votre connexion",
	            			"ERREUR	DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
	    			
	        }
	}
	
	
}
