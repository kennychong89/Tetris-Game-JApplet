package model.game;

import java.util.Scanner;

import model.enums.Actions;

public class Test {
	public static void main(String [] args) {
		Scanner scanner = new Scanner(System.in);
		
		TetrisGame game = new TetrisGame();
		game.startGame();
		System.out.println("Here is the grid: ");
		boolean [][] grid = game.getPieceDataBoolean();
		printGrid(grid);
		
		while (true) {
			System.out.print("(D)rop: ");
			String input = scanner.next();
			Actions action = getActionFromInput(input);
		
			if (action == null) {
				System.out.println("Invalid action, try again");
				continue;
			} else {
				int counter = 1;
				
				if (action == Actions.DROP) {
					System.out.print("How many times?: ");
					input = scanner.next();
					counter = Integer.parseInt(input);
					
					while(counter > 0) {
						game.setAction(action);
						game.startGameLoop();
						System.out.println();
						
						grid = game.getPieceDataBoolean();
						
						counter--;
					}
				} else {
					game.setAction(action);
					game.startGameLoop();
					System.out.println();
					
					grid = game.getPieceDataBoolean();
				}
				printGrid(grid);
			}
		}
		
	}
	
	public static void printGrid(boolean [][] grid) {
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[0].length; column++) 
				if (grid[row][column])
					System.out.print("[] ");
				else
					System.out.print("x  ");
			
			System.out.println();
		}
	}
	
	public static Actions getActionFromInput(String input) {
		Actions action = null;
		
		String lowercaseInput = input.toLowerCase();
		
		if (lowercaseInput.equals("d"))
			action = Actions.DROP;
		
		return action;
	}
}
