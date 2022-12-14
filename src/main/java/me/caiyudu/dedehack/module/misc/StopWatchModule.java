package me.caiyudu.dedehack.module.misc;

import me.caiyudu.dedehack.events.client.EventClientTick;
import me.caiyudu.dedehack.module.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;

public class StopWatchModule extends Module
{
    public StopWatchModule()
    {
        super("Stopwatch", new String[] {""}, "Counts a stopwatch starting from 0 when toggled.", "NONE", -1, ModuleType.MISC);
    }
    
    public long StartMS;
    public long ElapsedMS;
    
    @Override
    public void onEnable()
    {
        super.onEnable();
        
        StartMS = System.currentTimeMillis();
        ElapsedMS = System.currentTimeMillis();
    }

    @EventHandler
    private Listener<EventClientTick> OnTick = new Listener<>(p_Event ->
    {
        ElapsedMS = System.currentTimeMillis();
    });
}
