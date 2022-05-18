package init;

import java.awt.Color;
import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import utils.Constants;
import utils.Effectiveness;

import core.Coach;
import core.Fund;
import core.Goal;
import core.Match;
import core.MatchResult;
import core.PenaltyCard;
import core.Person;
import core.Player;
import core.Sponsor;
import core.Team;

/**
 * This JEuroTournament object represents the class system
 */
public class JEuroTournament {
	
	//---------------------the class parameters  -------------------------
	
	// TODO Note: in general key of a map can be any other type you want and not only String
	/**
	 * All teams list in the system
	 */
	private Map<String,Team> teams;
	
	/**
	 * All Matches listed in the system
	 */
	private Map<String,Match> matches;
	/**
	 * all players sponsors
	 */
	private Map<String,Person> persons;
	
	//--------------------- Constructors -----------------------------
	
	/**
	 * Constructor
	 * Perform initialization for related data structures
	 */
	public JEuroTournament() {
			teams	= new HashMap<String,Team>();
			matches = new HashMap<String,Match>();
			persons = new HashMap<String,Person>();
	}
		
	
	// ---------------------- DB Methods -----------------------------
	
	/**
	 * 
	 * The method creates and add new team to the system
	 * IFF the team does not exist.
	 * @param tId
	 * @param tName
	 * @param represents
	 * @param fansCount
	 * @return if manage to add
	 */
	public  boolean addTeam(String tId, String tName, String represents,
			int fansCount){
		Team teamToAdd = new Team(tId); 
		if(tId!=null && tName!=null	&& represents!=null	){
			// Checking if the team exist
			if(!teams.containsKey(teamToAdd.getKey())){
				teamToAdd = new Team(tId, tName, represents, fansCount);			
				teams.put(teamToAdd.getKey(),teamToAdd);
				return true;
			}
		}
		return false;
	} // ~ END OF addTeam
	
    
	/**
	 * Remove Team
	 * @param tId
	 * @param tName
	 * @param represents
	 * @param fansCount
	 * @return
	 */
	public  boolean removeTeam(String tId){
		Team teamToRemove = new Team(tId); 
		if(tId!=null){
			// Checking if the team exist
			if(teams.containsKey(teamToRemove.getKey())){			
				teams.remove(teamToRemove.getKey());
				return true;
			}
		}
		return false;
	} // ~ END OF removeTeam
	
    
	
	/**
	 * adds a match to the system IFF it does not exist
	 * @param matchID
	 * @param date
	 * @param stadium
	 * @param soldTickets
	 * @return true if managed to add the match
	 */
	public  boolean addMatch(String matchID, Date date, String stadium,long soldTickets){
		 
		if(matchID!=null && date!=null 
				&& stadium!=null){
			
			Match matchToAdd= new Match(matchID, date, stadium, soldTickets);
			if(!matches.containsKey(matchToAdd.getKey())){
				matches.put(matchToAdd.getKey(),matchToAdd);
				return true;
		
			}
		}
		return false;
	} // ~ END OF addMatch

	public  boolean RemoveMatch(String matchID, Date date){
		 
		if(matchID!=null && date!=null){
			
			Match matchToRem= new Match(matchID, date);
			if(matches.containsKey(matchToRem.getKey())){
				matches.get(matchToRem.getKey()).removeMatchResult();
				matches.remove(matchToRem.getKey());
				return true;
			}
		}
		return false;
	} // ~ END OF RemoveMatch
	
	/**
	 * receive a person and without knowing its exact type
	 * the method adds the Person to the system data base 
	 * IFF the person does not exist 
	 * @param person
	 * @return true if successfully added
	 */
	public boolean addPerson(Person person){
		if(!persons.containsKey(person.getKey())){
			 persons.put(person.getKey(),person);
			 return true;
		}
		return false;
	}
	
