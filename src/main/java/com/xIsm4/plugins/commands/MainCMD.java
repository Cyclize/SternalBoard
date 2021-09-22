
package com.xIsm4.plugins.commands;

import com.xIsm4.plugins.Structure;
import com.xIsm4.plugins.managers.animation.AnimationManager;
import com.xIsm4.plugins.utils.placeholders.PlaceholderUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class MainCMD implements CommandExecutor {

    private Structure plugin;

    public MainCMD(Structure plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command commands, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + " [X] U aren't a player!");
            return false;
        }
        Player player = (Player) sender;
        if (!(args.length > 0)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', " &fUse &e/sternalboard help &fto see more info about the plugin"));
            return true;
        }

        if (args[0].equalsIgnoreCase("version")) {
            player.sendMessage(PlaceholderUtils.colorize("&bThe version of the plugin it's stable build."));
            return true;

        } else if (args[0].equalsIgnoreCase("help")) {
            if (!(player.hasPermission("sternalboard.help"))) {
                player.sendMessage(PlaceholderUtils.colorize("&cU don't have permissions to use this command"));
                return true;
            }

            player.sendMessage(PlaceholderUtils.colorize("&9This server is runing &b&lSternal&f&lBoard &9by xIsm4"));
            return true;

        } else if (args[0].equalsIgnoreCase("reload")) {
            if (!(player.hasPermission("sternalboard.reload"))) {
                player.sendMessage(PlaceholderUtils.colorize("&cU don't have permissions to use this command"));
                return true;
            }
            plugin.reloadConfig();
            plugin.setAnimateScore(plugin.getConfig().getBoolean("settings.animated"));
            plugin.getScoreboardManager().reload();

            if (plugin.isAnimationEnabled()){
                if (plugin.getAnimationManager() != null){
                    plugin.loadAnimConfig();
                    plugin.getAnimationManager().reload();
                }
                else {
                    plugin.loadAnimConfig();
                    plugin.setAnimationManager(new AnimationManager());
                }
            }
            else {
                if (plugin.getAnimationManager() != null){
                    plugin.getAnimationManager().reload();
                }
            }

            player.sendMessage(PlaceholderUtils.colorize("&aThe plugin has been reloaded sucesfully"));
            return true;
        } else {
            player.sendMessage(PlaceholderUtils.colorize("&cThe command doesn't exist!"));
        }
        return true;
    }
}