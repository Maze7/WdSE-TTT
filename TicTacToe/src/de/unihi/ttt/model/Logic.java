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
     * Tries to assign the current Player to the given position.
     * @param x x-coordinate in the grid
     * @param y y-coordinate in the grid
     * @return true, if turn was possible, meaning Player has been assigned to position in the grid
     */
    public boolean doTurn(int x, int y) {
        turnCounter++;
        boolean success = false;
        if (fields[x][y].equals(Player.NOBODY)) {
            success = true;
            fields[x][y] = whichTurn();
        }
        return success;
    }
    
    /**
     * Determines which Player should perform in the current turn.
     * @return Player, who is supposed to perform a turn
     */
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
    
    
    /**
     * Checks if game has been won by a player.
     * @return Outcome of the current game (WIN or DRAW), NOTHING, if game is still ongoing.
     */
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
            outcomeValue =  Outcome.NOTHING;
        }
        return outcomeValue;
    }
    
    /**
     * Testing purposes. 
     * @return 2D Player array which represents the game field.
     */
    Player[][] getFields() {
        return fields;
    }
}