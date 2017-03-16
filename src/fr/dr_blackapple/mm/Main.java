package fr.dr_blackapple.mm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.dr_blackapple.mm.commands.CommandReport;
import fr.dr_blackapple.mm.commands.Commands;
import fr.dr_blackapple.mm.events.MainEvents;
import fr.dr_blackapple.mm.menu.Menu;

public class Main extends JavaPlugin{
	
	public static List<Player> openedInv = new ArrayList<Player>();
	public static Menu menu = new Menu();
	
	static FileConfiguration config;
	
	PluginManager pm;
	
	public void onEnable() {
		pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new MainEvents(pm, this), this);
		
		configure();
		
		getCommand("mm").setExecutor(new Commands());
		getCommand("panel").setExecutor(new Commands());
		getCommand("report").setExecutor(new CommandReport());
		
		System.out.println("La moderation est activé !");
	}
	
	private void configure() {
		File f = new File("plugins//moderator_moderation//config.yml");
		if(!f.exists()){
			saveDefaultConfig();
			saveConfig();
		}
		
		config = getConfig();
	}
	
	public static List<String> getReports(){
		if (config.getStringList("reports") != null)
		   return config.getStringList("reports");
		
		return new ArrayList<String>();
	}

	public void onDisable(){
		
		for(int i=0; i<openedInv.size(); i++){
			menu.invDel(openedInv.get(i).getInventory());
		}
		
		System.out.println("La moderation est desactivé !");
	}

	public static void setReports(List<String> reports) {
		
		if(reports.size() >= 54) {
			reports.remove(0);
		}
		
		config.set("reports", reports);
		
		try {
			config.save("plugins//moderator_moderation//config.yml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
