package net.evatunaanvil.salkcoding.main;

import net.evatunaanvil.salkcoding.Constants;
import net.evatunaanvil.salkcoding.Limit;
import net.evatunaanvil.salkcoding.Probability;
import net.evatunaanvil.salkcoding.command.AdminCommand;
import net.evatunaanvil.salkcoding.event.AnvilEvent;
import net.evatunaanvil.salkcoding.event.BlockBreak;
import net.evatunaanvil.salkcoding.event.Fishing;
import net.evatunaanvil.salkcoding.event.PlayerBow;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Main extends JavaPlugin {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    public Main() {
        if (instance != null)
            throw new IllegalStateException();
        instance = this;
    }

    @Override
    public void onEnable() {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new AnvilEvent(), this);
        manager.registerEvents(new BlockBreak(), this);
        manager.registerEvents(new Fishing(), this);
        manager.registerEvents(new PlayerBow(), this);
        getCommand("EvaTunaAnvil").setExecutor(new AdminCommand());
        try {
            Probability.load();
            Limit.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Constants.Console_Format + "Plugin is now working");
    }

    @Override
    public void onDisable() {
        try {
            Probability.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Constants.Console_Format + "Plugin is disabled");
    }

}
