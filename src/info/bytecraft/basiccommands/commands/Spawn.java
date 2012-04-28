package info.bytecraft.basiccommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("spawn")){
			if(args.length == 0){
				if(cs instanceof Player){
					Player player = (Player)cs;
					player.teleport(Bukkit.getWorld("world").getSpawnLocation());
					return true;
				}
			}else{
				return true;
			}
		}
		return false;
	}

}
