package model.tetrisgrid;

public class Grid {
	private boolean [][] tetrisGrid;
	public static final int DEFAULT_ROWS = 18;
	public static final int DEFAULT_COLUMNS = 10;
	
	/**
	 * Initializes grid to default 18 rows by 10 columns.
	 */
	public Grid() {
		tetrisGrid = new boolean[DEFAULT_ROWS + 2][DEFAULT_COLUMNS + 2];
		resetGrid();
		initBoundaries();
	}

	/* WILL NOT BE USED, FOR NOW
	public Grid(int rows, int columns) {
		if (rows < 0 || columns < 0)
			throw new IllegalArgumentException("row and columns must be positive");
		
		tetrisGrid = new boolean[rows][columns];
		resetGrid();
	}
	*/
	
	private void initBoundaries() {
		initLeftSideBoundary();
		initRightSideBoundary();
		initTopSideBoundary();
		initBottomSideBoundary();
	}
	
	private void initLeftSideBoundary() {
		// initialize left side boundary of grid
		for (int row = 0; row < tetrisGrid.length; row++) 
			tetrisGrid[row][0] = true;
	}
	
	private void initRightSideBoundary() {		
		// initialize right side boundary of grid
		for (int row = 0; row < tetrisGrid.length; row++) 
			tetrisGrid[row][tetrisGrid[0].length - 1] = true;
	}
	
	private void initTopSideBoundary() {
		// initialize top side boundary of grid
		for (int column = 0; column < tetrisGrid[0].length; column++)
			tetrisGrid[0][column] = true;
	}
	
	private void initBottomSideBoundary() {
		// initialize bottom side boundary of grid
		for (int column = 0; column < tetrisGrid[0].length; column++)
			tetrisGrid[tetrisGrid.length - 1][column] = true;
	}
	
	/**
	 * Resets the grid.
	 */
	public void resetGrid() {
		// remember the boundaries
		for (int row = 1; row < tetrisGrid.length - 1; row++) {
			for (int column = 1; column < tetrisGrid[0].length - 1; column++) {
				tetrisGrid[row][column] = false;
			}
		}
	}
	
	/**
	 * Checks whether row,column position is empty
	 * @param row
	 * @param column
	 * @return True if empty, false otherwise
	 * @throws ArrayOutOfBoundsException 
	 */
	public boolean isEmpty(int row, int column) {
		if (!isValidPosition(row, column))
			return false;
		
		return (tetrisGrid[row][column] == false);
	}
	
	/**
	 * Sets (row, column) to be occupied.
	 * @param row
	 * @param column
	 */
	public void occupyPosition(int row, int column) {
	   if (isValidPosition(row, column))
		   tetrisGrid[row][column] = true;
	}
	
	/**
	 * Sets (row, column) to be empty
	 * @param row
	 * @param column
	 */
	public void unoccupyPosition(int row, int column) {
		if (isValidPosition(row, column))
			tetrisGrid[row][column] = false;
	}
	
	/**
	 * Clears the rows given.
	 * @param rows
	 */
	public void clearRows(int... rows) {
		for (int i = 0; i < rows.length; i++) {
			if (isValidPosition(rows[i], 1)) {
				for (int column = 1; column <= DEFAULT_COLUMNS; column++)
					unoccupyPosition(rows[i], column);
			}
		}
	}
	
	public int getStartGridRow() {
		// with boundaries, we start at 1.
		return 1;
	}
	
	public int getStartGridColumn() {
		return 1;
	}
	
	public int getEndGridRow() {
		return tetrisGrid.length - 2;
	}
	
	public int getEndGridColumn() {
		return tetrisGrid[0].length - 2;
	}
	
	public boolean[][] getGridDataCopy() {
		// retrieve copy of row, column info from original grid.
		int tempGridRowLength = getEndGridRow();
		int tempGridColumnLength = getEndGridColumn();
		
		boolean [][] temp = new boolean[tempGridRowLength][tempGridColumnLength];
		
		for (int row = 0; row < tempGridRowLength; row++) {
			for (int column = 0; column < tempGridColumnLength; column++) {
				int currentGridRow = row + 1;
				int currentGridColumn = column + 1;
				
				if (!this.isEmpty(currentGridRow, currentGridColumn))
					temp[row][column] = true;
				else
					temp[row][column] = false;
			}
		}
		
		return temp;
	}
	
	/**
	 * Determine if row and column is valid
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean isValidPosition(int row, int column) {
		// minus the boundaries
		return (row < (tetrisGrid.length - 1) && column < (tetrisGrid[0].length - 1)
				&& (row >= 1 && column >= 1));
	}
	
	/*
	 * Testing purposes
	 */
	public void printGrid() {
		for (int row = 0; row < tetrisGrid.length; row++) {
			for (int column = 0; column < tetrisGrid[0].length; column++) {
				if (tetrisGrid[row][column] == true)
					System.out.print("t ");
				else
					System.out.print("f ");
			}
			System.out.println();
		}
	}
	
	/*
	 * Testing purposes
	 */
	private void printGrid(int x, int y) {
		for (int row = 0; row < tetrisGrid.length; row++) {
			if (row == x)
				System.out.print("[");
			
			for (int column = 0; column < tetrisGrid[0].length; column++) {
				if (tetrisGrid[row][column] == true)
					System.out.print(" t ");
				else
					System.out.print(" f ");
			}
			System.out.println();
		}
	}
}
