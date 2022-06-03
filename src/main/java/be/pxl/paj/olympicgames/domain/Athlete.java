package be.pxl.paj.olympicgames.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
public class Athlete {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String lastName;
	private String country;
	private LocalDate dateOfBirth;
	private Discipline discipline;

	public Athlete(String firstName, String lastName, String country, LocalDate dateOfBirth, Discipline discipline) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.dateOfBirth = dateOfBirth;
		this.discipline = discipline;
	}

	public Athlete() {
		// JPA only
	}

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCountry() {
		return country;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public int getAge() {
		return (int) ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now());
	}

	public String getName() {
		return firstName.charAt(0) + ". " + lastName;
	}
}
