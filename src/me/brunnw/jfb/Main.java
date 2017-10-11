package me.brunnw.jfb;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Main jp;
	public static String nome_do_servidor;
	private static int dia = 06;
	private static int mes = 10;
	private static int ano = 2017;
	
	
	public void onEnable() {
		
		jp = this;
		Bukkit.getConsoleSender().sendMessage("§b[JavaForBukkit] §fPlugin ativado com sucesso!");  
		Bukkit.getPluginManager().registerEvents(new Listeners(), this);
		saveDefaultConfig();
		saveConfig();
		nome_do_servidor = getConfig().getString("Informacoes.Nome-do-servidor");
		
		
	}
	
	public String getUltimoPlayer() {
		
		return getConfig().getString("Ultimo-jogador.Nome");
		
	}
	
	public static void setMaxHealthAndFood(Player p) {
		
		p.setFoodLevel(20);
		p.setHealth(20.0);
		
	}
	
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		
		if(command.getName().equalsIgnoreCase("site")) {
			
			if(!(sender instanceof Player)) {
				
				sender.sendMessage("§cEsse comando só pode ser executado por jogadores!");
				
			} else {
				
				Player p = (Player)sender;
				if(sender.hasPermission("site.use")) {
					
					
					p.sendMessage("§bSite do servidor: §f" + getConfig().getString("Site"));
					p.getInventory().addItem(new ItemStack(Material.INK_SACK, 1, (short) 3));
					
				
				} else if(p.getName().equals("BrunnoFdc")) {
					
					p.sendMessage("§dVocê não tem permissão, mas você é dono do servidor, então o site é:");
					p.sendMessage("§bSite do servidor: §f" + getConfig().getString("Site"));
				
				} else {
					
					sender.sendMessage("§4Você não tem permissão para isso!");
					
				}
				
				
			}

			
		}
		
		if(command.getName().equalsIgnoreCase("feed")) {
			
			if(sender instanceof Player) {
				Player p = (Player)sender;
				setMaxHealthAndFood(p);
				
			}
			
		}
		
		if(command.getName().equalsIgnoreCase("ultimo-jogador")) {
			
			sender.sendMessage("§aUltimo jogador que entrou no servidor: §c" + getUltimoPlayer());
			
		}
		
		return false;
		
	}

}
