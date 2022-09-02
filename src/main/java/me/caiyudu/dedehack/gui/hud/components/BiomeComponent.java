package me.caiyudu.dedehack.gui.hud.components;

import me.caiyudu.dedehack.gui.hud.HudComponentItem;
import me.caiyudu.dedehack.managers.ModuleManager;
import me.caiyudu.dedehack.module.ui.HudModule;
import me.caiyudu.dedehack.util.colors.SalRainbowUtil;
import me.caiyudu.dedehack.util.render.RenderUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;

public class BiomeComponent extends HudComponentItem
{
    public BiomeComponent()
    {
        super("Biome", 2, 95);
    }

    private HudModule l_Hud = (HudModule) ModuleManager.Get().GetMod(HudModule.class);
    private SalRainbowUtil Rainbow = new SalRainbowUtil(9);
    private int l_I = 0;

    @Override
    public void render(int p_MouseX, int p_MouseY, float p_PartialTicks)
    {
        super.render(p_MouseX, p_MouseY, p_PartialTicks);

        final BlockPos pos = mc.player.getPosition();
        final Chunk chunk = mc.world.getChunk(pos);
        final Biome biome = chunk.getBiome(pos, mc.world.getBiomeProvider());

        SetWidth(RenderUtil.getStringWidth(biome.getBiomeName()));
        SetHeight(RenderUtil.getStringHeight(biome.getBiomeName()));

        Rainbow.OnRender();
        RenderUtil.drawStringWithShadow(biome.getBiomeName(), GetX(), GetY(), l_Hud.Rainbow.getValue() ? Rainbow.GetRainbowColorAt(Rainbow.getRainbowColorNumber()) : -1);
    }
}
