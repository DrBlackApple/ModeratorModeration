package fr.dr_blackapple.mm.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.dr_blackapple.mm.Main;
import fr.dr_blackapple.mm.menu.MenuItems;

public class ReportSeeEvent implements Listener {
	
	@EventHandler
	public void onClickPaper(PlayerInteractEvent e){
		if(Main.openedInv.contains(e.getPlayer()) && e.getItem() != null){
			if(e.getItem().equals(MenuItems.Report.doItemStack())){
				e.setCancelled(true);
				
				Inventory inv = Bukkit.createInventory(null, 54, "§b§LListe des reports :");
				
				List<String> reports = Main.getReports();
				
				if(!reports.isEmpty()){
				
					for(int i=0; i< reports.size(); i++){
						List<String> lore = new ArrayList<String>();
						ItemStack paper = new ItemStack(Material.PAPER, 1);
						ItemMeta paperMeta = paper.getItemMeta();
						String report[] = reports.get(i).split(" / ");
					    paperMeta.setDisplayName("§3" + report[2]);
					    
					    lore.add("§2 a reporté §c" + report[1] + " §2pour");
					    lore.add(" §6" + report[0]);
					    
					    paperMeta.setLore(lore);
					    paper.setItemMeta(paperMeta);
					    
					    inv.addItem(paper);
					}		
			    }
				e.getPlayer().openInventory(inv);
			}
		}
	}

}
