package init;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;

import core.Coach;
import core.Fund;
import core.MatchResult;
import core.PenaltyCard;
import core.Player;
import core.Sponsor;
import core.Team;

import utils.Effectiveness;
import utils.*;
import utils.Role;


/**
 * The Main Class -The program runner  
 */
public class MainClass {

	/**
	 * The main method of this system, gets input from text file Writes output
	 * to output.txt file
	 * @param args
	 * @throws IOException
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws IOException, ParseException,
			ClassNotFoundException {
		
		// the command read from the input file 
		String command;
		
		// to check if the command updated the objects 
		boolean isUpdated;
		
		// writer buffer creation used after update 
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));
		
		// the JEuroTournament Object	
		JEuroTournament jEuroTournament = null;
		
		// create Scanner for the text file named "input.txt" 
		Scanner input = new Scanner(new File("input.txt"));
		
		// if the file has next - not empty 
		if (input.hasNext()) {
			jEuroTournament = new JEuroTournament();			
		}

		System.out.println("[ Start JEuroTournament System ...]");

		/*
		 *  read the commands. loop while input file [input.hasnext()]
		 * and the jEuroTournament is not null 
		 */
		while (input.hasNext() && jEuroTournament != null) {
			
			/* 
			 * read the command, must be first at line because command value 
			 * determine which method will be activated in JEuroTournament object.
			 */
			command = input.next();
			isUpdated = false;
			
			// ================				Command			================
			if (command.equals("addTeam")) {

				//  create and add new add team to jEuroTournament
				String tId 			=	input.next();
				String tName		=	input.next();
				String represents	=	input.next();
				int fansCount		=	input.nextInt();
				
				isUpdated = jEuroTournament.addTeam(tId, tName,  represents, fansCount);
				
				 
				System.out.println("addTeam returns:");
						
				if (isUpdated) { // if adding successfully, then true returned,
					// the following message is written to the output file
					 
							System.out.println("\tSuccessfully added team " + tId
									+ ","+represents+" to jEuroTournament");
				} else {
					 
							System.out.println("\tFailed to add new team " + tId
									+ " to jEuroTournament");
				}
			}
			// ================				Command			================
			else if (command.equals("addMatch")) {
				
				String matchID 	= input.next();
				Date date		= df.parse(input.next());
				String stadium	= input.next();
				int soldTickets = input.nextInt();
				
				isUpdated = jEuroTournament.addMatch(matchID, date, stadium,soldTickets);
				 
				System.out.println("addMatch returns:");
				if (isUpdated) { 
					 
							System.out.println("\tSuccessfully added match with identifier: "
									+ matchID +" "+date+" to jEuroTournament");
				} else {
					 
							System.out.println("\tFailed to add new match "
									+ matchID +" to jEuroTournament");
				}

			}
			// ================				Command			================
			else if (command.equals("addPlayer")) { 

				String pId 		= input.next();
				String pFullName= input.next()+" "+input.next();
				short age 		= input.nextShort();
				String nation 	= input.next();
				int pNum		= input.nextInt();
				Role skill1 	= Role.valueOf(input.next());
				Role skill2 	= Role.valueOf(input.next());
				int fansCount	= input.nextInt();
				
				Role[] skills = {skill1,skill2};
				if( pId !=null && pFullName!=null && nation!=null && skill1!=null
						&& skill2!=null){
					
					isUpdated = jEuroTournament.addPerson(new Player(pId, pFullName,  age
							,nation, pNum, skills, fansCount));
					
					 
						System.out.println("addPlayer returns:");
					if(isUpdated){
						 
						System.out.println("\tSuccessfully added Player "+
						pId +" , "+pNum+" to jEuroTournament");
					}else
						 
						System.out.println("\tFailed adding Player "+
						pId +" , "+pNum+" to jEuroTournament");
				}
				else
					 
					System.out.println("addPlayer returns: invalid input pId , "+pId);
			}
			// ================				Command			================
			else if (command.equals("addPlayerToTeam")) {
				 
				String pId	= input.next();
				int pNum	= input.nextInt();
				String tId	= input.next();
				
				if(pId!=null && tId!=null){
					isUpdated = jEuroTournament.addPlayerToTeam(pId, pNum, tId);
					 
					System.out.println("addPlayerToTeam returns:");
					if(isUpdated){
						 
							System.out.println("\tSuccessfully added Player "+
									pId +" , "+pNum+" to Team "+tId);
					}else
						 
							System.out.println("\tFailed adding Player "+
									pId +" , "+pNum+" to Team "+tId);
				}
				else
					 
					System.out.println("addPlayerToTeam returns: invalid input");
	
			}
			// ================				Command			================
						else if (command.equals("removePlayerFromTeam")) {
							 
							String pId	= input.next();
							int pNum	= input.nextInt();
							String tId	= input.next();
							
							if(pId!=null && tId!=null){
								isUpdated = jEuroTournament.removePlayerFromTeam( pId,  pNum, tId);
								System.out.println("removePlayerFromTeam returns:");
								if(isUpdated){
									 
										System.out.println("\tSuccessfully removed Player "+
												pId +" , "+pNum+" from Team "+tId);
								}else
									 
										System.out.println("\tFailed removing Player "+
												pId +" , "+pNum+" from Team "+tId);
							}
							else
								System.out.println("removePlayerFromTeam returns: invalid input");
				
						}
			// ================				Command			================
			else if (command.equals("addSponsor")) {
				
				String pId 		= input.next();
				String pFullName= input.next()+" "+input.next();
				String nation 	= input.next();
				short age 		= input.nextShort();
				String surname	= input.next();
				
				if(pId!=null && pFullName!=null && nation!=null && surname!=null ){
					Sponsor sponsor = new Sponsor(pId, pFullName, nation, age, surname); 
					isUpdated = jEuroTournament.addPerson(sponsor);
					 
					System.out.println("addSponsor returns:");
					if(isUpdated){
						 
							System.out.println("\tSuccessfully added sponsor "+
									pId +" , "+surname+" to system");
					}else
						 
							System.out.println("\tFailed adding Player "+
									pId +" , "+surname+" to system");
				}
				else
					 
					System.out.println("addSponsor returns: invalid input");
			}
			// ================				Command			================
			else if (command.equals("fundTeamBySponser")) {
				
				String pId		=	input.next();
				String surname	=	input.next();
				String tId		=	input.next();
				double amount	=	input.nextDouble();
				Date fDate		=	df.parse(input.next());
				
				if(pId!=null && surname!=null && tId!=null
						&& fDate!=null){
					isUpdated = jEuroTournament.fundTeamBySponser(pId, surname
							, tId, amount, fDate);
					 
						System.out.println("fundTeamBySponser returns:");
					
					if(isUpdated){
						 
							System.out.println("\tSuccessfully funded team " + tId
									+ " by sponsor "+pId+" , "+surname);
					}else
						 
							System.out.println("\tFailed funding team "+ tId
									+ " by sponsor "+pId+" , "+surname);
				}
				else
					 
						System.out.println("fundTeamBySponser returns: invalid input");
			}
			// ================				Command			================
			else if (command.equals("addMatchResult")) {
				
				String tIdA			= input.next(); 
				String tIdB 		= input.next();
				String mId			= input.next();
				Date date			= df.parse(input.next());
				int totalTime 		= input.nextInt();
				boolean penaltyEnd	= input.nextBoolean();
				
				
				if(tIdA!=null && tIdB!=null && mId!=null){
					isUpdated = jEuroTournament.addMatchResult( tIdA ,  tIdB , mId,  date
							, totalTime ,  penaltyEnd);
					 
					System.out.println("addMatchResult returns:");
				
					if(isUpdated){
						 
							System.out.println("\tSuccessfully added result of:\n\t" +
									"teamA: " + tIdA +" teamB: " + tIdB
								+ " in match "+mId);
					}else
						 
							System.out.println("\tFailed adding result of:\n\t" +
									"teamA: " + tIdA +" teamB: " + tIdB+ " in match "+mId);
				}else{
					 
					System.out.println("addMatchResult returns: invalid input" +
							", some Object does not exist");
				}
			}
			// ================				Command			================
						else if (command.equals("addGoalDetails")) {
							
							String tIdA			= input.next(); 
							String tIdB 		= input.next();
							String mId			= input.next();
							Date date			= df.parse(input.next());
							String pId			= input.next(); 
							int pNum 			= 	input.nextInt();
							short minute 		= (short) input.nextInt();
							boolean penalty		= input.nextBoolean();

							
							if(tIdA!=null && tIdB!=null && mId!=null){
								isUpdated = jEuroTournament.addGoalDetails( tIdA ,  tIdB , mId,  date, 
					                       pId,  pNum, minute,  penalty);
								System.out.println("addGoalDetails returns:");
							
								if(isUpdated){
									 
									System.out.println("\tSuccessfully adding goal of player pId: "+
											pId + " pNum: "+ pNum +"\n\t" +
											" in match "+mId + " between teamA: " + tIdA +" and teamB: " + tIdB);
								}else
									 
										System.out.println("\tFailed adding goal of player pId: "+
												pId + " pNum: "+ pNum +"\n\t" +
												" in match "+mId + " between teamA: " + tIdA +" and teamB: " + tIdB);
							}else{
								System.out.println("addGoalDetails returns: invalid input" +
										", some Object does not exist");
							}
						}
			
			// ================				Command			================
						else if (command.equals("addPenaltyCardDetails")) {
							
							String tIdA			= input.next(); 
							String tIdB 		= input.next();
							String mId			= input.next();
							Date date			= df.parse(input.next());
							String pId			= input.next(); 
							int pNum 			= 	input.nextInt();
							short minute 		= (short) input.nextInt();
							String color		= input.next(); 

							
							if(tIdA!=null && tIdB!=null && mId!=null){
								isUpdated = jEuroTournament.addPenaltyCardDetails( tIdA ,  tIdB , mId,  date, 
					                       pId,  pNum, minute,  color);
								System.out.println("addPenaltyCardDetails returns:");
							
								if(isUpdated){
									 
									System.out.println("\tSuccessfully adding penalty card of player pId: "+
											pId + " pNum: "+ pNum +"\n\t" +
											" in match "+mId + " between teamA: " + tIdA +" and teamB: " + tIdB);
								}else
									 
										System.out.println("\tFailed adding penalty card of player pId: "+
												pId + " pNum: "+ pNum +"\n\t" +
												" in match "+mId + " between teamA: " + tIdA +" and teamB: " + tIdB);
							}else{
								System.out.println("addPenaltyCardDetails returns: invalid input" +
										", some Object does not exist");
							}
						}
			// ================				Command			================
						else if (command.equals("removeMatchResult")) {
							
							String tIdA			= input.next(); 
							String tIdB 		= input.next();
							String mId			= input.next();
							Date date			= df.parse(input.next());

							
							
							if(tIdA!=null && tIdB!=null && mId!=null){
								isUpdated = jEuroTournament.removeMatchResult(tIdA ,  tIdB , mId, date);
								System.out.println("removeMatchResult returns:");
							
								if(isUpdated){
									 
										System.out.println("\tSuccessfully removed result of:\n\t" +
												"teamA: " + tIdA +" teamB: " + tIdB
											+ " in match "+mId);
								}else
									 
										System.out.println("\tFailed removig result of:\n\t" +
												"teamA: " + tIdA +" teamB: " + tIdB+ " in match "+mId);
							}else{
								System.out.println("addMatchResult returns: invalid input" +
										", some Object does not exist");
							}
						}
			// ================				Command			================
			else if (command.equals("addCoachToTeam")) {
				
				String tId		=	input.next();
				String pId		=	input.next();
				String pFullName=	input.next() +" "+input.next();
				short age		=	input.nextShort();
				String nation	=	input.next();
				String role		=	input.next();
                int seniority=  input.nextInt();
				
				
				if(tId!=null && pId!=null && pFullName!=null && age>0 && nation !=null
						&& role!=null && seniority>0){
					isUpdated = jEuroTournament.addCoachToTeam(tId, pId
							, pFullName, age, nation, role, seniority);
					 
						System.out.println("addCoachToTeam returns:");
					
					if(isUpdated){
						 
						System.out.println("\tSuccessfully added Coach :\n\t" +
								pId+" To team: " + tId +" and to the system ");
					}else
					 
						System.out.println("\tFailed adding Coach:\n\t" +
								pId+" To team: " + tId +" or to system");
				}else{
					 
					System.out.println("addCoachToTeam returns: invalid input" +
							", some Object does not exist");
				}
			}
			// ================				Command			================
						else if (command.equals("removeCoachFromTeam")) {
							
							String tId		=	input.next();
							String pId		=	input.next();
							
							
							
							if(tId!=null && pId!=null){
								isUpdated = jEuroTournament.removeCoachFromTeam( tId,pId);
								 
									System.out.println("removeCoachFromTeam returns:");
								
								if(isUpdated){
									 
									System.out.println("\tSuccessfully removed Coach :\n\t" +
											pId+" from team: " + tId);
								}else
								 
									System.out.println("\tFailed removing Coach:\n\t" +
											pId+" from team: " + tId);
							}else{
								System.out.println("removeCoachFromTeam returns: invalid input" +
										", some Object does not exist");
							}
						}
			// ================				Command			================
			else if (command.equals("discardRedCardsForPlayersOfTeam")) {
				
				String tId		= input.next();
				
				if(tId!=null){
					 
						System.out.println("discardRedCardsForPlayersOfTeam returns:");
					 Collection<PenaltyCard> cards = jEuroTournament.discardRedCardsForPlayersOfTeam(tId);
					
					 if(cards!=null && !cards.isEmpty()){
						  
							System.out.println("the following red card of team"+ tId +"were discarded:");
							int i = 0;
							for(PenaltyCard t:cards){
								if(t!=null)
									 
										System.out.println("\t"+ ++i +"-\t"+t);
							}
					}else
					 
						System.out.println("\tThere are no red cards to be discarded\n");
					
				}else{
					 
					System.out.println("discardRedCardsForPlayersOfTeam returns: invalid input");
				}
				
			}
			// ================				Command			================
			else if (command.equals("getAllCoachesThatWorksOnTwoRules")) {
									 
						System.out.println("getAllCoachesThatWorksOnTwoRules returns:");
					Collection<Coach> coaches=jEuroTournament.getAllCoachesThatWorksOnTwoRules();
					 if(coaches!=null && !coaches.isEmpty()){
						  
							System.out.println("the following Coaches were found:");
							int i = 0;
							for(Coach t:coaches){
								if(t!=null)
									 
										System.out.println("\t"+ ++i +"-\t"+t);
							}
					}else
					 
						System.out.println("\tThere are no coaches found!\n");
					
			}
			
			// ================				Command			================
						else if (command.equals("eliteTeamsArrangedByGoalsGap")) {
							
							int gap = input.nextInt();
							
							Team[] eT = jEuroTournament.eliteTeamsArrangedByGoalsGap(gap);
							System.out.println("eliteTeamsArrangedByGoalsGap "+gap+", returns:");
							
							if (eT!=null){
								System.out.println("the following elite Teams were found:");
								int i = 0;
								for(Team t:eT){
									if(t!=null)
										 
											System.out.println("\t"+ ++i +"-\t"+t);
								}
							}else
								System.out.println("No elite Teams were found");

						}
						// ================				Command			================
						else if (command.equals("getXDiffirentPlayerWithOneSkill")) {
							
							int playersPerTeam = input.nextInt();
							if(playersPerTeam>0){
							
								Player[] oneSP = jEuroTournament.getXDifferentPlayerWithOneSkill(playersPerTeam) ;
								 
									System.out.println("getXDifferentPlayerWithOneSkill, "+playersPerTeam
										+" player\\s per team, returns:");
							
								if (oneSP!=null){
									 
										System.out.println("the following one skill players were found:");
									int i = 0;
									for(Player p:oneSP){
										if(p!=null)
											 
												System.out.println("\t"+ ++i +"-\t"+p);
									}
								}
								else
									 
									System.out.println("No one skill players were found");
							}
							else
								System.out.println("getXDiffirentPlayerWithOneSkill returns:" +
										" invalid input"+playersPerTeam);
						}
						// ================				Command			================
						else if (command.equals("InterestFunding")) {
							
							int total = input.nextInt();
							if(total>0){
								
								Fund[] iFunds = jEuroTournament.InterestFunding(total) ;
								 
									System.out.println("InterestFunding, out of: "+total
										+" returns:");
							
								if (iFunds!=null){
									 
										System.out.println("the following Interest Funds were found:");
									int i = 0;
									for(Fund f:iFunds){
										if(f!=null)
											 
												System.out.println("\t"+ ++i +"-\t"+f);
									}
								}
								else
									 
									System.out.println("No Interest Funds were found");
							}
							else
								System.out.println("InterestFunding returns:" +
										" invalid input"+total);
						}
						// ================				Command			================
						else if (command.equals("getTeamsFundAndTotal")) {
							String[] teams = jEuroTournament.getTeamsFundAndTotal();
							
							 
							System.out.println("getTeamsFundAndTotal returns:");
							int i=0;
							for(String str:teams){
								System.out.println("\n\t"+ ++i +"-\t"+str);
							}
							
						}
						// ================				Command			================
						else if (command.equals("extendedMatcheswithoutPenaltyKicks")) {
							
							MatchResult[] mtR = jEuroTournament.extendedMatcheswithoutPenaltyKicks();
							
							if (mtR!=null){
								 
									System.out.println("the following extended matches were found:");
								int i = 0;
								for(MatchResult mR : mtR){
									if(mR!=null)
										 
											System.out.println("\t"+ ++i +"-\t"+mR);
								}
							}
							else
								System.out.println("No extended matches were found");
							
						}
						// ================				Command			================
						else if (command.equals("getAllTeamsInvolvedInExtendedMatches")) {
							Team[] exTm = jEuroTournament.getAllTeamsInvolvedInExtendedMatches();
							
							if (exTm!=null){
								 
									System.out.println("the following teams are involved in " +
											"extended matches were found:");
								int i = 0;
								for(Team tm : exTm){
									if(tm!=null)
										 
											System.out.println("\t"+ ++i +"-\t"+tm);
								}
							}
							else
								System.out.println("No teams and no extended matches were found");
						}
						// ================				Command			================
			// ================				Command			================
			else if (command.equals("printTeams")) {
				
			 //	jEuroTournament.printTeams();
			}
			// ================				Command			================
			else if (command.equals("extendedMatcheswithoutPenaltyKicks")) {
				
					
			}
			// ================				Command			================
			else if (command.equals("getAllTeamsInvolvedInExtendedMatches")) {
				
			}
			// ================				Command			================
			else if (command.equals("//")) {
			//	System.out.println("\n");
				input.nextLine();
				// ignores line starts by '//' the result is empty lines in the output
			}
			// ================				Command			================
			else if (command.equals("//=")) {
				System.out.println
				("=============================================================================================");
				input.nextLine();
			}
			// ================				Command			================
			else
				System.out.printf("Command %s does not exist\n", command);
		} //~ end of clause - while input has next

		System.out.println("[ End JEuroTournament System ]");
		
		input.close(); // close connection to input file
		System.out.println("[End Of process]");
		System.out.println("\n NOTICE:\n\t\"End of process\" " +
						"does NOT mean that all your methods are correct.\n" +
						"\n==>\t You NEED to check the \"output.txt\" file");
	}
}
