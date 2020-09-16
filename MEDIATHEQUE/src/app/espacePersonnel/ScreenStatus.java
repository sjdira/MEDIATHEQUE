package app.espacePersonnel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import app.Main;
import app.espaceConsultation.Button;
import net.proteanit.sql.DbUtils;


@SuppressWarnings("serial")
public class ScreenStatus extends JPanel 
{
	private JTextField numEcran = new JTextField();	
	private PreparedStatement prep;
	private ResultSet res;
	private JScrollPane scrollPane ;
	private JTable table;
	protected String requete;
	
	public ScreenStatus ()
	{
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(900,322));
	    this.setLayout(null);
	   
	    
	    JLabel backGround = new JLabel("");
	    backGround.setIcon(new ImageIcon("assets/ScreensStatus.png"));
	    backGround.setBounds(0, 0, 900, 322);
	   
	      	    
	    numEcran.setOpaque(false);
	    numEcran.setBorder(null);
	    numEcran.setBounds(222, 38, 96, 20);
	    numEcran.setColumns(10);
	    
	    Button btn_submit = new Button("Submit");
	    btn_submit.setBorder(null);
	    btn_submit.setOpaque(false);
	    btn_submit.setBounds(332, 55, 82, 20);
	   
	    btn_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {	
					findScrean(Integer.parseInt(numEcran.getText()));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(ScreenStatus.this, "Entrer un numero d'ecran");
				}
				
			}
		});
	
	    
	    Button btn_refresh = new Button("");
	    btn_refresh.setOpaque(false);
	    btn_refresh.setBorder(null);
	    btn_refresh.setBounds(791, 72, 18, 18);
	    
	    btn_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}				
				});
	    
	    
	    scrollPane = new JScrollPane();
	    scrollPane.setBounds(86, 101, 730, 189);	    
	    scrollPane.getViewport().setBackground(new Color(128,128,128));
	    
	    table = new JTable();
	    
	    table.setFont(new Font("URW Chancery L", Font.ITALIC, 20));
	    table.setSurrendersFocusOnKeystroke(true);
	    table.setShowVerticalLines(false);
	    table.setShowHorizontalLines(false);
	    table.setShowGrid(false);
	    table.setForeground(new Color(0, 0, 0));
	    table.setEnabled(false);
	    table.setBackground(new Color(128,128,128));
	    table.setColumnSelectionAllowed(true);
	    table.setRowHeight(25);
	    scrollPane.setViewportView(table);
	    scrollPane.setVisible(false);
	    
	    this.add(scrollPane);
	    this.add(numEcran);
	    this.add(btn_submit);
	    this.add(btn_refresh);
	    this.add(backGround);
	}
	
	public void  update() {
		try {			
			prep=Main.getConnection().prepareStatement(
					"Select * from  ecrans");
			res=prep.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(res));
			scrollPane.setVisible(true);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(ScreenStatus.this, "veillez verefier votre connection");
		}

	}
	
	public void findScrean(int postNum) {
		try {			
			prep=Main.getConnection().prepareStatement("Select * from  ecrans where posteNum="+postNum+";");
			res=prep.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(res));
			scrollPane.setVisible(true);
		}catch (Exception e) {	       
			e.printStackTrace();		
	     }
	}
}
