package ga.brunnofdc.jfb;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class Main extends JavaPlugin implements Listener {
	
	ItemStack item1;
	ItemStack item2;
	ItemStack balde;
	
	ItemMeta meta1;
	ItemMeta meta2;
	ItemMeta mbalde;
	
	public void onEnable() {
		
		Bukkit.getPluginManager().registerEvents(this, this);
		
	}
	
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player p = (Player)sender;
		if(command.getName().equalsIgnoreCase("abrirgui")) {
			
			Inventory inv = Bukkit.createInventory(p, 27, "§bMenu GUI!");
			
			item1 = new ItemStack(Material.DIAMOND_SWORD);
			item1.addEnchantment(Enchantment.DAMAGE_ALL, 1);
			meta1 = item1.getItemMeta();
			meta1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item1.setItemMeta(meta1);
			
			item2 = new ItemStack(Material.APPLE);
			item2.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
			meta2 = item2.getItemMeta();
			meta2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item2.setItemMeta(meta2);
			
			inv.setItem(12, item1);
			inv.setItem(14, item2);
			
			p.openInventory(inv);
			
		}
		
		if(command.getName().equalsIgnoreCase("pegarbalde")) {
			
			balde = new ItemStack(Material.WATER_BUCKET);
			mbalde = balde.getItemMeta();
			mbalde.setDisplayName("§dBalde Magico!");
			balde.addUnsafeEnchantment(Enchantment.LUCK, 5);
			balde.setItemMeta(mbalde);
			
			p.getInventory().addItem(balde);
			
		}
		
		return false;
		
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		
		Inventory inv = e.getInventory();
		Player p = (Player)e.getWhoClicked();
		
		if(inv.getName().equalsIgnoreCase("§bMenu GUI!")) {
			
			if(e.getCurrentItem().isSimilar(item1)) {
				
				p.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
				p.getInventory().addItem(new ItemStack(Material.DIAMOND_HELMET));
				p.getInventory().addItem(new ItemStack(Material.DIAMOND_CHESTPLATE));
				p.getInventory().addItem(new ItemStack(Material.DIAMOND_LEGGINGS));
				p.getInventory().addItem(new ItemStack(Material.DIAMOND_BOOTS));
				p.closeInventory();
				
			} else if(e.getCurrentItem().isSimilar(item2)) {
				
				p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 64));
				p.closeInventory();
				
			}
			
			e.setCancelled(true);
			
		}
		
		
	}
	
	@EventHandler
	public void onClickSign(PlayerInteractEvent e) {
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.WALL_SIGN) {
			
			Sign s = (Sign)e.getClickedBlock().getState();
			Player p = e.getPlayer();
			if(s.getLine(0).equalsIgnoreCase("[Terreno]")) {
				
				p.sendMessage("§aVocê está no terreno de §6" + s.getLine(1));
				p.getWorld().setDifficulty(Difficulty.PEACEFUL);
			
			}
			
		}
		
	}
	
}
