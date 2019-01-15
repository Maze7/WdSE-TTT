package de.unihi.ttt.gui;

import java.awt.Window;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import de.unihi.ttt.gui.Game.TurnListener;
import de.unihi.ttt.model.PlayerSymbol;

/**
 * Playfield class which contains game elements.
 * 
 * @author marcel
 */
public class Playfield {
    private ImageIcon iconX;
    private ImageIcon iconO;
    private JButton[][] buttonField = new JButton[3][3];
    
    /**
     * Creates a playfield containing 9x {@link JButton} where all 
     * moves will displayed.
     * @param window the {@link Window} where the playfield should displayed on.
     * @param actionListener an {@link ActionListener} which will executed if any of the buttons is pressed.
     */
    Playfield(JFrame window, TurnListener actionListener) {
    	
        // Assign images
        //iconX = new ImageIcon(getClass().getResource("X.png"));
        //iconO = new ImageIcon(getClass().getResource("O.png"));
        iconX = new ImageIcon(Paths.get("resources", "X.png").toString());
        iconO = new ImageIcon(Paths.get("resources", "O.png").toString());

        // Add Buttons
        for (int i = 0; i < buttonField.length; i++) {
        	for (int j = 0; j < buttonField[i].length; j++) {
        		buttonField[i][j] = new JButton();
        		window.add(buttonField[i][j]);
        		buttonField[i][j].addActionListener(actionListener);
        	}
        }
    }
    
    /**
     * 
     * @param button
     * @return
     */
    int[] getButtonIndex(JButton button) {
    	int[] indexValues = new int[2];
    	for (int i = 0; i < buttonField.length; i++) {
    		for (int j = 0; j < buttonField[i].length; j++) {
    			if (buttonField[i][j].equals(button)) {
    				indexValues[0] = i;
    				indexValues[1] = j;
    			}
    		}
    	}
    	return indexValues;
    }
    
    void disableButtons() {
        for (int i = 0; i < buttonField.length; i++) {
        	for (JButton field : buttonField[i]) {
        		field.setEnabled(false);
        	}
        }
    }
    
    void setButtonImage(int x, int y, PlayerSymbol symbol) {
		buttonField[x][y].setIcon(this.getImage(symbol));
		buttonField[x][y].setDisabledIcon(this.getImage(symbol));
		buttonField[x][y].setEnabled(false);	
    }
    
    /**
     * Method for matching {@link PlayerSymbol} with a specific {@link ImageIcon}.
     * @param symbol the symbol of the wanted image
     * @return an ImageIcon belonging to a player symbol
     */
    ImageIcon getImage(PlayerSymbol symbol) {
    	switch (symbol) {
    	case X:
    		return iconX;
    	case O:
    		return iconO;
    	default:
   			return null;
    	}
    }
}
