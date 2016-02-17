package com.rio.items;

import org.bukkit.inventory.ItemStack;

/* most likely will never be used.
 * 
 * 
 * 
 */


public class BasicItem {
	
	private final String name;
	private final ItemStack item;
	
	public BasicItem(String name, ItemStack item){
		this.name = name;
		this.item = item;
	}
	
	public String getName(){
		return name;
	}
	
	public ItemStack getItem(){
		return item;
	}
	

}
