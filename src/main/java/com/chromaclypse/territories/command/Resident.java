package com.chromaclypse.territories.command;

import org.bukkit.entity.Player;

import com.chromaclypse.api.command.Context;
import com.chromaclypse.territories.Territory;
import com.chromaclypse.territories.TerritoryStorage.Claim;

public class Resident {
	private Territory handle;
	
	public Resident(Territory handle) {
		this.handle = handle;
	}

	public boolean spawn(Context context) {
		Player player = context.Player();
		
		Claim c = handle.findClaim(player.getUniqueId()).orElseThrow(
				() -> new IllegalStateException());
		
		player.teleport(c.spawnpoint);
		player.sendMessage("Teleported to " + c.name);
		
		return true;
	}

	public boolean spawnOther(Context context) {

		Player player = context.Player();
		
		Claim c = handle.findClaim(context.GetArg(1)).orElseThrow(
				() -> new IllegalStateException());
		
		player.teleport(c.spawnpoint);
		player.sendMessage("Teleported to " + c.name);
		
		return true;
	}
}
