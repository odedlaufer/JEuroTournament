package core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import utils.Constants;
import utils.Country;
import utils.Effectiveness;


/**
 * This class represents the Coach and its properties
 *
 */
public class Team implements Comparable<Team>, Effectiveness {
	
	/**
	 * The team identification number 
	 * <b>used as key of this object<b>
	 */
	private String tId;
	
	/**
	 * team full name
	 */
	private String tName;
	
	/**
	 * country that this team represent, each team 
	 * represent one country 
	 */
	private Country represents;
	
	/**
	 * this calculated field is updated after 
	 * scouring in each game he participant
	 */
	private short goalsScored;
	
	/**
	 * this calculated field is updated 
	 * and calculate <b> the goals entered <b> 
	 * the total nets of this team
	 */
	private short goalsAgainst;
	
	/**
	 * total of YELLOW card received by<b> all <b>players of this team 
	 */
	private short yellowCards;
	
	/**
	 * total of RED card received by<b> all <b>players of this team 
	 */
	private short redCards;
	
	/**
	 * this fans of this team  
	 */
	private int fansCount;
		
	/**
	 * the team player up to 
	 * @see Constants#totalPlayers
	 */
	private Player[] players;
	
	/**
	 * all the games played by the team
	 */
	private Set<MatchResult> results;
	
	/**
	 * the fund received by the Sponsor
	 */
	private Fund fund;
	
	/*
	 * all technical coaches of the team
	 */
	private Set<Coach> technicalCoach;
	
	/*
	 * all fitness coaches of the team
	 */
	private Set<Coach> fitnessCoach;
	
	
	
	
	/**
	 * Partial constructor, used for searching issues
	 * @param tId
	 */
	public Team(String tId) {
		super();
		settId (tId);
	}
	
	/**
	 * Full constructor, notice that all the calculated fields are not
	 * initialized 
	 * @param tId
	 * @param tName
	 * @param represents
	 * @param fansCount
	 */
	public Team(String tId, String tName, String represents,
			int fansCount) {
		super();
		settId(tId);
		settName(tName);
		setRepresents(represents);
		setFansCount(fansCount);
		players = new Player[Constants.totalPlayers];
		results= new HashSet <MatchResult>();
		
		technicalCoach = new HashSet<>();
		fitnessCoach = new HashSet<>();
		
	}
	

	/**
	 * the method return the key of this object
	 * @return String key
	 */
	public String getKey(){
		return gettId();
	}
	
	public String gettId() {
		return tId;
	}
	/**
	 * a key property, must not be null
	 * in case it is then the id is a combination of
	 * the letter t and the Date today  
	 * @param tId
	 */
	public void settId(String tId) {
		if(tId!=null)
			this.tId = tId;
		else
			this.tId= "t"+Calendar.getInstance().getTime();
	}
	
	/**
	 * @return the team name
	 */
	public String gettName() {
		return tName;
	}
	/**
	 * set the team name
	 * @param tName
	 */
	public void settName(String tName) {
		this.tName = tName;
	}
	
	/**
	 * @return Country that this team represent 
	 */
	public Country getRepresents() {
		return represents;
	}
	/**
	 * set the value of the Country, for implementing the method
	 * @see Country
	 * @param represents
	 */
	public void setRepresents(String represents) {
		this.represents = Country.valueOf(represents);
	}
	
	/**
	 * @return fansCount , total number of the fans
	 */
	public int getFansCount() {
		return fansCount;
	}
	
	/**
	 * set the number of teams' fans, no negative numbers allowed
	 * @param fansCount
	 */
	public void setFansCount(int fansCount) {
		if (fansCount>=0)
			this.fansCount = fansCount;
	}
	
	/**
	 * @return total goals achieved by this team
	 */
	public short getGoalsScored() {
		return goalsScored;
	}
	/**
	 * @return total goals entered this team net
	 */
	public short getGoalsAgainst() {
		return goalsAgainst;
	}
	/**
	 * @return total received yellow cards by this teams players
	 */
	public short getYellowCards() {
		return yellowCards;
	}
	/**
	 * @return total received red cards by this teams players
	 */
	public short getRedCards() {
		return redCards;
	}
	
	/**
	 * get players
	 * @return
	 */
	private Player[] getPlayers() {
		return players;
	}
	
	/**
	 * gets fund			
	 * @return fund
	 */
	public Fund getFund() {
		return fund;
	}

