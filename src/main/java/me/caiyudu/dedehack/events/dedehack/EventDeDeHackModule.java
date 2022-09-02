package me.caiyudu.dedehack.events.dedehack;

import me.caiyudu.dedehack.events.MinecraftEvent;
import me.caiyudu.dedehack.module.Module;

public class EventDeDeHackModule extends MinecraftEvent
{
    public final Module Mod;
    
    public EventDeDeHackModule(final Module p_Mod)
    {
        super();
        Mod = p_Mod;
    }
}
