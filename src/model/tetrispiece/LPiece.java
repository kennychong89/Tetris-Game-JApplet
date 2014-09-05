package model.tetrispiece;

import model.enums.*;

public class LPiece extends TetrisPiece {
	private Block[] piece;
	
	public LPiece() {
		initPiece();
		setPieceName(TetrisPieceName.L_PIECE.name());
		setBlock(piece);
	}
	
	@Override
	public void initPiece() {
		piece = new Block[4];
		
		int startingRow = 1;
		int startingColumn = 1;
		
		piece[0] = new Block(startingRow, startingColumn);
		piece[1] = new Block(startingRow + 1, startingColumn);
		piece[2] = new Block(startingRow + 2, startingColumn);
		piece[3] = new Block(startingRow + 2, startingColumn + 1);
	}
}
