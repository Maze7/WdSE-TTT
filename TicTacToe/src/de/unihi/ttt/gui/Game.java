package de.unihi.ttt.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import de.unihi.ttt.model.Logic;
import de.unihi.ttt.model.Outcome;
import de.unihi.ttt.model.Player;
import de.unihi.ttt.model.PlayerSettings;
import de.unihi.ttt.model.PlayerSymbol;

/**
 * The Game "TicTacToe".
 */
public class Game {
    private static final int WINDOW_SIZE = 500;
    /**
     * List of {@link PlayerSettings} which contains the two available players in TicTacToe. 
     */ 
    private static final PlayerSettings[] PLAYERS = {
        new PlayerSettings("O", Player.PLAYER1, PlayerSymbol.O), 
        new PlayerSettings("X", Player.PLAYER2, PlayerSymbol.X) 
    };
    private JFrame window = new JFrame("Tic Tac Toe");
    private Playfield playfield;
    private Logic logic; 

    
    /**
     * After creation a window is shown where all game elements are displayed. 
     * It starts the whole game logic.
     */
    public Game() {
        // Create the Window
        window.setSize(WINDOW_SIZE, WINDOW_SIZE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new GridLayout(3, 3));
        window.setVisible(true);
        
        playfield = new Playfield(window, new TurnListener());
        logic = new Logic();
        
    }

    /**
     * Check if there is a win belonging to the last move. 
     * If there is an outcome like draw or a win, the result is displayed at {@link Game#window}. 
     */
    private void checkAndDisplayWin() {
        Outcome outcome = logic.checkOutcome();
        String playerName = this.getPlayerSettings(logic.whichTurn()).getName();
        switch (outcome) {
        case WIN:
            JOptionPane.showMessageDialog(null, "Player " + playerName + " wins!");
            window.setEnabled(false);
            break;
        case DRAW:
            JOptionPane.showMessageDialog(null, "The game ended in a tie.");
            break;
        case NOTHING:
            break;
        default:
            break;
        }
    }

    /**
     * Returns the belonging {@link PlayerSettings} to a given {@link Player}.
     * @param player the player which PlayerSettings are wanted.
     * @return PlayerSettings to a specific Player. 
     */
    private PlayerSettings getPlayerSettings(Player player) {
        PlayerSettings playerSettings = null;
        for (PlayerSettings settings : PLAYERS) {
            if (settings.getPlayer().equals(player)) {
                playerSettings = settings;
            }
        }
        return playerSettings;
    }

    /**
     * TurnListener which can set to a Button inside a {@link Playfield} as an {@link ActionListener}.
     * This class handles every game logic which should happen on a button press. 
     */
    class TurnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JButton button = (JButton) event.getSource();
            int[] indexValues = playfield.getButtonIndex(button);
            logic.doTurn(indexValues[0], indexValues[1]);

            PlayerSettings playerSettings = getPlayerSettings(logic.whichTurn());
            playfield.setButtonImage(indexValues[0], indexValues[1], playerSettings.getSymbol());
            checkAndDisplayWin();
        }
    }
}
