package com.plobnob.PluginList;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.plobnob.PluginList.command.CommandPlugin;

public class Main extends JavaPlugin 
{
	
	/**
	 * Variables
	 */
	public static Plugin plugin;
	
	/**
	 * OnEnable
	 * Register events for plugin enable event
	 */
	public void onEnable() {
		plugin = this;
		Bukkit.getPluginManager().registerEvents(new CommandPlugin(), plugin);
	}
	
	/**
	 * OnDisable
	 * Register events for plugin disable event
	 */
	public void onDisable() {
		plugin = null;
	}
	
}