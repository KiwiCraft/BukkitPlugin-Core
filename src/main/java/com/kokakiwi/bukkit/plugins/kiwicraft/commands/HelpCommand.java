package com.kokakiwi.bukkit.plugins.kiwicraft.commands;

import org.bukkit.command.CommandSender;

import com.kokakiwi.bukkit.plugins.kiwicraft.KiwiCraft;

public class HelpCommand extends KCCommand
{
    
    @Override
    public boolean execute(CommandSender sender, String command,
            CommandArgs args, KiwiCraft plugin)
    {
        sender.sendMessage("[WIP...]");
        
        return true;
    }
    
}
