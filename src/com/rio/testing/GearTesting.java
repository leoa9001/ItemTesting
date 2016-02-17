package com.rio.testing;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.rio.items.CustomItem;

public class GearTesting extends JavaPlugin{
	
	@Override
	public void onEnable(){
		
		registerCommands();
		registerEvents();
//		getLogger().info("INFO WORKS");
		
	}
	
	
	public void onDisable(){
		
	}
	
	
	public void registerCommands(){
		getCommand("gimme").setExecutor(new ItemCommands());
		getCommand("itemlist").setExecutor(new ItemCommands());
	}
	
	
	public void registerEvents(){
		PluginManager pm = getServer().getPluginManager();
		
		for(CustomItem c: CustomItem.values()){
			Listener l = c.getListener();
//			getLogger().info("RUNNINING r RUNNINING");
			if(l!=null){
				//if changes to the general layout... this may not work.
				pm.registerEvents(l,this);
//				getLogger().info("REGISTRATION");
			}
		}
	}
}
