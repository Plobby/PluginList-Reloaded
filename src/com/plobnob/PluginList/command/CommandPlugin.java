package com.plobnob.PluginList.command;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import com.plobnob.utils.UtilsText;

public class CommandPlugin implements Listener {

	/**
	 * Command pre process event
	 */
	@EventHandler(priority = EventPriority.HIGHEST)
	public void commandPreProcess(PlayerCommandPreprocessEvent event) {
		if (event.getMessage().toLowerCase().startsWith("/plugins") && event.getMessage().length() == 8
				|| event.getMessage().toLowerCase().startsWith("/plugin") && event.getMessage().length() == 7
				|| event.getMessage().toLowerCase().startsWith("/pl") && event.getMessage().length() == 3
				|| event.getMessage().toLowerCase().startsWith("/plugins ")
				|| event.getMessage().toLowerCase().startsWith("/plugin ")
				|| event.getMessage().toLowerCase().startsWith("/pl ")) {
			Player sender = event.getPlayer();
			if (sender.hasPermission("pluginlist.use")) {
				Plugin[] plugins = Bukkit.getServer().getPluginManager().getPlugins();
				String[] args = event.getMessage().split(" ");
				boolean filterNext = false;
				char filterChar = 'A';
				boolean filterUsed = false;
				boolean separateUsed = false;
				for (String str : args) {
					if (filterNext)
						if (str.length() == 1) {
							filterChar = str.toUpperCase().charAt(0);
							filterUsed = true;
							continue;
						}
					if (str.equalsIgnoreCase("-f"))
						filterNext = true;
					if (str.equalsIgnoreCase("-s"))
						separateUsed = true;
				}
				sender.sendMessage(UtilsText.colour("&8&l[&b&l" + plugins.length + "&8&l] &a&lPlugin List &2&l>"));
				if (filterUsed) {
					if (separateUsed) {
						List<String> pluginNames = new ArrayList<>();
						int count = 0;
						for (Plugin p : plugins) {
							if (p.getName().toUpperCase().startsWith(String.valueOf(filterChar))) {
								String colour = (p.isEnabled()) ? "&a" : "&c";
								pluginNames.add(colour + p.getName() + "&7");
								count++;
							}
						}
						if (count > 0) {
							sender.sendMessage(
									UtilsText.colour("&8&l[&b&l" + count + "&8&l] &a&l" + filterChar + "&2&l> &7"));
							for (String s : pluginNames) {
								sender.sendMessage(UtilsText.colour("    &2&l> &r" + s + "&7"));
							}
						}
					} else {
						StringJoiner joiner = new StringJoiner(",");
						int count = 0;
						for (Plugin p : plugins) {
							if (p.getName().toUpperCase().startsWith(String.valueOf(filterChar))) {
								String colour = (p.isEnabled()) ? "&a" : "&c";
								if (count == 0) {
									joiner.add("&a&l" + filterChar + " &2&l> " + colour + p.getName() + "&7");
								} else {
									joiner.add(colour + " " + p.getName() + "&7");
								}
								count++;
							}
						}
						if (count > 0) {
							sender.sendMessage(UtilsText.colour("&8&l[&b&l" + count + "&8&l] " + joiner.toString()));
						}
					}
				} else {
					if (separateUsed) {
						for (char a = 'A'; a <= 'Z'; a++) {
							List<String> pluginNames = new ArrayList<>();
							int count = 0;
							for (Plugin p : plugins) {
								if (p.getName().toUpperCase().startsWith(String.valueOf(a))) {
									String colour = (p.isEnabled()) ? "&a" : "&c";
									pluginNames.add(colour + p.getName() + "&7");
									count++;
								}
							}
							if (count > 0) {
								sender.sendMessage(
										UtilsText.colour("&8&l[&b&l" + count + "&8&l] &a&l" + a + "&2&l> &7"));
								for (String s : pluginNames) {
									sender.sendMessage(UtilsText.colour("    &2&l> &r" + s + "&7"));
								}
							}
						}
					} else {
						for (char a = 'A'; a <= 'Z'; a++) {
							StringJoiner joiner = new StringJoiner(",");
							int count = 0;
							for (Plugin p : plugins) {
								if (p.getName().toUpperCase().startsWith(String.valueOf(a))) {
									String colour = (p.isEnabled()) ? "&a" : "&c";
									if (count == 0) {
										joiner.add("&a&l" + a + " &2&l> " + colour + p.getName() + "&7");
									} else {
										joiner.add(colour + " " + p.getName() + "&7");
									}
									count++;
								}
							}
							if (count > 0) {
								sender.sendMessage(
										UtilsText.colour("&8&l[&b&l" + count + "&8&l] " + joiner.toString()));
							}
						}
					}
				}
				event.setCancelled(true);
			}
		}
	}
}
