package info.bytecraft.basiccommands.commands;

import info.bytecraft.basiccommands.Permission;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

public class MobSpawn implements CommandExecutor {
	int x = 0;
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("cmob") && args.length >= 1){
			EntityType type = EntityType.valueOf(args[0].toUpperCase());
			if(type != null){
			if(cs instanceof Player){
				Player player = (Player)cs;
					if(player.hasPermission(Permission.mob)){
						if(args.length == 1){
							player.getWorld().spawnCreature(player.getLocation(), type);
							player.sendMessage(ChatColor.AQUA + "Spawning " + type.getName().toLowerCase().replace("_", " "));
							return true;
						}else if(args.length == 2){
							int value = Integer.parseInt(args[1]);
							while(x<value){
								player.getWorld().spawnCreature(player.getLocation(), type);
								x++;
							}
							player.sendMessage(ChatColor.AQUA + "Spawning " + type.getName().toLowerCase().replace("_", " "));
							return true;
						}else{
							return true;
						}
					}
				}
			}
		}else if(cmd.getName().equalsIgnoreCase("nuke") && args.length == 0){
			if(cs instanceof Player){
				Player player = (Player)cs;
				if(player.hasPermission(Permission.nuke)){
						try{
							throw new UnsupportedOperationException("Not yet implmented");
						}catch(UnsupportedOperationException ex){
							player.sendMessage(ChatColor.RED + "Error: " + ex.getMessage());
						}
						return true;
				}
			}
		}
		return false;
	}
}
