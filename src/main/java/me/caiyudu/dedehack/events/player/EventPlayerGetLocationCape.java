package me.caiyudu.dedehack.events.player;

import me.caiyudu.dedehack.events.MinecraftEvent;
import me.caiyudu.dedehack.mixin.client.MixinAbstractClientPlayer;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class EventPlayerGetLocationCape extends MinecraftEvent
{
    private ResourceLocation m_Location = null;
    public AbstractClientPlayer Player;
    
    public EventPlayerGetLocationCape(AbstractClientPlayer abstractClientPlayer)
    {
        super();
        
        Player = abstractClientPlayer;
    }
    
    public void SetResourceLocation(ResourceLocation p_Location)
    {
        m_Location = p_Location;
    }

    public ResourceLocation GetResourceLocation()
    {
        return m_Location;
    }
}
