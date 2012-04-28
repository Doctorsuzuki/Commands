package info.bytecraft.basiccommands.commands;

import info.bytecraft.basiccommands.Bans;
import info.bytecraft.basiccommands.BasicCommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanCommand implements CommandExecutor{
	private static BasicCommands plugin;
	public BanCommand(BasicCommands instance){
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("ban") && args.length == 1){
			Player target = Bukkit.getPlayer(args[0]);
			if(target != null){
				if(cs instanceof Player){
					Player player = (Player)cs;
					if(player.hasPermission("bytecraft.ban")){
						Bans bans = plugin.getDatabase().find(Bans.class).where().ieq("playerName", target.getName()).findUnique();
						if(bans == null){
							bans = new Bans();
							bans.setPlayer(target);
							bans.setBanner(player);
							bans.setBanned(true);
							plugin.getDatabase().save(bans);
							target.kickPlayer(ChatColor.RED + "You have been banned by " + player.getDisplayName());
							Bukkit.broadcastMessage(target.getDisplayName() + ChatColor.RED + " has been banned by " + player.getDisplayName());
							return true;
						}else{
							return true;
						}
					}
				}else{
					Bans bans = plugin.getDatabase().find(Bans.class).where().ieq("playerName", target.getName()).findUnique();
					if(bans == null){
						bans = new Bans();
						bans.setBanner(target);
						bans.setBanner(null);
						bans.setBanned(true);
						plugin.getDatabase().save(bans);
						target.kickPlayer(ChatColor.RED + "You have been banned by " + ChatColor.DARK_RED + "GOD");
						Bukkit.broadcastMessage(target.getDisplayName() + ChatColor.RED + " has been banned by " + ChatColor.DARK_RED + "GOD");
						return true;
					}
				}
			}
		}
		return false;
	}

}
