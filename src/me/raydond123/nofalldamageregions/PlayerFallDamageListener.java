package me.raydond123.nofalldamageregions;

import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerFallDamageListener implements Listener {

    NoFallDamageRegions plugin;

    public PlayerFallDamageListener(NoFallDamageRegions plugin) {

        this.plugin = plugin;

    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {

        Entity entity = e.getEntity();

        if(entity instanceof Player) {

            if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {

                Player player = (Player) entity;

                Location location = player.getLocation();

                RegionContainer container = plugin.worldGuardPlugin.getRegionContainer();
                RegionManager rm = container.get(location.getWorld());

                ApplicableRegionSet ar = rm.getApplicableRegions(location);

                for(ProtectedRegion region : ar) {

                    for(String string : plugin.getConfig().getStringList("regions")) {

                        if(string.equalsIgnoreCase(region.getId())) {

                            if(player.hasPermission("nfdr.nodamage")) {

                                e.setCancelled(true);

                            }

                        }

                    }

                }

            }

        }

    }

}