	/**
	 * Computes and updates team properties according to matches results"
	 * goalsAgainst, goalsScored, yellowCards, redCards
	 */
	public void updateTeamProperties(){
		
		short goalsScored=0;
		short goalsAgainst=0;
		short yellowCards=0;
		short redCards=0;
		
		for(MatchResult mr:results){
			if (mr!=null){
				if(mr.getTeamA().equals(this)){
					
					goalsScored+=(short) mr.gettAgoals();
					goalsAgainst+=(short) mr.gettBgoals();
					yellowCards+=(short) mr.gettAYellowCards();
					redCards+=(short) mr.gettARedcards();
				}
				else{
					goalsScored+=(short) mr.gettBgoals();
					goalsAgainst+=(short) mr.gettAgoals();
					yellowCards+=(short) mr.gettBYellowCards();
					redCards+=(short) mr.gettBRedcards();
				
				}
			}
		}
		
		this.goalsAgainst= goalsAgainst;
		this.goalsScored = goalsScored;
		this.yellowCards = yellowCards;
		this.redCards	 = redCards;
	}
	
	
	/**
	 * the method calculate the gap between overall
	 * goals scored by this team minus overall goals against this team
	 * <p>If the gap is negative it means that the team has received goals 
	 * more than scored<p>
	 * @return the gap between the goals of this team 
	 */
	public Integer calculateGoalsGap() {
		
		int agains=0,scored=0;
		 
		for(MatchResult res: results){
			if(res!=null){
				if(this.equals(res.getTeamA())){
					scored+= res.gettAgoals();
					agains+= res.gettBgoals();
				}else{
					agains+= res.gettAgoals();
					scored+= res.gettBgoals();
				}
			}
		}
		return scored-agains;
	}
	
	
	
	/**
	 * add Fund to this team, IFF the team has no funding yet.
	 * @param fund
	 * @return true if successfully added
	 */
	public boolean addFund(Fund fund){
		if(this.fund==null){
			this.fund = fund;
			return true;
		}
		return false;
	}
	
	
	/**
	 * the method removes the funding from this team IFF it exists 
	 * @return true if the fund was changed to null
	 */
	public boolean removeFund(){
		if(this.fund != null){
			this.fund = null;
			return true;
		}
		return false;
	}
	
	
	/**
	 * The method adds player to this team IFF the
	 * player does not exist on this team
	 * @param player
	 * @return true if added player to this team players
	 */
	public boolean addPlayer(Player player){
		
		int index = -1;
		for(int i = players.length-1;i>=0;i--){
			if(players[i]!=null ){
				if(players[i].equals(player))
					return false;
			}else 
				index = i;
		}
		if(index !=-1){
			players[index]=player;
			return true;
		}
		return false;
	}
	
	/**
	 * Removes player from the team
	 * @param player
	 * @return true if the player was removed
	 */
	public boolean removePlayer(Player player){
		
		for(int i =0; i<players.length;i++){
			if(players[i]!=null && players[i].equals(player)){
				players[i]=null;
				return true;
			}			
		}
		return false;
	}
	
	
	/**
	 * adds the results received to this method IFF
	 * it does NOT exists
	 * @param result
	 * @return true if manage to add
	 */
	public boolean addMatchResult(MatchResult result){
		
		if(result !=null && results.add(result)){
			updateTeamProperties();
			return true;
		}
		return false;
		
	}
	
	/**
	 * removes a result IFF it exists in the results array of this Team
	 * @param result
	 * @return true if successfully removed
	 */
	public boolean removeMatchResult(MatchResult result){
		
		if(result !=null && results.remove(result)){
			updateTeamProperties();
			return true;
		}
		return false;
		
	}
	
	
	
	/**
	 * Gets all players with one skill
	 * @return all players with one skill
	 */
	public ArrayList<Player> getAllPlayersWithOneSkill() {
		
		ArrayList<Player> pOneSkill=new ArrayList<Player>();
		for(Player p:getPlayers())
			if(p!=null && p.isOneSkillPlayer())
				pOneSkill.add(p);
		return pOneSkill;
	}
	


	/**
	 * check if this team is funded by sponsor of same Country
	 * @return true if this team is funded by sponsor of same Country. Otherwise, return false
	 */
	public boolean isFundedBySponsorOfSameCountry() {
		if(getFund()!=null){
			Fund f=getFund();
			Country c=f.getSponsorRepresent();
			if(c.equals(getRepresents()))
			    return true;
		}
		return false;
	}
	
	//====================
	

		/**
		 * Adds a Coach to this Team object.
		 * isTechnical = true: means that the coach is a technical one.
		 * Otherwise it is a coach which responsible for the fitness of this Team 
		 * The method fails to add a Coach if it is null or it is already exists
		 * @param coach
		 * @param isTechnical
		 * @return true if successfully added. Otherwise, returns false
		 */
		public boolean addCoach(Coach coach, boolean isTechnical){	
			
			if(coach == null) { 
				
				return false;
			}
			
			if(isTechnical && !technicalCoach.contains(coach)) { 
				
				technicalCoach.add(coach);
				return true;
			}
			
			if(!isTechnical && !fitnessCoach.contains(coach)) { 
				
				fitnessCoach.add(coach);
				return true;
			}
			
		return false;
	}
		
