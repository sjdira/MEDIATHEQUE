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

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import app.Main;
import app.espaceConsultation.Button;
import media.Document;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class AddExempPanel extends JPanel implements ActionListener{

	private JTextField idDoc;
	private JTextField nombreExemplaire;
	private JTextField dateAjout;
	private JTextField idDocument;
	private JComboBox<String> categorie = new JComboBox<String>(new String[]{"Livre", "Journal", "CD-Rom", "MicroFilm"});
	
	Button bnt_cancel = new Button("Cancel");
	Button btn_modify = new Button("Submit");
	Button btn_refresh = new Button("");
	Button btn_chercher = new Button("Submit");
	
	JRadioButton button_true = new JRadioButton("true");
	JRadioButton button_false = new JRadioButton("false");
	
	Connection connection = Main.getConnection();
	JScrollPane tableView = new JScrollPane();
	JTable table = new JTable();
	
	public AddExempPanel()
	{
			this.setOpaque(false);
		    this.setPreferredSize(new Dimension( 700, 322));
		    this.setLayout(null);
		    
		    JLabel backGround = new JLabel("");
		    backGround.setIcon(new ImageIcon("assets/AddExemPanel.png"));
		    backGround.setBounds(0, 0, 700, 322);
		    
		    categorie.setBackground(Color.gray);
		    categorie.setOpaque(false);
		    categorie.setBorder(null);
		    categorie.setBounds(135, 161, 87, 20);
		    categorie.addActionListener(this);
		    
		    idDoc = new JTextField();
		    idDoc.setOpaque(false);
		    idDoc.setColumns(10);
		    idDoc.setBorder(null);
		    idDoc.setBounds(136, 80, 86, 20);

		    nombreExemplaire = new JTextField();
		    nombreExemplaire.setOpaque(false);
		    nombreExemplaire.setColumns(10);
		    nombreExemplaire.setBorder(null);
		    nombreExemplaire.setBounds(136, 105, 86, 20);
		    
		    bnt_cancel.setOpaque(false);
		    bnt_cancel.setBorder(null);
		    bnt_cancel.setBounds(74, 239, 71, 18);
		    bnt_cancel.addActionListener(this);

		    
		    btn_modify.setOpaque(false);
		    btn_modify.setBorder(null);
		    btn_modify.setBounds(159, 239, 71, 18);
		    btn_modify.addActionListener(this);
		    
		    
		    btn_refresh.setBorder(null);
		    btn_refresh.setOpaque(false);
		    btn_refresh.setBounds(646, 86, 19, 19);
		    btn_refresh.addActionListener(this);
		    
		    
		    button_true.setOpaque(false);
		    button_true.setBorder(null);
		    button_true.setBounds(48, 210, 97, 23);
			
		    
		    button_false.setOpaque(false);
		    button_false.setBorder(null);
		    button_false.setBounds(148, 210, 97, 23);
			
			ButtonGroup gp1 = new ButtonGroup();
			gp1.add(button_true);
			gp1.add(button_false);

		    dateAjout = new JTextField();
		    dateAjout.setOpaque(false);
		    dateAjout.setColumns(10);
		    dateAjout.setBorder(null);
		    dateAjout.setBounds(136, 132, 86, 20);

		    idDocument = new JTextField();
		    idDocument.setOpaque(false);
		    idDocument.setColumns(10);
		    idDocument.setBorder(null);
		    idDocument.setBounds(403, 47, 87, 20);

		    
		    btn_chercher.setOpaque(false);
		    btn_chercher.setBorder(null);
		    btn_chercher.setBounds(521, 70, 72, 19);
		    btn_chercher.addActionListener(this);
		    
		   
		    this.add(idDoc);
		    this.add(nombreExemplaire);
		    this.add(categorie);
		    this.add(bnt_cancel);
		    this.add(btn_modify);
		    this.add(btn_refresh);
		    this.add(button_true);
		    this.add(button_false);
		    this.add(dateAjout);
		    this.add(idDocument);
		    this.add(btn_chercher); 
		    this.add(tableView);
		    this.add(backGround);
		    
		    
		    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    Calendar cal = Calendar.getInstance();
			dateAjout.setText(dateFormat.format(cal.getTime()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == bnt_cancel) {
			idDoc.setText("");
			nombreExemplaire.setText("");
		}
		
		if(e.getSource() == btn_refresh) {
			idDocument.setText("");
			
			try {
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("SELECT * FROM exemplaire");
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				tableView.setBounds(300, 120, 368, 187);
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
		
		if(e.getSource() == btn_modify) {      //insertion
			try {
				Document doc = new Document("",0.0);
				String Categorie = null;
				
				if(categorie.getSelectedIndex() == 0)
				{
					Categorie = "Livre";
				}
					
				if(categorie.getSelectedIndex() == 2)
				{
					Categorie = "CD-Rom";
					button_true.setSelected(true);
				}
				
				if(categorie.getSelectedIndex() == 1)
				{
					Categorie = "Journal";
					button_false.setSelected(true);
				}
					
				if(categorie.getSelectedIndex() == 3)
				{
					Categorie = "MicroFilm";
					button_false.setSelected(true);
				}
					
				if(button_true.isSelected()) {
					for(int i = 0; i < Integer.parseInt(nombreExemplaire.getText()); i++) {
						doc.addExemplaire(Integer.parseInt(idDoc.getText()), Categorie,true , true);
					}
					JOptionPane.showMessageDialog(null, "successfully inserted");
				}
				
				if(button_false.isSelected()) {
					for(int i = 0; i < Integer.parseInt(nombreExemplaire.getText()); i++) {
						doc.addExemplaire(Integer.parseInt(idDoc.getText()), Categorie,true , false);
					}
					JOptionPane.showMessageDialog(null, "successfully inserted");
				}
				
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Not saved");
			}

		}
		
		if(e.getSource() == btn_chercher) {
			if (idDocument.getText().isBlank()) {
				try {
					Statement s = connection.createStatement();
					ResultSet rs = s.executeQuery("SELECT * FROM exemplaire");
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					tableView.setBounds(300, 120, 368, 187);
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
			}else {
				try {
					PreparedStatement ps = connection.prepareStatement("select * from exemplaire where idDoc = ?");
					ps.setInt(1, Integer.parseInt(idDocument.getText()));
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					tableView.setBounds(300, 120, 368, 187);
					tableView.getViewport().setBackground(new Color(128,128,128));
				    table.setBackground(new Color(128,128,128));
				    table.setShowHorizontalLines(false);
				    table.setShowVerticalLines(false);
				    table.setRowHeight(30);
				    tableView.setViewportView(table);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Entrer un identifiant entier");
				}
			}
			
		}
		
	}
}
