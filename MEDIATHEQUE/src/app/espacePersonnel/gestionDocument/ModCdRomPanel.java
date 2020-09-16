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

import app.Main;
import app.espaceConsultation.Button;
import media.CdRom;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class ModCdRomPanel extends JPanel implements ActionListener{
	private JTextField idCdRom;
	private JTextField titreCdRom;
	private JTextField auteur;
	private JTextField dateAjout;
	private JTextField idCdRom1;
	private JTextField guichet;
	private JTextField prix;
	private Button btn_submit1;
	
	Button bnt_cancel = new Button("Cancel");
	Button btn_modify = new Button("Modify");
	Button btn_chercher = new Button("Chercher");
	Button btn_refresh = new Button("");
	Connection connection = Main.getConnection();
	
	JScrollPane tableView = new JScrollPane();
	JTable table = new JTable();
	
	
	public ModCdRomPanel()
	{
		this.setOpaque(false);
	    this.setPreferredSize(new Dimension(700,322));
	    this.setLayout(null);
	    
	    JLabel backGround = new JLabel("New label");
	    backGround.setIcon(new ImageIcon("assets/ModCdRomPanel.png"));
	    backGround.setBounds(0, 0, 700, 322);
	    
	    
	    idCdRom = new JTextField();
	    idCdRom.setOpaque(false);
	    idCdRom.setBorder(null);
	    idCdRom.setBounds(122, 46, 86, 20);
	    idCdRom.setColumns(10);
	    
	    titreCdRom = new JTextField();
	    titreCdRom.setOpaque(false);
	    titreCdRom.setColumns(10);
	    titreCdRom.setBorder(null);
	    titreCdRom.setBounds(136, 108, 86, 20);

	    auteur = new JTextField();
	    auteur.setOpaque(false);
	    auteur.setColumns(10);
	    auteur.setBorder(null);
	    auteur.setBounds(136, 134, 91, 20);

	    dateAjout = new JTextField();
	    dateAjout.setOpaque(false);
	    dateAjout.setColumns(10);
	    dateAjout.setBorder(null);
	    dateAjout.setBounds(136, 161, 91, 20);

	   
	    
	    bnt_cancel.setOpaque(false);
	    bnt_cancel.setBorder(null);
	    bnt_cancel.setBounds(79, 240, 74, 18);
	    bnt_cancel.addActionListener(this);
	    
	    btn_modify.setOpaque(false);
	    btn_modify.setBorder(null);
	    btn_modify.setBounds(168, 240, 73, 18);
	    btn_modify.addActionListener(this);

	    btn_chercher.setOpaque(false);
	    btn_chercher.setBorder(null);
	    btn_chercher.setBounds(531, 67, 73, 19);
	    btn_chercher.addActionListener(this);
	    
	    btn_refresh.setBorder(null);
	    btn_refresh.setOpaque(false);
	    btn_refresh.setBounds(666, 79, 19, 19);
	    btn_refresh.addActionListener(this);

	    idCdRom1 = new JTextField();
	    idCdRom1.setOpaque(false);
	    idCdRom1.setColumns(10);
	    idCdRom1.setBorder(null);
	    idCdRom1.setBounds(418, 41, 86, 20);

	    guichet = new JTextField();
	    guichet.setOpaque(false);
	    guichet.setColumns(10);
	    guichet.setBorder(null);
	    guichet.setBounds(137, 206, 86, 20);

	    prix = new JTextField();
	    prix.setOpaque(false);
	    prix.setColumns(10);
	    prix.setBorder(null);
	    prix.setBounds(136, 185, 91, 20);

	    btn_submit1 = new Button("Submit");
	    btn_submit1.setOpaque(false);
	    btn_submit1.setBorder(null);
	    btn_submit1.setBounds(164, 77, 74, 18);
	    btn_submit1.addActionListener(this);
	    
	    this.add(idCdRom);
	    this.add(titreCdRom);
	    this.add(auteur);
	    this.add(dateAjout);
	    this.add(bnt_cancel);
	    this.add(btn_submit1);
	    this.add(btn_modify);
	    this.add(btn_chercher);
	    this.add(btn_refresh);
	    this.add(idCdRom1);
	    this.add(guichet);
	    this.add(prix);
	    this.add(tableView);
	    this.add(backGround);
	    
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == bnt_cancel) {
			idCdRom.setText("");
			titreCdRom.setText("");
			auteur.setText("");
			guichet.setText("");
			prix.setText("");
			dateAjout.setText("");
			
		}
		if(e.getSource() == btn_refresh) {
			idCdRom1.setText("");
			
			try {
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("SELECT * FROM cdroms");
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
		
		if(e.getSource() == btn_submit1) {
			try {
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM cdroms WHERE idCdRom = ?");
				ps.setInt(1,  Integer.parseInt(idCdRom.getText()));
				ResultSet rs = ps.executeQuery();
				rs.next();
				titreCdRom.setText(rs.getString(2));
				auteur.setText("" + rs.getString(3));
				dateAjout.setText("" + rs.getDate(4));
				guichet.setText("" + rs.getInt(5));				
				prix.setText("" + rs.getFloat(6));		
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Entrer un ID CdRom");
			}
		}
		
		if(e.getSource() == btn_modify) {
			try {	
				CdRom cd = new CdRom("",0.0,"",0);
				
				if(!titreCdRom.getText().isEmpty()) {
					cd.modCdrom("titre", titreCdRom.getText(), Integer.parseInt(idCdRom.getText()));
				}
				
				if(!auteur.getText().isEmpty()){
					cd.modCdrom("auteur", auteur.getText(), Integer.parseInt(idCdRom.getText()));
				}
				
				if(!guichet.getText().isEmpty()){
					cd.modCdrom("guichet",Integer.parseInt(guichet.getText()), Integer.parseInt(idCdRom.getText()));
				}
				
				if(!prix.getText().isEmpty()){
					cd.modCdrom("prix", Double.parseDouble(prix.getText()), Integer.parseInt(idCdRom.getText()));
				}
				JOptionPane.showMessageDialog(null, "successfully update");
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(null, "Not Update");
			}
		}
		
		if(e.getSource() == btn_chercher) {    
			if(idCdRom1.getText().isBlank()) { 
				try {
					Statement s = connection.createStatement();
					ResultSet rs = s.executeQuery("SELECT * FROM cdroms");
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
					PreparedStatement ps = connection.prepareStatement("SELECT * FROM cdroms WHERE idCdRom = ?");
					ps.setInt(1,  Integer.parseInt(idCdRom1.getText()));
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
					JOptionPane.showMessageDialog(null, "Entrer un Id CdRom");
				}
			}
		}
		
	}
}
