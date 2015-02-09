package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class ScoreBoard {

	int count = 0;
	private Color c;

	public void tick() {
		count += 3;
	}

	public void addScore(int x) {
		count += x;
	}

	public void render(Graphics g1) {

		int var = count % 1500;

		Graphics2D g = (Graphics2D) g1;

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

		if (count < 1000){
			c = Color.WHITE;
		}else if (count <2000){
			c = Color.CYAN;
		}else if (count < 4000){
			c = Color.YELLOW;
		}else if (count < 7000){
			c = Color.ORANGE;
		}else if (count > 10000){
			c = Color.RED;
		}
		
		g.setColor(c);
		g.setFont(new Font("Serif", Font.BOLD, 20));
		g.drawString(String.format("%d", count), 750, 50);
	}
	
	public int getScore (){
		return count;
	}

}
