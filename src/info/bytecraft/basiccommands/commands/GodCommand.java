package info.bytecraft.basiccommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("god") && args.length > 0){
			for(Player p: Bukkit.getOnlinePlayers()){
				if(!(cs instanceof Player))return false;
				Player player = (Player)cs;
				if(player.hasPermission("bytecraft.god")){
			int i = 0; int para = args.length;
			String s = "";
			while(i<para){
				s=s+ " " + args[i];
				i++;
			}
			Bukkit.broadcastMessage(ChatColor.RED + "<GOD>" + ChatColor.RED + s);
			if(p.hasPermission("bytecraft.god")){
				p.sendMessage(ChatColor.AQUA + "//Say used by: " + player.getDisplayName());
			}
			return true;
			}
			}
		}
		return false;
	}

}
