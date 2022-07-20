package com.gmail.artemis.the.gr8.deathcoordinates;

import github.scarsz.discordsrv.DiscordSRV;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {

    private static Main instance;   
    private static DiscordSRV discord;
    
    private PlayerDeathListener pdl;
    private Fancifier fancy;
    
    @Override
    public void onEnable() {
        pdl = new PlayerDeathListener();
        fancy = new Fancifier();
        if (getServer().getPluginManager().isPluginEnabled("DiscordSRV")) {
            discord = DiscordSRV.getPlugin();
        }

        Bukkit.getPluginManager().registerEvents(pdl, this);
        instance = this;
                
        //System.out.println("Enabled Death Coordinates");
        this.getLogger().info("Enabled DeathCoordinates");
    }

    public static Main getInstance() {
        return instance;
    }
    
    @Override
    public void onDisable() {

        //System.out.println("Disabled Death Coordinates");
        this.getLogger().info("Disabled DeathCoordinates :D");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if(sender instanceof Player) {
            if(label.equalsIgnoreCase("plshelp")) {
                if(args.length > 0) {
                    String locationID = args[0];
                    
                    try{
                        UUID locationUUID = UUID.fromString(locationID);
                        if(locationUUID != null) {
                            Location location = pdl.GetLocation(locationUUID);
                            if (location != null) {
                               
                                String playerName = sender.getName();
                                String coords = "("+location.getBlockX()+"/"+location.getBlockY()+"/"+location.getBlockZ()+")";
                                String fancyWorldName = fancy.GetFancyWorldName(location.getWorld().getName());
                                ChatColor fancyWorldColor = fancy.GetFancyWorldColor(location.getWorld().getName());
                                Player player = Bukkit.getPlayer(playerName);
                                ChatColor rankColor = fancy.GetRankColor(player);
                                Bukkit.broadcastMessage(rankColor+playerName+ChatColor.RESET+" would like some help!"+" They died at "+ChatColor.YELLOW+coords+ChatColor.RESET+" in "+fancyWorldColor+fancyWorldName);

                                //if DiscordSRV is enabled, also send a death message in chat
                                if (getServer().getPluginManager().isPluginEnabled("DiscordSRV")) {
                                    String discordMessage = "**Death Coordinates**: "+coords+" in "+fancyWorldName;
                                    discord.processChatMessage(player, discordMessage, discord.getMainChatChannel(), false);
                                }

                                return true;
                                
                            }
                        }
                    }
                    
                    catch(IllegalArgumentException exception) {
                        
                    }
                    
                    
                }
            }
            
        }
        
        return false;
        
    }
}
