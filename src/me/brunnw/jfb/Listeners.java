package me.brunnw.jfb;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listeners implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		Main.jp.getConfig().set("Ultimo-jogador.Nome", p.getName());
		Main.jp.saveConfig();
		p.sendMessage("§aBem vindo, §c" + p.getName() + "§a, ao servidor " + Main.nome_do_servidor + "!");
		
		e.setJoinMessage("");
	
	}
	
	

}
