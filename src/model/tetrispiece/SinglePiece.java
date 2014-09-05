package model.tetrispiece;

import model.enums.*;

public class SinglePiece extends TetrisPiece {
	private Block[] piece;
	
	public SinglePiece() {
		initPiece();
		setPieceName(TetrisPieceName.SINGLE_PIECE.name());
		setBlock(piece);
	}
	
	@Override
	public void initPiece() {
		piece = new Block[1];
		
		int startingRow = 1;
		int startingColumn = 1;
		
		piece[0] = new Block(startingRow, startingColumn);
	}
}
