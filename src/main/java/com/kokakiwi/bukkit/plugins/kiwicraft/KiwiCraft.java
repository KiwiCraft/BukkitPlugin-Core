package com.kokakiwi.bukkit.plugins.kiwicraft;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.kokakiwi.bukkit.plugins.kiwicraft.commands.HelpCommand;
import com.kokakiwi.bukkit.plugins.kiwicraft.commands.KCCommandExecutor;
import com.kokakiwi.bukkit.plugins.kiwicraft.commands.ReloadCommand;
import com.kokakiwi.bukkit.plugins.kiwicraft.listeners.KCListenersManager;

public class KiwiCraft extends JavaPlugin
{
    public final static Logger logger = Logger.getLogger("KiwiCraft");
    
    private PluginManager      pm;
    private KCListenersManager listenersManager;
    private KCCommandExecutor  commandExecutor;
    
    public void onEnable()
    {
        registerListeners();
        loadConfiguration();
        registerCommands();
        
        logger.info("KiwiCraft Bukkit Plugin v" + getDescription().getVersion()
                + " is enabled.");
    }
    
    public void onDisable()
    {
        logger.info("KiwiCraft Bukkit Plugin v" + getDescription().getVersion()
                + " is disabled.");
    }
    
    private void registerListeners()
    {
        pm = getServer().getPluginManager();
        listenersManager = new KCListenersManager(this, pm);
        listenersManager.registerEvents();
    }
    
    private void loadConfiguration()
    {
        getConfig().options().copyDefaults(true);
        getConfig().options().copyHeader(false);
        
        try
        {
            getConfig().save(new File(getDataFolder(), "config.yml"));
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }
    }
    
    private void registerCommands()
    {
        commandExecutor = new KCCommandExecutor(this);
        
        commandExecutor.register("help", HelpCommand.class);
        commandExecutor.register("?", HelpCommand.class);
        
        commandExecutor.register("reload", ReloadCommand.class);
        commandExecutor.register("r", ReloadCommand.class);
        
        getCommand("kc").setExecutor(commandExecutor);
    }
    
    public KCListenersManager getListenersManager()
    {
        return listenersManager;
    }
    
    public KCCommandExecutor getCommandExecutor()
    {
        return commandExecutor;
    }
    
}
