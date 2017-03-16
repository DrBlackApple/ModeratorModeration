package fr.dr_blackapple.mm.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.dr_blackapple.mm.events.VanishEvent;

public class Menu {
   
	public Menu () {}
	
	public void invSet(Inventory inv, Player p) {
		MenuItems mi[] = MenuItems.values();
		for(int i=0; i < mi.length; i++){
			if(mi[i].equals(MenuItems.Vanish) && VanishEvent.vanished.contains(p))
				inv.setItem(MenuItems.Vanished.getSlot(), MenuItems.Vanished.doItemStack());
			else
				inv.setItem(mi[i].getSlot(), mi[i].doItemStack());
		}
	}
	
	public void invDel (Inventory inv){
		MenuItems mi[] = MenuItems.values();
		for(int i=0; i < mi.length; i++){
			inv.setItem(mi[i].getSlot(), null);
		}
	}
}
