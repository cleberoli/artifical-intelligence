import java.util.Scanner;
 
public class Minimax {
 
 	static int count = 0, countPruning = 0;
	public static int[] minimax(TicTacToe ttt, int level, int player) {
 		count ++;
		int bestScore = (player == ttt.COMPUTER) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int currentScore;
		int bestRow = -1;
		int bestCol = -1;
 
		if (level == 0 || ttt.allOccuped() ) bestScore = evaluate(ttt);
		else {
			//System.out.println("else");
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (ttt.isEmpty(i, j)) {
						
						ttt.move(player, i, j);
 
						if (player == ttt.USER) {
							currentScore = minimax(ttt, level-1, ttt.COMPUTER)[0];
							if (bestScore > currentScore) {
								bestScore = currentScore;
								bestRow = i; bestCol = j;
							}
						} else {
							currentScore = minimax(ttt, level-1, ttt.USER)[0];
							if (bestScore < currentScore) {
								bestScore = currentScore;
								bestRow = i; bestCol = j;
							}
						}
 
						ttt.clearMove(i, j);
					}
				}	
			}
		}
 
		return new int[] {bestScore, bestRow, bestCol};
	}
 
	public static int[] minimaxWithPruning(TicTacToe ttt, int level, int player) {
		return minimaxWithPruning(ttt, level, player, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
 
	public static int[] minimaxWithPruning(TicTacToe ttt, int level, int player, int alpha, int beta) {
 		countPruning++;
		int score;
		int bestRow = -1;
		int bestCol = -1;
 
		if (level == 0 || ttt.allOccuped()) {
		
			score = evaluate(ttt);
			return new int[] {score, bestRow, bestCol};
 
		} else {
			FOROUTTER:
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (ttt.isEmpty(i, j)) {
						
						ttt.move(player, i, j);
 
						if (player == ttt.USER) {
							score = minimaxWithPruning(ttt, level-1, ttt.COMPUTER, alpha, beta)[0];
							if (score < beta) {
								beta = score;
								bestRow = i; bestCol = j;
							}
						} else {
							score = minimaxWithPruning(ttt, level-1, ttt.USER, alpha, beta)[0];
							if (score > alpha) {
								alpha = score;
								bestRow = i; bestCol = j;
							}
						}
 
						ttt.clearMove(i, j);
						if (alpha >= beta) break FOROUTTER;
					}
				}	
			}
		}
 
		return new int[] {(player == ttt.COMPUTER) ? alpha : beta, bestRow, bestCol};
	}
 
	private static int evaluate(TicTacToe ttt) {
		int score = 0;
		// Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
		score += evaluateLine(ttt, 0, 0, 0, 1, 0, 2);  // row 0
		score += evaluateLine(ttt, 1, 0, 1, 1, 1, 2);  // row 1
		score += evaluateLine(ttt, 2, 0, 2, 1, 2, 2);  // row 2
		score += evaluateLine(ttt, 0, 0, 1, 0, 2, 0);  // col 0
		score += evaluateLine(ttt, 0, 1, 1, 1, 2, 1);  // col 1
		score += evaluateLine(ttt, 0, 2, 1, 2, 2, 2);  // col 2
		score += evaluateLine(ttt, 0, 0, 1, 1, 2, 2);  // diagonal
		score += evaluateLine(ttt, 0, 2, 1, 1, 2, 0);  // opposite diagonal
		return score;
	}
	private static int evaluateLine(TicTacToe ttt, int row1, int col1, int row2, int col2, int row3, int col3) {
		int score = 0;
	
		// First cell
		if (ttt.board[row1][col1] == ttt.COMPUTER) {
			score = 1;
		} else if (ttt.board[row1][col1] == ttt.USER) {
			score = -1;
		}
	
		// Second cell
		if (ttt.board[row2][col2] == ttt.COMPUTER) {
			if (score == 1) {   // cell1 is ttt.COMPUTER
				score = 10;
			} else if (score == -1) {  // cell1 is ttt.USER
				return 0;
			} else {  // cell1 is empty
				score = 1;
			}
		} else if (ttt.board[row2][col2] == ttt.USER) {
			if (score == -1) { // cell1 is ttt.USER
				score = -10;
			} else if (score == 1) { // cell1 is ttt.COMPUTER
				return 0;
			} else {  // cell1 is empty
				score = -1;
			}
	}
 
      // Third cell
      if (ttt.board[row3][col3] == ttt.COMPUTER) {
         if (score > 0) {  // cell1 and/or cell2 is ttt.COMPUTER
            score *= 10;
         } else if (score < 0) {  // cell1 and/or cell2 is ttt.USER
            return 0;
         } else {  // cell1 and cell2 are empty
            score = 1;
         }
      } else if (ttt.board[row3][col3] == ttt.USER) {
         if (score < 0) {  // cell1 and/or cell2 is ttt.USER
            score *= 10;
         } else if (score > 1) {  // cell1 and/or cell2 is ttt.COMPUTER
            return 0;
         } else {  // cell1 and cell2 are empty
            score = -1;
         }
      }
      return score;
   }
 
	public static void main(String[] args) {
		TicTacToe ttt = new TicTacToe();
		Scanner s = new Scanner(System.in);
		int x,y;
 
		while (true) {
			System.out.println("User: ");
			x = s.nextInt();
			y = s.nextInt();
 
			ttt.move(ttt.USER, x, y);
			ttt.printBoard();
			if (ttt.won(ttt.USER)) {
				break;
			}
			if (ttt.allOccuped()) {
				ttt.restart();
				continue;
			}
			int[] move = minimax(ttt, 6, ttt.COMPUTER);
			int[] move1 = minimaxWithPruning(ttt, 6, ttt.COMPUTER);
			System.out.println("Moves equal: " + (move[1] == move1[1] && move[2] == move1[2]));
			System.out.println("Without alpha-beta: " + count);
			System.out.println("With alpha-beta: " + countPruning);
			ttt.move(ttt.COMPUTER, move[1], move[2]);
			ttt.printBoard();
			if (ttt.won(ttt.COMPUTER)) {
				break;
			}	
		}
		ttt.printBoard();
		int[] move = minimax(ttt, 3, ttt.COMPUTER);
		System.out.println(move[1] + " : " + move[2]);
 
	}
}
 
