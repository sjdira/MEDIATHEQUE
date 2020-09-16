package app.espacePersonnel.gestionAchatEmprunt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import app.Main;
import app.espaceConsultation.Button;
import media.Achat;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class AddAchatPanel extends JPanel implements ActionListener{

	private JTextField codeLecteur;
	private JTextField idExemplaire;
	private JTextField dateAchat;
	private JTextField codeLecteur1;
	Button btn_cancel = new Button("Cancel");
	Button btn_submit = new Button("Submit");
	Button btn_chercher = new Button("Chercher");
	Button btn_refresh = new Button("");
	Button btn_telecharger = new Button("Telecharger");
	JTable table=new JTable();
	JScrollPane tableView = new JScrollPane(table);
	Connection connection = Main.getConnection();
	
	JTextArea FicheAchat = new JTextArea(" ");
	String ficheAchat;
	
	public AddAchatPanel()
	{
	    this.setOpaque(false);
		this.setPreferredSize(new Dimension(900,322));
	    this.setLayout(null);
	    
	    FicheAchat.setBounds(550, 116, 300, 200);
	    FicheAchat.setEditable(false);
	    FicheAchat.setFont(new Font("Tahoma", Font.BOLD, 14));
	    FicheAchat.setForeground(Color.BLACK);
	    FicheAchat.setVisible(false);
	    FicheAchat.setOpaque(false);
	    FicheAchat.setLineWrap(true);
	    
	    JLabel backGround = new JLabel("");
	    backGround.setIcon(new ImageIcon("assets/AddAchatPanel.png"));
	    backGround.setBounds(0, 0, 900, 322);
	    
	    
	    codeLecteur = new JTextField();
	    codeLecteur.setOpaque(false);
	    codeLecteur.setBorder(null);
	    codeLecteur.setBounds(179, 97, 112, 20);    
	    codeLecteur.setColumns(10);
	    
	    idExemplaire = new JTextField();
	    idExemplaire.setBorder(null);
	    idExemplaire.setOpaque(false);
	    idExemplaire.setColumns(10);
	    idExemplaire.setBounds(179, 134, 112, 20);
	    
	    
	    dateAchat = new JTextField();
	    dateAchat.setBorder(null);
	    dateAchat.setOpaque(false);
	    dateAchat.setColumns(10);
	    dateAchat.setBounds(179, 177, 112, 20);
	    
	    
	    btn_cancel.setBounds(112, 236, 94, 20);
	    btn_cancel.addActionListener(this);
	    
	    btn_submit.setBounds(223, 236, 94, 20);
	    btn_submit.addActionListener(this);
	    
	    btn_telecharger.setBounds(600, 285, 120, 20);
	    btn_telecharger.setVisible(false);
	    btn_telecharger.setBorderPainted(true);
	    btn_telecharger.setContentAreaFilled(false);
	    btn_telecharger.setFocusPainted(false);
	    btn_telecharger.setBorder(new LineBorder(Color.BLACK));
	    btn_telecharger.addActionListener(this);
	    
	    codeLecteur1 = new JTextField();
	    codeLecteur1.setOpaque(false);
	    codeLecteur1.setBorder(null);
	    codeLecteur1.setBounds(554, 46, 112, 20);    
	    codeLecteur1.setColumns(10);
	    
	    btn_chercher.setBounds(775, 50, 93, 19);
	    btn_chercher.addActionListener(this); 
	   
	    btn_refresh.setBounds(850, 88, 18, 18);
	    btn_refresh.addActionListener(this);
	    
	    
	    this.add(codeLecteur);
	    this.add(idExemplaire);
	    this.add(dateAchat);
	    this.add(btn_cancel);
	    this.add(btn_submit);
	    this.add(codeLecteur1);
	    this.add(btn_chercher);
	    this.add(btn_refresh);
	    this.add(btn_telecharger);
	    this.add(FicheAchat);
	    this.add(tableView);
	    this.add(backGround);
	    
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar cal = Calendar.getInstance();
		dateAchat.setText(dateFormat.format(cal.getTime()));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == btn_cancel) {
			codeLecteur.setText("");
			idExemplaire.setText("");
		}
		
		if(e.getSource() == btn_refresh) {
			codeLecteur1.setText("");
			
			try {
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("SELECT idAchat, codeLecteur, achats.idUser, idExemplaire, dateAchat FROM achats, users WHERE achats.idUser = users.idUser");
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				btn_telecharger.setVisible(false);
				FicheAchat.setVisible(false);
				tableView.setBounds(400, 116, 485, 200);
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
		
		if (e.getSource() == btn_submit) {
			Achat achat = new Achat(Integer.parseInt(codeLecteur.getText()), Integer.parseInt(idExemplaire.getText()));
			achat.buyDoc();
			tableView.setVisible(false);
			ficheAchat = achat.genereFicheAchat();
			FicheAchat.setText(achat.genereFicheAchat());
			FicheAchat.setVisible(true);
			btn_telecharger.setVisible(true);
		}
		
		if (e.getSource() == btn_telecharger)
		{
	   		try {
	   			Document doc = new Document();
				PdfWriter.getInstance(doc, new FileOutputStream(".\\ficheAchat\\ficheAchat.pdf"));
				doc.open();
				Paragraph para1 = new Paragraph("Fiche d'achat");
	   			para1.setAlignment(Paragraph.ALIGN_CENTER);
				Paragraph para2 = new Paragraph("-----------------------------------------------------------------------------------------\n");
	   			para2.setAlignment(Paragraph.ALIGN_CENTER);
				Paragraph para = new Paragraph(ficheAchat);
				para.setAlignment(Paragraph.ALIGN_CENTER);
				doc.add(para1);
				doc.add(para2);
				doc.add(para);
				doc.close();
				JOptionPane.showMessageDialog(null, "La fiche est bien enregistrer dans le dossier ficheAchat");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Not saved");
			}
		}
		
		if (e.getSource() == btn_chercher) {
			btn_telecharger.setVisible(false);
			FicheAchat.setVisible(false);
			tableView.setBounds(400, 116, 485, 200);
		    table.setBackground(new Color(128,128,128));
		    table.setShowHorizontalLines(false);
		    table.setShowVerticalLines(false);
		    table.setRowHeight(30);
		    tableView.setViewportView(table);
		    tableView.setVisible(true);
		    
			if(codeLecteur1.getText().isBlank()) {       //Returns true if the string is empty or contains only white space codepoints.			
				try {
					Statement s = connection.createStatement();
					ResultSet rs = s.executeQuery("SELECT idAchat, codeLecteur, achats.idUser, idExemplaire, dateAchat FROM achats, users WHERE achats.idUser = users.idUser");
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				try {	
					PreparedStatement s = connection.prepareStatement("SELECT idAchat, codeLecteur, achats.idUser, idExemplaire, dateAchat FROM achats, users WHERE achats.idUser = users.idUser && codeLecteur = ?");
					s.setInt(1, Integer.parseInt(codeLecteur.getText()));
					ResultSet rs = s.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Entrer un code Lecteur entier");
				}
				
			}
		}
	}
}
