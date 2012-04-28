package info.bytecraft.basiccommands.listeners;

import java.util.HashMap;

import info.bytecraft.basiccommands.Bans;
import info.bytecraft.basiccommands.BasicCommands;
import info.bytecraft.basiccommands.Players;

import org.bukkit.ChatColor;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class PlayerJoin implements Listener{
	private static BasicCommands plugin;
	public PlayerJoin(BasicCommands instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onJoin(PlayerLoginEvent event){
		Player player = event.getPlayer();
		Bans bans = plugin.getDatabase().find(Bans.class).where().ieq("playerName", player.getName()).findUnique();
		if(bans != null && bans.isBanned()){
			if(bans.getReason() == null){
			event.disallow(Result.KICK_BANNED, ChatColor.RED + "You are banned from this server! good day!");
			}else{
				event.disallow(Result.KICK_BANNED, ChatColor.RED + "You have been banned for: " + ChatColor.WHITE + bans.getReason());
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent event){
		if(event.getEntity() instanceof Player){
			Player player = (Player)event.getEntity();
			if(player.hasPermission("bytecraft.god")){
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onHunger(FoodLevelChangeEvent event){
		if(event.getEntity() instanceof Player){
			Player player = (Player)event.getEntity();
			if(event.getFoodLevel() < player.getFoodLevel()){
            if(player.hasPermission("bytecraft.god")){
				event.setCancelled(true);
                }
			}
		}
	}
	
	public void onSignChange(SignChangeEvent event){
	BlockState state = event.getBlock().getState();
	if(state instanceof Sign) {
	Sign sign = (Sign) state;
	String line0 = event.getLine(0);
	String nline0 = line0.replaceAll("(&([a-fk0-9]))", "\u00A7$2");
	String line1 = event.getLine(1);
	String nline1 = line1.replaceAll("(&([a-fk0-9]))", "\u00A7$2");
	String line2 = event.getLine(2);
	String nline2 = line2.replaceAll("(&([a-fk0-9]))", "\u00A7$2");
	String line3 = event.getLine(3);
	String nline3 = line3.replaceAll("(&([a-fk0-9]))", "\u00A7$2");
	event.setLine(0, nline0);
	event.setLine(1, nline1);
	event.setLine(2, nline2);
	event.setLine(3, nline3);
	sign.update(true);
		}
	}
	
	HashMap<Item, Player> hashmap = new HashMap<Item, Player>();
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event){
		if(event.isCancelled())return;
		Player player = event.getPlayer();
		Item item = event.getItemDrop();
		hashmap.put(item, player);
	}
		
	@EventHandler
	public void onPickup(PlayerPickupItemEvent event){
		if(event.isCancelled())return;
		Player player = event.getPlayer();
		Item item = event.getItem();
		int value = event.getItem().getItemStack().getAmount();
		if(hashmap.containsKey(item)){
			if(event.getPlayer() != hashmap.get(item)){
			player.sendMessage(ChatColor.YELLOW + "You got " + value + " " + ChatColor.AQUA +item.getItemStack().getType().name().toLowerCase().replace("_", " ") + ChatColor.YELLOW + " from " + hashmap.get(event.getItem()).getPlayer().getDisplayName());
			hashmap.get(item).sendMessage(ChatColor.DARK_GREEN + "You gave " + value + " " + ChatColor.AQUA +  item.getItemStack().getType().name().toLowerCase().replace("_", " ") + ChatColor.DARK_GREEN + " to " + player.getDisplayName());
			hashmap	.remove(item);
			}
			
	}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		Players info = plugin.getDatabase().find(Players.class).where().ieq("playerName", player.getName()).findUnique();
		if(info == null){
			info = new Players();
			info.setPlayer(player);
			plugin.getDatabase().save(info);
		}
	}
}
