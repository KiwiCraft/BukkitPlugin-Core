package com.kokakiwi.bukkit.plugins.kiwicraft.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.kokakiwi.bukkit.plugins.kiwicraft.KiwiCraft;

public class KCCommandExecutor implements CommandExecutor
{
    private final KiwiCraft                                  plugin;
    private final Map<String, KCCommand>                     executors = new HashMap<String, KCCommand>();
    private final Map<Class<? extends KCCommand>, KCCommand> classes   = new HashMap<Class<? extends KCCommand>, KCCommand>();
    
    public KCCommandExecutor(KiwiCraft plugin)
    {
        this.plugin = plugin;
    }
    
    public boolean onCommand(CommandSender sender, Command command,
            String label, String[] args)
    {
        if (args.length > 0)
        {
            final CommandArgs arguments = new CommandArgs(args);
            final KCCommand executor = executors.get(arguments.getCommand());
            
            if (executor != null)
            {
                return executor.execute(sender, arguments.getCommand(),
                        arguments, plugin);
            }
            
            return false;
        }
        
        return false;
    }
    
    public void register(String name, KCCommand executor)
    {
        classes.put(executor.getClass(), executor);
        executors.put(name, executor);
    }
    
    @SuppressWarnings("unchecked")
    public <T extends KCCommand> void register(String name, Class<T> clazz)
    {
        T executor = (T) classes.get(clazz);
        
        if (executor == null)
        {
            try
            {
                executor = clazz.newInstance();
            }
            catch (final InstantiationException e)
            {
                e.printStackTrace();
            }
            catch (final IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
        
        register(name, executor);
    }
    
}
