
package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class User {

	private String host;
	private LocalDate date;
	private LocalTime hour;
	private int numberGuess;
	private int numberAttemps;

	public User(String host, LocalDate fecha, LocalTime hora, int numberGuess, int num) {
		super();
		this.host = host;
		this.date = fecha;
		this.hour = hora;
		this.numberGuess = numberGuess;
		this.numberAttemps = num;
	}

	public void setNumberAttemps(int numberAttemps) {
		this.numberAttemps = numberAttemps;
	}

	public String getHost() {
		return host;
	}

	public LocalDate getFecha() {
		return date;
	}

	public LocalTime getHora() {
		return hour;
	}

	public int getNumberGuess() {
		return numberGuess;
	}

	public int getNumberAttemps() {
		return numberAttemps;
	}	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[ Host: " + host + ", Fecha: " + date + 
				", Hora: " + hour +  ", Numero: " + numberGuess 
				+ ", Intentos: " + numberAttemps ;
	}
}