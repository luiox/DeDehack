package me.caiyudu.dedehack.events.player;

import me.caiyudu.dedehack.events.MinecraftEvent;

public class EventPlayerPushOutOfBlocks extends MinecraftEvent
{
    public double X, Y, Z;
    
    public EventPlayerPushOutOfBlocks(double p_X, double p_Y, double p_Z)
    {
        super();
        
        X = p_X;
        Y = p_Y;
        Z = p_Z;
    }
}
