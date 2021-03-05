package de.stevenpaw.custommodelviewer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {

    //== VARIABLEN =================
    private static Main plugin;
    private static Main instance;
    public static String prefix = "§e[CMV] ";
    //------------------------------



    //== PLUGIN-INSTANZ=============
    public static Main getInstance() {
        return instance;
    }

    public static void setInstance(Main instance) {
        Main.instance = instance;
    }

    public static Main getPlugin() {
        return plugin;
    }
    //-----------------------------

    @Override
    public void onEnable() {
        setInstance(this);
        plugin = this;

        getCommand("cmv").setExecutor(new CMDcmv());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InvListener(), this);

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(prefix + "§c§ldisabled!");
    }
}
