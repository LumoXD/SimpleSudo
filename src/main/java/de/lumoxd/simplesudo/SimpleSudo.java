package de.lumoxd.simplesudo;

import de.lumoxd.simplesudo.commands.sudo_cmd;
import de.lumoxd.simplesudo.listener.sudo_listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleSudo extends JavaPlugin {
    public static SimpleSudo instance;
    public static final String prefix = "§aSUDO §6";

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getCommand("sudo").setExecutor(new sudo_cmd());
        getServer().getPluginManager().registerEvents(new sudo_listener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
