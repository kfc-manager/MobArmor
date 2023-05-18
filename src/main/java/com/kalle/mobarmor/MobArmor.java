package com.kalle.mobarmor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public final class MobArmor extends JavaPlugin {

    private static MobArmor plugin;
    private static String pluginTag = ChatColor.DARK_PURPLE + "[MobArmor] " + ChatColor.RESET;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        String name = "Blaze";
        Color color = Color.ORANGE;
        ChatColor chatColor = ChatColor.GOLD;
        PotionEffectType[] effects = new PotionEffectType[]{PotionEffectType.FIRE_RESISTANCE, PotionEffectType.DAMAGE_RESISTANCE, PotionEffectType.REGENERATION};
        Material ingredient = Material.PAPER;
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.AQUA + "Set effects:");
        lore.add(ChatColor.DARK_AQUA + " 1 Piece " + ChatColor.WHITE + "- Fire Resistance");
        lore.add(ChatColor.DARK_AQUA + " 2 Pieces " + ChatColor.WHITE + "- Resistance I");
        lore.add(ChatColor.DARK_AQUA + " 3 Pieces " + ChatColor.WHITE + "- Regeneration");
        new ArmorSet(name, color, chatColor, effects, ingredient, lore);
        getServer().getPluginManager().registerEvents(new Events(), this); //registering events
        for (Player player : Bukkit.getOnlinePlayers()) {
            new ArmorManager(player);
        }
        getServer().getConsoleSender().sendMessage(pluginTag + ChatColor.GREEN + "plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(pluginTag + ChatColor.RED + "plugin has been disabled!");
    }

    public static MobArmor getPlugin() {
        return plugin;
    }

    public String getPluginTag() {
        return pluginTag;
    }
}
