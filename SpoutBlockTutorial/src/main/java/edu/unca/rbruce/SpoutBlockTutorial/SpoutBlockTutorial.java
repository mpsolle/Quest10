package edu.unca.rbruce.SpoutBlockTutorial;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

/*
 * This is the main class of the sample plug-in
 */
public class SpoutBlockTutorial extends JavaPlugin {
	SpoutBlockTutorialCommandExecutor executor = new SpoutBlockTutorialCommandExecutor(
			this);

	/*
	 * This is called when your plug-in is enabled
	 */
	@Override
	public void onEnable() {
		// from Spout tutorial
		getLogger().log(Level.INFO, "[Spout Block Test Plugin] Enabled!");

		// save the configuration file
		saveDefaultConfig();

		// Create the SampleListener
		new SpoutBlockTutorialListener(this);

		// set the command executor for sample
		this.getCommand("message").setExecutor(executor);
		this.getCommand("inventoryBlock").setExecutor(executor);
		this.getCommand("worldBlock").setExecutor(executor);

	}

	/*
	 * This is called when your plug-in shuts down
	 */
	@Override
	public void onDisable() {
		getLogger().log(Level.INFO, "[Spout Test Plugin] Disabled!");

	}

}
