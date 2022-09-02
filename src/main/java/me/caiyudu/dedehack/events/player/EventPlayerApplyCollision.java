package me.caiyudu.dedehack.events.player;

import me.caiyudu.dedehack.events.MinecraftEvent;
import net.minecraft.entity.Entity;

public class EventPlayerApplyCollision extends MinecraftEvent
{
    public Entity entity;
    
    public EventPlayerApplyCollision(Entity p_Entity)
    {
        super();
        
        entity = p_Entity;
    }
}
