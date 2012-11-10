package edu.unca.rbruce.SpoutBlockTutorial;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.CustomBlock;

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
			testBlock = new TestBlock(plugin);
			Player player = (Player) sender;
			Location loc = player.getLocation();
			Block b = player.getWorld().getBlockAt(loc);
			SpoutManager.getMaterialManager().overrideBlock(b, testBlock);
			SpoutBlock sb = (SpoutBlock) b;
			sb.setCustomBlock(testBlock);
			return true;

		} else if (command.getName().equalsIgnoreCase("message")
				&& sender.hasPermission("SpoutBlockTutorial.message")
				&& args.length > 0) {
			this.plugin.getConfig().set("sample.message",
					Joiner.on(' ').join(args));
			return true;

		} else {
			return false;
		}
	}

}
