package info.bytecraft.basiccommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveCommand implements CommandExecutor{
	
	int value;
	Player target;
	Player player;
	
	@Override
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("give") && args.length > 1){
			Material material = null;
			try{
				material = Material.getMaterial(Integer.parseInt(args[1]));
			}catch(NumberFormatException e){
				material = Material.getMaterial(args[1].toUpperCase());
			}
			if(material == null){
				cs.sendMessage(ChatColor.RED + "Invalid item");
				return true;
			}else{
			if(cs instanceof Player){
				player = (Player)cs;
				if(player.hasPermission("bytecraft.give")){
					if(args.length == 2){
						 target = Bukkit.getPlayer(args[0]);
						 if(target != null){
							ItemStack item = new ItemStack(material, 1);
								target.getInventory().addItem(item);
								target.updateInventory();
								player.sendMessage(ChatColor.AQUA + "You gave " + target.getDisplayName() + ChatColor.AQUA + " some " + material.name().toLowerCase().replace("_", " "));
								target.sendMessage(ChatColor.AQUA + "You have recieved a gift, check your inventory");
								return true;
						 }
					}else if(args.length == 3){
						target = Bukkit.getPlayer(args[0]);
						value = Integer.parseInt(args[2]);
							if(target != null){
								ItemStack item = new ItemStack(material, value);
								target.getInventory().addItem(item);
								target.updateInventory();
								player.sendMessage(ChatColor.AQUA + "You gave " + target.getDisplayName() + ChatColor.AQUA + " some " + material.name().toLowerCase().replace("_", " "));
								target.sendMessage(ChatColor.AQUA + "You have recieved a gift, check your inventory");
								return true;
							}
					}else if(args.length == 4){
						target = Bukkit.getPlayer(args[0]);
						value = Integer.parseInt(args[2]);
						short data = Short.parseShort(args[3]);
						if(target != null){
							ItemStack item = new ItemStack(material, value, data);
							target.getInventory().addItem(item);
							target.updateInventory();
							player.sendMessage(ChatColor.AQUA + "You gave " + target.getDisplayName() + ChatColor.AQUA + " some " + material.name().toLowerCase().replace("_", " "));
							target.sendMessage(ChatColor.AQUA + "You have recieved a gift, check your inventory");
							return true;
						}
					}
				}
			}
		}
	}
		return false;
	}
}
