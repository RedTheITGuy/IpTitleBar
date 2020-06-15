package org.RedTheITguy.IpTitleBar;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class CommandChangeIP implements CommandExecutor {
	// Called when the command is run
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// Exits if there are no arguments
		if (args.length < 1) return false;
		
		// Creates a string to store the IP
		String ip = "";
		
		// Runs if the first argument is config
		if (args[0].equalsIgnoreCase("config")) {
			// Stores the plugin in a variable
			Plugin plugin = Bukkit.getPluginManager().getPlugin("IpTitleBar");
			// Stores the config in a variable
			FileConfiguration config = plugin.getConfig();
			// Gets the ip from the config
			ip = config.getString("text.ip");
		}
		else {
			// Runs for all the arguments
			for (String word : args) {
				// Adds the argument to the ip
				ip += word + " ";
			}
		}
		
		// Removes empty space from the ip
		ip = ip.trim();
		
		// Gets the methods class
		Methods methods = new Methods();
		// Sets the new ip and runs code if there is an error
    	if (methods.updateIP(ip) == 1) {
    		// Tells the command sender the command failed
    		sender.sendMessage(ChatColor.RED + "Could not find boss bar.");
    	}
		
    	// Tells the server the user ran the command correctly
		return true;
	}
}
