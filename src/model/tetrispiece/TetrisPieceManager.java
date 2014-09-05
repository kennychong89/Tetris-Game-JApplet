package model.tetrispiece;

import model.tetrisgrid.GridManager;
import model.utilities.BlocksModifier;

/**
 * Manages a given Tetris piece including moving, rotating, etc.
 * @author Kenny
 */
public class TetrisPieceManager {
	private TetrisPiece currentPiece;
	private GridManager gridManager;
	
	private final int DROP_ONE_DOWN = 1;
	private final int MOVE_ONE_LEFT = -1;
	private final int MOVE_ONE_RIGHT = 1;

	public TetrisPieceManager(TetrisPiece piece) {
		this.gridManager = new GridManager();
		this.currentPiece = piece;
	}
	
	public TetrisPieceManager(TetrisPiece piece, GridManager gridManager) {
		this.gridManager = gridManager;
		this.currentPiece = piece;
	}
	
	public void manageGivenTetrisPiece(TetrisPiece currentPiece) {
		this.currentPiece = currentPiece;
	}
	
	public void placeCurrentTetrisPieceAt(int row, int column) {
		gridManager.updateGrid(currentPiece.getBlocks(), false);
		placePiece(row, column);
		gridManager.updateGrid(currentPiece.getBlocks(), true);
	}
	
	public void moveTetrisPieceLeft() {
		// move the piece to the left on the grid unless it is touching the
		// grid's left corner
		
		if (!gridManager.hasCollidedLeft(currentPiece.getBlocks())) {
			// clear previous piece
			gridManager.updateGrid(currentPiece.getBlocks(), false);	

			// move piece to the left
			shiftPiece(MOVE_ONE_LEFT);

			// update grid
			gridManager.updateGrid(currentPiece.getBlocks(), true);
		}
	}
	
	public void moveTetrisPieceRight() {
		// move the piece to the right on the grid unless it is touching the
		// grid's right corner
		if (!gridManager.hasCollidedRight(currentPiece.getBlocks())) {
			// clear previous piece
			gridManager.updateGrid(currentPiece.getBlocks(), false);

			// move piece to the right
			shiftPiece(MOVE_ONE_RIGHT);

			// update tetris piece
			gridManager.updateGrid(currentPiece.getBlocks(), true);
		}
	}

	public void dropTetrisPiece() {
		// drop the piece one row down on the grid unless it is at the bottom
		if (!gridManager.hasCollidedBelow(currentPiece.getBlocks())) {
			// clear previous piece
			gridManager.updateGrid(currentPiece.getBlocks(), false);

			// move piece down
			dropPiece(DROP_ONE_DOWN);

			// update tetris piece
			gridManager.updateGrid(currentPiece.getBlocks(), true);
		}
	}

	/*
	 * shift piece left (negative) or right (positive). How much shifted depends
	 * on value
	 */
	private void shiftPiece(int value) {
		// no change in row, just column
		BlocksModifier.update(currentPiece.getBlocks(), 0, value);
	}

	/*
	 * drop piece down depending on value
	 */
	private void dropPiece(int value) {
		// no change in column, just row
		BlocksModifier.update(currentPiece.getBlocks(), value, 0);
	}

	/*
	 * place the piece at (row, columN)
	 */
	private void placePiece(int row, int column) {
		BlocksModifier.update(currentPiece.getBlocks(), row, column);
	}
	
	/*
	 * rotate piece counter clock-wise
	 */
	public void rotateTetrisPiece() {
		Block [] blocks = currentPiece.getBlocks();
		
		// clear grid
		gridManager.updateGrid(blocks, false);
		
		// attempt a counter clockwise rotation.
		BlocksModifier.rotateCounterClockWise(blocks);
		
		// check if piece has collided
		if (!gridManager.hasCollidedTop(blocks) && !gridManager.hasCollidedBelow(blocks)) {
			// update tetris piece
			gridManager.updateGrid(blocks, true);
		} else {	
			// rotate back to original form
			BlocksModifier.rotateClockWise(blocks);
			
			gridManager.updateGrid(currentPiece.getBlocks(), true);
		}
	}
}
