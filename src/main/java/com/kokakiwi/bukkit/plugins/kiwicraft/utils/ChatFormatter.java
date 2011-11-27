package com.kokakiwi.bukkit.plugins.kiwicraft.utils;

import java.util.Map;

public class ChatFormatter
{
    public static String format(String source, Map<String, String> replacements)
    {
        String result = source.replace('&', '\u00A7');
        
        for (final String key : replacements.keySet())
        {
            result = result.replaceAll("\\{" + key.toUpperCase() + "\\}",
                    replacements.get(key));
        }
        
        return result;
    }
}
