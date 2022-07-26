package com.xism4.sternalboard.utils;

import com.xism4.sternalboard.SternalBoard;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlaceholderUtils {
    public static String sanitizeString(Player player, String text) {
        if (SternalBoard.getInstance().getServer().getPluginManager().getPlugin(
                "PlaceholderAPI") != null) {
            return colorize(PlaceholderAPI.setPlaceholders(player, text)
            );
        } else {
            return colorize(text);
        }
    }

    public static String colorize(String text) {
        if (
                Bukkit.getVersion().contains("1.16") ||
                Bukkit.getVersion().contains("1.17") ||
                Bukkit.getVersion().contains("1.18") ||
                Bukkit.getVersion().contains("1.19")
        ) {
            MiniMessage mm = MiniMessage.miniMessage();
            text = LegacyComponentSerializer.legacySection().serialize(mm.deserialize(text));
        }
        return ChatColor.translateAlternateColorCodes(
                '&', text
        );
    }

    public static String parsePAPI(Player player, String text) {
        if (SternalBoard.getInstance().getServer().getPluginManager().getPlugin(
                "PlaceholderAPI") != null) {
            return PlaceholderAPI.setPlaceholders(player, text);
        }
        return (colorize(text)
        );
    }
}
