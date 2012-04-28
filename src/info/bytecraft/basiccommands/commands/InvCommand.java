package info.bytecraft.basiccommands.commands;

import info.bytecraft.basiccommands.Permission;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("inv")){
			if(cs instanceof Player){
				Player player = (Player)cs;
				if(player.hasPermission(Permission.inventory)){
					if(args.length == 1){
						Player target = Bukkit.getPlayer(args[0]);
						if(target != null){
							player.openInventory(target.getInventory());
							return true;
						}
					}
				}
			}
		}
		return true;
	}

}
