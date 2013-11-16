package net.shinkasystems.kintai.component;

import java.io.Serializable;

public class Authority implements Serializable {
	
	private final int id;
	
	private final String name;
	
	private final String displayName;

	public Authority(int id, String name, String displayName) {
		super();
		this.id = id;
		this.name = name;
		this.displayName = displayName;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDisplayName() {
		return displayName;
	}

}