	/**
	 * the method adds a player to a team 
	 * IFF the player exist and he does not belong to the team or any other team
	 * @param pId
	 * @param pNum
	 * @param tId
	 * @return true if managed to add the player to the team and vis versa 
	 */
	public boolean addPlayerToTeam(String pId, int pNum,String tId){
		
		if(pId!=null && pNum>0 && tId!=null){
			Player pl = new Player(pId, pNum);
			Team tm   = new Team(tId);
			
			if(persons.containsKey(pl.getKey()) && teams.containsKey(tm.getKey())){
				
				pl =(Player) persons.get(pl.getKey());
				tm = teams.get(tm.getKey());
				
				if(pl.addTeam(tm))
					if(tm.addPlayer(pl))
						return true;
					else
					{
						pl.removeTeam();
						return false;
					}
			}
		}
		return false;
	}
	
	
	

	/**
	 * the method adds funding to the team by the sponsor
	 * @param pId
	 * @param surname
	 * @param tId
	 * @param amount
	 * @param fDate
	 * @return true if managed to add the team to the sponsor and vis versa
	 */
	public boolean fundTeamBySponser(String pId, String surname,String tId
			,double amount,Date fDate){
		
		if(pId!=null && surname!=null && tId!=null  && fDate!=null){
			
			Sponsor sp 	= new Sponsor(pId, surname);
			Team 	tm 	= new Team(tId);
			
			if(persons.containsKey(sp.getKey()) && teams.containsKey(tm.getKey())){
				
				sp = (Sponsor)persons.get(sp.getKey());
				tm = teams.get(tm.getKey());
				
				
				Fund fund = new Fund(amount, fDate, sp, tm); 
				if(tm.addFund(fund))	
					if(sp.addFundedTeam(fund))
						return true;
					else
					{
						tm.removeFund();
						return false;
					}
			}
		}
		return false;
	}
	
	
	/**
	 * the method ad a Match result that occurred between two teams,
	 * Team A and Team B. in addition the result details. 
	 * first search for the teams and match if they exist, create the match result
	 * object and then add it to the proper objects.  
	 * @param tIdA
	 * @param tIdB
	 * @param mId
	 * @param totalTime
	 * @param penaltyEnd
	 * @return if successfully added match result to both match the the involved teams
	 */
	public boolean addMatchResult(String tIdA , String tIdB ,String mId, Date date, 
			int totalTime , boolean penaltyEnd){

		if(tIdA!=null && tIdB!=null && date!=null && totalTime>0 && mId!=null){
		
			Team tA 	= new Team(tIdA);
			Team tB 	= new Team(tIdB);
			Match match = new Match(mId,date);
	
			if(teams.containsKey(tA.getKey()) && 
					teams.containsKey(tB.getKey()) && 
					matches.containsKey(match.getKey()))
			{
	
				tA 		= teams.get(tA.getKey());
				tB 		= teams.get(tB.getKey());
				match 	= matches.get(match.getKey());
				
				if(!tA.equals(tB)){
					
					MatchResult mResult = new MatchResult(tA, tB, match, totalTime, penaltyEnd);
	
					if(tA.addMatchResult(mResult))
						if(tB.addMatchResult(mResult)){
							if(match.addMatchResult(mResult))
								return true;
							else
							{
								tA.removeMatchResult(mResult);
								tB.removeMatchResult(mResult);
								return false;
							}
						}
						else
						{
							tA.removeMatchResult(mResult);
							return false;
						}
		
				}
			}
		}
	    return false;
	}
	
	
	/**
	 * for HW 3
	 * @param tIdA
	 * @param tIdB
	 * @param mId
	 * @param date
	 * @param totalTime
	 * @param penaltyEnd
	 * @param cornersA
	 * @param attemptsOnTargetA
	 * @param ballPossessionA
	 * @param cornersB
	 * @param attemptsOnTargetB
	 * @param ballPossessionB
	 * @return
	 */
	public boolean addMatchResult(String tIdA , String tIdB ,String mId, Date date, 
			int totalTime , boolean penaltyEnd,short cornersA, short attemptsOnTargetA, float ballPossessionA, 
			short cornersB, short attemptsOnTargetB, float ballPossessionB){
		
		if(tIdA!=null && tIdB!=null && date!=null && totalTime>0 && mId!=null){
		
			Team tA 	= new Team(tIdA);
			Team tB 	= new Team(tIdB);
			Match match = new Match(mId,date);
	
			if(teams.containsKey(tA.getKey()) && 
					teams.containsKey(tB.getKey()) && 
					matches.containsKey(match.getKey()))
			{
	
				tA 		= teams.get(tA.getKey());
				tB 		= teams.get(tB.getKey());
				match 	= matches.get(match.getKey());
				
				if(!tA.equals(tB)){
					
					MatchResult mResult = new MatchResult(tA, tB, match, totalTime, penaltyEnd, cornersA,  
							attemptsOnTargetA,  ballPossessionA, 
							 cornersB,  attemptsOnTargetB,  ballPossessionB);
	
					if(tA.addMatchResult(mResult))
						if(tB.addMatchResult(mResult)){
							if(match.addMatchResult(mResult))
								return true;
							else
							{
								tA.removeMatchResult(mResult);
								tB.removeMatchResult(mResult);
								return false;
							}
						}
						else
						{
							tA.removeMatchResult(mResult);
							return false;
						}
		
				}
			}
		}
	    return false;
	}
	
	

