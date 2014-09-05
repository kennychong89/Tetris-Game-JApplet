package model.tetrispiece;

/**
 * Representation of a single 1x1 Tetris piece block.
 * @author kenny
 */
public class Block {
	private int row;
	private int column;
	
	public Block() {
		this.row = 0;
	
	}
	
	public Block(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public String toString() {
		return "row : " + row + " column: " + column;
	}
}