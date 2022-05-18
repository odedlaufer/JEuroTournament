package core;

import java.awt.Color;

public class PenaltyCard {

	private short minute;
	private Color color;
	private Player player;
	
	public PenaltyCard(short minute, Color color) { 
		
		setMinute(minute);
		this.color = color;
		
	}
	
	/*
	 * methods checks if minute is valid
	 */
	private void setMinute(short minute) { 
		
		if(minute > 0 && minute <= 120) { 
			
			this.minute = minute;
		}
	}
	/*
	 * Adds the player who committed this penalty
	 */
	public boolean addPlayer(Player p) {
		
		if(p == null) {
			
			return false;
		}
		
		if(this.player != null) { 
			
			return false;
		}
		
		this.player = p;
		return true;
	}
	
	/*
	 * gets minute
	 */
	public short getMinute() {
		
		return this.minute;
	}
	
	/*
	 * gets the player that got this penalty card
	 */
	public Player getPlayer() {
		
		return this.player;
	}
	
	/*
	 * gets the team of the player that got this penalty card
	 */
	public Team getTeamOfPlayer() {
		
		return this.player.getTeam();
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + minute;
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if(!(obj instanceof PenaltyCard)) { 
			
			return false;
		}
		
		PenaltyCard pc = (PenaltyCard)obj;
		
		return this.color.equals(pc.color) &&
				this.minute == pc.minute &&
				this.player.equals(pc.player);
	}
	
	/*
	 * checks if it is a red card
	 */
	public static boolean isRedCard(PenaltyCard c) { 
		
		return Color.red.equals(c.color);
	}
	
	/*
	 * checks if it is a yellow card
	 */
	public static boolean isYellowCard(PenaltyCard c) { 
		
		return Color.yellow.equals(c.color);
	}
	
	/*
	 * Removes the player who committed this penalty
	 */
	public boolean removePlayer() {
		
		if(this.player != null) {
			
			this.player = null;
			return true;
		}
		
		return false;
		
	}

	/*
	 * Converts color from string to java.awt.Color object
	 */
	//TODO
	public static Color convertToColor(String color) { 
		
		try {
			
			return Color.getColor(color);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return null;
		
				
		}

	@Override
	public String toString() {
		return "minute=" + minute + ", color=" + color + ", player=" + player;
	}
	
	
	
	
}
	
	

