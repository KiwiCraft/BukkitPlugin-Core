package com.kokakiwi.bukkit.plugins.kiwicraft.listeners;

import java.util.HashMap;
import java.util.Map;

import org.anjocaido.groupmanager.data.Group;
import org.anjocaido.groupmanager.data.User;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.kokakiwi.bukkit.plugins.kiwicraft.KiwiCraft;
import com.kokakiwi.bukkit.plugins.kiwicraft.utils.ChatFormatter;

public class KCPlayerListener extends PlayerListener
{
    private final KiwiCraft plugin;
    
    public KCPlayerListener(KiwiCraft plugin)
    {
        this.plugin = plugin;
    }
    
    @Override
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        final String message = getMessage(event.getPlayer(), "join");
        if (message != null)
        {
            event.setJoinMessage(message);
        }
    }
    
    @Override
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        final String message = getMessage(event.getPlayer(), "leave");
        if (message != null)
        {
            event.setQuitMessage(message);
        }
    }
    
    @Override
    public void onPlayerKick(PlayerKickEvent event)
    {
        final String message = getMessage(event.getPlayer(), "leave");
        if (message != null)
        {
            event.setLeaveMessage(message);
        }
    }
    
    @Override
    public void onPlayerChat(PlayerChatEvent event)
    {
        String format = plugin.getConfig().getString("chat.format");
        final Map<String, String> keys = new HashMap<String, String>();
        keys.put("PREFIX", getPlayerPrefix(event.getPlayer()));
        keys.put("NAME", event.getPlayer().getDisplayName());
        keys.put("MESSAGE", event.getMessage());
        format = ChatFormatter.format(format, keys);
        
        event.setFormat(format);
    }
    
    public String getMessage(Player player, String label)
    {
        String message = null;
        
        if (plugin.getConfig()
                .get("messages." + player.getName().toLowerCase()) != null)
        {
            message = plugin.getConfig().getString(
                    "messages." + player.getName().toLowerCase() + "." + label);
            final Map<String, String> keys = new HashMap<String, String>();
            keys.put("name", player.getDisplayName());
            message = ChatFormatter.format(message, keys);
        }
        else
        {
            message = plugin.getConfig().getString("messages.all." + label);
            final Map<String, String> keys = new HashMap<String, String>();
            keys.put("name", player.getDisplayName());
            message = ChatFormatter.format(message, keys);
        }
        
        return message;
    }
    
    public String getPlayerPrefix(Player player)
    {
        String prefix = "";
        
        User user = plugin.getGroupManager().getWorldsHolder().getDefaultWorld().getUser(player.getName());
        Group group = user.getGroup();
        prefix = group.getVariables().getVarString("prefix");
        
        return prefix;
    }
}
