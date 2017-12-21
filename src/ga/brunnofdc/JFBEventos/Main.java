package ga.brunnofdc.JFBEventos;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	private boolean opened = false;
	List<Player> jogadores_no_evento = new ArrayList<Player>(); 
	public void onEnable() {
		
		
	}
	
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player p = (Player)sender;
		if(!(sender instanceof Player)) {
			
			return true;
			
		}
		
		if(command.getName().equalsIgnoreCase("abrirevento")) {
			
			if(p.hasPermission("jfb.admin")) {
				
				if(opened) {
					
					p.sendMessage("§4Um evento já está em andamento!");
					
				} else {
					
					opened = true;
					Bukkit.broadcastMessage(" ");
					Bukkit.broadcastMessage("§6A staff iniciou um evento!");
					Bukkit.broadcastMessage("§6Digite §e/participar §6para participar!");
					Bukkit.broadcastMessage(" ");
					
				}
				
			} else {
				
				p.sendMessage("§4Você não tem permissão!");
				
			}
		
		}
		
		if(command.getName().equalsIgnoreCase("participar")) {
			
			if(opened) {
				
				if(jogadores_no_evento.contains(p)) {
					
					p.sendMessage("§cVocê já está participando!");
					
				} else {
					
					jogadores_no_evento.add(p);
					p.sendMessage("§aVocê entrou no evento!");
					
				}
				
			} else {
				
				p.sendMessage("§cNão há um evento ocorrendo!");
				
			}
			
		}
		
		if(command.getName().equalsIgnoreCase("distribuir")) {
			
			if(p.hasPermission("jfb.admin")) {
				
				if(opened) {
					
					
					for(Player player : jogadores_no_evento) {
						
						player.getInventory().addItem(new ItemStack(Material.DIAMOND));
						
					}
					
					jogadores_no_evento.clear();
					Bukkit.broadcastMessage(" ");
					Bukkit.broadcastMessage("§6Todos os jogadores receberam um premio!");
					Bukkit.broadcastMessage("§6Obrigado por participarem do evento!");
					Bukkit.broadcastMessage(" ");
					opened = false;
					
				} else {
					
					p.sendMessage("§4Nenhum evento está em andamento!");
					
				}
				
			} else {
				
				p.sendMessage("§4Você não tem permissão!");
				
			}
			
		}
		
		
		return false;
		
	}

}
