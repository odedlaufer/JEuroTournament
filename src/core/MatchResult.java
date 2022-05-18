package core;

import java.awt.Color;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import utils.Constants;


/**
 * This class represents the MatchResult and its properties
 *
 */
public class MatchResult {
	
	
	/**
	 * team A, participant in the game
	 */
	private Team teamA;
	
	/**
	 * team B, participant in the game
	 */
	private Team teamB;
	
	/**
	 * team A, participant in the game
	 */
	private Match match;

	private int goalsA;
	private int goalsB;

	private short yellowCardsA;
	private short yellowCardsB;

	private short redCardsA;
	private short redCardsB;

	private short cornersA;
	private short cornersB;

	private short attemptsOnTargetA;
	private short attemptsOnTargetB;

	private float ballPossessionA;
	private float ballPossessionB;
	
	private int totalTime;
	private boolean penaltyEnd;
	
	private HashSet<Goal> goals;
	
	private HashSet<PenaltyCard> cards;
	
	

	
	/**
	 * Constructor
	 * @param tA
	 * @param tB
	 * @param match
	 * @param totalTime
	 * @param penaltyEnd
	 */
	public MatchResult(Team tA, Team tB, Match match, int totalTime, boolean penaltyEnd) {
		
		super();
		
		setTeamA(tA);
		setTeamB(tB);
		setMatch(match);
		setTotalTime(totalTime);
		setPenaltyEnd(penaltyEnd);
		
		this.goals = new HashSet<Goal>();
		this.cards = new HashSet<PenaltyCard>();
		
	}
	
	// Partial constructor for searching
	public MatchResult(Team tA, Team tB, Match match) {
		setTeamA(tA);
		setTeamB(tB);
		setMatch(match);
	}
	
	public MatchResult(Team teamA, Team teamB, Match match, int totalTime,
			boolean penaltyEnd, short cornersA, short attemptsOnTargetA, float ballPossessionA,
			short cornersB, short attemptsOnTargetB, float ballPossessionB) { 
		
		setTeamA(teamA);
		setTeamB(teamB);
		setMatch(match);
		setTotalTime(totalTime);
		setPenaltyEnd(penaltyEnd);
		
		this.cornersA = cornersA;
		this.attemptsOnTargetA = attemptsOnTargetA;
		setBallPossessionA(ballPossessionA);
		
		this.cornersB = cornersB;
		this.attemptsOnTargetB = attemptsOnTargetB;
		setBallPossessionB(ballPossessionB);
	}
	
	/**
	 * get Goals set
	 * @return
	 */
	private Set<Goal> getGoals() {
		return goals;
	}

	/**
	 * get Cards set
	 * @return
	 */
	private Set<PenaltyCard> getPenaltyCards() {
		return cards;
	}

	/**
	 * @param teamA the teamA to set
	 */
	private void setTeamA(Team teamA) {
		this.teamA = teamA;
	}

	/**
	 * @param teamB the teamB to set
	 */
	private void setTeamB(Team teamB) {
		this.teamB = teamB;
	}


	/**
	 * @param match the match to set
	 */
	private void setMatch(Match match) {
		this.match = match;
	}


	/**
	 * @param totalTime the totalTime to set
	 */
	private void setTotalTime(int totalTime) {
		if(totalTime>0 && totalTime<=120)
			this.totalTime = totalTime;
		else
			totalTime=Constants.gameDuration;
	}


	/**
	 * @param penaltyEnd the penaltyEnd to set
	 */
	private void setPenaltyEnd(boolean penaltyEnd) {
		this.penaltyEnd = penaltyEnd;
	}
	
	private void setBallPossessionA(float ballPossessionA) {
		
		if(ballPossessionA >= 0 && ballPossessionA <= 1) { 
			
			this.ballPossessionA = ballPossessionA;
		}
	}
	
	private void setBallPossessionB(float ballPossessionB) {
		
		if(ballPossessionB >= 0 && ballPossessionB <= 1) { 
			
			this.ballPossessionB = ballPossessionB;
		}
	}

	/**
	 * @return the teamA
	 */
	public Team getTeamA() {
		return teamA;
	}
	/**
	 * @return the teamB
	 */
	public Team getTeamB() {
		return teamB;
	}
	/**
	 * @return the match
	 */
	public Match getMatch() {
		return match;
	}
	/**
	 * @return totalTime (in minutes) of this game
	 */
	public int getTotalTime() {
		return totalTime;
	}
	
	/**
	 * @return the penaltyEnd
	 */
	public boolean isPenaltyEnd() {
		return penaltyEnd;
	}
	
	/**
	 * @return the tAYellowCards
	 */
	public short gettAYellowCards() {
		
		return yellowCardsA;
	}


	/**
	 * @return the tBYellowCards
	 */
	public short gettBYellowCards() {
		return yellowCardsB;
	}


	/**
	 * @return the tARedcards
	 */
	public short gettARedcards() {
		return redCardsA;
	}

	/**
	 * @return the tBRedcards
	 */
	public short gettBRedcards() {
		return redCardsB;
	}
	
	public short getACorners() {
		return cornersA;
	}
	
	public short getBCorners() {
		return cornersB;
	}
	
	public short getAattemptsOnTarget() {
		return attemptsOnTargetA;
	}
	

	public short getBattemptsOnTarget() {
		return attemptsOnTargetB;
	}

