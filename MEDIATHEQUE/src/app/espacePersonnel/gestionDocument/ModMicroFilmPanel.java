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
import media.MicroFilm;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class ModMicroFilmPanel extends JPanel implements ActionListener{
	
	private JTextField idMicroFilm;
	private JTextField titreMicroFilm;
	private JTextField dateAjout;
	private JTextField guichet;
	private JTextField idMicroFilm1;
	private JTextField prix;
	private Button btn_submit1;
	Button bnt_cancel = new Button("Cancel");
	Button btn_modify = new Button("Modify");
	Button btn_chercher = new Button("Chercher");
	Button btn_refresh = new Button("");
	
	Connection connection = Main.getConnection();
	
	JScrollPane tableView = new JScrollPane();
	JTable table = new JTable();
	
	public ModMicroFilmPanel()
	{
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(700,322));
		this.setLayout(null);
	    
	    JLabel backGround = new JLabel("New label");
	    backGround.setIcon(new ImageIcon("assets/ModMicroFilmPanel.png"));
	    backGround.setBounds(0, 0, 700, 322);
	    
	    
	    idMicroFilm = new JTextField();
	    idMicroFilm.setOpaque(false);
	    idMicroFilm.setBorder(null);
	    idMicroFilm.setBounds(122, 46, 86, 20);
	    idMicroFilm.setColumns(10);
	    
	    titreMicroFilm = new JTextField();
	    titreMicroFilm.setOpaque(false);
	    titreMicroFilm.setColumns(10);
	    titreMicroFilm.setBorder(null);
	    titreMicroFilm.setBounds(143, 118, 86, 20);

	    dateAjout = new JTextField();
	    dateAjout.setOpaque(false);
	    dateAjout.setColumns(10);
	    dateAjout.setBorder(null);
	    dateAjout.setBounds(143, 144, 91, 20);

	    guichet = new JTextField();
	    guichet.setOpaque(false);
	    guichet.setColumns(10);
	    guichet.setBorder(null);
	    guichet.setBounds(143, 172, 91, 20);

	    
	    
	    bnt_cancel.setOpaque(false);
	    bnt_cancel.setBorder(null);
	    bnt_cancel.setBounds(82, 236, 74, 18);
	    bnt_cancel.addActionListener(this);
	    
	    btn_modify.setOpaque(false);
	    btn_modify.setBorder(null);
	    btn_modify.setBounds(169, 236, 73, 18);
	    btn_modify.addActionListener(this);
	   
	    btn_chercher.setOpaque(false);
	    btn_chercher.setBorder(null);
	    btn_chercher.setBounds(532, 67, 73, 19);
	    btn_chercher.addActionListener(this);

	    
	    btn_refresh.setBorder(null);
	    btn_refresh.setOpaque(false);
	    btn_refresh.setBounds(666, 77, 19, 19);
	    btn_refresh.addActionListener(this);

	    idMicroFilm1 = new JTextField();
	    idMicroFilm1.setOpaque(false);
	    idMicroFilm1.setColumns(10);
	    idMicroFilm1.setBorder(null);
	    idMicroFilm1.setBounds(423, 39, 86, 20);

	    prix = new JTextField();
	    prix.setOpaque(false);
	    prix.setColumns(10);
	    prix.setBorder(null);
	    prix.setBounds(144, 196, 86, 20);

	    btn_submit1 = new Button("Submit");
	    btn_submit1.setOpaque(false);
	    btn_submit1.setBorder(null);
	    btn_submit1.setBounds(167, 76, 74, 18);
	    btn_submit1.addActionListener(this);
	    
	    this.add(idMicroFilm);
	    this.add(titreMicroFilm);
	    this.add(dateAjout);
	    this.add(guichet);
	    this.add(bnt_cancel);
	    this.add(btn_modify);
	    this.add(btn_chercher);
	    this.add(btn_refresh);
	    this.add(idMicroFilm1);
	    this.add(prix);
	    this.add(btn_submit1);
	    this.add(tableView);
	    this.add(backGround);
	    	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == bnt_cancel) {
			idMicroFilm.setText("");
			titreMicroFilm.setText("");
			guichet.setText("");
			prix.setText("");
			dateAjout.setText("");
		}
		
		if(e.getSource() == btn_refresh) {
			idMicroFilm1.setText("");
			
			try {
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("SELECT * FROM microfilms");
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
		
		if(e.getSource() == btn_submit1) {
			try {
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM microfilms WHERE idMicroFilm = ?");
				ps.setInt(1,  Integer.parseInt(idMicroFilm.getText()));
				ResultSet rs = ps.executeQuery();
				rs.next();
				titreMicroFilm.setText(rs.getString(2));
				dateAjout.setText("" + rs.getDate(3));
				guichet.setText("" + rs.getInt(4));
				prix.setText("" + rs.getFloat(5));
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Entrer un ID MicroFilm");
			}
		}
		
		if(e.getSource() == btn_modify) {
			try {
				MicroFilm cd = new MicroFilm("",0.0,0);
				
				if(!titreMicroFilm.getText().isEmpty()) {
					cd.modMicroFilm("titre", titreMicroFilm.getText(), Integer.parseInt(idMicroFilm.getText()));
				}
				
				if(!guichet.getText().isEmpty()){
					cd.modMicroFilm("guichet", Integer.parseInt(guichet.getText()), Integer.parseInt(idMicroFilm.getText()));
				}
				
				if(!prix.getText().isEmpty()){
					cd.modMicroFilm("prix", Double.parseDouble(prix.getText()), Integer.parseInt(idMicroFilm.getText()));
				}
				JOptionPane.showMessageDialog(null, "successfully update");
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(null, "Not Update");
			}
		}
		
		if(e.getSource() == btn_chercher) {
		    
			if(idMicroFilm1.getText().isBlank()) { 
				try {
					Statement s = connection.createStatement();
					ResultSet rs = s.executeQuery("SELECT * FROM microfilms");
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
					PreparedStatement ps = connection.prepareStatement("SELECT * FROM microfilms WHERE idMicroFilm = ?");
					ps.setInt(1, Integer.parseInt(idMicroFilm1.getText()));
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
					JOptionPane.showMessageDialog(null, "Entrer un ID MicroFilm");
				}
			}
		}
		
	}
}
