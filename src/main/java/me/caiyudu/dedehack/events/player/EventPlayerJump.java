package me.caiyudu.dedehack.events.player;

import me.caiyudu.dedehack.events.MinecraftEvent;

public class EventPlayerJump extends MinecraftEvent
{
    public double MotionX;
    public double MotionY;
    
    public EventPlayerJump(double p_MotionX, double p_MotionY)
    {
        super();
        MotionX = p_MotionX;
        MotionY = p_MotionY;
    }
}
