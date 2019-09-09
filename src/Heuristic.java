import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class tries to solve the Knight's Problem with a heuristic. 
 * 
 * @author Elizabeth Fultz
 * @version 2.3
 * Project 1 (Knight's Tour Problem Project)
 * Fall 2019
 */

/**
 * @author elifu
 *
 */
public class Heuristic {

	private final static int base = 8; //used as the dimensions of the chess board and as the total number of moves a knight can make
	private final static int[][] board = {{2, 3, 4, 4, 4, 4, 3, 2},
										  {3, 4, 6, 6, 6, 6, 4, 3},
										  {4, 6, 8, 8, 8, 8, 6, 4},
										  {4, 6, 8, 8, 8, 8, 6, 4},
										  {4, 6, 8, 8, 8, 8, 6, 4},
										  {4, 6, 8, 8, 8, 8, 6, 4},
										  {3, 4, 6, 6, 6, 6, 4, 3},
										  {2, 3, 4, 4, 4, 4, 3, 2}}; //initializes the heuristic on the chess board
	private final static int[][] possibleMoves = {{1,-2},{-2,1},{2,-1},{-1,2},{1,2},{2,1},{-2,-1},{-1,-2}}; //used as the possible moves for a knight
	private static int moveNumber = 1; //used to count the number of moves made
	private static int finalX; //the final x coordinate of the knight
	private static int finalY; //the final y coordinate of the knight
	
	public static void main(String[] args) {
		
		int startX, startY;
		String fileContent = "";
		
		for(int i = 0; i < base; i++) {
			for(int k = 0; k < base; k++) {
			
				startX = i;
				startY = k;
				
				board[startX][startY]--;
				
				move(startX, startY);
				
				if(startX == finalX && startY == finalY && moveNumber == 64)
					fileContent = fileContent+ "[" + startX + ", " + startY + "] " + moveNumber + " [" + finalX + ", " + finalY + "]*\n";
				else
					fileContent = fileContent+ "[" + startX + ", " + startY + "] " + moveNumber + " [" + finalX + ", " + finalY + "]\n";
			
			}//k for loop
			
		}//i for loop
		
		 try {
		    	
				BufferedWriter filewrite = new BufferedWriter(new FileWriter("HeuristicOutput.txt"));
				filewrite.append(fileContent);
				filewrite.close();
				
				} //end try
		 
		 catch (IOException ex) {
					
					System.out.println("Garbage Fire: failed to write to file.");
				
				}//end catch
		
	}//end main
	
	
	/**
	 * This method checks the values of x and y that the knight will move to to make sure those coordinates exist on the chess board
	 * 
	 * @param xValue the value of x to be checked
	 * @param yValue the value of y to be checked
	 * @return true is the coordinates are valid, false if the coordinates are invalid
	 */
	public static boolean moveCheck(int xValue, int yValue) {
		
		if(xValue >= 0 && xValue < base && yValue >= 0 && yValue < base) {
				if(board[xValue][yValue] != 0) 
					return true;
			}else
				return false;
		
		return false;
		
	}//end move check
	
	/**
	 * This method makes the moves of the knight to solve the problem
	 * @param nextX the value of x to be moved to
	 * @param nextY the value of y to be moved to
	 * @return true if a complete tour is possible, false if a complete tour is not possible
	 */
	public static boolean move(int nextX, int nextY) {
		
		int[] squareNumber = new int[base];
		
		if(moveNumber == 64) {
			finalX = nextX;
			finalY = nextY;
			 return true;
		}
		
		for(int i = 0; i < base; i++) {
			
			int moveX = nextX + possibleMoves[i][0];
			int moveY = nextY + possibleMoves[i][1];
			
			if(moveCheck(moveX, moveY)) {
				
				squareNumber[i] = board[moveX][moveY];
			
				int index = lowestArray(squareNumber);
				
				moveNumber++;
				
				int lowestMoveX = moveX + possibleMoves[index][0];
				int lowestMoveY = moveY + possibleMoves[index][1];
				
				if(moveCheck(lowestMoveX, lowestMoveY))
					board[lowestMoveX][lowestMoveY]--;
				
				if(move(moveX, moveY))
					return true;
					
			} else
					return false;
				
		}//for
			
		return false;
		
	}//end move
	
	public static int lowestArray(int[] array) {
		
		int minValue = array[0];
		int index = 0;
		
		for(int i = 0; i < base; i++ ) {
			if(array[i] < minValue)
				minValue = array[i];
				index = i;
		}//end for loop
		
		return index;
		
	}//end lowestArray
	
}//end class
