package fr.dr_blackapple.mm.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.dr_blackapple.mm.menu.MenuItems;

public class VanishEvent implements Listener {

	public static List<Player> vanished = new ArrayList<Player>();
	
	@EventHandler
	public void onVanish(PlayerInteractEvent e){
		if((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)){
			if(e.getItem().equals(MenuItems.Vanish.doItemStack()) || e.getItem().equals(MenuItems.Vanished.doItemStack())){
				e.setCancelled(true);
				Player p = e.getPlayer();
				if (vanished.contains(p)){
					p.getInventory().setItem(MenuItems.Vanish.getSlot(), MenuItems.Vanish.doItemStack());
					p.sendMessage("§6Vous êtes visible !");
					p.removePotionEffect(PotionEffectType.INVISIBILITY);
					p.setDisplayName(p.getCustomName());
					p.setCustomNameVisible(true);
					vanished.remove(p);
				} else {
					p.getInventory().setItem(MenuItems.Vanished.getSlot(), MenuItems.Vanished.doItemStack());
					p.sendMessage("§6Vous êtes invisible !");
					p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000000, 1));
					p.setDisplayName("");
					p.setCustomNameVisible(false);
					vanished.add(p);
				}
			}
		}
	}
}
