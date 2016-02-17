package com.rio.testing;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rio.items.CustomItem;

public class ItemCommands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String label, String[] args) {
		// TODO Auto-generated method stub

		if(!(sender instanceof Player)){
			sender.sendMessage("You must be a player to use the tools :p");
		}else {
			Player pl = (Player)sender;


			if(label.equals("gimme")){
				try{
					pl.getInventory().addItem(CustomItem.getItem(args[0]).getItem());
				}catch(Exception e){
					pl.sendMessage("It looks like you entered something wrong. Here are the list of items: "
							+ CustomItem.itemLabels());
				}
			}else if(label.equals("itemlist")){
				pl.sendMessage(CustomItem.itemLabels());
			}



		}

		return false;
	}

}
