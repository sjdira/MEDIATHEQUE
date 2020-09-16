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
import javax.swing.text.MaskFormatter;

import app.Main;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class JournalPanel extends JPanel
{
	private JTextField titreJournal;
	private JFormattedTextField dateJournal;
	private JRadioButton ordreAlpha;
	private JRadioButton date ;
	private Statement prep;
	private ResultSet resultset;
	private JTable table;
	private JScrollPane scrollPane;
	
	public JournalPanel()
	{
		this.setLayout(null);
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(897,313));

	    
	    JLabel BackGround = new JLabel("New label");
	    BackGround.setIcon(new ImageIcon("assets/PersonnelJournalPanelConPanel.png"));
	    BackGround.setBounds(0, 0, 897, 313);
	    
	    
	    
	    titreJournal = new JTextField();
	    titreJournal.setForeground(new Color(0, 0, 0));
	    titreJournal.setFont(new Font("Furat", Font.BOLD, 14));
	    titreJournal.setBorder(null);
	    titreJournal.setOpaque(false);
	    titreJournal.setBounds(155, 85, 102, 20);
	    titreJournal.setColumns(10);
	    
	     try {
			MaskFormatter mf1 = new MaskFormatter("####-##-##");
			dateJournal = new JFormattedTextField(mf1);
			} catch (ParseException e) {}
	    dateJournal.setFont(new Font("Furat", Font.BOLD, 14));
	    dateJournal.setForeground(new Color(0, 0, 0));
	    dateJournal.setBorder(null);
	    dateJournal.setOpaque(false);
	    dateJournal.setColumns(10);
	    dateJournal.setBounds(154, 114, 102, 20);
	    JLabel lp = new JLabel("yyyy-mm-dd");
	    lp.setOpaque(false);
	    lp.setBorder(null);
	    lp.setBounds(154, 134, 90,15);
	    
	    Button cancelButton = new Button("Cancel");
	    cancelButton.setBounds(97, 248, 74, 19);
	    cancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				titreJournal.setText("");
				dateJournal.setText("");
				
			}	
		});
	    
	    Button submitButton = new Button("Submit");
	    submitButton.setBounds(187, 248, 75, 19);
	    submitButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {
				find(titreJournal.getText(), dateJournal.getText());
			}
		});
	    
	    Button refreshButton = new Button("");
	    refreshButton.setBounds(838, 22, 19, 20);
	    refreshButton.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {
			updateTable();
			}
		});
	    
	   
	    ButtonGroup bg = new ButtonGroup();
	    ordreAlpha = new JRadioButton("Ordre Alphabetique");
	    ordreAlpha.setOpaque(false);
	    ordreAlpha.setBorder(null);
	    ordreAlpha.setBounds(50, 186, 226, 20);
	    ordreAlpha.setSelected(true);
	    
	    
	    date = new JRadioButton("Par date");
	    date.setOpaque(false);
	    date.setBorder(null);
	    date.setBounds(50, 205, 97, 23);
	   
	    bg.add(ordreAlpha);
	    bg.add(date);
	    
	    this.add(lp);
	    this.add(titreJournal);
	    this.add(dateJournal);
	    this.add(cancelButton);
	    this.add(submitButton);
	    this.add(refreshButton);
	    this.add(ordreAlpha);
	    this.add(date);
	    
	    
	    
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
        	if(ordreAlpha.isSelected())resultset = prep.executeQuery(
        			"SELECT titre,datejournal,rayonnage,prix FROM `journals` "
					+"ORDER BY `journals`.`titre` ASC ; ");
        	else if(date.isSelected())resultset = prep.executeQuery(
        			"SELECT titre,datejournal,rayonnage,prix FROM `journals` "
        			+"ORDER BY `journals`.`datejournal` ASC ; ");
            table.setModel(DbUtils.resultSetToTableModel(resultset));
            scrollPane.setVisible(true);
        }
            catch (Exception e) {
            	JOptionPane.showMessageDialog(null, 
            			"verifier votre connexion !", "ERREUR	DE CONNEXION ! ",
            			JOptionPane.ERROR_MESSAGE);    			
        }
    }
	public void find (String titre ,String date) {
		 try {
	        	prep=Main.getConnection().createStatement();
	        	if(ordreAlpha.isSelected())resultset = prep.executeQuery(
	        			"SELECT titre,datejournal,rayonnage,prix FROM `journals` WHERE `titre`='"+titre+
	        			"' and `datejournal`='"+date+"' ORDER BY `titre` ; ");
	        	else if(this.date.isSelected())resultset = prep.executeQuery(
	        			"SELECT titre,datejournal,rayonnage,prix FROM `journals` WHERE `titre`='"+titre+
	        			"' and `datejournal`='"+date+"' ORDER BY `datejournal`; ");
	        	
	        	table.setModel(DbUtils.resultSetToTableModel(resultset));
	        	scrollPane.setVisible(true);
	        }
	            catch (Exception e) {
	            	JOptionPane.showMessageDialog(null,
	            			"Veillez verifier votre connexion", "ERREUR	DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
	    			
	        }
	}
}