	// ===================== Queries  =================================== 
		/**
		 * Elite teams are defined as teams with total goals gap (in-out)
		 * bigger or equals to the parameter gap that is received to this method
		 * <p> then the method arrange the teams according to this gap from highest to lowest<p>
		 * <p><b>notice:<b> if two teams have the same goals gap then the are compared
		 * according to the total fans number of each team.<p>    
		 * @return Team[] contains the elite teams arrange by goals gap, number of fans
		 */		
		public Team[] eliteTeamsArrangedByGoalsGap(int gap){
			
			Comparator<Team> gapComp=new Comparator<Team>(){
				public int compare(Team t1,Team t2){
					
					Integer t1gap=t1.calculateGoalsGap();
					Integer t2gap=t2.calculateGoalsGap();
					int comp=t2gap.compareTo(t1gap);
					if(comp!=0)
						return comp;
						return t1.getFansCount()-t2.getFansCount();
				}
			};	
					
			ArrayList<Team> sortedList=new ArrayList<>();
			for(Team t:teams.values()) 
				if(t.calculateGoalsGap()>=gap) 
					sortedList.add(t);	

			Collections.sort(sortedList, gapComp);
			return sortedList.toArray(new Team[sortedList.size()]);
			
		}
		
		/**
		 * the method searches the players for total of X player 
		 * that has exactly one skill from every team. 
		 * @return Player[] Array of X player from every team sorted by player key fields.
		 */
		public Player[] getXDifferentPlayerWithOneSkill(int X){
			
			ArrayList<Player> players=new ArrayList<Player>();
			
			ArrayList<Player> oneTeam=null;
			
			for(Team tm: teams.values())
				if(tm!=null){
				   oneTeam=tm.getAllPlayersWithOneSkill();
				   if(oneTeam!=null)
					   if(oneTeam.size()<=X)
						   players.addAll(oneTeam);
					   else
						   players.addAll(oneTeam.subList(0, X)); 
				}	
			
			if(players.size()>0){
			   Collections.sort(players);
			   return players.toArray(new Player[players.size()]);
			   
			}
			return null;
		}
		
		
		/**
		 * <b>Interest funding<b> is a fund which was give from a sponsor
		 * to a team that represents his(sponsor) country
		 * @param total - how many funds to be returned that meets the condition
		 * @return Fund[] that contains Interest funds
		 */
		public Fund[] InterestFunding(int total){
			
			ArrayList<Fund> iFunds = new ArrayList<Fund>();
			
	        int cnt=0;
			
	        for(Team tm:teams.values())
				if(tm!=null && tm.isFundedBySponsorOfSameCountry()){
						if(cnt<total){
					        iFunds.add(tm.getFund());
					        cnt++;
						}
				}
			return iFunds.toArray(new Fund[iFunds.size()]);
		}
		
		
		/**
		 * the method return an array of strings where each string
		 * is a combination of three raws
		 * <p>first row is the team<p>
		 * <p>second row indicates if the team received fund <p>
		 * <p>third row shows the Amount of received fund <p>
		 * <p><b>Warning:<b> the use of String.format() is <b>compulsory(must)<b><p>
		 * <b>NOT USING IT WILL CAUSE LOSING POINT<b> 
		 * @return String[] that contains Objects as indicated above
		 */
		public String[] getTeamsFundAndTotal(){
			ArrayList<String> tmsFunds = new ArrayList<String>();
			
			for(Team t:teams.values()){
				if(t!=null){
					
					String temp = String.format("%s: %s\n\t%s: %b\n\t%s: %.2f"
							, "Team", t
							, "Funded", (t.getFund()!=null)?true:false
							, "Total Amount of Fund", (t.getFund()!=null)? t.getFund().getAmount():0);
							
							tmsFunds.add(temp);
					
				}
			}
			return tmsFunds.toArray(new String[tmsFunds.size()]);
		}

