package othello;

import javax.swing.JLabel;

/* Here is a stub of the OthelloModel, containing all the test boards listed in
 * in the assignment file for your implementation.
 *
 * Don't forget to add your Javadoc!
 */

public class OthelloModel
{
	private int[][] board;
	
    //Some class constants for your use:
    public static final int NORMAL=0;
    public static final int CORNER_TEST=1;
    public static final int OUTER_TEST=2;
    public static final int TEST_CAPTURE=3;
    public static final int TEST_CAPTURE2=4;
    public static final int UNWINNABLE=5;
    public static final int INNER_TEST=6;
    public static final int ARROW=7;

    public static final int EMPTY=0;
    public static final int BLACK=1;
    public static final int WHITE=2;
    
    OthelloViewController vc;
    
    public int[][] boardTemp = new int[8][8];
    
    private boolean isXAxis;
    private boolean isYAxis;
    private boolean isDiagonal;
    
    //X-Axis
    boolean startRightLeft;
    boolean startLeftRight;
    
    //Y-Axis
    boolean startDownUp;
    boolean startUpDown;
    
    //Diagonal
    
    /**
     *   
     *   \
     *    \
     *     \
     *      \
     * 
     */
    boolean diagonalUpDownRight;
    boolean diagonalDownUpLeft;
    
    
    /**
     *      
     *      /
     *     /
     *    /
     *   /
     * 
     */
    boolean diagonalUpDownLeft;
    boolean diagonalDownUpRight;
    
    
    //Default constructor prepares a normal game.
    public OthelloModel(OthelloViewController vc)
    {
    	this.vc = vc;
    	
        prepareBoard(NORMAL);
    }
    
    public int[][] getBoard() {
    	return board;
    }

	public void prepareBoard(int mode)
	{
		switch (mode)
		{
		case CORNER_TEST: 
			board=new int[][]{
				{2, 0, 0, 0, 0, 0, 0, 1},
				{0, 1, 0, 0, 0, 0, 2, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 1, 0, 0, 0, 0, 1, 0},
                {2, 0, 0, 0, 0, 0, 0, 2}};
            break;
                
		case OUTER_TEST:
			board = new int[][] {
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 2, 2, 2, 2, 2, 2, 0},
				{0, 2, 1, 1, 1, 1, 2, 0},
				{0, 2, 1, 0, 0, 1, 2, 0},
				{0, 2, 1, 0, 0, 1, 2, 0},
				{0, 2, 1, 1, 1, 1, 2, 0},
				{0, 2, 2, 2, 2, 2, 2, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}};
			break;
                
		case INNER_TEST:
			board = new int[][] {
				{2, 2, 2, 2, 2, 2, 2, 2},
				{2, 0, 0, 0, 0, 0, 0, 2},
				{2, 0, 2, 2, 2, 2, 0, 2},
				{2, 0, 2, 1, 1, 2, 0, 2},
				{2, 0, 2, 1, 1, 2, 0, 2},
				{2, 0, 2, 2, 2, 2, 0, 2},
				{2, 0, 0, 0, 0, 0, 0, 2},
				{2, 2, 2, 2, 2, 2, 2, 2}};
			break;
                
		case UNWINNABLE:
			board = new int[][] {
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}};
			break;
                
