package be.pxl.paj.olympicgames.domain;

import be.pxl.paj.olympicgames.exception.BusinessException;
import jdk.jfr.Enabled;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Race {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime dateTime;
	private Discipline discipline;
	@OneToMany
	private List<Score> scores = new ArrayList<>();

	public Race() {
		// JPA only
	}

	public Race(LocalDateTime dateTime, Discipline discipline) {
		this.dateTime = dateTime;
		this.discipline = discipline;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public Long getId() {
		return id;
	}

	public void addParticipant(Athlete athlete) {
		if (getScore(athlete).isEmpty()) {
			if (athlete.getDiscipline() != discipline) {
				throw new BusinessException("Athlete [" + athlete.getId() + " ] is not allowed to participate in race [" + id + "]");
			}
			scores.add(new Score(athlete, this));
		}
	}

	public void removeParticipant(Athlete athlete) {
		Optional<Score> toRemove = getScore(athlete);
		toRemove.ifPresent(score -> scores.remove(score));
	}

	public Optional<Score> getScore(Athlete athlete) {
		return scores.stream().filter(s -> s.getAthlete().equals(athlete)).findAny();
	}

	public List<Score> getScores() {
		return scores;
	}
}
