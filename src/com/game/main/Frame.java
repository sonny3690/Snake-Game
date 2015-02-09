package com.game.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Frame extends JFrame implements KeyListener {

	private Panel pane;
	private KeyInput ki;

	public static final int WIDTH = 800, HEIGHT = 600;

	// INITIALIZES OBJECTS
	public void init() {
		pane = new Panel(this);
		ki = new KeyInput(pane);

		addKeyListener(this);
	}

	public void setFrame() {
		init();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		add(pane);
		pack();
		setVisible(true);

	}

	public Frame() {
		setFrame();
	}

	public static void main(String[] args) {
		new Frame();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		ki.keyPressed(e);
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		ki.keyReleased(e);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
