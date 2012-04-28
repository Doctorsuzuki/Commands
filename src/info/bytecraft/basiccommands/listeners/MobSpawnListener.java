package info.bytecraft.basiccommands.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class MobSpawnListener implements Listener {
	
	@EventHandler
	public void onBreak(EntityChangeBlockEvent event){
		if(event.getEntityType() == EntityType.ENDER_DRAGON || event.getEntityType() == EntityType.CREEPER){
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onSpawn(CreatureSpawnEvent event){
		if(event.getEntityType() == EntityType.ENDER_DRAGON){
			if(event.getLocation().getWorld().getName().equalsIgnoreCase("world")){
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onExplode(EntityExplodeEvent event){
		if(event.getEntityType() == EntityType.CREEPER || event.getEntityType() == EntityType.PRIMED_TNT){
			if(event.getLocation().getWorld().getName().equalsIgnoreCase("world")){
			event.setCancelled(true);
			}
		}
	}
}