		/**
		 * the method search for all matches where the time was extended
		 * but the game was over before getting to the penalty stage.
		 * @return MatchResult[] that contains objects that satisfy the above.
		 */
		public MatchResult[] extendedMatcheswithoutPenaltyKicks(){
			ArrayList<MatchResult> extM = new ArrayList<MatchResult>();
		   
			for(Match mt : matches.values())
				if(mt.getResult().getTotalTime()>Constants.gameDuration
						&& !mt.getResult().isPenaltyEnd())
					extM.add(mt.getResult());
			
			return extM.toArray(new MatchResult[extM.size()]);
		}
		
		
		/**
		 * the method return all teams that participant at least once
		 * in an extended Match that ends before the penalty stage
		 * <b>use the method:<b>
		 * @see JEuroTournament#extendedMatcheswithoutPenaltyKicks()  
		 * @return Team[] that participant in the extended matches 
		 */
		public Team[] getAllTeamsInvolvedInExtendedMatches(){
			
			MatchResult[] extM = extendedMatcheswithoutPenaltyKicks();
			Team[] tms = new Team[extM.length*2];
			
			for(MatchResult mR : extM){
				if(mR!=null){
					addTeam(mR.getTeamA(),tms);
					addTeam(mR.getTeamB(),tms);
				}
			}
			return tms;
			
		}

		/**
		 * this private method is used to add an element to the array
		 * where no duplicate items are not allowed
		 * @param team
		 * @param tms
		 * @return true if the element was added
		 */
		private boolean addTeam(Team team, Team[] tms) {
			
			int index = -1;
			
			for(int i=tms.length-1;i>=0;i--){
				if(tms[i]!=null){
					if(tms[i].equals(team)){
						return false;
					}
				}else
					index=i;
			}
			if(index!=-1){
				tms[index]=team;
				return true;
			}
			return false;
		}
		
		
	//=============================================================================
	
	/**
	 * the method removes a player to a team 
	 * IFF the player exist and he does not belong to the team or any other team
	 * @param pId
	 * @param pNum
	 * @param tId
	 * @return true if managed to remove the player to the team and vis versa 
	 */
	public boolean removePlayerFromTeam(String pId, int pNum,String tId){
		//TODO complete this method
		if(pId!=null && pNum>0 && tId!=null){
			Player pl = new Player(pId, pNum);
			Team tm   = new Team(tId);
			
			if(persons.containsKey(pl.getKey()) && teams.containsKey(tm.getKey())){
				
				pl =(Player) persons.get(pl.getKey());
				tm = teams.get(tm.getKey());
				
				if(pl.removeTeam())
					if(tm.removePlayer(pl))
						return true;
					else
					{
						pl.addTeam(tm);
						return false;
					}
			}
		}
		return false;
	}
	
