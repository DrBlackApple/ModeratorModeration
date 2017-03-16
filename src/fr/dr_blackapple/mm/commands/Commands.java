package fr.dr_blackapple.mm.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.dr_blackapple.mm.Main;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
        	Player player = (Player)sender;
        	if(Main.openedInv.contains(player)) {
        		player.sendMessage(ChatColor.GOLD+ "Fermeture du menu...");
                Main.menu.invDel(player.getInventory());
                Main.openedInv.remove(player);
        	}
        	else {
        		player.sendMessage(ChatColor.GOLD+ "Ouverture du menu...");
        		Main.menu.invSet(player.getInventory(), player);
        		Main.openedInv.add(player);
        	}
        	
        	
        }
        else {
        	sender.sendMessage(ChatColor.RED + "Il faut être un joueur pour avoir les objets !");
        }
		return false;
	}

}
