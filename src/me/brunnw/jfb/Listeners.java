package me.brunnw.jfb;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class Listeners implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		Main.jp.getConfig().set("Ultimo-jogador.Nome", p.getName());
		Main.jp.saveConfig();
		p.sendMessage("§aBem vindo, §c" + p.getName() + "§a, ao servidor " + Main.nome_do_servidor + "!");
		
		if(Main.jp.getConfig().isSet("Vezes." + p.getName())) {
			
			int vezes = Main.jp.getConfig().getInt("Vezes." + p.getName());
			vezes++;
			
			Main.jp.getConfig().set("Vezes." + p.getName(), vezes);
			Main.jp.saveConfig();
			
			
		} else {
			
			Main.jp.getConfig().set("Vezes." + p.getName(), 1);
			Main.jp.saveConfig();
			
		}
		
		
		e.setJoinMessage("");
	
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		
		Block b = e.getBlock();
		ItemStack item = b.getDrops().stream().findFirst().get(); 

		
	}
	
	

}
