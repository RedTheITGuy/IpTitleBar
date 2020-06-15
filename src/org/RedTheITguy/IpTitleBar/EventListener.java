package org.RedTheITguy.IpTitleBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener implements Listener {
	// Fired when a player joins the game
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		// Gets the method class
		Methods methods = new Methods();
		// Displayes the bar for the player
		methods.updateInterface(event.getPlayer());
	}
}
