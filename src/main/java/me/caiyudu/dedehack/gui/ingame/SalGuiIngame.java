package me.caiyudu.dedehack.gui.ingame;

import me.caiyudu.dedehack.DeDeHackMod;
import me.caiyudu.dedehack.events.render.EventRenderGameOverlay;
import me.caiyudu.dedehack.managers.ModuleManager;
import me.caiyudu.dedehack.module.ui.ReliantChatModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class SalGuiIngame extends GuiIngameForge
{
    public SalGuiIngame(Minecraft mc)
    {
        super(mc);

        ObfuscationReflectionHelper.setPrivateValue(GuiIngame.class, this, new SalGuiPlayerTabOverlay(mc, this), "field_175196_v");
        
        ReliantChatModule l_Mod = (ReliantChatModule)ModuleManager.Get().GetMod(ReliantChatModule.class);
        
        if (l_Mod != null && l_Mod.isEnabled())
            l_Mod.Activate();
    }

    @Override
    public void renderGameOverlay(float partialTicks)
    {
        super.renderGameOverlay(partialTicks);
        
        DeDeHackMod.EVENT_BUS.post(new EventRenderGameOverlay(partialTicks, new ScaledResolution(mc)));
    }
}
