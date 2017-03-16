package fr.dr_blackapple.mm.menu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public enum MenuTp {
   
	Skull (Material.SKULL_ITEM, (byte)3),
	Compass (49, Material.COMPASS, "§c§LTp random", "§eCe tp aleatoirement sur un joueur");
	
	private Material mat;
	private byte data;
	private String name, lore;
	private int slot;
	
	MenuTp (Material mat, byte data){
		this.mat = mat;
		this.data = data;
	}
	
	MenuTp (int slot, Material mat, String name, String lore){
		this.mat =mat;
		this.name= name;
		this.lore = lore;
		this.slot = slot;
	}
	
	public ItemStack doItemStack(){
	    ItemStack is = new ItemStack(this.mat, 1);
	    ItemMeta im = is.getItemMeta();
	    im.setDisplayName(this.name);
	    List<String> loreList = new ArrayList<String>();
	    loreList.add(this.lore);
	    im.setLore(loreList);
	    is.setItemMeta(im);
	    return is;
	}
	
	public ItemStack doSkull(Player p){
	    ItemStack skull = new ItemStack(this.mat, 1, data);
    	SkullMeta sm = (SkullMeta)skull.getItemMeta();
    	sm.setOwner(p.getName());
    	sm.setDisplayName("§e§L" + p.getName());
    	skull.setItemMeta(sm);
	    return skull;
	}
	
	public int getSlot() {
		return slot;
	}
}
