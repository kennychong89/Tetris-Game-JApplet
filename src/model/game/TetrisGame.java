package model.game;

import model.enums.Actions;
import model.tetrisgrid.GridManager;
import model.tetrispiece.Block;
import model.tetrispiece.TetrisPiece;
import model.tetrispiece.TetrisPieceGenerator;
import model.tetrispiece.TetrisPieceManager;
import model.utilities.BlocksModifier;

public class TetrisGame {
	public GridManager gridManager;
	private TetrisPieceManager tetrisPieceManager;
	private TetrisPieceGenerator tetrisPieceGenerator;
	private TetrisPiece currentPiece;
	private boolean hasStarted;
	private Actions currentAction;
	
	public TetrisGame() {
		gridManager = new GridManager();
		tetrisPieceGenerator = new TetrisPieceGenerator();
		currentPiece = tetrisPieceGenerator.getRandomPiece();
		tetrisPieceManager = new TetrisPieceManager(currentPiece, gridManager);
		
		hasStarted = false;
		
		// no action yet
		currentAction = null;
	}

	public boolean hasStarted() {
		return hasStarted;
	}
	
	public void setStart(boolean val) {
		hasStarted = val;
	}
	
	public boolean hasPerformedAction() {
		return currentAction != null;
	}
	
	public Actions getCurrentAction() {
		return currentAction;
	}
	
	public void startGame() {
		hasStarted = true;
		
		// reset grid
		gridManager.clearGrid();
		
		// generate random piece
		currentPiece = tetrisPieceGenerator.getRandomPiece();
		
		tetrisPieceManager.manageGivenTetrisPiece(currentPiece);
		tetrisPieceManager.placeCurrentTetrisPieceAt(0, 0);
		
		setAction(Actions.DROP);
	}
	
	public void performAction(Actions action) {
		switch(action) {
		case LEFT:
			tetrisPieceManager.moveTetrisPieceLeft();
			break;
		case RIGHT:
			tetrisPieceManager.moveTetrisPieceRight();
			break;
		case DROP:
			tetrisPieceManager.dropTetrisPiece();
			break;
		case ROTATE:
			tetrisPieceManager.rotateTetrisPiece();
			break;
		}
	}
	
	public void setAction(Actions action) {
		this.currentAction = action;
	}
	
	public Block[] getPieceData() {
		Block [] blockCopy = BlocksModifier.getBlocksCopy(currentPiece.getBlocks());
		
		// start from (0,0) position in grid not (1,1)
		BlocksModifier.update(blockCopy, -1, -1);
		
		return blockCopy;
	}

	public void startGameLoop() {
		if (!hasStarted())
			startGame();
		else {
			if (hasCollision()) {
				
				getNextPiece();
			} else {
				Actions userAction = getCurrentAction();
				
				performAction(userAction);
			}
		}
	}
	
	public boolean [][] getPieceDataBoolean() {
		return gridManager.getGridInfo();
	}
	
	public int getGridColumns() {
		return gridManager.getGridInfo()[0].length;
	}
	
	public int getGridRows() {
		return gridManager.getGridInfo().length;
	}
	
	public boolean hasCollision() {
		return hasCollided();
	}
	
	public void getNextPiece() {
		currentPiece = tetrisPieceGenerator.getRandomPiece();
		tetrisPieceManager.manageGivenTetrisPiece(currentPiece);
	}
	
	private boolean hasCollided() {
		return gridManager.hasCollidedBelow(currentPiece.getBlocks());
	}
}
