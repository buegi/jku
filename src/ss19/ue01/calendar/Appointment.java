package ss19.ue01.calendar;

import java.time.*;

public class Appointment {

	static private int idCounter = 0;
	private final int id;
	private final String description;
	private final LocalDate date;
	private final LocalTime time;
	private final Duration duration;

	public Appointment(String description, LocalDate date, LocalTime time, Duration duration) {
		idCounter++;
		id = idCounter;
		this.description = description;
		this.date = date;
		this.time = time;
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "[" + id + "]" + " " + description + ": am " + date + ", von " + time + " bis "
				+ time.plusMinutes(duration.toMinutes());

	}

	int getId() {
		return id;
	}

	String getDescription() {
		return description;
	}

	LocalDate getDate() {
		return date;
	}

	LocalTime getTime() {
		return time;
	}

	Duration getDuration() {
		return duration;
	}

	LocalDateTime getStart() {
		return LocalDateTime.of(date, time);
	}

	LocalDateTime getEnd() {
		return LocalDateTime.of(date, time).plus(duration);

	}

}