package com.gmail.artemis.the.gr8.deathcoordinates;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Fancifier {

    public ChatColor GetRankColor(Player player) {

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team admin = scoreboard.getTeam("admin");
        Team moderator = scoreboard.getTeam("moderator");
        Team member = scoreboard.getTeam("member");
        Team stranger = scoreboard.getTeam("stranger");

        if (admin != null && admin.getEntries().contains(player.getName())) {
            return admin.getColor();
        }

        if (moderator != null && moderator.getEntries().contains(player.getName())) {
            return moderator.getColor();
        }

        if (member != null && member.getEntries().contains(player.getName())) {
            return member.getColor();
        }

        if (stranger != null && stranger.getEntries().contains(player.getName())) {
            return stranger.getColor();
        }

        return ChatColor.LIGHT_PURPLE;
    }

    public ChatColor GetFancyWorldColor(String worldName) {

        ChatColor worldColor;

        if (worldName.equalsIgnoreCase("world_nether")) {
            worldColor = ChatColor.RED;
        } else if (worldName.equalsIgnoreCase("world_the_end")) {
            worldColor = ChatColor.DARK_PURPLE;
        } else if (worldName.equalsIgnoreCase("world")) {
            worldColor = ChatColor.GREEN;
        } else if (worldName.equalsIgnoreCase("resourceworld")) {
            worldColor = ChatColor.DARK_GREEN;
        } else if (worldName.equalsIgnoreCase("resourceworld_nether")) {
                worldColor = ChatColor.DARK_GREEN;
        } else if (worldName.equalsIgnoreCase("creativeworld")) {
            worldColor = ChatColor.BLUE;
        } else {
            worldColor = ChatColor.WHITE;
        }

        return worldColor;
    }

    public String GetFancyWorldName(String worldName) {
        
        if (worldName.equalsIgnoreCase("world_nether")) {
            worldName = "the Nether";
        } else if (worldName.equalsIgnoreCase("world_the_end")) {
            worldName = "the End";
        } else if (worldName.equalsIgnoreCase("world")) {
            worldName = "the Overworld";
        } else if (worldName.equalsIgnoreCase("resourceworld")) {
            worldName = "the Resource World";
        } else if (worldName.equalsIgnoreCase("resourceworld_nether")) {
            worldName = "the Resource Nether";
        } else if (worldName.equalsIgnoreCase("creativeworld")) {
            worldName = "Creative";
        } else {
            worldName = worldName;
        }

        return worldName;
    }
}
