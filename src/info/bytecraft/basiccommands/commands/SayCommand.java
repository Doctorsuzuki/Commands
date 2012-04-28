package info.bytecraft.basiccommands.commands;

import info.bytecraft.basiccommands.BasicCommands;
import info.bytecraft.basiccommands.Players;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SayCommand implements CommandExecutor{
	private static BasicCommands plugin;
	public SayCommand(BasicCommands instance){
		plugin = instance;
	}
	
	String god = "<GOD>";
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("say") && args.length > 0){
			int i = 0; int para = args.length; String s = "";
			while(i<para){
				s=s+" " + args[i];
				i++;
			}
			for(Player p: Bukkit.getOnlinePlayers()){
				if(cs instanceof Player){
					Player player = (Player)cs;
						if(player.hasPermission("bytecraft.god")){
							Players players = plugin.getDatabase().find(Players.class).where().ieq("playerName", player.getName()).findUnique();
							String color;
							if(players == null || players.getGodColor() == null){
								color = "&c";
							}else{
								color = players.getGodColor();
							}
							Bukkit.broadcastMessage(color.replaceAll("(&([0-9a-f]))", ChatColor.COLOR_CHAR + "$2") + god + ChatColor.RED + s);
							if(p.hasPermission("bytecraft.god")){
								p.sendMessage(ChatColor.AQUA + "//Say used by: " + player.getDisplayName());
							}
							return true;
					}
				}else{
					ChatColor red = ChatColor.DARK_RED;
					Bukkit.broadcastMessage(red + god + ChatColor.RED + s);
					return true;
				}
			}
		}
		return false;
	}

}
