package info.bytecraft.basiccommands.commands;

import info.bytecraft.basiccommands.Permission;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemCommand implements CommandExecutor{

	@Override
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("item") && args.length !=0){
		Material material = null;
		try{
			material = Material.getMaterial(Integer.parseInt(args[0]));
		}catch(NumberFormatException e){
			material = Material.getMaterial(args[0].toUpperCase());
		}
		
		if(material != null){
			if(cs instanceof Player){
				Player player = (Player)cs;
				if(player.hasPermission(Permission.item)){
					if(args.length == 1){
						ItemStack item = new ItemStack(material, 1);
						player.getInventory().addItem(item);
						player.sendMessage(ChatColor.AQUA + "You got a " + material.name().toLowerCase().replace("_", " "));
						player.updateInventory();
						return true;
					}else if(args.length == 2){
						int value = Integer.parseInt(args[1]);
						ItemStack item = new ItemStack(material, value);
						player.getInventory().addItem(item);
						player.sendMessage(ChatColor.AQUA + "You got " + value + " " + material.name().toLowerCase().replace("_", " "));
						player.updateInventory();
						return true;
					}else if(args.length == 3){
						int value = Integer.parseInt(args[1]);
						short data = Short.parseShort(args[2]);
						ItemStack item = new ItemStack(material, value, data);
						player.getInventory().addItem(item);
						player.sendMessage(ChatColor.AQUA + "You got " + value + " " + material.name().toLowerCase().replace("_", " "));
						player.updateInventory();
						return true;
					}
				}
			}
		}else{
			return true;
			}
		}else if(cmd.getName().equalsIgnoreCase("clear") && args.length == 0){
			if(cs instanceof Player){
				Player player = (Player)cs;
				player.getInventory().clear();
				player.updateInventory();
				player.sendMessage(ChatColor.AQUA + "Your inventory has been cleared");
				return true;
			}
		}
		return false;
	}

}
