package me.caiyudu.dedehack.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.caiyudu.dedehack.DeDeHackMod;
import me.caiyudu.dedehack.events.render.EventRenderEntityName;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RenderPlayer.class)
public class MixinRenderPlayer
{
    @Inject(method = "renderEntityName", at = @At("HEAD"), cancellable = true)
    public void renderLivingLabel(AbstractClientPlayer entityIn, double x, double y, double z, String name, double distanceSq, CallbackInfo info)
    {
        EventRenderEntityName l_Event = new EventRenderEntityName(entityIn, x, y, z, name, distanceSq);
        DeDeHackMod.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled())
            info.cancel();
    }
}
