package Projekt;


import java.awt.*;

import javax.swing.*;
import java.awt.event.*;

public class JComboBoxDemo extends JFrame {
	  
	String[] description = { "Bialy", "Zolty",
	  	    "Czerwony", "Niebieski", };
	JTextField poleTekstowe = new JTextField("Wpisz nazwe nowej pozycji");
	JComboBox comboBox = new JComboBox(description);
	JButton przycisk = new JButton("Dodaj pozycje");
	JLabel etykieta = new JLabel();
	
	  	  
	public JComboBoxDemo() {
		super("Aplikacja Swing ComboBox");
		add(new PanelZKontrolkami(), BorderLayout.CENTER);
	}

	public static void main(String args[]) 
	{
		final JFrame f = new JComboBoxDemo();

		f.setBounds(100, 100, 300, 200);
		f.setVisible(true);
		f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}

	class PanelZKontrolkami extends JPanel //implements ActionListener
	{
		public PanelZKontrolkami() 
		{
				setLayout(new GridLayout(4,1));
			   
				przycisk.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e){
			          if (poleTekstowe.getText() != "") 
			        	  	comboBox.addItem(poleTekstowe.getText());
			                etykieta.setText("Dodano: " + poleTekstowe.getText());
			          }
			      });
			      comboBox.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e){
			          etykieta.setText("indeks: "+ comboBox.getSelectedIndex()
			            + "   " + comboBox.getSelectedItem());
			          if (comboBox.getSelectedItem().equals("Bialy")) 
			        	  etykieta.setText("Wybrano kolor Bialy");
			         
			        }
			      });
			   
			      
			      add(poleTekstowe);
			      add(comboBox);
			      add(przycisk);
			      add(etykieta);
			      pack();

		}

	}
}
