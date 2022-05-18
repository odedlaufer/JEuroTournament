package core;

import utils.Country;

/**
 * This class represents the properties of a Person Object
 * @author JAVA SUMMER COURSE 2012
 */
public abstract class Person{
	
	/**
	 * person identification number
	 * also used as a primary key
	 */
	private String pId;
	/**
	 * person full name - first name and surname
	 */
	private String fullName;
	/**
	 * person age, as the fact that we are taking 
	 * about a specific period in time  
	 */
	private short age;
	
	/**
	 * the person nationality, and any other
	 * object that extends it
	 */
	private Country nation;
	
	/**
	 * partial constructor, for searching issues
	 * @param pId
	 */
	public Person(String pId){
		super();
		setPId(pId);
	}
	
	/**
	 * person full constructor
	 * @param pId
	 * @param fullName
	 * @param age
	 */
	public Person(String pId, String fullName, short age
			,String nation){
		super();
		setPId(pId);
		setFullName(fullName);
		setAge(age);
		setNation(nation);
	}
	
	
	/**
	 * @return the primary key of this Person
	 */
 	public String getKey(){
 		return getPId();
 	}
	
	/**
	 * the method calculate the popularity of the object 
	 * that implementation depends on the object and the requirements
	 * @return double that is the value of popularity
	 */
	public abstract double getPopularity();

	/**
	 * @return  the person identification number
	 */
	public String getPId() {
		return pId;
	}
	/**
	 * get the person ID
	 * @param pId
	 */
	public void setPId(String pId) {
		this.pId = pId;
	}
	/**
	 * @return the person full name.
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * set the person full name
	 * @param fullName
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the person age
	 */
	public short getAge() {
		return age;
	}
	
	/**
	 * set the age of person
	 * <P><b>no negative numbers allowed<b><p> 
	 * @param age
	 */
	public void setAge(short age) {
		if (age>18)
			this.age = age;
	}
	
	/**
	 * get person nationality
	 * @see Country#values() 
	 * @return Country of person
	 */
	public Country getNation() {
		return nation;
	}
	/**
	 * set person nationality
	 * @see Country#values() 
	 */
	public void setNation(String nation) {
		
		try {
			this.nation = Country.valueOf(nation);
			
		} catch (Exception e) {
			
			this.nation = Country.UNKNOWN;
		}
		
	}

	@Override
	/**
	 * hash code according to id (primary key)
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pId == null) ? 0 : pId.hashCode());
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
		Person other = (Person) obj;
		if (pId == null) {
			if (other.pId != null)
				return false;
		} else if (!pId.equals(other.pId))
			return false;
		return true;
	}

	@Override
	/**
	 * Gets the string representation of this Person object
	 */
	public String toString() {
		return " pId=" + getPId() + ", fullName=" + getFullName() + ", age="
				+ age + ", nation=" + getNation();
	}
	
	
}
