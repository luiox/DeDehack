package me.caiyudu.dedehack.module.movement;

import me.caiyudu.dedehack.events.MinecraftEvent.Era;
import me.caiyudu.dedehack.events.player.EventPlayerMotionUpdate;
import me.caiyudu.dedehack.module.Module;
import me.caiyudu.dedehack.module.Value;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.Minecraft;

public final class SprintModule extends Module
{
    public final Value<Modes> Mode = new Value<Modes>("Mode", new String[]
    { "Mode", "M" }, "The sprint mode to use.", Modes.Rage);

    private enum Modes
    {
        Rage,
        Legit
    }

    public SprintModule()
    {
        super("Sprint", new String[]
        { "AutoSprint", "Spr" }, "Automatically sprints for you", "NONE", 0xDB2450, ModuleType.MOVEMENT);
    }

    @Override
    public void onDisable()
    {
        super.onDisable();

        if (mc.world != null)
        {
            mc.player.setSprinting(false);
        }
    }

    @Override
    public String getMetaData()
    {
        return String.valueOf(Mode.getValue());
    }

    @EventHandler
    private Listener<EventPlayerMotionUpdate> OnPlayerUpdate = new Listener<>(p_Event ->
    {
        if (p_Event.getEra() != Era.PRE)
            return;
        
        switch (this.Mode.getValue())
        {
            case Rage:
                if ((mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown())
                        && !(mc.player.isSneaking()) && !(mc.player.collidedHorizontally) && !(mc.player.getFoodStats().getFoodLevel() <= 6f))
                {
                    mc.player.setSprinting(true);
                }
                break;
            case Legit:
                if ((mc.gameSettings.keyBindForward.isKeyDown()) && !(mc.player.isSneaking()) && !(mc.player.isHandActive()) && !(mc.player.collidedHorizontally) && mc.currentScreen == null
                        && !(mc.player.getFoodStats().getFoodLevel() <= 6f))
                {
                    mc.player.setSprinting(true);
                }
                break;
        }
    });

}
