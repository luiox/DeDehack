package me.caiyudu.dedehack.events.entity;

import me.caiyudu.dedehack.events.MinecraftEvent;
import net.minecraft.entity.Entity;

public class EventEntityRemoved extends MinecraftEvent
{
    private Entity m_Entity;

    public EventEntityRemoved(Entity p_Entity)
    {
        m_Entity = p_Entity;
    }

    public Entity GetEntity()
    {
        return m_Entity;
    }
}
