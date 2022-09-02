package me.caiyudu.dedehack.events.player;

import me.caiyudu.dedehack.events.MinecraftEvent;
import net.minecraft.entity.Entity;
import net.minecraft.potion.Potion;

public class EventPlayerIsPotionActive extends MinecraftEvent
{
    public Potion potion;
    
    public EventPlayerIsPotionActive(Potion p_Potion)
    {
        super();
        
        potion = p_Potion;
    }
}
