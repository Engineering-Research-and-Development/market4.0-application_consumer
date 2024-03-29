package it.eng.idsa.model.fiware.common;


/**
 * The TimeInstant class supports description of FIWARE Data Models objects
 * 
 * 
 * @author  Gabriele De Luca, Milan Karajovic
 */
public class TimeInstant {
	private String type;
	private String value;
	
	public TimeInstant() {}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
