package me.caiyudu.dedehack.events.player;

import me.caiyudu.dedehack.events.MinecraftEvent;
import me.caiyudu.dedehack.mixin.client.MixinAbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class EventPlayerGetLocationSkin extends MinecraftEvent
{
    private ResourceLocation m_Location = null;
    public MixinAbstractClientPlayer Player;
    
    public EventPlayerGetLocationSkin(MixinAbstractClientPlayer p_Player)
    {
        super();
        
        Player = p_Player;
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
