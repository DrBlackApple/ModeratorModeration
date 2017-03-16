package fr.dr_blackapple.mm.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.dr_blackapple.mm.menu.MenuItems;

public class FreezeEvents implements Listener {
	
	List<Player> freezed = new ArrayList<Player>();

	@EventHandler
	public void onFreeze(PlayerInteractEntityEvent e){
		if(e.getPlayer().getItemInHand().equals(MenuItems.Freeze.doItemStack())) {
			e.setCancelled(true);
			if(e.getRightClicked() instanceof Player) {
				e.setCancelled(true);
				Player target = (Player)e.getRightClicked();
				if(freezed.contains(target)){
					freezed.remove(target);
					e.getPlayer().sendMessage("§b[Freeze] §6Vous avez défreeze " + target.getName());
				}else {
					freezed.add(target);
					e.getPlayer().sendMessage("§b[Freeze] §6Vous avez freeze " + target.getName());
				}
			}
		}
	}
	
	@EventHandler
	public void backAction(PlayerInteractEvent e){
		if(e.getPlayer().getItemInHand().equals(MenuItems.Freeze.doItemStack())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if(freezed.contains(e.getPlayer())){
			e.setCancelled(true);
		}
	}
}
