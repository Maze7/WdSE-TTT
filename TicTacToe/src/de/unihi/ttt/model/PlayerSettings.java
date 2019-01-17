package de.unihi.ttt.model;


/**
 * Class to capsulate a player name with his corresponding symbol.
 */
public class PlayerSettings {

    private String name;
    private Player player;
    private PlayerSymbol symbol;

    /**
    * Constructor.
    * @param name player's name
    * @param player PLAYER1 or PlAYER2
    * @param symbol X or O
    */
    public PlayerSettings(String name, Player player, PlayerSymbol symbol) {
        this.name = name;
        this.player = player;
        this.symbol = symbol;
    }

    /**
    * Getter for the player's name.
    * @return name of the player
    */
    public String getName() {
        return name;
    }

    /**
    * Getter for the player.
    * @return Player 
    */
    public Player getPlayer() {
        return player;
    }

   /**
    * Getter fo the player's symbol.
    * @return symbol of the player
    */
    public PlayerSymbol getSymbol() {
        return symbol;
    }
}
