package me.caiyudu.dedehack.main;

import me.caiyudu.dedehack.DeDeHackMod;
import me.caiyudu.dedehack.events.network.EventNetworkPacketEvent;
import me.caiyudu.dedehack.events.player.EventPlayerJoin;
import me.caiyudu.dedehack.events.player.EventPlayerLeave;
import me.caiyudu.dedehack.managers.UUIDManager;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listenable;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.network.play.server.SPacketPlayerListItem;
import net.minecraft.util.text.TextComponentString;

public class AlwaysEnabledModule implements Listenable
{
    public AlwaysEnabledModule()
    {
    }

    public void init()
    {
        DeDeHackMod.EVENT_BUS.subscribe(this);
    }

    public static String LastIP = null;
    public static int LastPort = -1;


    @EventHandler
    private Listener<EventNetworkPacketEvent> PacketEvent = new Listener<>(p_Event ->
    {
        if (p_Event.getPacket() instanceof SPacketChat)
        {
            final SPacketChat packet = (SPacketChat) p_Event.getPacket();

            if (packet.getChatComponent() instanceof TextComponentString)
            {
                final TextComponentString component = (TextComponentString) packet.getChatComponent();

                // if (component.getFormattedText().toLowerCase().contains("polymer") || component.getFormattedText().toLowerCase().contains("veteranhack"))
                // p_Event.cancel();
                // 22 Aug 2020 , Beepjay: I got rid of these lines of code.... who needs
            }
        }
        else if (p_Event.getPacket() instanceof C00Handshake)
        {
            final C00Handshake packet = (C00Handshake) p_Event.getPacket();
            if (packet.getRequestedState() == EnumConnectionState.LOGIN)
            {
                LastIP = packet.ip;
                LastPort = packet.port;
            }
        }
        else if (p_Event.getPacket() instanceof SPacketPlayerListItem)
        {
            final SPacketPlayerListItem packet = (SPacketPlayerListItem) p_Event.getPacket();
            final Minecraft mc = Wrapper.GetMC();
            if (mc.player != null && mc.player.ticksExisted >= 1000)
            {

                if (packet.getAction() == SPacketPlayerListItem.Action.ADD_PLAYER)
                {
                    for (SPacketPlayerListItem.AddPlayerData playerData : packet.getEntries()) {
                        if (playerData.getProfile().getId() != mc.session.getProfile().getId()) {
                            new Thread(() -> {
                                final String name = UUIDManager.Get().resolveName(playerData.getProfile().getId().toString());
                                if (name != null) {
                                    if (mc.player != null && mc.player.ticksExisted >= 1000)

                                        DeDeHackMod.EVENT_BUS.post(new EventPlayerJoin(name, playerData.getProfile().getId().toString()));

                                }
                            }).start();
                        }
                    }

                }
                if (packet.getAction() == SPacketPlayerListItem.Action.REMOVE_PLAYER)
                {
                    for (SPacketPlayerListItem.AddPlayerData playerData : packet.getEntries()) {
                        if (playerData.getProfile().getId() != mc.session.getProfile().getId()) {
                            new Thread(() -> {
                                final String name = UUIDManager.Get().resolveName(playerData.getProfile().getId().toString());
                                if (name != null)
                                    DeDeHackMod.EVENT_BUS.post(new EventPlayerLeave(name, playerData.getProfile().getId().toString()));
                            }).start();
                        }
                    }
                }
            }
        }
    });
}