package me.raydond123.nofalldamageregions;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class AddRegionCommand implements CommandExecutor {

    NoFallDamageRegions plugin;

    public AddRegionCommand(NoFallDamageRegions plugin) {

        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {

        if(!sender.hasPermission("nfdr.addregion")) return false;

        if(args.length == 1) {

            String region = args[0];

            List<String> list = plugin.getConfig().getStringList("regions");

            list.add(region);

            plugin.getConfig().set("regions", list);

            plugin.saveConfig();

            sender.sendMessage(plugin.prefix + ChatColor.translateAlternateColorCodes('&', "&5Your region, &9" + region + "&5, has been added!"));

        } else {

            sender.sendMessage(plugin.prefix + ChatColor.translateAlternateColorCodes('&', "&5Correct Usage: /addregion [region]"));

        }

        return false;
    }

}
