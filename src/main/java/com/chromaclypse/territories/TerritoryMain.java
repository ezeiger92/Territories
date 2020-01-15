package com.chromaclypse.territories;

import org.bukkit.plugin.java.JavaPlugin;

import com.chromaclypse.territories.command.Commands;

public class TerritoryMain extends JavaPlugin {
	private Territory territory;
	
	@Override
	public void onEnable() {
		territory = new Territory(this);
		Commands commandsMapping = new Commands(territory);
		
		commandsMapping.initialize();
	}
	
	@Override
	public void onDisable() {
		territory = null;
	}
}
