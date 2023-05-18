package com.kalle.mobarmor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArmorManager {

    private static Map<Player, ArmorManager> map = new HashMap<>();

    private Player player;
    private int taskID;
    private Map<ArmorSet, Integer> sets;

    public ArmorManager(Player player) {
        this.player = player;
        startListening();
    }

    public static ArmorManager getInstance(Player player) {
        return map.get(player);
    }

    private void resetSets() {
        sets = new HashMap<>();
    }

    private void addSet(ArmorSet set) {
        if (set == null) return;
        if (sets.containsKey(set)) {
            sets.replace(set, sets.get(set) + 1);
            return;
        }
        sets.put(set, 1);
    }

    private void startListening() {
        map.put(player, this);
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(MobArmor.getPlugin(), () -> {
            PlayerInventory inv = player.getInventory();
            resetSets();
            addSet(ArmorSet.getSetByHelmet(inv.getHelmet()));
            addSet(ArmorSet.getSetByChest(inv.getChestplate()));
            addSet(ArmorSet.getSetByLeggings(inv.getLeggings()));
            addSet(ArmorSet.getSetByBoots(inv.getBoots()));
            List<PotionEffectType> activeEffects = new ArrayList<>();
            for (PotionEffect i : player.getActivePotionEffects()) {
                activeEffects.add(i.getType());
            }
            for (ArmorSet i : sets.keySet()) {
                for (PotionEffectType j : i.getEffects(sets.get(i))) {
                    if (activeEffects.contains(j)) {
                        player.removePotionEffect(j);
                    }
                    player.addPotionEffect(new PotionEffect(j,100,0));
                }
            }
        }, 0L, 20L); //task starts instantly (0L) and repeats every tick (20L)
    }

    public void stopListening() {
        map.remove(player);
        Bukkit.getScheduler().cancelTask(taskID);
    }


}
