package me.brunnofdc.javaforbukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.ChatColor.translateAlternateColorCodes;
import static org.bukkit.Material.DIAMOND;

public class JavaForBukkit extends JavaPlugin {

    public void onEnable() {
        getLogger().info("Plugin Inicilizado!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if(command.getName().equalsIgnoreCase("exemplo")) {
            if(player.hasPermission("javaforbukkit.exemplo")) {
                player.sendMessage(translateAlternateColorCodes('&', "&aOlá jogador!"));
                player.getInventory().addItem(new ItemStack(DIAMOND));
            } else {
                player.sendMessage("§4Você não tem permissão para isso!");
            }
        }

        return true;
    }
}
