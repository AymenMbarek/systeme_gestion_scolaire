package emploiDuTemps_java;
/*Code écrit du 26 au 28 Mars 2021 à N'djaména au Tchad par TARGOTO CHRISTIAN
 * Contact: ct@chrislink.net / 23560316682*/
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Requetes extends JFrame {
	Statement st;
	Conneccion con=new Conneccion();
	ResultSet rst;
	JTable table,table2;
	JScrollPane scroll,scroll2;
	JLabel lbclasse,lbmatiere,lbtitre,lbtitre2,lbid,lbclasse2;
	JTextField tfmatiere,tfid;
	JComboBox comboclasse,comboclasse2;
	JButton btrech,btsupp,btrech2;
	public Requetes(){
		this.setTitle("chcode_appli");
		this.setSize(800,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		JPanel pn=new JPanel();
		pn.setLayout(null);
		add(pn);
		pn.setBackground(new Color(240,200,200));
		
		lbtitre=new JLabel("Les séances de cours dans la semaine d'une matière dans une classe");
		lbtitre.setBounds(20,5,800,30);
		lbtitre.setFont(new Font("Arial",Font.BOLD,18));
		pn.add(lbtitre);
		
		lbtitre2=new JLabel("Emploi du temps de la semaine par classe");
		lbtitre2.setBounds(20,380,800,30);
		lbtitre2.setFont(new Font("Arial",Font.BOLD,18));
		pn.add(lbtitre2);
		
		//classe
		lbclasse=new JLabel("Classe");
		lbclasse.setBounds(30,40,150,25);
		lbclasse.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbclasse);
		
		comboclasse=new JComboBox();
		comboclasse.addItem("");
		comboclasse.addItem("6eme");
		comboclasse.addItem("5eme");
		comboclasse.addItem("4eme");
		comboclasse.addItem("3eme");
		comboclasse.addItem("2nde L");
		comboclasse.addItem("2dne S");
		comboclasse.addItem("1ere L");
		comboclasse.addItem("1ere S");
		comboclasse.addItem("TA");
		comboclasse.addItem("TD");
		comboclasse.addItem("TC");
		comboclasse.setBounds(30,70,150,25);
		pn.add(comboclasse);
		//classe2
				lbclasse2=new JLabel("Classe");
				lbclasse2.setBounds(30,420,150,25);
				lbclasse2.setFont(new Font("Arial",Font.BOLD,16));
				pn.add(lbclasse2);
				
				comboclasse2=new JComboBox();
				comboclasse2.addItem("");
				comboclasse2.addItem("6eme");
				comboclasse2.addItem("5eme");
				comboclasse2.addItem("4eme");
				comboclasse2.addItem("3eme");
				comboclasse2.addItem("2nde L");
				comboclasse2.addItem("2dne S");
				comboclasse2.addItem("1ere L");
				comboclasse2.addItem("1ere S");
				comboclasse2.addItem("TA");
				comboclasse2.addItem("TD");
				comboclasse2.addItem("TC");
				comboclasse2.setBounds(100,420,150,25);
				pn.add(comboclasse2);
		//matiere
		lbmatiere=new JLabel("Matière");
		lbmatiere.setBounds(200,40,150,25);
		lbmatiere.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbmatiere);
		
		tfmatiere=new JTextField();
		tfmatiere.setBounds(200,70,150,25);
		pn.add(tfmatiere);
		//id
		
				lbid=new JLabel("ID");
				lbid.setBounds(20,340,150,25);
				lbid.setFont(new Font("Arial",Font.BOLD,16));
				pn.add(lbid);
				
				tfid=new JTextField();
				tfid.setBounds(50,340,90,25);
				pn.add(tfid);
			//bouton supprimer
				btsupp=new JButton("SUPPRIMER");
				btsupp.setBounds(150,340,110,25);
				btsupp.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ev){
						String id=tfid.getText();
						String rq="delete from tb_cours where id='"+id+"'";
						try{
							st=con.laConnection().createStatement();
		if(JOptionPane.showConfirmDialog(null,"voulez vous Supprimer? ",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
							 st.executeUpdate(rq);
							 JOptionPane.showMessageDialog(null,"Suppréssion éffectuée avec succès !",null,JOptionPane.INFORMATION_MESSAGE);
		}
						}
						catch(SQLException ex){
					    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
					    }
						
					}
					
				});
				pn.add(btsupp);
		//bouton recherche
		btrech=new JButton("CHERCHER");
		btrech.setBounds(360,70,120,25);
		btrech.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				String classe=comboclasse.getSelectedItem().toString(),
						matiere=tfmatiere.getText();
				DefaultTableModel df2=new  DefaultTableModel();
				  init2();
				  pn.add(scroll2);
				 df2.addColumn("ID");
				 df2.addColumn("Classe");
				 df2.addColumn("Matiere");
				 df2.addColumn("Jour");
				 df2.addColumn("Heure");
				 df2.addColumn("Nom enseignant");
				 df2.addColumn("Contact enseignant");
				 table2.setModel(df2);
				 String rq2="select * from enseignant_cours where classe='"+classe+"' and matiere='"+matiere+"' order by num_jour";
				 try{
					 st=con.laConnection().createStatement();
					 rst=st.executeQuery(rq2);
					 while(rst.next()){
						 df2.addRow(new Object[]{
		rst.getString("id"),rst.getString("classe"),rst.getString("matiere"),rst.getString("jour"),rst.getString("heure"),
		rst.getString("nom"),rst.getString("contact")
								 });
						 
					   } 
					 }
					 
				 catch(SQLException ex){
				    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
				    }
				
			}
		});
		pn.add(btrech);
		
		//btrech2
		//bouton recherche
				btrech2=new JButton("CHERCHER");
				btrech2.setBounds(260,420,120,25);
				btrech2.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ev){
						String classe=comboclasse2.getSelectedItem().toString();
						DefaultTableModel df2=new  DefaultTableModel();
						  init2();
						  pn.add(scroll2);
						 df2.addColumn("ID");
						 df2.addColumn("Classe");
						 df2.addColumn("Jour");
						 df2.addColumn("Matiere");
						 df2.addColumn("Heure");
						 df2.addColumn("Nom enseignant");
						 df2.addColumn("Contact enseignant");
						 table2.setModel(df2);
						 String rq2="select * from enseignant_cours where classe='"+classe+"' order by num_jour,heure";
						 try{
							 st=con.laConnection().createStatement();
							 rst=st.executeQuery(rq2);
							 while(rst.next()){
								 df2.addRow(new Object[]{
				rst.getString("id"),rst.getString("classe"),rst.getString("jour"),rst.getString("matiere"),rst.getString("heure"),
				rst.getString("nom"),rst.getString("contact")
										 });
								 
							   } 
							 }
							 
						 catch(SQLException ex){
						    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
						    }
						
					}
				});
				pn.add(btrech2);
		
	}
	private void init2(){
		table2=new JTable();
		scroll2=new JScrollPane();
		scroll2.setBounds(10,120,770,200);
		scroll2.setViewportView(table2);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Requetes rq=new Requetes();
	rq.setVisible(true);
	/*Code écrit du 26 au 28 Mars 2021 à N'djaména au Tchad par TARGOTO CHRISTIAN
	 * Contact: ct@chrislink.net / 23560316682*/
	}

}
