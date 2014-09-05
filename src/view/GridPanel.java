package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class GridPanel extends JPanel {
	private TetrisGridBox[][] tetrisGrid;
	private int gridRows;
	private int gridColumns;
	
	public GridPanel(int rows, int columns, int width, int height) {
		this.gridRows = rows;
		this.gridColumns = columns;
		
		tetrisGrid = new TetrisGridBox[gridRows][gridColumns];
		setSize(width, height);
		initGrid();
	}
	
	private void initGrid() {
		int panelWidth = getWidth();
		int panelHeight = getHeight();
		
		int roundedPanelWidth = panelWidth - (panelWidth % (this.gridColumns));
		int roundedPanelHeight = panelHeight - (panelHeight % (this.gridRows));
		
		int boxWidth = roundedPanelWidth / (this.gridColumns);
		int boxHeight = roundedPanelHeight / (this.gridRows);
		
		for (int row = 0; row < tetrisGrid.length; row++) {
			for (int column = 0; column < tetrisGrid[0].length; column++) {
				Rectangle rectangle = new Rectangle((column * boxWidth),
						(row * boxHeight), (column + 1) * boxWidth, (row + 1) * boxHeight);
				
				Color backgroundColor = Color.WHITE;
				Color borderColor = Color.BLACK;
				
				// test
				TetrisGridBox gridBox = new TetrisGridBox(rectangle,
						backgroundColor, borderColor);
				tetrisGrid[row][column] = gridBox;
			}
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		Stroke stroke = g2.getStroke();
		g2.setStroke(new BasicStroke(1));
		
		for (int row = 0; row < tetrisGrid.length; row++) {
			for (int column = 0; column < tetrisGrid[0].length; column++) {
				// create rectangle object
				Rectangle box = tetrisGrid[row][column].getRect();
				Color backgroundColor = tetrisGrid[row][column].getBackgroundColor();
				Color borderColor = tetrisGrid[row][column].getBorderColor();
				
				g2.setColor(backgroundColor);
				g2.fillRect(box.x, box.y, box.width, box.height);
				
				g2.setColor(borderColor);
				g2.setStroke(stroke);
				
				g2.drawRect(box.x, box.y, box.width, box.height);
			}
		}
	}
	
	public int getGridRows() {
		return gridRows;
	}
	
	public int getGridColumns() {
		return gridColumns;
	}
	
	public void updateGrid(int row, int column, Color color, boolean filled) {
		if (row < this.gridRows && column < this.gridColumns) {
			
			TetrisGridBox box = tetrisGrid[row][column];
			
			if (box != null) {	
				if (filled)
					box.setBackgroundColor(color);
				else 
					box.setBackgroundColor(Color.WHITE);
				
				repaint();
			}
		}
	}
	
	private class TetrisGridBox {
		private Rectangle rectangle;
		private Color backgroundColor;
		private Color borderColor;
		
		public TetrisGridBox() {
			rectangle = new Rectangle();
			backgroundColor = Color.WHITE;
			borderColor = Color.BLACK;
		}
		
		public TetrisGridBox(Rectangle rectangle, Color backgroundColor, Color borderColor) {
			this.rectangle = rectangle;
			this.backgroundColor = backgroundColor;
			this.borderColor = borderColor;
		}
		
		public Rectangle getRect() {
			return rectangle;
		}
		
		public Color getBackgroundColor() {
			return backgroundColor;
		}
		
		public Color getBorderColor() {
			return borderColor;
		}
		
		public void setBackgroundColor(Color color) {
			this.backgroundColor = color;
		}
		
		public void setBorderColor(Color color) {
			this.borderColor = color;
		}
 	}
}
