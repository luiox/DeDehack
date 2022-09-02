package me.caiyudu.dedehack.events.render;

import me.caiyudu.dedehack.events.MinecraftEvent;

public class EventRenderSetupFog extends MinecraftEvent
{
    public int StartCoords;
    public float PartialTicks;

    public EventRenderSetupFog(int startCoords, float partialTicks)
    {
        StartCoords = startCoords;
        PartialTicks = partialTicks;
    }

}
