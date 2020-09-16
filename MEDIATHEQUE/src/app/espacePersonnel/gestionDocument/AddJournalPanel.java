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
import media.Journal;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class AddJournalPanel extends JPanel implements ActionListener{
	
	private JTextField titreJournal;
	private JTextField dateAjout;
	private JTextField dateJournal;
	private JTextField rayonnage;
	private JTextField titreJournal1;
	private JTextField dateAjout1;
	private JTextField prix;
	
	Button bnt_cancel = new Button("Cancel");
	Button btn_submit = new Button("Submit");
	Button btn_chercher = new Button("Chercher");
	Button btn_refresh = new Button("New button");
	
	JScrollPane tableView = new JScrollPane();
	JTable table = new JTable();
	
	Connection connection = Main.getConnection();
	
	public AddJournalPanel()
	{
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(700, 322));
	    this.setLayout(null);
	    
	    JLabel backGround = new JLabel("New label");
	    backGround.setIcon(new ImageIcon("assets/AddJournalPanel.png"));
	    backGround.setBounds(0, 0, 700, 322);
	    
	    
	    titreJournal = new JTextField();
	    titreJournal.setOpaque(false);
	    titreJournal.setBorder(null);
	    titreJournal.setBounds(142, 73, 86, 20);
	    titreJournal.setColumns(10);
	    
	    dateAjout = new JTextField();
	    dateAjout.setOpaque(false);
	    dateAjout.setColumns(10);
	    dateAjout.setBorder(null);
	    dateAjout.setBounds(142, 99, 86, 20);

	    dateJournal = new JTextField();
	    dateJournal.setOpaque(false);
	    dateJournal.setColumns(10);
	    dateJournal.setBorder(null);
	    dateJournal.setBounds(142, 126, 86, 20);
	    
	    
	    rayonnage = new JTextField();
	    rayonnage.setOpaque(false);
	    rayonnage.setColumns(10);
	    rayonnage.setBorder(null);
	    rayonnage.setBounds(142, 150, 91, 20);
	    
	    
	    
	    bnt_cancel.setOpaque(false);
	    bnt_cancel.setBorder(null);
	    bnt_cancel.setBounds(79, 219, 74, 18);
	    bnt_cancel.addActionListener(this);
	    
	    
	    
	    btn_submit.setOpaque(false);
	    btn_submit.setBorder(null);
	    btn_submit.setBounds(168, 219, 74, 18);
	    btn_submit.addActionListener(this);
	    
	    
	    btn_chercher.setOpaque(false);
	    btn_chercher.setBorder(null);
	    btn_chercher.setBounds(540, 64, 73, 19);
	    btn_chercher.addActionListener(this);
	    
	    
	    
	    btn_refresh.setBounds(671, 78, 20, 20);
	    btn_refresh.addActionListener(this);
	    
	    
	    titreJournal1 = new JTextField();
	    titreJournal1.setOpaque(false);
	    titreJournal1.setColumns(10);
	    titreJournal1.setBorder(null);
	    titreJournal1.setBounds(427, 39, 86, 20);
	    
	    
	    dateAjout1 = new JTextField();
	    dateAjout1.setOpaque(false);
	    dateAjout1.setColumns(10);
	    dateAjout1.setBorder(null);
	    dateAjout1.setBounds(427, 64, 86, 20);
	    
	    
	    prix = new JTextField();
	    prix.setOpaque(false);
	    prix.setColumns(10);
	    prix.setBorder(null);
	    prix.setBounds(142, 173, 91, 20);
	    
	    
	    this.add(titreJournal);
	    this.add(dateAjout);
	    this.add(dateJournal);
	    this.add(rayonnage);
	    this.add(bnt_cancel);
	    this.add(btn_submit);
	    this.add(btn_chercher);
	    this.add(btn_refresh);
	    this.add(titreJournal1);
	    this.add(dateAjout1);
	    this.add(prix);
	    this.add(tableView);
	    this.add(backGround);
	    
	    
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar cal = Calendar.getInstance();
	    dateJournal.setText(dateFormat.format(cal.getTime()));
		dateAjout.setText(dateFormat.format(cal.getTime()));
		dateAjout1.setText(dateFormat.format(cal.getTime()));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bnt_cancel) {
			titreJournal.setText("");
			rayonnage.setText("");
			prix.setText("");
		}
		
		if(e.getSource() == btn_submit) {
			try {
				new Journal(titreJournal.getText(), rayonnage.getText(), Double.parseDouble(prix.getText())).addJournal();
			}
			catch(Exception e1){	
				JOptionPane.showMessageDialog(null, "Not saved");
			}
		}
		
		if(e.getSource() == btn_refresh) {
			titreJournal1.setText("");
			
			try {
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("SELECT * FROM journals");
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
		if(e.getSource() == btn_chercher) {
			try {
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM journals WHERE titre = ? && dateAjout = ?");
				ps.setString(1, titreJournal1.getText());
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
