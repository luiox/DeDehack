package me.caiyudu.dedehack.mixin.client;

import me.caiyudu.dedehack.DeDeHackMod;
import me.caiyudu.dedehack.events.entity.EventSteerEntity;
import net.minecraft.entity.passive.EntityLlama;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLlama.class)
public class MixinEntityLlama
{
    @Inject(method = "canBeSteered", at = @At("HEAD"), cancellable = true)
    public void canBeSteered(CallbackInfoReturnable<Boolean> cir)
    {
        EventSteerEntity event = new EventSteerEntity();
        DeDeHackMod.EVENT_BUS.post(event);

        if (event.isCancelled())
        {
            cir.cancel();
            cir.setReturnValue(true);
        }
    }
}
