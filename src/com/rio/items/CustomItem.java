package com.rio.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.rio.items.eventclasses.ItemMaker;

public enum CustomItem{


	//Enumeration
	/*
	 * In Item formation we use the ItemMaker interface (specified below) to construct an anonymous class to allow easy creation of custom items
	 * 
	 */		


	WACKYSTICK("wacky",(new ItemMaker(){
		public ItemStack get(){
			ItemStack wack = new ItemStack(Material.STICK);
			ItemMeta meta = wack.getItemMeta();meta.setDisplayName(ChatColor.DARK_BLUE+"Wack Attack");
			wack.setItemMeta(meta);
			wack.addUnsafeEnchantment(Enchantment.KNOCKBACK,2);
			return wack;
		}


	}).get(),null),

	HOMERUNBAT("homer",(new ItemMaker(){
		public ItemStack get(){
			ItemStack homer = new ItemStack(Material.WOOD_SPADE);
			ItemMeta meta = homer.getItemMeta();meta.setDisplayName(ChatColor.GOLD+"Home Run Bat");
			homer.setItemMeta(meta);
			homer.addUnsafeEnchantment(Enchantment.KNOCKBACK,15);
			homer.setDurability((short)57);
			return homer;
		}
	}).get(),null),

	GRASSSPADE("grassspade",(new ItemMaker(){
		public ItemStack get(){
			ItemStack superSpade = new ItemStack(Material.DIAMOND_SPADE);
			superSpade.addUnsafeEnchantment(Enchantment.DIG_SPEED,8);
			ItemMeta meta = superSpade.getItemMeta();meta.setDisplayName(ChatColor.BOLD+"The Grass Spade");

			//Adding hidden metadata that will match up with our listener <3
			List<String>lore = new ArrayList<String>();
			lore.add("The Grass Spade");
			meta.setLore(lore);
			superSpade.setItemMeta(meta);


			return superSpade;
		}

	}).get(),new Listener(){
		//only if uses spade cancellation whatnot
		@EventHandler
		public void onBlockBreak(PlayerInteractEvent e){	
			//if uses on anything but a grass block it does nothing.
			ItemStack is = e.getItem();
			if(is==null)return;
			List<String>lore;
			if((lore = is.getItemMeta().getLore()).size()>0){
				if(lore.get(0).equals("The Grass Spade")&&e.getClickedBlock().getType()!=Material.GRASS){
					e.setCancelled(true);
				}
			}
		}
	}),
	//NOT DONE NEED TO MAKE SPECIFIC
	PEASHOOTER("peashooter",(new ItemMaker(){
		public ItemStack get(){
			ItemStack em = new ItemStack(Material.EMERALD);


			ItemMeta meta = em.getItemMeta();meta.setDisplayName(ChatColor.GREEN+"Pea Shooter");
			em.setItemMeta(meta);

			return em;
		}


	}).get(),new Listener(){
		@EventHandler
		public void onClick(PlayerInteractEvent e){
			if(e.getItem().getType()!=Material.EMERALD)return;
			if(e.getAction()==Action.RIGHT_CLICK_BLOCK){
				Player pl= e.getPlayer();
				pl.getInventory().remove(e.getItem());
				Location l = pl.getLocation();
				World w = l.getWorld();
				for(int i = 0; i < 8;i++)
					w.spawnEntity(l,EntityType.CREEPER);
			}
		}
	});

	protected static Map<String,CustomItem>names;
	private final ItemStack item;
	private final String name;
	private final Listener listener;

	//Constructor
	CustomItem(String name,ItemStack item,Listener listener){
		this.name = name;
		this.item = item;
		this.listener = listener;
	}


	//Standard gets
	public ItemStack getItem(){
		return item;
	}

	public String getName(){
		return name;
	}

	public Listener getListener(){
		return listener;
	}


	static{
		names = new HashMap<String,CustomItem>();
		for(CustomItem r: CustomItem.values()){
			names.put(r.getName(),r);
		}
	}

	public static CustomItem getItem(String name){
		return names.get(name);
	}

	public static String itemLabels(){
		StringBuffer str = new StringBuffer();

		for(CustomItem r: names.values()){
			str.append(r.getName()+" ");
		}
		return str.toString();
	}




}
