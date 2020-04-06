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
	    private static final int SLIDER_MIN1 = 0;  //ustawiam wartoœæ minimaln¹ suwaka 1 i 2
	    private static final int SLIDER_MAX1 = 90;  //ustawiam wartoœæ maksymaln¹ suwaka 1 i 2
	    private static final int SLIDER_INIT1 = 0;  //ustawiam wartoœæ pocz¹tkow¹ suwaka 1 i 2
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
	    private int speedValue; 	//zmienne niezbêdne do ChangeListenera
	    private int angleValue;
	   
	    private double range;
	    private double maxheight;
	    private double flighttime;	   	 
	    	   
	    //menu
	    private JMenuBar menuBar;  //Tworzê pasek, w którym umieszczam 2 opcje: Menu oraz More
	    private JMenu menu;
	    private JMenu more;
	    private JMenuItem itemExit; //Tworzê elementy, które bêd¹ zawarte w opcjach Menu i More
	    private JMenuItem itemSave;
	    private JMenuItem itemNew;
	    private JMenuItem itemLoad;
	    private JMenuItem itemAuthors;

	    //panels
	    private JPanel animationPanel;  //Tworzê 2 panele
	    private JPanel controlPanel;
	    
	    //bottom panel
	    private JSlider sliderAngleValue;  //Tworzê 2 suwaki 
	    private JSlider sliderInitialSpeed;  	  	   
	    private JTextField textAirResistance; //Tworzê pola niezbêdne pola tekstowe, guziki, etykiety oraz pole wyboru
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
	    	setTitle("Archer");		//ustawiam tytu³ ramki
	    	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        setLayout(null);	       
	        this.setResizable(true);  //u¿ytkownik nie mo¿e zmieniæ rozmiaru ramki

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
	        		itemLoad = new JMenuItem("Wczytaj grê");
	        		itemLoad.addActionListener(new ActionListener() 
	        		{
	        			@Override
	        			public void actionPerformed(ActionEvent e) 
	        			{
	                
	        			}
	        		});
	        		itemExit = new JMenuItem("Wyjœcie");
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
					itemAuthors = new JMenuItem("Twórcy"); //wyœwietlenie informacji na temat autorów programu
					itemAuthors.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent arg0) 
						{
							JOptionPane.showMessageDialog(itemAuthors, "Program zosta³ napisany przez \n Aleksandrê Trelê oraz Bartosza Kucharskiego.");
						}	
					});
				more.add(itemAuthors);	//dodaje elementy do More
	        menuBar.add(menu); //dodaje opcje do paska
	        menuBar.add(more);
	        this.setJMenuBar(menuBar);
	        
	        //panel z animacj¹
	        animationPanel = new JPanel();
	        animationPanel.setBounds(1, 1, 1250, 400); //ustawiam rozmiar panelu
	        animationPanel.setBackground(Color.white); //ustawiam kolor panelu
	        //add(animationPanel);
	        this.add(animationPanel, BorderLayout.CENTER); //wykorzystanie BorderLayoutu
	        
	            
	        //panel ustawieñ
	        controlPanel = new JPanel();	       	        				
	        	labelAngleValue = new JLabel("K¹t nachylenia ³uku do ziemi: 0 °"); //Dodaje etykietê nad suwakiem 1
	        	labelAngleValue.setBounds(10, 390, 200, 50); //ustawiam po³o¿enie etykiety
	        add(labelAngleValue); 
	        	
	        	sliderAngleValue = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN1, SLIDER_MAX1, SLIDER_INIT1);
	        	sliderAngleValue.setBounds(10, 425, 200, 50); //ustawiam po³o¿enie suwaka
	        	sliderAngleValue.setPreferredSize(new Dimension(200, 50));  //rozmiar suwaka
	        	sliderAngleValue.setMajorTickSpacing(30);  //wartoœci na podzia³ce co 30
	        	sliderAngleValue.setMinorTickSpacing(5);  //ka¿dy kolejny punkt na podzia³ce wiêkszy o 5
	        	sliderAngleValue.setPaintTicks(true);
	        	sliderAngleValue.setPaintLabels(true);
	        	sliderAngleValue.addChangeListener(new SliderChangeListener());	//dodaje ChangeListener
	        add(sliderAngleValue);
	      	
	        	labelInitialSpeed = new JLabel("Prêdkoœæ pocz¹tkowa strza³y: 0 m/s"); //Dodaje etykietê nad suwakiem 2
	        	labelInitialSpeed.setBounds(10, 465, 250, 50); 
	        add(labelInitialSpeed);
	        	
	        	sliderInitialSpeed = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN2, SLIDER_MAX2, SLIDER_INIT2);
	        	sliderInitialSpeed.setBounds(10, 500, 200, 50);
	        	sliderInitialSpeed.setPreferredSize(new Dimension(200, 50)); 
	        	sliderInitialSpeed.setMajorTickSpacing(10);  //wartoœci na podzia³ce co 10
	        	sliderInitialSpeed.setMinorTickSpacing(2);  //ka¿dy kolejny punkt na podzia³ce wiêkszy o 2
	        	sliderInitialSpeed.setPaintTicks(true);
	        	sliderInitialSpeed.setPaintLabels(true);
	        	sliderInitialSpeed.addChangeListener(new SliderChangeListener());	//dodaje ChangeListener        	         
	        add(sliderInitialSpeed);
	        	        		     		
	        	
	        	labelMass = new JLabel("Masa wybranej strza³y: ");  //etykieta z wyswietlana mas¹
	        	labelMass.setBounds(230, 390, 200, 50);
	        	add(labelMass);
	        	  
	        	comboboxMass = new JComboBox<String>(); //dodaje pole wyboru oraz tworzê mu opcje
	        	comboboxMass.setBounds(230, 425, 165, 20); //ustawiam po³o¿enie pola wyboru
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
	        				labelMass.setText("Masa wybranej strza³y: " + mass1 + " kg");	//7 kg
	        			}
	        			if (comboboxMass.getSelectedItem().equals("aluminiowa"))	      			
	        			{
	        				labelMass.setText("Masa wybranej strza³y: " + mass2 + " kg");	//2.4 kg
	        			}
	        			if (comboboxMass.getSelectedItem().equals("tytanowa"))	      			
	        			{
	        				labelMass.setText("Masa wybranej strza³y: " + mass3 + " kg");	//13 kg
	        			}
	        		}
	        	});
	        add(comboboxMass);
	     
	        	labelAirResistance = new JLabel("Opór powietrza");	       
	        	labelAirResistance.setBounds(425, 390, 120, 50);	
		    add(labelAirResistance); 	 
		    	textAirResistance = new JTextField("");  //pole tekstowe, w którym wyœwietlaæ siê bêdzie opór powietrza
		    	textAirResistance.setBounds(425, 425, 120, 30);
	       	add(textAirResistance);
	       		buttonRandom = new JButton("Losuj");  //przycisk Losuj, który generuje liczbê z zakresu 1-100
	       		buttonRandom.setBounds(425, 455, 120, 30);	
	        	buttonRandom.addActionListener(new ActionListener() 
	        	{
	        		@Override
	        		public void actionPerformed(ActionEvent e) 
	        		{	        			
	        			Random rand = new Random();
	        		    resistance = rand.nextInt(100)+1;	        			      			
	        			textAirResistance.setText(String.valueOf(resistance)); //wyswietli wylosowan¹ wartoœæ oporu
	        		}	        				        		
	        	});
	        add(buttonRandom);
	        
	        	labelFlightTime = new JLabel("Czas lotu strza³y");	        
	        	labelFlightTime.setBounds(570, 390, 200, 50);	
	        add(labelFlightTime); 	        
	        	textFlightTime = new JTextField(); //pole tekstowe, w którym wyœwietlaæ siê bêdzie Czas lotu strza³y (korzystamy ze wzorów ze specyfikacji)
	        	textFlightTime.setBounds(570, 425, 150, 30);	
	        add(textFlightTime);
	       
	        	labelMaxHeight = new JLabel("Maksymalna wysokoœæ");	
	        	labelMaxHeight.setBounds(740, 390, 200, 50);
	        add(labelMaxHeight); 	       
	        	textMaxHeight = new JTextField(); //pole tekstowe, w którym wyœwietlaæ siê bêdzie Maksymalna wysokoœæ (korzystamy ze wzorów ze specyfikacji)
	        	textMaxHeight.setBounds(740, 425, 150, 30);
	        add(textMaxHeight);
	        
	     		labelRange = new JLabel("Zasiêg strza³y");
	     		labelRange.setBounds(910, 390, 200, 50);
	        add(labelRange); 
	        	textRange = new JTextField();  //pole tekstowe, w którym wyœwietlaæ siê bêdzie Zasiêg strza³y (korzystamy ze wzorów ze specyfikacji)
	        	textRange.setBounds(910, 425, 150, 30);
	        add(textRange);
	        	      
	        	buttonStart = new JButton("Start/Stop");  //przycisk, który pozwoli uruchomiæ i wstrzymaæ grê
	        	buttonStart.setBounds(1080, 425, 150, 50);		
        		buttonStart.addActionListener(new ActionListener() 
        		{
        			@Override
        			public void actionPerformed(ActionEvent e) //Math.sin(Math.toRadians(cos)) - wzor zeby z tego co mamy zrobic to co chcemy xd
        			{					
        				flighttime = (2 * speedValue * Math.sin(Math.toRadians(angleValue)) ) / g; //wzory
        				textFlightTime.setText(String.valueOf(String.format("%.02f", flighttime) + " [s]")); //wyswietli  wartoœæ oporu
        				        			       				        				
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
			labelInitialSpeed.setText("Prêdkoœæ pocz¹tkowa strza³y: " + speedValue + " m/s");	
							
			angleValue = sliderAngleValue.getValue();//?
			labelAngleValue.setText("K¹t nachylenia ³uku do ziemi: " + angleValue + " °");												
		}	
	} 
	
		
	public static void main(String[] args) 
	{
		Archer1 frame = new Archer1(); 
        frame.setVisible(true);
	}

}
