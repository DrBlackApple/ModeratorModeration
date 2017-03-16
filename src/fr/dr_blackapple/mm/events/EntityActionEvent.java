package fr.dr_blackapple.mm.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.dr_blackapple.mm.menu.MenuItems;

public class EntityActionEvent implements Listener {
	
	@EventHandler
	public void onKillOrKick(PlayerInteractEntityEvent e){
		if(e.getPlayer().getItemInHand().equals(MenuItems.Kick.doItemStack()) || e.getPlayer().getItemInHand().equals(MenuItems.Kill.doItemStack())) {
			e.setCancelled(true);
			if(e.getRightClicked() instanceof Player) {
				Player target = (Player)e.getRightClicked();
				if(e.getPlayer().getItemInHand().equals(MenuItems.Kick.doItemStack())){
					target.kickPlayer("§4Vous avez été kick !");
					e.getPlayer().sendMessage("§2Vous avez bien kick §6" + target.getName());
				} else if (e.getPlayer().getItemInHand().equals(MenuItems.Kill.doItemStack())){
					target.damage(1000.000000);
					e.getPlayer().sendMessage("§2Vous avez bien tué §6" + target.getName());
				}
			}
		}
	}
	
	@EventHandler
	public void backAction(PlayerInteractEvent e){
		if(e.getPlayer().getItemInHand().equals(MenuItems.Kill.doItemStack()) || e.getPlayer().getItemInHand().equals(MenuItems.Kick.doItemStack())) {
			e.setCancelled(true);
		}
	}

}
