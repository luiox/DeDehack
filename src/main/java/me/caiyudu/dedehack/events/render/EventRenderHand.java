package me.caiyudu.dedehack.events.render;

import me.caiyudu.dedehack.events.MinecraftEvent;

public class EventRenderHand extends MinecraftEvent
{
    public float PartialTicks;
    public int Pass;

    public EventRenderHand(float partialTicks, int pass)
    {
        super();
        
        PartialTicks = partialTicks;
        Pass = pass;
    }

}
