package me.caiyudu.dedehack.events.render;

import me.caiyudu.dedehack.events.MinecraftEvent;

public class EventRenderUpdateLightmap extends MinecraftEvent
{
    public float PartialTicks;
    
    public EventRenderUpdateLightmap(float p_PartialTicks)
    {
        super();
        PartialTicks = p_PartialTicks;
    }
}
