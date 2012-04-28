package info.bytecraft.basiccommands;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.avaje.ebean.validation.NotNull;

@Entity()
@Table(name="bytecraft_bans")
public class Bans {
	
	@Id
	private int id;
	
	@NotNull
	private String playerName;
	
	private String bannerName;
	
	@NotNull
	private boolean banned;
	
	private String reason;
	
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
	
	public boolean isBanned(){
		return this.banned;
	}
	
	public void setBanned(boolean banned){
		this.banned = banned;
	}
	
	public String getBannerName(){
		return this.bannerName;
	}
	
	public void setBannerName(String name){
		this.bannerName = name;
	}
	
	public void setBanner(Player player){
		this.bannerName = player.getName();
	}
		
	public String getReason(){
		return this.reason;
	}
	
	public void setReason(String reason){
		this.reason = reason;
	}
}
