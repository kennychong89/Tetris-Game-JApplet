package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JApplet;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import model.enums.Actions;
import model.game.TetrisGame;
import model.tetrispiece.Block;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class TetrisApplet extends JApplet {
	private GridPanel gridPanel;
	private ControlPanel controlPanel;
	private TetrisGame tetrisGame;
	private Timer dropPieceTimer;
	
	public void init() {
		setSize(250,450);
		tetrisGame = new TetrisGame();
		
		int gridRows = tetrisGame.getGridRows();
		int gridColumns= tetrisGame.getGridColumns();
		
		gridPanel = new GridPanel(gridRows, gridColumns, getWidth(), getHeight());
		controlPanel = new ControlPanel(this);
		
		add(gridPanel, BorderLayout.CENTER);
		add(controlPanel,BorderLayout.NORTH);
	
		tetrisGame.startGame();
		update(tetrisGame.getPieceData(), true);
		
		dropPieceTimer = new Timer(1000, new DropPieceAction());
		dropPieceTimer.start();
	}

	public void movePieceLeft() {
		performGameIteration(Actions.LEFT);
	}
	
	public void movePieceRight() {
		performGameIteration(Actions.RIGHT);
	}
	
	public void rotatePiece() {
		performGameIteration(Actions.ROTATE);
	}
	
	public void performGameIteration(Actions action) {
		if (tetrisGame.hasCollision()) 
			tetrisGame.getNextPiece();
		else {			
			update(tetrisGame.getPieceData(), false);
			
			// perform action
			tetrisGame.performAction(action);
		
			update(tetrisGame.getPieceData(), true);
		}
	}
	
	private void update(Block [] blocks, boolean filled) {
		for (Block b: blocks) {
			int blockRow = b.getRow();
			int blockColumn = b.getColumn();
			
			// update UI
			updateView(blockRow, blockColumn, filled);
		}
	}
	
	private void updateView(int row, int column, boolean filled) {
		if (filled) 
			gridPanel.updateGrid(row, column, getRandomColor(), filled);
		else
			gridPanel.updateGrid(row, column, Color.WHITE, filled);
	}
	
	private Color getRandomColor() {
		Random random = new Random();
		int colorPicker = random.nextInt(5);
		
		switch(colorPicker) {
			case 0:
				return Color.BLUE;
			case 1:
				return Color.GREEN;
			case 2:
				return Color.YELLOW;
			case 3: 
				return Color.MAGENTA;
			case 4:
				return Color.RED;
			default:
				return Color.BLACK;
		}
	}
	
	private class DropPieceAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// add drop action to the front of the queue.
			//actionsBuffer.add(Actions.DROP);
			performGameIteration(Actions.DROP);
		}	
	}
}


