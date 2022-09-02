package me.caiyudu.dedehack.gui.hud.components;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.caiyudu.dedehack.DeDeHackMod;
import me.caiyudu.dedehack.gui.hud.HudComponentItem;
import me.caiyudu.dedehack.main.Wrapper;
import me.caiyudu.dedehack.managers.ModuleManager;
import me.caiyudu.dedehack.module.Value;
import me.caiyudu.dedehack.module.ui.HudModule;
import me.caiyudu.dedehack.util.colors.SalRainbowUtil;
import me.caiyudu.dedehack.util.render.RenderUtil;

public class WatermarkComponent extends HudComponentItem
{
    public final Value<Boolean> Reliant = new Value<Boolean>("Reliant", new String[]
    { "" }, "Shows reliant text instead of dedehack", false);

    private static HudModule l_Hud = (HudModule) ModuleManager.Get().GetMod(HudModule.class);
    private SalRainbowUtil Rainbow = new SalRainbowUtil(9);
    private int l_I = 0;

    private static String WatermarkString = l_Hud.Rainbow.getValue() ?  DeDeHackMod.NAME + " " + DeDeHackMod.VERSION : DeDeHackMod.NAME + ChatFormatting.WHITE + " " + DeDeHackMod.VERSION;

    public WatermarkComponent()
    {
        super("Watermark", 2, 2);
        SetHidden(false);
    }

    @Override
    public void render(int p_MouseX, int p_MouseY, float p_PartialTicks)
    {
        super.render(p_MouseX, p_MouseY, p_PartialTicks);
        
        if (Reliant.getValue())
        {
            final String l_Text = "Reliant (rel-1.12.2-Forge)";
            
            Wrapper.GetMC().fontRenderer.drawStringWithShadow(l_Text, GetX(), GetY(), l_Hud.Rainbow.getValue() ? Rainbow.GetRainbowColorAt(Rainbow.getRainbowColorNumber()) : 0xFFFFFF);
            
            SetWidth(Wrapper.GetMC().fontRenderer.getStringWidth(l_Text));
            SetHeight(Wrapper.GetMC().fontRenderer.FONT_HEIGHT);
        }
        else
        {
            Rainbow.OnRender();
            RenderUtil.drawStringWithShadow(WatermarkString, GetX(), GetY(), l_Hud.Rainbow.getValue() ? Rainbow.GetRainbowColorAt(Rainbow.getRainbowColorNumber()) : 0x2ACCED);
            
            SetWidth(RenderUtil.getStringWidth(WatermarkString));
            SetHeight(RenderUtil.getStringHeight(WatermarkString));
        }
    }
}
