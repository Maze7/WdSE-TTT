package de.unihi.ttt.model;

/**
 * This class provides a game logic for Tic Tac Toe.
 * @author marcel
 */
public class Logic {
	private Player[][] fields = new Player[3][3];
	private int turnCounter;
	private static final int ROUND_END = 9;
	
	public Logic() {
		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < fields[i].length; j++) {
				fields[i][j] = Player.NOBODY;
			}
		}
	}
	
	/**
	 * @param x
	 * @param y
	 */
	public void doTurn(int x, int y) {
		turnCounter++;
		fields[x][y] = whichTurn();
	}
	
	public Player whichTurn() {
		Player returnValue = null;
        if (turnCounter % 2 == 1) {
            returnValue = Player.PLAYER1;
        }
        if (turnCounter % 2 == 0) {
        	returnValue = Player.PLAYER2;
        }
        return returnValue;
	}
	
	public Outcome checkOutcome() {
		Outcome outcomeValue;
		
		int index = 0;
		boolean win = false;
		while (!win && index < 3) {
			
			win = fields[index][0] != Player.NOBODY
					&& fields[index][0].equals(fields[index][1])
					&& fields[index][1].equals(fields[index][2]);
			win = win
					|| (fields[0][index] != Player.NOBODY 
					&& fields[0][index].equals(fields[1][index]) 
					&& fields[1][index].equals(fields[2][index]));
			index++;
		}
		win = win
				|| (fields[0][0] != Player.NOBODY
				&& fields[0][0].equals(fields[1][1]) 
				&& fields[1][1].equals(fields[2][2]));
		win = win
				|| (fields[0][2] != Player.NOBODY
				&& fields[0][2].equals(fields[1][1]) 
				&& fields[1][1].equals(fields[2][0]));
				
		if (win) {
			outcomeValue = Outcome.WIN;
		} else if (turnCounter == ROUND_END) {
			outcomeValue = Outcome.DRAW;
		} else {
			outcomeValue = Outcome.NOTHING;
		}
		return outcomeValue;
	}
}