		case TEST_CAPTURE:
			board=new int[][]{
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 1, 1, 1, 1, 1, 1, 0},
				{0, 1, 1, 1, 1, 1, 1, 0},
				{0, 1, 2, 2, 2, 1, 1, 0},
				{0, 1, 2, 0, 2, 1, 1, 0},
				{0, 1, 2, 2, 2, 1, 1, 0},
				{0, 1, 1, 1, 1, 1, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}};
				break;
				
		case TEST_CAPTURE2:
			board=new int[][]{
				{1, 1, 1, 1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1, 1, 1, 1},
				{1, 2, 2, 2, 1, 2, 1, 1},
				{1, 2, 2, 2, 2, 2, 1, 1},
				{1, 2, 2, 0, 2, 2, 1, 1},
				{1, 2, 2, 2, 2, 1, 1, 1},
				{1, 2, 1, 2, 2, 2, 1, 1},
				{1, 1, 1, 1, 1, 1, 1, 1}};
				break;
                
            case ARROW:
                board=new int[][]{
                {0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 0, 0},
                {0, 1, 0, 1, 1, 0, 1, 0},
                {1, 0, 0, 1, 1, 0, 0, 1},
                {0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0}};
                break;
                
		default:
			board = new int[][]{
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 2, 1, 0, 0, 0},
				{0, 0, 0, 1, 2, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}};
		}	
	}
	
	public void setBoard() {
		
		vc.defaultBoard = board;
		
		
		for (int i = 0; i < 8 ; i++) {
			for (int j = 0; j < 8 ; j++) {
				if (board[i][j] == 1) {
					vc.gameBoard[i][j].setIcon(vc.blackPlayer);
					vc.bPieceCount++;
				}
				if (board[i][j] == 2) {
					vc.gameBoard[i][j].setIcon(vc.whitePlayer);
					vc.wPieceCount++;
				}
				if (board[i][j] == 0) {
					vc.gameBoard[i][j].setIcon(null);
				}
				
				vc.gameBoard[i][j].setHorizontalAlignment(JLabel.CENTER);
				
			}
		}
	}
	
	
	
    
    //This method will return the value of a given square.
	public int getSquare(int row, int col)
	{
		
		for (int i = 0; i < 8 ; i++) {
			for (int j = 0; j < 8 ; j++) {
				if(board[row][col] == 0)
					return 0;
				else if(board[row][col] == 1)
					return 1;
				else if(board[row][col] == 2)
					return 2;
			}
		}
		return 0;


	}
	

    //Can the player make a valid move at the square specified?
	public boolean canMove(int row, int col, int player)
	{
	   	 for(int i = 0; i < board.length; i++) {
				 for(int j = 0; j < board.length; j++) {
					if(boardTemp[row][col] == 3) return true;
				 }
			 }
	   	 
	   	 return false;
    }
	
	public void flipSigns(int pos) {
		startRightLeft = pos == 1 ? true : false;
		startLeftRight = pos == 2 ? true : false;
		startDownUp = pos == 3 ? true : false;
		startUpDown = pos == 4 ? true : false;
		diagonalUpDownRight = pos == 5 ? true : false;
		diagonalUpDownLeft = pos == 6 ? true : false;
		diagonalDownUpRight = pos == 7 ? true : false;
		diagonalDownUpLeft = pos == 8 ? true : false;
	}
	
    //The player is trying to move at the specified square.
    //Return the number of chips captured, 0 for an invalid move.
	public int tryMove(int row, int col, int player)
	{
		
    	int captureCount = 0;
    	int tempRow = row;
		int tempCol = col;
    	
    	int prevPlayer = player == 1 ? 2 : 1;
    	
    	//Captured from right to left
    	if(col <= 6) {
        	if(vc.defaultBoard[row][col + 1] == prevPlayer) {
        		while(true) {
        			tempCol++;
        			startRightLeft = false;
        			if(tempCol > 7) {
        				break;
        			}
        			if(vc.defaultBoard[row][tempCol] == player) {
        				startRightLeft = true;
        				break;
        			}else if(vc.defaultBoard[row][tempCol] == 0) {
        				break;
        			}
        		}
        	}
    	}
    	
    	tempRow = row;
		tempCol = col;
    	if(col >= 1) {
    		if(vc.defaultBoard[row][col - 1] == prevPlayer) {
        		while(true) {
        			tempCol--;
        			startLeftRight = false;
        			if(tempCol < 0 ) {
        				break;
        			}
        			if(vc.defaultBoard[row][tempCol] == player) {
        				startLeftRight = true;
        				break;
        			}else if(vc.defaultBoard[row][tempCol] == 0) {
        				break;
        			}
        			
        			
        		}
        	}
    	}
    	
    	tempRow = row;
		tempCol = col;
    	
		if(row <= 6) {
			if(vc.defaultBoard[row + 1][col] == prevPlayer) {
	    		while(true) {
	    			tempRow++;
	    			startDownUp = false;
	    			if(tempRow > 7 ) {
	    				break;
	    			}
	    			if(vc.defaultBoard[tempRow][tempCol] == player) {
	    				startDownUp = true;
	    				break;
	    			}else if(vc.defaultBoard[tempRow][tempCol] == 0) {
	    				break;
        			}
	    		}
	    	}
		}
    	
    	tempRow = row;
		tempCol = col;
    	if(row >= 1) {
    		if(vc.defaultBoard[row - 1][col] == prevPlayer) {
        		while(true) {
        			tempRow--;
        			startUpDown =  false;
        			if(tempRow < 0 ) {
        				break;
        			}
        			if(vc.defaultBoard[tempRow][tempCol] == player) {
        				startUpDown = true;
        				break;
        			}else if(vc.defaultBoard[tempRow][tempCol] == 0) {
        				break;
        			}
        			
        			
        		}
        	}
    	}
    	
    	tempRow = row;
		tempCol = col;
    	
		if(row <= 6 && col <= 6) {
			if(vc.defaultBoard[row + 1][col + 1] == prevPlayer) {
	    		while(true) {
	    			tempRow++;
	    			tempCol++;
	    			diagonalUpDownRight = false;
	    			if(tempRow > 7 || tempCol > 7) {
	    				break;
	    			}
	    			if(vc.defaultBoard[tempRow][tempCol] == player) {
	    				diagonalUpDownRight = true;
	    				break;
	    			}else if(vc.defaultBoard[tempRow][tempCol] == 0) {
	    				break;
        			}
	    			
	    			
	    			
	    		}
	    	}
		}
    	tempRow = row;
		tempCol = col;
		
		if(row <=6 && col >= 1) {
			System.out.println(col);
	    	if(vc.defaultBoard[row + 1][col - 1] == prevPlayer) {
	    		while(true) {
	    			tempRow++;
	    			tempCol--;
	    			System.out.println("tempRow: " + tempRow);
	    			System.out.println("tempCol: " + tempCol);
	    			diagonalUpDownLeft = false;
	    			if(tempCol < 0 || tempRow > 7) {
	    				break;
	    			}
	    			if(vc.defaultBoard[tempRow][tempCol] == player) {
	    				diagonalUpDownLeft = true;
	    				break;
	    			}else if(vc.defaultBoard[tempRow][tempCol] == 0) {
	    				break;
        			}
	    			
	    		}
	    	}
		}
    	
    	tempRow = row;
		tempCol = col;
    	if(row >= 2 && col <= 6) {
    		if(vc.defaultBoard[row - 1][col + 1] == prevPlayer) {
        		while(true) {
        			tempRow--;
        			tempCol++;
        			diagonalDownUpRight = false;
        			if(tempCol > 7 || tempRow < 0) {
        				break;
        			}
        			if(vc.defaultBoard[tempRow][tempCol] == player) {
        				diagonalDownUpRight = true;
        				break;
        			}else if(vc.defaultBoard[tempRow][tempCol] == 0) {
        				break;
        			}
        			
        			
        		}
        	}
    	}
    	
    	tempRow = row;
		tempCol = col;
		
		if(row >= 2 && col >= 1) {
			if(vc.defaultBoard[row - 1][col - 1] == prevPlayer) {
	    		while(true) {
	    			tempRow--;
	    			tempCol--;
	    			diagonalDownUpLeft = false;
	    			if(tempCol < 0 || tempRow < 0) {
	    				break;
	    			}
	    			if(vc.defaultBoard[tempRow][tempCol] == player) {
	    				diagonalDownUpLeft = true;
	    				break;
	    			}else if(vc.defaultBoard[tempRow][tempCol] == 0) {
        				break;
        			}
	    			
	    		}
	    	}
		}

		
    	System.out.println("startRightLeft: " + startRightLeft);
    	System.out.println("startLeftRight: " + startLeftRight);
    	System.out.println("startDownUp: " + startDownUp);
    	System.out.println("startUpDown: " + startUpDown);
    	System.out.println("diagonalUpDownRight: " + diagonalUpDownRight);
    	System.out.println("diagonalUpDownLeft: " + diagonalUpDownLeft);
    	System.out.println("diagonalDownUpRight: " + diagonalDownUpRight);
    	System.out.println("diagonalDownUpLeft: " + diagonalDownUpLeft);
    	
    	
		tempRow = row;
		tempCol = col;
    	
		
		
		if(startRightLeft) {
			while(true) {
				tempCol++;
				if(vc.defaultBoard[tempRow][tempCol] != player) {
					vc.defaultBoard[tempRow][tempCol] = player;
					vc.gameBoard[tempRow][tempCol].setIcon(player == 1 ? vc.blackPlayer : vc.whitePlayer);
					captureCount++;
				}else {
					break;
				}
			}
		}
		
		tempRow = row;
		tempCol = col;
		if(startLeftRight) {
			while(true) {
				tempCol--;
				if(vc.defaultBoard[tempRow][tempCol] != player) {
					vc.defaultBoard[tempRow][tempCol] = player;
					vc.gameBoard[tempRow][tempCol].setIcon(player == 1 ? vc.blackPlayer : vc.whitePlayer);
					captureCount++;
				}else {
					break;
				}
			}
		}
		tempRow = row;
		tempCol = col;
		
		if(startDownUp) {
			while(true) {
				tempRow++;
				if(vc.defaultBoard[tempRow][tempCol] != player) {
					vc.defaultBoard[tempRow][tempCol] = player;
					vc.gameBoard[tempRow][tempCol].setIcon(player == 1 ? vc.blackPlayer : vc.whitePlayer);
					captureCount++;
				}else {
					break;
				}
			}
		}
		tempRow = row;
		tempCol = col;
		if(startUpDown) {
			while(true) {
				tempRow--;
				if(vc.defaultBoard[tempRow][tempCol] != player) {
					vc.defaultBoard[tempRow][tempCol] = player;
					vc.gameBoard[tempRow][tempCol].setIcon(player == 1 ? vc.blackPlayer : vc.whitePlayer);
					captureCount++;
				}else {
					break;
				}
			}
		}
		tempRow = row;
		tempCol = col;
		
		if(diagonalUpDownRight) {
			while(true) {
				tempRow++;
				tempCol++;
				if(vc.defaultBoard[tempRow][tempCol] != player) {
					vc.defaultBoard[tempRow][tempCol] = player;
					vc.gameBoard[tempRow][tempCol].setIcon(player == 1 ? vc.blackPlayer : vc.whitePlayer);
					captureCount++;
				}else {
					break;
				}
			}

		}
		tempRow = row;
		tempCol = col;
		
		if(diagonalUpDownLeft) {
			while(true) {
				tempRow++;
    			tempCol--;
    			System.out.println("tempRow: " + tempRow);
    			System.out.println("tempCol: " + tempCol);
				if(vc.defaultBoard[tempRow][tempCol] != player) {
					vc.defaultBoard[tempRow][tempCol] = player;
					vc.gameBoard[tempRow][tempCol].setIcon(player == 1 ? vc.blackPlayer : vc.whitePlayer);
					captureCount++;
				}else {
					break;
				}
			}
		}
		tempRow = row;
		tempCol = col;
		if(diagonalDownUpRight) {
			while(true) {
				tempRow--;
				tempCol++;
				if(vc.defaultBoard[tempRow][tempCol] != player) {
					vc.defaultBoard[tempRow][tempCol] = player;
					vc.gameBoard[tempRow][tempCol].setIcon(player == 1 ? vc.blackPlayer : vc.whitePlayer);
					captureCount++;
				}else {
					break;
				}
			}
		}
		tempRow = row;
		tempCol = col;
		
		if(diagonalDownUpLeft) {
			while(true) {
				tempRow--;
				tempCol--;
				if(vc.defaultBoard[tempRow][tempCol] != player) {
					vc.defaultBoard[tempRow][tempCol] = player;
					vc.gameBoard[tempRow][tempCol].setIcon(player == 1 ? vc.blackPlayer : vc.whitePlayer);
					captureCount++;
				}else {
					break;
				}
			}
		}
		
		flipSigns(9);
		
		return captureCount;
        
    }
        
    //If there exists ANY valid move for that player, return true.
    public boolean moveTest(int player)
    {
    	 for(int i = 0; i < board.length; i++) {
			 for(int j = 0; j < board.length; j++) {
				if(boardTemp[i][j] == 3) return true;
			 }
		 }
    	 
    	 return false;

    }
    
    //How many chips does the specified player have in play?
    public int chipCount(int player)
    {
		 int count = 0;
		 
		 for(int i = 0; i < board.length; i++) {
			 for(int j = 0; j < board.length; j++) {
				 if(vc.defaultBoard[i][j] == player) count++;
			 }
		 }

		 return count;
    }
    
    public boolean checkYAxis(int row, int col, int currentPlayer, int prevPlayer) {
    	
    	int tempNum = row;
    	
    	 try {
    		 if(row >= 2 && row <= 6) {
    			 if(vc.defaultBoard[row+1][col] == prevPlayer && row < 6) {//Down
    	 				
        			 while(true) {
        				 tempNum++;
        				 if(vc.defaultBoard[tempNum][col] == currentPlayer) break;
        				 if(vc.defaultBoard[tempNum][col] != prevPlayer) {
        					 boardTemp[tempNum][col] = 3;
        					 break;
        				 }
        			 }
        		 }
        		 
    		 }
    		 tempNum = row;
    		 
    		 if(row >= 2 && row <= 7) {
        		 if(vc.defaultBoard[row-1][col] == prevPlayer) {//Up
        			 while(true) {
        				 tempNum--;
        				 if(vc.defaultBoard[tempNum][col] == currentPlayer) break;
        				 if(vc.defaultBoard[tempNum][col] != prevPlayer) {
        					 boardTemp[tempNum][col] = 3;
        					 break;
        				 }
        			 }
        		 }
    		 }

    		 return true;
    	 }catch(Exception e) {
    		 System.out.println(e.getMessage());
    		 return false;
    	 }
    	
    }
    
    public boolean checkXAxis(int row, int col, int currentPlayer, int prevPlayer, boolean isCapture) {
    	
    	int tempNum1 = col;
    	
    	
		 try {
			 if(col > 1 && col < 6) {
				 if(vc.defaultBoard[row][col - 1] == prevPlayer && col > 1) {//Left
					 while(true) {
						 tempNum1--;
						 if(vc.defaultBoard[row][tempNum1] == currentPlayer) break;
						 if(vc.defaultBoard[row][tempNum1] != prevPlayer) {
							 boardTemp[row][tempNum1] = 3;
							 break;
						 }
					 }
				 }
			 }
			 
			 tempNum1 = col;
			 
			 if(col > 1 && col < 6) {
				 if(vc.defaultBoard[row][col + 1] == prevPlayer && col < 7) {//Right
					 while(true) {
						 tempNum1++;
						 if(vc.defaultBoard[row][tempNum1] == currentPlayer) break;
						 if(vc.defaultBoard[row][tempNum1] != prevPlayer) {
							 boardTemp[row][tempNum1] = 3;
							 break;
						 }
					 }
				 }
			 }
			 
			 return true;
			
		 }catch(Exception e) {
			 System.out.println(e.getMessage());
			 return false;
		 }
		 
		 
    	
    }
    
    public boolean checkDiagonal(int row, int col, int currentPlayer, int prevPlayer, boolean isCapturing) {
		 int tempNum = row;
		 int tempNum1 = col;
		 
		 try {
			 tempNum = row;
			 tempNum1 = col;
			
			 
			 if(col >= 2 && row < 6) {
				 if(vc.defaultBoard[row+1][col-1] == prevPlayer && col >= 2) {//Down left Diagonal
					 while(true) {
						 tempNum++;
						 tempNum1--;
						 if(vc.defaultBoard[tempNum][tempNum1] == currentPlayer) break;
						 if(vc.defaultBoard[tempNum][tempNum1] != prevPlayer || vc.defaultBoard[tempNum][tempNum1] == 0) {
							 boardTemp[tempNum][tempNum1] = 3;
							 break;
						 }
					 }
				 }
			 }
			 
			 tempNum = row;
			 tempNum1 = col;
			 
			 if(row >= 2 && col < 6) {
				 if(vc.defaultBoard[row-1][col+1] == prevPlayer && col <= 6) {//Up right Diagonal
					 while(true) {
						 tempNum--;
						 tempNum1++;
						 if(vc.defaultBoard[tempNum][tempNum1] == currentPlayer) break;
						 if(vc.defaultBoard[tempNum][tempNum1] != prevPlayer) {
							 boardTemp[tempNum][tempNum1] = 3;
							 break;
						 }
					 }
				 }
			 }
			 
			 
			 tempNum = row;
			 tempNum1 = col;
			 
			 if(row < 6 && col < 6) {
				 if(vc.defaultBoard[row+1][col+1] == prevPlayer && row <= 6) {//Down right Diagonal
					 while(true) {
						 tempNum++;
						 tempNum1++;
						 if(vc.defaultBoard[tempNum][tempNum1] == currentPlayer) break;
						 if(vc.defaultBoard[tempNum][tempNum1] != prevPlayer) {
							 boardTemp[tempNum][tempNum1] = 3;
							 break;
						 }
					 }
				 }
			 }
			 
			 tempNum = row;
			 tempNum1 = col;
			 
			 if(row >= 2 && col >= 2) {
				 if(vc.defaultBoard[row-1][col-1] == prevPlayer && row >= 2) {//UP left Diagonal
					 while(true) {
						 tempNum--;
						 tempNum1--;
						 if(vc.defaultBoard[tempNum][tempNum1] == currentPlayer) break;
						 if(vc.defaultBoard[tempNum][tempNum1] != prevPlayer) {
							 boardTemp[tempNum][tempNum1] = 3;
							 break;
						 }
					 }
				 }
			 }
			 return true;
		 }catch(Exception e) {
			 System.out.println(e.getMessage());
			 return false;
		 }
		 
		 

    	
    	
    	
    }
    
    public boolean checkEndGame() {
    	
    	vc.bPieceCount = 0;
    	vc.wPieceCount = 0;
    	for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(vc.defaultBoard[i][j] == 1) vc.bPieceCount++;
				else if(vc.defaultBoard[i][j] == 2) vc.wPieceCount++;
				else if(vc.defaultBoard[i][j] == 0) return false;
			}
    	}
    	return true;
    	
    }
    
    public void showGameValidMoves(int player, boolean isShow) {
    	setBoardValidMoves(player);
    	
    	int count = 0;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				++count;
				System.out.print(boardTemp[i][j] + " ");
				if(count == 8) {
					System.out.println();
					count = 0;
				}
			}
		}
    	
    	
    	for(int i = 0; i < board.length; i++) {
			 for(int j = 0; j < board.length; j++) {
				 
				 
				 
				 if(boardTemp[i][j] == 3) {
					 if(isShow)
						 vc.gameBoard[i][j].setIcon(vc.validMoves);
					 else
						 vc.gameBoard[i][j].setIcon(null);
				 }
				 
				 vc.gameBoard[i][j].setHorizontalAlignment(JLabel.CENTER);
				 
			 }
	    }
    	
    }
    
    
    
    public void setBoardValidMoves(int player) {
    	
    	int count = 0;
    	for(int i = 0; i < board.length; i++) {
			 for(int j = 0; j < board.length; j++) {
				 
				 boardTemp[i][j] = vc.defaultBoard[i][j];
				 
			 }
	    }
    	
    	int currentPlayer = player;
    	int prevPlayer = player == 1 ? 2 : 1;
    	
    	 for(int i = 0; i < board.length; i++) {
			 for(int j = 0; j < board.length; j++) {
				 
				 
				 if(vc.defaultBoard[i][j] == currentPlayer) {
					 int tempNum = i;
					 int tempNum1 = j;
					 
					//Check y-axis
					 isYAxis = checkYAxis(i, j, currentPlayer, prevPlayer);
					 
					
					//Check X-axis
					 isXAxis = checkXAxis(i, j, currentPlayer, prevPlayer, false);
					 
			
					 //Diagonal
					 isDiagonal = checkDiagonal(i, j, currentPlayer, prevPlayer, false);
					 
					 

				 }
			 }
		 }
    }
}



	
