package edu.smith.cs.csc212.p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class SudokuGame {

	public static int sudoku[][];
	public static int n = 9;

	public SudokuGame(int sudoku[][]) {
		this.sudoku = sudoku;
	}

	public static boolean isSuitableToPutXThere(int i, int j, int x) {

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

		// Everything looks good.
		return true;
	}

	public static boolean[][] original(int sudoku[][]) {
		boolean[][] A;
		A = new boolean[9][9];
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

	public static void main(String args[]) throws IOException {
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
		A = original(sudoku);

//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(A[i][j] + " ");
//			}
//			System.out.println();
//		}

		while (true) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(sudoku[i][j] + " ");
				}
				System.out.println();
			}
			BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Input your position and number :");

			// To read data using readLine method. Here I will pass
			// SoftwareTestingMaterial.com
			String myInput = myReader.readLine();
			List<String> input = WordSplitter.splitTextToWords(myInput);
			int i = Integer.parseInt(input.get(0));
			int j = Integer.parseInt(input.get(1));
			int number = Integer.parseInt(input.get(2));

		
			if (A[i][j] == true) {

				if (isSuitableToPutXThere(i, j, number)) {
					sudoku[i][j] = number;
				} else {
					System.out.print("Your input is invalid. \n");
				}

			} else {
				System.out.print("This is the original number that cannot be changed. \n"
						+ "Choose another position. \n");
			}

			continue;

		}
	}

}
