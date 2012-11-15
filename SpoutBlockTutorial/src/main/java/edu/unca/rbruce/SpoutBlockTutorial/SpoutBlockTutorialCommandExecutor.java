package edu.unca.rbruce.SpoutBlockTutorial;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.getspout.spout.player.SpoutCraftPlayer;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.gui.Button;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.PopupScreen;
import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.entity.*;
import org.bukkit.entity.Player;
import com.google.common.base.Joiner;

/*
 * This is a sample CommandExectuor
 */
public class SpoutBlockTutorialCommandExecutor implements CommandExecutor {
	private final SpoutBlockTutorial plugin;

	public static CustomBlock testBlock;

	/*
	 * This command executor needs to know about its plugin from which it came
	 * from
	 */
	public SpoutBlockTutorialCommandExecutor(SpoutBlockTutorial plugin) {
		this.plugin = plugin;
	}

	/*
	 * On command set the sample message
	 */
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED
					+ "you must be logged on to use these commands");
			return false;

		} else if (command.getName().equalsIgnoreCase("inventoryBlock")
				&& sender.hasPermission("SpoutBlockTutorial.inventoryBlock")) {
			Player player = (Player) sender;
			testBlock = new TestBlock(plugin);
			player.getInventory().addItem(new SpoutItemStack(testBlock, 1));
			return true;

		} else if (command.getName().equalsIgnoreCase("worldBlock")
				&& sender.hasPermission("SpoutBlockTutorial.worldBlock")) {
			testBlock = new RedBlock(plugin);
			Player player = (Player) sender;
			Location loc = player.getLocation();
			Block b = player.getWorld().getBlockAt(loc);
			SpoutManager.getMaterialManager().overrideBlock(b, testBlock);
			SpoutBlock sb = (SpoutBlock) b;
			sb.setCustomBlock(testBlock);
			return true;

		} else if (command.getName().equalsIgnoreCase("message")) {
				PopupScreen popup = new GenericPopup(); // New Popup				
				GenericLabel label1 = new GenericLabel("Hope you enjoy my server");
//				label1.setAuto(false).setX(0).setY(0).setWidth(100).setHeight(30); // Set off center
				label1.setWidth(100).setHeight(30); //Sets height and width of text
				label1.setAlign(WidgetAnchor.CENTER_CENTER); //Aligns off center of x and y
				label1.setAnchor(WidgetAnchor.CENTER_CENTER); //Anchors to center of x and y
				
				//Getting Coordinates of message
//				int y = label1.getHeight();
//				int x = label1.getWidth();				
//				Button button = (Button) new GenericButton("Button").setWidth(200).setHeight(20); // Read more about creating widgets in Widget
//				button.setAlign(WidgetAnchor.BOTTOM_CENTER);
//				button.setAnchor(WidgetAnchor.BOTTOM_CENTER);
							
				GenericButton button = new GenericButton("Continue");
				button.setX(110).setY(180);
				button.setWidth(200).setHeight(20); //Makes the button 200 pixels wide and 20 high
				popup.attachWidget(plugin, button); // Necessary because the player needs a mouse to interact with a button
							
				popup.attachWidgets(plugin,label1, button); // Attach the widget to the popup
				Player player = (Player) sender;
				SpoutCraftPlayer.getPlayer(player).getMainScreen().attachPopupScreen(popup); // Show the player the popup				
				return true;
		
		} else if (command.getName().equalsIgnoreCase("nomessage")) {
				Player player = (Player) sender;
				SpoutCraftPlayer.getPlayer(player).getMainScreen().closePopup();
				return true;
		
		} else {
			return false;
		}
	}

}
