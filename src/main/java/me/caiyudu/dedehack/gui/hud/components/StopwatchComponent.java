package me.caiyudu.dedehack.gui.hud.components;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.caiyudu.dedehack.gui.hud.HudComponentItem;
import me.caiyudu.dedehack.managers.ModuleManager;
import me.caiyudu.dedehack.module.misc.StopWatchModule;
import me.caiyudu.dedehack.module.ui.HudModule;
import me.caiyudu.dedehack.util.colors.SalRainbowUtil;
import me.caiyudu.dedehack.util.render.RenderUtil;

import java.util.concurrent.TimeUnit;

public class StopwatchComponent extends HudComponentItem
{
    private StopWatchModule Stopwatch = null;
    
    public StopwatchComponent()
    {
        super("Stopwatch", 2, 275);
        
        Stopwatch = (StopWatchModule) ModuleManager.Get().GetMod(StopWatchModule.class);
    }

    private HudModule l_Hud = (HudModule) ModuleManager.Get().GetMod(HudModule.class);
    private SalRainbowUtil Rainbow = new SalRainbowUtil(9);
    private int l_I = 0;

    @Override
    public void render(int p_MouseX, int p_MouseY, float p_PartialTicks)
    {
        super.render(p_MouseX, p_MouseY, p_PartialTicks);

        final String l_Seconds = l_Hud.Rainbow.getValue() ? "Seconds " + TimeUnit.MILLISECONDS.toSeconds(Stopwatch.ElapsedMS - Stopwatch.StartMS) : ChatFormatting.GRAY + "Seconds " + ChatFormatting.WHITE + TimeUnit.MILLISECONDS.toSeconds(Stopwatch.ElapsedMS - Stopwatch.StartMS);

        Rainbow.OnRender();
        RenderUtil.drawStringWithShadow(l_Seconds, GetX(), GetY(), l_Hud.Rainbow.getValue() ? Rainbow.GetRainbowColorAt(Rainbow.getRainbowColorNumber()) : -1);

        SetWidth(RenderUtil.getStringWidth(l_Seconds));
        SetHeight(RenderUtil.getStringHeight(l_Seconds));
    }
}
