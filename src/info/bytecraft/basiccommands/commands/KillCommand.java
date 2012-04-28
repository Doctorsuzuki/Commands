package info.bytecraft.basiccommands.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("kill")){
			if(cs instanceof Player){
				Player player = (Player)cs;
				player.setHealth(0);
				return true;
			}
		}
		return false;
	}
}
