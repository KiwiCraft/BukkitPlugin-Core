package com.kokakiwi.bukkit.plugins.kiwicraft.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.kokakiwi.bukkit.plugins.kiwicraft.KiwiCraft;

public class ExpCommand extends KCCommand
{
    
    @Override
    public boolean execute(CommandSender sender, String command,
            CommandArgs args, KiwiCraft plugin)
    {
        if (args.argsLength() == 0)
        {
            if (sender instanceof Player)
            {
                sender.sendMessage("WIP.");
                return true;
            }
        }
        else if (args.argsLength() == 1)
        {
            if (sender instanceof Player)
            {
                if (((Player) sender).isOp())
                {
                    ((Player) sender).giveExp(args.getInteger(0));
                }
            }
        }
        else if (args.argsLength() == 2)
        {
            if (sender instanceof ConsoleCommandSender)
            {
                Player target = plugin.getServer().getPlayer(args.getString(0));
                if (target != null)
                {
                    target.giveExp(args.getInteger(1));
                }
            }
            else
            {
                if (((Player) sender).isOp())
                {
                    Player target = plugin.getServer().getPlayer(
                            args.getString(0));
                    target.giveExp(args.getInteger(0));
                }
            }
        }
        
        return false;
    }
    
}
