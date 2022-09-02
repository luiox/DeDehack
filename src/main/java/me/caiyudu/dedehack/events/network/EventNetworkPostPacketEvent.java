package me.caiyudu.dedehack.events.network;

import me.caiyudu.dedehack.events.MinecraftEvent;
import net.minecraft.network.Packet;

public class EventNetworkPostPacketEvent extends EventNetworkPacketEvent
{
    public EventNetworkPostPacketEvent(Packet p_Packet)
    {
        super(p_Packet);
    }
}
