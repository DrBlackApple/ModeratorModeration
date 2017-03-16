package fr.dr_blackapple.mm.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;

import fr.dr_blackapple.mm.Main;
import fr.dr_blackapple.mm.menu.MenuItems;

public class MainEvents implements Listener {
	
	public static List<Player> nonStaff = new ArrayList<Player>();
    
	public MainEvents(PluginManager pm, Main main){
		pm.registerEvents(new FreezeEvents(), main);
		pm.registerEvents(new ListPlayerEvent(), main);
		pm.registerEvents(new EntityActionEvent(), main);
		pm.registerEvents(new VanishEvent(), main);
		pm.registerEvents(new InvSeeEvents(), main);
		pm.registerEvents(new ReportSeeEvent(), main);
	}

	@EventHandler
	public void onClickItemMod(InventoryClickEvent e){
	    MenuItems mi[] = MenuItems.values();
	    for(MenuItems im : mi){
	    	if(e.getSlot() == im.getSlot() && Main.openedInv.contains(e.getWhoClicked()) && e.getClickedInventory().getType() == InventoryType.PLAYER) {
	    		e.setCancelled(true);
	    	}
	    }
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e){
    	if(Main.openedInv.contains(e.getPlayer())) {
    		e.setCancelled(true);
    	}
	}
	
	/*@EventHandler
	public void onInteractInMod(PlayerInteractEvent e){
		if(Main.openedInv.contains(e.getPlayer())) {
			e.setCancelled(true);
		}
	}*/
	@EventHandler
	public void onDisconnect(PlayerQuitEvent e){
		if(Main.openedInv.contains(e.getPlayer())){
            Main.menu.invDel(e.getPlayer().getInventory());
            Main.openedInv.remove(e.getPlayer());
		}
	}
}
