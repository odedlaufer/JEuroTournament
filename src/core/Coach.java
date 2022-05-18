package core;

import utils.Popularity;

/**
 * @author odedlaufer
 *
 */
public class Coach extends Person implements Popularity{
	
	private float seniority;
	private Team team;

	public Coach(String pId, String fullName, short age, String nation, float seniority) {
		
		super(pId, fullName, age, nation);
		this.seniority = seniority;

	}
	
	public Coach(String pId) { 
		
		super(pId);
	
	}
	/* 
	 * Gets the seniority of this Coach
	 */
	public float getSeniority() {
		
		return seniority;
	}
	
	/*
	 * Adds a team to this coach
	 */
	public boolean addTeam(Team team) { 
		
		if(team == null) { 
			
			return false;
		}
		
		if(this.team == null) { 
			
			this.team = team;
			return true;
		}
		
		return false;
	}
	
	/*
	 * checks if this Coach is the coach of the given team
	 */
	public boolean hasTeam(Team team) { 
		
		
		return this.team.equals(team);
	}
	
	/*
	 * removes the coach from the team
	 */
	public boolean removeTeam() { 
		
		if(this.team != null) { 
			
			this.team = null;
			return true;
		}
		
		return false;
	}
	
	/*
	 * Sets the seniority of this Coach
	 */
	public void setSeniority(float seniority) { 
		
		this.seniority = seniority;
	}

	//TODO 
	@Override
	public double getPopularity() {

		return calcPopularity();
	}

	@Override
	public String toString() {
		
		return super.toString() + "seniority=" + seniority + ", team=" + team.gettName();
	}

	
	@Override
	public double calcPopularity() {

		if(this.team == null) { 
			
			return seniority * 0.2;
		}
		
		return team.getEffectivenessLevel() * 0.8 + seniority * 0.2;
	}
	
	

}
