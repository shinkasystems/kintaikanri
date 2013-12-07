package net.shinkasystems.kintai.component;

import java.io.Serializable;

/**
 * 
 * @author Aogiri
 * 
 */
public class RoleOption implements Serializable {

	private final String id;

	private final String display;

	public RoleOption(String id, String display) {
		super();
		this.id = id;
		this.display = display;
	}

	public String getId() {
		return id;
	}

	public String getDisplay() {
		return display;
	}
}