class TicTacToe {
 
	int[][] board;
 
	static final int EMPTY 		= 0;
	static final int USER 	= 1; // user
	static final int COMPUTER 	= 2; // computer
 
	public TicTacToe() {
		board = new int[3][3];
 
		restart();
	}
 
	public boolean allOccuped() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++ ) {
				if (board[i][j] == EMPTY) return false;
			}
		}
		return true;
	}
 
	public void restart() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++ ) {
				board[i][j] = EMPTY;
			}
		}
	}
 
	public boolean won(int player) {
		for (int i = 0; i < 3; i++) {
			//row
			if (board[i][0] == board[i][1] 
				&& board[i][1] == board[i][2] 
				&& board[i][2] == player)
				return true; 
		
			// col
			if (board[0][i] == board[1][i] 
				&& board[1][i] == board[2][i] 
				&& board[2][i] == player)
				return true; 
		}
 
		// diagonal
		if (board[0][0] == board[1][1] 
				&& board[1][1] == board[2][2] 
				&& board[2][2] == player)
			return true;
 
		// opposite diagonal
		if (board[0][2] == board[1][1] 
				&& board[1][1] == board[2][0] 
				&& board[2][0] == player)
			return true;
 
		return false;
	}
 
	public void move(int player, int x, int y) {
		board[x][y] = player;
	}
 
	public void clearMove(int x, int y) {
		board[x][y] = EMPTY;
	}
 
	public boolean isEmpty(int x, int y) {
		return (board[x][y] == EMPTY);
	}
 
	public void printBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				printCell(board[row][col]);
			}
			System.out.println();
		}
		System.out.println();
	}
 
	public void printCell(int content) {
		switch (content) {
		case EMPTY:  System.out.print(" _ "); break;
		case USER: System.out.print(" U "); break;
		case COMPUTER:  System.out.print(" C "); break;
		}
	}
}
