package me.caiyudu.dedehack.module.movement;

import me.caiyudu.dedehack.events.entity.EventHorseSaddled;
import me.caiyudu.dedehack.events.entity.EventSteerEntity;
import me.caiyudu.dedehack.events.liquid.EventLiquidCollisionBB;
import me.caiyudu.dedehack.events.network.EventNetworkPacketEvent;
import me.caiyudu.dedehack.module.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;

public class EntityControlModule extends Module
{
    public EntityControlModule()
    {
        super("EntityControl", new String[]
        { "AntiSaddle", "EntityRide", "NoSaddle" }, "Allows you to control llamas, horses, pigs without a saddle/carrot", "NONE", -1, ModuleType.MOVEMENT);
    }

    @EventHandler
    private Listener<EventSteerEntity> OnSteerEntity = new Listener<>(p_Event ->
    {
        p_Event.cancel();
    });

    @EventHandler
    private Listener<EventHorseSaddled> OnHorseSaddled = new Listener<>(p_Event ->
    {
        p_Event.cancel();
    });
}