	/**
	 * Adds new coach to the system and to a team
	 * the method checks if the team exist and the coach does not
	 * then it adds the team to the coach and vice versa
	 * @param tId
	 * @param pId
	 * @param pFullName
	 * @param age
	 * @param nation
	 * @param role
	 * @param seniority
	 * @return true if successfully adds the coach to the system and to the team
	 */
	public boolean addCoachToTeam(String tId,String pId, String pFullName, short age
			,String nation,String role, int seniority){
		
		if(tId!=null && pId !=null && pFullName!=null && age>0 && nation!=null && role!=null && seniority>0){
			
				Coach coach = new Coach(pId);
				Team team   = new Team(tId);
				
				if(teams.containsKey(team.getKey()))
				{
					if(!persons.containsKey(coach.getKey()))
					{
					
						coach=new Coach(pId, pFullName, age, nation, seniority);
					
						// if succeed to add coach
						if(addPerson(coach))
						{
							coach=(Coach) persons.get(coach.getKey());
							team = teams.get(team.getKey());
							
							if(coach.addTeam(team))
								if(team.addCoach(coach, role.equals("TECHNICAL")))
									return true;
							    else
							    {
									coach.removeTeam();
									return false;
								}	
						}
				
					}
					else 
					{ // coach is already exists
						coach=(Coach) persons.get(coach.getKey());
						team = teams.get(team.getKey());
						if(coach.hasTeam(team)){
							
							if(team.addCoach(coach, role.equals("TECHNICAL")))
								return true;
						}
					}
		
				}
		}
		return false;
	}
	
	
	/**
	 * Removes coach from a team
	 * the method checks if the team exist and the coach does not
	 * then it adds the team to the coach and vice versa
	 * @param tId
	 * @param pId
	 * @return true if successfully adds the coach to the system and to the team
	 */
	public boolean removeCoachFromTeam(String tId,String pId){
		
		if(tId == null && pId == null) { 
			
			return false;
		}
		
		Coach coach = new Coach(pId);
		Team team = new Team(tId);
		
		if(persons.containsKey(coach.getKey()) && teams.containsKey(team.getKey())) {
			
			coach = (Coach)persons.get(coach.getKey());
			team = (Team)teams.get(team.getKey());
			
			if(coach.removeTeam()) { 
				if(team.removeCoach(coach)) { 
					
					return true;
				}
				else {
					
					coach.addTeam(team);
					return false;
				}
			}
		}
		
		return false;

	}
	

	/**
	 * the method ad a Match result that occurred between two teams,
	 * Team A and Team B. in addition the result details. 
	 * first search for the teams and match if they exist, create the match result
	 * object and then add it to the proper objects.  
	 * @param tIdA
	 * @param tIdB
	 * @param mId
	 * @return if successfully added match result to both match the the involved teams
	 */
	public boolean removeMatchResult(String tIdA , String tIdB ,String mId,Date date){
		
		if(tIdA != null && tIdA != null && mId != null && date != null) { 
			
			Team teamA = new Team(tIdA);
			Team teamB = new Team(tIdB);
			
			if(teams.containsKey(tIdA) && teams.containsKey(tIdB)) { 
				
				teamA = (Team)teams.get(teamA.getKey());
				teamB = (Team)teams.get(teamB.getKey());
				
				Match match = new Match(mId, date);
				
				if(matches.containsKey(mId)) { 
					
					MatchResult matchResult = match.getResult();
					
					if(teamA.removeMatchResult(matchResult)) { 
						if(teamB.removeMatchResult(matchResult)) { 
							
							return true;
						}
						else {
							
							teamA.addMatchResult(matchResult);
							return false;
						}
					}
				}
			}
		}
		
		return false;


	}
	
	
	/**
	 * 
	 * @param tIdA
	 * @param tIdB
	 * @param mId
	 * @param date
	 * @param pId
	 * @param pNum
	 * @param minute
	 * @param penalty
	 * @return true if succeed to add goal details
	 */
	public boolean addGoalDetails(String tIdA , String tIdB ,String mId, Date date, 
			                      String pId, int pNum,short minute, boolean penalty){
		// TODO implement this method

		if(tIdA !=null && tIdB !=null 
				&&  mId !=null && date!=null 
			                   &&    pId !=null && pNum>0 &&  minute>0){
			
			Team tA 	= new Team(tIdA);
			Team tB 	= new Team(tIdB);
			Match match = new Match(mId,date);
			Player p=new Player(pId, pNum);
			
			if(teams.containsKey(tA.getKey()) && 
					teams.containsKey(tB.getKey()) && 
					matches.containsKey(match.getKey())&&
					persons.containsKey(p.getKey())){
				
				
				
				tA 		= teams.get(tA.getKey());
				tB 		= teams.get(tB.getKey());
				match 	= matches.get(match.getKey());
				p= (Player) persons.get(p.getKey());

				MatchResult mResult = match.getResult();
				
				if(tA.containsMatchResult(mResult) && tB.containsMatchResult(mResult)){
					
						Goal g=new Goal(minute, penalty);
						
						if(g.addPlayer(p))
								return mResult.addGoal(g);
						return false;
				}
			}
			
		}
		return false;
	}
	

