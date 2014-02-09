package menue;

import graphics.frame;

import javax.swing.JPanel;

import java.awt.GridLayout;

import javax.swing.JButton;

import projekt.main;

import java.awt.Color;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class menue extends JPanel {
public key keys = new key();
	/**
	 * Create the panel.
	 */
	public menue() {
		setBackground(Color.BLACK);
		setLayout(null);
		
		
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {frame.content = "game";}
		    
		});
		
		btnNewGame.setBackground(Color.RED);
		btnNewGame.setBounds(412, 267, 200, 70);
		add(btnNewGame);
		

		
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.setBackground(Color.RED);
		
		btnContinue.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {frame.content = "game";}
		});
		
		btnContinue.setBounds(412, 363, 200, 70);
		add(btnContinue);
		
		
		
		
		JButton btnOptions = new JButton("Options");
		btnOptions.setBackground(Color.RED);
		
		btnOptions.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {frame.content = "game";}
		});
			
		
		btnOptions.setBounds(412, 462, 200, 70);
		add(btnOptions);
		
		
		
		
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(Color.RED);
		
		btnExit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {System.exit(0);}
		});
			
		
		btnExit.setBounds(412, 562, 200, 70);
		add(btnExit);

	}


	
	public void action() throws InterruptedException {
	  while (frame.content == "menue"){
	   	 repaint();
	     Thread.sleep(10);	

	  }

	}
	



}
