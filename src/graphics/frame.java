 package graphics;
import game.key;
import game.screen;


import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import map.map;
import menue.menue;

public class frame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	

	
	
	public static JPanel contentPane;
	public static screen panel;
	public static menue menu;

	public int f;
	public static String content = "menue";
    
	//DisplayMode screen = new DisplayMode(1024,768,16,75);
	//GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
    //GraphicsDevice device           = environment.getDefaultScreenDevice();	
	
		  //device.setFullScreenWindow(this);
		  //device.setDisplayMode(screen);

	
	
	
	
	public frame() throws InterruptedException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1024, 768);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		
		
		 panel = new screen();
		 menu = new menue();	
	
	    
		    setSize( Toolkit.getDefaultToolkit().getScreenSize() );
		    
		    	//GraphicsDevice device;
		        //device=GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
		        //device.setFullScreenWindow(this);
		        //device.setDisplayMode(new DisplayMode(1024,768,16,0));

		
		
		
		setVisible(true);


		setContentPane(contentPane);

		
	while (true){
		
		requestFocus();
		change(content);
		
	}

		
}

void change(String c) throws InterruptedException{


	if (c == "menue"){
		setContentPane(menu);
		addKeyListener(menu.keys);

		removeKeyListener(panel.keys);

		
		menu.action();

	}

	
	if (c == "game"){
		setContentPane(panel);
		addKeyListener(panel.keys);

		removeKeyListener(menu.keys);

		panel.game();

	    
	}
	
	

	
	
}


}



