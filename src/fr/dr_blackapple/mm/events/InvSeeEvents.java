package fr.dr_blackapple.mm.events;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
//import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import fr.dr_blackapple.mm.menu.MenuItems;

public class InvSeeEvents implements Listener {
	
	private int slotArmure[] = {38,39,40,41};
	
	private HashMap<Player, Player> openTargetInv = new HashMap<>();
   
	@EventHandler
	public void onSeeInv(PlayerInteractEntityEvent e){
		
		if (e.getPlayer().getItemInHand().equals(MenuItems.InvSee.doItemStack())){
			e.setCancelled(true);
			if(e.getRightClicked() instanceof Player){
				e.setCancelled(true);
				Player target = (Player) e.getRightClicked();
				Player p = e.getPlayer();
				
				Inventory inv = Bukkit.createInventory(null, 54, "§6§l" + target.getName());
				
				createInv(p, target, inv);
			}
	    }
	}
	
	@EventHandler
	public void backAction(PlayerInteractEvent e){
		if(e.getPlayer().getItemInHand().equals(MenuItems.InvSee.doItemStack())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onChangeItem(InventoryClickEvent e){
		if(openTargetInv.containsKey(e.getWhoClicked()) && e.getWhoClicked() instanceof Player
				&& e.getSlot() >= 0){
			
			/*Player target = openTargetInv.get(e.getWhoClicked());
			Player p = (Player) e.getWhoClicked();
			
			InventoryView inv = p.getOpenInventory();
			
			boolean itsArmor = false;
			
			if(inv.getTopInventory().getItem(slotArmure[0]) != null){
				target.getInventory().setHelmet(inv.getTopInventory().getItem(slotArmure[0]));
				itsArmor = true;
			}
			if(inv.getTopInventory().getItem(slotArmure[1]) != null){
				target.getInventory().setChestplate(inv.getTopInventory().getItem(slotArmure[1]));
				itsArmor = true;
			}
			if(inv.getTopInventory().getItem(slotArmure[2]) != null){
				target.getInventory().setLeggings(inv.getTopInventory().getItem(slotArmure[2]));
				itsArmor = true;
			}
			if(inv.getTopInventory().getItem(slotArmure[3]) != null){
				target.getInventory().setBoots(inv.getTopInventory().getItem(slotArmure[3]));
				itsArmor = true;
			}
			
			if(!itsArmor){
			    target.getInventory().setItem(e.getSlot(), p.getItemOnCursor());
			}
			
			target.updateInventory();*/
			e.setCancelled(true);
              
		}
	}

	public Inventory createInv(Player p, Player target, Inventory inv) {
		
		ItemStack items[] = target.getInventory().getContents();
		
		for(int i=0; i<items.length; i++){
			if(items[i] != null){
				inv.setItem(i, items[i]);
			}		
		}
		
		if(target.getInventory().getHelmet() != null){
			inv.setItem(slotArmure[0], target.getInventory().getHelmet());
		}
		if(target.getInventory().getChestplate() != null){
			inv.setItem(slotArmure[1], target.getInventory().getChestplate());
		}
		if(target.getInventory().getLeggings() != null){
			inv.setItem(slotArmure[2], target.getInventory().getLeggings());
		}
		if(target.getInventory().getBoots() != null){
			inv.setItem(slotArmure[3], target.getInventory().getBoots());
		}

		if(!openTargetInv.containsValue(target)){
		   openTargetInv.put(p, target);
		}
		
		p.openInventory(inv);
		
		return inv;
	}
	
	@EventHandler
	public void onCloseInv(InventoryCloseEvent e){
		if(openTargetInv.containsKey(e.getPlayer())){
			openTargetInv.remove(e.getPlayer());
		}
	}
}
