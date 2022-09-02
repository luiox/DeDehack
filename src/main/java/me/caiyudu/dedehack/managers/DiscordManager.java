package me.caiyudu.dedehack.managers;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import me.caiyudu.dedehack.main.DeDeHack;
import me.caiyudu.dedehack.main.Wrapper;
import me.caiyudu.dedehack.module.misc.DiscordRPCModule;

public class DiscordManager
{
    private DiscordRPCModule _rpcModule = null;
    private Thread _thread = null;

    public void enable()
    {
        _rpcModule = (DiscordRPCModule)ModuleManager.Get().GetMod(DiscordRPCModule.class);

        DiscordRPC lib = DiscordRPC.INSTANCE;
        String applicationId = "719038221807386665";
        String steamId = "";
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.ready = (user) -> System.out.println("Ready!");
        lib.Discord_Initialize(applicationId, handlers, true, steamId);
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 2000; // epoch second
        lib.Discord_UpdatePresence(presence);
        presence.largeImageKey = "image";
        presence.largeImageText = "https://discord.gg/dedehack Join The DeDeHack Development Discord!";
        _thread = new Thread(() ->
        {
            while (!Thread.currentThread().isInterrupted())
            {
                lib.Discord_RunCallbacks();
                presence.details = _rpcModule.generateDetails();
                presence.state = _rpcModule.generateState();
                lib.Discord_UpdatePresence(presence);
                try
                {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored)
                {
                }
            }
        }, "RPC-Callback-Handler");

        _thread.start();
    }

    public void disable() throws InterruptedException
    {
        if (_thread != null)
           _thread.interrupt();
    }

    public static DiscordManager Get()
    {
        return DeDeHack.GetDiscordManager();
    }
}