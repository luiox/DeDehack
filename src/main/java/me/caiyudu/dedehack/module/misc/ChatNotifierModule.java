package me.caiyudu.dedehack.module.misc;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.caiyudu.dedehack.events.client.EventClientTick;
import me.caiyudu.dedehack.events.dedehack.EventDeDeHackModuleDisable;
import me.caiyudu.dedehack.events.dedehack.EventDeDeHackModuleEnable;
import me.caiyudu.dedehack.managers.NotificationManager;
import me.caiyudu.dedehack.module.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;

public class ChatNotifierModule extends Module
{
    public ChatNotifierModule()
    {
        super("ChatNotifier", new String[]
        { "" }, "Notifiys you in chat and notification system when a mod is enabled/disabled", "NONE", -1,
                ModuleType.MISC);
    }

    @EventHandler
    private Listener<EventDeDeHackModuleEnable> OnModEnable = new Listener<>(p_Event ->
    {
        if(p_Event.Mod.getDisplayName() == "ManualDupe")
            return;

        String l_Msg = String.format("%s was enabled.",
                ChatFormatting.GREEN + p_Event.Mod.getDisplayName() + ChatFormatting.AQUA);

        SendMessage(l_Msg);
        NotificationManager.Get().AddNotification("ChatNotifier", l_Msg);
    });

    @EventHandler
    private Listener<EventDeDeHackModuleDisable> OnModDisable = new Listener<>(p_Event ->
    {
        if(p_Event.Mod.getDisplayName() == "ManualDupe")
            return;

        String l_Msg = String.format("%s was disabled.",
                ChatFormatting.RED + p_Event.Mod.getDisplayName() + ChatFormatting.AQUA);

        SendMessage(l_Msg);
        NotificationManager.Get().AddNotification("ChatNotifier", l_Msg);
    });
}
