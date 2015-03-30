package entity;

import java.sql.Date;
import java.util.List;

public class Actor {
	
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private Date dateOfBirth;
	
	private List<Cast> casts;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<Cast> getCasts() {
		return casts;
	}

	public void setCasts(List<Cast> casts) {
		this.casts = casts;
	}


	public Actor(int id, String firstName, String lastName, Date dateOfBirth,
			List<Cast> casts) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.casts = casts;
	}

	public Actor() {
		super();
	}
	
	

}
