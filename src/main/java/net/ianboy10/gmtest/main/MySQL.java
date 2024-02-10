package net.ianboy10.gmtest.main;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {
    public static String host;
    public static String port;
    public static String database;
    public static String username;
    public static String password;
    public static Connection con;

    public static void connect() {
        if (!isCon()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                Bukkit.getConsoleSender().sendMessage("§aDie Verbindung zur MySQL Datenbank wurde hergestellt!");

                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS `gm` (id int AUTO_INCREMENT, uuid VARCHAR(255), time LONG, seriennummer VARCHAR(255), PRIMARY KEY (id))");
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("§cÜberprüfe die MySQL Zugangsdaten!");
            }
        }
    }

    public static void disconnect() {
        if(isCon()) {
            try {
                con.close();
                Bukkit.getConsoleSender().sendMessage("§cDie Verbindung zur MySQL Datenbank wurde getrennt!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static boolean isCon() {
        return con != null;
    }

    public static ResultSet getResult(String qry) {
        if(!isCon()) return null;
        try {
            return con.createStatement().executeQuery(qry);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(String qry) {
        if(!isCon()) return;
        try {
            con.createStatement().executeUpdate(qry);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
