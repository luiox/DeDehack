package me.caiyudu.dedehack.mixin;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import me.caiyudu.dedehack.DeDeHackMod;

import java.util.Map;

public class MixinLoaderForge implements IFMLLoadingPlugin
{
    private static boolean isObfuscatedEnvironment = false;

    public MixinLoaderForge()
    {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.dedehack.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
        DeDeHackMod.log.info(MixinEnvironment.getDefaultEnvironment().getObfuscationContext());
    }

    @Override
    public String[] getASMTransformerClass()
    {
        return new String[0];
    }

    @Override
    public String getModContainerClass()
    {
        return null;
    }

    @Override
    public String getSetupClass()
    {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data)
    {
        isObfuscatedEnvironment = (boolean) (Boolean) data.get("runtimeDeobfuscationEnabled");
    }

    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }
}
