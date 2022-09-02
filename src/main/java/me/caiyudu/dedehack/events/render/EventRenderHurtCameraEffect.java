package me.caiyudu.dedehack.events.render;

import me.caiyudu.dedehack.events.MinecraftEvent;

public class EventRenderHurtCameraEffect extends MinecraftEvent
{
    public float Ticks;
    
    public EventRenderHurtCameraEffect(float p_Ticks)
    {
        super();
        Ticks = p_Ticks;
    }
}
