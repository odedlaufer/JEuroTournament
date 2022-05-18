	package core;

import java.util.Date;

import utils.Effectiveness;

import utils.Stadium;

/**
 * This class represents the Match and its properties
 *
 */
public class Match implements Effectiveness {
	/**
	 * game identification number , used as key
	 */
	private String mId; // part of key
	/**
	 * the date when the game took place
	 */
	private Date mDate; // part of key
	/**
	 * the place where the game took place;
	 */
	private Stadium tookPlace;
	
	/**
	 * total Tickets sold 
	 */
	private long totalTickets;
	
	/**
	 * as the two teams participant in ONE match
	 */
	private MatchResult result;
	
	
	/**
	 * partial constructor for searching issues
	 * @param mId
	 */
	public Match(String mId, Date date){
		super();
		setmId(mId);
		setmDate(date);
	}
	
	/**
	 * full constructor
	 * @param matchID
	 * @param date
	 * @param stadium
	 * @param totalTickets
	 */
	public Match(String matchID, Date date, String stadium, long totalTickets){
		super();
		setmId(matchID);
		setmDate(date);
		setTookPlace(stadium);
		setTotalTickets(totalTickets);
	
	}
	/**
	 * add a match result IFF the current result is null
	 * to do changes in result it not allowed 
	 * @param result
	 * @return true if added match result to this match
	 */
	public boolean addMatchResult(MatchResult result){
		if(this.result==null){
			this.result=result;
			return true;
		}
		return false;
	}
	
	/**
	 * remove a result of this match only if it exist, not null 
	 * @return true if managed to remove the result from this team
	 */
	public boolean removeMatchResult(){
		if(this.result!=null){
			this.result=null;
			return true;
		}
		return false;
	}

	/**
	 * @return this object key
	 */
	public String getKey(){
		return String.format("%s %s", this.mId, this.mDate);
	}
	
	/**
	 * @return the mId
	 */
	public String getmId() {
		return mId;
	}

	/**
	 * @param mId the mId to set
	 */
	public void setmId(String mId) {
		this.mId = mId;
	}

	/**
	 * gets the date to set
	 * @return the Date
	 */
	public Date getmDate() {
		return mDate;
	}

	/**
	 * sets  the date to set
	 * @param mDate 
	 */
	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}

	
	/**
	 * gets the tookPlace
	 * @return the tookPlace
	 */
	public Stadium getTookPlace() {
		return tookPlace;
	}

	/**
	 * set the tookPlace 
	 * @param tookPlace 
	 */
	public void setTookPlace(String tookPlace) {
		this.tookPlace = Stadium.valueOf(tookPlace);
	}

	
	/**
	 * gets the total tickets
	 * @return the totalTickets
	 */
	public long getTotalTickets() {
		return totalTickets;
	}
	
	/**
	 * @param totalTickets the totalTickets to set
	 */
	public void setTotalTickets(long totalTickets) {
		this.totalTickets = totalTickets;
	}
	
	/**
	 * @param tookPlace the tookPlace to set
	 */
	public void setTookPlace(Stadium tookPlace) {
		this.tookPlace = tookPlace;
	}
	
	
	
	/**
	 * @return the result of this match
	 */
	public MatchResult getResult() {
		return result;
	}
	
	@Override
	/**
	 * Checks equality according to primary key fields 
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Match other = (Match) obj;
		if (mDate == null) {
			if (other.mDate != null)
				return false;
		} else if (!mDate.equals(other.mDate))
			return false;
		if (mId == null) {
			if (other.mId != null)
				return false;
		} else if (!mId.equals(other.mId))
			return false;
		return true;
	}

	@Override
	/**
	 * Gets the string representation of this Match object
	 */
	public String toString() {
		return "Match: mId=" + getmId() + ", mDate=" + getmDate()
				+ ", TookPlace=" + getTookPlace() + ", TotalTickets="
				+ getTotalTickets() + ", EffectivenessLevel="
				+ String.format("%.3f",getEffectivenessLevel());
	}

	@Override
	public double getEffectivenessLevel() {
		
		double totalCorners = (result.getACorners() - result.getBCorners()) * 0.2;
		double totalYellowCards = (result.gettAYellowCards() + result.gettBYellowCards()) * 0.2;
		double totalGoals = (result.gettAgoals() + result.gettBgoals()) * 0.3;
		double totalAttempts = (result.getAattemptsOnTarget() + result.getBattemptsOnTarget()) * 0.1;
		double totalRedCards = (result.gettARedcards() + result.gettBRedcards()) * 0.2;
		
		return totalCorners - totalYellowCards + totalGoals + totalAttempts - totalRedCards;
	}

}
