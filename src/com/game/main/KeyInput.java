package com.game.main;

import java.awt.event.KeyEvent;

public class KeyInput {

	private Panel pane;
	private final int speed;
	private Blocks b;

	private boolean isUpDown = false;
	private boolean isRightLeft = false;

	public KeyInput(Panel pane) {
		this.pane = pane;
		b = pane.getBlocks();

		speed = pane.getBlockSize();

	}

	public void keyPressed(KeyEvent e) {

		int key = e.getExtendedKeyCode();

		if (key == e.VK_DOWN) {

			if (!isUpDown) {
				pane.setVelX(0);
				pane.setVelY(speed);
				isUpDown = true;
				isRightLeft= false;
			}
		}

		if (key == e.VK_UP) {
			if (!isUpDown) {
				pane.setVelX(0);
				pane.setVelY(-speed);
				isUpDown = true;
				isRightLeft = false;
			}
		}

		if (key == e.VK_RIGHT) {
			if (!isRightLeft) {
				pane.setVelX(speed);
				pane.setVelY(0);
				isRightLeft = true;
				isUpDown = false;
			}
		}

		if (key == e.VK_LEFT) {
			if(!isRightLeft){
			pane.setVelX(-speed);
			pane.setVelY(0);
			isRightLeft = true;
			isUpDown = false;
			}
		}

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
