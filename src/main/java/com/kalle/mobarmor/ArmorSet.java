package com.kalle.mobarmor;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class ArmorSet {

    private static List<ArmorSet> sets = new ArrayList<>();

    private String name;
    private Color color;
    private ChatColor chatColor;
    private PotionEffectType[] effects;
    private Material ingredient;
    private List<String> lore;
    private ItemStack helmet;
    private ItemStack chest;
    private ItemStack leggings;
    private ItemStack boots;

    public ArmorSet(String name, Color color, ChatColor chatColor, PotionEffectType[] effects, Material ingredient, List<String> lore) {
        this.name = name;
        this.color = color;
        this.chatColor = chatColor;
        this.effects = effects;
        this.ingredient = ingredient;
        this.lore = lore;

        sets.add(this);

        ShapedRecipe upperHelmetRecipe = new ShapedRecipe(getHelmet());
        upperHelmetRecipe.shape("iii", "i i", "   ");
        upperHelmetRecipe.setIngredient('i', ingredient);
        MobArmor.getPlugin().getServer().addRecipe(upperHelmetRecipe);

        ShapedRecipe lowerHelmetRecipe = new ShapedRecipe(getHelmet());
        lowerHelmetRecipe.shape("   ", "iii", "i i");
        lowerHelmetRecipe.setIngredient('i', ingredient);
        MobArmor.getPlugin().getServer().addRecipe(lowerHelmetRecipe);

        ShapedRecipe chestRecipe = new ShapedRecipe(getChest());
        chestRecipe.shape("i i", "iii", "iii");
        chestRecipe.setIngredient('i', ingredient);
        MobArmor.getPlugin().getServer().addRecipe(chestRecipe);

        ShapedRecipe leggingsRecipe = new ShapedRecipe(getLeggings());
        leggingsRecipe.shape("iii", "i i", "i i");
        leggingsRecipe.setIngredient('i', ingredient);
        MobArmor.getPlugin().getServer().addRecipe(leggingsRecipe);

        ShapedRecipe upperBootsRecipe = new ShapedRecipe(getBoots());
        upperBootsRecipe.shape("i i", "i i", "   ");
        upperBootsRecipe.setIngredient('i', ingredient);
        MobArmor.getPlugin().getServer().addRecipe(upperBootsRecipe);

        ShapedRecipe lowerBootsRecipe = new ShapedRecipe(getBoots());
        lowerBootsRecipe.shape("   ", "i i", "i i");
        lowerBootsRecipe.setIngredient('i', ingredient);
        MobArmor.getPlugin().getServer().addRecipe(lowerBootsRecipe);
    }

    public static ArmorSet getSetByHelmet(ItemStack helmet) {
        if (helmet == null) return null;
        for (ArmorSet i : sets) {
            if (i.getHelmet().isSimilar(helmet)) return i;
        }
        return null;
    }

    public static ArmorSet getSetByChest(ItemStack chest) {
        if (chest == null) return null;
        for (ArmorSet i : sets) {
            if (i.getChest().isSimilar(chest)) return i;
        }
        return null;
    }

    public static ArmorSet getSetByLeggings(ItemStack leggings) {
        if (leggings == null) return null;
        for (ArmorSet i : sets) {
            if (i.getLeggings().isSimilar(leggings)) return i;
        }
        return null;
    }

    public static ArmorSet getSetByBoots(ItemStack boots) {
        if (boots == null) return null;
        for (ArmorSet i : sets) {
            if (i.getBoots().isSimilar(boots)) return i;
        }
        return null;
    }

    public ItemStack getHelmet() {
        if (helmet == null) {
            helmet = new ItemStack(Material.LEATHER_HELMET);
            LeatherArmorMeta meta = (LeatherArmorMeta) helmet.getItemMeta();
            meta.setColor(color);
            meta.setLore(lore);
            meta.setDisplayName(chatColor + name + " Mob Helmet");
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
            meta.spigot().setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            helmet.setItemMeta(meta);
        }
        return helmet;
    }

    public ItemStack getChest() {
        if (chest == null) {
            chest = new ItemStack(Material.LEATHER_CHESTPLATE);
            LeatherArmorMeta meta = (LeatherArmorMeta) chest.getItemMeta();
            meta.setColor(color);
            meta.setLore(lore);
            meta.setDisplayName(chatColor + name + " Mob Chestplate");
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
            meta.spigot().setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            chest.setItemMeta(meta);
        }
        return chest;
    }

    public ItemStack getLeggings() {
        if (leggings == null) {
            leggings = new ItemStack(Material.LEATHER_LEGGINGS);
            LeatherArmorMeta meta = (LeatherArmorMeta) leggings.getItemMeta();
            meta.setColor(color);
            meta.setLore(lore);
            meta.setDisplayName(chatColor + name + " Mob Leggings");
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
            meta.spigot().setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            leggings.setItemMeta(meta);
        }
        return leggings;
    }

    public ItemStack getBoots() {
        if (boots == null) {
            boots = new ItemStack(Material.LEATHER_BOOTS);
            LeatherArmorMeta meta = (LeatherArmorMeta) boots.getItemMeta();
            meta.setColor(color);
            meta.setLore(lore);
            meta.setDisplayName(chatColor + name + " Mob Boots");
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
            meta.spigot().setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            boots.setItemMeta(meta);
        }
        return boots;
    }

    public List<PotionEffectType> getEffects(int range) {
        List<PotionEffectType> applied = new ArrayList<>();
        for (int i = 0; i < range; i++) {
            if (effects.length <= i) break;
            applied.add(effects[i]);
        }
        return applied;
    }

}
