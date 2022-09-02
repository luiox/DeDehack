package me.caiyudu.dedehack.module.movement;


import me.caiyudu.dedehack.events.player.EventPlayerUpdate;
import me.caiyudu.dedehack.module.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;


public class HorseJump extends Module {

    public HorseJump()
    {
        super("HorseJump", new String[]
                { "HorseJump" }, "Modifies how high a horse jump.", "NONE", 0x24DB3E, ModuleType.MOVEMENT);
    }

    @EventHandler
    private final Listener<EventPlayerUpdate> listener = new Listener<>(p_Event -> {
        mc.player.horseJumpPower = 1;
        mc.player.horseJumpPowerCounter = -10;
    });
}
