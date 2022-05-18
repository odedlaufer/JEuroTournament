package core;

import java.util.Arrays;

import utils.Constants;

import utils.Role;

public class Player extends Person implements Comparable<Player>{

	/**
	 * player number which is printed on the T-shirt
	 * <b>also used as part of the key<b>
	 */
	private int pNum;
	/**
	 * skills that the player has, the first is the main one
	 * all the others are secondary
	 * @see Constants#PlayerSkils for maximum skills number
	 */
	private Role[] skills;
	/**
	 * the number of the player fans
	 */
	private int fansCount;
	
	/**
	 * the team which this player plays for
	 */
	private Team team;
	
	/**
	 * partial constructor
	 * for searching issues
	 * @param pId
	 * @param pNum
	 */
	public Player(String pId, int pNum) {
		super(pId);
		setpNum (pNum);
	}
	
	/**
	 * full constructor
	 * @param pId
	 * @param pNum
	 * @param skills
	 * @param fansCount
	 */
	public Player(String pId, String pFullName, short age
			,String nation,int pNum, Role[] skills, int fansCount) {
		super(pId,pFullName,age,nation);
		this.skills = new Role[Constants.PlayerSkills];
		setpNum (pNum);
		setSkills(skills);
		setFansCount(fansCount);
	}

	/**
	 * the method set the value of the team IFF the player 
	 * does not play for any other team
	 * @param team
	 * @return true if added successfully
	 */
	public boolean addTeam(Team team){
		if(this.team ==null ){
			this.team=team;
			return true;
		}
		return false;
	}
	/**
	 * set the team value to null IFF it is not null, 
	 * it means the player is playing for another team.
	 * @return true if remove from the team was changed to null
	 */
	public boolean removeTeam(){
		if(this.team!=null){
			this.team=null;
			return true;
		}
		return false;
	}
	
	/**
	 * the method return the identifier of this object as String
	 * which depends on the person ID and the Player number  
	 */
	@Override
	public String getKey() {
		return getPId()+" "+getpNum();
	}
	

	@Override
	public double getPopularity() {
		double pop=0;
		int toCards = team.getRedCards()+team.getYellowCards();
		if(team.getFansCount()!=0 && toCards!=0){
			
			pop = ((double)getFansCount()/team.getFansCount())
					+((double)team.getGoalsScored()-team.getGoalsAgainst())/toCards;
		}
	
		return pop;
	}

	/**
	 * the method set the player number
	 * negative numbers are not allowed
	 * @param pNum
	 */
	public void setpNum(int pNum) {
		if(pNum>=0)
			this.pNum = pNum;
	}
	/**
	 * @return player number as integer
	 */
	public int getpNum() {
		return pNum;
	}
	/**
	 * the method set the values of this player skills according
	 * to the values in the array received (skills)
	 * <p><b> notice:<b><p>
	 * <p>- no duplicate skills allowed<p>
	 * <p>- the number of skills might change<p>
	 * @see Constants#PlayerSkils 
	 * @param skills
	 */
	public void setSkills(Role[] skills) {
		for(Role r:skills){
			addSkill(r);
		}
		this.skills = skills;
	}
	
	/**
	 * the method ad a skill to the first empty place.
	 * @param skill
	 * @return true if successfully added
	 */
	public boolean addSkill(Role skill) {
		if(!hasSkilll(skill)){
			for(int i=0;i<skills.length;i++){
				if(skills[i]==null){
					skills[i]=skill;
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * the method get a skill and remove the skill
	 * from this player skills if it exist
	 * @param skill
	 * @return true if successfully removed
	 */
	public boolean removeSkill(Role skill) {
		for(int i=0; i<Constants.PlayerSkills;i++){
			if(skills[i]!=null && skills[i].equals(skill)){
				skills[i]=null;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * the method return true if the role(skill)
	 * exist in this player skills
	 * @param role
	 * @return true if found
	 */
	private boolean hasSkilll(Role role) {
		for(Role r:this.skills){
			if(r!=null && r.equals(role))
				return true;
		}
		return false;
	}
	
	/**
	 * @return the player skills array
	 */
	public Role[] getSkills() {
		return skills;
	}
	
	/**
	 * the fans count should not be a negative number
	 * @param fansCount
	 */
	public void setFansCount(int fansCount) {
		if(fansCount>=0)
			this.fansCount = fansCount;
		else
			this.fansCount = 0;
	}
	
	/**
	 * @return the fans number of this player
	 */
	public int getFansCount() {
		return fansCount;
	}

	public Team getTeam() {
		return team;
	}
	
	public boolean isOneSkillPlayer() {
		for(Role r:getSkills()){
			if(r.equals(Role.UN))
				return true;
		}
		return false;
	}
	


	@Override
	public int compareTo(Player o) {
		int comp=getPId().compareTo(o.getPId());
		if(comp!=0)
			return comp;
		return getpNum()-o.getpNum();
	}
	
	/**
	 *  Hashcode according to primary key fields 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + pNum;
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
		Player other = (Player) obj;
		if (pNum != other.pNum)
			return false;
		return true;
	}

	@Override
	/**
	 * Gets the string representation of this Sponsor object
	 */
	public String toString() {
		return "Player: "+super.toString()+" pNum= "+getpNum() +" skills= " + Arrays.toString(skills)
				+ ", fansCount= " + getFansCount() + " ,Popularity= "+ String.format("%.3f",getPopularity())
				+ ", team= " + ((getTeam()==null)? "NOT EXISTS":getTeam().getKey());
	}

	

	
}
