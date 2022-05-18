package core;

import utils.Constants;

/**
 * This class represents the Sponsor and its properties
 * @author JAVA SUMMER COURSE 2012
 *
 */
public class Sponsor extends Person {

	/**
	 * the sponsor surname, used as part of the key
	 */
	private String surname;
	
	/**
	 * teams funded by this sponsor
	 * sponsor can fund up to three different teams
	 * @see Constants#sponseredTeams 
	 */
	private Fund[] teamsFunded;
	
	/**
	 * Partial constructor
	 * @param pId
	 * @param surname
	 */
	public Sponsor(String pId, String surname){
		super(pId);
		setSurname(surname);
	}
	
	/**
	 * Sponsor Full constructor
	 * @param pId
	 * @param surname
	 */
	public Sponsor(String pId,String pFullName,String nation
			, short age, String surname) {
		super(pId,pFullName,age,nation);
		setSurname(surname);
		teamsFunded = new Fund[Constants.sponseredTeams];
	}
	
	/**
	 * The method adds a funded team to this sponsor
	 * IFF the team does not exist in the array
	 * @param fund
	 * @return true if managed to add
	 */
	public boolean addFundedTeam(Fund fund){
		int index = -1;
		for(int i=teamsFunded.length-1 ; i>=0 ; i--){
			if(teamsFunded[i]!=null ){
				if(teamsFunded[i].equals(fund))
					return false;
			}else
				index =i;
		}
		if(index!=-1){
			teamsFunded[index]=fund;
			return true;
		}
		return false;
	}
	
	/**
	 * The method removes the funded team from the array of this sponsor
	 * IFF it exists
	 * @param fund
	 * @return true if successfully removed
	 */
	public boolean removeFundedTeam(Fund fund){
		
		for(int i=0; i <teamsFunded.length;i++)
			if(teamsFunded[i]!=null && teamsFunded[i].equals(fund)){
				teamsFunded[i]=null;
				return true;
			}
		return false;
	}
	
	/**
	 * get the sponsor full key 
	 */
	@Override
	public String getKey() {
		return String.format("%s %s",super.getKey(),getSurname());
	}

	@Override
	/**
	 * gets popularity of this Sponsor as follows:
	 * popularity= total amount of money funded by this sponsor / number of funded teams
	 */
	public double getPopularity() {
		double total=0;
		int count =0;
		for(Fund f:teamsFunded){
			if(f!=null){
				total+=f.getAmount();
				count++;
			}
		}
		if (count>0)
			return Math.round((double)total/count); 
		return 0;
	}
	
	/**
	 * Sets the surname of this Sponsor
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	/**
	 * Gets the surname of this Sponsor
	 * @return surname
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 *  Hashcode according to primary key fields 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}
	
	/**
	 * Checks equality according according to primary key fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sponsor other = (Sponsor) obj;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	/**
	 * Gets the string representation of this Sponsor object
	 */
	public String toString() {
		return "Sponsor: "+super.toString()+", surname=" + getSurname() + ", Popularity="+String.format("%.3f",getPopularity());
	}
	
}
