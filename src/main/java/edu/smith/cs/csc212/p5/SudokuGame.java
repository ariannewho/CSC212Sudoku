package edu.smith.cs.csc212.p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class SudokuGame {

	public static int sudoku[][];
	public static int n = 9;
	public static boolean isComplete = false;

	public SudokuGame(int sudoku[][]) {
		this.sudoku = sudoku;
	}

	/**
	 * Does the input number satisfy the rules of Sudoku?
	 * To be considered as solved, a Sudoku grid must respect some constraints :
     *Each row that composes the grid must contain all of the digits from 1 to 9
     *Each column that composes the grid must contain all of the digits from 1 to 9
     *Each nine 3x3 subgrids composing the grid must contain all of the digits from 1 to 9
     *
	 * @return boolean true(valid) or false(invalid)
	 */
	public static boolean isValid(int i, int j, int x) {
		

		// Is 'x' used in row.
		for (int jj = 0; jj < n; jj++) {
			if (sudoku[i][jj] == x) {
				return false;
			}
		}

		// Is 'x' used in column.
		for (int ii = 0; ii < n; ii++) {
			if (sudoku[ii][j] == x) {
				return false;
			}
		}

		// Is 'x' used in sudoku 3x3 box.
		int boxRow = i - i % 3;
		int boxColumn = j - j % 3;

		for (int ii = 0; ii < 3; ii++) {
			for (int jj = 0; jj < 3; jj++) {
				if (sudoku[boxRow + ii][boxColumn + jj] == x) {
					return false;
				}
			}
		}

		// If all rules are satisfied
		return true;
	}

	/**
	 * To keep a record of which position can be modified by the user
	 * true = modifiable 
	 * false = not modifiable
	 * @return a 2D boolean array table
	 */
	public static boolean[][] modifiable(int sudoku[][]) {
		boolean[][] A;
		A = new boolean[n][n];
		
		// "0" positions can be changed
		// other numbers are static which belong to the original Sudoku puzzle
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				if (sudoku[row][col] != 0) {
					A[row][col] = false;
				} else {
					A[row][col] = true;
				}
			}
		}
		return A;

	}
	
	/**
	 * Is the puzzle complete?
	 * true = complete 
	 * false = not complete
	 * @return boolean true or false
	 */
	public static boolean isComplete(int sudoku[][]) {
		
		// if the board contains 0, then there's still way to go!
		for (int ii = 0; ii < n ; ii++) {
			for (int jj = 0; jj < n ; jj++) {
				if (sudoku[ii][jj] == 0) {
					isComplete = false;
					
				}else {
					isComplete = true;
				}
			}
		}return isComplete;
	}
	
	/**
	 * This is our Sudoku game
	 * 
	 */
	public static void main(String args[]) throws IOException {
		// This is our puzzle
		new SudokuGame(new int[][] { { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, 
                                     { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                                     { 0, 8, 7, 0, 0, 0, 0, 3, 1 }, 
                                     { 0, 0, 3, 0, 1, 0, 0, 8, 0 }, 
                                     { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                                     { 0, 5, 0, 0, 9, 0, 6, 0, 0 }, 
                                     { 1, 3, 0, 0, 0, 0, 2, 5, 0 }, 
                                     { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                                     { 0, 0, 5, 2, 0, 6, 3, 0, 0 } });

		boolean[][] A;
		A = modifiable(sudoku);


		while (true) {
			
			// print out the puzzle
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(sudoku[i][j] + " ");
				}
				System.out.println();
			}
			
			// ask the user for input
			BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Input your position and number :");

			// To read data using readLine and WordSplitter
			String myInput = myReader.readLine();
			List<String> input = WordSplitter.splitTextToWords(myInput);
			int i = Integer.parseInt(input.get(0));
			int j = Integer.parseInt(input.get(1));
			int number = Integer.parseInt(input.get(2));
			

			// If the position is modifiable
			if (A[i][j] == true) {
				// If the input is valid
				if (isValid(i, j, number)) {
					sudoku[i][j] = number;
				} else {
					System.out.println("Your input is invalid. \n");
				}
			} else {
				System.out.println("This is the original number that cannot be changed. \n"
						+ "Choose another position. \n");
			}
			
			// If the puzzle is not complete yet, continue
			if (!isComplete(sudoku)) {
				continue;
			}
			
			// If the puzzle is complete, exit the loop
			break;


		}
		// We're done here!
		System.out.println("Yay, you win!");
	}

}
