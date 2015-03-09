package net.shinkasystems.kintai.component;

import java.io.Serializable;

/**
 * 
 * @author Aogiri
 * 
 */
public class ActivatedOption implements Serializable {

	private final boolean activated;

	private final String display;

	public ActivatedOption(boolean activated, String display) {
		super();
		this.activated = activated;
		this.display = display;
	}

	public boolean isActivated() {
		return activated;
	}

	public String getDisplay() {
		return display;
	}
}