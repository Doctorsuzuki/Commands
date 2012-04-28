package info.bytecraft.basiccommands;

import info.bytecraft.basiccommands.commands.*;
import info.bytecraft.basiccommands.listeners.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BasicCommands extends JavaPlugin{

	@Override
	public void onEnable(){
		setupDatabase();
		setupPlayers();
		registerCommands();
		registerEvents();
		removeMessages();
	}
	
	private void setupDatabase(){
		try{
			this.getDatabase().find(Bans.class).findRowCount();
		}catch(PersistenceException ex){
			this.installDDL();
		}
	}
	
	private void setupPlayers(){
		try{
			this.getDatabase().find(Players.class).findRowCount();
		}catch(PersistenceException ex){
			this.installDDL();
		}
	}
	
	@Override
	public List<Class<?>> getDatabaseClasses(){
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(Bans.class);
		list.add(Players.class);
		return list;
	}
	
	private void registerCommands(){
		this.getCommand("ban").setExecutor(new BanCommand(this));
		this.getCommand("kick").setExecutor(new KickCommand());
		this.getCommand("item").setExecutor(new ItemCommand());
		this.getCommand("give").setExecutor(new GiveCommand());
		this.getCommand("god").setExecutor(new GodCommand());
		this.getCommand("say").setExecutor(new SayCommand(this));
		this.getCommand("spawn").setExecutor(new Spawn());
		this.getCommand("clear").setExecutor(new ItemCommand());
		this.getCommand("who").setExecutor(new WhoCommand(this));
		this.getCommand("cmob").setExecutor(new MobSpawn());
		this.getCommand("poof").setExecutor(new Vanish(this));
		this.getCommand("gamemode").setExecutor(new CGamemode());
		this.getCommand("creative").setExecutor(new CGamemode());
		this.getCommand("survival").setExecutor(new CGamemode());
		this.getCommand("nuke").setExecutor(new MobSpawn());
		this.getCommand("summon").setExecutor(new SummonCommand());
		this.getCommand("kill").setExecutor(new KillCommand());
		this.getCommand("t").setExecutor(new SmiteCommand());
		this.getCommand("killp").setExecutor(new SmiteCommand());
		this.getCommand("inv").setExecutor(new InvCommand());
	}
	
	private void removeMessages(){
		this.getCommand("ban").setPermissionMessage("");
		this.getCommand("kick").setPermissionMessage("");
		this.getCommand("item").setPermissionMessage("");
		this.getCommand("give").setPermissionMessage("");
		this.getCommand("god").setPermissionMessage("");
		this.getCommand("say").setPermissionMessage("");
		this.getCommand("spawn").setPermissionMessage("");
		this.getCommand("clear").setPermissionMessage("");
		this.getCommand("who").setPermissionMessage("");
		this.getCommand("cmob").setPermissionMessage("");
		this.getCommand("poof").setPermissionMessage("");
		this.getCommand("gamemode").setPermissionMessage("");
		this.getCommand("creative").setPermissionMessage("");
		this.getCommand("survival").setPermissionMessage("");
		this.getCommand("nuke").setPermissionMessage("");
		this.getCommand("summon").setPermissionMessage("");
		this.getCommand("kill").setPermissionMessage("");
		this.getCommand("t").setPermissionMessage("");
		this.getCommand("killp").setPermissionMessage("");
		this.getCommand("inv").setPermissionMessage("");
	}
	
	private void registerEvents(){
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new PlayerJoin(this), this);
		pm.registerEvents(new MobSpawnListener(), this);
		pm.registerEvents(new Vanish(this), this);
		pm.registerEvents(new Respawn(), this);
	}
	
	
}
