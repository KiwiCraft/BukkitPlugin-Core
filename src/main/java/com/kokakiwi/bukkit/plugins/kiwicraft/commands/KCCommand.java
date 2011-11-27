package com.kokakiwi.bukkit.plugins.kiwicraft.commands;

import org.bukkit.command.CommandSender;

import com.kokakiwi.bukkit.plugins.kiwicraft.KiwiCraft;

public abstract class KCCommand
{
    public abstract boolean execute(CommandSender sender, String command,
            CommandArgs args, KiwiCraft plugin);
}
