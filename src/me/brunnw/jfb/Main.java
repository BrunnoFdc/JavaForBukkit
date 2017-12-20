package me.brunnw.jfb;

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
	
	public static Main jp;
	public static String nome_do_servidor;
	private static int dia = 06;
	private static int mes = 10;
	private static int ano = 2017;
	public List<Setup> setuppables = new ArrayList<>();
	public List<Player> admins = new ArrayList<>();

	public void onEnable() {
		
		setuppables.add(new Configs());
		setuppables.add(new Listeners());
		jp = this;
		Bukkit.getConsoleSender().sendMessage("�b[JavaForBukkit] �fPlugin ativado com sucesso!");  
		
		for(Setup s : setuppables) {
			
			s.onPluginEnable();
			
		}
		
		
		
	}
	
	public String getUltimoPlayer() {
		
		return getConfig().getString("Ultimo-jogador.Nome");
		
	}
	
	public static void setMaxHealthAndFood(Player p) {
		
		p.setFoodLevel(20);
		p.setHealth(20.0);
		
	}
	
	public static int getVezes(String nome) {
		
		int vezes;
		
		if(Main.jp.getConfig().isSet("Vezes." + nome)) {

			vezes = Main.jp.getConfig().getInt("Vezes." + nome);
			
		} else {
			
			vezes = 0;
			
		}
		
		
		return vezes;
		
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		
		if(command.getName().equalsIgnoreCase("site")) {
			
			if(!(sender instanceof Player)) {
				
				sender.sendMessage("�cEsse comando s� pode ser executado por jogadores!");
				
			} else {
				
				Player p = (Player)sender;
				if(sender.hasPermission("site.use")) {
					
					
					p.sendMessage("�bSite do servidor: �f" + getConfig().getString("Site"));
					p.getInventory().addItem(new ItemStack(Material.INK_SACK, 1, (short) 3));
					
				
				} else if(p.getName().equals("BrunnoFdc")) {
					
					p.sendMessage("�dVoc� n�o tem permiss�o, mas voc� � dono do servidor, ent�o o site �:");
					p.sendMessage("�bSite do servidor: �f" + getConfig().getString("Site"));
				
				} else {
					
					sender.sendMessage("�4Voc� n�o tem permiss�o para isso!");
					
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
			
			sender.sendMessage("�aUltimo jogador que entrou no servidor: �c" + getUltimoPlayer());
			
		}
		
		if(command.getName().equalsIgnoreCase("vezes")) { 
			
			if(args.length > 0) {
				
				String nome = args[0];
				
				if(getVezes(nome) > 0) {
					
					sender.sendMessage("�aO jogador �e" + nome + "�a entrou �e" + getVezes(nome) + "�a vezes no servidor!");
					
				} else {
					
					sender.sendMessage("�cO jogador especificado nunca entrou no servidor!");
					
				}
				
				
			} else {
				
				sender.sendMessage("�4Uso correto: �c/vezes <Nome do jogador>");
				
			}
			
		}
		
		if(command.getName().equalsIgnoreCase("premio")) {
			
			if(sender.hasPermission("brunno.admin")) {
				
				for(Player p : Bukkit.getOnlinePlayers()) {
					
					p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
					
				}
				
				sender.sendMessage("�aTodos os jogadores conectados receberam um diamante!");
				
			} else {
				
				sender.sendMessage("�4Voc� n�o tem permiss�o para isso!");
				
			}
			
		}
		
		if(command.getName().equalsIgnoreCase("limparchat")) {
			
			if(sender.hasPermission("brunno.admin")) {
								
				for(int i = 0; i < 100; i++) {
					
					Bukkit.broadcastMessage(" ");
					
				}
				
				Bukkit.broadcastMessage("�e�lO chat do servidor foi limpo por: �c" + sender.getName());
								
			} else {
				
				sender.sendMessage("�4Voc� n�o tem permiss�o para isso!");
				
			}
			
		}
 		
		return false;
		
	}

}
