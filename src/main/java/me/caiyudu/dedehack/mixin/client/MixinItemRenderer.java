package me.caiyudu.dedehack.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.caiyudu.dedehack.DeDeHackMod;
import me.caiyudu.dedehack.events.render.EventRenderSetupFog;
import me.caiyudu.dedehack.events.render.EventRenderUpdateEquippedItem;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.ItemRenderer;

@Mixin(ItemRenderer.class)
public class MixinItemRenderer
{
    @Inject(method = "updateEquippedItem", at = @At("HEAD"), cancellable = true)
    public void updateEquippedItem(CallbackInfo p_Info)
    {
        EventRenderUpdateEquippedItem l_Event = new EventRenderUpdateEquippedItem();
        DeDeHackMod.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled())
            p_Info.cancel();
    }
}
