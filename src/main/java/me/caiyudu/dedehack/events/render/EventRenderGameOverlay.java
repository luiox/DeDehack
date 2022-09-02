package me.caiyudu.dedehack.events.render;

import me.caiyudu.dedehack.events.MinecraftEvent;
import me.caiyudu.dedehack.main.Wrapper;
import net.minecraft.client.gui.ScaledResolution;

public class EventRenderGameOverlay extends MinecraftEvent
{
    public float PartialTicks;
    public ScaledResolution scaledResolution;

    public EventRenderGameOverlay(float p_PartialTicks, ScaledResolution p_Res)
    {
        super();
        PartialTicks = p_PartialTicks;
        scaledResolution = p_Res;
    }

    public ScaledResolution getScaledResolution()
    {
        return scaledResolution;
    }

}
