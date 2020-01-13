package com.chromaclypse.territories;

import java.util.List;

import org.bukkit.Location;

import com.chromaclypse.api.Defaults;
import com.chromaclypse.api.config.ConfigObject;
import com.chromaclypse.api.config.Section;

@Section(path="territories.yml")
public class TerritoryStorage extends ConfigObject {
	
	public List<Claim> claims = Defaults.emptyList();
	public static class Claim {
		public Location spawnpoint;
		public String name;
		public String owner_uuid;
	}
}
