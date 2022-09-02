package me.caiyudu.dedehack.module.misc;

import me.caiyudu.dedehack.events.minecraft.GuiScreenEvent;
import me.caiyudu.dedehack.events.player.EventPlayerUpdate;
import me.caiyudu.dedehack.module.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraftforge.client.model.PerspectiveMapWrapper;

public class AutoRespawnModule extends Module {

    public AutoRespawnModule()
    {
        super("AutoRespawn", new String[] {"AutoRespawn"}, "Automatically respawn.", "NONE", 0xFFFB11, ModuleType.MISC);
    }

    @EventHandler
    private final Listener<EventPlayerUpdate> listener = new Listener<>(p_Event -> {
        if (mc.player.getHealth() == 0.0f && mc.player != null) {
            mc.player.respawnPlayer();
            mc.displayGuiScreen(null);
        }
    });
}
