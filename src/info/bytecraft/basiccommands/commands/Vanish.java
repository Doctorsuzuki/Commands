package info.bytecraft.basiccommands.commands;

import info.bytecraft.basiccommands.BasicCommands;
import info.bytecraft.basiccommands.Players;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Vanish implements CommandExecutor, Listener {
	private static BasicCommands plugin;

	public Vanish(BasicCommands instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("poof")) {
			if (cs instanceof Player) {
				Player player = (Player) cs;
				if (player.hasPermission("bytecraft.vanish")) {
					for (Player other : Bukkit.getOnlinePlayers()) {
						Players players = plugin.getDatabase()
								.find(Players.class).where()
								.ieq("playerName", player.getName())
								.findUnique();
						if (players == null) {
							players = new Players();
							players.setPlayer(player);
							players.setVanished(true);
							plugin.getDatabase().save(players);
							if (!other.hasPermission("bytecraft.vanish")) {
								other.hidePlayer(player);
							}
							player.sendMessage(ChatColor.AQUA + "poof!");
							return true;
						} else {
							if (players.isVanished()) {
								players.setVanished(false);
								plugin.getDatabase().save(players);
								other.showPlayer(player);
								player.sendMessage(ChatColor.AQUA
										+ "You have re appeared");
								return true;
							} else {
								players.setVanished(true);
								players.setGodColor(players.getGodColor());
								plugin.getDatabase().save(players);
								if (!other.hasPermission("bytecraft.vanish")) {
									other.hidePlayer(player);
								}
								player.sendMessage(ChatColor.AQUA + "poof!");
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		for (Player players : Bukkit.getOnlinePlayers()) {
			Players database = plugin.getDatabase().find(Players.class).where()
					.ieq("playerName", player.getName()).findUnique();
			if (database != null && database.isVanished()) {
				if (!players.hasPermission("bytecraft.vanish")) {
					players.hidePlayer(players);
				} else {
					if (players != player) {
						players.sendMessage(player.getDisplayName() + ChatColor.AQUA + " has joined the server vanished");
					}
				}
				event.setJoinMessage(null);
				player.sendMessage(ChatColor.AQUA + "You have joined vanished");
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void moreJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		for (Player other : Bukkit.getOnlinePlayers()) {
			Players database = plugin.getDatabase().find(Players.class).where().ieq("playerName", other.getName()).findUnique();
			if (database != null && database.isVanished()) {
				if (!player.hasPermission("bytecraft.vanish")) {
					player.hidePlayer(other);
				}
			}
		}
	}

}
