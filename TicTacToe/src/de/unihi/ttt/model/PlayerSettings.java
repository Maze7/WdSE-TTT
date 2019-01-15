package de.unihi.ttt.model;


public class PlayerSettings {
	
	private String name;
	private Player player;
	private PlayerSymbol symbol;
	
	public PlayerSettings(String name, Player player, PlayerSymbol symbol) {
		this.name = name;
		this.player = player;
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public Player getPlayer() {
		return player;
	}
	
	public PlayerSymbol getSymbol() {
	    return symbol;
	}
}
