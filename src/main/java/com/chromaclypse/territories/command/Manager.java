package com.chromaclypse.territories.command;

import org.bukkit.entity.Player;

import com.chromaclypse.api.command.Context;
import com.chromaclypse.territories.Territory;
import com.chromaclypse.territories.TerritoryStorage.Claim;

public class Manager {
	private Territory handle;
	
	public Manager(Territory handle) {
		this.handle = handle;
	}
	
	private void checkPermission(Player player, String operation) {
		String prefix = "territory.world.";
		String world = player.getWorld().getName().toLowerCase();
		
		if(!player.hasPermission(prefix + "*") &&
				!player.hasPermission(prefix + world))
			throw new IllegalStateException("You don't have permission to own land in \"" + world + "\"");
	}

	public boolean create(Context context) {
		Player player = context.Player();
		String name = context.GetArg(1);
		
		checkPermission(player, "create");
		
		if(handle.findClaim(name).isPresent()) {
			throw new IllegalArgumentException("That name is already used!");
		}
		
		Claim c = new Claim();
		c.name = name;
		c.owner_uuid = player.getUniqueId().toString();
		c.spawnpoint = player.getLocation();
		
		handle.registerClaim(c);
		
		player.sendMessage("Territory claimed");
		
		return true;
	}

	public boolean move(Context context) {
		Player player = context.Player();
		
		checkPermission(player, "move");
		
		Claim c = handle.findClaim(player.getUniqueId()).orElseThrow(
				() -> new IllegalArgumentException("You are not part of a territory!"));
		
		c.spawnpoint = player.getLocation();
		handle.save();
		
		player.sendMessage("Territory spawn updated");
		
		return true;
	}
}
