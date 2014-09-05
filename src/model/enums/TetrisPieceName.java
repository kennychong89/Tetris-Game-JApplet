package model.enums;

public enum TetrisPieceName {
	SINGLE_PIECE("Single Block"),
	L_PIECE("L-Shape"), 
	BOX_PIECE("BOX-SHAPE");
	
	private String s;
	
	TetrisPieceName(String s) {
		this.s = s;
	}
}