	/**
	 * 
	 * @param tIdA
	 * @param tIdB
	 * @param mId
	 * @param date
	 * @param pId
	 * @param pNum
	 * @param minute
	 * @param color
	 * @return true if succeed 
	 */
	public boolean addPenaltyCardDetails(String tIdA , String tIdB ,String mId, Date date, 
			                      String pId, int pNum,short minute, String color){
		
		if(tIdA != null && tIdB != null && mId != null && date != null &&
				pId != null && pNum > 0 && minute > 0) { 
			
			Team teamA = new Team(tIdA);
			Team teamB = new Team(tIdB);
			Match match = new Match(mId, date);
			Player player = new Player(pId, pNum);
			
			if(teams.containsKey(teamA.getKey()) && teams.containsKey(teamB.getKey()) &&
					matches.containsKey(match.getKey()) && persons.containsKey(player.getKey())) {
				
				teamA = teams.get(teamA.getKey());
				teamB = teams.get(teamB.getKey());
				match = matches.get(match.getKey());
				player = (Player)persons.get(player.getKey());
				
				MatchResult matchResult = match.getResult();
				
				if(teamA.containsMatchResult(matchResult) && teamB.containsMatchResult(matchResult)) {
					
					PenaltyCard pc = new PenaltyCard(minute, PenaltyCard.convertToColor(color));
					
					if(pc.addPlayer(player)) {
						return matchResult.addPenaltyCard(pc);
					}
					
					pc.removePlayer();
					return false;
				}

			}
		}
		
		return false;

	}
	

		
		/**
		 * get All Coaches That Works On Two Rules
		 * @return All Coaches That Works On Two Rules
		 */
		public Collection<Coach> getAllCoachesThatWorksOnTwoRules(){
			// TODO implement this method

			HashSet<Coach> coaches=new HashSet<Coach>();
			for(Team t:teams.values())
				coaches.addAll(t.getAllCoachesWithTwoRules());
				return coaches;
		}
		
		/**
		 * discard Red Cards For Players Of Team
		 * @param tId
		 * @return discarder red cards
		 */
		public Collection<PenaltyCard> discardRedCardsForPlayersOfTeam(String tId){
			
			HashSet<PenaltyCard> redCards = new HashSet<>();
			Team team = new Team(tId);
			
			if(teams.containsKey(team.getKey())) { 
				
				team = teams.get(team.getKey());
				
				for (Team teamItr : teams.values()) {
					
					if(teamItr.getKey().equals(team.getKey())) { 
						
						redCards.addAll(teamItr.discardRedCardsForPlayers());
						return redCards;
					}
					
				}
				
			}
			// null
			return redCards;
			

		
		}
		
		Set<Effectiveness> getTop20FascinatingMatchesAndTeams() {
			
			HashSet<Effectiveness> teamsAndMatches = new HashSet<>();
			
			double effect;
			int counter = 0;
			
			Map<Double, Effectiveness> tMap = new TreeMap<>((x, y) -> Double.compare(y, x));

			
			for (Match match : matches.values()) {
				
				effect = match.getEffectivenessLevel();
				tMap.put(effect, match);
				
			}
			
			for(Team team: teams.values()) { 
				
				effect = team.getEffectivenessLevel();
				tMap.put(effect, team);
				
			}
		
			Iterator<Map.Entry<Double, Effectiveness>> itr = tMap.entrySet().iterator();
			
			while(itr.hasNext() && counter < 20) { 
				
				Effectiveness teamOrMatch = itr.next().getValue();
				teamsAndMatches.add(teamOrMatch);
				counter++;
			}
			
			return teamsAndMatches;

		}
	
}
