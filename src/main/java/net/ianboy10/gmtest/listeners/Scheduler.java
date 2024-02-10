package net.ianboy10.gmtest.listeners;

import net.ianboy10.gmtest.main.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Scheduler extends BukkitRunnable {
    private final JavaPlugin plugin;

    public Scheduler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            Map<String, Integer> counts = Data.counts;
            for (Player player : Bukkit.getOnlinePlayers()) {
                Inventory inventory = player.getInventory();

                for (ItemStack item : inventory.getContents()) {
                    if (item != null && item.hasItemMeta() && item.getItemMeta().hasLore()) {
                        ItemMeta itemMeta = item.getItemMeta();
                        for (String lore : itemMeta.getLore()) {
                            if (lore.contains("§8Seriennummer: ")) {
                                String sn = lore.replace("§8Seriennummer: ", "");
                                if (Data.sn().contains(sn)) {
                                    if (counts.containsKey(sn)) {
                                        counts.put(sn, counts.get(sn) + 1);
                                    } else {
                                        counts.put(sn, 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for (Map.Entry<String, Integer> entry : counts.entrySet()) {
                String lore = entry.getKey();
                int count = entry.getValue();
                if (count > 1) {
                    System.out.println("Es gibt " + count + " Gegenstände im Inventar mit der Lore: " + lore);
                    Bukkit.broadcastMessage("Es gibt " + count + " Gegenstände im Umlauf mit der Lore: " + lore);
                }
            }
        });
    }

    public void startTask() {
        this.runTaskTimer(plugin, 0, 5 * 60 * 20); // 10 Sekunden in Ticks (20 Ticks pro Sekunde)
        System.out.println("Scheduler started");
    }
}
