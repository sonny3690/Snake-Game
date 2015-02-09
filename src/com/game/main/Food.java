package com.game.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Food {

	private Panel pane;
	private int x, y, size;
	private Random rand;
	private BufferedImage foodImage = null;

	public Food(Panel pane, int x, int y, int size) {
		this.pane = pane;
		this.x = x;
		this.y = y;
		this.size = size;

		rand = new Random();
	}

	public void render(Graphics g) {
		try {
			
				foodImage = ImageIO
						.read(new File(
								"E:\\Programming\\Java Workspace\\Snake\\food.png"));
			g.drawImage(foodImage, x, y, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void refresh() {
		int xRandom = rand.nextInt(760);
		int yRandom = rand.nextInt(560);
		setX(xRandom-xRandom%size);
		setY(yRandom-yRandom%size);
		
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 30, 35);
	}

}
