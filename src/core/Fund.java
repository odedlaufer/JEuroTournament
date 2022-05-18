package core;

import java.util.Date;

import utils.Country;

/**
 * This class represents the Fund and its properties
 * @author JAVA SUMMER COURSE 2012
 *
 */
public class Fund {
	
	/**
	 * part of the key
	 * the date the team received the funding
	 */
	private Date fDate;
	
	/**
	 * the total amount of the funding 
	 */
	private double amount;
	
	/**
	 * the sponsor of this team funding
	 */
	private Sponsor sponsor;
	
	/**
	 * the team that received this fund
	 */
	private Team team;
	
	/**
	 * the funding constructor
	 * <p><b>notice:<b> the Funding details can not be changed after
	 * creating the Fund<p>
	 * @param amount
	 * @param fDate
	 * @param sponsor
	 * @param team
	 */
	public Fund(double amount, Date fDate, Sponsor sponsor, Team team) {
		super();
		this.amount = amount;
		this.fDate = fDate;
		this.sponsor = sponsor;
		this.team = team;
	}
	
	/**
	 * Gets the amount of this Fund
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * Gets the date of this Fund
	 * @return the fDate
	 */
	public Date getfDate() {
		return fDate;
	}
	
	/**
	 * get the nation of the funded sponsor
	 * @return country
	 */
	public Country getSponsorRepresent(){
		return (sponsor!=null)? sponsor.getNation():Country.UNKNOWN;
	}
	
	/**
	 *  Hashcode according to primary key fields 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fDate == null) ? 0 : fDate.hashCode());
		result = prime * result + ((sponsor == null) ? 0 : sponsor.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		return result;
	}
	
	/**
	 * Checks equality according to primary key fields 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fund other = (Fund) obj;
		if (fDate == null) {
			if (other.fDate != null)
				return false;
		} else if (!fDate.equals(other.fDate))
			return false;
		if (sponsor == null) {
			if (other.sponsor != null)
				return false;
		} else if (!sponsor.equals(other.sponsor))
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}

	/**
	 * Gets the string representation of this Person object
	 */
	@Override
	public String toString() {
		return "Fund: amount= " + amount + ", fDate= " + fDate + ", Sponser= "
				+ sponsor.getKey() + ", Team= " + team.getKey();
	}
}
