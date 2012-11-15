package edu.unca.rbruce.SpoutBlockTutorial;

import java.text.MessageFormat;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.getspout.spout.player.SpoutCraftPlayer;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;

/*
 * This is a sample event listener
 */
public class SpoutBlockTutorialListener implements Listener {
    private final SpoutBlockTutorial plugin;

    /*
     * This listener needs to know about the plugin which it came from
     */
    public SpoutBlockTutorialListener(SpoutBlockTutorial plugin) {
        // Register the listener
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
   
        this.plugin = plugin;
    }

    /*
     * Send the sample message to all players that join
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage(this.plugin.getConfig().getString("sample.message"));
    }
    
    /*
     * Another example of a event handler. This one will give you the name of
     * the entity you interact with, if it is a Creature it will give you the
     * creature Id.
     */
   
    
    
    @EventHandler
    public void onButtonClick(ButtonClickEvent event) {
    	Player player = event.getPlayer();
    	SpoutCraftPlayer.getPlayer(player).getMainScreen().closePopup();
    	event.getPlayer().getMainScreen().closePopup();
    }
    
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        final EntityType entityType = event.getRightClicked().getType();

        event.getPlayer().sendMessage(MessageFormat.format(
                "You interacted with a {0} it has an id of {1}",
                entityType.getName(),
                entityType.getTypeId()));
    }
}
