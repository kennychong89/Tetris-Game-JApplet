package model.tetrisgrid;

import model.tetrispiece.Block;
import model.utilities.BlocksModifier;

public class GridManager {
	public Grid tetrisGrid;

	// constants
	private final int UP_ONE = -1;
	private final int DROP_ONE = 1;
	private final int MOVE_ONE_LEFT = -1;
	private final int MOVE_ONE_RIGHT = 1;

	public GridManager() {
		tetrisGrid = new Grid();
	}

	// test method - cascades the rest of rows down only if bottom row has been
	// cleared
	public void cascadeRowDown() {

	}

	public void updateGrid(Block [] blocks, boolean fill) {	
		// we're filling
		if (fill)
			fillMultipleGridLocations(blocks);
		else
			unFillMultipleGridLocations(blocks);
	}
	
	public boolean hasCollided(Block [] blocks) {
		return hasCollidedBelow(blocks) 
				|| hasCollidedLeft(blocks)
				|| hasCollidedRight(blocks)
				|| hasCollidedTop(blocks);
	}
	
	public boolean hasCollidedTop(Block [] blocks) {
		return hasCollidedRow(blocks, UP_ONE);
	}

	public boolean hasCollidedBelow(Block [] blocks) {
		return hasCollidedRow(blocks, DROP_ONE);
	}
	
	public boolean hasCollidedLeft(Block [] blocks) {
		return hasCollidedColumn(blocks, MOVE_ONE_LEFT);
	}

	public boolean hasCollidedRight(Block [] blocks) {
		return hasCollidedColumn(blocks, MOVE_ONE_RIGHT);
	}

	public void clearGrid() {
		tetrisGrid.resetGrid();
	}

	public boolean isOccupied(int row, int column) {
		return !tetrisGrid.isEmpty(row, column);
	}

	public boolean[][] getGridInfo() {
		return tetrisGrid.getGridDataCopy();
	}
	
	private boolean hasCollidedColumn(Block [] blocks, int columnChange) {
		int leftSide = BlocksModifier.getLeftLocationOfBlocks(blocks);
		int rightSide = BlocksModifier.getRightLocationOfBlocks(blocks);
		int bottomSide = BlocksModifier.getBottomLocationOfBlocks(blocks);
		int topSide = BlocksModifier.getTopLocationOfBlocks(blocks);
		
		for (int row = topSide; row <= bottomSide; row++) {
			for (int column = leftSide; column <= rightSide; column++) {
				if (BlocksModifier.checkBlockExists(blocks, row, column)) {
					// probe one down to if the cell is occupied
					// but make sure that that cell block is not part of the piece
					if (isOccupied(row, column + columnChange) 
							&& !BlocksModifier.checkBlockExists(blocks, row, column + columnChange)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean hasCollidedRow(Block [] blocks, int rowChange) {
		int leftSide = BlocksModifier.getLeftLocationOfBlocks(blocks);
		int rightSide = BlocksModifier.getRightLocationOfBlocks(blocks);
		int bottomSide = BlocksModifier.getBottomLocationOfBlocks(blocks);
		int topSide = BlocksModifier.getTopLocationOfBlocks(blocks);
		
		for (int row = topSide; row <= bottomSide; row++) {
			for (int column = leftSide; column <= rightSide; column++) {
				if (BlocksModifier.checkBlockExists(blocks, row, column)) {
					// probe one down to if the cell is occupied
					// but make sure that that cell block is not part of the piece
					if (isOccupied(row + rowChange, column) 
							&& !BlocksModifier.checkBlockExists(blocks, row + rowChange, column)) {
						return true;
					}
				}
			}
		}	
		return false;
	}

	private void fillMultipleGridLocations(Block [] blocks) {
		
		for (Block block : blocks) {
			if (block != null) {
				tetrisGrid.occupyPosition(block.getRow(), block.getColumn());
			}
		}
	}

	private void unFillMultipleGridLocations(Block [] blocks) {
	
		for (Block block : blocks) {
			if (block != null) {
				tetrisGrid.unoccupyPosition(block.getRow(), block.getColumn());
			}
		}
	}
}
