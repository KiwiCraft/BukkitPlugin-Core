package com.kokakiwi.bukkit.plugins.kiwicraft;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import me.taylorkelly.help.Help;

import org.anjocaido.groupmanager.GroupManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.kokakiwi.bukkit.plugins.kiwicraft.commands.ExpCommand;
import com.kokakiwi.bukkit.plugins.kiwicraft.commands.HelpCommand;
import com.kokakiwi.bukkit.plugins.kiwicraft.commands.KCCommandExecutor;
import com.kokakiwi.bukkit.plugins.kiwicraft.commands.ReloadCommand;
import com.kokakiwi.bukkit.plugins.kiwicraft.listeners.KCListenersManager;

public class KiwiCraft extends JavaPlugin
{
    public final static Logger logger       = Logger.getLogger("KiwiCraft");
    
    private PluginManager      pm;
    private KCListenersManager listenersManager;
    private KCCommandExecutor  commandExecutor;
    
    // Plugin dependencies
    private GroupManager       groupManager = null;
    private Help               help         = null;
    
    public void onEnable()
    {
        registerListeners();
        loadConfiguration();
        registerCommands();
        registerHelp();
        
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
        
        commandExecutor.register("exp", ExpCommand.class);
        
        getCommand("kc").setExecutor(commandExecutor);
    }
    
    public void registerHelp()
    {
        if (help != null)
        {
            help.registerCommand("gm", "Change your Gamemode", this);
            help.registerCommand("gm [0/1]", "Change your Gamemode", this);
            help.registerCommand("gm [Player name] [0/1]",
                    "Change player's Gamemode", this, "gamemode.change.others");
        }
    }
    
    public KCListenersManager getListenersManager()
    {
        return listenersManager;
    }
    
    public KCCommandExecutor getCommandExecutor()
    {
        return commandExecutor;
    }
    
    public GroupManager getGroupManager()
    {
        if (groupManager == null)
        {
            groupManager = (GroupManager) pm.getPlugin("GroupManager");
        }
        
        return groupManager;
    }
    
    public void setGroupManager(GroupManager groupManager)
    {
        this.groupManager = groupManager;
    }
    
    public Help getHelp()
    {
        if (help == null)
        {
            help = (Help) pm.getPlugin("Help");
        }
        
        return help;
    }
    
    public void setHelp(Help help)
    {
        this.help = help;
    }
    
}
