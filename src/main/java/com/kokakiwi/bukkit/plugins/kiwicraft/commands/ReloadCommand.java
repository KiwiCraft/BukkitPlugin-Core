package com.kokakiwi.bukkit.plugins.kiwicraft.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;

import com.kokakiwi.bukkit.plugins.kiwicraft.KiwiCraft;

public class ReloadCommand extends KCCommand
{
    
    @Override
    public boolean execute(CommandSender sender, String command,
            CommandArgs args, KiwiCraft plugin)
    {
        try
        {
            plugin.getConfig().load(
                    new File(plugin.getDataFolder(), "config.yml"));
        }
        catch (final FileNotFoundException e)
        {
            try
            {
                plugin.getConfig().save(
                        new File(plugin.getDataFolder(), "config.yml"));
            }
            catch (final IOException e1)
            {
                e1.printStackTrace();
            }
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }
        catch (final InvalidConfigurationException e)
        {
            e.printStackTrace();
        }
        
        return true;
    }
    
}
