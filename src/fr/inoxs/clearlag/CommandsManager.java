package fr.inoxs.clearlag;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Monster;

public class CommandsManager implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String prefix = ClearLag.config.getString("prefix").replace('&', '§');
		if(label.equalsIgnoreCase("lagclear")){
		 if(sender.hasPermission("lag.clear")){
			World world = Bukkit.getServer().getWorld("world");
	        List<Entity> entList = world.getEntities();
	 int count = 0;
	        for(Entity current : entList){
	            if (current instanceof Item){
	            current.remove();
	            count++;
	            }
	            if (current instanceof Monster){
		            current.remove();
	            count++;
	            }
	        }
	    	Bukkit.broadcastMessage(prefix+ClearLag.config.getString("delete_message").replace('&', '§').replace("%count%", count+""));
		}
		}
		
		
		return false;
	}

}
