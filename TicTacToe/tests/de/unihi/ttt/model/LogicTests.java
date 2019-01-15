package de.unihi.ttt.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author marcel
 */
public class LogicTests {
    /**
     * Array contains moves, which will result in a draw
     */
    private static final int[][] DRAW_MOVES = {
            {0, 0}, {0, 1}, {1, 0}, {1, 1}, {0, 2}, {1, 2}, {2, 1}, {2, 0}, {2, 2}
    };
    
    /**
     * Array contains moves, which will result in a WIN for Player1
     */
    private static final int[][] WIN_MOVES = {
            {0, 0}, {1, 1}, {0, 1}, {2, 2}, {0, 2}
    };
    
    /**
     * Array contains moves which results not in any game result
     */
    private static final int[][] NOTHING_MOVES = {
            {0, 0}, {1, 1}, {2, 2}
    };
    
    
    private Logic logic;
    private int[][] moveSet;
    private Outcome expectedOutcome;
    
    /**
     * Execute a set of moves in 2d game field. 
     * @param moveSet two dimensional array containing a set of moves which should be executed
     */
    private void doTurns(final int[][] moveSet) {
       boolean sucess = false;
       for (int[] move : moveSet) {
           sucess = logic.doTurn(move[0], move[1]);
           assertTrue("Precondition failed: Move was not successful", sucess);
       }
    }
    
    
    /**
     * Init an instance of {@link Logic}. 
     */
    @Before
    public void prepare() {
        logic = new Logic();
    }
    
    /**
     * Sets {@link #logic} to null that each test starts with a new instance of {@link Logic}. 
     */
    @After
    public void cleanUp() {
        logic = null;
    }

    @Test
    public void testOutcomeCalculationWin() {
        doTurns(WIN_MOVES);
        assertEquals("Player1 should win, but he doesn't", Outcome.WIN, logic.checkOutcome());
    }
    
    @Test
    public void testOutComeCalculationDraw() {
        doTurns(DRAW_MOVES);
        assertEquals("This moves should result in nothing.", Outcome.DRAW, logic.checkOutcome());
    }
    
    @Test
    public void testOutComeCalculationWin() {
        doTurns(NOTHING_MOVES);
        assertEquals("This moves should result in nothing.", Outcome.NOTHING, logic.checkOutcome());
    }
    
    /**
     * Tests for {@link Logic#checkOutcome()}.
     * Tries to execute an invalid turn and doTurn on the same field twice.
     */
    @Test
    public void testInvalidTurn() {
        Logic logic = new Logic(); 
        logic.doTurn(0, 0); // player 1
        logic.doTurn(0, 0); // player 2 (invalid turn)
        
        Player[][] fields = logic.getFields();
        assertEquals(fields[0][0], Player.PLAYER1);
    }
}
