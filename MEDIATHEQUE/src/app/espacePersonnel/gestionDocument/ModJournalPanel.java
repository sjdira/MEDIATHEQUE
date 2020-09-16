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
import media.Journal;
import net.proteanit.sql.DbUtils;
import app.Main;

@SuppressWarnings("serial")
public class ModJournalPanel extends JPanel implements ActionListener{
	
	private JTextField idJournal;
	private JTextField titreJournal;
	private JTextField dateAjout;
	private JTextField dateDuJournal;
	private JTextField idJournal1;
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
	
	public ModJournalPanel()
	{
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(700,322));
	    this.setLayout(null);
	    
	    JLabel backGround = new JLabel("New label");
	    backGround.setIcon(new ImageIcon("assets/ModJournalPanel.png"));
	    backGround.setBounds(0, 0, 700, 322);
	    
	    
	    idJournal = new JTextField();
	    idJournal.setOpaque(false);
	    idJournal.setBorder(null);
	    idJournal.setBounds(122, 46, 86, 20);
	    idJournal.setColumns(10);
	    
	    titreJournal = new JTextField();
	    titreJournal.setOpaque(false);
	    titreJournal.setColumns(10);
	    titreJournal.setBorder(null);
	    titreJournal.setBounds(136, 108, 86, 20);

	    dateAjout = new JTextField();
	    dateAjout.setOpaque(false);
	    dateAjout.setColumns(10);
	    dateAjout.setBorder(null);
	    dateAjout.setBounds(136, 133, 91, 20);

	    dateDuJournal = new JTextField();
	    dateDuJournal.setOpaque(false);
	    dateDuJournal.setColumns(10);
	    dateDuJournal.setBorder(null);
	    dateDuJournal.setBounds(136, 161, 91, 20);

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

	    idJournal1 = new JTextField();
	    idJournal1.setOpaque(false);
	    idJournal1.setColumns(10);
	    idJournal1.setBorder(null);
	    idJournal1.setBounds(418, 41, 86, 20);

	    rayonnage = new JTextField();
	    rayonnage.setOpaque(false);
	    rayonnage.setColumns(10);
	    rayonnage.setBorder(null);
	    rayonnage.setBounds(137, 206, 86, 20);

	    prix = new JTextField();
	    prix.setOpaque(false);
	    prix.setColumns(10);
	    prix.setBorder(null);
	    prix.setBounds(137, 185, 91, 20);

	    btn_submit1 = new Button("Submit");
	    btn_submit1.setOpaque(false);
	    btn_submit1.setBorder(null);
	    btn_submit1.setBounds(164, 77, 74, 18);
	    btn_submit1.addActionListener(this);
	    
	    this.add(idJournal);
	    this.add(titreJournal);
	    this.add(dateAjout);
	    this.add(dateDuJournal);
	    this.add(btn_submit1);
	    this.add(bnt_cancel);
	    this.add(btn_modify);
	    this.add(btn_chercher);
	    this.add(btn_refresh);
	    this.add(idJournal1);
	    this.add(rayonnage);
	    this.add(prix);
	    this.add(tableView);
	    this.add(backGround);
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bnt_cancel) {
			idJournal.setText("");
			titreJournal.setText("");
			rayonnage.setText("");
			prix.setText("");
			dateAjout.setText("");
			dateDuJournal.setText("");
		}
		
		if(e.getSource() == btn_refresh) {
			idJournal1.setText("");
			
			try {
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("SELECT * FROM journals");
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				tableView.setBounds(315, 120, 375, 187);
				tableView.getViewport().setBackground(new Color(128,128,128));
			    table.setBackground(new Color(128,128,128));
			    table.setShowHorizontalLines(false);
			    table.setShowVerticalLines(false);
			    table.setRowHeight(30);
			    table.setEnabled(false);
			    tableView.setViewportView(table);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == btn_modify) {
			try{	
				Journal journal = new Journal ("","",0);
				
				if(!(titreJournal.getText().isEmpty())) {
					journal.modJournal("titre", titreJournal.getText(), Integer.parseInt(idJournal.getText()));
				}
				
				if(!(rayonnage.getText().isEmpty())){
					journal.modJournal("rayonnage", rayonnage.getText(), Integer.parseInt(idJournal.getText()));
				}
				
				if(!(prix.getText().isEmpty())){
					journal.modJournal("prix", Double.parseDouble(prix.getText()), Integer.parseInt(idJournal.getText()));
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
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM journals WHERE idJournal = ?");
				ps.setInt(1,  Integer.parseInt(idJournal.getText()));
				ResultSet rs = ps.executeQuery();
				rs.next();
				titreJournal.setText(rs.getString(2));
				dateAjout.setText("" + rs.getString(3));
				dateDuJournal.setText("" + rs.getDate(4));
				rayonnage.setText("" + rs.getString(5));				
				prix.setText("" + rs.getFloat(6));			
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Entrer un ID Journal");
			}
		}
		
		if(e.getSource() == btn_chercher) {
			if(idJournal1.getText().isBlank()) { 
				try {
					Statement s = connection.createStatement();
					ResultSet rs = s.executeQuery("SELECT * FROM journals");
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
					PreparedStatement ps = connection.prepareStatement("SELECT * FROM journals WHERE idJournal = ?");
					ps.setInt(1,  Integer.parseInt(idJournal1.getText()));
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
					JOptionPane.showMessageDialog(null, "Entrer un ID Journal");
				}
			}
		}
		
	}
}
