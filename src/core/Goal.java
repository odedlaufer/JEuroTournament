package core;

public class Goal {
	
	private short minute;
	private boolean penalty;
	private Player player;
	
	public Goal(short minute, boolean penalty) { 
		
		setMinute(minute);
		this.penalty = penalty;
		
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
	 * Adds the player who committed this goal
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
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + minute;
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {

		if(!(obj instanceof Goal)) { 
			
			return false;
		}
		
		Goal g = (Goal)obj;
		
		return this.minute == g.minute &&
				this.player.equals(g.player);
	}
	
	/*
	 * Gets when the goal was committed in minutes
	 */
	public short getMinute() {
		
		return this.minute;
	}
	
	/*
	 * Get the scorer (player) associated with this Goal
	 */
	public Player getPlayer() {
		
		return this.player;
	}
	
	/*
	 * gets the team of the player who committed the goal
	 */
	public Team getScorerTeam() {
		
		return this.player.getTeam();
	}
	
	/*
	 * check if the Goal is a penalty kick
	 */
	public boolean isPenalty() {
		
		return this.penalty;
	}
	
	/*
	 * Removes the player who committed this goal
	 */
	public boolean removePlayer() {
		
		if(this.player != null) { 
			
			this.player = null;
			return true;
		}
		
		return false;
	}
	@Override
	public String toString() {
		return "minute=" + minute + ", penalty=" + penalty + ", player=" + player;
	}
	
	
	
	
	
	
	
	

}
