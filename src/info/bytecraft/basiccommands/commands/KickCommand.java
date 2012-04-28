package info.bytecraft.basiccommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("kick") && args.length == 1){
			Player target = Bukkit.getPlayer(args[0]);
			if(target != null){
				if(cs instanceof Player){
					Player player = (Player)cs;
					if(player.hasPermission("bytecraft.kick")){
						if(!target.getName().equalsIgnoreCase("sabersamus")){
							target.kickPlayer(ChatColor.DARK_AQUA + "You were kicked by " + player.getDisplayName());
							Bukkit.broadcastMessage(target.getDisplayName() + ChatColor.DARK_AQUA + " was kicked by " + player.getDisplayName());
						}else{
							player.kickPlayer(ChatColor.RED + "Do not kick your god!");
							Bukkit.broadcastMessage(player.getDisplayName() + ChatColor.DARK_AQUA + " was kicked");
							return true;
						}
					}
				}else{
					
				}
			}
		}
		return false;
	}
}
