package me.caiyudu.dedehack.events.player;

import me.caiyudu.dedehack.events.MinecraftEvent;

public class EventPlayerSendChatMessage extends MinecraftEvent
{
    public String Message;

    public EventPlayerSendChatMessage(String p_Message)
    {
        super();
        
        Message = p_Message;
    }

}
