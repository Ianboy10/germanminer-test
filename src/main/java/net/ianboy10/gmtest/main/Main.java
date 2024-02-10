package net.ianboy10.gmtest.main;

import net.ianboy10.gmtest.commands.CheckSeriennummerCommand;
import net.ianboy10.gmtest.commands.SetSeriennummerCommand;
import net.ianboy10.gmtest.listeners.Scheduler;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Files.setStandart();
        Files.readConfig();
        MySQL.connect();

        new Scheduler(this).startTask();

        getCommand("setsn").setExecutor(new SetSeriennummerCommand());
        getCommand("checksn").setExecutor(new CheckSeriennummerCommand());
    }

    @Override
    public void onDisable() {
    }
}
