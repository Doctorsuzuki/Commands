package info.bytecraft.basiccommands.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Respawn implements Listener {

	@EventHandler
	public void onSpawn(PlayerRespawnEvent event){
		event.setRespawnLocation(Bukkit.getWorld("world").getSpawnLocation());
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event){
		event.setDeathMessage(null);
	}
}
