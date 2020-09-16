package app.espacePersonnel.gestionAchatEmprunt;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import app.Main;
import app.espaceConsultation.Button;
import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class StatisticsEmpruntPanel extends JPanel
{
	private JTextField codeLecteur;
	//Sprivate JFormattedTextField jours;
	private JTextField jours=new JTextField();
	private Button bnt_submit = new Button("Submit");
	private Button btnEnvoyer = new Button("Envoyer");
	private JLabel nbrEmpruntLivre = new JLabel("");
	private JLabel nbrEmpruntJournal = new JLabel("");
	private JLabel nbrEmpruntCdRom = new JLabel("");
	private JLabel nbrEmpruntMicroFilm = new JLabel("");
	private JRadioButton filtreNbrJour = new JRadioButton("");
	private JRadioButton sansFiltre = new JRadioButton("");
	private ButtonGroup group =new ButtonGroup();
	
	private Date date = new Date();
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
	private String requete="";
	private Connection conn;
	private PreparedStatement prep;
	private ResultSet res;
	private JTable table;
	
	JScrollPane scrollPane = new JScrollPane();

	public StatisticsEmpruntPanel()
	{
	 this.setOpaque(false);
	 this.setPreferredSize(new Dimension(900,322));
	 this.setLayout(null);
	 this.conn=Main.getConnection();
	 
	 JLabel backGround = new JLabel("");
	 backGround.setIcon(new ImageIcon("assets/StatisticsEmpPanel.png"));
	 backGround.setBounds(0, 0, 900, 322);

	 
	 codeLecteur = new JTextField();
	 codeLecteur.setOpaque(false);
	 codeLecteur.setBorder(null);
	 codeLecteur.setBounds(171, 53, 113, 20);
	 codeLecteur.setColumns(10);
	 

	 jours.setBorder(null);
	 jours.setOpaque(false);
	 jours.setColumns(10);
	 jours.setBounds(120, 127, 35, 20);

	 
	 bnt_submit.setOpaque(false);
	 bnt_submit.setBorder(null);
	 bnt_submit.setBounds(189, 185, 94, 20);
	 bnt_submit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(sansFiltre.isSelected())
			{
				if(codeLecteur.getText().equals(""))
				{
					requete="Select codeLecteur,categorie as Document,dateEmp,dateRetour from users u ,emprunts e,exemplaire x where u.idUser=e.idUser && e.idExemplaire=x.idExemplaire &&  dateRetour is NULL ;";
					
				}
				else 
					requete="Select codeLecteur,categorie as Document,dateEmp,dateRetour from users ,emprunts e,exemplaire x where users.idUser=e.idUser && users.codeLecteur="+codeLecteur.getText()+"&& e.idExemplaire=x.idExemplaire &&  dateRetour is NULL;";
			}
			else
			{
				if (codeLecteur.getText().equals(""))
				{
					
					try {
						int nbr=Integer.parseInt(jours.getText());
						requete="Select codeLecteur,categorie as Document,dateEmp,dateRetour from users ,emprunts,exemplaire x where users.idUser=emprunts.idUser && DATEDIFF('"+dateFormat.format(date)+"',dateEmp)>="+nbr+ " && emprunts.idExemplaire=x.idExemplaire &&  dateRetour is NULL;";
						
					}catch (NumberFormatException nfe) {
				       
						JOptionPane.showMessageDialog(StatisticsEmpruntPanel.this, "Svp veuillez entrer un nombre de jours valide");
						return;
				      }
					
				}	
				else	
				{
					try {
						
						int nbr=Integer.parseInt(jours.getText());
						requete="Select codeLecteur,categorie as Document,dateEmp,dateRetour from users ,emprunts,exemplaire x where users.idUser=emprunts.idUser && DATEDIFF('"+dateFormat.format(date)+"',dateEmp)>="+nbr
								+" && codeLecteur="+Integer.parseInt(codeLecteur.getText())+"&& emprunts.idExemplaire=x.idExemplaire  &&  dateRetour is NULL;";
					}catch (NumberFormatException nfe) {
				       
						JOptionPane.showMessageDialog(StatisticsEmpruntPanel.this, "Svp Spécifiez le nombre de jours");
						return;
				      }
				}
			}
			try {
				prep=conn.prepareStatement(requete);
				res=prep.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(res));
				scrollPane.setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(StatisticsEmpruntPanel.this, "Check your database connection");
				System.out.print(e1.getMessage());
			}
		}});
	 
	 
	 btnEnvoyer.setAlignmentX(Component.RIGHT_ALIGNMENT);
	 btnEnvoyer.setOpaque(false);
	 btnEnvoyer.setBorder(null);
	 btnEnvoyer.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JOptionPane.showMessageDialog(StatisticsEmpruntPanel.this, "Il faut envoyer une lettre au lecteur d'après l'autorisation du bibliothécaire");
		}});
	 btnEnvoyer.setBounds(777, 288, 94, 20);

	 
	 nbrEmpruntLivre.setBounds(65, 272, 54, 20);
	
	 
	 nbrEmpruntJournal.setBounds(161, 272, 60, 20);
	
	 
	 nbrEmpruntCdRom.setBounds(270, 271, 54, 20);
	 
	 
	 nbrEmpruntMicroFilm.setBounds(365, 272, 54, 20);
	 
	 	
	 
	 
	 filtreNbrJour.setBorder(null);
	 filtreNbrJour.setBounds(28, 109, 109, 23);
	 filtreNbrJour.setOpaque(false);
	 filtreNbrJour.setBorder(null);
	 
	 
	 
	 sansFiltre.setBorder(null);
	 sansFiltre.setBounds(28, 151, 109, 23);
	 sansFiltre.setOpaque(false);
	 sansFiltre.setBorder(null);
	 sansFiltre.setSelected(true);
	 
	 group.add(sansFiltre);
	 group.add(filtreNbrJour);
	 
	 
	 scrollPane.setBorder(null);
	 scrollPane.setOpaque(false);
	 scrollPane.setBounds(451, 83, 420, 200);
	
	 table = new JTable();
	 table.setOpaque(false);
	 table.setBorder(null);
	 table.setShowVerticalLines(false);
	 table.setShowHorizontalLines(false);
	 table.setShowGrid(false);
	 table.setForeground(new Color(0, 0, 0));
	 table.setEnabled(false);
	 table.setColumnSelectionAllowed(true);
	 table.setBackground(new Color(128,128,128));
	   
	 scrollPane.setViewportView(table);
	 scrollPane.getViewport().setBackground(new Color(128,128,128));
	 scrollPane.setVisible(false);
	     
	 this.setLabel("livre");
	 this.setLabel("microfilm");
	 this.setLabel("cdrom");
	 this.setLabel("journal");
	 
	 
	 
	 add(scrollPane);
	 add(sansFiltre);
	 add(filtreNbrJour);
	 add(codeLecteur);
	 add(jours);
	 add(bnt_submit);
	 add(btnEnvoyer);
	 add(nbrEmpruntLivre);
	 add(nbrEmpruntJournal);
	 add(nbrEmpruntCdRom);
	 add(nbrEmpruntMicroFilm);
	 add(backGround);
	}
	
	public void setLabel(String str)
	{
		int a=0;
			try {
			prep=conn.prepareStatement("Select * from  emprunts,exemplaire where emprunts.idExemplaire=exemplaire.idExemplaire && categorie like '"+str+"';");
			res=prep.executeQuery();
			while (res.next())a++;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
			JOptionPane.showMessageDialog(StatisticsEmpruntPanel.this, "Check your database connection");
		}
		if (str.equals("livre"))
			nbrEmpruntLivre.setText(String.valueOf(a));
		else if (str.equals("microfilm"))
			nbrEmpruntMicroFilm.setText((String.valueOf(a)));
		else if (str.equals("cdrom"))
			nbrEmpruntCdRom.setText(String.valueOf(a));
		else
			nbrEmpruntJournal.setText(String.valueOf(a));
			
	}
}
