package me.caiyudu.dedehack.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.caiyudu.dedehack.DeDeHackMod;
import me.caiyudu.dedehack.events.render.EventRenderEntity;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;

@Mixin(RenderManager.class)
public class MixinRenderManager
{
    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    public void isPotionActive(Entity entityIn, ICamera camera, double camX, double camY, double camZ, final CallbackInfoReturnable<Boolean> callback)
    {
        EventRenderEntity event = new EventRenderEntity(entityIn, camera, camX, camY, camZ);
        DeDeHackMod.EVENT_BUS.post(event);

        if (event.isCancelled())
            callback.setReturnValue(false);
    }
    
}
