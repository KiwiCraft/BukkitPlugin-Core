package com.kokakiwi.bukkit.plugins.kiwicraft.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandArgs
{
    private final String       command;
    private final List<String> args = new ArrayList<String>();
    
    public CommandArgs(String[] args)
    {
        command = args[0];
        
        if (args.length > 1)
        {
            for (int i = 1; i < args.length; i++)
            {
                this.args.add(args[i]);
            }
        }
    }
    
    public String getCommand()
    {
        return command;
    }
    
    public List<String> getArgs()
    {
        return args;
    }
    
    public int argsLength()
    {
        return args.size();
    }
    
    public String getString(int index)
    {
        if (index > args.size())
        {
            return null;
        }
        
        return args.get(index);
    }
    
    public int getInteger(int index)
    {
        final String s = getString(index);
        
        if (s == null)
        {
            return 0;
        }
        
        return Integer.parseInt(s);
    }
    
    public boolean getBoolean(int index)
    {
        final String s = getString(index);
        
        if (s == null)
        {
            return false;
        }
        
        return Boolean.parseBoolean(s);
    }
}
