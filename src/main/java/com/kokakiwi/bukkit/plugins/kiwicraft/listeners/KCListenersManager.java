package com.kokakiwi.bukkit.plugins.kiwicraft.listeners;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;

import com.kokakiwi.bukkit.plugins.kiwicraft.KiwiCraft;

public class KCListenersManager
{
    // Base
    private final KiwiCraft        plugin;
    private final PluginManager    pm;
    
    // Listeners
    private final KCPlayerListener playerListener;
    private final KCServerListener serverListener;
    
    public KCListenersManager(KiwiCraft plugin, PluginManager pm)
    {
        this.plugin = plugin;
        this.pm = pm;
        
        playerListener = new KCPlayerListener(plugin);
        serverListener = new KCServerListener(plugin);
    }
    
    public void registerEvents()
    {
        pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener,
                Event.Priority.Highest, plugin);
        pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener,
                Event.Priority.Normal, plugin);
        pm.registerEvent(Event.Type.PLAYER_KICK, playerListener,
                Event.Priority.Normal, plugin);
        pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener,
                Event.Priority.Normal, plugin);
        
        pm.registerEvent(Event.Type.PLUGIN_ENABLE, serverListener,
                Event.Priority.Normal, plugin);
    }
}
