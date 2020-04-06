package Projekt;


import javax.swing.*;
//import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.util.ArrayList;
import java.util.Random;

public class Archer1 extends JFrame 
{
	    private static final int SLIDER_MIN1 = 0;  //ustawiam warto�� minimaln� suwaka 1 i 2
	    private static final int SLIDER_MAX1 = 90;  //ustawiam warto�� maksymaln� suwaka 1 i 2
	    private static final int SLIDER_INIT1 = 0;  //ustawiam warto�� pocz�tkow� suwaka 1 i 2
	    private static final int SLIDER_MIN2 = 0;  
	    private static final int SLIDER_MAX2 = 30;  
	    private static final int SLIDER_INIT2 = 0;  

	    //private static final int radius = 100;
	   
	    static final double g = 9.80665; //stale przyspieszenie ziemiskie do obliczen
	  	    
	  //variable values
	    private double resistance;	 
	    private int mass1 = 7; 
	    private double mass2 = 2.4;
	    private int mass3 = 13;
	    private int speedValue; 	//zmienne niezb�dne do ChangeListenera
	    private int angleValue;
	   
	    private double range;
	    private double maxheight;
	    private double flighttime;	   	 
	    	   
	    //menu
	    private JMenuBar menuBar;  //Tworz� pasek, w kt�rym umieszczam 2 opcje: Menu oraz More
	    private JMenu menu;
	    private JMenu more;
	    private JMenuItem itemExit; //Tworz� elementy, kt�re b�d� zawarte w opcjach Menu i More
	    private JMenuItem itemSave;
	    private JMenuItem itemNew;
	    private JMenuItem itemLoad;
	    private JMenuItem itemAuthors;

	    //panels
	    private JPanel animationPanel;  //Tworz� 2 panele
	    private JPanel controlPanel;
	    
