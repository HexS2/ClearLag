package fr.inoxs.clearlag;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Monster;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ClearLag extends JavaPlugin implements Listener {
	
	public static FileConfiguration config;

	public void onEnable() {

		getCommand("lagclear").setExecutor(new CommandsManager());
		ClearLag.config = getConfig();
		ClearLag.config.options().copyDefaults(true);
		saveDefaultConfig();
		final String prefix = ClearLag.config.getString("prefix").replace('&', '§');
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			private int a = 300;

			public void run() {
				switch (a) {
				case 180:
					Bukkit.broadcastMessage(prefix + config.getString("message").replace('&', '§').replace("%x%", "3"));
					break;
				case 60:
					Bukkit.broadcastMessage(prefix + config.getString("message").replace('&', '§').replace("%x%", "1"));
					break;

				case 0:
					World world = getServer().getWorld("world");
					List<Entity> entList = world.getEntities();
					int count = 0;
					for (Entity current : entList) {
						if (current instanceof Item) {
							current.remove();
							count++;
						}
						if (current instanceof Monster) {
							current.remove();
							count++;
						}
					}
					Bukkit.broadcastMessage(prefix + ClearLag.config.getString("delete_message").replace('&', '§')
							.replace("%count%", count + ""));
				}
				if (a == 0) {
					a = 300;
				}
				a -= 1;

			}
		}, 20L, 20L);
	}

}
