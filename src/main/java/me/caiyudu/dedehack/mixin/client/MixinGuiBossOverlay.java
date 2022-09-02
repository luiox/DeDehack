package me.caiyudu.dedehack.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.caiyudu.dedehack.DeDeHackMod;
import me.caiyudu.dedehack.events.render.EventRenderBossHealth;
import net.minecraft.client.gui.GuiBossOverlay;

@Mixin(GuiBossOverlay.class)
public class MixinGuiBossOverlay
{
    @Inject(method = "renderBossHealth", at = @At("HEAD"), cancellable = true)
    public void renderBossHealth(CallbackInfo p_Info)
    {
        EventRenderBossHealth l_Event = new EventRenderBossHealth();
        DeDeHackMod.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled())
            p_Info.cancel();
    }
}
