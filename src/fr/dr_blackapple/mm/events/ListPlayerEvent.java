package fr.dr_blackapple.mm.events;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.dr_blackapple.mm.Main;
import fr.dr_blackapple.mm.menu.MenuItems;
import fr.dr_blackapple.mm.menu.MenuTp;

public class ListPlayerEvent implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		if(!e.getPlayer().hasPermission("moderator.staff") && !MainEvents.nonStaff.contains(e.getPlayer())) {
			MainEvents.nonStaff.add(e.getPlayer());
		}
	}
    
	@EventHandler
	public void onListPlayer(PlayerInteractEvent e){
		if(Main.openedInv.contains(e.getPlayer()) && e.getItem() != null) {
			if(e.getItem().equals(MenuItems.Tp.doItemStack())) {			
				if((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) ) {
					
					e.setCancelled(true);
					
					Player p = e.getPlayer();
				    Inventory inv = Bukkit.createInventory(null, 54, "§2§LJoueurs connectés");	    
				    
				    inv.setItem(MenuTp.Compass.getSlot(), MenuTp.Compass.doItemStack());
				    
				    if(!MainEvents.nonStaff.isEmpty()) {
					    for(int i=0; i < MainEvents.nonStaff.size(); i++){
					    	inv.addItem(MenuTp.Skull.doSkull(MainEvents.nonStaff.get(i)));
					    }
				    }
				    
				    p.openInventory(inv);
				    
				}
			}
		}
	}
	
	@EventHandler
	public void onClickInventory(InventoryClickEvent e){    
		if((e.getClick().equals(ClickType.RIGHT) || e.getClick().equals(ClickType.LEFT)) 
			&& e.getClickedInventory().contains(MenuTp.Compass.doItemStack()) && e.getClickedInventory().getName() == "§2§LJoueurs connectés") {
			
			e.setCancelled(true);
			if(!MainEvents.nonStaff.isEmpty()){
				if(e.getCurrentItem().getType().equals(new ItemStack(Material.SKULL_ITEM, 1, (byte)3).getType())){
					ItemStack skull = e.getCurrentItem();
					Player target = Bukkit.getPlayer(skull.getItemMeta().getDisplayName().replace("§e§L", ""));
					e.getWhoClicked().sendMessage("§6Téléportation...");
					e.getWhoClicked().teleport(target);
				}
				else if(e.getCurrentItem().equals(MenuTp.Compass.doItemStack())){					
                	Random r = new Random();
                	e.getWhoClicked().sendMessage("§6Téléportation...");
                	e.getWhoClicked().teleport(MainEvents.nonStaff.get(r.nextInt(MainEvents.nonStaff.size() - 0)));
                }
			}
		}
	}
	
	@EventHandler
	public void onDisconnect(PlayerQuitEvent e){
		if(MainEvents.nonStaff.contains(e.getPlayer())){
            Main.menu.invDel(e.getPlayer().getInventory());
            MainEvents.nonStaff.remove(e.getPlayer());
		}
	}
}
