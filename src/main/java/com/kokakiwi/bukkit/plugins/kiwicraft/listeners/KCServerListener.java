package com.kokakiwi.bukkit.plugins.kiwicraft.listeners;

import me.taylorkelly.help.Help;

import org.anjocaido.groupmanager.GroupManager;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;

import com.kokakiwi.bukkit.plugins.kiwicraft.KiwiCraft;

public class KCServerListener extends ServerListener
{
    private final KiwiCraft plugin;
    
    public KCServerListener(KiwiCraft plugin)
    {
        this.plugin = plugin;
    }
    
    @Override
    public void onPluginEnable(PluginEnableEvent event)
    {
        if (event.getPlugin().getDescription().getName()
                .equalsIgnoreCase("GroupManager"))
        {
            KiwiCraft.logger.info("[KiwiCraft] GroupManager v"
                    + event.getPlugin().getDescription().getVersion()
                    + " binded.");
            plugin.setGroupManager((GroupManager) event.getPlugin());
        }
        
        if (event.getPlugin().getDescription().getName()
                .equalsIgnoreCase("Help"))
        {
            KiwiCraft.logger.info("[KiwiCraft] Help v"
                    + event.getPlugin().getDescription().getVersion()
                    + " binded.");
            plugin.setHelp((Help) event.getPlugin());
            plugin.registerHelp();
        }
    }
}
