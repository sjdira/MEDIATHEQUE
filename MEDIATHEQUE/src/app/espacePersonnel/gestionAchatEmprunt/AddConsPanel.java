package app.espacePersonnel.gestionAchatEmprunt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import app.Main;
import app.espaceConsultation.Button;
import media.Consultation;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class AddConsPanel extends JPanel implements ActionListener
{
	private JTextField codeLecteur;
	private JTextField idExemplaire;
	private JTextField idScreen;
	private JTextField codeLecteur1;
	private JTextField idExemplaire1;
	private JTextField codeLecteur2;
	private JTextField idExemplaire2;
	private Button btn_submit = new Button("Submit");
	private Button btn_cancel = new Button("Cancel");
	private Button chercher = new Button("Chercher");
	private Button btn_modify = new Button("Modify");
	private Button refresh = new Button("");
	
	JScrollPane tableView = new JScrollPane();
	JTable table=new JTable();
	Connection connection = Main.getConnection();
	
	public AddConsPanel()
	{
	 this.setOpaque(false);
	 this.setPreferredSize(new Dimension(900,322));
	 this.setLayout(null);
	 
	 JLabel backGround = new JLabel("");
	 backGround.setIcon(new ImageIcon("assets/AddConPanel.png"));
	 backGround.setBounds(0, 0, 900, 322);
	 
	 
	 codeLecteur = new JTextField();
	 codeLecteur.setOpaque(false);
	 codeLecteur.setBorder(null);
	 codeLecteur.setBounds(178, 48, 114, 20);	 
	 codeLecteur.setColumns(10);
	 
	 idExemplaire = new JTextField();
	 idExemplaire.setOpaque(false);
	 idExemplaire.setColumns(10);
	 idExemplaire.setBorder(null);
	 idExemplaire.setBounds(178, 86, 114, 20);

	 idScreen = new JTextField();
	 idScreen.setOpaque(false);
	 idScreen.setColumns(10);
	 idScreen.setBorder(null);
	 idScreen.setBounds(178, 121, 114, 20);

	 
	 btn_cancel.setBounds(114, 158, 95, 20);
	 btn_cancel.addActionListener(this);
	 
	 btn_submit.setBounds(228, 160, 93, 18);
	 btn_submit.addActionListener(this);
	 
	 codeLecteur1 = new JTextField();
	 codeLecteur1.setOpaque(false);
	 codeLecteur1.setColumns(10);
	 codeLecteur1.setBorder(null);
	 codeLecteur1.setBounds(178, 193, 114, 20);

	 
	 idExemplaire1 = new JTextField();
	 idExemplaire1.setOpaque(false);
	 idExemplaire1.setColumns(10);
	 idExemplaire1.setBorder(null);
	 idExemplaire1.setBounds(178, 225, 114, 20);

	 
	 codeLecteur2 = new JTextField();
	 codeLecteur2.setOpaque(false);
	 codeLecteur2.setColumns(10);
	 codeLecteur2.setBorder(null);
	 codeLecteur2.setBounds(555, 48, 114, 20);

	 
	 idExemplaire2 = new JTextField();
	 idExemplaire2.setOpaque(false);
	 idExemplaire2.setColumns(10);
	 idExemplaire2.setBorder(null);
	 idExemplaire2.setBounds(555, 73, 114, 20);

	 
	 chercher.setBounds(780, 52, 95, 20);
	 chercher.addActionListener(this);
	 
	 btn_modify.setBounds(228, 269, 93, 18);
	 btn_modify.addActionListener(this);
	 
	 refresh.setBounds(861, 110, 19, 20);
	 refresh.addActionListener(this);
	 
	 add(codeLecteur);
	 add(idExemplaire);
	 add(idScreen);
	 add(btn_cancel);
	 add(btn_submit);
	 add(codeLecteur1);
	 add(idExemplaire1);
	 add(codeLecteur2);
	 add(idExemplaire2);
	 add(chercher);
	 add(refresh);
	 add(btn_modify);
	 add(tableView);
	 add(backGround);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btn_cancel) {
			codeLecteur.setText("");
			idExemplaire.setText("");
			idScreen.setText("");
		}
		
		if (e.getSource() == btn_submit) {
			Consultation consultation = new Consultation(Integer.parseInt(codeLecteur.getText()), Integer.parseInt(idExemplaire.getText()), Integer.parseInt(idScreen.getText()));
			consultation.addConsultation();
		}
		
		if (e.getSource() == btn_modify) {
			new Consultation(Integer.parseInt(codeLecteur1.getText()), Integer.parseInt(idExemplaire1.getText()), 0).retourConsultation();
		}
		
		if (e.getSource() == chercher) {
			tableView.setBounds(400, 140, 485, 200);
		    table.setBackground(new Color(128,128,128));
		    table.setShowHorizontalLines(false);
		    table.setShowVerticalLines(false);
		    table.setRowHeight(30);
		    table.setEnabled(false);
		    tableView.setViewportView(table);
		    tableView.setVisible(true);
		    
			if(codeLecteur2.getText().isBlank() || idExemplaire2.getText().isBlank()) {       //Returns true if the string is empty or contains only white space codepoints.
				try {
					Statement s = connection.createStatement();
					ResultSet rs = s.executeQuery("SELECT idConsultation, codeLecteur, consultations.idUser, idExemplaire, idEcran, dateConsultation FROM consultations, users WHERE consultations.idUser = users.idUser");
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				try {
					PreparedStatement s = connection.prepareStatement("SELECT idConsultation, codeLecteur, consultations.idUser, idExemplaire, idEcran, dateConsultation FROM consultations, users WHERE consultations.idUser = users.idUser && codeLecteur = ? && idExemplaire = ?");
					s.setInt(1, Integer.parseInt(codeLecteur2.getText()));
					s.setInt(2, Integer.parseInt(idExemplaire2.getText()));
					ResultSet rs = s.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Entrer un code Lecteur et un id Exemplaire entier");
				}
			}
		}
		
		if(e.getSource() == refresh) {
			codeLecteur2.setText("");
			
			try {
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("SELECT idConsultation, codeLecteur, consultations.idUser, idExemplaire, idEcran, dateConsultation FROM consultations, users WHERE consultations.idUser = users.idUser");
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				tableView.setBounds(400, 140, 485, 200);
			    table.setBackground(new Color(128,128,128));
			    table.setShowHorizontalLines(false);
			    table.setShowVerticalLines(false);
			    table.setRowHeight(30);
			    table.setEnabled(false);
			    tableView.setViewportView(table);
			    tableView.setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
