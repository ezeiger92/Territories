package com.chromaclypse.territories.command;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import com.chromaclypse.api.command.CommandBase;
import com.chromaclypse.api.command.Context;
import com.chromaclypse.territories.Territory;

public class Commands {
	private final Territory handle;
	private final Manager managerCmds;
	private final Resident residentCmds;
	
	public Commands(Territory handle) {
		this.handle = handle;
		managerCmds = new Manager(handle);
		residentCmds = new Resident(handle);
	}
	
	private List<String> allTerritories(Context context){
		return handle.getClaims().stream().map(c -> c.name).collect(Collectors.toList());
	}
	
	public void initialize() {
		JavaPlugin plugin = (JavaPlugin) handle.getPlugin();
		
		TabExecutor command = new CommandBase()
				.with().arg("spawn").calls(residentCmds::spawn)
				.with().arg("spawn").option(this::allTerritories).calls(residentCmds::spawnOther)
				.with().arg("create").requires("territories.manage").calls(managerCmds::create)
				.with().arg("move").requires("territories.manage").calls(managerCmds::move)
				.with().arg("version").calls(CommandBase::pluginVersion)
				.getCommand();

		plugin.getCommand("territory").setExecutor(command);
		plugin.getCommand("territory").setTabCompleter(command);
	}
}
