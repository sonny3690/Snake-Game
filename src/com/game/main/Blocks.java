package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Blocks {

	private Panel pane;
	private int x, y, size;
	private KeyInput ki;
	private int blockNumber;

	public Blocks(Panel pane, int x, int y, int size, int blockNumber) {
		this.pane = pane;
		this.x = x;
		this.y = y;
		this.size = size;
		
		this.blockNumber = blockNumber;

	}

	public void render(Graphics g) {

		// SNAKE BORDER
		g.setColor(Color.YELLOW);
		g.fillRect(x - 1, y - 1, size + 2, size + 2);

		// ACTUAL SNAKE DRAWING
		g.setColor(Color.GREEN);
		g.fillRect(x, y, size, size);

	}

	public void tick(int velX, int velY) {
		if (blockNumber == 0) {
			x += velX;
			y += velY;
			
			if (x <= -10)
				x = 800;
			else if (x>=810)
				x= 0;
			else if (y<=-10)
				y = 600;
			else if (y>=610)
				y=0;
		}
	}
	
	public void setX (int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Rectangle getBounds (){
		return new Rectangle(x, y, size, size);
	}
}