	    //bottom panel
	    private JSlider sliderAngleValue;  //Tworz� 2 suwaki 
	    private JSlider sliderInitialSpeed;  	  	   
	    private JTextField textAirResistance; //Tworz� pola niezb�dne pola tekstowe, guziki, etykiety oraz pole wyboru
	    private JTextField textRange;
	    private JTextField textMaxHeight;
	    private JTextField textFlightTime;	    
	    private JButton buttonRandom;
	    private JButton buttonStart;
	    private JLabel labelAirResistance;
	    private JLabel labelAngleValue;
	    private JLabel labelInitialSpeed;
	    private JLabel labelFlightTime;
	    private JLabel labelMaxHeight;
	    private JLabel labelRange;
	    private JLabel labelMass;
	    private JComboBox<String> comboboxMass;
	   
	    
	    public Archer1() throws HeadlessException 
	    {
	    	setSize(1250, 620);  //ustawiam rozmiar ramki
	    	setTitle("Archer");		//ustawiam tytu� ramki
	    	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        setLayout(null);	       
	        this.setResizable(true);  //u�ytkownik nie mo�e zmieni� rozmiaru ramki

	      //Menu
	        menuBar = new JMenuBar();   
	        	menu = new JMenu("Menu");	 	
	        		itemNew = new JMenuItem("Nowa gra");   //zaprogramowanie opcji umieszczonych w menu
	        		itemNew.addActionListener(new ActionListener() 
	        		{
	        			@Override
	        			public void actionPerformed(ActionEvent e) 
	        			{
	        			
	        			}
	        		});
	        		itemSave = new JMenuItem("Zapisz stan gry");//dodaje opcje do Menu wybrane opcje
	        		itemSave.addActionListener(new ActionListener() 
	        		{
	        			@Override
	        			public void actionPerformed(ActionEvent e) 
	        			{
	        			
	        			}
	        		});
	        		itemLoad = new JMenuItem("Wczytaj gr�");
	        		itemLoad.addActionListener(new ActionListener() 
	        		{
	        			@Override
	        			public void actionPerformed(ActionEvent e) 
	        			{
	                
	        			}
	        		});
	        		itemExit = new JMenuItem("Wyj�cie");
					itemExit.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent arg0) 
						{
							System.exit(0);
						}	
					});
	        	menu.add(itemNew);//dodaje elementy do Menu
	        	menu.add(itemSave);	   
	        	menu.add(itemLoad);
				menu.addSeparator(); //ta kreska miedzy nimi
	        	menu.add(itemExit);
	      
	        	more = new JMenu("More");
					itemAuthors = new JMenuItem("Tw�rcy"); //wy�wietlenie informacji na temat autor�w programu
					itemAuthors.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent arg0) 
						{
							JOptionPane.showMessageDialog(itemAuthors, "Program zosta� napisany przez \n Aleksandr� Trel� oraz Bartosza Kucharskiego.");
						}	
					});
				more.add(itemAuthors);	//dodaje elementy do More
	        menuBar.add(menu); //dodaje opcje do paska
	        menuBar.add(more);
	        this.setJMenuBar(menuBar);
	        
	        //panel z animacj�
	        animationPanel = new JPanel();
	        animationPanel.setBounds(1, 1, 1250, 400); //ustawiam rozmiar panelu
	        animationPanel.setBackground(Color.white); //ustawiam kolor panelu
	        //add(animationPanel);
	        this.add(animationPanel, BorderLayout.CENTER); //wykorzystanie BorderLayoutu
	        
	            
	        //panel ustawie�
	        controlPanel = new JPanel();	       	        				
	        	labelAngleValue = new JLabel("K�t nachylenia �uku do ziemi: 0 �"); //Dodaje etykiet� nad suwakiem 1
	        	labelAngleValue.setBounds(10, 390, 200, 50); //ustawiam po�o�enie etykiety
	        add(labelAngleValue); 
	        	
	        	sliderAngleValue = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN1, SLIDER_MAX1, SLIDER_INIT1);
	        	sliderAngleValue.setBounds(10, 425, 200, 50); //ustawiam po�o�enie suwaka
	        	sliderAngleValue.setPreferredSize(new Dimension(200, 50));  //rozmiar suwaka
	        	sliderAngleValue.setMajorTickSpacing(30);  //warto�ci na podzia�ce co 30
	        	sliderAngleValue.setMinorTickSpacing(5);  //ka�dy kolejny punkt na podzia�ce wi�kszy o 5
	        	sliderAngleValue.setPaintTicks(true);
	        	sliderAngleValue.setPaintLabels(true);
	        	sliderAngleValue.addChangeListener(new SliderChangeListener());	//dodaje ChangeListener
	        add(sliderAngleValue);
	      	
	        	labelInitialSpeed = new JLabel("Pr�dko�� pocz�tkowa strza�y: 0 m/s"); //Dodaje etykiet� nad suwakiem 2
	        	labelInitialSpeed.setBounds(10, 465, 250, 50); 
	        add(labelInitialSpeed);
	        	
	        	sliderInitialSpeed = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN2, SLIDER_MAX2, SLIDER_INIT2);
	        	sliderInitialSpeed.setBounds(10, 500, 200, 50);
	        	sliderInitialSpeed.setPreferredSize(new Dimension(200, 50)); 
	        	sliderInitialSpeed.setMajorTickSpacing(10);  //warto�ci na podzia�ce co 10
	        	sliderInitialSpeed.setMinorTickSpacing(2);  //ka�dy kolejny punkt na podzia�ce wi�kszy o 2
	        	sliderInitialSpeed.setPaintTicks(true);
	        	sliderInitialSpeed.setPaintLabels(true);
	        	sliderInitialSpeed.addChangeListener(new SliderChangeListener());	//dodaje ChangeListener        	         
	        add(sliderInitialSpeed);
	        	        		     		
	        	
	        	labelMass = new JLabel("Masa wybranej strza�y: ");  //etykieta z wyswietlana mas�
	        	labelMass.setBounds(230, 390, 200, 50);
	        	add(labelMass);
	        	  
	        	comboboxMass = new JComboBox<String>(); //dodaje pole wyboru oraz tworz� mu opcje
	        	comboboxMass.setBounds(230, 425, 165, 20); //ustawiam po�o�enie pola wyboru
	        	comboboxMass.addItem("stalowa");
	        	comboboxMass.addItem("aluminiowa");
	        	comboboxMass.addItem("tytanowa");
	        	comboboxMass.addActionListener(new ActionListener() 
	        	{
	        		@Override
	        		public void actionPerformed(ActionEvent e) 
	        		{		       			
	        			if (comboboxMass.getSelectedItem().equals("stalowa"))	      			
	        			{	        				
	        				labelMass.setText("Masa wybranej strza�y: " + mass1 + " kg");	//7 kg
	        			}
	        			if (comboboxMass.getSelectedItem().equals("aluminiowa"))	      			
	        			{
	        				labelMass.setText("Masa wybranej strza�y: " + mass2 + " kg");	//2.4 kg
	        			}
	        			if (comboboxMass.getSelectedItem().equals("tytanowa"))	      			
	        			{
	        				labelMass.setText("Masa wybranej strza�y: " + mass3 + " kg");	//13 kg
	        			}
	        		}
	        	});
	        add(comboboxMass);
	     
	        	labelAirResistance = new JLabel("Op�r powietrza");	       
	        	labelAirResistance.setBounds(425, 390, 120, 50);	
		    add(labelAirResistance); 	 
		    	textAirResistance = new JTextField("");  //pole tekstowe, w kt�rym wy�wietla� si� b�dzie op�r powietrza
		    	textAirResistance.setBounds(425, 425, 120, 30);
	       	add(textAirResistance);
	       		buttonRandom = new JButton("Losuj");  //przycisk Losuj, kt�ry generuje liczb� z zakresu 1-100
	       		buttonRandom.setBounds(425, 455, 120, 30);	
	        	buttonRandom.addActionListener(new ActionListener() 
	        	{
	        		@Override
	        		public void actionPerformed(ActionEvent e) 
	        		{	        			
	        			Random rand = new Random();
	        		    resistance = rand.nextInt(100)+1;	        			      			
	        			textAirResistance.setText(String.valueOf(resistance)); //wyswietli wylosowan� warto�� oporu
	        		}	        				        		
	        	});
	        add(buttonRandom);
	        
	        	labelFlightTime = new JLabel("Czas lotu strza�y");	        
	        	labelFlightTime.setBounds(570, 390, 200, 50);	
	        add(labelFlightTime); 	        
	        	textFlightTime = new JTextField(); //pole tekstowe, w kt�rym wy�wietla� si� b�dzie Czas lotu strza�y (korzystamy ze wzor�w ze specyfikacji)
	        	textFlightTime.setBounds(570, 425, 150, 30);	
	        add(textFlightTime);
	       
	        	labelMaxHeight = new JLabel("Maksymalna wysoko��");	
	        	labelMaxHeight.setBounds(740, 390, 200, 50);
	        add(labelMaxHeight); 	       
	        	textMaxHeight = new JTextField(); //pole tekstowe, w kt�rym wy�wietla� si� b�dzie Maksymalna wysoko�� (korzystamy ze wzor�w ze specyfikacji)
	        	textMaxHeight.setBounds(740, 425, 150, 30);
	        add(textMaxHeight);
	        
	     		labelRange = new JLabel("Zasi�g strza�y");
	     		labelRange.setBounds(910, 390, 200, 50);
	        add(labelRange); 
	        	textRange = new JTextField();  //pole tekstowe, w kt�rym wy�wietla� si� b�dzie Zasi�g strza�y (korzystamy ze wzor�w ze specyfikacji)
	        	textRange.setBounds(910, 425, 150, 30);
	        add(textRange);
	        	      
	        	buttonStart = new JButton("Start/Stop");  //przycisk, kt�ry pozwoli uruchomi� i wstrzyma� gr�
	        	buttonStart.setBounds(1080, 425, 150, 50);		
        		buttonStart.addActionListener(new ActionListener() 
        		{
        			@Override
        			public void actionPerformed(ActionEvent e) //Math.sin(Math.toRadians(cos)) - wzor zeby z tego co mamy zrobic to co chcemy xd
        			{					
        				flighttime = (2 * speedValue * Math.sin(Math.toRadians(angleValue)) ) / g; //wzory
        				textFlightTime.setText(String.valueOf(String.format("%.02f", flighttime) + " [s]")); //wyswietli  warto�� oporu
        				        			       				        				
        				maxheight = Math.pow(speedValue * Math.sin(Math.toRadians(angleValue)), 2) / 2 * g;
        				textMaxHeight.setText(String.valueOf(String.format("%.02f", maxheight) + " [m]")); 
        				        			
        				range = ( Math.pow(speedValue, 2) * Math.sin( 2* Math.toRadians(angleValue)) ) / g;
        				textRange.setText(String.valueOf(String.format("%.02f", range) + " [m]")); 			
        			}
        		});
        	add(buttonStart); 
        	//this.add(controlPanel, BorderLayout.PAGE_END); //wykorzystanie BorderLayoutu
	    }
	    
	    	   	    
	private class SliderChangeListener implements ChangeListener  //klasa implementacyjna suwaka
    {
		@Override
		public void stateChanged(ChangeEvent e) 
		{
			speedValue = sliderInitialSpeed.getValue();//?
			labelInitialSpeed.setText("Pr�dko�� pocz�tkowa strza�y: " + speedValue + " m/s");	
							
			angleValue = sliderAngleValue.getValue();//?
			labelAngleValue.setText("K�t nachylenia �uku do ziemi: " + angleValue + " �");												
		}	
	} 
	
		
	public static void main(String[] args) 
	{
		Archer1 frame = new Archer1(); 
        frame.setVisible(true);
	}

}
