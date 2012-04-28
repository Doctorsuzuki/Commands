package info.bytecraft.basiccommands;

import java.util.ArrayList;
import java.util.List;

public class Permission {
	private static String base = "bytecraft.";
	public static String kick = base + "kick";
	public static String ban = base + "ban";
	public static String give = base + "give";
	public static String item = base + "item";
	public static String god = base + "god";
	public static String mob = base + "mobspawn";
	public static String vanish = base + "vanish";
	public static String nuke = base + ".nuke";
	public static String summon = base + ".summon";
	public static String smite = base + ".smite";
	public static String killSmite = smite + ".kill";
	public static String inventory = base + ".inv";
	public static String[] all = {kick,ban,give,item,god,mob,vanish,summon,smite,killSmite,inventory};
	
	public static List<String> getPermissions(){
		List<String> list = new ArrayList<String>();
		list.add(ban); list.add(kick); list.add(ban);
		list.add(give); list.add(item);
		list.add(god); list.add(mob); list.add(vanish);
		list.add(nuke); list.add(summon); list.add(smite); 
		list.add(killSmite); list.add(inventory);
		return list;
	}
}
