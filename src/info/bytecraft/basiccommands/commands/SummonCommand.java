package info.bytecraft.basiccommands.commands;

import info.bytecraft.basiccommands.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SummonCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("summon")){
			if(cs instanceof Player){
				Player player = (Player)cs;
				if(player.hasPermission(Permission.summon)){
					if(args.length == 1){
						Player target = Bukkit.getPlayer(args[0]);
						if(target != null){
							target.sendMessage(player.getDisplayName() + ChatColor.AQUA + " has summoned you to himself");
							player.sendMessage(ChatColor.AQUA + "You have summoned " + target.getDisplayName());
							target.teleport(player.getLocation());
							return true;
						}
					}
				}
			}
		}
		return false;
	}

}
