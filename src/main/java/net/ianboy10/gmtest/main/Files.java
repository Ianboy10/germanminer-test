package net.ianboy10.gmtest.main;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Files {
    public static File getConfig() {
        return new File("plugins/GM-Test", "mysql.yml");
    }

    public static FileConfiguration getConfiguration() {
        return YamlConfiguration.loadConfiguration(getConfig());
    }

    public static void setStandart() {
        FileConfiguration cfg = getConfiguration();
        cfg.options().copyDefaults(true);
        cfg.addDefault("host", "localhost");
        cfg.addDefault("port", "3306");
        cfg.addDefault("database", "database");
        cfg.addDefault("username", "root");
        cfg.addDefault("password", "password");
        try {
            cfg.save(getConfig());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readConfig() {
        FileConfiguration cfg = getConfiguration();
        MySQL.host = cfg.getString("host");
        MySQL.port = cfg.getString("port");
        MySQL.database = cfg.getString("database");
        MySQL.username = cfg.getString("username");
        MySQL.password = cfg.getString("password");
    }
}
