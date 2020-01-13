package com.chromaclypse.territories;

import org.bukkit.plugin.java.JavaPlugin;

public class TerritoryMain extends JavaPlugin {
	private Territory territory;
	@Override
	public void onEnable() {
		territory = new Territory(this);
	}
	
	@Override
	public void onDisable() {
		territory = null;
	}
}
