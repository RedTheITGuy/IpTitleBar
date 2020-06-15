package org.RedTheITguy.IpTitleBar;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	// Fired when the plugin is first enabled
	@Override
	public void onEnable() {
		// Creates a config file if one does not exist
		this.saveDefaultConfig();
		// Stores the config in a variable
		FileConfiguration config = this.getConfig();
		// Gets the information from the config
		BarColor barColour = BarColor.valueOf(config.getString("format.barColour").toUpperCase().trim());
		BarStyle barStyle = BarStyle.valueOf(config.getString("format.barStyle").toUpperCase().trim());
		String ip = config.getString("text.ip");
		
		// Runs if the bar color was not valid
		if (barColour == null) {
			// Outputs a warning to the console
			System.err.println("Invalid bar colour in config, using default.");
			// Sets the bar colour
			barColour = BarColor.BLUE;
		}
		
		// Runs if the bar color was not valid
		if (barStyle == null) {
			// Outputs a warning to the console
			System.err.println("Invalid bar style in config, using default.");
			// Sets the bar colour
			barStyle = BarStyle.SEGMENTED_6;
		}
		
		// Gets the server info
		Server server = this.getServer();
		// Gets the methods class
		Methods methods = new Methods();
		
		// Creates the namespace key for the bar
		NamespacedKey key = new NamespacedKey(this, "ipBar");
		// Stores the boss bar in a variable if it exists
		KeyedBossBar bossBar = server.getBossBar(key);
		// Creates a boss bar if one does not exist
		if (bossBar == null) {
			bossBar = server.createBossBar(key, "", barColour, barStyle);
		}
		// Runs if the bar did exist
		else {
			// Sets the bar colour and style
			bossBar.setColor(barColour);
			bossBar.setStyle(barStyle);
		}
		// Ensures the bar is visible
		bossBar.setVisible(true);
		
		// Updates the IP
		methods.updateIP(ip);
		
		// Runs for all online players
		for (Player player : server.getOnlinePlayers()) {
			methods.updateInterface(player);
		}
		
		// Registers the event listener
		getServer().getPluginManager().registerEvents(new EventListener(), this);
		// Registers the commands
		this.getCommand("IP").setExecutor(new CommandIP());
		this.getCommand("changeIP").setExecutor(new CommandChangeIP());
	}
	
	// Fired when the plugin is disabled
	@Override
	public void onDisable() {
		// Generates the namespaced key
		NamespacedKey key = new NamespacedKey(this, "ipBar");
		// Removes everyone from the boss bar
		this.getServer().getBossBar(key).removeAll();
	}
}
