package fr.dr_blackapple.mm.menu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum MenuItems {
	
	Freeze (0, Material.ICE, "§bFreeze", "§eFreeze le joueur ciblé"),
	Tp (1, Material.NETHER_STAR, "§6Tp", "§eListe les joueurs\n§econnectés, ce tp en\n§ecliquant sur leur\n§etête."),
	Vanished (2, Material.EYE_OF_ENDER, "§0Vanished", "§eEnlever le vanish", true),
	Vanish (2, Material.ENDER_PEARL, "§0Vanish", "§eCe mettre en vanish"),
	Kick(3, Material.BLAZE_ROD, "§cKick", "§eKick le joueur ciblé"),
	Kill(4, Material.STICK, "§9Kill", "§eTue le joueur ciblé"),
	Report(5, Material.PAPER, "§6Report", "§eVoir les reports"),
	KnockBack(6, Material.WATCH, "§2KnockBack", "§eFaire un test de\n§eknockback sur le joueur\n§eciblé"),
	InvSee(7, Material.CHEST, "§6Inventaire", "§eVoir l'inventaire\n§edu joueur et d'autre\n§einformation");
	
	private int slot;
	private Material mat;
	private String nom,lore;
	
	MenuItems(int slot, Material mat,String nom, String lore) {
		this.slot = slot;
		this.mat = mat;
		this.nom = nom;
		this.lore = lore;
	}
	
	MenuItems(int slot, Material mat,String nom, String lore, boolean vanish) {
		this.slot = slot;
		this.mat = mat;
		this.nom = nom;
		this.lore = lore;
	}
	
	public ItemStack doItemStack(){
	    ItemStack is = new ItemStack(mat, 1);
	    ItemMeta im = is.getItemMeta();
	    im.setDisplayName(nom);
	    List<String> loreList = new ArrayList<String>();
	    for (String temp : lore.split("\n")) {
	    	loreList.add(temp);
	    }
	    im.setLore(loreList);
	    is.setItemMeta(im);
	    return is;
	}
	
	public int getSlot() {
		return slot;
	}

}
