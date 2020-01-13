package com.chromaclypse.territories;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.plugin.Plugin;

import com.chromaclypse.territories.TerritoryStorage.Claim;

public class Territory {
	private final Plugin handle;
	private final TerritoryStorage storage = new TerritoryStorage();
	
	public Territory(Plugin handle) {
		this.handle = handle;
		
		storage.init(handle);
	}
	
	public Plugin getPlugin() {
		return handle;
		
	}
	
	public List<Claim> getClaims(){
		return Collections.unmodifiableList(storage.claims);
	}
	
	public Optional<Claim> findClaim(String name){
		return storage.claims.stream().filter(c -> c.name.equalsIgnoreCase(name)).findFirst();
	}
	
	public Optional<Claim> findClaim(UUID owner){
		return storage.claims.stream().filter(c -> c.owner_uuid.equalsIgnoreCase(owner.toString())).findFirst();
	}
	
	public boolean registerClaim(Claim claim) {
		storage.claims.add(claim);
		save();
		return true;
	}
	
	public void save() {
		storage.save(handle);
	}
}
