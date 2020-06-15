package org.RedTheITguy.IpTitleBar;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Methods {
	// Changes the IP to that provided
	public int updateIP(String ip) {
		// Stores the plugin in a variable
		Plugin plugin = Bukkit.getPluginManager().getPlugin("IpTitleBar");
		
		// Generates the namespace key
		NamespacedKey key = new NamespacedKey(plugin, "ipBar");
		
		// Stores the boss bar in a variable
		BossBar bar = Bukkit.getServer().getBossBar(key);
		
		// Runs if the boss bar doesn't exist
		if (bar == null) {
			// Outputs a warning to the console
			System.err.println("Could not update IP bar: Boss bar not found.");
			// Exits with code 1
			return 1;
		}
		
		// Stores the config in a variable
		FileConfiguration config = plugin.getConfig();

		// Gets the prefix from the config
		String prefix = config.getString("text.prefix").trim();
		
		// Creates the colour prefix with the default value
		char colourPrefix = '&';
		// Runs if the colour prefix is a char
		if (config.getString("colourPrefix").length() == 1) {
			// Sets the colour prefix character
			colourPrefix = config.getString("colourPrefix").charAt(0);
		}
		else {
			// Outputs a warning to the console
			System.err.println("Invalid colour prefix in config, using default.");
		}
		
		// Creates the string needed for the bar
		String barTxt = prefix + ": " + ip;
		barTxt = ChatColor.translateAlternateColorCodes(colourPrefix, barTxt);
		
		// Updates the boss bar
		bar.setTitle(barTxt);
		
		// Exits the method
		return 0;
	}
	
	// Shows the boss bar to the player
	public void updateInterface(Player player) {
		// Stores the plugin in a variable
		Plugin plugin = Bukkit.getPluginManager().getPlugin("IpTitleBar");
		
		// Generates the namespace key
		NamespacedKey key = new NamespacedKey(plugin, "ipBar");
		
		// Stores the boss bar in a variable
		BossBar bar = Bukkit.getServer().getBossBar(key);
		
		// Runs if the boss bar doesn't exist
		if (bar == null) {
			// Outputs a warning to the console
			System.err.println("Could not add player to IP bar: Boss bar not found.");
			// Exits the method
			return;
		}
		
		// Adds player to the bar
		bar.addPlayer(player);
		
		// Exits the method
		return;
	}
}
