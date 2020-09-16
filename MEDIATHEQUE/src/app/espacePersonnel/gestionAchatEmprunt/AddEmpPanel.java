package app.espacePersonnel.gestionAchatEmprunt;

import java.awt.*;
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
import media.Emprunt;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class AddEmpPanel  extends JPanel implements ActionListener{

	private JTextField codeLecteur;
	private JTextField idExemplaire;
	private JTextField dateEmprunt;
	private JTextField codeLecteur2;
	private JTextField codeLecteur1;
	private JTextField idExemplaire1;
	private JTextField dateRetour;
	Button btn_submit = new Button("Submit");
	Button btn_cancel = new Button("Cancel");
	Button bnt_modify = new Button("Modify");
	Button btn_chercher = new Button("Chercher");
	Button btn_refresh = new Button("");
	Button btn_telecharger = new Button("Telecharger");
	String fichePret;
	JTextArea FichePret = new JTextArea(" ");
	
	JScrollPane tableView = new JScrollPane();
	JTable table=new JTable();
	Connection connection = Main.getConnection();
	
	public AddEmpPanel()
	{
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(900,322));
	    this.setLayout(null);
	    
	    FichePret.setBounds(550, 116, 300, 200);
	    FichePret.setEditable(false);
	    FichePret.setFont(new Font("Tahoma", Font.BOLD, 14));
	    FichePret.setForeground(Color.BLACK);
	    FichePret.setVisible(false);
	    FichePret.setOpaque(false);
	    FichePret.setLineWrap(true);
	    
	    JLabel backGround = new JLabel("");
	    backGround.setIcon(new ImageIcon("assets/AddEmpPanel.png"));
	    backGround.setBounds(0, 0, 900, 322);
	    
	    
	    codeLecteur = new JTextField();
	    codeLecteur.setOpaque(false);
	    codeLecteur.setBorder(null);
	    codeLecteur.setBounds(182, 60, 112, 20);
	    codeLecteur.setColumns(10);
	    
	    idExemplaire = new JTextField();
	    idExemplaire.setBorder(null);
	    idExemplaire.setOpaque(false);
	    idExemplaire.setColumns(10);
	    idExemplaire.setBounds(182, 88, 112, 20);
	    
	    
	    dateEmprunt = new JTextField();
	    dateEmprunt.setBorder(null);
	    dateEmprunt.setOpaque(false);
	    dateEmprunt.setColumns(10);
	    dateEmprunt.setBounds(182, 116, 112, 20);
	    
	    
	    btn_cancel.setBounds(76, 169, 95, 20);
	    btn_cancel.addActionListener(this);
	    
	    
	    btn_submit.setBounds(188, 169, 94, 20);
	    btn_submit.addActionListener(this);
	    
	    
	    codeLecteur2 = new JTextField();
	    codeLecteur2.setOpaque(false);
	    codeLecteur2.setBorder(null);
	    codeLecteur2.setBounds(532, 44, 112, 20);
	    codeLecteur2.setColumns(10);
	    
	    btn_chercher.setBounds(778, 49, 93, 19);
	    btn_chercher.addActionListener(this);
	    
	    
	    btn_refresh.setBounds(853, 84, 19, 18);
	    btn_refresh.addActionListener(this);
	    
	    
	    bnt_modify.setBounds(223, 281, 94, 20);
	    bnt_modify.addActionListener(this);
	    
	    
	    btn_telecharger.setBounds(600, 285, 120, 20);
	    btn_telecharger.setVisible(false);
	    btn_telecharger.setBorderPainted(true);
	    btn_telecharger.setContentAreaFilled(false);
	    btn_telecharger.setFocusPainted(false);
	    btn_telecharger.setBorder(new LineBorder(Color.BLACK));
	    btn_telecharger.addActionListener(this);
	    
	    codeLecteur1 = new JTextField();
	    codeLecteur1.setOpaque(false);
	    codeLecteur1.setColumns(10);
	    codeLecteur1.setBorder(null);
	    codeLecteur1.setBounds(184, 211, 112, 20);
	    
	    
	    idExemplaire1 = new JTextField();
	    idExemplaire1.setOpaque(false);
	    idExemplaire1.setColumns(10);
	    idExemplaire1.setBorder(null);
	    idExemplaire1.setBounds(184, 234, 112, 20);
	   
	    dateRetour = new JTextField();
	    dateRetour.setOpaque(false);
	    dateRetour.setColumns(10);
	    dateRetour.setBorder(null);
	    dateRetour.setBounds(184, 255, 112, 20);
	    
	    
	    this.add(codeLecteur);
	    this.add(idExemplaire);
	    this.add(dateEmprunt);
	    this.add(btn_cancel);
	    this.add(btn_submit);
	    this.add(btn_chercher);
	    this.add(btn_refresh);
	    this.add(bnt_modify);
	    this.add(codeLecteur1);
	    this.add(idExemplaire1);
	    this.add(dateRetour);
	    this.add(codeLecteur2);
	    this.add(btn_telecharger);
	    this.add(FichePret);
	    this.add(tableView);
	    this.add(backGround);
	    
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar cal = Calendar.getInstance();
		dateEmprunt.setText(dateFormat.format(cal.getTime()));
		dateRetour.setText(dateFormat.format(cal.getTime()));
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btn_cancel) {
			codeLecteur.setText("");
			idExemplaire.setText("");
		}
		
		if (e.getSource() == bnt_modify) {
			new Emprunt(Integer.parseInt(codeLecteur1.getText()), Integer.parseInt(idExemplaire1.getText())).retourEmprunt();
		}
		
		if (e.getSource() == btn_submit) {
			Emprunt emprunt = new Emprunt(Integer.parseInt(codeLecteur.getText()), Integer.parseInt(idExemplaire.getText()));
			emprunt.addEmprunt();
			tableView.setVisible(false);
			fichePret = emprunt.genereFichePret();
			FichePret.setText(emprunt.genereFichePret());
			FichePret.setVisible(true);
			btn_telecharger.setVisible(true);
		}
		
		if (e.getSource() == btn_telecharger)
		{
	   		try {
	   			Document doc = new Document();
				PdfWriter.getInstance(doc, new FileOutputStream(".\\fichePret\\fichePret.pdf"));
				doc.open();
				Paragraph para1 = new Paragraph("Fiche de pret");
	   			para1.setAlignment(Paragraph.ALIGN_CENTER);
				Paragraph para2 = new Paragraph("-----------------------------------------------------------------------------------------\n");
	   			para2.setAlignment(Paragraph.ALIGN_CENTER);
				Paragraph para = new Paragraph(fichePret);
				para.setAlignment(Paragraph.ALIGN_CENTER);
				doc.add(para1);
				doc.add(para2);
				doc.add(para);
				doc.close();
				JOptionPane.showMessageDialog(null, "La fiche est bien enregistrerdans le dossier fichePret");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Not saved");
			}
		}
		
		if (e.getSource() == btn_chercher) {
			btn_telecharger.setVisible(false);
			FichePret.setVisible(false);
			tableView.setBounds(400, 116, 485, 200);
		    table.setBackground(new Color(128,128,128));
		    table.setShowHorizontalLines(false);
		    table.setShowVerticalLines(false);
		    table.setRowHeight(30);
		    table.setEnabled(false);
		    tableView.setViewportView(table);
		    tableView.setVisible(true);
		    
			if(codeLecteur2.getText().isBlank()) {       //Returns true if the string is empty or contains only white space codepoints.
				try {
					Statement s = connection.createStatement();
					ResultSet rs = s.executeQuery("SELECT idEmp, codeLecteur, emprunts.idUser, idExemplaire, dateEmp, dateRetour FROM emprunts, users WHERE emprunts.idUser = users.idUser");
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				try {
					PreparedStatement s = connection.prepareStatement("SELECT idEmp, codeLecteur, emprunts.idUser, idExemplaire, dateEmp, dateRetour FROM emprunts, users WHERE emprunts.idUser = users.idUser && codeLecteur = ?");
					s.setInt(1, Integer.parseInt(codeLecteur2.getText()));
					ResultSet rs = s.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Entrer un code Lecteur entier");
				}
			}
		}
		
		if(e.getSource() == btn_refresh) {
			codeLecteur2.setText("");
			
			try {
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("SELECT idEmp, codeLecteur, emprunts.idUser, idExemplaire, dateEmp, dateRetour FROM emprunts, users WHERE emprunts.idUser = users.idUser");
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				btn_telecharger.setVisible(false);
				FichePret.setVisible(false);
				tableView.setBounds(400, 116, 485, 200);
			    table.setBackground(new Color(128,128,128));
			    table.setShowHorizontalLines(false);
			    table.setShowVerticalLines(false);
			    table.setRowHeight(30);
			    tableView.setViewportView(table);
			    table.setEnabled(false);
			    tableView.setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