	public float getAballPossession() {
		return ballPossessionA;
	}
	
	public float getBballPossession() {
		return ballPossessionB;
	}

	/*
	 * added getter to fix bug in toString()
	 */
	public int gettAgoals() {
		return goalsA;
	}

	/*
	 * added getter to fix bug in toString()
	 */
	public int gettBgoals() {
		return goalsB;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((match == null) ? 0 : match.hashCode());
		result = prime * result + ((teamA == null) ? 0 : teamA.hashCode());
		result = prime * result + ((teamB == null) ? 0 : teamB.hashCode());
		return result;
	}
	

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatchResult other = (MatchResult) obj;
		if (match == null) {
			if (other.match != null)
				return false;
		} else if (!match.equals(other.match))
			return false;
		if (teamA == null) {
			if (other.teamA != null)
				return false;
		} else if (!teamA.equals(other.teamA))
			return false;
		if (teamB == null) {
			if (other.teamB != null)
				return false;
		} else if (!teamB.equals(other.teamB))
			return false;
		return true;
	}
	

	@Override
	/**
	 * 
	 */
	public String toString() {
		return String.format("Match %s Results:\n" +
				"\t\tteamA %s G %d RC %d YC %d AOT %d CN %d BP %f" +
				"\n\t\tteamB %s G %d RC %d YC %d AOT %d CN %d BP %f\n"
				, getMatch(),getTeamA().gettName(), gettAgoals(), gettARedcards() 
				, gettAYellowCards(), getAattemptsOnTarget(), getACorners(), getAballPossession()
				,getTeamB().gettName(), gettBgoals(), gettBRedcards() 
				, gettBYellowCards(), getBattemptsOnTarget(), getBCorners(), getBballPossession());
	}
	
	public boolean addGoal(Goal g) { 
		
		if(g == null) { 
			
			return false;
		}
		
		if(goals.contains(g)) { 
			
			return false;
		}
		
		goals.add(g);
		
		if(g.getScorerTeam().equals(teamA)) { 
			
			goalsA++;
		}

		else if (g.getScorerTeam().equals(teamB)) {
			
			goalsB++;
		}
		
		teamA.updateTeamProperties();
		teamB.updateTeamProperties();
		
		return true;
		
	}
	
	public boolean addPenaltyCard(PenaltyCard c) {
		
		if(c == null) {
			
			return false;
		}
		
		if(cards.contains(c)) { 
			
			return false;
		}
		
		cards.add(c);
		
		if(c.getTeamOfPlayer().equals(teamA)) { 
			
			if(PenaltyCard.isRedCard(c)) { 
				
				redCardsA++;
			}
			
			else if (PenaltyCard.isYellowCard(c)) {
				
				yellowCardsA++;
			}
		}
		
		else if (c.getTeamOfPlayer().equals(teamB)) {
			
			if(PenaltyCard.isRedCard(c)) { 
				
				redCardsB++;
			}
			
			else if (PenaltyCard.isYellowCard(c)) {
				
				yellowCardsB++;
			}
			
		}
		
		teamA.updateTeamProperties();
		teamB.updateTeamProperties();
		
		return true;
		
	}
	
	public Set<PenaltyCard> discardedRedCards(Team t) { 
		
		Set<PenaltyCard> discardedRedCards = new HashSet<>();
		
		Team teamOfPlayer;
		
		for (PenaltyCard pc : cards) {
			
			teamOfPlayer = pc.getTeamOfPlayer();
			
			if(teamOfPlayer.equals(t)) { 
				
				if(PenaltyCard.isRedCard(pc)) { 
					
					discardedRedCards.add(pc);
					cards.remove(pc);
				}
			}
			
		}
		
		return discardedRedCards;
	}
	
	public Player getTheBestScorerOfTeam(Team t) { 
		
		HashMap<Player, Integer> playerGoals = new HashMap<>();
		Team teamOfPlayer;
		Player thePlayer;
		int value;
		
		for (Goal goal : goals) {
			
			teamOfPlayer = goal.getScorerTeam();
			
			if(teamOfPlayer.equals(t)) {
				
				thePlayer = goal.getPlayer();
				
				if(playerGoals.containsKey(thePlayer)) {
					
					value = playerGoals.get(thePlayer);
					playerGoals.put(thePlayer, value + 1);
				}
				
				else {
					
					playerGoals.put(thePlayer, 1);
				}
			}
			
		}
		
		thePlayer = findMaxInMap(playerGoals);
		return thePlayer;
				
				
	}
	
	/*
	 * private method to find the max value in a map
	 * @return: Player object
	 * @param: HashMap
	 */
	private <Player, Integer extends Comparable<Integer>> Player findMaxInMap(HashMap<Player, Integer> map) {
	   
		Map.Entry<Player, Integer> maxEntry = null;
		
	    for (Map.Entry<Player, Integer> entry : map.entrySet()) {
	    	
	        if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
	        	
	            maxEntry = entry;
	        }
	    }
	    
	    return maxEntry.getKey();
	}
	
	
	public boolean isWinner(Team t) {
		
		if(getTeamA().equals(t)) { 
			
			if(goalsA > goalsB) { 
				
				return true;
			}
			
			else if (goalsB < goalsA) {
				
				return false;
			}
		}
		
		return false;
	
	}
	
	

	
} // ~ END MatchResult class
