package me.caiyudu.dedehack.events.player;

import me.caiyudu.dedehack.events.MinecraftEvent;

public class EventPlayerLeave extends MinecraftEvent
{
    private String _name;
    private String _id;
    
    public EventPlayerLeave(String name, String id)
    {
        _name = name;
        _id = id;
    }

    public String getName()
    {
        return _name;
    }
    
    public String getId()
    {
        return _id;
    }
}
