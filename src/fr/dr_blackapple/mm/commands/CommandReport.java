package fr.dr_blackapple.mm.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.dr_blackapple.mm.Main;

public class CommandReport implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(args.length <= 1){
				cmd.setUsage("§b/report <player> <raison>");
			} else if(args.length >= 2){
				Player reported;
				if((reported = Bukkit.getPlayer(args[0])) != null){
					
					List<String> reports = Main.getReports();
					
					String msg = "";
					for(int i=0; i<args.length - 1; i++) {
						msg += args[i+1] + " ";
					}
					p.sendMessage("§6Vous avez bien reporté §4" + reported.getName() + " §6pour §2" + msg);
					msg += "/ " + reported.getName() + " / " + p.getName();
					reports.add(msg);
					Main.setReports(reports);
				} else {
					p.sendMessage("§c§lLe joueur est introuvable ou n'est pas connecté");
				}
				return true;
			}
		}
		
		return false;
	}

}
