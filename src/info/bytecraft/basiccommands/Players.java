package info.bytecraft.basiccommands;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.avaje.ebean.validation.NotNull;

@Entity()
@Table(name="bytecraft_players")
public class Players {
	
	@Id
	private int id;
	
	@NotNull
	private String playerName;
	
	private String godColor;
	
	private boolean vanished;
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getPlayerName(){
		return this.playerName;
	}
	
	public void setPlayerName(String name){
		this.playerName = name;
	}
	
	public Player getPlayer(){
		return Bukkit.getPlayer(playerName);
	}
	
	public void setPlayer(Player player){
		this.playerName = player.getName();
	}
	
	public String getGodColor(){
		return this.godColor;
	}
	
	public void setGodColor(String color){
		this.godColor = color;
	}
	
	public boolean isVanished(){
		return this.vanished;
	}
	
	public void setVanished(boolean vanished){
		this.vanished = vanished;
	}
}
