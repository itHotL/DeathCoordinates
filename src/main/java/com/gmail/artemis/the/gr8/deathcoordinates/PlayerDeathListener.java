package com.gmail.artemis.the.gr8.deathcoordinates;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;



public class PlayerDeathListener implements Listener {
    
    Fancifier fancy = new Fancifier();
    private HashMap<UUID, Location> deathList = new HashMap<>();
    
    
    @EventHandler
    public void OnPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Location playerLocation = player.getLocation();
        World world = playerLocation.getWorld();
        String worldName = world.getName();
        Integer xCoords = playerLocation.getBlockX();
        Integer yCoords = playerLocation.getBlockY();
        Integer zCoords = playerLocation.getBlockZ();
        
        ChatColor worldColor = fancy.GetFancyWorldColor(worldName);
        worldName = fancy.GetFancyWorldName(worldName);
        
        String worldColorString = worldColor.name().toLowerCase();
        
        //String whisperPrefix = ChatColor.GRAY+"[server] "+ChatColor.ITALIC+"whispers: ";
        String coords = "("+xCoords+"/"+yCoords+"/"+zCoords+")";
        //player.sendMessage(whisperPrefix+"you died at "+coords+" in "+worldColor+ChatColor.ITALIC+worldName);
        
        UUID locationID = StoreLocation(playerLocation);
        
        String command = "tellraw "+player.getName()+" [\"\",{\"text\":\"[server] \",\"color\":\"gray\"}"+
                ",{\"text\":\"whispers: \",\"italic\":true,\"color\":\"gray\"}"+
                ",{\"text\":\"you died at "+coords+" in \",\"italic\":true,\"color\":\"gray\"}"+
                ",{\"text\":\""+worldName+"\",\"italic\":true,\"color\":\""+worldColorString+"\"}"+
                ",{\"text\":\" [share]\",\"color\":\"blue\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/plshelp "+locationID+"\"}"+
                ",\"hoverEvent\":{\"action\":\"show_text\",\"contents\":[{\"text\":\"Click here to share your coordinates in chat!\",\"color\":\"white\"}]}}]";
        
        ConsoleCommandSender console = Bukkit.getConsoleSender(); 
        
        
        //Main.getInstance();
        //JavaPlugin plugin = JavaPlugin.getPlugin(Main.class);
        
        Plugin plugin = Main.getInstance();
        //Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                Bukkit.dispatchCommand(console, command);
            }
        }, 20L);
    }
    
    public UUID StoreLocation(Location location) {
        
        UUID superRandomCode = UUID.randomUUID();
        deathList.put(superRandomCode, location);
        return superRandomCode;
        
    }
    
    public Location GetLocation (UUID locationID) {
        Location location = deathList.remove(locationID);
        return location;
    }                
}
