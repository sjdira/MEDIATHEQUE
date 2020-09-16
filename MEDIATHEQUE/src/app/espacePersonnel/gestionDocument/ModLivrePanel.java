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
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import app.espaceConsultation.Button;
import media.Livre;
import net.proteanit.sql.DbUtils;
import app.Main;

@SuppressWarnings("serial")
public class ModLivrePanel extends JPanel implements ActionListener{
	//
	private JTextField idLivre;
	private JTextField titreLivre;
	private JTextField auteur;
	private JTextField dateAjout;
	private JTextField idLivre1;
	private JTextField rayonnage;
	private JTextField prix;
	private Button btn_submit1;
	
	Button bnt_cancel = new Button("Cancel");
	Button btn_modify = new Button("Modify");
	Button btn_chercher = new Button("Chercher");
	Button btn_refresh = new Button("");
	Connection connection = Main.getConnection();
	
	JScrollPane tableView = new JScrollPane();
	JTable table = new JTable();
	
	public ModLivrePanel()
	{
		this.setOpaque(false);
	    this.setPreferredSize(new Dimension(700,322));
	    this.setLayout(null);
	    
	    JLabel backGround = new JLabel("New label");
	    backGround.setIcon(new ImageIcon("assets/ModLivrePanel.png"));
	    backGround.setBounds(0, 0, 700, 322);
	    
	    
	    idLivre = new JTextField();
	    idLivre.setOpaque(false);
	    idLivre.setBorder(null);
	    idLivre.setBounds(147, 34, 86, 20);
	    idLivre.setColumns(10);
	    
	    titreLivre = new JTextField();
	    titreLivre.setOpaque(false);
	    titreLivre.setColumns(10);
	    titreLivre.setBorder(null);
	    titreLivre.setBounds(147, 110, 86, 20);

	    auteur = new JTextField();
	    auteur.setOpaque(false);
	    auteur.setColumns(10);
	    auteur.setBorder(null);
	    auteur.setBounds(147, 136, 86, 20);
	   
	    dateAjout = new JTextField();
	    dateAjout.setOpaque(false);
	    dateAjout.setColumns(10);
	    dateAjout.setBorder(null);
	    dateAjout.setBounds(147, 165, 91, 20);
	    
	    
	    bnt_cancel.setOpaque(false);
	    bnt_cancel.setBorder(null);
	    bnt_cancel.setBounds(84, 263, 74, 18);
	    bnt_cancel.addActionListener(this);
	    
	    	    
	    btn_modify.setOpaque(false);
	    btn_modify.setBorder(null);
	    btn_modify.setBounds(172, 263, 74, 18);
	    btn_modify.addActionListener(this);
	    
	    
	    btn_chercher.setOpaque(false);
	    btn_chercher.setBorder(null);
	    btn_chercher.setBounds(542, 67, 73, 19);
	    btn_chercher.addActionListener(this);
	    
	    
	    btn_refresh.setBorder(null);
	    btn_refresh.setOpaque(false);
	    btn_refresh.setBounds(670, 83, 20, 20);
	    btn_refresh.addActionListener(this);
	    
	    idLivre1 = new JTextField();
	    idLivre1.setOpaque(false);
	    idLivre1.setColumns(10);
	    idLivre1.setBorder(null);
	    idLivre1.setBounds(423, 44, 86, 20);
	    
	    
	    rayonnage = new JTextField();
	    rayonnage.setOpaque(false);
	    rayonnage.setColumns(10);
	    rayonnage.setBorder(null);
	    rayonnage.setBounds(147, 192, 86, 20);
	    
	    
	    prix = new JTextField();
	    prix.setOpaque(false);
	    prix.setColumns(10);
	    prix.setBorder(null);
	    prix.setBounds(147, 215, 91, 20);
	    
	    
	    btn_submit1 = new Button("Submit");
	    btn_submit1.setOpaque(false);
	    btn_submit1.setBorder(null);
	    btn_submit1.setBounds(172, 68, 74, 18);
	    btn_submit1.addActionListener(this);
	    
	    this.add(idLivre);
	    this.add(titreLivre);
	    this.add(auteur);
	    this.add(dateAjout);
	    this.add(bnt_cancel);
	    this.add(btn_modify);
	    this.add(btn_chercher);
	    this.add(btn_refresh);
	    this.add(idLivre1);
	    this.add(rayonnage);
	    this.add(prix);
	    this.add(btn_submit1);
	    this.add(tableView);
	    this.add(backGround);
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == bnt_cancel) {
			idLivre.setText("");
			titreLivre.setText("");
			auteur.setText("");
			rayonnage.setText("");
			prix.setText("");
			dateAjout.setText("");
		}
		
		if(e.getSource() == btn_refresh) {
			idLivre1.setText("");
			
			try {
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("SELECT * FROM livres");
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				tableView.setBounds(315, 120, 375, 187);
				tableView.getViewport().setBackground(new Color(128,128,128));
			    table.setBackground(new Color(128,128,128));
			    table.setShowHorizontalLines(false);
			    table.setShowVerticalLines(false);
			    table.setEnabled(false);
			    table.setRowHeight(30);
			    tableView.setViewportView(table);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == btn_modify) {
			try
			{
				Livre livre = new Livre("",0.0,"", "");
				
				if(!titreLivre.getText().isEmpty()) {
					livre.modLivre("titre", titreLivre.getText(), Integer.parseInt(idLivre.getText()));
				}
				
				if(!auteur.getText().isEmpty()){
					livre.modLivre("auteur", auteur.getText(), Integer.parseInt(idLivre.getText()));
				}
				
				if(!rayonnage.getText().isEmpty()){
					livre.modLivre("rayonnage", rayonnage.getText(), Integer.parseInt(idLivre.getText()));
				}
				
				if(!prix.getText().isEmpty()){
					livre.modLivre("prix", Double.parseDouble(prix.getText()), Integer.parseInt(idLivre.getText()));
				}
				JOptionPane.showMessageDialog(null, "successfully update");
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(null, "Not Update");
			}
		}
		
		if(e.getSource() == btn_submit1) {
			try {
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM livres WHERE idLivre = ?");
				ps.setInt(1,  Integer.parseInt(idLivre.getText()));
				ResultSet rs = ps.executeQuery();
				rs.next();
				titreLivre.setText(rs.getString(2));
				auteur.setText(rs.getString(3));
				rayonnage.setText("" + rs.getString(4));
				dateAjout.setText("" + rs.getDate(5));
				prix.setText("" + rs.getFloat(6));
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Entrer un ID Livre");
			}
		}
		
		if(e.getSource() == btn_chercher) {
			if(idLivre1.getText().isBlank()) { 
				try {
					Statement s = connection.createStatement();
					ResultSet rs = s.executeQuery("SELECT * FROM livres");
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
			else {
				try {
					PreparedStatement ps = connection.prepareStatement("SELECT * FROM livres WHERE idLivre = ?");
					ps.setInt(1,  Integer.parseInt(idLivre1.getText()));
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					tableView.setBounds(315, 120, 375, 187);
					tableView.getViewport().setBackground(new Color(128,128,128));
				    table.setBackground(new Color(128,128,128));
				    table.setShowHorizontalLines(false);
				    table.setShowVerticalLines(false);
				    table.setRowHeight(30);
				    tableView.setViewportView(table);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Entrer un ID Livre");
				}
			}
		}
	}
	
}
