package net.ianboy10.gmtest.commands;

import net.ianboy10.gmtest.main.Data;
import net.ianboy10.gmtest.main.MySQL;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Random;

public class SetSeriennummerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        if(p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType() == Material.AIR) {
            p.sendMessage("§cBitte halte ein Item in deiner Hand.");
            return false;
        }

        String sn = "";
        String abc = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder stringBuilder = new StringBuilder();
        Random rdm = new Random();
        for (int i = 0; i < 6; i++) {
            int index = rdm.nextInt(abc.length());
            stringBuilder.append(abc.charAt(index));
            sn = stringBuilder.toString();
        }

        ItemStack itemStack = p.getInventory().getItemInMainHand();
        ItemMeta itemMeta = (ItemMeta) itemStack.getItemMeta();
        itemMeta.setLore(Arrays.asList("§8Seriennummer: " + sn));
        itemMeta.setDisplayName("§bSchwert");
        itemStack.setItemMeta(itemMeta);

        p.getInventory().setItemInMainHand(itemStack);
        p.sendMessage("§aDein Item hat die Seriennummer §a§l" + sn + "§a erhalten.");

        MySQL.update("INSERT INTO `gm` (`uuid`, `time`, `seriennummer`) VALUES ('" + p.getUniqueId().toString() + "', '" + (long) System.currentTimeMillis() + "', '" + sn + "')");
        Data.sn.add(sn);
        Data.uuid.put(sn, p.getUniqueId());
        Data.created.put(sn, (long) System.currentTimeMillis());
        return false;
    }
}
