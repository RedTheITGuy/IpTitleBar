package org.RedTheITguy.IpTitleBar;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class CommandIP implements CommandExecutor {
	// Called when the command is run
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// Stores the plugin in a variable
		Plugin plugin = Bukkit.getPluginManager().getPlugin("IpTitleBar");
		// Generates the namespace key
		NamespacedKey key = new NamespacedKey(plugin, "ipBar");
		
		// Stores the boss bar in a variable
		BossBar bar = Bukkit.getServer().getBossBar(key);
		
		// Runs if the boss bar doesn't exist
		if (bar == null) {
			// Outputs a warning to the console
			System.err.println("Could not get IP: Boss bar not found.");
			// Tells the sender the ip could not be found
			sender.sendMessage(ChatColor.RED + "Could not get IP: Boss bar not found.");
			// Tells the server the user ran the command correctly
			return true;
		}
		
		
		// Stores the config in a variable
		FileConfiguration config = plugin.getConfig();
		// Gets the info from the config
		String prefix = config.getString("text.prefix").trim();
		String message = config.getString("text.ipCommand.message");
		String hover = config.getString("text.ipCommand.hover");
		
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
		
		// Creates the ip bar string without the ip
		String beforeIP = prefix + ": ";
		// Formats the string the same as the bar
		beforeIP = ChatColor.translateAlternateColorCodes(colourPrefix, beforeIP);
		// Gets the length of the ip bar string without the IP
		int beforeIPlength = beforeIP.length();
		
		// Gets the IP
		String ip = bar.getTitle().substring(beforeIPlength);
		// Removes colour codes from the string
		String rawIP = ChatColor.stripColor(ip);
		
		// Runs is the sender is a player
		if (sender instanceof Player) {
			// Converts the sender to a player
			Player player = (Player) sender;
			
			// Stores the message in a string
			String msg = message + " " + ip;
			msg = ChatColor.translateAlternateColorCodes(colourPrefix, msg);
			
			// Formats the hover text
			hover = ChatColor.translateAlternateColorCodes(colourPrefix, hover);
			
			// Creates a text element to send
			TextComponent toBeSent = new TextComponent(msg);
			// Adds the hover text to the text element
			toBeSent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create()));
			// Creates an event to copy the IP when clicked
			toBeSent.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, rawIP));
			
			// Sends the message to the player
			player.spigot().sendMessage(toBeSent);
		}
		else {
			// Sends the IP in plain text
			sender.sendMessage(rawIP);
		}
		
		// Tells the server the user ran the command correctly
		return true;
	}
}