		/**
		 * Removes a Coach to this Team object.
		 * Otherwise it is a coach which responsible for the fitness of this Team 
		 * The method fails to remove a Coach if it is null or it is not exists
		 * @param coach
		 * @return true if successfully removed. Otherwise, returns false
		 */
		public boolean removeCoach(Coach coach){
			
			if(coach == null) { 
				
				return false;
			}
			
			if(technicalCoach.contains(coach) && fitnessCoach.contains(coach)) {
				
				technicalCoach.remove(coach);
				fitnessCoach.remove(coach);
				return true;
			}
			
			else if (technicalCoach.contains(coach)) {
				
				technicalCoach.remove(coach);
				return true;
				
			}
			
			else if (fitnessCoach.contains(coach)) {
				
				fitnessCoach.remove(coach);
				return true;
			}
			
			return false;
			
		}
		

	
		/**
		 * checks if the given player belongs to this team
		 * @param p
		 * @return true if the given player belongs to this team otherwise return false
		 */
		public boolean isPlayerOfTeam(Player p){
			
			Set<Player> playersSet = new HashSet<>(); 
			
			if(p == null) { 
				
				return false;
			}
			
			for (Player player : players) {
				
				playersSet.add(player);
			}
			
			return playersSet.contains(p);
				
				
			
		}	
		
	
	
	/**
	 * 
	 * @return coaches that meets the condition
	 */
	public Collection<Coach> getAllCoachesWithTwoRules() {
		
		Collection<Coach> twoRolesCoaches = new HashSet<>();
		
		
		for (Coach coach : technicalCoach) {
			
			if(fitnessCoach.contains(coach)) { 
				
				twoRolesCoaches.add(coach);
			}
			
		}
		
		return twoRolesCoaches;
		
	}
	

	/**
	 * checks if the given match result is exists for this team
	 * @param mr
	 * @return true if the given matchRsult is related to this team object
	 */
	public boolean containsMatchResult(MatchResult mr) {
		
		if(mr == null) { 
			
			return false;
		}
		
		return results.contains(mr);
	}

	public Player getTheBestScorer() {
		
		HashMap<Player, Integer> mostScoringPlayers = new HashMap<>();
		Player thePlayer;
		int value;
		
		for (MatchResult mr : results) {
			
			thePlayer = mr.getTheBestScorerOfTeam(this);
			
			if(mostScoringPlayers.containsKey(thePlayer)) { 
				
				value = mostScoringPlayers.get(thePlayer);
				mostScoringPlayers.put(thePlayer, value + 1);
			}
			
			else {
				
				mostScoringPlayers.put(thePlayer, 1);
			}
			
		}
		
		return findMaxInMap(mostScoringPlayers);
		
		
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


	
	public Collection<PenaltyCard> discardRedCardsForPlayers(){
		
		Collection<PenaltyCard> redCards = new ArrayList<>();
		Set<PenaltyCard> discardedCardsForTeam;
		
		for (MatchResult mr : results) {
			
			discardedCardsForTeam = mr.discardedRedCards(this);
			
			for (PenaltyCard pc : discardedCardsForTeam) {
				
				
				if(PenaltyCard.isRedCard(pc)) { 
					
					redCards.add(pc);
				}
				
				
			}
		}
		
		this.redCards = 0;
		
		return redCards;
		
			
	}
		
		
		
	
	
	public int getTotalWonMatches(){
		
		int winCounter = 0;
		
		for (MatchResult mr : results) {

			if(mr.gettAgoals() > mr.gettBgoals()) { 
				
				winCounter++;
			}
			
		}
		
		return winCounter;
	}
	

	@Override
	/**
	 * Compares according to primary key field
	 */
	public int compareTo(Team o) {
		return this.gettId().compareTo(o.gettId());	
	}
	
	@Override
	/**
	 * hash code according to id (primary key)
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gettId() == null) ? 0 : gettId().hashCode());
		return result;
	}

	@Override
	/**
	 * Checks equality according to the id of the Person
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (gettId() == null) {
			if (other.gettId() != null)
				return false;
		} else if (!gettId().equals(other.gettId()))
			return false;
		return true;
	}
	

	@Override
	/**
	 * Gets the string representation of this Person object
	 */
	public String toString() {
		return "Team: tId=" + gettId() + ", tName=" + gettName() 
				+ ", represents=" + getRepresents() + ", goalsScored=" + getGoalsScored()
				+ ", goalsAgainst=" + getGoalsAgainst() + ", yellowCards="
				+ getYellowCards() + ", redCards=" + getRedCards() + ", fansCount="
				+ getFansCount() + ", EffectivnessLevel="+ String.format("%.3f",getEffectivenessLevel());
	}

	//TODO
	@Override
	public double getEffectivenessLevel() {
		
		double allGamesEffect = 0;
		double singleGameEffect;

		for (MatchResult mr : results) {
			
			double x = mr.getAballPossession() + (mr.getACorners() * 0.3);
			double y = (mr.gettAYellowCards() * 0.1) - (mr.gettARedcards() * 0.1);
			double z = (mr.gettAgoals() * 0.3) + (mr.getAattemptsOnTarget() * 0.2);
			
			singleGameEffect = x - y + z;
			allGamesEffect += singleGameEffect;
			
		}
		
		return allGamesEffect / results.size();
	}

	
}
