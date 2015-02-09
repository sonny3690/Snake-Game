package com.game.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

//CLASS WHERE EVERYTHING IS RENDERED
public class Panel extends JPanel implements ActionListener {

	private Graphics g;
	private Frame frame;
	private Timer t1;
	private KeyInput ki;
	private Blocks b;
	private Food food;
	private ImageLoader imgLoader;
	private ScoreBoard scoreBoard;

	// GAME VARIABLES
	private int length = 1;
	private int x = 100;
	private int y = 50;
	private BufferedImage backgroundImage;
	private int increment = 0;
	private int ticks, frames;

	private final int size = 20;

	public int velX = size;
	public int velY = 0;

	private int blockNumber = 3;
	private double speed = 100/60;

	private int[][] coordinates = new int[100][2];

	LinkedList<Blocks> blockList = new LinkedList<Blocks>();

	public Panel(Frame frame) {
		this.frame = frame;
		imgLoader = new ImageLoader();
		scoreBoard = new ScoreBoard();

		food = new Food(this, 500, 300, size);

		for (int i = 0; i < blockNumber; i++)
			addBlock(new Blocks(this, x + size * -i, y, size, i));

		// b = new Blocks(this, x, y, size, velX, velY);

		setPreferredSize(new Dimension(800, 600));
		setMaximumSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		setDoubleBuffered(true);

		t1 = new Timer(1000/60, this);
		t1.start();

	}

	public void paintComponent(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, frame.WIDTH, frame.HEIGHT);

		try {
			backgroundImage = ImageIO.read(new File(
					"E:\\Programming\\Java Workspace\\Snake\\background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		g.drawImage(backgroundImage, 0, 0, null);
		// ////BACKGROUND DRAWING/////

		for (int i = 0; i < blockList.size(); i++) {
			blockList.get(i).render(g);
		}

		food.render(g);
		scoreBoard.render(g);

	}

	public void actionPerformed(ActionEvent e) {

		long firstTime = System.nanoTime();
		scoreBoard.tick();
		render();
		frames ++;
		
		
		
		
		if (increment % 10 == 0){
			speed *= 0.50;
		}
		
		increment ++;
		
		if (increment%5==0){
			tick();
			ticks ++;
		}
		
		
	
		System.out.println("Time Lag: " +  (System.nanoTime()-firstTime) + " nanoseconds");
		
		
		
	}

	public void tick() {
		for (int i = 0; i < blockList.size(); i++) {
			blockList.get(i).tick(velX, velY);
			coordinates[i][0] = blockList.get(i).getX();
			coordinates[i][1] = blockList.get(i).getY();
		}

		for (int i = 0; i < blockList.size(); i++) {
			if (i != 0) {
				blockList.get(i).setX(coordinates[i - 1][0]);
				blockList.get(i).setY(coordinates[i - 1][1]);
			}
		}

		checkCollision();
		checkFoodCollision();
	}

	public void render() {
		// System.out.println("HI");
		this.repaint();
		// g.fillRect(7, 8, 100, 100);
	}

	public void checkCollision() {
		for (int i = 0; i < blockList.size(); i++) {
			for (int j = i + 1; j < blockList.size(); j++) {
				if (blockList.get(i).getBounds()
						.intersects(blockList.get(j).getBounds())
						&& Math.abs(i - j) != 1) {
			
					JOptionPane.showConfirmDialog(null, String.format ("GAME OVER! YOUR SCORE: %d", scoreBoard.getScore()));
					t1.stop();
				}
			}
		}
	}

	public void checkFoodCollision() {
		if (blockList.get(0).getBounds().intersects(food.getBounds())) {
			food.refresh();
			addBlock(new Blocks(this, blockList.get(blockNumber - 1).getX()
					- size, blockList.get(blockNumber - 1).getY(), size,
					blockNumber++));
			scoreBoard.addScore(100);
		}
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public Blocks getBlocks() {
		return b;
	}

	public void addBlock(Blocks b) {
		blockList.add(b);
	}

	public int[][] getCoordinates() {
		return coordinates;
	}

	public int getBlockSize() {
		return size;
	}

}
