package model.tetrispiece;

import java.util.Random;

import model.enums.TetrisPieceName;

/**
 * Classes generates random Tetris pieces.
 * Note: Should be a Singleton object.
 * @author kenny
 */
public class TetrisPieceGenerator {

	public TetrisPiece getRandomPiece() {
		TetrisPieceName [] names = TetrisPieceName.values();
		
		// simple random generator
		Random r = new Random();
		int selection = r.nextInt(names.length);
		
		return getPiece(names[selection]);
	}
	
	public TetrisPiece getPiece(TetrisPieceName pieceName) {
		switch(pieceName) {
		case L_PIECE:
			return new LPiece();
		case BOX_PIECE:
			return new BoxPiece();
		default:
			return new SinglePiece();
		}
	}
}
