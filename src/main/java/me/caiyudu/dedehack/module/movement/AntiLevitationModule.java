package me.caiyudu.dedehack.module.movement;

import me.caiyudu.dedehack.events.player.EventPlayerIsPotionActive;
import me.caiyudu.dedehack.module.Module;
import me.caiyudu.dedehack.module.Value;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.init.MobEffects;

public final class AntiLevitationModule extends Module
{
    public AntiLevitationModule()
    {
        super("AntiLevitation", new String[]
        { "NoLevitate" }, "Prevents you from levitating", "NONE", 0xC224DB, ModuleType.MOVEMENT);
    }

    @EventHandler
    private Listener<EventPlayerIsPotionActive> IsPotionActive = new Listener<>(p_Event ->
    {
        if (p_Event.potion == MobEffects.LEVITATION)
            p_Event.cancel();
    });
}
