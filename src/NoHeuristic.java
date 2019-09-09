import java.io.*;

/**
 * This class tries to solve the Knight's Problem without a heruistic. 
 * 
 * @author Elizabeth Fultz
 * @version 3.2
 * Project 1 (Knight's Tour Problem Project)
 * Fall 2019
 */

public class NoHeuristic {
	
	private final static int base = 8; //used as the dimensions of the chess board and as the total number of moves a knight can make
	private final static int[][] board = new int [base][base]; //used as the chess board
	private final static int[][] possibleMoves = {{1,-2},{-2,1},{2,-1},{-1,2},{1,2},{2,1},{-2,-1},{-1,-2}}; //used as the possible moves for a knight
	private static int moveNumber; //used to count the number of moves made
	private static int finalX; //the final x coordinate of the knight
	private static int finalY; //the final y coordinate of the knight

	public static void main(String[] args) throws IOException {
		
		int startX, startY;	
		String fileContent = "";
		
		for(int k = 0; k < 1000; k++) {
			 
				moveNumber = 1;
				
			for(int i = 0; i <= base-1; i++) {
				for(int j = 0; j <= base-1; j++) {
						board[i][j] = 0;
				}//end j loop
			}//end i loop
			
			startX = (int)(Math.random() * base);
			startY = (int)(Math.random() * base);
			
			board[startX][startY] = moveNumber;
			
			move(startX, startY);
			
			if(startX == finalX && startY == finalY && moveNumber == 64)
				fileContent = fileContent+ "[" + startX + ", " + startY + "] " + moveNumber + " [" + finalX + ", " + finalY + "]\n";
			else
				fileContent = fileContent+ "[" + startX + ", " + startY + "] " + moveNumber + " [" + finalX + ", " + finalY + "]\n";
			
		}//end k for loop
		
	    try {
	    	
			BufferedWriter filewrite = new BufferedWriter(new FileWriter("NoHeuristicOutput.txt"));
			filewrite.append(fileContent);
			filewrite.close();
			
			}//end try
	    
	    catch (IOException ex) {
				
				System.out.println("Garbage Fire: failed to write to file.");
				
			} 
		
		
	}//end main

	
	/**
	 * This method checks the values of x and y that the knight will move to to make sure those coordinates exist on the chess board
	 * 
	 * @param xValue the value of x to be checked
	 * @param yValue the value of y to be checked
	 * @return true is the coordinates are valid, false if the coordinates are invalid
	 */
	public static boolean moveCheck(int xValue, int yValue) {
	
		if(xValue >= 0 && xValue < base) {
			if(yValue >= 0 && yValue < base) {
				if(board[xValue][yValue] == 0) 
					return true;
			}else
				return false;
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
		
		if(moveNumber == 64) {
			 return true;
		}
		
		for(int i = 0; i < base; i++) {
			
			int moveX = nextX + possibleMoves[i][0];
			int moveY = nextY + possibleMoves[i][1];
			
			if(moveCheck(moveX, moveY)) {
				moveNumber++;
				finalX = moveX;
				finalY = moveY;
				board[moveX][moveY] = moveNumber;
				if(move(moveX, moveY))
					return true;
			} else
				return false;
			
		}//for
		
		return false;
		
	}//end move
	
}//end class
