package me.raydond123.nofalldamageregions;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NoFallDamageRegions extends JavaPlugin {

    WorldGuardPlugin worldGuardPlugin;
    AddRegionCommand addRegionCommand;
    PlayerFallDamageListener playerFallDamageListener;

    public String prefix = ChatColor.translateAlternateColorCodes('&', "&9[&5NoFallDamageFlag&9] ");

    public void onEnable() {

        saveConfig();

        worldGuardPlugin = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");

        addRegionCommand = new AddRegionCommand(this);
        playerFallDamageListener = new PlayerFallDamageListener(this);

        getCommand("addregion").setExecutor(addRegionCommand);

        Bukkit.getPluginManager().registerEvents(playerFallDamageListener, this);

    }

    public void onDisable() {

        worldGuardPlugin = null;
        playerFallDamageListener = null;
        addRegionCommand = null;

    }

}
