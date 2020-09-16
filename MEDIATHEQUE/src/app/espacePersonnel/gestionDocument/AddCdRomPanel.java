package app.espacePersonnel.gestionDocument;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import app.Main;
import app.espaceConsultation.Button;
import media.CdRom;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class AddCdRomPanel extends JPanel implements ActionListener{
	
	private JTextField titreCdRom;
	private JTextField auteur;
	private JTextField dateAjout;
	private JTextField guichet;
	private JTextField prix;
	private JTextField titreLivre1;
	private JTextField dateAjout1;
	
	Connection connection = Main.getConnection();
	JScrollPane tableView = new JScrollPane();
	JTable table = new JTable();
	
	Button bnt_cancel = new Button("Cancel");
	Button btn_submit = new Button("Submit");
	Button btn_chercher = new Button("Chercher");
	Button btn_refresh = new Button("");
	
	public AddCdRomPanel()
	{
		    this.setOpaque(false);
		    this.setPreferredSize(new Dimension(700, 322));
		    this.setLayout(null);
		    
		    JLabel backGround = new JLabel("");
		    backGround.setIcon(new ImageIcon("assets/AddCdRomPanel.png"));
		    backGround.setBounds(0, 0, 700, 322);
		    
		    
		    titreCdRom = new JTextField();
		    titreCdRom.setOpaque(false);
		    titreCdRom.setBorder(null);
		    titreCdRom.setBounds(141, 74, 86, 20);
		    titreCdRom.setColumns(10);
		    
		    auteur = new JTextField();
		    auteur.setOpaque(false);
		    auteur.setColumns(10);
		    auteur.setBorder(null);
		    auteur.setBounds(142, 100, 86, 20);
		    
		    
		    dateAjout = new JTextField();
		    dateAjout.setOpaque(false);
		    dateAjout.setColumns(10);
		    dateAjout.setBorder(null);
		    dateAjout.setBounds(142, 128, 86, 20);
		    
		    
		    guichet = new JTextField();
		    guichet.setOpaque(false);
		    guichet.setColumns(10);
		    guichet.setBorder(null);
		    guichet.setBounds(142, 152, 91, 20);
		    
		    
		    prix = new JTextField();
		    prix.setOpaque(false);
		    prix.setColumns(10);
		    prix.setBorder(null);
		    prix.setBounds(141, 175, 91, 20); 

		    
		    bnt_cancel.setOpaque(false);
		    bnt_cancel.setBorder(null);
		    bnt_cancel.setBounds(79, 220, 74, 18);
		    bnt_cancel.addActionListener(this);
		    
		    		    
		    btn_submit.setOpaque(false);
		    btn_submit.setBorder(null);
		    btn_submit.setBounds(168, 220, 74, 18);
		    btn_submit.addActionListener(this);
		    
		    
		    btn_chercher.setOpaque(false);
		    btn_chercher.setBorder(null);
		    btn_chercher.setBounds(542, 65, 74, 19);
		    btn_chercher.addActionListener(this);    
		    
		    btn_refresh.setBounds(671, 80, 19, 19);
		    btn_refresh.addActionListener(this);
		    
		    
		    titreLivre1 = new JTextField();
		    titreLivre1.setOpaque(false);
		    titreLivre1.setColumns(10);
		    titreLivre1.setBorder(null);
		    titreLivre1.setBounds(425, 41, 91, 20);
		    
		    
		    dateAjout1 = new JTextField();
		    dateAjout1.setOpaque(false);
		    dateAjout1.setColumns(10);
		    dateAjout1.setBorder(null);
		    dateAjout1.setBounds(425, 66, 91, 20);  
		        
		    
		    this.add(titreCdRom);
		    this.add(auteur);
		    this.add(dateAjout);
		    this.add(guichet);
		    this.add(prix);
		    this.add(bnt_cancel);
		    this.add(btn_submit);
		    this.add(btn_chercher);
		    this.add(btn_refresh);
		    this.add(titreLivre1);
		    this.add(dateAjout1);
		    this.add(tableView);
		    this.add(backGround);
		    
		    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    Calendar cal = Calendar.getInstance();
			dateAjout.setText(dateFormat.format(cal.getTime()));
			dateAjout1.setText(dateFormat.format(cal.getTime()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == bnt_cancel) {
			titreCdRom.setText("");
			auteur.setText("");
			guichet.setText("");
			prix.setText("");
			
		}
		if(e.getSource() == btn_refresh) {
			titreLivre1.setText("");
			
			try {
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("SELECT * FROM cdroms");
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				tableView.setBounds(315, 120, 375, 187);
				tableView.getViewport().setBackground(new Color(128,128,128));
			    table.setBackground(new Color(128,128,128));
			    table.setShowHorizontalLines(false);
			    table.setEnabled(false);
			    table.setShowVerticalLines(false);
			    table.setRowHeight(30);
			    tableView.setViewportView(table);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		if(e.getSource() == btn_submit) {
			try {
				new CdRom(titreCdRom.getText(), Double.parseDouble(prix.getText()),auteur.getText(),Integer.parseInt(guichet.getText())).addCdrom();
			}
			catch(Exception e1){	
				JOptionPane.showMessageDialog(null, "Not saved");
			}
		}
		if(e.getSource() == btn_chercher) {
			try {
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM cdroms WHERE titre = ? && dateAjout = ?");
				ps.setString(1, titreLivre1.getText());
				ps.setDate(2,new java.sql.Date(Calendar.getInstance().getTime().getTime()));
				ResultSet rs = ps.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				tableView.setBounds(315, 120, 375, 187);
				tableView.getViewport().setBackground(new Color(128,128,128));
			    table.setBackground(new Color(128,128,128));
			    table.setShowHorizontalLines(false);
			    table.setShowVerticalLines(false);
			    table.setRowHeight(30);
			    tableView.setViewportView(table);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
