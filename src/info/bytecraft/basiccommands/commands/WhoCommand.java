package info.bytecraft.basiccommands.commands;


import info.bytecraft.basiccommands.BasicCommands;
import info.bytecraft.basiccommands.Players;
import info.bytecraft.bytes.Bytes;
import info.bytecraft.bytes.api.Economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhoCommand implements CommandExecutor{
	private static BasicCommands plugin;
	public WhoCommand(BasicCommands instance){
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("who")){
			if(args.length == 0){
				if(cs instanceof Player){
					Player sender = (Player)cs;
				StringBuilder list = new StringBuilder();
				for(Player player: Bukkit.getOnlinePlayers()){
					if(Bukkit.getOnlinePlayers().length > 1){
						if(sender.canSee(player)){
						list.append(player.getDisplayName() +", ");
						}
					}
					list.append(player.getDisplayName());
				}
				sender.sendMessage(ChatColor.DARK_AQUA + "~*~*~*~* " + ChatColor.GOLD + Bukkit.getOnlinePlayers().length + " player(s) online" + ChatColor.DARK_AQUA + " ~*~*~*~*");
				sender.sendMessage(ChatColor.GOLD + "Online Players: " + list.toString());
				return true;
			}else{
				StringBuilder list = new StringBuilder();
				for(Player player: Bukkit.getOnlinePlayers()){
					if(Bukkit.getOnlinePlayers().length > 1){
						list.append(", ");
					}
					list.append(player.getName());
				}
				cs.sendMessage("Online Players"+"("+Bukkit.getOnlinePlayers().length+")"+": " + list.toString());
				return true;
			}
			}else if(args.length == 1){
				Player target = Bukkit.getPlayer(args[0]);
				if(target != null){
					Economy eco = ((Bytes)plugin.getServer().getPluginManager().getPlugin("Bytes")).getEconomy();
					Players info = plugin.getDatabase().find(Players.class).where().ieq("playerName", target.getName()).findUnique();
					if(cs instanceof Player){
						Player player = (Player)cs;
						if(player.hasPermission("bytecraft.who")){
							player.sendMessage(ChatColor.GREEN + "Name: " + target.getDisplayName());
							player.sendMessage(ChatColor.GREEN + "IP: " + ChatColor.WHITE + target.getAddress().getAddress().getHostAddress());
							player.sendMessage(ChatColor.GREEN + "World: " + ChatColor.WHITE + target.getWorld().getName());
							player.sendMessage(ChatColor.GREEN + "Bytes: " + ChatColor.WHITE + eco.getBalance(target));
							player.sendMessage(ChatColor.GREEN + "Id: " + ChatColor.WHITE + info.getId());
							return true;
							}
						}else{
							cs.sendMessage(ChatColor.GREEN + "Name: " + target.getDisplayName());
							cs.sendMessage(ChatColor.GREEN + "IP: " + ChatColor.WHITE + target.getAddress().getAddress().getHostAddress());
							cs.sendMessage(ChatColor.GREEN + "World: " + ChatColor.WHITE + target.getWorld().getName());
							cs.sendMessage(ChatColor.GREEN + "Bytes: " + ChatColor.WHITE + eco.getBalance(target));
							cs.sendMessage(ChatColor.GREEN + "Id: " + ChatColor.WHITE + info.getId());
							return true;
						}
					}else{
						return true;
					}
				}
		}
		return false;
	}

}
