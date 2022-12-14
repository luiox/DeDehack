package me.caiyudu.dedehack.module.movement;

import me.caiyudu.dedehack.events.player.EventPlayerUpdateMoveState;
import me.caiyudu.dedehack.managers.ModuleManager;
import me.caiyudu.dedehack.module.Module;
import me.caiyudu.dedehack.module.world.AutoTunnelModule;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;

public final class AutoWalkModule extends Module
{
    public AutoWalkModule()
    {
        super("AutoWalk", new String[]
        { "AW" }, "Automatically walks forward", "NONE", 0xC224DB, ModuleType.MOVEMENT);
    }
    
    private AutoTunnelModule _autoTunnel;
    
    @Override
    public void init()
    {
        _autoTunnel = (AutoTunnelModule)ModuleManager.Get().GetMod(AutoTunnelModule.class);
    }

    @EventHandler
    private Listener<EventPlayerUpdateMoveState> OnUpdateMoveState = new Listener<>(p_Event ->
    {
        if (!NeedPause())
            mc.player.movementInput.moveForward++;
    });
    
    private boolean NeedPause()
    {
        if (_autoTunnel.PauseAutoWalk())
            return true;
        
        return false;
    }
}
