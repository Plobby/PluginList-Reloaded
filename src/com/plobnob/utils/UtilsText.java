package com.plobnob.utils;

import org.bukkit.ChatColor;

public class UtilsText {

	/** Colour text method **/
	public static String colour(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}

	/** Remove colour from text method **/
	public static String removeColour(String text) {
		return ChatColor.stripColor(text);
	}

}