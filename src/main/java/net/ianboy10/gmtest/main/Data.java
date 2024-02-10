package net.ianboy10.gmtest.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Data {
    public static List<String> sn = new ArrayList<>();
    public static HashMap<String, Integer> counts = new HashMap<>();
    public static HashMap<String, Long> created = new HashMap<>();
    public static HashMap<String, UUID> uuid = new HashMap<>();

    public static void created() {
        ResultSet rs = MySQL.getResult("SELECT * FROM `gm`");
        try {
            while (rs != null && rs.next()) {
                created.put(rs.getString("seriennummer"), rs.getLong("created"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void user() {
        ResultSet rs = MySQL.getResult("SELECT * FROM `gm`");
        try {
            while (rs != null && rs.next()) {
                uuid.put(rs.getString("seriennummer"), UUID.fromString(rs.getString("uuid")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> sn() {
        ResultSet rs = MySQL.getResult("SELECT * FROM `gm`");
        try {
            while (rs != null && rs.next()) {
                sn.add(rs.getString("seriennummer"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sn;
    }
}