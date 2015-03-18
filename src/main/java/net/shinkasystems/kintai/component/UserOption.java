package net.shinkasystems.kintai.component;

import java.io.Serializable;

/**
 * 
 * @author Aogiri
 *
 */
public class UserOption implements Serializable {

	private final int id;
	
	private final String display;

	public UserOption(int id, String display) {
		super();
		this.id = id;
		this.display = display;
	}

	public int getId() {
		return id;
	}

	public String getDisplay() {
		return display;
	}
}